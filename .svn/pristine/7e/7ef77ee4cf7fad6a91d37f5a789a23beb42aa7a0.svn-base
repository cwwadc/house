package com.msz.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msz.VO.MszOrderInfoVo;
import com.msz.VO.PaymentVO;
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

    PageInfo<MszOrderInfoVo> listPage(PagingRequest pagingRequest, String status, String endTimeBegin, String endTimeEnd, String collectMoneyDay, String tenantName, SysUser user);

    /**
     * 未交/已交的数量
     *
     * @return
     */
    RoomStatusNumVO getCountNum(SysUser user);

    /**
     * 缴费提醒
     *
     * @param id
     * @return
     */
    boolean remind(Integer id, SysUser user);

    /**
     * 修改交费信息
     *
     * @param orderInfo
     * @return
     */
    boolean updateOrderInfo(MszOrderInfo orderInfo);

    /**
     * 已收
     *
     * @param id
     * @return
     */
    boolean receiveOrder(Integer id);

    /**
     * @Author Maoyy
     * @Description 计算欠收(应收租金 - 实收租金) + (应收押金 - 实收押金) + 所有的交费项(应用到每一期并在时效时间段内) -
     * @Date 14:35 2019/10/16
     **/
    MszOrderInfo countPrice(MszOrderInfo mszOrderInfo);
}
