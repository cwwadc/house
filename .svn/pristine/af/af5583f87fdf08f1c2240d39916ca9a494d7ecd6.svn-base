package com.msz.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.common.PagingRequest;
import com.msz.dao.MszWalletBillMapper;
import com.msz.model.MszWalletBill;
import com.msz.service.MszWalletBillService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;


/**
 * <p>
 * 钱包流水 服务实现类
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Service
public class MszWalletBillServiceImpl extends ServiceImpl<MszWalletBillMapper, MszWalletBill> implements MszWalletBillService {

    @Override
    public PageInfo<MszWalletBill> listPage(PagingRequest pagingRequest, Wrapper wrapper){
        PageHelper.startPage( pagingRequest.getOffset(), pagingRequest.getLimit() );
        return new PageInfo<>(super.selectList(wrapper));
    }

}
