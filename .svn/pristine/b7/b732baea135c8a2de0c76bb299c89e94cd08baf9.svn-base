package com.msz.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.msz.model.SysAuthorities;
import com.msz.model.SysMenu;
import com.msz.model.SysRole;
import com.msz.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.security.PermitAll;
import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Mapper
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> getPrincipalList();

    List<SysUser> getAdminList();

    List<SysAuthorities> getPermissionByUserId(Integer userId);

    List<SysMenu> getMenuListByUserId( Integer userId);

    List<SysMenu> getMenuListByRoleId( Integer roleId);

    SysRole getRoleByUserId(Integer userId);

    List<SysUser> getSalesmanList(@Param("name") String name);

    SysUser login(@Param("username") String username,@Param("password") String password);

    SysUser getLoginUserById(@Param("userId") Integer userId);
}
