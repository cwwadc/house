package com.msz.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.VO.FloorVO;
import com.msz.common.PagingRequest;
import com.msz.dao.MszFloorMapper;
import com.msz.model.*;
import com.msz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Maoyy
 * @since 2019-09-18 ${time}
 */
@Service
public class MszFloorServiceImpl extends ServiceImpl<MszFloorMapper, MszFloor> implements MszFloorService {

    @Autowired
    private MszRoomService mszRoomService;

    @Autowired
    private MszFloorMapper mszFloorMapper;

    @Autowired
    private SysOrgService sysOrgService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private MszAccountService mszAccountService;

    @Override
    public PageInfo<MszFloor> listPage(PagingRequest pagingRequest, Wrapper wrapper) {
        PageHelper.startPage(pagingRequest.getOffset(), pagingRequest.getLimit());
        return new PageInfo<>(super.selectList(wrapper));
    }

    @Override
    public List<FloorVO> FloorByRoom(Integer id) {
        List<MszFloor> list = super.selectList(new EntityWrapper<MszFloor>().eq("houseId", id).orderBy("name",true));
        List<FloorVO> vo = new ArrayList<>();
        for (MszFloor item : list) {
            FloorVO floorVO = new FloorVO();
            floorVO.setId(item.getId());
            floorVO.setHouseId(item.getHouseId());
            floorVO.setName(item.getName().toString());
            List<MszRoom> listRoom = mszRoomService.selectList(new EntityWrapper<MszRoom>().eq("floorId", item.getId()));
            List<MszRoom> room = new ArrayList<>();
            for(MszRoom val : listRoom){
                val.setUserName(sysUserService.selectById(val.getUserId()).getTrueName());
                val.setOrgName(sysOrgService.selectById(val.getOrgId()).getName());
                val.setTelName(mszAccountService.selectById(val.getTelId()).getName());
                room.add(val);
            }
            floorVO.setMszRooms(room);
            vo.add(floorVO);
        }
        return vo;
    }

    @Override
    public Integer insertHouseGetId(MszFloor mszFloor) {
        return mszFloorMapper.insertHouseGetId(mszFloor);
    }

}
