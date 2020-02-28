package com.msz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageInfo;
import com.msz.annotation.LoginRequired;
import com.msz.common.PagingRequest;
import com.msz.common.RespEntity;
import com.msz.common.SysLogUtil;
import com.msz.common.UserCommon;
import com.msz.model.SysAuthorities;
import com.msz.model.SysRole;
import com.msz.model.SysUser;
import com.msz.service.SysRoleService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Api(value = "/sys-roles", description = "角色 接口; Responseble:cww")
@RestController
@RequestMapping("/sys-roles")
public class SysRoleController {

    /**
     * id  Integer
     * <p>
     * 角色名称  roleName  String
     * <p>
     * 备注  comments  String
     * <p>
     * 创建时间  createTime  Date
     * <p>
     * 修改时间  updateTime  Date
     */
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysLogUtil sysLogUtil;


    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取单个SysRole", notes = "根据id获取单个SysRole")
    public RespEntity<SysRole> get(@PathVariable Integer id) {
        return RespEntity.ok().setResponseContent(sysRoleService.selectById(id));
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query")
    })
    @ApiOperation(value = "分页查询SysRole", notes = "分页查询SysRole")
    @GetMapping("/list")
    public RespEntity<PageInfo<SysRole>> list(PagingRequest pagingRequest) {
        return RespEntity.ok().setResponseContent(sysRoleService.listPage(pagingRequest));
    }


    @PostMapping("/insert")
    @ApiOperation(value = "保存SysRole", notes = "保存SysRole")
    @LoginRequired
    public RespEntity insert(HttpServletRequest request,  @RequestBody SysRole sysRole) {
        SysUser sysUser = UserCommon.getCurrentUser();
        sysRole.setCreateTime(new Date());
        if (!sysRoleService.insert(sysRole)) {
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"角色 ->启用->失败");
            return RespEntity.badRequest("保存失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"角色 ->启用->成功");
        return RespEntity.ok("保存成功");
    }


    @PutMapping("/update")
    @ApiOperation(value = "根据ID修改SysRole", notes = "根据ID修改SysRole")
    @LoginRequired
    public RespEntity update(HttpServletRequest request, @RequestBody SysRole sysRole) {
        SysUser sysUser = UserCommon.getCurrentUser();
        if (!sysRoleService.updateById(sysRole)) {
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"角色 ->更新->失败");
            return RespEntity.badRequest("更新失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"角色 ->更新->成功");
        return RespEntity.ok("更新成功");
    }

    @PutMapping("/updateStart/{id}")
    @ApiOperation(value = "启用", notes = "启用")
    @LoginRequired
    public RespEntity updateStart(HttpServletRequest request, @PathVariable Integer id) {
        SysUser sysUser = UserCommon.getCurrentUser();
        EntityWrapper<SysRole> wrapper = new EntityWrapper<>();
        wrapper.eq("id", id);
        SysRole sysRole = new SysRole();
        sysRole.setIsDel("0");
        if (!sysRoleService.update(sysRole, wrapper)) {
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"角色 ->启用->失败");
            return RespEntity.badRequest("启用失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"角色 ->启用->成功");
        return RespEntity.ok("启用成功");
    }

    @PutMapping("/updateForbidden/{id}")
    @ApiOperation(value = "禁用", notes = "禁用")
    @LoginRequired
    public RespEntity updateForbidden(HttpServletRequest request, @PathVariable Integer id) {
        SysUser sysUser = UserCommon.getCurrentUser();
        EntityWrapper<SysRole> wrapper = new EntityWrapper<>();
        wrapper.eq("id", id);
        SysRole sysRole = new SysRole();
        sysRole.setIsDel("1");
        if (!sysRoleService.update(sysRole, wrapper)) {
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"角色 ->禁用->失败");
            return RespEntity.badRequest("禁用失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"角色 ->禁用->成功");
        return RespEntity.ok("禁用成功");
    }

    @PutMapping("/authorize")
    @ApiOperation(value = "授权", notes = "授权")
    @LoginRequired
    public RespEntity authorize(HttpServletRequest request, @RequestParam Integer roleId, @RequestParam String menus) {
        SysUser sysUser = UserCommon.getCurrentUser();
        if (!sysRoleService.authorize(roleId, menus)) {
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"角色 ->授权->失败");
            return RespEntity.badRequest("授权失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"角色 ->授权->成功");
        return RespEntity.ok("授权成功");
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除SysRole", notes = "根据ID删除SysRole")
    @LoginRequired
    public RespEntity delete(HttpServletRequest request, @PathVariable Integer id) {
        SysUser sysUser = UserCommon.getCurrentUser();
        if (!sysRoleService.deleteById(id)) {
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"角色 ->删除->失败");
            return RespEntity.badRequest("删除失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"角色 ->删除->成功");
        return RespEntity.ok("删除成功");
    }

}