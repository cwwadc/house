package com.msz.model;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * 支付信息（作为发起支付、支付回调的中转，以及支付记录的留存）
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MszPayInfo对象", description = "支付信息（作为发起支付、支付回调的中转，以及支付记录的留存）")
@TableName("msz_pay_info")
public class MszPayInfo extends Model<MszPayInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 30位uuid
     */
    @ApiModelProperty(value = "30位uuid", name = "id")
    private String id;
    /**
     * 支付发起账户
     */
    @ApiModelProperty(value = "", name = "accountId")
    private Integer accountId;
    /**
     * 支付发起账户名称(冗余字段方便查询)
     */
    @ApiModelProperty(value = "", name = "accountName")
    private String accountName;
    /**
     * 场景：1支付押金和租金2.支付租金和水电费
     */
    @ApiModelProperty(value = "场景：1支付押金和租金2.支付租金和水电费", name = "type")
    private String type;
    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额", name = "amt")
    private BigDecimal amt;
    /**
     * 支付渠道：0钱包余额，1微信支付
     */
    @ApiModelProperty(value = "支付渠道：0钱包余额，1微信支付", name = "channel")
    private String channel;
    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID", name = "orderId")
    private Integer orderId;
    /**
     * 商户订单号
     */
    @ApiModelProperty(value = "商户订单号", name = "orderNo")
    private String orderNo;
    /**
     * 0创建、1成功、2失败
     */
    @ApiModelProperty(value = "0创建、1成功、2失败", name = "status")
    private String status;
    @ApiModelProperty(value = "", name = "updateTime")
    private Date updateTime;
    @ApiModelProperty(value = "", name = "createTime")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
