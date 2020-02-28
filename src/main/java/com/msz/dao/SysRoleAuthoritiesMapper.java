package com.msz.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.msz.model.SysRoleAuthorities;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 角色权限 Mapper 接口
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Mapper
@Repository
public interface SysRoleAuthoritiesMapper extends BaseMapper<SysRoleAuthorities> {

}
