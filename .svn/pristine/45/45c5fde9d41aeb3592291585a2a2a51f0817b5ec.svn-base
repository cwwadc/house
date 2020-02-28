package com.msz.util;

import com.msz.VO.MessageTeamplate;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 业务员消息模板
 * @author: cww
 * @date: 2019/7/10 11:47
 */
public class SendMessage {

    private static MessageTeamplate teamplate = new MessageTeamplate();

    /**
     * 新建租约 发送给业务员
     * @param salesmanName 业务员
     * @param tenantName 房客
     * @param leaseNo 租约编号
     * @param rentPrice 租金
     * @param depositPrice 押金
     * @param total 合计
     * @return
     */
    public static MessageTeamplate insertLeaseSalesman(String salesmanName,String tenantName,String leaseNo, BigDecimal rentPrice, BigDecimal depositPrice, BigDecimal total){
        teamplate.setTitle(salesmanName+"您的房客"+tenantName+"的租约已签订");
        teamplate.setContentText("您的房客"+tenantName+"成功签订租约（编号"+leaseNo+"），本期租金"+rentPrice+"元，" +
                "押金"+depositPrice+"元，合计"+total+"元，请在48小时内完成支付，逾期租约将失效！");
        return teamplate;
    }


    /**
     * 新建租约正常交费 发送给业务员
     * @param salesmanName 业务员
     * @param tenantName 房客
     * @param leaseNo 租约编号
     * @return
     */
    public static MessageTeamplate orderInfoSuccessSalesman(String salesmanName,String tenantName, String leaseNo){
        teamplate.setTitle(salesmanName+"您的房客"+tenantName+"的租约已生效");
        teamplate.setContentText("您的房客"+tenantName+"交费成功，租约"+leaseNo+"已生效！");
        return teamplate;
    }


    /**
     * 新建租约逾期未交费 发送给业务员
     * @param salesmanName 业务员
     * @param tenantName 房客
     * @param leaseNo 租约编号
     * @return
     */
    public static MessageTeamplate overdueSalesman(String salesmanName, String tenantName, String leaseNo){
        teamplate.setTitle(salesmanName+"您的房客"+tenantName+"的租约已失效");
        teamplate.setContentText("很遗憾，您的房客"+tenantName+"签订的"+leaseNo+"租约已失效！");
        return teamplate;
    }


    /**
     * 租金到期、交费提醒 (只发送给业务员)
     * @param salesmanName 业务员
     * @param tenantName 房客
     * @param rentPrice 租金
     * @param monthEndTime 本月交租时间
     * @return
     */
    public static MessageTeamplate rentExpiresLeaseSalesman(String salesmanName, String tenantName, BigDecimal rentPrice, Date monthEndTime){
        teamplate.setTitle(salesmanName+"您的房客"+tenantName+"的租约账单已生成");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(monthEndTime);
        int year = calendar.get(Calendar.YEAR);//年
        int month = calendar.get(Calendar.MONTH)+1;//月
        int day = calendar.get(Calendar.DAY_OF_MONTH);//日
        teamplate.setContentText("您的房客"+tenantName+year+"年"+month+"月份的租约账单金额为：租金"+rentPrice+"元，请在"+month+"月"+day+"日前完成支付，逾期将影响租约的执行！");
        return teamplate;
    }

    /**
     * 中止租约 发送给业务员
     * @param salesmanName 业务员
     * @param tenantName 房客
     * @param leaseNo 租约编号
     * @param  total 退款金额
     * @return
     */
    public static MessageTeamplate terminationLeaseSalesman(String salesmanName, String tenantName, String leaseNo, BigDecimal total){
        teamplate.setTitle(salesmanName+"您的房客"+tenantName+"的租约已中止");
        teamplate.setContentText("很遗憾，您的房客"+tenantName+"签订的"+leaseNo+"租约已中止，退款"+total+"元申请已生成！");
        return teamplate;
    }


    /**
     * 租约即将到期(只发送给业务员)
     * @param salesmanName 业务员
     * @param tenantName 房客
     * @param leaseNo 租约编号
     * @return
     */
    public static MessageTeamplate dueLeaseSalesman(String salesmanName, String tenantName, String leaseNo){
        teamplate.setTitle(salesmanName+"您的房客"+tenantName+"的租约即将到期");
        teamplate.setContentText("您的房客"+tenantName+"签订的"+leaseNo+"租约即将期，请及时签约！");
        return teamplate;
    }

    /**
     * 租约到期 发送给业务员
     * @param salesmanName 业务员
     * @param tenantName 房客
     * @param leaseNo 租约编号
     * @param total 退款金额
     * @return
     */
    public static MessageTeamplate becomeDueLeaseSalesman(String salesmanName, String tenantName, String leaseNo, BigDecimal total){
        teamplate.setTitle(salesmanName+"您的租约已到期");
        teamplate.setContentText("您签订的"+leaseNo+"租约已到期，押金退款"+total+"元申请已生成，请及时签约！");
        return teamplate;
    }
    
}
