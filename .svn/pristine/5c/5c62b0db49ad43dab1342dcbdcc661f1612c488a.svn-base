package com.msz.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.VO.MessageParamVO;
import com.msz.VO.RoomStatusNumVO;
import com.msz.VO.SystemMessageVO;
import com.msz.common.PagingRequest;
import com.msz.common.UserCommon;
import com.msz.dao.MszMessageMapper;
import com.msz.model.MszMessage;
import com.msz.model.SysUser;
import com.msz.service.MszMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;

import java.util.List;


/**
 * <p>
 * 消息 服务实现类
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Service
public class MszMessageServiceImpl extends ServiceImpl<MszMessageMapper, MszMessage> implements MszMessageService {

    @Autowired
    MszMessageMapper messageMapper;

    @Autowired
    UserCommon userCommon;

    @Override
    public PageInfo<MszMessage> listPage(PagingRequest pagingRequest, Wrapper wrapper) {
        PageHelper.startPage(pagingRequest.getOffset(), pagingRequest.getLimit());
        return new PageInfo<>(super.selectList(wrapper));
    }

    @Override
    public List<SystemMessageVO> listPage(Integer id, String isRead) {
        return messageMapper.selectMessageList(id, isRead);
    }

    @Override
    public MessageParamVO getMszMessageNum(SysUser user) {
        MessageParamVO vo = new MessageParamVO();
        vo.setUnprocessedNum(messageMapper.selectCount(new EntityWrapper<MszMessage>().eq("isRead", 0).eq("isDel", "0").eq("receiverId", user.getId())));//未读
        vo.setProcessedNum(messageMapper.selectCount(new EntityWrapper<MszMessage>().eq("isRead", 1).eq("isDel", "0").eq("receiverId", user.getId())));//已读
        return vo;
    }

}
