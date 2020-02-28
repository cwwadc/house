package com.msz.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.VO.MszWithdrawStatusNum;
import com.msz.VO.PayFromVO;
import com.msz.VO.WithdrawReturnParamVO;
import com.msz.VO.WithdrawVO;
import com.msz.common.PagingRequest;
import com.msz.common.UserCommon;
import com.msz.dao.MszAccountMapper;
import com.msz.dao.MszWithdrawMapper;
import com.msz.dao.SysUserMapper;
import com.msz.model.MszWithdraw;
import com.msz.service.MszWithdrawService;
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
 * 提现记录 服务实现类
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Service
public class MszWithdrawServiceImpl extends ServiceImpl<MszWithdrawMapper, MszWithdraw> implements MszWithdrawService {

    @Autowired
    MszWithdrawMapper withdrawMapper;
    @Autowired
    MszAccountMapper accountMapper;
    @Autowired
    SysUserMapper userMapper;
    @Autowired
    UserCommon userCommon;

    @Override
    public PageInfo<WithdrawReturnParamVO> listAll(PagingRequest pagingRequest, String status){
        PageHelper.startPage(pagingRequest.getOffset(), pagingRequest.getLimit() );
        WithdrawReturnParamVO vo = new WithdrawReturnParamVO();
        vo.setStatus(status);
        List<WithdrawReturnParamVO> list = withdrawMapper.listAll(vo);
        if (list != null && list.size() >0) {
            list.forEach(data -> {
                data.setMszAccount(accountMapper.selectById(data.getAccountId()));
            });
        }
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<WithdrawReturnParamVO> listPage(PagingRequest pagingRequest, Wrapper wrapper){
        PageHelper.startPage(pagingRequest.getOffset(), pagingRequest.getLimit() );
        return new PageInfo<>(super.selectList(wrapper));
    }

    @Override
    public PageInfo<WithdrawVO> getListByYearMonth(String createTimeMin, String createTimeMax) {
        List<WithdrawVO> listByYearMonth = withdrawMapper.getListByYearMonth(createTimeMin, createTimeMax);
        return new PageInfo<>(listByYearMonth);
    }

    @Override
    public PageInfo<PayFromVO> findList(PagingRequest pagingRequest, String createTimeMin, String createTimeMax, String date) {
        PageHelper.startPage( pagingRequest.getOffset(), pagingRequest.getLimit() );
        List<PayFromVO> list = withdrawMapper.findList(createTimeMin, createTimeMax, date);
        return new PageInfo<>(list);
    }

    @Override
    public MszWithdrawStatusNum getCountNum() {
        //状态：0待处理，1成功，2拒绝提现
        MszWithdrawStatusNum vo = new MszWithdrawStatusNum();
        vo.setCheckNum(withdrawMapper.selectCount(new EntityWrapper<MszWithdraw>().eq("status","0")));
        vo.setAgreeNum(withdrawMapper.selectCount(new EntityWrapper<MszWithdraw>().eq("status","1")));
        vo.setNoAgreeNum(withdrawMapper.selectCount(new EntityWrapper<MszWithdraw>().eq("status","2")));
        return vo;
    }

    @Override
    public List<WithdrawReturnParamVO> exportExcelData(String date) {
        List<WithdrawReturnParamVO> list = withdrawMapper.exportExcelData(date);
        return list;
    }

}
