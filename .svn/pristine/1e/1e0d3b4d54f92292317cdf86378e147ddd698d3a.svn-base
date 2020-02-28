package com.msz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageInfo;
import com.msz.common.PagingRequest;
import com.msz.common.RespEntity;
import com.msz.model.SysAuthorities;
import com.msz.model.SysDict;
import com.msz.model.SysMenu;
import com.msz.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;


/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Api(value = "/sys-menus", description = "菜单表 接口; Responseble:cww")
@RestController
@RequestMapping("/sys-menus")
public class SysMenuController {

    /**
    
     *    菜单id  id  Integer 
    
     *    父级id  parentId  Integer 
    
     *    菜单名称  menuName  String 
    
     *    菜单url  menuUrl  String 
    
     *    菜单图标  menuIcon  String 
    
     *    排序号  sort  Integer 
    
     *    对应权限  authority  String 
    
     *    创建时间  createTime  Date 
    
     *    修改时间  updateTime  Date 
         */
    @Autowired
    private SysMenuService sysMenuService;


    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取单个SysMenu", notes = "根据id获取单个SysMenu")
    public RespEntity<SysMenu> get(@PathVariable Integer id){
        return RespEntity.ok().setResponseContent(sysMenuService.selectById(id));
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query")
    })
    @ApiOperation(value = "分页查询SysMenu", notes = "分页查询SysMenu")
    @GetMapping
    public RespEntity<PageInfo<SysMenu>>list(PagingRequest pagingRequest){
        return RespEntity.ok().setResponseContent(sysMenuService.listPage(pagingRequest,null));
    }

    @ApiOperation(value = "根据角色id查询菜单列表", notes = "根据角色id查询菜单列表")
    @GetMapping("/getMenuListByRoleId")
    public RespEntity<PageInfo<SysMenu>> getMenuListByRoleId(@RequestParam Integer roleId){

        return RespEntity.ok().setResponseContent(sysMenuService.getMenuListByRoleId(roleId));
    }


    @ApiOperation(value = "查询父级菜单", notes = "查询父级菜单")
    @GetMapping("/getParentMenuList")
    public RespEntity<PageInfo<SysMenu>> getParentMenuList(){

        EntityWrapper<SysMenu> wrapper = new EntityWrapper<>();
        wrapper.eq("parentId",0);
        return RespEntity.ok().setResponseContent(sysMenuService.selectList(wrapper));
    }

    @ApiOperation(value = "根据parentId查询子级菜单", notes = "根据parentId查询子级菜单")
    @GetMapping("/getSubMenuList")
    public RespEntity<PageInfo<SysMenu>> getSubMenuList(Integer parentId){

        EntityWrapper<SysMenu> wrapper = new EntityWrapper<>();
        wrapper.eq("parentId",parentId);
        return RespEntity.ok().setResponseContent(sysMenuService.selectList(wrapper));
    }

    @ApiOperation(value = "根据菜单id查询所拥有的权限", notes = "根据菜单id查询所拥有的权限")
    @GetMapping("/getAuthoritiesByMenuId")
    public RespEntity<PageInfo<SysAuthorities>> getAuthoritiesByMenuId(Integer menuId){

        return RespEntity.ok().setResponseContent(sysMenuService.getAuthoritiesByMenuId(menuId));
    }


    @PostMapping
    @ApiOperation(value = "保存SysMenu", notes = "保存SysMenu")
    public RespEntity insert(@RequestBody SysMenu sysMenu ){
        if ( ! sysMenuService.insert( sysMenu) ){
        return RespEntity.badRequest("保存失败");
        }
        return RespEntity.ok("保存成功");
    }


    @PutMapping
    @ApiOperation(value = "根据ID修改SysMenu", notes = "根据ID修改SysMenu")
    public RespEntity update(@RequestBody SysMenu sysMenu ){
        if ( ! sysMenuService.updateById( sysMenu ) ){
        return RespEntity.badRequest("更新失败");
        }
        return RespEntity.ok("更新成功");
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除SysMenu", notes = "根据ID删除SysMenu")
    public RespEntity delete(@PathVariable Integer id){
        if ( ! sysMenuService.deleteById(id) ){
        return RespEntity.badRequest("删除失败");
        }
        return RespEntity.ok("删除成功");
    }


}