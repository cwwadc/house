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
 * 用户信息
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MszAccount对象", description = "用户信息")
@TableName("msz_account")
public class MszAccount extends Model<MszAccount> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	@ApiModelProperty(value = "", name = "id")
	private Integer id;
    /**
     * 账户
     */
	@ApiModelProperty(value = "账户", name = "tel")
	private String tel;
    /**
     * 登录密码
     */
	@ApiModelProperty(value = "登录密码", name = "pwd")
	private String pwd;
    /**
     * 姓名
     */
	@ApiModelProperty(value = "姓名", name = "name")
	private String name;
    /**
     * 头像
     */
	@ApiModelProperty(value = "头像", name = "logo")
	private String logo;
    /**
     * 账号类型：1房东，2房客
     */
	@ApiModelProperty(value = "账号类型：1房东，2房客", name = "type")
	private String type;
    /**
     * 状态：0正常
     */
	@ApiModelProperty(value = "状态：0正常", name = "state")
	private String state;
    /**
     * 性别
     */
	@ApiModelProperty(value = "性别", name = "sex")
	private String sex;
    /**
     * 联系电话
     */
	@ApiModelProperty(value = "联系电话", name = "phone")
	private String phone;
    /**
     * 身份证号
     */
	@ApiModelProperty(value = "身份证号", name = "idCard")
	private String idCard;
    /**
     * 联系地址
     */
	@ApiModelProperty(value = "联系地址", name = "address")
	private String address;
    /**
     * 身份证照片正面
     */
	@ApiModelProperty(value = "身份证照片正面", name = "imgIdcard1")
	private String imgIdcard1;
    /**
     * 身份证照片正面
     */
	@ApiModelProperty(value = "身份证照片正面", name = "imgIdcard2")
	private String imgIdcard2;
    /**
     * 微信关联ID
     */
	@ApiModelProperty(value = "微信关联ID", name = "openId")
	private String openId;
    /**
     * 是否删除
     */
	@ApiModelProperty(value = "是否删除", name = "isDel")
	private String isDel;
    /**
     * 更新时间
     */
	@ApiModelProperty(value = "更新时间", name = "updateTime")
	private Date updateTime;
    /**
     * 生成时间
     */
	@ApiModelProperty(value = "生成时间", name = "createTime")
	private Date createTime;
	/**
	 * 所属网点id
	 */
	@ApiModelProperty(value = "所属网点id", name = "orgId")
	private Integer orgId;
	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
