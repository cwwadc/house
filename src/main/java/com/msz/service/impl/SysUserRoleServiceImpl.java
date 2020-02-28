package com.msz.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.common.PagingRequest;
import com.msz.dao.SysUserRoleMapper;
import com.msz.model.SysUserRole;
import com.msz.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;


/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public PageInfo<SysUserRole> listPage(PagingRequest pagingRequest, Wrapper wrapper){
        PageHelper.startPage( pagingRequest.getOffset(), pagingRequest.getLimit() );
        return new PageInfo<>(super.selectList(wrapper));
    }

}
