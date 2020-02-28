package com.msz.controller;

import com.github.pagehelper.PageInfo;
import com.msz.common.PagingRequest;
import com.msz.common.RespEntity;
import com.msz.model.SysAuthorities;
import com.msz.service.SysAuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;


/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Api(value = "/sys-authoritiess", description = "权限 接口; Responseble:cww")
@RestController
@RequestMapping("/sys-authoritiess")
public class SysAuthoritiesController {

    /**
    
     *    id  Integer 
    
     *    授权标识  authority  String 
    
     *    名称  authorityName  String 
    
     *    栏目ID  menuId  Integer 
    
     *    排序号  sort  Integer 
    
     *    创建时间  createTime  Date 
         */
    @Autowired
    private SysAuthoritiesService sysAuthoritiesService;


    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取单个SysAuthorities", notes = "根据id获取单个SysAuthorities")
    public RespEntity<SysAuthorities> get(@PathVariable Integer id){
        return RespEntity.ok().setResponseContent(sysAuthoritiesService.selectById(id));
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query")
    })
    @ApiOperation(value = "分页查询SysAuthorities", notes = "分页查询SysAuthorities")
    @GetMapping
    public RespEntity<PageInfo<SysAuthorities>>list(PagingRequest pagingRequest){
        return RespEntity.ok().setResponseContent(sysAuthoritiesService.listPage(pagingRequest,null));
    }


    @PostMapping
    @ApiOperation(value = "保存SysAuthorities", notes = "保存SysAuthorities")
    public RespEntity insert(@RequestBody SysAuthorities sysAuthorities ){
        if ( ! sysAuthoritiesService.insert( sysAuthorities) ){
        return RespEntity.badRequest("保存失败");
        }
        return RespEntity.ok("保存成功");
    }


    @PutMapping
    @ApiOperation(value = "根据ID修改SysAuthorities", notes = "根据ID修改SysAuthorities")
    public RespEntity update(@RequestBody SysAuthorities sysAuthorities ){
        if ( ! sysAuthoritiesService.updateById( sysAuthorities ) ){
        return RespEntity.badRequest("更新失败");
        }
        return RespEntity.ok("更新成功");
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除SysAuthorities", notes = "根据ID删除SysAuthorities")
    public RespEntity delete(@PathVariable Integer id){
        if ( ! sysAuthoritiesService.deleteById(id) ){
        return RespEntity.badRequest("删除失败");
        }
        return RespEntity.ok("删除成功");
    }

    
    
}