package com.msz.service;

import com.msz.common.PagingRequest;
import com.msz.model.MszArrears;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;

/**
 * <p>
 * 欠租 服务类
 * </p>
 *
 * @author Maoyy
 * @since 2019-10-11
 */
public interface MszArrearsService extends IService<MszArrears> {
    

    PageInfo<MszArrears> listPage(PagingRequest pagingRequest, Wrapper wrapper);


    boolean insertArrears(MszArrears mszArrears);
}
