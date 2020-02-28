package com.msz.model;

import com.baomidou.mybatisplus.enums.IdType;
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
 * 角色
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SysRole对象", description = "角色")
@TableName("sys_role")
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;


	@TableId(value="id", type= IdType.AUTO)
	@ApiModelProperty(value = "", name = "id")
	private Integer id;
    /**
     * 角色名称
     */
	@ApiModelProperty(value = "角色名称", name = "roleName")
	private String roleName;
    /**
     * 备注
     */
	@ApiModelProperty(value = "备注", name = "comments")
	private String comments;
	/**
	 * 是否删除
	 */
	@ApiModelProperty(value = "是否删除", name = "isDel")
	private String isDel;
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


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
