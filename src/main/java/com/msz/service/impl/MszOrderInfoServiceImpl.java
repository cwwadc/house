package com.msz.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.VO.MessageTeamplate;
import com.msz.VO.MszOrderInfoVo;
import com.msz.VO.RoomStatusNumVO;
import com.msz.common.PagingRequest;
import com.msz.common.UserCommon;
import com.msz.dao.*;
import com.msz.model.*;
import com.msz.service.MszOrderChargeService;
import com.msz.service.MszOrderInfoService;
import com.msz.service.MszWalletService;
import com.msz.util.DateUtil;
import com.msz.util.MessageTeamplateUtil;
import com.msz.util.SendMessage;
import com.msz.util.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 缴费信息 服务实现类
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Service
public class MszOrderInfoServiceImpl extends ServiceImpl<MszOrderInfoMapper, MszOrderInfo> implements MszOrderInfoService {

    @Autowired
    MszOrderInfoMapper mszOrderInfoMapper;
    @Autowired
    MszRoomMapper mszRoomMapper;
    @Autowired
    MszMessageMapper messageMapper;
    @Autowired
    MszAccountMapper accountMapper;
    @Autowired
    MszLeaseMapper leaseMapper;
    @Autowired
    MszOrderHistoryMapper historyMapper;
    @Autowired
    SysUserMapper userMapper;
    @Autowired
    UserCommon userCommon;
    @Autowired
    MszOrderChargeService chargeService;
    @Autowired
    private MszWalletService mszWalletService;
    @Autowired
    private  MszWithdrawMapper withdrawMapper;

    @Override
    public PageInfo<MszOrderInfoVo> listPage(PagingRequest pagingRequest, String status, String endTimeBegin, String endTimeEnd, String collectMoneyDay, String tenantName, SysUser user) {
        PageHelper.startPage(pagingRequest.getOffset(), pagingRequest.getLimit());
        String role = user.getRole();
        List<MszOrderInfoVo> list = null;
        if (role.equals("管理员")) {
            if (status.equals("0")) {
                //房客未交费
                MszOrderInfoVo vo = new MszOrderInfoVo();
                vo.setStatus(status);
                vo.setEndTimeBegin(endTimeBegin);
                vo.setEndTimeEnd(endTimeEnd);
                vo.setTenantName(tenantName);
                vo.setBillDay(collectMoneyDay);
                list = mszOrderInfoMapper.listAll(vo);
                for (MszOrderInfoVo mszOrderInfo : list) {
                    mszOrderInfo.setMszRoom(mszRoomMapper.selectById(mszOrderInfo.getRoomId()));
                }
            }
            if (status.equals("1")) {
                //房客已交费
                MszOrderInfoVo vo = new MszOrderInfoVo();
                vo.setStatus(status);
                vo.setEndTimeBegin(endTimeBegin);
                vo.setEndTimeEnd(endTimeEnd);
                vo.setTenantName(tenantName);
                vo.setBillDay(collectMoneyDay);
                list = historyMapper.findList(vo);
                for (MszOrderInfoVo history : list) {
                    history.setMszRoom(mszRoomMapper.selectById(history.getRoomId()));
                    List<MszOrderCharge> chargeList = chargeService.selectList(new EntityWrapper<MszOrderCharge>().eq("order_id", history.getId()).eq("is_del", "0"));
                    history.setChargeList(chargeList);
                }
            }
        } else {
            if (status.equals("0")) {
                //房客未交费
                MszOrderInfoVo vo = new MszOrderInfoVo();
                vo.setOrgId(user.getOrgId());
                vo.setStatus(status);
                vo.setEndTimeBegin(endTimeBegin);
                vo.setEndTimeEnd(endTimeEnd);
                vo.setTenantName(tenantName);
                vo.setCollectMoneyDay(collectMoneyDay);
                list = mszOrderInfoMapper.listAll(vo);
                for (MszOrderInfoVo mszOrderInfo : list) {
                    mszOrderInfo.setMszRoom(mszRoomMapper.selectById(mszOrderInfo.getRoomId()));
                }
            }
            if (status.equals("1")) {
                //房客已交费
                MszOrderInfoVo vo = new MszOrderInfoVo();
                vo.setOrgId(user.getOrgId());
                vo.setStatus(status);
                vo.setEndTimeBegin(endTimeBegin);
                vo.setEndTimeEnd(endTimeEnd);
                vo.setTenantName(tenantName);
                vo.setCollectMoneyDay(collectMoneyDay);
                list = historyMapper.findList(vo);
                for (MszOrderInfoVo history : list) {
                    history.setMszRoom(mszRoomMapper.selectById(history.getRoomId()));
                    List<MszOrderCharge> chargeList = chargeService.selectList(new EntityWrapper<MszOrderCharge>().eq("order_id", history.getId()).eq("is_del", "0"));
                    history.setChargeList(chargeList);
                }
            }
        }
        return new PageInfo<>(list);
    }

    @Override
    public RoomStatusNumVO getCountNum(SysUser user) {
        String role = user.getRole();
        RoomStatusNumVO vo = new RoomStatusNumVO();
        if (role.equals("管理员")) {
            //缴费状态 0未缴费 1已缴费
            vo.setHandedInNum(historyMapper.selectCount(new EntityWrapper<MszOrderHistory>().eq("status", 1)));//已交
            vo.setUnpaidNum(mszOrderInfoMapper.selectCount(new EntityWrapper<MszOrderInfo>().eq("status", 0)));//未交
        } else {
            vo.setHandedInNum(historyMapper.selectCount(new EntityWrapper<MszOrderHistory>().eq("status", 1)
                    .eq("orgId", user.getOrgId())));//已交
            vo.setUnpaidNum(mszOrderInfoMapper.selectCount(new EntityWrapper<MszOrderInfo>().eq("status", 0)
                    .eq("orgId", user.getOrgId())));//未交
        }
        return vo;
    }

    @Override
    @Transactional
    public boolean remind(Integer id, SysUser user) {

        MszOrderInfo orderInfo = mszOrderInfoMapper.selectById(id);
        orderInfo.setUpdateTime(new Date());
        mszOrderInfoMapper.updateById(orderInfo);
        if (orderInfo != null) {
            MszLease lease = leaseMapper.selectById(orderInfo.getLeaseId());
            BigDecimal receiveTotal = lease.getReceiveTotal();
            //1.租金到期、交费提醒 消息提醒
            MszMessage message = new MszMessage();
            message.setCreateTime(new Date());
            message.setReceiverId(orderInfo.getTenantId());
            message.setType("1");//租约消息
            MessageTeamplate messageTeamplate = MessageTeamplateUtil.rentExpiresLeaseTenant(receiveTotal, new Date());
            message.setTitle(messageTeamplate.getTitle());
            message.setContentText(messageTeamplate.getContentText());
            messageMapper.insert(message);
            //2.租金到期、交费提醒 消息提醒 发送给业务员
            message.setReceiverId(lease.getUserId());//业务员id
            message.setIsUser("1");
            String tenantName = accountMapper.selectById(orderInfo.getTenantId()).getName();
            String salesmanName = userMapper.selectById(lease.getUserId()).getTrueName();//业务员名称
            MessageTeamplate sendMessage = SendMessage.rentExpiresLeaseSalesman(salesmanName, tenantName, receiveTotal, new Date());
            message.setTitle(sendMessage.getTitle());
            message.setContentText(sendMessage.getContentText());
            messageMapper.insert(message);
            //TODO
            //3.租金到期、交费提醒 短信提醒
            return true;
        }
        return false;
    }

    @Override
    public boolean updateOrderInfo(MszOrderInfo orderInfo) {

        //2.修改交费信息
        List<MszOrderCharge> orderChargeList = orderInfo.getOrderChargeList();
        BigDecimal orderChargeTotal = new BigDecimal("0.00");
        if (orderChargeList != null && orderChargeList.size() > 0) {
            for (int i = 0; i < orderChargeList.size(); i++) {
                MszOrderCharge mszOrderCharge = orderChargeList.get(i);
                BigDecimal receiveAmount = mszOrderCharge.getReceiveAmount();
                orderChargeTotal = orderChargeTotal.add(receiveAmount); // 收费项费用
            }
        }
        BigDecimal receiveTotal = orderInfo.getRentPrice().add(orderInfo.getDepositPrice()); // 应收总费用
        BigDecimal paidTotal = orderInfo.getPaidRentPrice().add(orderInfo.getPaidDepositPrice()); // 实收总费用
        BigDecimal oweTotal = receiveTotal.subtract(paidTotal); // 欠收总费用
        orderInfo.setReceiveTotal(receiveTotal);
        orderInfo.setPaidTotal(paidTotal);
        orderInfo.setOweTotal(oweTotal);
        orderInfo.setOperatorId(UserCommon.getCurrentUser().getId());
        mszOrderInfoMapper.updateById(orderInfo);
        return true;
    }

    @Override
    @Transactional
    public boolean receiveOrder(Integer id) {
        MszOrderInfo mszOrderInfo = mszOrderInfoMapper.selectById(id);
        mszOrderInfo.setStatus("1");
        mszOrderInfo.setOperatorId(UserCommon.getCurrentUser().getId());// 操作人ID
        MszOrderHistory history = getMszOrderHistory(mszOrderInfo);
        historyMapper.insert(history);
        mszOrderInfoMapper.deleteById(id);

        // 生成提现记录
        MszAccount mszAccount = accountMapper.selectById(mszOrderInfo.getLandlordId());
        if (mszAccount != null) {
            BigDecimal paidTotal = history.getPaidTotal();
            MszWithdraw withdraw = new MszWithdraw();
            withdraw.setAccountId(mszAccount.getId());
            withdraw.setNo(UUIDUtils.getOrderIdByTime());
            withdraw.setCardNo(mszAccount.getOpenId());
            withdraw.setAccountName(mszAccount.getTel());
            withdraw.setAmt(paidTotal);
            withdraw.setCreateTime(new Date());
            withdraw.setStatus("3"); // 支出
            withdrawMapper.insert(withdraw);
        }
      /*  //变更房东钱包
        MszWallet mszWallet = mszWalletService.selectOne(new EntityWrapper<MszWallet>().eq("accountId", mszOrderInfo.getLandlordId()));
        if(mszWallet!=null){
            mszWallet.setBalance(mszWallet.getBalance().add(mszOrderInfo.getPaidTotal()));
            mszWalletService.updateById(mszWallet);
        }*/
        return true;
    }


    @Override
    public MszOrderInfo countPrice(MszOrderInfo mszOrderInfo) {
        List<MszOrderCharge> list = chargeService.selectList(new EntityWrapper<MszOrderCharge>().eq("order_id", mszOrderInfo.getOrgId()));
        //计算应收
        BigDecimal receiveTotal = new BigDecimal(0);
        for (MszOrderCharge item : list) {
            if (item.getAvailable().equals("1")) {
                if (item.getCharge_end_time() != null && item.getCharge_start_time() != null) {
                        if (DateUtil.isEffectiveDate(new Date(), item.getCharge_start_time(), item.getCharge_end_time())) {
                            //加入应收
                            receiveTotal.add(item.getReceiveAmount());
                        }
                } else {
                    receiveTotal.add(item.getReceiveAmount());
                }
            }
        }
        mszOrderInfo.setReceiveTotal(mszOrderInfo.getReceiveTotal().add(receiveTotal));
        //设置欠费
        mszOrderInfo.setOweTotal(mszOrderInfo.getReceiveTotal().subtract(mszOrderInfo.getPaidTotal()));
        //判断欠费
        mszOrderInfo.setArrearsFlag("1");
        if (!(mszOrderInfo.getOweTotal().compareTo(new BigDecimal(0)) == 0)) {
            mszOrderInfo.setArrearsFlag("0");
        }
        return mszOrderInfo;
    }

    private MszOrderHistory getMszOrderHistory(MszOrderInfo mszOrderInfo) {
        MszOrderHistory history = new MszOrderHistory();
        history.setId(mszOrderInfo.getId());
        history.setLandlordId(mszOrderInfo.getLandlordId());
        history.setReceiveTotal(mszOrderInfo.getReceiveTotal());
        history.setBreachPrice(mszOrderInfo.getBreachPrice());
        history.setCreateTime(new Date());
        history.setDepositPrice(mszOrderInfo.getDepositPrice());
        history.setEndTime(mszOrderInfo.getEndTime());
        history.setLeaseId(mszOrderInfo.getLeaseId());
        history.setNo(mszOrderInfo.getNo());
        history.setOrgId(mszOrderInfo.getOrgId());
        history.setRentPrice(mszOrderInfo.getRentPrice());
        history.setRoomId(mszOrderInfo.getRoomId());
        history.setStatus(mszOrderInfo.getStatus());
        history.setTenantId(mszOrderInfo.getTenantId());
        history.setAvailable(mszOrderInfo.getAvailable());
        history.setBillDay(mszOrderInfo.getBillDay());
        history.setCollectMoneyDay(mszOrderInfo.getCollectMoneyDay());
        history.setFlag(mszOrderInfo.getFlag());
        history.setIsDel(mszOrderInfo.getIsDel());
        history.setOweTotal(mszOrderInfo.getOweTotal());
        if (mszOrderInfo.getReceiveTotal().compareTo(mszOrderInfo.getPaidTotal()) > 0 ){
            history.setArrearsFlag("1");
        }else {
            history.setArrearsFlag("0");
        }
        history.setPaidDepositPrice(mszOrderInfo.getPaidDepositPrice());
        history.setPaidRentPrice(mszOrderInfo.getPaidRentPrice());
        history.setPaidSchedulePrice(mszOrderInfo.getPaidSchedulePrice());
        history.setPaidTotal(mszOrderInfo.getPaidTotal());
        history.setScheduleId(mszOrderInfo.getScheduleId());
        history.setSchedulePrice(mszOrderInfo.getSchedulePrice());
        history.setOperatorId(mszOrderInfo.getOperatorId());
        history.setScheduleDesc(mszOrderInfo.getScheduleDesc());
        return history;
    }

}
