package com.msz.dao;

import com.msz.model.MszArrears;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 欠租 Mapper 接口
 * </p>
 *
 * @author Maoyy
 * @since 2019-10-11
 */
@Mapper
@Repository
public interface MszArrearsMapper extends BaseMapper<MszArrears> {

}
