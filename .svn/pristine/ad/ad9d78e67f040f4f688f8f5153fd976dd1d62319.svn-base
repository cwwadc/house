package com.msz.controller;

import com.github.pagehelper.PageInfo;
import com.msz.common.PagingRequest;
import com.msz.common.RespEntity;
import com.msz.model.MszRoomImg;
import com.msz.service.MszRoomImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;


/**
 * <p>
 * 房间图片 前端控制器
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Api(value = "/msz-room-imgs", description = "房间图片 接口; Responseble:cww")
@RestController
@RequestMapping("/msz-room-imgs")
public class MszRoomImgController {

    /**
    
     *    主键id  id  Integer 
    
     *    配置名称  name  String 
    
     *    配置图片链接  pictureUrl  String 
    
     *    配置类型(1：室内图，2：室外图，3：户型图）  type  Integer 
    
     *    roomId  Integer 
         */
    @Autowired
    private MszRoomImgService mszRoomImgService;


    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取单个MszRoomImg", notes = "根据id获取单个MszRoomImg")
    public RespEntity<MszRoomImg> get(@PathVariable Integer id){
        return RespEntity.ok().setResponseContent(mszRoomImgService.selectById(id));
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query")
    })
    @ApiOperation(value = "分页查询MszRoomImg", notes = "分页查询MszRoomImg")
    @GetMapping
    public RespEntity<PageInfo<MszRoomImg>>list(PagingRequest pagingRequest){
        return RespEntity.ok().setResponseContent(mszRoomImgService.listPage(pagingRequest,null));
    }


    @PostMapping
    @ApiOperation(value = "保存MszRoomImg", notes = "保存MszRoomImg")
    public RespEntity insert(@RequestBody MszRoomImg mszRoomImg ){
        if ( ! mszRoomImgService.insert( mszRoomImg) ){
        return RespEntity.badRequest("保存失败");
        }
        return RespEntity.ok("保存成功");
    }


    @PutMapping
    @ApiOperation(value = "根据ID修改MszRoomImg", notes = "根据ID修改MszRoomImg")
    public RespEntity update(@RequestBody MszRoomImg mszRoomImg ){
        if ( ! mszRoomImgService.updateById( mszRoomImg ) ){
        return RespEntity.badRequest("更新失败");
        }
        return RespEntity.ok("更新成功");
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除MszRoomImg", notes = "根据ID删除MszRoomImg")
    public RespEntity delete(@PathVariable Integer id){
        if ( ! mszRoomImgService.deleteById(id) ){
        return RespEntity.badRequest("删除失败");
        }
        return RespEntity.ok("删除成功");
    }

    
    
}