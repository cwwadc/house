package com.msz.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msz.VO.IdleNumAndRentedNumAndObtainedNumVO;
import com.msz.VO.RoomReceive;
import com.msz.VO.WeRoomsVO;
import com.msz.common.PagingRequest;
import com.msz.model.MszRoom;
import com.msz.model.MszSchedule;
import com.msz.model.SysUser;

import java.util.List;

/**
 * <p>
 * 房源信息 服务类
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
public interface MszRoomService extends IService<MszRoom> {


    PageInfo<MszRoom> listPage(PagingRequest pagingRequest, MszRoom room, SysUser user);

    /**
     * @Author Maoyy
     * @Description 代码写了一行又一行
     * @Date 15:22 2019/6/11
     **/
    PageInfo<MszRoom> weProgramList(PagingRequest pagingRequest, RoomReceive roomReceive);

    /**
     * @Author Maoyy
     * @Description 代码写了一行又一行
     * @Date 23:02 2019/7/3
     **/
    PageInfo<MszRoom> landlordRoom(PagingRequest pagingRequest, RoomReceive roomReceive, Integer landlordId);

    /**
     * @Author Maoyy
     * @Description 代码写了一行又一行
     * @Date 11:46 2019/7/4
     **/
    IdleNumAndRentedNumAndObtainedNumVO getIdleNumAndRentedNumAndObtainedNum(SysUser user);

    /**
     * 获取房源详情
     *
     * @param id
     * @return
     */
    MszRoom getRoomDesc(Integer id);

    /**
     * @Author Maoyy
     * @Description 小程序首页查看房源
     * @Date 19:48 2019/9/25
     **/
    PageInfo<WeRoomsVO> getRooms(PagingRequest pagingRequest, Integer telId,String status);

    /**
     * @Author Maoyy
     * @Description 即期房源
     * @Date 19:48 2019/9/25
     **/
    List<WeRoomsVO>  getSpotRooms(Integer telId);


    List<MszRoom> getRoomListByOrg(PagingRequest pagingRequest, Integer orgId, String telName,
                                   String houseName, String houseNumber, String userName);

    int getRoomListByOrgSums(Integer orgId, String telName, String houseName, String houseNumber, String userName);

}
