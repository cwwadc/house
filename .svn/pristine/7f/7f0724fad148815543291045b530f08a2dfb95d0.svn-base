package com.msz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageInfo;
import com.msz.VO.SysLogVO;
import com.msz.common.PagingRequest;
import com.msz.common.RespEntity;
import com.msz.model.SysLog;
import com.msz.service.SysLogService;
import com.msz.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;


/**
 * <p>
 * 操作日志 前端控制器
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Api(value = "/sys-logs", description = "操作日志 接口; Responseble:cww")
@RestController
@RequestMapping("/sys-logs")
public class SysLogController {

    /**
    
     *    id  Integer 
    
     *    操作用户  userId  Integer 
    
     *    日志类型  type  Integer 
    
     *    操作IP  ip  String 
    
     *    操作详情  info  String 
    
     *    操作时间  createTime  Date 
         */
    @Autowired
    private SysLogService sysLogService;


    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取单个SysLog", notes = "根据id获取单个SysLog")
    public RespEntity<SysLog> get(@PathVariable Integer id){
        return RespEntity.ok().setResponseContent(sysLogService.selectById(id));
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query")
    })
    @ApiOperation(value = "分页查询SysLog", notes = "分页查询SysLog")
    @GetMapping
    public RespEntity<PageInfo<SysLogVO>>list(PagingRequest pagingRequest,String createTimeMin,String createTimeMax){

        return RespEntity.ok().setResponseContent(sysLogService.listPage(pagingRequest,createTimeMin, createTimeMax));
    }


    @PostMapping
    @ApiOperation(value = "保存SysLog", notes = "保存SysLog")
    public RespEntity insert(@RequestBody SysLog sysLog ){
        if ( ! sysLogService.insert( sysLog) ){
        return RespEntity.badRequest("保存失败");
        }
        return RespEntity.ok("保存成功");
    }


    @PutMapping
    @ApiOperation(value = "根据ID修改SysLog", notes = "根据ID修改SysLog")
    public RespEntity update(@RequestBody SysLog sysLog ){
        if ( ! sysLogService.updateById( sysLog ) ){
        return RespEntity.badRequest("更新失败");
        }
        return RespEntity.ok("更新成功");
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除SysLog", notes = "根据ID删除SysLog")
    public RespEntity delete(@PathVariable Integer id){
        if ( ! sysLogService.deleteById(id) ){
        return RespEntity.badRequest("删除失败");
        }
        return RespEntity.ok("删除成功");
    }

    
    
}