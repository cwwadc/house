package com.msz.model;

import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
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
 * 预付定金表
 * </p>
 *
 * @author Maoyy
 * @since 2019-10-08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MszSchedule对象", description = "预付定金表")
@TableName("msz_schedule")
public class MszSchedule extends Model<MszSchedule> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键ID", name = "id")
    private Integer id;
    /**
     * 房屋ID
     */
    @TableField("room_id")
    @ApiModelProperty(value = "房屋ID", name = "roomId")
    private Integer roomId;
    /**
     * 开始时间
     */
    @TableField("schedule_start_time")
    @ApiModelProperty(value = "开始时间", name = "scheduleStartTime")
    private Date scheduleStartTime;
    /**
     * 结束时间
     */
    @TableField("schedule_end_time")
    @ApiModelProperty(value = "结束时间", name = "scheduleEndTime")
    private Date scheduleEndTime;
    /**
     * 预付定金
     */
    @TableField("paid_amount")
    @ApiModelProperty(value = "预付定金", name = "paidAmount")
    private BigDecimal paidAmount;
    /**
     * 房客ID
     */
    @TableField("tenant_id")
    @ApiModelProperty(value = "房客ID", name = "tenant_id")
    private Integer tenantId;
    /**
     * 业务员ID
     */
    @TableField("user_id")
    @ApiModelProperty(value = "业务员ID", name = "user_id")
    private Integer userId;
    /**
     * 是否删除
     */
    @TableField("is_del")
    @ApiModelProperty(value = "是否删除", name = "isDel")
    private String isDel;
    /**
     * 创建时间
     */
    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "房客电话", name = "tenantTel")
    private String tenantTel;

    @TableField(exist = false)
    @ApiModelProperty(value = "房客姓名", name = "tenantName")
    private String tenantName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
