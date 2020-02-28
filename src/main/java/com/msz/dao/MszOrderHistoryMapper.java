package com.msz.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.msz.VO.*;
import com.msz.model.MszOrderHistory;
import com.msz.model.MszPayInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 历史缴费信息 Mapper 接口
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Mapper
@Repository
public interface MszOrderHistoryMapper extends BaseMapper<MszOrderHistory> {

    List<MszOrderInfoVo> findList(MszOrderInfoVo mszOrderInfoVo);

    List<PayInfoVO> byYearMonthGroup(@Param("landlordId") Integer landlordId);

    List<MszOrderHistoryVO> byMonthGroup(@Param("id") Integer id, @Param("date") String date);

    List<IncomeListVo> incomeStatement(@Param("start") int start,
                                       @Param("size") int size,
                                       @Param("name") String name,
                                       @Param("startTime") Date startTime,
                                       @Param("endTime") Date endTime);

    int incomeStatementSums(@Param("name") String name,
                            @Param("startTime") Date startTime,
                            @Param("endTime") Date endTime);


    List<IncomeListVo> incomeStatement2(@Param("orgId") Integer orgId,
                                        @Param("name") String name,
                                        @Param("startTime") Date startTime,
                                        @Param("endTime") Date endTime);

    int incomeStatement2Sums(@Param("orgId") Integer orgId,
                            @Param("name") String name,
                            @Param("startTime") Date startTime,
                            @Param("endTime") Date endTime);

    List<MszOrderHistory> incomeMonthly (@Param("orgId") Integer orgId, @Param("groupTime") String groupTime);

    List<MszOrderHistory> incomeStatementMessage(HistoryParamVO historyParamVO);

    List<MszOrderHistory> selectexportExcelData(@Param("id") Integer id,
                                                @Param("startTime") Date startTime,
                                                @Param("endTime") Date endTime);
}
