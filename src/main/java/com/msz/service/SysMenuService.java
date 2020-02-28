package com.msz.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msz.common.PagingRequest;
import com.msz.model.SysAuthorities;
import com.msz.model.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
public interface SysMenuService extends IService<SysMenu> {
    
    PageInfo<SysMenu> listPage(PagingRequest pagingRequest, Wrapper wrapper);

    List<SysAuthorities> getAuthoritiesByMenuId(Integer menuId);

    List<SysMenu> getMenuListByRoleId(Integer roleId);
        
}
