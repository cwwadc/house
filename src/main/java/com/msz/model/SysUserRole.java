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
 * 用户角色
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SysUserRole对象", description = "用户角色")
@TableName("sys_user_role")
public class SysUserRole extends Model<SysUserRole> {

    private static final long serialVersionUID = 1L;


	@TableId(value="id", type= IdType.AUTO)
	@ApiModelProperty(value = "", name = "id")
	private Integer id;
    /**
     * 用户id
     */
	@ApiModelProperty(value = "用户id", name = "userId")
	private Integer userId;
    /**
     * 角色id
     */
	@ApiModelProperty(value = "角色id", name = "roleId")
	private Integer roleId;
    /**
     * 创建时间
     */
	@ApiModelProperty(value = "创建时间", name = "createTime")
	private Date createTime;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
