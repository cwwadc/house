package com.msz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageInfo;
import com.msz.common.PagingRequest;
import com.msz.common.RespEntity;
import com.msz.model.SysDict;
import com.msz.model.SysMenu;
import com.msz.service.SysDictService;
import com.sun.pisces.PiscesRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.sql.Wrapper;
import java.util.Date;


/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Api(value = "/sys-dicts", description = "字典表 接口; Responseble:cww")
@RestController
@RequestMapping("/sys-dicts")
public class SysDictController {

    /**
     * 主键id  id  Integer
     * <p>
     * 排序  num  Integer
     * <p>
     * 父级字典  pid  Integer
     * <p>
     * 名称  name  String
     * <p>
     * 提示  tips  String
     */
    @Autowired
    private SysDictService sysDictService;


    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取单个SysDict", notes = "根据id获取单个SysDict")
    public RespEntity<SysDict> get(@PathVariable Integer id) {
        return RespEntity.ok().setResponseContent(sysDictService.selectById(id));
    }


    @PostMapping
    @ApiOperation(value = "保存SysDict", notes = "保存SysDict")
    public RespEntity insert(@RequestBody SysDict sysDict) {
        if (!sysDictService.insert(sysDict)) {
            return RespEntity.badRequest("保存失败");
        }
        return RespEntity.ok("保存成功");
    }


    @PutMapping
    @ApiOperation(value = "根据ID修改SysDict", notes = "根据ID修改SysDict")
    public RespEntity update(@RequestBody SysDict sysDict) {
        sysDict.setCreateTime(new Date());
        if (!sysDictService.updateById(sysDict)) {
            return RespEntity.badRequest("更新失败");
        }
        return RespEntity.ok("更新成功");
    }

    @PutMapping("/qiYong/{id}")
    @ApiOperation(value = "字典启用", notes = "字典启用")
    public RespEntity qiYong(@PathVariable Integer id) {

        SysDict dict= new SysDict();
        dict.setId(id);
        dict.setIsDel("0");
        if (!sysDictService.updateById(dict)) {
            return RespEntity.badRequest("字典启用失败");
        }
        return RespEntity.ok("字典启用成功");
    }

    @PutMapping("/jinYong/{id}")
    @ApiOperation(value = "字典禁用", notes = "字典禁用")
    public RespEntity jinYong(@PathVariable Integer id) {
        SysDict dict= new SysDict();
        dict.setId(id);
        dict.setIsDel("1");
        if (!sysDictService.updateById(dict)) {
            return RespEntity.badRequest("字典禁用失败");
        }
        return RespEntity.ok("字典禁用成功");
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除SysDict", notes = "根据ID删除SysDict")
    public RespEntity delete(@PathVariable Integer id) {
        if (!sysDictService.deleteById(id)) {
            return RespEntity.badRequest("删除失败");
        }
        return RespEntity.ok("删除成功");
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pname", value = "字典类型名称"),
            @ApiImplicitParam(name = "pid", value = "字典值"),
            @ApiImplicitParam(name = "name", value = "字典名称"),
    })
    @ApiOperation(value = "分页查询SysDict", notes = "分页查询SysDict")
    @GetMapping("/listSysDict")
    public RespEntity<PageInfo<SysDict>> listSysDict(PagingRequest pagingRequest, String pname, Integer pid, String name) {
        return RespEntity.ok().setResponseContent(sysDictService.listSysDict(pagingRequest, pname, pid, name));
    }

    @ApiOperation(value = "查询pid为0的字典名称", notes = "查询pid为0的字典名称")
    @GetMapping("/getSysDictByPid")
    public RespEntity<PageInfo<SysDict>> getSysDictByPid() {

        EntityWrapper<SysDict> wrapper = new EntityWrapper<>();
        wrapper.eq("pid", "0");
        return RespEntity.ok().setResponseContent(sysDictService.selectList(wrapper));
    }

}