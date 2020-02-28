package com.msz.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.common.PagingRequest;
import com.msz.dao.MszMsmMapper;
import com.msz.model.MszMsm;
import com.msz.service.MszMsmService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Maoyy
 * @since 2019-07-16 ${time}
 */
@Service
public class MszMsmServiceImpl extends ServiceImpl<MszMsmMapper, MszMsm> implements MszMsmService {

    @Override
    public PageInfo<MszMsm> listPage(PagingRequest pagingRequest, Wrapper wrapper){
        PageHelper.startPage( pagingRequest.getOffset(), pagingRequest.getLimit() );
        return new PageInfo<>(super.selectList(wrapper));
    }

}
