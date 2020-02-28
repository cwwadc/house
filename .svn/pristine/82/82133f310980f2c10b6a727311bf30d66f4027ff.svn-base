package com.msz.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.VO.HistoryParamVO;
import com.msz.VO.IncomeListVo;
import com.msz.VO.MszOrderHistoryVO;
import com.msz.VO.PayInfoVO;
import com.msz.common.PagingRequest;
import com.msz.common.UserCommon;
import com.msz.dao.MszOrderHistoryMapper;
import com.msz.dao.MszOrderInfoMapper;
import com.msz.model.MszOrderHistory;
import com.msz.model.SysUser;
import com.msz.service.MszOrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;

import java.util.Date;
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

    @Override
    public List<IncomeListVo> incomeStatement(PagingRequest pagingRequest, String name, Date startTime, Date endTime){
        int start = (pagingRequest.getOffset() - 1) * pagingRequest.getLimit();
        return mszOrderHistoryMapper.incomeStatement(start, pagingRequest.getLimit(),name, startTime, endTime);
    }

    @Override
    public int incomeStatementSums(String name, Date startTime, Date endTime){
        return mszOrderHistoryMapper.incomeStatementSums(name, startTime, endTime);
    }

    @Override
    public List<IncomeListVo> incomeStatement2(PagingRequest pagingRequest, Integer orgId, String name, Date startTime, Date endTime){
        int start = (pagingRequest.getOffset() - 1) * pagingRequest.getLimit();
        return mszOrderHistoryMapper.incomeStatement2(orgId, name, startTime, endTime);
    }

    @Override
    public int incomeStatement2Sums(Integer orgId, String name, Date startTime, Date endTime) {
        return mszOrderHistoryMapper.incomeStatement2Sums(orgId, name, startTime, endTime);
    }

    @Override
    public List<MszOrderHistory> incomeMonthly (Integer orgId, String groupTime){
        return mszOrderHistoryMapper.incomeMonthly(orgId, groupTime);
    }

    @Override
    public List<MszOrderHistory> incomeStatementMessage(Integer orgId){
        SysUser user = UserCommon.getCurrentUser();
        List<MszOrderHistory> historyList;
        HistoryParamVO historyParamVO = new HistoryParamVO();
        if ("管理员".equals(user.getRole()) || "财务人员".equals(user.getRole())){
            historyParamVO.setOrgId(orgId);
            historyList = mszOrderHistoryMapper.incomeStatementMessage(historyParamVO);
        }else {
            historyParamVO.setOrgId(user.getOrgId());
            historyList = mszOrderHistoryMapper.incomeStatementMessage(historyParamVO);
        }
        return historyList;
    }

    @Override
    public List<MszOrderHistory> selectexportExcelData(Integer id, Date startTime, Date endTime){
        return mszOrderHistoryMapper.selectexportExcelData(id, startTime, endTime);
    }

}
