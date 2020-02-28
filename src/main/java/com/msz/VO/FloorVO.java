package com.msz.VO;

import com.msz.model.MszRoom;
import lombok.Data;

import java.util.List;

/**
 * @Author Maoyy
 * @Description 生活不止眼前的苟且
 * @Date 2019/9/18 10:51
 */
@Data
public class FloorVO {

    private Integer id;
    /**
     * 楼层名称
     */
    private String name;
    /**
     * 楼栋id
     */
    private Integer houseId;


    private List<MszRoom> mszRooms;
    /**
     * 这里写的级别是第二层
     */
    private String level = "2";
}
