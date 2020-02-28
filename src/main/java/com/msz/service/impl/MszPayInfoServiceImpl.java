package com.msz.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.VO.MszPayInfoVO;
import com.msz.VO.PayInfoMonthGroupVO;
import com.msz.VO.PayInfoReturnParam;
import com.msz.VO.PayInfoVO;
import com.msz.common.PagingRequest;
import com.msz.dao.MszOrderInfoMapper;
import com.msz.dao.MszPayInfoMapper;
import com.msz.model.MszOrderInfo;
import com.msz.model.MszPayInfo;
import com.msz.service.MszPayInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 支付信息（作为发起支付、支付回调的中转，以及支付记录的留存） 服务实现类
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Service
public class MszPayInfoServiceImpl extends ServiceImpl<MszPayInfoMapper, MszPayInfo> implements MszPayInfoService {

    @Autowired
    private MszPayInfoMapper mszPayInfoMapper;

    @Autowired
    private MszOrderInfoMapper mszOrderInfoMapper;

    @Override
    public PageInfo<MszPayInfo> listPage(PagingRequest pagingRequest, Wrapper wrapper) {
        PageHelper.startPage(pagingRequest.getOffset(), pagingRequest.getLimit());
        return new PageInfo<>(super.selectList(wrapper));
    }

    /**
     * @Author Maoyy
     * @Description 代码写了一行又一行
     * @Date 9:16 2019/6/13
     **/
    @Override
    public PageInfo<PayInfoVO> byYearMonthGroup(Integer accId) {
        return new PageInfo<>(mszPayInfoMapper.byYearMonthGroup(accId));
    }

    @Override
    public PageInfo<MszPayInfo> byMonthGroup(Integer accId, String date) {
        return new PageInfo<>(mszPayInfoMapper.byMonthGroup(accId, date));
    }


    @Override
    public MszPayInfoVO mszPayInfo(String id) {
        MszPayInfoVO vo = new MszPayInfoVO();
        MszPayInfo mszPayInfo = selectOne(new EntityWrapper<MszPayInfo>().eq("id", id));
        BeanUtils.copyProperties(mszPayInfo, vo);
        vo.setOrderInfo(mszOrderInfoMapper.selectById(mszPayInfo.getOrderId()));
        return vo;
    }

    /**
     * 后台收入明细列表
     * @param pagingRequest
     * @param orgId
     * @param createTimeMin
     * @param createTimeMax
     * @return
     */
    @Override
    public PageInfo<PayInfoReturnParam> findList(PagingRequest pagingRequest, Integer orgId, String createTimeMin, String createTimeMax, String date) {
        PageHelper.startPage(pagingRequest.getOffset(), pagingRequest.getLimit());
        List<PayInfoReturnParam> list = mszPayInfoMapper.findList(orgId, createTimeMin, createTimeMax,date);
        return new PageInfo<>(list);
    }

    /**
     * 收入详情
     * @param id
     * @return
     */
    @Override
    public PayInfoReturnParam getPayInfoDesc(String id) {

        PayInfoReturnParam param = mszPayInfoMapper.getPayInfoDesc(id);
        return param;
    }

    /**
     * 收入明细导出
     * @param orgId
     * @param date
     * @return
     */
    @Override
    public List<PayInfoReturnParam> exportExcelData(Integer orgId, String date) {

        List<PayInfoReturnParam> list = mszPayInfoMapper.exportExcelData(orgId,date);
        return list;
    }

    /**
     * 收入汇总
     * @param orgId
     * @param createTimeMin
     * @param createTimeMax
     * @return
     */
    @Override
    public PageInfo<PayInfoVO> getListByYearMonth(Integer orgId, String createTimeMin, String createTimeMax) {

        return new PageInfo<>(mszPayInfoMapper.getListByYearMonth(orgId,createTimeMin,createTimeMax));
    }

}
