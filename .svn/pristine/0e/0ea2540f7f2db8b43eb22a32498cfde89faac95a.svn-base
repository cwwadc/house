package com.msz.service.impl;

import com.msz.dao.MszLeaseChargeMapper;
import com.msz.model.MszLeaseCharge;
import com.msz.service.MszLeaseChargeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.msz.common.PagingRequest;


/**
 * <p>
 * 租约收费项目（水电费等） 服务实现类
 * </p>
 *
 * @author Maoyy
 * @since 2019-10-08 ${time}
 */
@Service
public class MszLeaseChargeServiceImpl extends ServiceImpl<MszLeaseChargeMapper, MszLeaseCharge> implements MszLeaseChargeService {

    @Override
    public PageInfo<MszLeaseCharge> listPage(PagingRequest pagingRequest,Wrapper wrapper){
        PageHelper.startPage( pagingRequest.getOffset(), pagingRequest.getLimit() );
        return new PageInfo<>(super.selectList(wrapper));
    }

}
