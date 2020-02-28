package com.msz.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.common.PagingRequest;
import com.msz.dao.SysMenuMapper;
import com.msz.dao.SysUserMapper;
import com.msz.model.SysAuthorities;
import com.msz.model.SysMenu;
import com.msz.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    SysMenuMapper menuMapper;
    @Autowired
    SysUserMapper userMapper;

    @Override
    public PageInfo<SysMenu> listPage(PagingRequest pagingRequest, Wrapper wrapper){
        PageHelper.startPage( pagingRequest.getOffset(), pagingRequest.getLimit() );
        return new PageInfo<>(super.selectList(wrapper));
    }

    @Override
    public List<SysAuthorities> getAuthoritiesByMenuId(Integer menuId) {
        List<SysAuthorities> list= menuMapper.getAuthoritiesByMenuId(menuId);
        return list;
    }

   /* @Override
    public List<SysMenu> getMenuListByRoleId(Integer roleId) {
        List<SysMenu> rootMenu = userMapper.getMenuListByRoleId(roleId);
        List<SysMenu> result = new ArrayList<>();
        if (rootMenu != null && rootMenu.size() > 0) {
            for (SysMenu menu : rootMenu) {
                List<SysMenu> childList = menuMapper.selectList(new EntityWrapper<SysMenu>().eq("parentId", menu.getId()));
                menu.setChildMenus(childList);
                result.add(menu);
            }
        }
        return rootMenu;
    }*/
    @Override
    public List<SysMenu> getMenuListByRoleId(Integer roleId) {
        List<SysMenu> list = userMapper.getMenuListByRoleId(roleId);
        List<SysMenu> menuTreeList = null;
        if (list != null && list.size() > 0){
           menuTreeList = getMenuTree(list, 0);
        }
        return menuTreeList;
    }

    /**
     * 递归取出所有关系树
     * @param list
     * @param pid
     * @return
     */
    private List<SysMenu> getMenuTree(List<SysMenu> list,int pid) {
        List<SysMenu> menuList=new ArrayList<>();
        //取出所有菜单
        for (int i = 0;i< list.size();i++) {
            SysMenu menu = list.get(i);
            if (menu != null) {
                if (menu.getParentId() == pid) {//取出所有父菜单
                    menu.setChildMenus(getMenuTree(list, menu.getId()));
                    menuList.add(menu);
                }
            }
        }
        return menuList;
    }
}
