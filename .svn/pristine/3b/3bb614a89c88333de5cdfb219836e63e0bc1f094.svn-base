package com.msz.controller;

import com.github.pagehelper.PageInfo;
import com.msz.common.PagingRequest;
import com.msz.common.RespEntity;
import com.msz.model.SysRoleAuthorities;
import com.msz.service.SysRoleAuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;


/**
 * <p>
 * 角色权限 前端控制器
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Api(value = "/sys-role-authoritiess", description = "角色权限 接口; Responseble:cww")
@RestController
@RequestMapping("/sys-role-authoritiess")
public class SysRoleAuthoritiesController {

    /**
    
     *    id  Integer 
    
     *    角色id  roleId  Integer 
    
     *    权限id  authorityId  Integer 
    
     *    创建时间  createTime  Date 
         */
    @Autowired
    private SysRoleAuthoritiesService sysRoleAuthoritiesService;


    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取单个SysRoleAuthorities", notes = "根据id获取单个SysRoleAuthorities")
    public RespEntity<SysRoleAuthorities> get(@PathVariable Integer id){
        return RespEntity.ok().setResponseContent(sysRoleAuthoritiesService.selectById(id));
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query")
    })
    @ApiOperation(value = "分页查询SysRoleAuthorities", notes = "分页查询SysRoleAuthorities")
    @GetMapping
    public RespEntity<PageInfo<SysRoleAuthorities>>list(PagingRequest pagingRequest){
        return RespEntity.ok().setResponseContent(sysRoleAuthoritiesService.listPage(pagingRequest,null));
    }


    @PostMapping
    @ApiOperation(value = "保存SysRoleAuthorities", notes = "保存SysRoleAuthorities")
    public RespEntity insert(@RequestBody SysRoleAuthorities sysRoleAuthorities ){
        if ( ! sysRoleAuthoritiesService.insert( sysRoleAuthorities) ){
        return RespEntity.badRequest("保存失败");
        }
        return RespEntity.ok("保存成功");
    }


    @PutMapping
    @ApiOperation(value = "根据ID修改SysRoleAuthorities", notes = "根据ID修改SysRoleAuthorities")
    public RespEntity update(@RequestBody SysRoleAuthorities sysRoleAuthorities ){
        if ( ! sysRoleAuthoritiesService.updateById( sysRoleAuthorities ) ){
        return RespEntity.badRequest("更新失败");
        }
        return RespEntity.ok("更新成功");
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除SysRoleAuthorities", notes = "根据ID删除SysRoleAuthorities")
    public RespEntity delete(@PathVariable Integer id){
        if ( ! sysRoleAuthoritiesService.deleteById(id) ){
        return RespEntity.badRequest("删除失败");
        }
        return RespEntity.ok("删除成功");
    }

    
    
}