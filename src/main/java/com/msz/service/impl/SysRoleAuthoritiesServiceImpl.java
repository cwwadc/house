package com.msz.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.common.PagingRequest;
import com.msz.dao.SysRoleAuthoritiesMapper;
import com.msz.model.SysRoleAuthorities;
import com.msz.service.SysRoleAuthoritiesService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;


/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Service
public class SysRoleAuthoritiesServiceImpl extends ServiceImpl<SysRoleAuthoritiesMapper, SysRoleAuthorities> implements SysRoleAuthoritiesService {

    @Override
    public PageInfo<SysRoleAuthorities> listPage(PagingRequest pagingRequest, Wrapper wrapper){
        PageHelper.startPage( pagingRequest.getOffset(), pagingRequest.getLimit() );
        return new PageInfo<>(super.selectList(wrapper));
    }

}
