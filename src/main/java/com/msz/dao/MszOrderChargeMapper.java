package com.msz.dao;

import com.msz.model.MszLeaseCharge;
import com.msz.model.MszOrderCharge;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 缴费收费项目（破坏房屋设施等） Mapper 接口
 * </p>
 *
 * @author Maoyy
 * @since 2019-10-08
 */
@Mapper
@Repository
public interface MszOrderChargeMapper extends BaseMapper<MszOrderCharge> {

    List<MszLeaseCharge> getMoneyByLease(@Param("leaseId") Integer leaseId);
}
