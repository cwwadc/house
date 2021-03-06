package com.msz.VO;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.msz.model.MszLeaseCharge;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class LeaseReturnParamVO {


    private Integer id;//租约ID
    private String no;//租约编号
    private Integer roomId;//房间ID
    private Integer landlordId;//房东ID
    private String landlordName;//房东名称
    private Integer tenantId;//房客ID
    private Integer userId;//业务员
    private Date startTime;//开始时间
    private Date endTime;//结束时间
    private BigDecimal duePrice;//应付款
    private String contractPicture;//合同照片
    private String status;//租约状态 1执行中 2已结束 3即期
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
    private String houseName;//门牌号
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
    private BigDecimal paidRentPrice;//实收租金
    private BigDecimal depositPrice;//押金
    private BigDecimal paidDepositPrice;//实收押金、
    private BigDecimal roomRentPrice; // 房源租金
    private BigDecimal roomDepositPrice; // 房源押金
    private String billDay;//账单日
    private String collectMoneyDay;//租金交费日
    private String available;//0 不应用到每期交费  1 应用到每期交费
    private String flag;//0 不修改金额提醒  1 修改金额提醒
    private String spotFlag; // 0 正常  1 即期
    private List<MszLeaseCharge> mszLeaseCharge;

    //房客信息
    private String tenantName;//房客名称
    private String tel;//房客账号
    private String idCard;//房客身份证
    private String tenantPhone;//房客手机号
    private String salesmanName;//业务员名称

    //预付定金信息
    private Integer scheduleId;//预付定金ID
    private Date scheduleStartTime;//开始时间
    private Date scheduleEndTime;//结束时间
    private BigDecimal paidAmount;//预付定金
    private Date scheduleCreateTime;//预付定金创建时间

    //搜索字段
    private  String searchName;
}
