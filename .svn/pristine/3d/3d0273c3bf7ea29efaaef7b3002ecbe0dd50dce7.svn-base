package com.msz.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.msz.VO.WithdrawReturnParamVO;
import com.msz.VO.WithdrawVO;
import com.msz.model.MszWithdraw;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 提现记录 Mapper 接口
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Mapper
@Repository
public interface MszWithdrawMapper extends BaseMapper<MszWithdraw> {

    @Update("update msz_wallet set balance = balance - #{amount.stringCache} where accountId = #{accId}")
    boolean withdraw(@Param("accId") Integer accId,@Param("amount") BigDecimal amount);

    List<WithdrawVO> getListByYearMonth(@Param("createTimeMin") String createTimeMin,@Param("createTimeMax") String createTimeMax );

    List<MszWithdraw> findList(@Param("createTimeMin") String createTimeMin,@Param("createTimeMax") String createTimeMax,
                               @Param("date") String date);

    List<WithdrawReturnParamVO> listAll(WithdrawReturnParamVO vo);

    List<WithdrawReturnParamVO> exportExcelData(@Param("date") String date);

}
