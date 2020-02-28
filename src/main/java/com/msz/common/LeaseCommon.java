package com.msz.common;

import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.msz.VO.MessageTeamplate;
import com.msz.dao.*;
import com.msz.model.*;
import com.msz.service.MszLeaseChargeService;
import com.msz.service.MszRoomService;
import com.msz.service.SysUserService;
import com.msz.util.MessageTeamplateUtil;
import com.msz.util.MoblieMessageUtil;
import com.msz.util.SendMessage;
import com.msz.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LeaseCommon {

    @Autowired
    MszOrderInfoMapper mszOrderInfoMapper;
    @Autowired
    MszAccountMapper accountMapper;
    @Autowired
    MszRoomMapper mszRoomMapper;
    @Autowired
    MszRoomService roomService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysOrgMapper orgMapper;
    @Autowired
    MszMessageMapper messageMapper;
    @Autowired
    MszOrderChargeMapper mszOrderChargeMapper;
    @Autowired
    MszLeaseChargeService mszLeaseChargeService;

    /**
     * 添加交费信息
     *
     * @param lease
     * @param receiveTotal
     */
    public void insertOrderInfo(MszLease lease, BigDecimal receiveTotal, BigDecimal rentPrice,BigDecimal paidAmount, List<MszLeaseCharge> leaseChargeList) {
        //3.生成交费信息
        MszOrderInfo mszOrderInfo = new MszOrderInfo();
        mszOrderInfo.setOrgId(lease.getOrgId());//
        mszOrderInfo.setRoomId(lease.getRoomId());
        mszOrderInfo.setCreateTime(new Date());
        mszOrderInfo.setLeaseId(lease.getId());//获取插入后生成主键租约id
        mszOrderInfo.setRentPrice(rentPrice);//租金
        mszOrderInfo.setDepositPrice(lease.getDepositPrice());//押金
        mszOrderInfo.setBillDay(lease.getBillDay());//账单日
        mszOrderInfo.setCollectMoneyDay(lease.getCollectMoneyDay());//租金交费日
        mszOrderInfo.setAvailable(lease.getAvailable());//应用到每期交费
        mszOrderInfo.setArrearsFlag("1"); // 欠租
        mszOrderInfo.setFlag(lease.getFlag());//修改金额提醒
        String orderNo = UUIDUtils.getOrderIdByTime();
        mszOrderInfo.setNo(orderNo);//缴费编号
        mszOrderInfo.setTenantId(lease.getTenantId());//房客ID(电话号码)
        mszOrderInfo.setLandlordId(lease.getLandlordId());//房东ID
        mszOrderInfo.setEndTime(lease.getEndTime());//结束时间
        mszOrderInfo.setReceiveTotal(receiveTotal);
        mszOrderInfo.setOweTotal(receiveTotal);
        if (paidAmount.compareTo(new BigDecimal(0)) > 0){
            mszOrderInfo.setScheduleDesc("定金已抵扣" + paidAmount  );
        }
        mszOrderInfo.setUpdateTime(new Date(new Date().getTime() - 24 * 60 * 60 * 1000));
        mszOrderInfoMapper.insert(mszOrderInfo);

        //订单交费项
//        MszOrderCharge charge = new MszOrderCharge();
//        if(leaseChargeList != null){
//            for (MszLeaseCharge item : leaseChargeList) {
//                charge.setCreateTime(new Date());
//                charge.setChargeName(item.getChargeName());
//                charge.setReceiveAmount(item.getChargePrice());
//                charge.setOrderId(mszOrderInfo.getId());
//                charge.setAvailable("0");
//                mszOrderChargeMapper.insert(charge);
//            }
//        }
    }

    /**
     * 修改交费信息
     *
     * @param lease
     * @param receiveTotal
     */
    public void updateOrderInfo(MszLease lease, BigDecimal rentPrice, BigDecimal receiveTotal,MszOrderInfo orderInfo) {
        // 修改交费信息

        orderInfo.setRentPrice(rentPrice);//租金
        if(orderInfo.getDepositPrice().compareTo(new BigDecimal(0)) == 1){
            orderInfo.setDepositPrice(lease.getDepositPrice());//押金
        }
        orderInfo.setBillDay(lease.getBillDay());//账单日
        orderInfo.setCollectMoneyDay(lease.getCollectMoneyDay());//租金交费日
        orderInfo.setAvailable(lease.getAvailable());//应用到每期交费
        orderInfo.setFlag(lease.getFlag());//修改金额提醒
        orderInfo.setReceiveTotal(receiveTotal);
        BigDecimal oweTotal = receiveTotal.subtract(orderInfo.getPaidTotal());
        orderInfo.setOweTotal(oweTotal);
        orderInfo.setArrearsFlag("1");
        orderInfo.setUpdateTime(new Date(new Date().getTime() - 24 * 60 * 60 * 1000));
        mszOrderInfoMapper.updateById(orderInfo);
    }

    /**
     * 定时生成交费信息： 账单
     *
     * @param lease
     */
    public void insertApplyOrderInfo(MszLease lease) {
        MszOrderInfo mszOrderInfo = new MszOrderInfo();
        //缴费编号
        String orderNo = UUIDUtils.getOrderIdByTime();
        mszOrderInfo.setNo(orderNo);
        //租约
        mszOrderInfo.setLeaseId(lease.getId());
        //房源
        mszOrderInfo.setRoomId(lease.getRoomId());
        MszRoom room = roomService.selectById(lease.getRoomId());
        //房东
        mszOrderInfo.setLandlordId(lease.getLandlordId());
        //房客
        mszOrderInfo.setTenantId(lease.getTenantId());
        //网点
        mszOrderInfo.setOrgId(lease.getOrgId());
        //账单日
        mszOrderInfo.setBillDay(lease.getBillDay());
        //租金交费日
        mszOrderInfo.setCollectMoneyDay(lease.getCollectMoneyDay());
        // 租金  0 不应用到每一期， 1 应用到每一期
        BigDecimal rentPrice  = "1".equals(lease.getAvailable()) ?  lease.getRentPrice() : room.getRentPrice();
        mszOrderInfo.setRentPrice(rentPrice);
        //应收总费用
          //固定费用
        List<MszLeaseCharge> mszLeaseChargeList = mszLeaseChargeService.selectList(new EntityWrapper<MszLeaseCharge>().eq("leaseId", lease.getId()).eq("isDel", 0));
        BigDecimal allChargePrice = new BigDecimal(0.00);
        if(mszLeaseChargeList!=null && mszLeaseChargeList.size()>0){
            for(MszLeaseCharge mszLeaseCharge : mszLeaseChargeList){
                allChargePrice = allChargePrice.add(mszLeaseCharge.getChargePrice());
            }
        }

        BigDecimal receiveTotal = "1".equals(lease.getAvailable()) ? lease.getRentPrice().add(allChargePrice) : room.getRentPrice().add(allChargePrice);
        mszOrderInfo.setReceiveTotal(receiveTotal);
        //应用到每期交费
        mszOrderInfo.setAvailable(lease.getAvailable());
        //金额提醒
        mszOrderInfo.setFlag(lease.getFlag());
        // 欠租
        mszOrderInfo.setArrearsFlag("1");
        //结束时间
        mszOrderInfo.setEndTime(lease.getEndTime());
        //创建时间
        mszOrderInfo.setCreateTime(new Date());
        mszOrderInfoMapper.insert(mszOrderInfo);
    }

    /**
     * 新建租约 推送消息
     *
     * @param lease
     * @param leaseNo
     * @param receiveTotal
     */
    public void sendMessage(MszLease lease, String leaseNo, BigDecimal receiveTotal, List<MszLeaseCharge> leaseChargeList) {
        String str = "";
        if(leaseChargeList != null){
            for (MszLeaseCharge item : leaseChargeList) {
                str += item.getChargeName() + ":" + item.getChargePrice() + "，";
            }
        }

        //1.推送信息给房客
        MszMessage message = new MszMessage();
        MszAccount account1 = accountMapper.selectById(lease.getTenantId());
        String tenantName = account1.getName();
        MessageTeamplate teamplate = MessageTeamplateUtil.insertLeaseTenant(tenantName, leaseNo, lease.getRentPrice(), lease.getDepositPrice(), receiveTotal, str);
        message.setTitle(teamplate.getTitle());
        message.setCreateTime(new Date());
        message.setReceiverId(lease.getTenantId());
        message.setType("1");//租约消息
        message.setContentText(teamplate.getContentText());
        messageMapper.insert(message);
        //2.发送短信给房客
        Map<String, Object> map = new HashMap<>();
        map.put("leaseNo", leaseNo);
        map.put("rentPrice", lease.getRentPrice());
        map.put("depositPrice", lease.getDepositPrice());
        map.put("str", str);
        map.put("total", receiveTotal);
        try {
            MoblieMessageUtil.sendSms(account1.getTel(), map, "SMS_176940672"); //旧版 SMS_171540935
        } catch (ClientException e) {
            e.printStackTrace();
        }
        //5.推送信息给业务员
        MszMessage message3 = new MszMessage();
        String salesmanName = sysUserService.selectById(lease.getUserId()).getTrueName();
        MessageTeamplate sendMessage = SendMessage.insertLeaseSalesman(salesmanName, tenantName, leaseNo, lease.getRentPrice(), lease.getDepositPrice(), receiveTotal, str);
        message3.setTitle(sendMessage.getTitle());
        message3.setContentText(sendMessage.getContentText());
        message3.setCreateTime(new Date());
        message3.setReceiverId(lease.getUserId());
        message3.setIsUser("1");
        message3.setType("1");//租约消息
        messageMapper.insert(message3);
        //3.推送信息给房东
        MszMessage message2 = new MszMessage();
        MszAccount account2 = accountMapper.selectById(lease.getLandlordId());
        MszRoom mszRoom1 = mszRoomMapper.selectById(lease.getRoomId());
        String orgName = orgMapper.selectById(mszRoom1.getOrgId()).getName();
        MessageTeamplate teamplate2 = MessageTeamplateUtil.insertLeaseByLandlord(account2.getName(), mszRoom1.getAddress(), account1.getName(), orgName);
        message2.setTitle(teamplate2.getTitle());
        message2.setCreateTime(new Date());
        message2.setReceiverId(lease.getLandlordId());
        message2.setType("1");//租约消息
        message2.setContentText(teamplate2.getContentText());
        messageMapper.insert(message2);
    }



}
