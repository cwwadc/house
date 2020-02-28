package com.msz.VO;

import com.msz.model.MszRoom;
import com.msz.model.MszSchedule;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ScheduleVO {

    private Integer id;//租约ID
    private String no;//租约编号
    private Integer roomId;//房间ID
    private Integer landlordId;//房东ID
    private String landlordName;//房东名称
    private Integer tenantId;//房客ID
    private Integer userId;//业务员
    private Date startTime;//开始时间
    private Date endTime;//结束时间
    private Date sotpTime;//中止时间
    private BigDecimal duePrice;//应付款
    private String contractPicture;//合同照片
    private String status;//租约状态 0预约中 1执行中 2已结束 3异常
    private Integer operator;//创建人
    private String operatorName;//创建人名称
    private String isDel;//是否删除
    private Date updateTime;//更新时间
    private Date createTime;//生成时间
    private String roomNo;//房源编号
    private String roomName;//房间名称
    private Integer provinceId;//省份id
    private String province;//省份
    private Integer cityId;//城市id
    private String city;//城市
    private Integer countyId;//区县id
    private String county;//区县
    private Integer townId;//街道id
    private String town;//街道
    private String houseNumber;//门牌号
    private String address;//地址
    private String community;//小区
    private String room;//室
    private String hall;//厅
    private String toilet;//卫
    private String floor;//楼层
    private String whichFloor;//共几层
    private Double area;//房间面积
    private Integer orgId;//网点编号
    private String orgName;//网点名称
    private String orgCode;//网点编号
    private Integer payWay;//付款方式
    private Integer payType;//付款类型
    private BigDecimal rentPrice;//租金价格
    private BigDecimal depositPrice;//押金

    //房客信息
    private String tenantName;//房客名称
    private String tel;//房客账号
    private String idCard;//房客身份证
    private String tenantPhone;//房客手机号
    private String salesmanName;//业务员名称
}
