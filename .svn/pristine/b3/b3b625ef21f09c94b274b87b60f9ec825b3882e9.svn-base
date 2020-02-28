package com.msz.service.impl;

import com.msz.dao.MszRefundMessageMapper;
import com.msz.model.MszRefundMessage;
import com.msz.service.MszRefundMessageService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.msz.common.PagingRequest;


/**
 * <p>
 * 押金退款详细 服务实现类
 * </p>
 *
 * @author Maoyy
 * @since 2019-10-18 ${time}
 */
@Service
public class MszRefundMessageServiceImpl extends ServiceImpl<MszRefundMessageMapper, MszRefundMessage> implements MszRefundMessageService {

    @Override
    public PageInfo<MszRefundMessage> listPage(PagingRequest pagingRequest,Wrapper wrapper){
        PageHelper.startPage( pagingRequest.getOffset(), pagingRequest.getLimit() );
        return new PageInfo<>(super.selectList(wrapper));
    }

}
