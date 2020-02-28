package com.msz.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PayInfoReturnParam {

    /**
     * 30位uuid
     */
    private String id;
    /**
     * 支付发起账户
     */
    private Integer accountId;
    /**
     * 账号类型：1房东，2房客
     */
    private String accountType;
    /**
     * 交易账号
     */
    private String accountTel;
    /**
     * 支付发起账户名称(冗余字段方便查询)
     */
    private String accountName;
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
     * 商户订单号
     */
    private String orderNo;
    /**
     * 0创建、1成功、2失败
     */
    private String status;
    /**
     * 网点id
     */
    private Integer orgId;
    /**
     * 网点名称
     */
    private String orgName;
    private Date updateTime;
    private Date createTime;
    private String createTimeMin;
    private String createTimeMax;

}
