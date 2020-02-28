package com.msz.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.msz.VO.MszRefundInfoVO;
import com.msz.model.MszRefundInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 退款信息 Mapper 接口
 * </p>
 *
 * @author cww
 * @since 2019-06-14
 */
@Mapper
@Repository
public interface MszRefundInfoMapper extends BaseMapper<MszRefundInfo> {

    List<MszRefundInfoVO> findList(MszRefundInfoVO vo);

    MszRefundInfo getDescById(Integer id);

}
