package com.msz.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.msz.VO.FloorVO;
import com.msz.common.PagingRequest;
import com.msz.model.MszFloor;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 * @author Maoyy
 * @since 2019-09-18
 */
public interface MszFloorService extends IService<MszFloor> {

    PageInfo<MszFloor> listPage(PagingRequest pagingRequest, Wrapper wrapper);

    /**
    * @Author Maoyy
    * @Description 按照
    * @Date 10:54 2019/9/18
    **/
    List<FloorVO> FloorByRoom(Integer id);

    /**
     * @Author Maoyy
     * @Description 插入数据并返回主键
     * @Date 9:39 2019/9/20
     **/
    Integer insertHouseGetId(MszFloor mszFloor);
        
}
