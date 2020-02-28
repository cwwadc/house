package com.msz.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 退款信息
 * </p>
 *
 * @author cww
 * @since 2019-06-14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MszRefundInfo对象", description = "退款信息")
@TableName("msz_refund_info")
public class MszRefundInfo extends Model<MszRefundInfo> {

    private static final long serialVersionUID = 1L;


	@TableId(value="id", type= IdType.AUTO)
	@ApiModelProperty(value = "", name = "id")
	private Long id;
    /**
     * 退款编号
     */
	@ApiModelProperty(value = "退款编号", name = "no")
	private String no;
    /**
     * 租约ID
     */
	@ApiModelProperty(value = "租约ID", name = "leaseId")
	private Integer leaseId;
	/**
	 * 房东id（退款人id）
	 */
	@ApiModelProperty(value = "房东id（退款人id）", name = "tenantId")
	private Integer landlordId;
    /**
     * 房客ID(申请人id)
     */
	@ApiModelProperty(value = "房客ID(申请人id)", name = "tenantId")
	private Integer tenantId;
    /**
     * 租金
     */
	@ApiModelProperty(value = "租金", name = "rentPrice")
	private BigDecimal rentPrice;
    /**
     * 押金
     */
	@ApiModelProperty(value = "押金", name = "depositPrice")
	private BigDecimal depositPrice;
    /**
     * 退款合计
     */
	@ApiModelProperty(value = "退款合计", name = "total")
	private BigDecimal total;
    /**
     * 退款申请时间
     */
	@ApiModelProperty(value = "退款申请时间", name = "applyTime")
	private Date applyTime;
    /**
     * 审核人id
     */
	@ApiModelProperty(value = "审核人id", name = "userId")
	private Integer userId;
    /**
     * 退款状态 0待审核 1已同意 2已拒绝
     */
	@ApiModelProperty(value = "退款状态 0待审核 1已同意 2已拒绝", name = "status")
	private String status;
    /**
     * 是否删除
     */
	@ApiModelProperty(value = "是否删除", name = "isDel")
	private String isDel;
    /**
     * 更新时间
     */
	@ApiModelProperty(value = "更新时间", name = "updateTime")
	private Date updateTime;
    /**
     * 生成时间
     */
	@ApiModelProperty(value = "生成时间", name = "createTime")
	private Date createTime;

	/**
	 * 租约编号
	 */
	@ApiModelProperty(value = "租约编号", name = "leaseNo")
	@TableField(exist = false)
	private String leaseNo;
	/**
	 * 房客电话（收款人账号）
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "房客电话（收款人账号）", name = "tenantTel")
	private String tenantTel;
	/**
	 * 房客名称（申请人名称）
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "房客名称（申请人名称）", name = "tenantName")
	private  String tenantName;
	/**
	 * 房东名称（退款人名称）
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "房东名称（退款人名称）", name = "landlordName")
	private  String landlordName;
	/**
	 * 审核人
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "审核人", name = "userName")
	private String userName;
	/**
	 * 微信关联ID
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "微信关联ID", name = "openId")
	private String openId;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
