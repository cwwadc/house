package com.msz.model;

import com.baomidou.mybatisplus.enums.IdType;
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
 * 房间图片
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MszRoomImg对象", description = "房间图片")
@TableName("msz_room_img")
public class MszRoomImg extends Model<MszRoomImg> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */

	@TableId(value="id", type= IdType.AUTO)
	@ApiModelProperty(value = "主键id", name = "id")
	private Integer id;
    /**
     * 配置名称
     */
	@ApiModelProperty(value = "配置名称", name = "name")
	private String name;
    /**
     * 配置图片链接
     */
	@ApiModelProperty(value = "配置图片链接", name = "pictureUrl")
	private String pictureUrl;
    /**
     * 配置类型(1：室内图，2：室外图，3：户型图）
     */
	@ApiModelProperty(value = "配置类型(1：室内图，2：室外图，3：户型图）", name = "type")
	private Integer type;
	@ApiModelProperty(value = "", name = "roomId")
	private Integer roomId;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
