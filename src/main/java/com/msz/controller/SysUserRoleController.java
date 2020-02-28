package com.msz.controller;

import com.msz.annotation.LoginRequired;
import com.msz.common.PagingRequest;
import com.msz.common.RespEntity;
import com.msz.common.SysLogUtil;
import com.msz.common.UserCommon;
import com.msz.model.SysUser;
import com.msz.model.SysUserRole;
import com.msz.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import javax.servlet.http.HttpServletRequest;


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
    @Autowired
    private SysLogUtil sysLogUtil;


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
    @LoginRequired
    public RespEntity insert(HttpServletRequest request,  @RequestBody SysUserRole sysUserRole ){
        SysUser sysUser = UserCommon.getCurrentUser();
        if ( ! sysUserRoleService.insert( sysUserRole) ){
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"用户角色 ->新增->失败");
            return RespEntity.badRequest("保存失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"用户角色 ->新增->成功");
        return RespEntity.ok("保存成功");
    }


    @PutMapping
    @ApiOperation(value = "根据ID修改SysUserRole", notes = "根据ID修改SysUserRole")
    @LoginRequired
    public RespEntity update(HttpServletRequest request, @RequestBody SysUserRole sysUserRole ){
        SysUser sysUser = UserCommon.getCurrentUser();
        if ( ! sysUserRoleService.updateById( sysUserRole ) ){
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"用户角色 ->更新->失败");
            return RespEntity.badRequest("更新失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"用户角色 ->更新->成功");
        return RespEntity.ok("更新成功");
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除SysUserRole", notes = "根据ID删除SysUserRole")
    @LoginRequired
    public RespEntity delete(HttpServletRequest request, @PathVariable Integer id){
        SysUser sysUser = UserCommon.getCurrentUser();
        if ( ! sysUserRoleService.deleteById(id) ){
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"用户角色 ->删除->失败");
            return RespEntity.badRequest("删除失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"用户角色 ->删除->成功");
        return RespEntity.ok("删除成功");
    }

    
    
}