package com.msz.VO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Maoyy
 * @Description 生活不止眼前的苟且
 * @Date 2019/6/13 15:09
 */
public class PayInfoMonthGroupVO {

    /**
     * 30位uuid
     */
    private String id;
    /**
     * 账户名
     */
    private Integer accountId;
    /**
     * 场景：1支付押金和租金2.支付租金和水电费
     */
    private String type;
    /**
     * 支付金额
     */
    private BigDecimal amt;
    /**
     * 支付渠道：0钱包余额，1微信支付
     */
    private String channel;
    /**
     * 订单ID
     */
    private Integer orderId;
    /**
     * 0创建、1成功、2失败
     */
    private String status;
    private Date updateTime;
    private Date createTime;
    /**
     * 列如: 2019年06月
     */
    private String date;
}
