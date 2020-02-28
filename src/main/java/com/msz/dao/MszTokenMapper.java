package com.msz.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.msz.model.MszToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户token表 Mapper 接口
 * </p>
 *
 * @author Maoyy
 * @since 2019-07-17
 */
@Mapper
@Repository
public interface MszTokenMapper extends BaseMapper<MszToken> {

}
