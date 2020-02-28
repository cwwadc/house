package com.msz.controller;

import com.github.pagehelper.PageInfo;
import com.msz.annotation.LoginRequired;
import com.msz.common.PagingRequest;
import com.msz.common.RespEntity;
import com.msz.common.SysLogUtil;
import com.msz.common.UserCommon;
import com.msz.model.SysAuthorities;
import com.msz.model.SysUser;
import com.msz.service.SysAuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import javax.servlet.http.HttpServletRequest;


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
    @Autowired
    private SysLogUtil sysLogUtil;


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
    @LoginRequired
    public RespEntity insert(HttpServletRequest request, @RequestBody SysAuthorities sysAuthorities ){
        SysUser sysUser = UserCommon.getCurrentUser();
        if ( ! sysAuthoritiesService.insert( sysAuthorities) ){
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"权限 ->新增->失败");
            return RespEntity.badRequest("保存失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"权限 ->新增->成功");
        return RespEntity.ok("保存成功");
    }


    @PutMapping
    @ApiOperation(value = "根据ID修改SysAuthorities", notes = "根据ID修改SysAuthorities")
    @LoginRequired
    public RespEntity update(HttpServletRequest request, @RequestBody SysAuthorities sysAuthorities ){
        SysUser sysUser = UserCommon.getCurrentUser();
        if ( ! sysAuthoritiesService.updateById( sysAuthorities ) ){
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"权限 ->更新->失败");
            return RespEntity.badRequest("更新失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"权限 ->更新->成功");
        return RespEntity.ok("更新成功");
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除SysAuthorities", notes = "根据ID删除SysAuthorities")
    @LoginRequired
    public RespEntity delete(HttpServletRequest request, @PathVariable Integer id){
        SysUser sysUser = UserCommon.getCurrentUser();
        if ( ! sysAuthoritiesService.deleteById(id) ){
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"权限 ->删除->失败");
            return RespEntity.badRequest("删除失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"权限 ->删除->成功");
        return RespEntity.ok("删除成功");
    }

    
    
}