package com.msz.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.msz.VO.GuangDongVO;
import com.msz.model.SysCity;
import org.apache.ibatis.annotations.Mapper;
import org.hibernate.validator.constraints.EAN;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Mapper
@Repository
public interface SysCityMapper extends BaseMapper<SysCity> {

    List<SysCity> getCityList();

    /**
     * @Author Maoyy
     * @Description 得到广东
     * @Date 16:41 2019/9/23
     **/
    List<GuangDongVO> getGuangDong();
}
