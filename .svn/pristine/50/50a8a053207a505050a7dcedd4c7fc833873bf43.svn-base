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
 * 组织机构表
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SysOrg对象", description = "组织机构表")
@TableName("sys_org")
public class SysOrg extends Model<SysOrg> {

    private static final long serialVersionUID = 1L;


	@TableId(value="id", type= IdType.AUTO)
	@ApiModelProperty(value = "", name = "id")
	private Integer id;
    /**
     * 网点编号
     */
	@ApiModelProperty(value = "网点编号", name = "code")
	private String code;
    /**
     * 网点名称
     */
	@ApiModelProperty(value = "网点名称", name = "name")
	private String name;
    /**
     * 上级网点ID
     */
	@ApiModelProperty(value = "上级网点ID", name = "parentId")
	private Integer parentId;
    /**
     * 负责人
     */
	@ApiModelProperty(value = "负责人", name = "stafId")
	private Integer stafId;
	/**
	 * 地址
	 */
	@ApiModelProperty(value = "地址", name = "address")
	private String address;
	/**
	 * 电话
	 */
	@ApiModelProperty(value = "电话", name = "tel")
	private String tel;
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
     * 更新时间
     */
	@ApiModelProperty(value = "更新时间", name = "updateTime")
	private Date updateTime;

	/**
	 * 负责人名称
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "更新时间", name = "updateTime")
	private String stafName;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
