package com.msz.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.VO.MszOrderHistoryVO;
import com.msz.VO.PayInfoVO;
import com.msz.common.PagingRequest;
import com.msz.dao.MszOrderHistoryMapper;
import com.msz.dao.MszOrderInfoMapper;
import com.msz.model.MszOrderHistory;
import com.msz.service.MszOrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;

import java.util.List;


/**
 * <p>
 * 历史缴费信息 服务实现类
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Service
public class MszOrderHistoryServiceImpl extends ServiceImpl<MszOrderHistoryMapper, MszOrderHistory> implements MszOrderHistoryService {

    @Autowired
    private MszOrderHistoryMapper mszOrderHistoryMapper;

    @Override
    public PageInfo<MszOrderHistory> listPage(PagingRequest pagingRequest, Wrapper wrapper) {
        PageHelper.startPage(pagingRequest.getOffset(), pagingRequest.getLimit());
        return new PageInfo<>(super.selectList(wrapper));
    }

    @Override
    public List<PayInfoVO> byYearMonthGroup(Integer landlordId) {
        return mszOrderHistoryMapper.byYearMonthGroup(landlordId);
    }

    @Override
    public List<MszOrderHistoryVO> byMonthGroup(Integer id, String date) {
        return mszOrderHistoryMapper.byMonthGroup(id,date);
    }

}
