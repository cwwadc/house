package com.msz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.msz.VO.MszRefundMessageVO;
import com.msz.annotation.LoginRequired;
import com.msz.common.SysLogUtil;
import com.msz.common.UserCommon;
import com.msz.model.*;
import com.msz.service.MszAccountService;
import com.msz.service.MszLeaseService;
import com.msz.service.MszRefundInfoService;
import com.msz.service.MszRefundMessageService;

import com.msz.common.PagingRequest;
import com.msz.common.RespEntity;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.util.Date;


/**
 * <p>
 * 押金退款详细 前端控制器
 * </p>
 *
 * @author Maoyy
 * @since 2019-10-18 ${time}
 */
@Api(value = "/msz-refund-messages", description = "押金退款详细 接口; Responseble:Maoyy")
@RestController
@RequestMapping("/msz-refund-messages")
public class MszRefundMessageController {

    /**
     * 主键Id  id  Long
     * <p>
     * 申请人  applyName  String
     * <p>
     * 房客Id  tenantId  Long
     * <p>
     * 房东Id  telId  Long
     * <p>
     * 申请时间  applyTime  Date
     * <p>
     * 租约时间  leaseTime  Date
     * <p>
     * 押金  depositPrice  BigDecimal
     * <p>
     * 备注  remarks  String
     * <p>
     * 状态： 0同意 1拒绝  status  Integer
     */
    @Autowired
    private MszRefundMessageService mszRefundMessageService;

    @Autowired
    private MszRefundInfoService mszRefundInfoService;

    @Autowired
    private MszAccountService mszAccountService;

    @Autowired
    private MszLeaseService mszLeaseService;
    @Autowired
    private SysLogUtil sysLogUtil;

    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取单个MszRefundMessage-------PC@Author=Maoyy", notes = "根据id获取单个MszRefundMessage-------PC@Author=Maoyy")
    public RespEntity<MszRefundMessageVO> get(@PathVariable Long id) {
        MszRefundMessage message = mszRefundMessageService.selectById(id);
        MszRefundMessageVO vo = new MszRefundMessageVO();
        BeanUtils.copyProperties(message, vo);
        MszAccount account = mszAccountService.selectById(vo.getTenantId());
        //房客姓名
        vo.setTenantName(account.getName());
        vo.setOpenId(account.getOpenId());
        vo.setOpenId(account.getTel());
        //房东姓名
        vo.setTelName(mszAccountService.selectById(vo.getTelId()).getName());
        //租约No
        vo.setLeaseNo(mszLeaseService.selectById(vo.getLeaseId()).getNo());
        return RespEntity.ok().setResponseContent(vo);
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query")
    })
    @ApiOperation(value = "分页查询MszRefundMessage-------PC@Author=Maoyy", notes = "分页查询MszRefundMessage-------PC@Author=Maoyy")
    @GetMapping
    public RespEntity<PageInfo<MszRefundMessage>> list(PagingRequest pagingRequest) {
        return RespEntity.ok().setResponseContent(mszRefundMessageService.listPage(pagingRequest, null));
    }

    @ApiOperation(value = "不分页查询房东MszRefundInfo-------小程序@Author=Maoyy", notes = "不分页查询房东MszRefundInfo-------小程序@Author=Maoyy")
    @GetMapping("listMszRefundMessage")
    public RespEntity<PageInfo<MszRefundMessage>> listMszRefundMessage(Integer telId) {
        return RespEntity.ok().setResponseContent(mszRefundMessageService.selectList(new EntityWrapper<MszRefundMessage>().eq("telId", telId).orderBy("applyTime", false)));
    }

    @PostMapping
    @ApiOperation(value = "保存MszRefundMessage-------PC@Author=Maoyy", notes = "保存MszRefundMessage-------PC@Author=Maoyy")
    public RespEntity insert(HttpServletRequest request, @RequestBody MszRefundMessage mszRefundMessage) {
        MszLease lease = new MszLease();
        lease.setId(mszRefundMessage.getLeaseId());
        lease.setIsRefundMoney("2");
        try {
            if (!mszLeaseService.updateById(lease)) {
                return RespEntity.badRequest("保存失败");
            }
            if (!mszRefundMessageService.insert(mszRefundMessage)) {
                return RespEntity.badRequest("保存失败");
            }
        } catch (Exception e) {
            return RespEntity.badRequest("错误的请求");
        }
        return RespEntity.ok("保存成功").setResponseContent(mszRefundMessage);
    }


    @PutMapping("agree")
    @ApiOperation(value = "同意退款-------小程序@Author=Maoyy", notes = "同意退款-------小程序@Author=Maoyy")
    @Transactional
    public RespEntity agree(HttpServletRequest request, @RequestBody MszRefundMessage mszRefundMessage) {
        //插入退款数据
        MszRefundInfo info = new MszRefundInfo();
        info.setLeaseId(mszRefundMessage.getLeaseId());
        info.setTenantId(mszRefundMessage.getTenantId());
        info.setLandlordId(mszRefundMessage.getTelId());
        info.setDepositPrice(mszRefundMessage.getDepositPrice());
        info.setTotal(mszRefundMessage.getDepositPrice());
        info.setCreateTime(new Date());
        info.setApplyTime(mszRefundMessage.getApplyTime());
        if (!mszRefundInfoService.insert(info)) {
            return RespEntity.badRequest("更新失败");
        }
        //同意
        mszRefundMessage.setStatus(0);
        if (!mszRefundMessageService.updateById(mszRefundMessage)) {
            return RespEntity.badRequest("更新失败");
        }
        return RespEntity.ok("更新成功");
    }

    @PutMapping
    @ApiOperation(value = "根据ID修改MszRefundMessage-------PC@Author=Maoyy", notes = "根据ID修改MszRefundMessage-------PC@Author=Maoyy")
    public RespEntity update(HttpServletRequest request, @RequestBody MszRefundMessage mszRefundMessage) {
        if (!mszRefundMessageService.updateById(mszRefundMessage)) {
            return RespEntity.badRequest("更新失败");
        }
        return RespEntity.ok("更新成功");
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除MszRefundMessage-------PC@Author=Maoyy", notes = "根据ID删除MszRefundMessage-------PC@Author=Maoyy")
    @LoginRequired
    public RespEntity delete(HttpServletRequest request, @PathVariable Long id) {
        SysUser sysUser = UserCommon.getCurrentUser();
        if (!mszRefundMessageService.deleteById(id)) {
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"押金退款详细->删除->失败");
            return RespEntity.badRequest("删除失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"押金退款详细->删除->成功");
        return RespEntity.ok("删除成功");
    }


}