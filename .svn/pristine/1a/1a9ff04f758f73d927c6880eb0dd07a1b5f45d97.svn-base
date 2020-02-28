package com.msz.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.VO.SysLogVO;
import com.msz.common.PagingRequest;
import com.msz.dao.SysLogMapper;
import com.msz.dao.SysUserMapper;
import com.msz.model.SysLog;
import com.msz.model.SysUser;
import com.msz.service.SysLogService;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {


    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysLogMapper logMapper;

    @Override
    public PageInfo<SysLogVO> listPage(PagingRequest pagingRequest,String createTimeMin,String createTimeMax){
        PageHelper.startPage( pagingRequest.getOffset(), pagingRequest.getLimit());
        SysLogVO vo = new SysLogVO();
        vo.setCreateTimeMin(createTimeMin);
        vo.setCreateTimeMax(createTimeMax);
        List<SysLogVO> list = logMapper.findList(vo);
        return new PageInfo<>(list);
    }

}
