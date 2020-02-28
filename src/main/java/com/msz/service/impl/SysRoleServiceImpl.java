package com.msz.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.common.PagingRequest;
import com.msz.dao.SysRoleAuthoritiesMapper;
import com.msz.dao.SysRoleMapper;
import com.msz.dao.SysRoleMenuMapper;
import com.msz.model.SysRole;
import com.msz.model.SysRoleAuthorities;
import com.msz.model.SysRoleMenu;
import com.msz.service.SysRoleAuthoritiesService;
import com.msz.service.SysRoleMenuService;
import com.msz.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    SysRoleAuthoritiesMapper roleAuthoritiesMapper;
    @Autowired
    SysRoleAuthoritiesService roleAuthoritiesService;
    @Autowired
    SysRoleMenuService roleMenuService;
    @Autowired
    SysRoleMapper roleMapper;

    @Override
    public PageInfo<SysRole> listPage(PagingRequest pagingRequest){
        PageHelper.startPage( pagingRequest.getOffset(), pagingRequest.getLimit() );
        List<SysRole> roleList = roleMapper.findList();
        return new PageInfo<>(roleList);
    }

    @Override
    public boolean authorize(Integer roleId, String menus) {
        List<SysRoleMenu> result = new ArrayList<>();
        String[] splits = menus.split(",");
        for (String str : splits) {
            SysRoleMenu roleMenu = new SysRoleMenu();
            Integer menuId = Integer.parseInt(str);
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleId);
            result.add(roleMenu);
        }
        //查看此角色是否拥有此菜单
        List<SysRoleMenu> selectList = roleMenuService.selectList(new EntityWrapper<SysRoleMenu>().eq("roleId", roleId));
        if (selectList != null && selectList.size() > 0){
            List<Integer> idList = new ArrayList<>();
            selectList.forEach(item->{
                idList.add(item.getId());
            });
            roleMenuService.deleteBatchIds(idList);
            roleMenuService.insertBatch(result);
            return true;
        }
        roleMenuService.insertBatch(result);
        return true;
    }

  /*  @Override
    public boolean authorize(Integer roleId, String authorities) {
        List<SysRoleAuthorities> result = new ArrayList<>();
        String[] splits = authorities.split(",");
        for (String str : splits) {
            SysRoleAuthorities roleAuthorities = new SysRoleAuthorities();
            Integer authoritiesId = Integer.parseInt(str);
            roleAuthorities.setAuthorityId(authoritiesId);
            roleAuthorities.setRoleId(roleId);
            result.add(roleAuthorities);
        }
        //查看此角色是否拥有权限
        List<SysRoleAuthorities> selectList = roleAuthoritiesService.selectList(new EntityWrapper<SysRoleAuthorities>().eq("roleId", roleId));
        if (selectList != null && selectList.size() > 0){
            List<Integer> idList = new ArrayList<>();
            selectList.forEach(item->{
                idList.add(item.getId());
            });
            roleAuthoritiesService.deleteBatchIds(idList);
            roleAuthoritiesService.insertBatch(result);
            return true;
        }
        roleAuthoritiesService.insertBatch(result);
        return true;
    }*/

}
