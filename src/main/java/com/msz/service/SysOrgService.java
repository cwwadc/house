package com.msz.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msz.VO.SysOrgVo;
import com.msz.common.PagingRequest;
import com.msz.model.SysOrg;
import com.msz.model.SysUser;

import java.util.List;

/**
 * <p>
 * 组织机构表 服务类
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
public interface SysOrgService extends IService<SysOrg> {
    
    PageInfo<SysOrg> listPage(PagingRequest pagingRequest, Wrapper wrapper);

    PageInfo<SysOrgVo> getOrgList(PagingRequest pagingRequest, String searchField, SysUser user);

    List<SysUser> getCollectors(SysUser user, Integer orgId, String trueName);

    PageInfo<SysUser> getCollectorsPage(PagingRequest pagingRequest, Integer orgId, String trueName);

    boolean updateSetEmpty(SysUser user);

    PageInfo<SysUser> getOrgCollectors(PagingRequest pagingRequest,SysUser user, Integer orgId, String trueName);

}
