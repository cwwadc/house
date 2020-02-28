package com.msz.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.msz.VO.WeRoomsVO;
import com.msz.common.PagingRequest;
import com.msz.model.MszRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 房源信息 Mapper 接口
 * </p>
 *
 * @author cww
 * @since 2019-06-10
 */
@Mapper
@Repository
public interface MszRoomMapper extends BaseMapper<MszRoom> {

    List<MszRoom> findList(MszRoom room);

    /**
    * @Author Maoyy
    * @Description 小程序首页查看房源
    * @Date 19:51 2019/9/25
    **/
    List<WeRoomsVO> getRooms(@Param("status")String status,@Param("id")Integer telId);

    /**
     * @Author cww
     * @Description 小程序首页查看即期房源
     * @Date 19:51 2019/9/25
     **/
    List<WeRoomsVO> getSpotRooms(@Param("id")Integer telId);

    List<MszRoom> getRoomListByOrg(@Param("start") int start,
                                   @Param("size") int size,
                                   @Param("orgId") Integer orgId,
                                   @Param("telName") String telName,
                                   @Param("houseName") String houseName,
                                   @Param("houseNumber") String houseNumber,
                                   @Param("userName") String userName);

    int getRoomListByOrgSums(@Param("orgId") Integer orgId,
                             @Param("telName") String telName,
                             @Param("houseName") String houseName,
                             @Param("houseNumber") String houseNumber,
                             @Param("userName") String userName);

}
