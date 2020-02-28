package com.msz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.msz.VO.LeaseReturnParamVO;
import com.msz.annotation.LoginRequired;
import com.msz.common.SysLogUtil;
import com.msz.common.UserCommon;
import com.msz.model.MszAccount;
import com.msz.model.MszSchedule;
import com.msz.model.SysUser;
import com.msz.service.MszAccountService;
import com.msz.service.MszScheduleService;

import com.msz.common.PagingRequest;
import com.msz.common.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * <p>
 * 预付定金表 前端控制器
 * </p>
 *
 * @author Maoyy
 * @since 2019-10-08 ${time}
 */
@Api(value = "/msz-schedules", description = "预付定金表 接口; Responseble:Maoyy")
@RestController
@RequestMapping("/msz-schedules")
public class MszScheduleController {

    /**
     * 主键ID  id  Integer
     * <p>
     * 房屋ID  roomId  Integer
     * <p>
     * 开始时间  scheduleStartTime  Date
     * <p>
     * 结束时间  scheduleEndTime  Date
     * <p>
     * 预付定金  schedulePrice  BigDecimal
     * <p>
     * 0 不应用到每期收费  1 应用到每期收费  available  String
     * <p>
     * 是否删除  isDel  String
     * <p>
     * 创建时间  createTime  Date
     */
    @Autowired
    private MszScheduleService mszScheduleService;
    @Autowired
    private SysLogUtil sysLogUtil;

    @Autowired
    private MszAccountService mszAccountService;


    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取单个MszSchedule(待生效)-------PC@Author=Maoyy", notes = "根据id获取单个MszSchedule(待生效)-------PC@Author=Maoyy")
    public RespEntity<MszSchedule> get(@PathVariable Integer id) {
        // 房客电话 房客姓名 租约编号
        return RespEntity.ok().setResponseContent(mszScheduleService.getSchedule(id));
    }

    @GetMapping("byRoom/{roomId}")
    @ApiOperation(value = "根据房源Id获取预定信息-------PC@Author=Maoyy", notes = "根据房源Id获取预定信息-------PC@Author=Maoyy")
    public RespEntity<MszSchedule> byRoom(@PathVariable Integer roomId) {
        MszSchedule mszSchedule = mszScheduleService.selectOne(new EntityWrapper<MszSchedule>().eq("room_id", roomId).eq("is_del", "0"));
        MszAccount mszAccount = mszAccountService.selectById(mszSchedule.getTenantId());
        mszSchedule.setTenantName(mszAccount.getName());
        mszSchedule.setTenantTel(mszAccount.getTel());
        return RespEntity.ok().setResponseContent(mszSchedule);
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query")
    })
    @ApiOperation(value = "分页查询MszSchedule-------PC@Author=Maoyy", notes = "分页查询MszSchedule-------PC@Author=Maoyy")
    @GetMapping("/list")
    @LoginRequired
    public RespEntity<PageInfo<LeaseReturnParamVO>> list(PagingRequest pagingRequest, String tenantName) {
        return RespEntity.ok().setResponseContent(mszScheduleService.listPage(pagingRequest, tenantName));
    }


    @PostMapping("/insertSchedule")
    @ApiOperation(value = "保存MszSchedule(预定房源)-------PC@Author=Maoyy", notes = "保存MszSchedule(预定房源)-------PC@Author=Maoyy")
    public RespEntity insertSchedule(HttpServletRequest request, @RequestBody MszSchedule mszSchedule) {
        mszSchedule.setCreateTime(new Date());
        if (!mszScheduleService.insertSchedule(mszSchedule)) {
            return RespEntity.badRequest("保存失败");
        }
        return RespEntity.ok("保存成功");
    }


    @PutMapping("/updateSchedule")
    @ApiOperation(value = "根据ID修改MszSchedule-------PC@Author=Maoyy", notes = "根据ID修改MszSchedule-------PC@Author=Maoyy")
    @LoginRequired
    public RespEntity updateSchedule(HttpServletRequest request, @RequestBody MszSchedule mszSchedule) {
        SysUser sysUser = UserCommon.getCurrentUser();
        if (!mszScheduleService.updatSchedule(mszSchedule)) {
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(), "预付定金 ->更新->失败");
            return RespEntity.badRequest("更新失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(), "预付定金 ->更新->成功");
        return RespEntity.ok("更新成功");
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "根据ID删除MszSchedule-------PC@Author=Maoyy", notes = "根据ID删除MszSchedule-------PC@Author=Maoyy")
    @LoginRequired
    public RespEntity delete(HttpServletRequest request, @PathVariable Integer id) {
        SysUser sysUser = UserCommon.getCurrentUser();
        if (!mszScheduleService.deleteById(id)) {
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(), "预付定金 ->删除->失败");
            return RespEntity.badRequest("删除失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(), "预付定金 ->删除->成功");
        return RespEntity.ok("删除成功");
    }


}