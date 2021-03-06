package com.msz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageInfo;
import com.msz.annotation.LoginRequired;
import com.msz.common.PagingRequest;
import com.msz.common.RespEntity;
import com.msz.common.SysLogUtil;
import com.msz.common.UserCommon;
import com.msz.model.SysUser;
import com.msz.model.SysUserRole;
import com.msz.service.SysOrgService;
import com.msz.service.SysRoleService;
import com.msz.service.SysUserRoleService;
import com.msz.service.SysUserService;
import com.msz.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Api(value = "/sys-users", description = "用户 接口; Responseble:cww")
@RestController
@RequestMapping("/sys-users")
public class SysUserController {

    /**
     * 用户id  id  Integer
     * <p>
     * 账号  username  String
     * <p>
     * 密码  password  String
     * <p>
     * 性别  sex  String
     * <p>
     * 手机号  phone  String
     * <p>
     * 真实姓名  trueName  String
     * <p>
     * 身份证号  idCard  String
     * <p>
     * 出生日期  address  String
     * <p>
     * 部门id  orgId  Integer
     * <p>
     * 状态，0正常，1冻结  state  Integer
     * <p>
     * 账号类型：1管理员，2普通员工  type  String
     * <p>
     * 入职时间  hireDate  Date
     * <p>
     * 身份证照片正面  imgIdcard1  String
     * <p>
     * 身份证照片正面  imgIdcard2  String
     * <p>
     * 创建时间  createTime  Date
     * <p>
     * 修改时间  updateTime  Date
     */
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService userRoleService;
    @Autowired
    SysRoleService roleService;
    @Autowired
    SysOrgService orgService;
    @Autowired
    private SysLogUtil sysLogUtil;


    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取单个SysUser", notes = "根据id获取单个SysUser")
    public RespEntity<SysUser> get(@PathVariable Integer id) {
        return RespEntity.ok().setResponseContent(sysUserService.selectById(id));
    }


    @ApiOperation(value = "网点负责人列表", notes = "网点负责人列表")
    @GetMapping("/getPrincipalList")
    public RespEntity<PageInfo<SysUser>> getPrincipalList() {
        return RespEntity.ok().setResponseContent(sysUserService.getPrincipalList());
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query")
    })
    @ApiOperation(value = "管理人设置列表", notes = "管理人设置列表")
    @GetMapping("/adminList")
    public RespEntity<PageInfo<SysUser>> list(PagingRequest pagingRequest) {
        return RespEntity.ok().setResponseContent(sysUserService.getAdminList(pagingRequest));
    }

    @ApiOperation(value = "模糊查询业务员", notes = "模糊查询业务员")
    @GetMapping("/getSalesmanList")
    public RespEntity<PageInfo<SysUser>> getSalesmanList(String name) {
        return RespEntity.ok().setResponseContent(sysUserService.getSalesmanList(name));
    }


    @PostMapping("/insert")
    @ApiOperation(value = "保存SysUser", notes = "保存SysUser")
    @LoginRequired
    public RespEntity insert(HttpServletRequest request, @RequestBody SysUser sysUser) throws Exception {
        SysUser user = sysUserService.selectOne(new EntityWrapper<SysUser>().eq("username", sysUser.getUsername()));
        if (user == null) {
            String md5Password = MD5Utils.MD5Encode(sysUser.getUsername(), sysUser.getPassword());
            sysUser.setPassword(md5Password);
            if (sysUserService.insert(sysUser)) {
                SysUserRole userRole = new SysUserRole();
                userRole.setRoleId(sysUser.getRoleId());
                userRole.setUserId(sysUser.getId());
                userRoleService.insert(userRole);
       /*         String roleName = roleService.selectById(sysUser.getRoleId()).getRoleName();
                if (roleName.equals("网点负责人")){
                    SysOrg sysorg = new SysOrg();
                    sysorg.setStafId(sysUser.getId());
                    sysorg.setId(sysUser.getOrgId());
                    orgService.updateById(sysorg);
                }*/
                sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"用户 ->新增->成功");
                return RespEntity.ok("保存成功");
            }
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"用户 ->新增->失败");
            return RespEntity.badRequest("保存失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"用户 ->新增->不能重复注册");
        return RespEntity.badRequest("您已经有该账号了，不能重复注册");
    }


    @PutMapping("/update")
    @ApiOperation(value = "根据ID修改SysUser", notes = "根据ID修改SysUser")
    @LoginRequired
    public RespEntity update(HttpServletRequest request, @RequestBody SysUser sysUser) throws  Exception{
        SysUser sysUser1 = UserCommon.getCurrentUser();
        if (!sysUserService.updateById(sysUser)) {
            SysUserRole userRole = userRoleService.selectOne(new EntityWrapper<SysUserRole>().eq("userId", sysUser.getId()));
            userRole.setRoleId(sysUser.getRoleId());
            userRoleService.updateById(userRole);
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"用户 ->修改->失败");
            return RespEntity.badRequest("更新失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"用户 ->修改->成功");
        return RespEntity.ok("更新成功");
    }

    @PutMapping("/updateState/{id}")
    @ApiOperation(value = "禁用", notes = "禁用")
    @LoginRequired
    public RespEntity updateState(HttpServletRequest request, @PathVariable Integer id) {
        SysUser crrUser = UserCommon.getCurrentUser();
        SysUser sysUser = new SysUser();
        sysUser.setState("1");
        EntityWrapper<SysUser> wrapper = new EntityWrapper<>();
        wrapper.eq("id", id);
        if (!sysUserService.update(sysUser, wrapper)) {
            sysLogUtil.insertSysLog(request, crrUser.getId(), crrUser.getUsername(),"用户 ->禁用->失败");
            return RespEntity.badRequest("禁用失败");
        }
        sysLogUtil.insertSysLog(request, crrUser.getId(), crrUser.getUsername(),"用户 ->禁用->成功");
        return RespEntity.ok("禁用成功");
    }

    @PutMapping("/updateEnable/{id}")
    @ApiOperation(value = "启用", notes = "启用")
    @LoginRequired
    public RespEntity updateEnable(HttpServletRequest request, @PathVariable Integer id) {
        SysUser crrUser = UserCommon.getCurrentUser();
        SysUser sysUser = new SysUser();
        sysUser.setState("0");
        EntityWrapper<SysUser> wrapper = new EntityWrapper<>();
        wrapper.eq("id", id);
        if (!sysUserService.update(sysUser, wrapper)) {
            sysLogUtil.insertSysLog(request, crrUser.getId(), crrUser.getUsername(),"用户 ->启用->失败");
            return RespEntity.badRequest("启用失败");
        }
        sysLogUtil.insertSysLog(request, crrUser.getId(), crrUser.getUsername(),"用户 ->启用->成功");
        return RespEntity.ok("启用成功");
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除SysUser", notes = "根据ID删除SysUser")
    @LoginRequired
    public RespEntity delete(HttpServletRequest request, @PathVariable Integer id) {
        SysUser sysUser = UserCommon.getCurrentUser();
        if (!sysUserService.deleteById(id)) {
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"用户 ->删除->失败");
            return RespEntity.badRequest("删除失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"用户 ->删除->成功");
        return RespEntity.ok("删除成功");
    }


}