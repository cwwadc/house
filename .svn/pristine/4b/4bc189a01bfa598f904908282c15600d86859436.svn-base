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
 * 菜单表
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SysMenu对象", description = "菜单表")
@TableName("sys_menu")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */

	@TableId(value="id", type= IdType.AUTO)
	@ApiModelProperty(value = "菜单id", name = "id")
	private Integer id;
    /**
     * 父级id
     */
	@ApiModelProperty(value = "父级id", name = "parentId")
	private Integer parentId;
    /**
     * 菜单名称
     */
	@ApiModelProperty(value = "菜单名称", name = "menuName")
	private String menuName;
    /**
     * 菜单url
     */
	@ApiModelProperty(value = "菜单url", name = "menuUrl")
	private String menuUrl;
    /**
     * 菜单图标
     */
	@ApiModelProperty(value = "菜单图标", name = "menuIcon")
	private String menuIcon;
    /**
     * 排序号
     */
	@ApiModelProperty(value = "排序号", name = "sort")
	private Integer sort;
    /**
     * 对应权限
     */
	@ApiModelProperty(value = "对应权限", name = "authority")
	private String authority;
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

	@TableField(exist = false)
	@ApiModelProperty(value = "子菜单", name = "menuChild")
	private List<SysMenu> childMenus;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
