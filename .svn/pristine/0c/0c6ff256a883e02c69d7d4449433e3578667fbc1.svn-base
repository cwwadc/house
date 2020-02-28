package com.msz.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
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
 * 提现记录
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MszWithdraw对象", description = "提现记录")
@TableName("msz_withdraw")
public class MszWithdraw extends Model<MszWithdraw> {

    private static final long serialVersionUID = 1L;


	@TableId(value="id", type= IdType.AUTO)
	@ApiModelProperty(value = "", name = "id")
	private Integer id;
    /**
     * 编号
     */
	@ApiModelProperty(value = "编号", name = "no")
	private String no;
    /**
     * 微信支付订单号
     */
	@ApiModelProperty(value = "微信支付订单号", name = "orderId")
	private String transactionId;
	/**
	 * 商户订单号
	 */
	@ApiModelProperty(value = "商户订单号", name = "outTradeNo")
	private String outTradeNo;
	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id", name = "accountId")
	private Integer accountId;
    /**
     * 提现金额
     */
	@ApiModelProperty(value = "提现金额", name = "amt")
	private BigDecimal amt;
    /**
     * 收款账号
     */
	@ApiModelProperty(value = "收款账号", name = "cardNo")
	private String cardNo;
    /**
     * 户名
     */
	@ApiModelProperty(value = "户名", name = "accountName")
	private String accountName;
    /**
     * 状态：0待处理，1成功，2拒绝提现
     */
	@ApiModelProperty(value = "状态：0待处理，1成功，2拒绝提现", name = "status")
	private String status;
    /**
     * 处理人
     */
	@ApiModelProperty(value = "处理人", name = "operator")
	private Integer operator;
    /**
     * 备注
     */
	@ApiModelProperty(value = "备注", name = "note")
	private String note;
    /**
     * 处理时间
     */
	@ApiModelProperty(value = "处理时间", name = "updateTime")
	private Date updateTime;
    /**
     * 申请时间
     */
	@ApiModelProperty(value = "申请时间", name = "createTime")
	private Date createTime;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
