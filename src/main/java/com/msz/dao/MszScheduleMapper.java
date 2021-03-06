package com.msz.dao;

import com.msz.VO.LeaseReturnParamVO;
import com.msz.VO.ScheduleVO;
import com.msz.model.MszSchedule;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.catalina.LifecycleState;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 预付定金表 Mapper 接口
 * </p>
 *
 * @author Maoyy
 * @since 2019-10-08
 */
@Mapper
@Repository
public interface MszScheduleMapper extends BaseMapper<MszSchedule> {

    List<LeaseReturnParamVO> findList(LeaseReturnParamVO vo);

    MszSchedule getSchedule(Integer id);

    int getScheduleNum(@Param("orgId") Integer orgId);
}
