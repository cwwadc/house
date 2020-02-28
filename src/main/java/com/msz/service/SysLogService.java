package com.msz.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msz.VO.SysLogVO;
import com.msz.common.PagingRequest;
import com.msz.model.SysLog;

/**
 * <p>
 * 操作日志 服务类
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
public interface SysLogService extends IService<SysLog> {
    
    PageInfo<SysLogVO> listPage(PagingRequest pagingRequest,String createTimeMin,String createTimeMax);
    
        
}
