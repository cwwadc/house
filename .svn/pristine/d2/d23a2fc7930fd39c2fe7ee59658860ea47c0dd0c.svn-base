package com.msz.service;

import com.msz.common.PagingRequest;
import com.msz.model.MszRoom;
import com.msz.model.MszRoomTemple;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;

/**
 * <p>
 * 楼栋模板表 服务类
 * </p>
 *
 * @author Maoyy
 * @since 2019-09-21
 */
public interface MszRoomTempleService extends IService<MszRoomTemple> {

    PageInfo<MszRoomTemple> listPage(PagingRequest pagingRequest, Wrapper wrapper);

    /**
    * @Author Maoyy
    * @Description 拿到房源数据，转换为MszRoomTemple对象再进行插入
    * @Date 10:54 2019/9/21
    **/
    boolean insertTemple(MszRoom mszRoom,Integer houseId);
}
