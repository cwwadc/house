package com.msz.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msz.VO.MszRefundInfoVO;
import com.msz.VO.MszWithdrawStatusNum;
import com.msz.common.PagingRequest;
import com.msz.model.MszRefundInfo;

/**
 * <p>
 * 退款信息 服务类
 * </p>
 *
 * @author cww
 * @since 2019-06-14
 */
public interface MszRefundInfoService extends IService<MszRefundInfo> {
    
    PageInfo<MszRefundInfoVO> listPage(PagingRequest pagingRequest, String status);

    MszWithdrawStatusNum getCountNum();

    MszRefundInfo getDescById(Integer id);

}
