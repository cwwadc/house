package com.msz.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.msz.VO.MszPayInfoVO;
import com.msz.VO.PayInfoMonthGroupVO;
import com.msz.VO.PayInfoReturnParam;
import com.msz.VO.PayInfoVO;
import com.msz.model.MszPayInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 支付信息（作为发起支付、支付回调的中转，以及支付记录的留存） Mapper 接口
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Mapper
@Repository
public interface MszPayInfoMapper extends BaseMapper<MszPayInfo> {

    List<PayInfoVO> byYearMonthGroup(@Param("accId") Integer accId);

    List<MszPayInfo> byMonthGroup(@Param("accId") Integer accId,@Param("date") String date);

    List<PayInfoReturnParam> findList(@Param("orgId") Integer orgId,@Param("createTimeMin") String createTimeMin,
                                      @Param("createTimeMax") String createTimeMax, @Param("date") String date);

    PayInfoReturnParam getPayInfoDesc(@Param("id") String id);

    List<PayInfoReturnParam> exportExcelData(@Param("orgId") Integer orgId,@Param("date") String date);

    List<PayInfoVO> getListByYearMonth(@Param("orgId") Integer orgId,@Param("createTimeMin") String createTimeMin,
                                        @Param("createTimeMax") String createTimeMax);
}
