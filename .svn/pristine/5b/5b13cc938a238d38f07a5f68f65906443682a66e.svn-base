package com.msz.service.impl;

import com.msz.common.PagingRequest;
import com.msz.dao.MszRoomTempleMapper;
import com.msz.model.MszRoom;
import com.msz.model.MszRoomTemple;
import com.msz.service.MszRoomTempleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;


/**
 * <p>
 * 楼栋模板表 服务实现类
 * </p>
 *
 * @author Maoyy
 * @since 2019-09-21 ${time}
 */
@Service
public class MszRoomTempleServiceImpl extends ServiceImpl<MszRoomTempleMapper, MszRoomTemple> implements MszRoomTempleService {

    @Override
    public PageInfo<MszRoomTemple> listPage(PagingRequest pagingRequest, Wrapper wrapper){
        PageHelper.startPage( pagingRequest.getOffset(), pagingRequest.getLimit() );
        return new PageInfo<>(super.selectList(wrapper));
    }

    @Override
    public boolean insertTemple(MszRoom mszRoom,Integer houseId) {
        MszRoomTemple temple = new MszRoomTemple();
        BeanUtils.copyProperties(mszRoom,temple);
        temple.setHouseId(houseId);
        return super.insert(temple);
    }

}
