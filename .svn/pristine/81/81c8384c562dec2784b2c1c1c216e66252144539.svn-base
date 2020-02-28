package com.msz.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msz.common.PagingRequest;
import com.msz.model.SysAuthorities;
import com.msz.model.SysRole;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
public interface SysRoleService extends IService<SysRole> {

    PageInfo<SysRole> listPage(PagingRequest pagingRequest);

    boolean authorize(Integer roleId, String menus);
        
}
