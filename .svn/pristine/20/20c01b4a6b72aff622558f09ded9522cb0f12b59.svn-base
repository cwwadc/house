package com.msz.controller;

import com.msz.common.PagingRequest;
import com.msz.common.RespEntity;
import com.msz.model.SysUserRole;
import com.msz.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;


/**
 * <p>
 * 用户角色 前端控制器
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Api(value = "/sys-user-roles", description = "用户角色 接口; Responseble:cww")
@RestController
@RequestMapping("/sys-user-roles")
public class SysUserRoleController {

    /**
    
     *    id  Integer 
    
     *    用户id  userId  Integer 
    
     *    角色id  roleId  Integer 
    
     *    创建时间  createTime  Date 
         */
    @Autowired
    private SysUserRoleService sysUserRoleService;


    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取单个SysUserRole", notes = "根据id获取单个SysUserRole")
    public RespEntity<SysUserRole> get(@PathVariable Integer id){
        return RespEntity.ok().setResponseContent(sysUserRoleService.selectById(id));
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query")
    })
    @ApiOperation(value = "分页查询SysUserRole", notes = "分页查询SysUserRole")
    @GetMapping
    public RespEntity<PageInfo<SysUserRole>>list(PagingRequest pagingRequest){
        return RespEntity.ok().setResponseContent(sysUserRoleService.listPage(pagingRequest,null));
    }


    @PostMapping
    @ApiOperation(value = "保存SysUserRole", notes = "保存SysUserRole")
    public RespEntity insert(@RequestBody SysUserRole sysUserRole ){
        if ( ! sysUserRoleService.insert( sysUserRole) ){
        return RespEntity.badRequest("保存失败");
        }
        return RespEntity.ok("保存成功");
    }


    @PutMapping
    @ApiOperation(value = "根据ID修改SysUserRole", notes = "根据ID修改SysUserRole")
    public RespEntity update(@RequestBody SysUserRole sysUserRole ){
        if ( ! sysUserRoleService.updateById( sysUserRole ) ){
        return RespEntity.badRequest("更新失败");
        }
        return RespEntity.ok("更新成功");
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除SysUserRole", notes = "根据ID删除SysUserRole")
    public RespEntity delete(@PathVariable Integer id){
        if ( ! sysUserRoleService.deleteById(id) ){
        return RespEntity.badRequest("删除失败");
        }
        return RespEntity.ok("删除成功");
    }

    
    
}