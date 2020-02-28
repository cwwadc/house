package com.msz.model;

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
 * 
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SysCity对象", description = "")
@TableName("sys_city")
public class SysCity extends Model<SysCity> {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "", name = "id")
	private String id;
	@ApiModelProperty(value = "", name = "name")
	private String name;
	@ApiModelProperty(value = "", name = "province")
	private String province;
	@ApiModelProperty(value = "", name = "city")
	private String city;
	@ApiModelProperty(value = "", name = "district")
	private String district;
	@ApiModelProperty(value = "", name = "town")
	private String town;
	@ApiModelProperty(value = "", name = "parent")
	private String parent;
	@ApiModelProperty(value = "", name = "level")
	private Boolean level;
    /**
     * 经度
     */
	@ApiModelProperty(value = "经度", name = "lng")
	private String lng;
    /**
     * 纬度
     */
	@ApiModelProperty(value = "纬度", name = "lat")
	private String lat;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
