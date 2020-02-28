package com.msz.VO;

import com.msz.model.MszOrderInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Maoyy
 * @Description 生活不止眼前的苟且
 * @Date 2019/6/14 14:27
 */
@Data
public class MszPayInfoVO {

    /**
     * 30位uuid
     */
    private String id;
    /**
     * 支付发起账户
     */
    private Integer accountId;
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
    private MszOrderInfo orderInfo;
    /**
     * 0创建、1成功、2失败
     */
    private String status;
    private Date updateTime;
    private Date createTime;


}
