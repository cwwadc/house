package com.msz.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
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
 * 用户钱包
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MszWallet对象", description = "用户钱包")
@TableName("msz_wallet")
public class MszWallet extends Model<MszWallet> {

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
     * 支付密码（约定为6位纯数字）
     */
	@ApiModelProperty(value = "支付密码（约定为6位纯数字）", name = "pwd")
	private String pwd;
    /**
     * 余额（分）
     */
	@ApiModelProperty(value = "余额（分）", name = "balance")
	private BigDecimal balance;
    /**
     * 冻结资金（分）
     */
	@ApiModelProperty(value = "冻结资金（分）", name = "frozen")
	private BigDecimal frozen;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
