package com.msz.controller;

import com.github.pagehelper.PageInfo;
import com.msz.annotation.LoginRequired;
import com.msz.common.PagingRequest;
import com.msz.common.RespEntity;
import com.msz.common.SysLogUtil;
import com.msz.common.UserCommon;
import com.msz.model.MszToken;
import com.msz.model.SysUser;
import com.msz.service.MszTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 * 用户token表 前端控制器
 * </p>
 *
 * @author Maoyy
 * @since 2019-07-17 ${time}
 */
@Api(value = "/msz-tokens", description = "用户token表 接口; Responseble:Maoyy")
@RestController
@RequestMapping("/msz-tokens")
public class MszTokenController {

    /**
    
     *    id  Long 
    
     *    用户id  userId  Integer 
    
     *    登录返回的token  token  String 
    
     *    expireTime  Date 
    
     *    创建时间  createTime  Date 
         */
    @Autowired
    private MszTokenService mszTokenService;
    @Autowired
    private SysLogUtil sysLogUtil;


    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取单个MszToken", notes = "根据id获取单个MszToken")
    public RespEntity<MszToken> get(@PathVariable Long id){
        return RespEntity.ok().setResponseContent(mszTokenService.selectById(id));
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query")
    })
    @ApiOperation(value = "分页查询MszToken", notes = "分页查询MszToken")
    @GetMapping
    public RespEntity<PageInfo<MszToken>>list(PagingRequest pagingRequest){
        return RespEntity.ok().setResponseContent(mszTokenService.listPage(pagingRequest,null));
    }


    @PostMapping
    @ApiOperation(value = "保存MszToken", notes = "保存MszToken")
    @LoginRequired
    public RespEntity insert(HttpServletRequest request, @RequestBody MszToken mszToken ){
        SysUser sysUser = UserCommon.getCurrentUser();
        if ( ! mszTokenService.insert( mszToken) ){
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"用户token ->新增->失败");
            return RespEntity.badRequest("保存失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"用户token ->新增->成功");
        return RespEntity.ok("保存成功");
    }


    @PutMapping
    @ApiOperation(value = "根据ID修改MszToken", notes = "根据ID修改MszToken")
    @LoginRequired
    public RespEntity update(HttpServletRequest request, @RequestBody MszToken mszToken ){
        SysUser sysUser = UserCommon.getCurrentUser();
        if ( ! mszTokenService.updateById( mszToken ) ){
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"用户token ->更新->失败");
            return RespEntity.badRequest("更新失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"用户token ->更新->成功");
        return RespEntity.ok("更新成功");
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除MszToken", notes = "根据ID删除MszToken")
    @LoginRequired
    public RespEntity delete(HttpServletRequest request, @PathVariable Long id){
        SysUser sysUser = UserCommon.getCurrentUser();
        if ( ! mszTokenService.deleteById(id) ){
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"用户token ->删除->成功");
            return RespEntity.badRequest("删除失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"用户token ->删除->成功");
        return RespEntity.ok("删除成功");
    }

    
    
}