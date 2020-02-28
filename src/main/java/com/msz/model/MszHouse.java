package com.msz.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
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
 * 楼栋表
 * </p>
 *
 * @author Maoyy
 * @since 2019-09-18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MszHouse对象", description = "楼栋表")
@TableName("msz_house")
public class MszHouse extends Model<MszHouse> {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "", name = "id")
	private Integer id;
    /**
     * 楼栋名称
     */
	@ApiModelProperty(value = "楼栋名称", name = "houseName")
	private String houseName;
    /**
     * 地址
     */
	@ApiModelProperty(value = "地址", name = "address")
	private String address;
    /**
     * 属于网点
     */
	@ApiModelProperty(value = "属于网点", name = "orgId")
	private Integer orgId;
    /**
     * 房东id
     */
	@ApiModelProperty(value = "房东id", name = "telId")
	private Integer telId;

	@TableField(exist = false)
	private String level = "1";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
