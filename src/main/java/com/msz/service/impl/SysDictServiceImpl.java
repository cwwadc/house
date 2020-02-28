package com.msz.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.VO.SysDictVo;
import com.msz.common.PagingRequest;
import com.msz.dao.SysDictMapper;
import com.msz.model.SysDict;
import com.msz.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;

import java.util.List;


/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    @Autowired
    SysDictMapper sysDictMapper;

    @Override
    public PageInfo<SysDict> listPage(PagingRequest pagingRequest, Wrapper wrapper){
        PageHelper.startPage( pagingRequest.getOffset(), pagingRequest.getLimit() );
        return new PageInfo(super.selectList(wrapper));
    }

    @Override
    public PageInfo<SysDictVo> listSysDict(PagingRequest pagingRequest,String pname, Integer pid, String name) {
        PageHelper.startPage( pagingRequest.getOffset(), pagingRequest.getLimit() );
        List<SysDictVo> list = sysDictMapper.listSysDict(pname,pid, name);
        return new PageInfo(list);
    }


}
