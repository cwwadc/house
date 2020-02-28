package com.msz.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msz.VO.MszOrderHistoryVO;
import com.msz.VO.PayInfoVO;
import com.msz.common.PagingRequest;
import com.msz.model.MszOrderHistory;
import com.msz.model.MszPayInfo;

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
}
