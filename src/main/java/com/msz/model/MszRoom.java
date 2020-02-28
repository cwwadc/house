package com.msz.model;

import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
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
 * 房源信息
 * </p>
 *
 * @author cww
 * @since 2019-09-18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MszRoom对象", description = "房源信息")
@TableName("msz_room")
public class MszRoom extends Model<MszRoom> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "编号", name = "id")
    private Integer id;
    /**
     * 房源编号
     */
    @ApiModelProperty(value = "房源编号", name = "no")
    private String no;
    /**
     * 楼层id
     */
    @ApiModelProperty(value = "楼层id", name = "floorId")
    private Integer floorId;
    /**
     * 层名(方便查询)
     */
    @ApiModelProperty(value = "层名(方便查询)", name = "floorName")
    private String floorName;
    /**
     * 楼号(方便查询)
     */
    @ApiModelProperty(value = "楼号(方便查询)", name = "houseName")
    private String houseName;
    /**
     * 楼id
     */
    @ApiModelProperty(value = "楼id", name = "houseId")
    private Integer houseId;
    /**
     * 房东id
     */
    @ApiModelProperty(value = "房东id", name = "telId")
    private Integer telId;
    /**
     * 房东名称
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "房东名称", name = "telName")
    private String telName;
    /**
     * 房间名称
     */
    @ApiModelProperty(value = "房间名称", name = "name")
    private String name;
    /**
     * 省份id
     */
    @ApiModelProperty(value = "省份id", name = "provinceId")
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
     * 小区
     */
    @ApiModelProperty(value = "小区", name = "community")
    private String community;
    /**
     * 门牌号
     */
    @ApiModelProperty(value = "门牌号", name = "houseNumber")
    private String houseNumber;
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址", name = "address")
    private String address;
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
     * 户型
     */
    @ApiModelProperty(value = "户型", name = "houseType")
    private String houseType;
    /**
     * 房间面积
     */
    @ApiModelProperty(value = "房间面积", name = "area")
    private Double area;
    /**
     * 是否有电梯：1-是，2-否
     */
    @TableField("isElevator")
    @ApiModelProperty(value = "是否有电梯：1-是，2-否", name = "elevator")
    private Boolean elevator;
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
     * 付款类型
     */
    @ApiModelProperty(value = "付款类型", name = "payType")
    private Integer payType;
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
     * 室内图
     */
    @TableField("indoor_image")
    @ApiModelProperty(value = "室内图", name = "indoorImage")
    private String indoorImage;
    /**
     * 户型图
     */
    @TableField("huxing_image")
    @ApiModelProperty(value = "户型图", name = "huxingImage")
    private String huxingImage;
    /**
     * 室外图
     */
    @TableField("outdoor_image")
    @ApiModelProperty(value = "室外图", name = "outdoorImage")
    private String outdoorImage;
    /**
     * 所属网点ID
     */
    @ApiModelProperty(value = "所属网点ID", name = "orgId")
    private Integer orgId;
    /**
     * 所属网点编号
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "所属网点编号", name = "orgCode")
    private String orgCode;
    /**
     * 所属网点名称
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "所属网点名称", name = "orgName")
    private String orgName;
    /**
     * 采集人
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "采集人", name = "userName")
    private String userName;
    /**
     * 管家
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "管家", name = "housekeeper")
    private String housekeeper;
    /**
     * 省份
     */
    @TableField(exist = false)
    private String provinceName;
    /**
     * 城市
     */
    @TableField(exist = false)
    private String cityName;
    /**
     * 区县
     */
    @TableField(exist = false)
    private String countyName;
    /**
     * 街道
     */
    @TableField(exist = false)
    private String townName;
    /**
     * 采集人ID
     */
    @ApiModelProperty(value = "采集人ID", name = "userId")
    private Integer userId;
    /**
     * 房源状态 0闲置 1已出租 2下架
     */
    @ApiModelProperty(value = "房源状态 0闲置 1已出租 2下架", name = "status")
    private String status;
    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除", name = "isDel")
    private String isDel;
    /**
     * 发布人id
     */
    @ApiModelProperty(value = "发布人id", name = "publisherId")
    private Long publisherId;
    /**
     * 发布人
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "发布人", name = "publisher")
    private String publisher;
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
     * 最后锁定时间
     */
    @ApiModelProperty(value = "最后锁定时间", name = "endTime")
    private Date endTime;
    /**
     * 0 开放 1锁定
     */
    @ApiModelProperty(value = "0 开放 1锁定", name = "isLock")
    private String isLock;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
