package com.msz.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.msz.VO.SysOrgVo;
import com.msz.model.SysOrg;
import com.msz.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 组织机构表 Mapper 接口
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Mapper
@Repository
public interface SysOrgMapper extends BaseMapper<SysOrg> {

    List<SysOrgVo> getOrgList(SysOrgVo sysOrgVo);

    List<SysUser> getCollectors(SysUser user);

    boolean updateSetEmpty(SysUser user);

    List<SysUser> getOrgCollectors(SysUser sysUser);

}
