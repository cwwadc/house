package com.msz.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.common.PagingRequest;
import com.msz.dao.MszLeaseMapper;
import com.msz.dao.MszRoomImgMapper;
import com.msz.dao.MszRoomMapper;
import com.msz.model.MszRoom;
import com.msz.model.MszRoomImg;
import com.msz.service.MszRoomImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;


/**
 * <p>
 * 房间图片 服务实现类
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Service
public class MszRoomImgServiceImpl extends ServiceImpl<MszRoomImgMapper, MszRoomImg> implements MszRoomImgService {

    @Autowired
    MszRoomMapper mszRoomMapper;

    @Override
    public PageInfo<MszRoomImg> listPage(PagingRequest pagingRequest, Wrapper wrapper){
        PageHelper.startPage( pagingRequest.getOffset(), pagingRequest.getLimit() );
        return new PageInfo<>(super.selectList(wrapper));
    }

}
