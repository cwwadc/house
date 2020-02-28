package com.msz.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.msz.VO.SysDictVo;
import com.msz.model.SysDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
@Repository
@Mapper
public interface SysDictMapper extends BaseMapper<SysDict> {

    List<SysDictVo> listSysDict(@Param("pname") String pname,@Param("pid") Integer pid,@Param("name") String name);
}
