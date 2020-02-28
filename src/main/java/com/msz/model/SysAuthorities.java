package com.msz.model;

import com.baomidou.mybatisplus.annotations.TableField;
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
 * 权限
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SysAuthorities对象", description = "权限")
@TableName("sys_authorities")
public class SysAuthorities extends Model<SysAuthorities> {

    private static final long serialVersionUID = 1L;


	@TableId(value="id", type= IdType.AUTO)
	@ApiModelProperty(value = "", name = "id")
	private Integer id;
    /**
     * 授权标识
     */
	@ApiModelProperty(value = "授权标识", name = "authority")
	private String authority;
    /**
     * 名称
     */
	@ApiModelProperty(value = "名称", name = "authorityName")
	private String authorityName;
    /**
     * 栏目ID
     */
	@ApiModelProperty(value = "栏目ID", name = "menuId")
	private Integer menuId;
    /**
     * 排序号
     */
	@ApiModelProperty(value = "排序号", name = "sort")
	private Integer sort;
    /**
     * 创建时间
     */
	@ApiModelProperty(value = "创建时间", name = "createTime")
	private Date createTime;

	//菜单名称
	@TableField(exist = false)
	@ApiModelProperty(value = "菜单名称", name = "menuName")
	private String menuName;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
