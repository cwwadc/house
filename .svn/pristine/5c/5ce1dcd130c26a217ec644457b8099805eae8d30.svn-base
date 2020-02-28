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
 * 钱包流水
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MszWalletBill对象", description = "钱包流水")
@TableName("msz_wallet_bill")
public class MszWalletBill extends Model<MszWalletBill> {

    private static final long serialVersionUID = 1L;


	@TableId(value="id", type= IdType.AUTO)
	@ApiModelProperty(value = "", name = "id")
	private Integer id;
    /**
     * 账号id
     */
	@ApiModelProperty(value = "账号id", name = "accountId")
	private Integer accountId;
    /**
     * 金额
     */
	@ApiModelProperty(value = "金额", name = "amt")
	private BigDecimal amt;
    /**
     * 账单类型：1充值，2提现
     */
	@ApiModelProperty(value = "账单类型：1充值，2提现，3月租，4退款", name = "type")
	private String type;
	@ApiModelProperty(value = "", name = "createTime")
	private Date createTime;
    /**
     * 关联id
     */
	@ApiModelProperty(value = "关联id", name = "receiverId")
	private String receiverId;
    /**
     * 关联类型
     */
	@ApiModelProperty(value = "关联类型", name = "receiverType")
	private String receiverType;
    /**
     * 流水号
     */
	@ApiModelProperty(value = "流水号", name = "no")
	private String no;
    /**
     * 状态 0.新建 1.成功 2.失败
     */
	@ApiModelProperty(value = "状态 0.新建 1.成功 2.失败", name = "status")
	private String status;



	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
