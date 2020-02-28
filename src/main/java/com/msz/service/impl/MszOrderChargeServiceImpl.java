package com.msz.service.impl;

import com.msz.dao.MszLeaseMapper;
import com.msz.dao.MszOrderChargeMapper;
import com.msz.model.MszLeaseCharge;
import com.msz.model.MszOrderCharge;
import com.msz.service.MszOrderChargeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.msz.common.PagingRequest;

import java.util.List;


/**
 * <p>
 * 缴费收费项目（破坏房屋设施等） 服务实现类
 * </p>
 *
 * @author Maoyy
 * @since 2019-10-08 ${time}
 */
@Service
public class MszOrderChargeServiceImpl extends ServiceImpl<MszOrderChargeMapper, MszOrderCharge> implements MszOrderChargeService {
    @Autowired
    private MszOrderChargeMapper mszOrderChargeMapper;

    @Override
    public PageInfo<MszOrderCharge> listPage(PagingRequest pagingRequest,Wrapper wrapper){
        PageHelper.startPage( pagingRequest.getOffset(), pagingRequest.getLimit() );
        return new PageInfo<>(super.selectList(wrapper));
    }

    @Override
    public List<MszLeaseCharge> getMoneyByLease(Integer leaseId){
        return  mszOrderChargeMapper.getMoneyByLease(leaseId);
    }

}
