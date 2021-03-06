package com.msz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageInfo;
import com.msz.annotation.LoginRequired;
import com.msz.common.PagingRequest;
import com.msz.common.RespEntity;
import com.msz.common.SysLogUtil;
import com.msz.common.UserCommon;
import com.msz.model.MszAccount;
import com.msz.model.MszWalletBill;
import com.msz.model.SysUser;
import com.msz.service.MszAccountService;
import com.msz.service.MszWalletBillService;
import com.msz.util.UUIDUtils;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * <p>
 * 钱包流水 前端控制器
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Api(value = "/msz-wallet-bills", description = "钱包流水 接口; Responseble:cww")
@RestController
@RequestMapping("/msz-wallet-bills")
public class MszWalletBillController {

    /**
     * id  Integer
     * <p>
     * 账号id  accountId  Integer
     * <p>
     * 金额  amt  BigDecimal
     * <p>
     * 账单类型：1充值，2提现，3月租，4退款  type  String
     * <p>
     * createTime  Date
     * <p>
     * 关联id  receiverId  String
     * <p>
     * 关联类型  receiverType  String
     * <p>
     * 流水号  no  String
     */
    @Autowired
    private MszWalletBillService mszWalletBillService;
    @Autowired
    private SysLogUtil sysLogUtil;
    @Autowired
    private MszAccountService mszAccountService;


    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取单个MszWalletBill", notes = "根据id获取单个MszWalletBill")
    public RespEntity<MszWalletBill> get(@PathVariable Integer id) {
        MszWalletBill mszWalletBill = mszWalletBillService.selectById(id);
        MszAccount mszAccount = mszAccountService.selectById(mszWalletBill.getAccountId());
        if(mszAccount != null){
            mszWalletBill.setAccountName(mszAccount.getName());
        }else{
            mszWalletBill.setAccountName("未知人员");
        }
        return RespEntity.ok().setResponseContent(mszWalletBill);
    }

    @GetMapping("byAccountId/{id}")
    @ApiOperation(value = "查询交易记录*******小程序已使用(租客)@Author=Maoyy", notes = "查询交易记录*******小程序已使用(租客)@Author=Maoyy")
    public RespEntity<MszWalletBill> byAccountId(@PathVariable Integer id) {
        return RespEntity.ok().setResponseContent(mszWalletBillService.selectList(new EntityWrapper<MszWalletBill>().eq("accountId", id).orderBy("createTime", false)));
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query")
    })
    @ApiOperation(value = "分页查询MszWalletBill", notes = "分页查询MszWalletBill")
    @GetMapping
    public RespEntity<PageInfo<MszWalletBill>> list(PagingRequest pagingRequest) {
        return RespEntity.ok().setResponseContent(mszWalletBillService.listPage(pagingRequest, null));
    }


    @PostMapping("/insert")
    @ApiOperation(value = "保存MszWalletBill", notes = "保存MszWalletBill")
    public RespEntity insert(HttpServletRequest request,  @RequestBody MszWalletBill mszWalletBill) {
        mszWalletBill.setCreateTime(new Date());
        mszWalletBill.setNo(UUIDUtils.getOrderIdByTime());
        if (!mszWalletBillService.insert(mszWalletBill)) {
            return RespEntity.badRequest("保存失败");
        }
        return RespEntity.ok("保存成功");
    }

    @PutMapping
    @ApiOperation(value = "根据ID修改MszWalletBill", notes = "根据ID修改MszWalletBill")
    @LoginRequired
    public RespEntity update(HttpServletRequest request, @RequestBody MszWalletBill mszWalletBill) {
        SysUser sysUser = UserCommon.getCurrentUser();
        if (!mszWalletBillService.updateById(mszWalletBill)) {
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"钱包流水 ->更新->失败");
            return RespEntity.badRequest("更新失败");
    }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"钱包流水 ->更新->成功");
        return RespEntity.ok("更新成功");
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除MszWalletBill", notes = "根据ID删除MszWalletBill")
    @LoginRequired
    public RespEntity delete(HttpServletRequest request, @PathVariable Integer id) {
        SysUser sysUser = UserCommon.getCurrentUser();
        if (!mszWalletBillService.deleteById(id)) {
            sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"钱包流水 ->删除->失败");
            return RespEntity.badRequest("删除失败");
        }
        sysLogUtil.insertSysLog(request, sysUser.getId(), sysUser.getUsername(),"钱包流水 ->删除->成功");
        return RespEntity.ok("删除成功");
    }


}