package com.msz.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msz.common.PagingRequest;
import com.msz.model.SysUser;

import java.util.List;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
public interface SysUserService extends IService<SysUser> {
    
    PageInfo<SysUser> getAdminList(PagingRequest pagingRequest);

    List<SysUser> getPrincipalList();

    SysUser login(String username, String password);

    SysUser getLoginUserById(Integer userId);

    SysUser findByUsername(String username);

    List<SysUser> getSalesmanList(String name);
}
