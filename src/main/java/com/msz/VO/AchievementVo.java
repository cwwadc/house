package com.msz.VO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 业绩列表
 */
@Data
public class AchievementVo {
    //房源编号
    private String houseNo;
    //房东
    private String telName;
    //小区
    private String community;
    //楼栋
    private String houseName;
    //房间号
    private String houseNumber;

    //租金
    private BigDecimal rentPrice;
    //租约日期
    private Date startTime;
    private Date endTime;
    //创建时间
    private Date createTime;

    //租户姓名
    private String tenantName;
    //租户电话
    private String tenantPhone;
}
