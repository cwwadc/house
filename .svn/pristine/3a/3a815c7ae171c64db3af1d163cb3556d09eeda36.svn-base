package com.msz.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.msz.VO.MszOrderHistoryVO;
import com.msz.VO.MszOrderInfoVo;
import com.msz.VO.PayInfoVO;
import com.msz.model.MszOrderHistory;
import com.msz.model.MszPayInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
}
