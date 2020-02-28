package com.msz.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.common.PagingRequest;
import com.msz.dao.SysRoleMenuMapper;
import com.msz.model.SysRoleMenu;
import com.msz.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;


/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author cww
 * @since 2019-06-29 ${time}
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Override
    public PageInfo<SysRoleMenu> listPage(PagingRequest pagingRequest, Wrapper wrapper){
        PageHelper.startPage( pagingRequest.getOffset(), pagingRequest.getLimit() );
        return new PageInfo<>(super.selectList(wrapper));
    }

}
