package com.msz.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msz.VO.SysDictVo;
import com.msz.common.PagingRequest;
import com.msz.model.SysDict;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
public interface SysDictService extends IService<SysDict> {
    
    PageInfo<SysDict> listPage(PagingRequest pagingRequest, Wrapper wrapper);

    PageInfo<SysDictVo> listSysDict(PagingRequest pagingRequest,String pname, Integer pid, String name);


        
}
