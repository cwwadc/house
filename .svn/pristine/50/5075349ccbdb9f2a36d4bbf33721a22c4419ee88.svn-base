package com.msz.util;

import com.msz.VO.MessageTeamplate;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 消息模板工具类
 * @author: cww
 * @date: 2019/6/27 15:39
 */
public class MessageTeamplateUtil {


    private static MessageTeamplate teamplate = new MessageTeamplate();

    //=====================提现场景推送

    /**
     * 提现申请
     * @param name 提现申请人
     * @param amt 提现金额
     * @param no 提现单号
     * @return
     */
    public static MessageTeamplate withdrawApply(String name, BigDecimal amt, String no){
        teamplate.setTitle(name+"发起一笔提现申请");
        teamplate.setContentText("申请单号："+no+"，"+name+"申请提现"+amt+"元， 请及时审核处理！");
        return teamplate;
    }

    /**
     * 提现成功
     * @param amt 提现金额
     * @param no 提现单号
     * @return
     */
    public static MessageTeamplate withdrawSuccess(BigDecimal amt, String no){
        teamplate.setTitle("申请提现成功");
        teamplate.setContentText("您已成功提现"+amt+"元，提现申请单号"+ no +"，请查收微信钱包记录");
        return teamplate;
    }

    /**
     * 提现失败
     * @param no 提现单号
     * @return
     */
    public static MessageTeamplate withdrawFailure(String no){
        teamplate.setTitle("申请提现失败");
        teamplate.setContentText("您发起的提现申请，单号"+no+"，出现异常，提现失败，请及时与客服联系400*****！");
        return teamplate;
    }

    //=====================退款场景推送


    /**
     * 退款审批--同意时 发送给房客
     * @param tenantName 房客
     * @param leaseNo 租约编号
     * @param total 退款金额
     * @return
     */
    public static MessageTeamplate refundInfoSuccessByTenant(String tenantName, String leaseNo, BigDecimal total){
        teamplate.setTitle(tenantName+"您的租约退款申请成功");
        teamplate.setContentText("您的租约"+leaseNo+"申请退款"+total+"元，实退款"+total+"已成功，请核查系统钱包记录！");
        return teamplate;
    }

    /**
     * 退款审批--同意时 发送给房东
     * @param landlordName 房东
     * @param leaseNo 租约编号
     * @param total 退款金额
     * @return
     */
    public static MessageTeamplate refundInfoSuccessByLandlord(String landlordName, String leaseNo, BigDecimal total){
        teamplate.setTitle(landlordName+"您的房源订单退款成功");
        teamplate.setContentText("您的房源"+leaseNo+"订单申请退款"+total+"元，实退款"+total+"元已成功，请核查系统钱包记录！");
        return teamplate;
    }

    /**
     * 退款审批--拒绝时 发送给房客
     * @param tenantName 房客
     * @param leaseNo 租约编号
     * @param total 退款金额
     * @return
     */
    public static MessageTeamplate refundInfoFailureTenant(String tenantName, String leaseNo, BigDecimal total){
        teamplate.setTitle(tenantName+"您的租约退款申请已失败");
        teamplate.setContentText("您的租约"+leaseNo+"申请退款"+total+"元，已因故失败，请与客服联系 400*****");
        return teamplate;
    }

    /**
     * 退款审批--拒绝时 发送给房东
     * @param landlordName 房东
     * @param leaseNo 租约编号
     * @param total 退款金额
     * @return
     */
    public static MessageTeamplate refundInfoFailureByLandlord(String landlordName, String leaseNo, BigDecimal total){
        teamplate.setTitle(landlordName+"您的房源订单退款已取消");
        teamplate.setContentText("您的房源"+leaseNo+"订单申请退款"+total+"元，已取消！");
        return teamplate;
    }

    //=====================租约场景推送

    /**
     * 新建租约 发送给房客
     * @param tenantName 房客
     * @param leaseNo 租约编号
     * @param rentPrice 租金
     * @param depositPrice 押金
     * @param total 合计
     * @return
     */
        public static MessageTeamplate insertLeaseTenant(String tenantName, String leaseNo, BigDecimal rentPrice, BigDecimal depositPrice,
                                                     BigDecimal total){
        teamplate.setTitle(tenantName+"您的租约已签订");
        teamplate.setContentText("恭喜您成功签订租约（编号"+leaseNo+"），本期租金"+rentPrice+"元，" +
                "押金"+depositPrice+"元，合计"+total+"元，请在48小时内完成支付，逾期租约将失效！");
        return teamplate;
    }

    /**
     * 新建租约 发送给房东
     * @param landlordName 房东
     * @param address 房源地址
     * @param tenantName 房客
     * @return
     */
    public static MessageTeamplate insertLeaseByLandlord(String landlordName, String address, String tenantName, String orgName){
        teamplate.setTitle(landlordName+"您有房源被预订啦");
        teamplate.setContentText("您的房源"+address+"被"+tenantName+"的房客预订了，请留意交费情况！（网点名称:"+orgName+"）");
        return teamplate;
    }

    /**
     * 新建租约正常交费 发送给房客
     * @param tenantName 房客
     * @param leaseNo 租约编号
     * @return
     */
    public static MessageTeamplate orderInfoSuccessTenant(String tenantName, String leaseNo){
        teamplate.setTitle(tenantName+"您的租约已生效");
        teamplate.setContentText("恭喜您交费成功，租约"+leaseNo+"已生效！");
        return teamplate;
    }

    /**
     * 新建租约正常交费 发送给房东
     * @param landlordName 房东
     * @param address 房源地址
     * @return
     */
    public static MessageTeamplate orderInfoSuccessByLandlord(String landlordName, String address){
        teamplate.setTitle(landlordName+"您的房源成功出租");
        teamplate.setContentText("您的房源"+address+"出租成功，请留意后续交租情况！");
        return teamplate;
    }

    /**
     * 新建租约逾期未交费 发送给房客
     * @param tenantName 房客
     * @param leaseNo 租约编号
     * @return
     */
    public static MessageTeamplate overdueTenant(String tenantName, String leaseNo){
        teamplate.setTitle(tenantName+"您的租约已失效");
        teamplate.setContentText("很遗憾，您签订的"+leaseNo+"租约已失效！");
        return teamplate;
    }

    /**
     * 新建租约逾期未交费 发送给房东
     * @param landlordName 房东
     * @param leaseNo 租约编号
     * @return
     */
    public static MessageTeamplate overdueLandlord(String landlordName, String leaseNo){
        teamplate.setTitle(landlordName+"您的房源订单已取消");
        teamplate.setContentText("很遗憾，房客已取消与您房源"+leaseNo+"的订单！");
        return teamplate;
    }

    /**
     * 租金到期、交费提醒 (只发送给房客)
     * @param rentPrice 租金
     * @param monthEndTime 本月交租时间
     * @return
     */
    public static MessageTeamplate rentExpiresLeaseTenant(BigDecimal rentPrice, Date monthEndTime){
        teamplate.setTitle("租金催交通知");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(monthEndTime);
        int year = calendar.get(Calendar.YEAR);//年
        int month = calendar.get(Calendar.MONTH)+1;//月
        int day = calendar.get(Calendar.DAY_OF_MONTH);//日
        teamplate.setContentText("您"+year+"年"+month+"月份的租约账单金额为：租金"+rentPrice+"元，请在"+month+"月"+day+"日前完成支付，逾期将影响租约的执行！");
        return teamplate;
    }

    /**
     * 中止租约 发送给房客
     * @param tenantName 房客
     * @param leaseNo 租约编号
     * @param  total 退款金额
     * @return
     */
    public static MessageTeamplate terminationLeaseTenant(String tenantName, String leaseNo, BigDecimal total){
        teamplate.setTitle(tenantName+"您的租约已中止");
        teamplate.setContentText("很遗憾，您签订的"+leaseNo+"租约已中止，退款"+total+"元申请已生成！");
        return teamplate;
    }

    /**
     * 中止租约 发送给房东
     * @param landlordName 房东
     * @param leaseNo 租约编号
     * @param total 退款金额
     * @return
     */
    public static MessageTeamplate terminationLeaseLandlord(String landlordName, String leaseNo, BigDecimal total){
        teamplate.setTitle(landlordName+"您的房源订单已取消");
        teamplate.setContentText("很遗憾，房客已中止与您房源"+leaseNo+"的订单，退款"+total+"元申请已生成！");
        return teamplate;
    }

    /**
     * 租约即将到期(只发送给房客)
     * @param tenantName 房客
     * @param leaseNo 租约编号
     * @return
     */
    public static MessageTeamplate dueLeaseTenant(String tenantName, String leaseNo){
        teamplate.setTitle(tenantName+"您的租约即将到期");
        teamplate.setContentText("您签订的"+leaseNo+"租约即将期，请及时签约！");
        return teamplate;
    }

    /**
     * 租约到期 发送给房客
     * @param tenantName 房客
     * @param leaseNo 租约编号
     * @param total 退款金额
     * @return
     */
    public static MessageTeamplate becomeDueLeaseTenant(String tenantName, String leaseNo, BigDecimal total){
        teamplate.setTitle(tenantName+"您的租约已到期");
        teamplate.setContentText("您签订的"+leaseNo+"租约已到期，押金退款"+total+"元申请已生成，请及时签约！");
        return teamplate;
    }

    /**
     * 租约到期 发送给房东
     * @param landlordName 房东
     * @param leaseNo 租约编号
     * @param total 退款金额
     * @return
     */
    public static MessageTeamplate becomeDueLeaseLandlord(String landlordName, String leaseNo, BigDecimal total){
        teamplate.setTitle(landlordName+"您的房源订单已到期");
        teamplate.setContentText("您的房源"+leaseNo+"的订单已到期，押金退款"+total+"元申请已生成！");
        return teamplate;
    }


}
