package com.msz.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.msz.VO.TelHouseVO;
import com.msz.VO.WeHouseDataVO;
import com.msz.model.MszHouse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Maoyy
 * @since 2019-09-17
 */
@Mapper
public interface MszHouseMapper extends BaseMapper<MszHouse> {
    /**
     * @Author Maoyy
     * @Description 插入数据并返回主键
     * @Date 9:39 2019/9/20
     **/
    Integer insertHouseGetId(@Param("mszHouse") MszHouse mszHouse);

    /**
     * @Author Maoyy
     * @Description 小程序首页查看房源
     * @Date 20:23 2019/9/25
     **/
    List<WeHouseDataVO> getHouseData(@Param("id") Integer id);
}
