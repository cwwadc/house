package com.msz.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * 欠租
 * </p>
 *
 * @author Maoyy
 * @since 2019-10-11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MszArrears对象", description = "欠租")
@TableName("msz_arrears")
public class MszArrears extends Model<MszArrears> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	@ApiModelProperty(value = "主键ID", name = "id")
	private Integer id;
    /**
     * 交费ID
     */
	@TableField("order_id")
	@ApiModelProperty(value = "交费ID", name = "orderId")
	private Integer orderId;
    /**
     * 欠收金额
     */
	@TableField("arrears_amount")
	@ApiModelProperty(value = "欠收金额", name = "arrearsAmount")
	private BigDecimal arrearsAmount;
    /**
     * 开始时间
     */
	@TableField("start_time")
	@ApiModelProperty(value = "开始时间", name = "startTime")
	private Date startTime;
    /**
     * 结束时间
     */
	@TableField("end_time")
	@ApiModelProperty(value = "结束时间", name = "endTime")
	private Date endTime;
    /**
     * 0 不用消息提醒 1 消息提醒
     */
	@ApiModelProperty(value = "0 不用消息提醒 1 消息提醒", name = "available")
	private String available;
    /**
     * 创建时间
     */
	@TableField("create_time")
	@ApiModelProperty(value = "创建时间", name = "createTime")
	private Date createTime;
    /**
     * 是否删除
     */
	@TableField("is_del")
	@ApiModelProperty(value = "是否删除", name = "isDel")
	private String isDel;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
