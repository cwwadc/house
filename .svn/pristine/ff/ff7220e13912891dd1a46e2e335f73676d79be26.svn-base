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
 * @author Maoyy
 * @since 2019-09-18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MszFloor对象", description = "")
@TableName("msz_floor")
public class MszFloor extends Model<MszFloor> {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "", name = "id")
	private Integer id;
    /**
     * 楼层名称
     */
	@ApiModelProperty(value = "楼层名称", name = "name")
	private Integer name;
    /**
     * 楼栋id
     */
	@ApiModelProperty(value = "楼栋id", name = "houseId")
	private Integer houseId;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
