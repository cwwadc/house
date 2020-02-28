package com.msz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageInfo;
import com.msz.common.PagingRequest;
import com.msz.common.RespEntity;
import com.msz.model.MszWallet;
import com.msz.service.MszWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.math.BigDecimal;


/**
 * <p>
 * 用户钱包 前端控制器
 * </p>
 *
 * @author cww
 * @since 2019-06-03 ${time}
 */
@Api(value = "/msz-wallets", description = "用户钱包 接口; Responseble:cww")
@RestController
@RequestMapping("/msz-wallets")
public class MszWalletController {

    /**
     * id  Integer
     * <p>
     * 账号id  accountId  Integer
     * <p>
     * 支付密码（约定为6位纯数字）  pwd  String
     * <p>
     * 余额（分）  balance  BigDecimal
     * <p>
     * 冻结资金（分）  frozen  BigDecimal
     */
    @Autowired
    private MszWalletService mszWalletService;

    @GetMapping("{id}")
    @ApiOperation(value = "查询此人账户钱包-------小程序(房东)@Author=Maoyy", notes = "查询此人账户钱包-------小程序(房东)@Author=Maoyy")
    public RespEntity<MszWallet> get(@PathVariable Integer id) {
        MszWallet mw = mszWalletService.selectOne(new EntityWrapper<MszWallet>().eq("accountId", id));
        if (mw == null) {
            MszWallet mszWallet = new MszWallet();
            mszWallet.setFrozen(new BigDecimal(0));
            mszWallet.setBalance(new BigDecimal(0));
            mszWallet.setAccountId(id);
            mszWallet.setPwd("888888");
            mszWalletService.insert(mszWallet);
            return RespEntity.ok().setResponseContent(mszWalletService.selectOne(new EntityWrapper<MszWallet>().eq("accountId", id)));
        }
        return RespEntity.ok().setResponseContent(mw);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "起始页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query")
    })
    @ApiOperation(value = "分页查询MszWallet", notes = "分页查询MszWallet")
    @GetMapping
    public RespEntity<PageInfo<MszWallet>> list(PagingRequest pagingRequest) {
        return RespEntity.ok().setResponseContent(mszWalletService.listPage(pagingRequest, null));
    }

    @PutMapping("recharge")
    @ApiOperation(value = "根据ID修改MszWallet", notes = "根据ID修改MszWallet")
    public RespEntity recharge(@RequestBody MszWallet mszWallet) {

        return RespEntity.ok("更新成功");
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "客户ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "total", value = "充值金额", required = true, paramType = "query")
    })
    @PutMapping("walletPay")
    @ApiOperation(value = "充值-------小程序(房客)@Author=Maoyy", notes = "充值-------小程序(房客)@Author=Maoyy")
    public RespEntity walletPay(Integer id, BigDecimal total) {
        if (mszWalletService.walletPay(id, total)) {
            return RespEntity.ok("充值成功");
        }
        return RespEntity.ok("充值失败");
    }

    @PostMapping
    @ApiOperation(value = "保存MszWallet", notes = "保存MszWallet")
    public RespEntity insert(@RequestBody MszWallet mszWallet) {
        if (!mszWalletService.insert(mszWallet)) {
            return RespEntity.badRequest("保存失败");
        }
        return RespEntity.ok("保存成功");
    }


    @PutMapping
    @ApiOperation(value = "根据ID修改MszWallet", notes = "根据ID修改MszWallet")
    public RespEntity update(@RequestBody MszWallet mszWallet) {
        if (!mszWalletService.updateById(mszWallet)) {
            return RespEntity.badRequest("更新失败");
        }
        return RespEntity.ok("更新成功");
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除MszWallet", notes = "根据ID删除MszWallet")
    public RespEntity delete(@PathVariable Integer id) {
        if (!mszWalletService.deleteById(id)) {
            return RespEntity.badRequest("删除失败");
        }
        return RespEntity.ok("删除成功");
    }


}