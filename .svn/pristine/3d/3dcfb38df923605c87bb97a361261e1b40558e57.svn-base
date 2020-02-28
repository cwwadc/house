package com.msz.model;

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
 * 
 * </p>
 *
 * @author Maoyy
 * @since 2019-07-16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MszMsm对象", description = "")
@TableName("msz_msm")
public class MszMsm extends Model<MszMsm> {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "", name = "id")
	private Long id;
    /**
     * 验证码
     */
	@ApiModelProperty(value = "验证码", name = "code")
	private Long code;
    /**
     * 短信内容
     */
	@ApiModelProperty(value = "短信内容", name = "msg")
	private String msg;
    /**
     * 发送给谁
     */
	@ApiModelProperty(value = "发送给谁", name = "username")
	private String username;
    /**
     * 0.account表1.user表
     */
	@ApiModelProperty(value = "0.account表1.user表", name = "isUser")
	private String isUser;

	@ApiModelProperty(value = "", name = "createTime")
	private Date createTime;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
