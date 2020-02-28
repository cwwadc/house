package com.msz.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.msz.VO.MessageTeamplate;
import com.msz.common.UserCommon;
import com.msz.dao.MszArrearsMapper;
import com.msz.dao.MszMessageMapper;
import com.msz.dao.MszOrderHistoryMapper;
import com.msz.dao.MszOrderInfoMapper;
import com.msz.model.MszArrears;
import com.msz.model.MszMessage;
import com.msz.model.MszOrderHistory;
import com.msz.model.MszOrderInfo;
import com.msz.service.MszArrearsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.util.MessageTeamplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.msz.common.PagingRequest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>
 * 欠租 服务实现类
 * </p>
 *
 * @author Maoyy
 * @since 2019-10-11 ${time}
 */
@Service
public class MszArrearsServiceImpl extends ServiceImpl<MszArrearsMapper, MszArrears> implements MszArrearsService {

    @Autowired
    MszArrearsMapper arrearsMapper;
    @Autowired
    MszArrearsService arrearsService;
    @Autowired
    MszMessageMapper messageMapper;
    @Autowired
    MszOrderInfoMapper orderInfoMapper;
    @Autowired
    MszOrderHistoryMapper historyMapper;

    @Override
    public PageInfo<MszArrears> listPage(PagingRequest pagingRequest,Wrapper wrapper){
        PageHelper.startPage( pagingRequest.getOffset(), pagingRequest.getLimit() );
        return new PageInfo<>(super.selectList(wrapper));
    }

    @Override
    @Transactional
    public boolean insertArrears(MszArrears mszArrears) {
        MszArrears arrears = arrearsService.selectOne(new EntityWrapper<MszArrears>().eq("order_id",mszArrears.getOrderId()));
        if (arrears != null){
            arrearsMapper.updateById(mszArrears);
        }else {
            //1.添加欠租信息
            arrearsMapper.insert(mszArrears);
        }
        //2.欠租通知
        if( mszArrears.getAvailable().equals("1")) {
            // 是否消息提醒
            insertMessage(mszArrears);
        }
        return true;
    }

    private void insertMessage(MszArrears mszArrears) {
        MszMessage message = new MszMessage();
        message.setCreateTime(new Date());
        MszOrderInfo mszOrderInfo = orderInfoMapper.selectById(mszArrears.getOrderId());
        MszOrderHistory history = historyMapper.selectById(mszArrears.getOrderId());
        Integer tenantId = null;
        String orderNo = null;
        if(mszOrderInfo != null){
            tenantId = mszOrderInfo.getTenantId();
            orderNo = mszOrderInfo.getNo();
        }else if (history != null){
            tenantId = history.getTenantId();
            orderNo =history.getNo();
        }
        message.setReceiverId(tenantId);//房客id
        message.setType("4");//欠租通知
        MessageTeamplate messageTeamplate = MessageTeamplateUtil.arrearsOrderTenant(orderNo, mszArrears.getEndTime(), mszArrears.getArrearsAmount());
        message.setTitle(messageTeamplate.getTitle());
        message.setContentText(messageTeamplate.getContentText());
        messageMapper.insert(message);
    }

}
