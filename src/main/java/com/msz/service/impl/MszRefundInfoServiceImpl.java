package com.msz.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msz.VO.MszRefundInfoVO;
import com.msz.VO.MszWithdrawStatusNum;
import com.msz.common.PagingRequest;
import com.msz.dao.MszAccountMapper;
import com.msz.dao.MszLeaseMapper;
import com.msz.dao.MszRefundInfoMapper;
import com.msz.dao.SysUserMapper;
import com.msz.model.MszRefundInfo;
import com.msz.model.MszWithdraw;
import com.msz.service.MszRefundInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 退款信息 服务实现类
 * </p>
 *
 * @author cww
 * @since 2019-06-14 ${time}
 */
@Service
public class MszRefundInfoServiceImpl extends ServiceImpl<MszRefundInfoMapper, MszRefundInfo> implements MszRefundInfoService {

    @Autowired
    MszRefundInfoMapper refundInfoMapper;

    @Override
    public PageInfo<MszRefundInfoVO> listPage(PagingRequest pagingRequest,String status){
        PageHelper.startPage( pagingRequest.getOffset(), pagingRequest.getLimit() );
        MszRefundInfoVO vo = new MszRefundInfoVO();
        vo.setStatus(status);
        List<MszRefundInfoVO> list = refundInfoMapper.findList(vo);
        return new PageInfo<>(list);
    }

    @Override
    public MszWithdrawStatusNum getCountNum() {
        //退款状态 0待审核 1已同意 2已拒绝
        MszWithdrawStatusNum vo = new MszWithdrawStatusNum();
        vo.setCheckNum(refundInfoMapper.selectCount(new EntityWrapper<MszRefundInfo>().eq("status","0")));
        vo.setAgreeNum(refundInfoMapper.selectCount(new EntityWrapper<MszRefundInfo>().eq("status","1")));
        vo.setNoAgreeNum(refundInfoMapper.selectCount(new EntityWrapper<MszRefundInfo>().eq("status","2")));
        vo.setRefuseNum(refundInfoMapper.selectCount(new EntityWrapper<MszRefundInfo>().eq("status","3")));
        return vo;
    }

    @Override
    public MszRefundInfo getDescById(Integer id) {
        MszRefundInfo vo = refundInfoMapper.getDescById(id);
        return vo;
    }

}
