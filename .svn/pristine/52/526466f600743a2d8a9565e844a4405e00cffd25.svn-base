package com.msz.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msz.common.PagingRequest;
import com.msz.model.MszToken;

/**
 * <p>
 * 用户token表 服务类
 * </p>
 *
 * @author Maoyy
 * @since 2019-07-17
 */
public interface MszTokenService extends IService<MszToken> {
    

    PageInfo<MszToken> listPage(PagingRequest pagingRequest, Wrapper wrapper);

    MszToken findByUserId(Integer userId);

}
