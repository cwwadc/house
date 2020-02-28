package com.msz.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msz.VO.MszOrderInfoVo;
import com.msz.VO.RoomStatusNumVO;
import com.msz.common.PagingRequest;
import com.msz.model.MszOrderInfo;
import com.msz.model.SysUser;


/**
 * <p>
 * 缴费信息 服务类
 * </p>
 *
 * @author cww
 * @since 2019-06-03
 */
public interface MszOrderInfoService extends IService<MszOrderInfo> {

    PageInfo<MszOrderInfoVo> listPage(PagingRequest pagingRequest, String status, String endTimeBegin, String endTimeEnd, String tenantName, SysUser user);

    /**
     * 未交/已交的数量
     * @return
     */
    RoomStatusNumVO getCountNum(SysUser user);

    /**
     * 缴费提醒
     * @param id
     * @return
     */
    boolean remind(Integer id, SysUser user);





}
