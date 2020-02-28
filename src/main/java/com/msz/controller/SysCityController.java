package com.msz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageInfo;
import com.msz.common.PagingRequest;
import com.msz.common.RespEntity;
import com.msz.model.SysCity;
import com.msz.service.SysCityService;
import com.msz.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Api(value = "/sys-citys", description = " 省市区接口; Responseble:cww")
@RestController
@RequestMapping("/sys-citys")
public class SysCityController {

    /**
     * id  String
     * <p>
     * name  String
     * <p>
     * province  String
     * <p>
     * city  String
     * <p>
     * district  String
     * <p>
     * town  String
     * <p>
     * parent  String
     * <p>
     * level  boolean
     * <p>
     * 经度  lng  String
     * <p>
     * 纬度  lat  String
     */
    @Autowired
    private SysCityService sysCityService;


    @GetMapping("/getCityListByParent")
    @ApiOperation(value = "根据id获取区或街道列表", notes = "根据id获取区或街道列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "name", value = "城市名称")
    })
    public RespEntity<SysCity> getCityListByParent(@RequestParam String id, @RequestParam(required = false) String name) {
        EntityWrapper<SysCity> wrapper = new EntityWrapper<>();
        wrapper.eq("parent", id).like("name", name);
        List<SysCity> sysCities = sysCityService.selectList(wrapper);
        return RespEntity.ok().setResponseContent(sysCities);
    }

    @GetMapping("/getGuangDong")
    @ApiOperation(value = "获取所有广东有带层级的所有数据-------小程序@Author=Maoyy", notes = "获取所有广东有带层级的所有数据-------小程序@Author=Maoyy")
    public RespEntity<SysCity> getGuangDong() {
        return RespEntity.ok().setResponseContent(sysCityService.getGuangDong());
    }

    @ApiOperation(value = "查询所有市-------PC(添加房源)@Author=cww@Update=Maoyy", notes = "查询所有市-------PC(添加房源)@Author=cww@Update=Maoyy")
    @GetMapping("/getCityList")
    public RespEntity<PageInfo<SysCity>> getCityList() {
        return RespEntity.ok().setResponseContent(sysCityService.getCityList());
    }

}