package com.msz.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msz.VO.MszPayInfoVO;
import com.msz.VO.PayInfoMonthGroupVO;
import com.msz.VO.PayInfoReturnParam;
import com.msz.VO.PayInfoVO;
import com.msz.common.PagingRequest;
import com.msz.model.MszPayInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 支付信息（作为发起支付、支付回调的中转，以及支付记录的留存） 服务类
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
public interface MszPayInfoService extends IService<MszPayInfo> {


    PageInfo<MszPayInfo> listPage(PagingRequest pagingRequest, Wrapper wrapper);



    PageInfo<PayInfoVO> byYearMonthGroup(Integer accId);

    /**
     * @Author Maoyy
     * @Description 代码写了一行又一行
     * @Date 11:52 2019/6/13
     **/
    PageInfo<MszPayInfo> byMonthGroup(Integer accId, String date);

    /**
     * @Author Maoyy
     * @Description 代码写了一行又一行
     * @Date 14:28 2019/6/14
     **/
    MszPayInfoVO mszPayInfo(String id);

    /**
     * 后台收入明细列表
     * @param pagingRequest
     * @param orgId
     * @param createTimeMin
     * @param createTimeMax
     * @return
     */
    PageInfo<PayInfoReturnParam> findList(PagingRequest pagingRequest, Integer orgId, String createTimeMin, String createTimeMax, String date);

    /**
     * 收入详情
     * @param id
     * @return
     */
    PayInfoReturnParam getPayInfoDesc(String id);

    /**
     * 收入明细导出
     * @param orgId
     * @param date
     * @return
     */
    List<PayInfoReturnParam> exportExcelData(Integer orgId, String date);

    /**
     * 收入汇总
     * @param orgId
     * @param createTimeMin
     * @param createTimeMax
     * @return
     */
    PageInfo<PayInfoVO> getListByYearMonth(Integer orgId, String createTimeMin, String createTimeMax);




}
