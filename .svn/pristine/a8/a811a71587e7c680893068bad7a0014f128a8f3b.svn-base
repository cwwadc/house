package com.msz.model;

import com.baomidou.mybatisplus.enums.IdType;
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
 * 角色菜单表
 * </p>
 *
 * @author cww
 * @since 2019-06-29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SysRoleMenu对象", description = "角色菜单表")
@TableName("sys_role_menu")
public class SysRoleMenu extends Model<SysRoleMenu> {

    private static final long serialVersionUID = 1L;


	@TableId(value="id", type= IdType.AUTO)
	@ApiModelProperty(value = "", name = "id")
	private Integer id;
    /**
     * 菜单id
     */
	@ApiModelProperty(value = "菜单id", name = "menuId")
	private Integer menuId;
    /**
     * 角色id
     */
	@ApiModelProperty(value = "角色id", name = "roleId")
	private Integer roleId;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
