package com.msz.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.common.PagingRequest;
import com.msz.dao.MszTokenMapper;
import com.msz.model.MszToken;
import com.msz.service.MszTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;


/**
 * <p>
 * 用户token表 服务实现类
 * </p>
 *
 * @author Maoyy
 * @since 2019-07-17 ${time}
 */
@Service
public class MszTokenServiceImpl extends ServiceImpl<MszTokenMapper, MszToken> implements MszTokenService {

    @Autowired
    MszTokenMapper tokenMapper;

    @Override
    public PageInfo<MszToken> listPage(PagingRequest pagingRequest, Wrapper wrapper){
        PageHelper.startPage( pagingRequest.getOffset(), pagingRequest.getLimit() );
        return new PageInfo<>(super.selectList(wrapper));
    }

    @Override
    public MszToken findByUserId(Integer userId) {

        MszToken token = new MszToken();
        token.setUserId(userId);
        MszToken mszToken = tokenMapper.selectOne(token);
        return mszToken;
    }

}
