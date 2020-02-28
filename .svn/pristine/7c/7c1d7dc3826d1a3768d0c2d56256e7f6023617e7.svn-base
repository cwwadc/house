package com.msz.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msz.VO.MszWithdrawStatusNum;
import com.msz.VO.PayFromVO;
import com.msz.VO.WithdrawReturnParamVO;
import com.msz.VO.WithdrawVO;
import com.msz.common.PagingRequest;
import com.msz.model.MszWithdraw;
import java.util.List;

/**
 * <p>
 * 提现记录 服务类
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
public interface MszWithdrawService extends IService<MszWithdraw> {


    PageInfo<WithdrawReturnParamVO> listAll(PagingRequest pagingRequest, String status);

    PageInfo<WithdrawReturnParamVO> listPage(PagingRequest pagingRequest, Wrapper wrapper);

    PageInfo<WithdrawVO> getListByYearMonth(String createTimeMin, String createTimeMax);

    PageInfo<PayFromVO> findList(PagingRequest pagingRequest, String createTimeMin, String createTimeMax, String date);


    MszWithdrawStatusNum getCountNum();

    List<WithdrawReturnParamVO> exportExcelData(String date);
}
