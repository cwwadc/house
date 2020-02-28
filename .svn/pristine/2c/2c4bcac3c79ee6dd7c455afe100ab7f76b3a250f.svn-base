package com.msz.model;

import com.baomidou.mybatisplus.enums.IdType;
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
 * 用户token表
 * </p>
 *
 * @author Maoyy
 * @since 2019-07-17
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MszToken对象", description = "用户token表")
@TableName("msz_token")
public class MszToken extends Model<MszToken> {

    private static final long serialVersionUID = 1L;


	@TableId(value="id", type= IdType.AUTO)
	@ApiModelProperty(value = "", name = "id")
	private Long id;
    /**
     * 用户id
     */
	@TableField("user_id")
	@ApiModelProperty(value = "用户id", name = "userId")
	private Integer userId;
    /**
     * 登录返回的token
     */
	@ApiModelProperty(value = "登录返回的token", name = "token")
	private String token;
	@ApiModelProperty(value = "", name = "bulidTime")
	private Long bulidTime;
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
