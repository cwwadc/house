package com.msz.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msz.VO.MessageParamVO;
import com.msz.VO.SystemMessageVO;
import com.msz.common.PagingRequest;
import com.msz.model.MszMessage;
import com.msz.model.SysUser;

import java.util.List;

/**
 * <p>
 * 消息 服务类
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
public interface MszMessageService extends IService<MszMessage> {
    /**
     * @Author Maoyy
     * @Description 分页
     * @Date 20:06 2019/7/12
     **/
    PageInfo<MszMessage> listPage(PagingRequest pagingRequest, Wrapper wrapper);

    /**
     * @Author Maoyy
     * @Description 不分页
     * @Date 20:06 2019/7/12
     **/
    List<SystemMessageVO> listPage(Integer id, String isRead);

    /**
     * 后台系统已处理/未处理数量
     * @return
     */
    MessageParamVO getMszMessageNum(SysUser user);


}
