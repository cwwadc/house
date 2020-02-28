package com.msz.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msz.VO.GuangDongVO;
import com.msz.common.PagingRequest;
import com.msz.model.SysCity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
public interface SysCityService extends IService<SysCity> {

    PageInfo<SysCity> listPage(PagingRequest pagingRequest, Wrapper wrapper);

    PageInfo<SysCity> getCityList();

    /**
    * @Author Maoyy
    * @Description 获取广东
    * @Date 16:04 2019/9/23
    **/
    List<GuangDongVO> getGuangDong();
}
