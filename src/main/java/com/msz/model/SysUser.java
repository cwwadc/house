package com.msz.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SysUser对象", description = "用户")
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "用户id", name = "id")
    private Integer id;
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号", name = "username")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password")
    private String password;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别", name = "sex")
    private String sex;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", name = "phone")
    private String phone;
    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名", name = "trueName")
    private String trueName;
    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号", name = "idCard")
    private String idCard;
    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期", name = "address")
    private String address;
    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id", name = "orgId")
    private Integer orgId;
    /**
     * 状态，0正常，1冻结
     */
    @ApiModelProperty(value = "状态，0正常，1冻结", name = "state")
    private String state;
    /**
     * 账号类型：1管理员，2普通员工
     */
    @ApiModelProperty(value = "账号类型：1管理员，2普通员工", name = "type")
    private String type;
    /**
     * 入职时间
     */
    @ApiModelProperty(value = "入职时间", name = "hireDate")
    private Date hireDate;
    /**
     * 身份证照片正面
     */
    @ApiModelProperty(value = "身份证照片正面", name = "imgIdcard1")
    private String imgIdcard1;
    /**
     * 身份证照片正面
     */
    @ApiModelProperty(value = "身份证照片正面", name = "imgIdcard2")
    private String imgIdcard2;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", name = "updateTime")
    private Date updateTime;

    /**
     * 网点名称
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "网点名称", name = "orgName")
    private String orgName;

    @TableField(exist = false)
    private Integer roleId;

    @TableField(exist = false)
    private String role;

    @TableField(exist = false)
    private List<SysMenu> menuList;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
