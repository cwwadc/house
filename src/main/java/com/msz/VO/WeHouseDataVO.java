package com.msz.VO;

import lombok.Data;

/**
 * @Author Maoyy
 * @Description 生活不止眼前的苟且
 * @Date 2019/9/25 20:18
 */
@Data
public class WeHouseDataVO {
    private Integer id; //id
    private String houseName; //楼栋名称
    private Integer sum; //总房间数
    private Integer checkIn; //入住数
}
