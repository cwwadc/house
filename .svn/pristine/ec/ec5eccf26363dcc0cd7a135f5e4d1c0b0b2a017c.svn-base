package com.msz.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.common.PagingRequest;
import com.msz.dao.SysMenuMapper;
import com.msz.dao.SysUserMapper;
import com.msz.model.SysMenu;
import com.msz.model.SysUser;
import com.msz.service.SysUserService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;

import java.util.*;


/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    SysUserMapper userMapper;
    @Autowired
    SysUserService userService;
    @Autowired
    SysMenuMapper sysMenuMapper;


    @Override
    public PageInfo<SysUser> getAdminList(PagingRequest pagingRequest) {
        List<SysUser> adminList = userMapper.getAdminList();
        return new PageInfo<>(adminList);
    }

    @Override
    public List<SysUser> getPrincipalList() {
        List<SysUser> principalList = userMapper.getPrincipalList();
        return principalList;
    }

    @Override
    public SysUser login(String username, String password) {
        SysUser user = userMapper.login(username, password);
        if (user != null) {
            // 原始的数据
            List<SysMenu> rootMenu = userMapper.getMenuListByUserId(user.getId());
            List<SysMenu> menuTree = null;
            if (rootMenu != null && rootMenu.size() >0){
               menuTree = getMenuTree(rootMenu, 0);
            }
            user.setMenuList(menuTree);
            user.setRole(userMapper.getRoleByUserId(user.getId()).getRoleName());
        }
        return user;
    }

    @Override
    public SysUser getLoginUserById(Integer userId) {
        SysUser user = userMapper.getLoginUserById(userId);
        return user;
    }

    @Override
    public SysUser findByUsername(String username) {
        SysUser user = userService.selectOne(new EntityWrapper<SysUser>().eq("username", username));
        if (user != null) {
            List<SysMenu> menuList = userMapper.getMenuListByUserId(user.getId());
            user.setMenuList(menuList);
            user.setRole(userMapper.getRoleByUserId(user.getId()).getRoleName());
        }
        return user;
    }

    @Override
    public List<SysUser> getSalesmanList(String name) {
        List<SysUser> userList = userMapper.getSalesmanList(name);
        return userList;
    }

    /**
     * 递归取出所有关系树
     *
     * @param list
     * @param pid
     * @return
     */
    private List<SysMenu> getMenuTree(List<SysMenu> list, int pid) {
        List<SysMenu> menuList = new ArrayList<>();
        //取出所有菜单
        for (int i = 0; i < list.size(); i++) {
            SysMenu menu = list.get(i);
            if (menu != null) {
                if (menu.getParentId() == pid) {//取出所有父菜单
                    if (menu.getParentId() == 0) {
                        menu.setChildMenus(getMenuTree(list, menu.getId()));
                    }
                    menuList.add(menu);
                }
            }
        }
        return menuList;
    }


}
