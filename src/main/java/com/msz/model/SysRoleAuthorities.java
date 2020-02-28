package com.msz.model;

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
 * 角色权限
 * </p>
 *
 * @author cww
 * @since 2019-06-26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SysRoleAuthorities对象", description = "角色权限")
@TableName("sys_role_authorities")
public class SysRoleAuthorities extends Model<SysRoleAuthorities> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	@ApiModelProperty(value = "", name = "id")
	private Integer id;
    /**
     * 角色id
     */
	@ApiModelProperty(value = "角色id", name = "roleId")
	private Integer roleId;
    /**
     * 权限id
     */
	@ApiModelProperty(value = "权限id", name = "authorityId")
	private Integer authorityId;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
