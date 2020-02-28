package com.msz.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SysDict对象", description = "字典表")
@TableName("sys_dict")
public class SysDict extends Model<SysDict> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */

	@TableId(value="id", type= IdType.AUTO)
	@ApiModelProperty(value = "主键id", name = "id")
	private Integer id;
    /**
     * 排序
     */
	@ApiModelProperty(value = "排序", name = "num")
	private Integer num;
    /**
     * 父级字典
     */
	@ApiModelProperty(value = "父级字典", name = "pid")
	private Integer pid;
    /**
     * 名称
     */
	@ApiModelProperty(value = "名称", name = "name")
	private String name;
    /**
     * 提示
     */
	@ApiModelProperty(value = "提示", name = "tips")
	private String tips;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间", name = "createTime")
	private Date createTime;
	/**
	 * 0启用 1禁用
	 */
	@ApiModelProperty(value = "0启用 1禁用", name = "isDel")
	private String isDel;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
