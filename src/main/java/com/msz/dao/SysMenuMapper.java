package com.msz.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.msz.model.SysAuthorities;
import com.msz.model.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Mapper
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysAuthorities> getAuthoritiesByMenuId(@Param("menuId") Integer menuId);
}
