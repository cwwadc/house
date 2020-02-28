package com.msz.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msz.VO.GuangDongDept;
import com.msz.VO.GuangDongVO;
import com.msz.common.PagingRequest;
import com.msz.dao.SysCityMapper;
import com.msz.model.SysCity;
import com.msz.service.SysCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Service
public class SysCityServiceImpl extends ServiceImpl<SysCityMapper, SysCity> implements SysCityService {


    @Autowired
    SysCityMapper sysCityMapper;

    @Override
    public PageInfo<SysCity> listPage(PagingRequest pagingRequest, Wrapper wrapper) {
        PageHelper.startPage(pagingRequest.getOffset(), pagingRequest.getLimit());
        return new PageInfo<>(super.selectList(wrapper));
    }

    @Override
    public PageInfo<SysCity> getCityList() {
        List<SysCity> cityList = sysCityMapper.getCityList();
        return new PageInfo<>(cityList);
    }

    @Override
    public List<GuangDongVO> getGuangDong() {

//        List<SysCity> oneFloor = super.selectList(new EntityWrapper<SysCity>().eq("parent","44"));
//        for(SysCity oneFloorItem : oneFloor){
//            //第一层 广州。。。
//            GuangDongVO oneFloorGuangDong = new GuangDongVO();
//            oneFloorGuangDong.setName(oneFloorItem.getName());
//            List<SysCity> towFloor = selectList(new EntityWrapper<SysCity>().eq("parent",oneFloorItem.getId()));
//            List<GuangDongDept> dept = new ArrayList<>();
//            for(SysCity towFloorItem : towFloor){
//                GuangDongDept towGuangDong = new GuangDongDept();
//                towGuangDong.setName(towFloorItem.getName());
//                List<SysCity> depts = selectList(new EntityWrapper<SysCity>().eq("parent",towFloorItem.getId()))
//                for(SysCity threeFloorItem : depts){
//                }
//                dept.add(towGuangDong);
//            }
//            vo.add(oneFloorGuangDong);
//        }
        return sysCityMapper.getGuangDong();
    }

}
