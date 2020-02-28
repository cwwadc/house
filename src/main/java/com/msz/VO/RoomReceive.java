package com.msz.VO;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Maoyy
 * @Description 生活不止眼前的苟且
 * @Date 2019/6/11 14:49
 */
@Data
public class RoomReceive {
    //描述
    private String text;
    // 房源属于
    private String byId;
    //房源状态 0闲置 1已出租 2下架
    private String status;
    //室
    private String room;
    //网点ID
    private Integer orgId;
    //最低租金价格
    private BigDecimal lowsetPrice;
    //最高租金价格
    private BigDecimal highestPrice;
    //房东
    private Integer telId;
}
