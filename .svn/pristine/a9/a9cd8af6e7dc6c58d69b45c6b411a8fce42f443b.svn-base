package com.msz.model;

import com.baomidou.mybatisplus.annotations.TableField;
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
 * 押金退款详细
 * </p>
 *
 * @author Maoyy
 * @since 2019-10-18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MszRefundMessage对象", description = "押金退款详细")
@TableName("msz_refund_message")
public class MszRefundMessage extends Model<MszRefundMessage> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键Id", name = "id")
    private Integer id;
    /**
     * 申请人
     */
    @ApiModelProperty(value = "申请人", name = "applyName")
    private String applyName;
    /**
     * 房客Id
     */
    @ApiModelProperty(value = "房客Id", name = "tenantId")
    private Integer tenantId;
    /**
     * 房东Id
     */
    @ApiModelProperty(value = "房东Id", name = "telId")
    private Integer telId;
    /**
     * 租约Id
     */
    @ApiModelProperty(value = "租约Id", name = "leaseId")
    private Integer leaseId;
    /**
     * 申请时间
     */
    @ApiModelProperty(value = "申请时间", name = "applyTime")
    private Date applyTime;
    /**
     * 租约时间
     */
    @ApiModelProperty(value = "租约时间", name = "leaseTime")
    private Date leaseTime;
    /**
     * 押金
     */
    @ApiModelProperty(value = "押金", name = "depositPrice")
    private BigDecimal depositPrice;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remarks")
    private String remarks;
    /**
     * 状态： 0同意 1拒绝
     */
    @ApiModelProperty(value = "状态： 0同意 1拒绝", name = "status")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
