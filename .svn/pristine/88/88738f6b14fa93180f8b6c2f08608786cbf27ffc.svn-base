package com.msz.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.common.PagingRequest;
import com.msz.dao.MszWalletMapper;
import com.msz.dao.MszWithdrawMapper;
import com.msz.model.MszWallet;
import com.msz.service.MszWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;

import java.math.BigDecimal;


/**
 * <p>
 * 用户钱包 服务实现类
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Service
public class MszWalletServiceImpl extends ServiceImpl<MszWalletMapper, MszWallet> implements MszWalletService {

    @Autowired
    private MszWithdrawMapper mszWithdrawMapper;

    @Override
    public PageInfo<MszWallet> listPage(PagingRequest pagingRequest, Wrapper wrapper) {
        PageHelper.startPage(pagingRequest.getOffset(), pagingRequest.getLimit());
        return new PageInfo<>(super.selectList(wrapper));
    }

    @Override
    public boolean withdraw(Integer accId, BigDecimal amount) {
        return mszWithdrawMapper.withdraw(accId, amount);
    }

    @Override
    public boolean walletPay(Integer id, BigDecimal total) {
        MszWallet mw = super.selectOne(new EntityWrapper<MszWallet>().eq("accountId", id));
        if (mw != null) {
            mw.setBalance(mw.getBalance().add(total));
            if (super.updateById(mw)) {
                return true;
            }
        }
        return false;
    }

}
