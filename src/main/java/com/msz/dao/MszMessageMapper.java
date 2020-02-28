package com.msz.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.msz.VO.SystemMessageVO;
import com.msz.model.MszMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 消息 Mapper 接口
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Mapper
@Repository
public interface MszMessageMapper extends BaseMapper<MszMessage> {

    List<SystemMessageVO> selectMessageList(@Param("id") Integer id, @Param("isRead") String isRead);

}
