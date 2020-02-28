package com.msz.Scheduler;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.msz.VO.MessageTeamplate;
import com.msz.common.LeaseCommon;
import com.msz.dao.*;
import com.msz.model.*;
import com.msz.service.MszLeaseService;
import com.msz.service.MszOrderHistoryService;
import com.msz.service.MszOrderInfoService;
import com.msz.util.DateUtil;
import com.msz.util.MessageTeamplateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class BillNoticeTask {

    private static Logger logger = LoggerFactory.getLogger(BillNoticeTask.class);

    @Autowired
    MszLeaseService leaseService;
    @Autowired
    LeaseCommon leaseCommon;
    @Autowired
    MszLeaseMapper leaseMapper;
    @Autowired
    MszAccountMapper accountMapper;
    @Autowired
    MszMessageMapper messageMapper;
    @Autowired
    MszOrderInfoMapper orderInfoMapper;
    @Autowired
    MszOrderInfoService orderInfoService;
    @Autowired
    MszArrearsMapper arrearsMapper;
    @Autowired
    MszOrderHistoryMapper historyMapper;
    @Autowired
    MszOrderHistoryService historyService;
    @Autowired
    MszLeaseChargeMapper leaseChargeMapper;
    @Autowired
    SysUserMapper userMapper;

    /**
     * 账单日生成交费信息
     */
    @Scheduled(cron = "${lease.billDay.room.crop}")
    @Transactional
    public void createBillOrderInfo() {
        try {
            List<MszLease> leaseList = leaseService.selectList(new EntityWrapper<MszLease>().eq("status", "1"));
            if (leaseList != null && leaseList.size() > 0) {
                MszLease lease;
                int billDay; // 账单日
                Calendar calendar;
                int nowday; // 今日
                int collectMoneyDay; // 交费日
                int maxMonthDay; // 本月最大日
                int maxLastMonthDay; // 上月最大日
                for (int i = 0; i < leaseList.size(); i++) {
                    lease = leaseList.get(i);
                    billDay = Integer.parseInt(lease.getBillDay());
                    collectMoneyDay = Integer.parseInt(lease.getCollectMoneyDay());
                    calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    nowday = calendar.get(Calendar.DAY_OF_MONTH);
                    maxMonthDay = DateUtil.getMaxMonthDay();
                    maxLastMonthDay = DateUtil.getMaxLastMonthDay();
                    billDay = getBillDay(billDay, maxLastMonthDay);
                    if (!isMaxMonthDay(collectMoneyDay, maxMonthDay)) {
                        collectMoneyDay = maxMonthDay; // 账单交费日等于本月最大日
                    }
                    if (nowday == billDay) {
                        //添加交费信息
                        leaseCommon.insertApplyOrderInfo(lease);
                        //固定费用
                        List<MszLeaseCharge> mszLeaseChargeList = leaseChargeMapper.selectList(new EntityWrapper<MszLeaseCharge>().eq("leaseId", lease.getId()).eq("isDel", 0));
                        //推送信息
                        MszMessage message = new MszMessage();
                        message.setCreateTime(new Date());
                        message.setReceiverId(lease.getTenantId());
                        message.setType("1");//租约消息
                        MessageTeamplate messageTeamplate = MessageTeamplateUtil.collectMoneyDayTenant(mszLeaseChargeList,lease.getReceiveTotal(), new Date(),collectMoneyDay);
                        message.setTitle(messageTeamplate.getTitle());
                        message.setContentText(messageTeamplate.getContentText());
                        messageMapper.insert(message);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    private int getBillDay(int billDay, int maxLastMonthDay) {
        if (billDay == 2){
            billDay = maxLastMonthDay;
        } else if (billDay == 1){
            billDay = maxLastMonthDay -1;
        } else{
            billDay = billDay -2;
        }
        return billDay;
    }

    /**
     * 执行中修改状态为即期 (一周以内)
     */
    @Scheduled(cron = "${lease.update.room.crop}")
    public void comSoonLease() {
        try {
            leaseMapper.updateSpotLease();
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    /**
     * 欠租逾期
     */
    /* @Scheduled(cron = "${lease.update.min.crop}")*/
    @Scheduled(cron = "${lease.update.data.crop}")
    @Transactional
    public void rentArrears() {
        try {
            List<MszArrears> arrearsList = arrearsMapper.selectList(new EntityWrapper<MszArrears>()
                    .eq("is_del", "0").le("end_time", new Date()));
            if (arrearsList != null && arrearsList.size() > 0) {
                for (int i = 0; i < arrearsList.size(); i++) {
                    MszArrears mszArrears = arrearsList.get(i);
                    // 提醒消息
                    MszOrderInfo orderInfo = orderInfoMapper.getOrderInfo(mszArrears.getOrderId());
                    if (orderInfo != null) {
                        MszMessage message = new MszMessage();
                        message.setCreateTime(new Date());
                        message.setReceiverId(orderInfo.getTenantId());//房客id
                        MszAccount mszAccount = accountMapper.selectById(orderInfo.getTenantId());
                        message.setType("4");// 欠租通知
                        message.setTitle("欠租通知");
                        message.setContentText(mszAccount.getName()+"，请您及时交还所欠租金"+ mszArrears.getArrearsAmount());
                        messageMapper.insert(message);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }

    }


    /**
     * 租金缴费日 提醒
     */
    @Scheduled(cron = "${lease.billDay.room.crop}")
    @Transactional
    public void collectMoneyDay() {
        try {
            List<MszLease> leaseList = leaseService.selectList(new EntityWrapper<MszLease>().eq("status", "1"));
            if (leaseList != null && leaseList.size() > 0) {
                int collectMoneyDay;
                int maxMonthDay;
                Calendar calendar;
                int nowday; // 今日
                int month; // 月份
                for (int i = 0; i < leaseList.size(); i++) {
                    MszLease lease = leaseList.get(i);
                    collectMoneyDay = Integer.parseInt(lease.getCollectMoneyDay());
                    maxMonthDay = DateUtil.getMaxMonthDay();
                    calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    nowday = calendar.get(Calendar.DAY_OF_MONTH);//日
                    if (!isMaxMonthDay(collectMoneyDay, maxMonthDay)) {
                        collectMoneyDay = maxMonthDay; // 账单交费日等于本月最大日
                    }
                    if (nowday > collectMoneyDay) {
                        MszOrderInfo orderInfo = orderInfoMapper.getOrderInfo(lease.getId());
                        calendar.setTime(orderInfo.getCreateTime());
                        month = calendar.get(Calendar.MONTH)+1;
                        if (orderInfo != null) {
                            // 逾期交费通知
                            insertMessage(lease, month, orderInfo.getNo(), orderInfo.getReceiveTotal());
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }

    }

    private boolean isMaxMonthDay(int collectMoneyDay, int maxMonthDay) {
        if (collectMoneyDay <= maxMonthDay) {
            return true;
        }
        return false;
    }

    private void insertMessage(MszLease lease,Integer month, String orderNo, BigDecimal total) {
        // 1.房客信息
        MszMessage message = new MszMessage();
        message.setCreateTime(new Date());
        message.setReceiverId(lease.getTenantId());//房客id
        message.setType("5");// 逾期交费通知
        MessageTeamplate messageTeamplate = MessageTeamplateUtil.collectMoneyDayMessage(orderNo,month, total);
        message.setTitle(messageTeamplate.getTitle());
        message.setContentText(messageTeamplate.getContentText());
        messageMapper.insert(message);
        // 2. 业务员信息
        MszMessage message2 = new MszMessage();
        message2.setCreateTime(new Date());
        message2.setReceiverId(lease.getUserId());//  业务员id
        SysUser user = userMapper.selectById(lease.getUserId());
        message2.setIsUser("1");
        message2.setType("5");// 逾期交费通知
        MessageTeamplate messageTeamplate2 = MessageTeamplateUtil.collectMoneyDayMessage(user.getTrueName(),month, orderNo, total);
        message2.setTitle(messageTeamplate2.getTitle());
        message2.setContentText(messageTeamplate2.getContentText());
        messageMapper.insert(message2);
    }





}
