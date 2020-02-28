package com.msz.model;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
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
 * 历史缴费信息
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MszOrderHistory对象", description = "历史缴费信息")
@TableName("msz_order_history")
public class MszOrderHistory extends Model<MszOrderHistory> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "", name = "id")
    private Integer id;
    /**
     * 缴费编号
     */
    @ApiModelProperty(value = "缴费编号", name = "no")
    private String no;
    /**
     * 租约ID
     */
    @ApiModelProperty(value = "租约ID", name = "leaseId")
    private Integer leaseId;
    /**
     * 房间ID
     */
    @ApiModelProperty(value = "房间ID", name = "roomId")
    private Integer roomId;
    /**
     * 房东ID
     */
    @ApiModelProperty(value = "房东ID", name = "landlordId")
    private Integer landlordId;
    /**
     * 房客ID
     */
    @ApiModelProperty(value = "房客ID", name = "tenantId")
    private Integer tenantId;
    /**
     * 房客ID
     */
    @TableField(exist = false)
    private String tenantName;
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
     * 应付款合计
     */
    @ApiModelProperty(value = "应付款合计", name = "total")
    private BigDecimal total;

    @ApiModelProperty(value = "", name = "breachPrice")
    private BigDecimal breachPrice;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", name = "endTime")
    private Date endTime;
    /**
     * 缴费状态 0未缴费 1已缴费
     */
    @ApiModelProperty(value = "缴费状态 0未缴费 1已缴费", name = "status")
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
     * 所属网点id
     */
    @ApiModelProperty(value = "所属网点id", name = "orgId")
    private Integer orgId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
