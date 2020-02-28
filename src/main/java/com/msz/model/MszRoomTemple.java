package com.msz.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
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
 * 楼栋模板表
 * </p>
 *
 * @author Maoyy
 * @since 2019-09-21
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MszRoomTemple对象", description = "楼栋模板表")
@TableName("msz_room_temple")
public class MszRoomTemple extends Model<MszRoomTemple> {

    private static final long serialVersionUID = 1L;


	@TableId(value="id", type= IdType.AUTO)
	@ApiModelProperty(value = "", name = "id")
	private Integer id;
    /**
     * 属于哪楼的模板
     */
	@ApiModelProperty(value = "属于哪楼的模板", name = "houseId")
	private Integer houseId;
    /**
     * 楼栋名称
     */
	@ApiModelProperty(value = "楼栋名称", name = "houseName")
	private String houseName;
    /**
     * 网点id
     */
	@ApiModelProperty(value = "网点id", name = "orgId")
	private Integer orgId;
    /**
     * 采集人
     */
	@ApiModelProperty(value = "采集人", name = "userId")
	private Integer userId;
    /**
     * 房东id
     */
	@ApiModelProperty(value = "房东id", name = "telId")
	private Integer telId;
    /**
     * 省份id(暂时没有用上)
     */
	@ApiModelProperty(value = "省份id(暂时没有用上)", name = "provinceId")
	private Integer provinceId;
    /**
     * 城市id
     */
	@ApiModelProperty(value = "城市id", name = "cityId")
	private Integer cityId;
    /**
     * 区县id
     */
	@ApiModelProperty(value = "区县id", name = "countyId")
	private Integer countyId;
    /**
     * 街道
     */
	@ApiModelProperty(value = "街道", name = "townId")
	private Integer townId;
    /**
     * 街区号
     */
	@ApiModelProperty(value = "街区号", name = "townNumber")
	private String townNumber;
    /**
     * 地址
     */
	@ApiModelProperty(value = "地址", name = "address")
	private String address;
    /**
     * 小区
     */
	@ApiModelProperty(value = "小区", name = "community")
	private String community;
    /**
     * 室
     */
	@ApiModelProperty(value = "室", name = "room")
	private String room;
    /**
     * 厅
     */
	@ApiModelProperty(value = "厅", name = "hall")
	private String hall;
    /**
     * 卫
     */
	@ApiModelProperty(value = "卫", name = "toilet")
	private String toilet;
    /**
     * 户型描述
     */
	@ApiModelProperty(value = "户型描述", name = "houseDesc")
	private String houseDesc;
    /**
     * 楼层
     */
	@ApiModelProperty(value = "楼层", name = "floor")
	private String floor;
    /**
     * 共几层
     */
	@ApiModelProperty(value = "共几层", name = "whichFloor")
	private String whichFloor;
    /**
     * 是否有电梯：1-是，2-否
     */
	@TableField("isElevator")
	@ApiModelProperty(value = "是否有电梯：1-是，2-否", name = "elevator")
	private Boolean elevator;
    /**
     * 房间面积
     */
	@ApiModelProperty(value = "房间面积", name = "area")
	private Double area;
    /**
     * 装修情况
     */
	@ApiModelProperty(value = "装修情况", name = "decorate")
	private Integer decorate;
    /**
     * 朝向
     */
	@ApiModelProperty(value = "朝向", name = "toward")
	private Integer toward;
    /**
     * 房间配置（1：全部，2：标配，3：电视，4：空调，5：热水器，6：沙发，7：床，8：暖气，9：衣柜，10：可做饭，11：独立卫生间，12：独立阳台，13：冰箱，14：洗衣机）
     */
	@ApiModelProperty(value = "房间配置（1：全部，2：标配，3：电视，4：空调，5：热水器，6：沙发，7：床，8：暖气，9：衣柜，10：可做饭，11：独立卫生间，12：独立阳台，13：冰箱，14：洗衣机）", name = "configuration")
	private String configuration;
    /**
     * 付款方式(0: 月付  1 季付 2: 半年付 3: 一年付  )
     */
	@ApiModelProperty(value = "付款方式(0: 月付  1 季付 2: 半年付 3: 一年付  )", name = "payWay")
	private Integer payWay;
    /**
     * 租金价格
     */
	@ApiModelProperty(value = "租金价格", name = "rentPrice")
	private BigDecimal rentPrice;
    /**
     * 押金
     */
	@ApiModelProperty(value = "押金", name = "depositPrice")
	private BigDecimal depositPrice;
    /**
     * 底价
     */
	@ApiModelProperty(value = "底价", name = "floorPrice")
	private BigDecimal floorPrice;
    /**
     * 差价
     */
	@ApiModelProperty(value = "差价", name = "diffPrice")
	private BigDecimal diffPrice;
    /**
     * 标题
     */
	@ApiModelProperty(value = "标题", name = "title")
	private String title;
    /**
     * 房间描述
     */
	@ApiModelProperty(value = "房间描述", name = "description")
	private String description;
    /**
     * 图
     */
	@TableField("indoor_image")
	@ApiModelProperty(value = "图", name = "indoorImage")
	private String indoorImage;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
