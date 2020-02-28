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
 * 租约收费项目（水电费等）
 * </p>
 *
 * @author Maoyy
 * @since 2019-10-08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MszLeaseCharge对象", description = "租约收费项目（水电费等）")
@TableName("msz_lease_charge")
public class MszLeaseCharge extends Model<MszLeaseCharge> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */

	@TableId(value="id", type= IdType.AUTO)
	@ApiModelProperty(value = "主键ID", name = "id")
	private Integer id;
    /**
     * 收费项目
     */
	@ApiModelProperty(value = "收费项目", name = "chargeName")
	private String chargeName;
    /**
     * 收费金额
     */
	@ApiModelProperty(value = "收费金额", name = "chargePrice")
	private BigDecimal chargePrice;
    /**
     * 0 不应用到每期收费  1 应用到每期收费
     */
	@ApiModelProperty(value = "0 不应用到每期收费  1 应用到每期收费", name = "available")
	private String available;
    /**
     * 创建时间
     */
	@ApiModelProperty(value = "创建时间", name = "createTime")
	private Date createTime;
    /**
     * 是否删除
     */
	@ApiModelProperty(value = "是否删除", name = "isDel")
	private String isDel;
    /**
     * 租约ID
     */
	@ApiModelProperty(value = "租约ID", name = "leaseId")
	private Integer leaseId;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
