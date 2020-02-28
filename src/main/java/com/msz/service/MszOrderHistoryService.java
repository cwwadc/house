package com.msz.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msz.VO.IncomeListVo;
import com.msz.VO.MszOrderHistoryVO;
import com.msz.VO.PayInfoVO;
import com.msz.common.PagingRequest;
import com.msz.model.MszOrderHistory;
import com.msz.model.MszPayInfo;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 历史缴费信息 服务类
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
public interface MszOrderHistoryService extends IService<MszOrderHistory> {

    PageInfo<MszOrderHistory> listPage(PagingRequest pagingRequest, Wrapper wrapper);

    List<PayInfoVO> byYearMonthGroup(Integer landlordId);

    List<MszOrderHistoryVO> byMonthGroup(Integer id, String date);

    List<IncomeListVo> incomeStatement(PagingRequest pagingRequest, String name, Date startTime, Date endTime);

    int incomeStatementSums(String name, Date startTime, Date endTime);

    List<IncomeListVo> incomeStatement2(PagingRequest pagingRequest, Integer orgId, String name, Date startTime, Date endTime);

    int incomeStatement2Sums(Integer orgId, String name, Date startTime, Date endTime);

    List<MszOrderHistory> incomeMonthly (Integer orgId, String groupTime);

    List<MszOrderHistory> incomeStatementMessage(Integer orgId);

    List<MszOrderHistory> selectexportExcelData(Integer id, Date startTime, Date endTime);
}
