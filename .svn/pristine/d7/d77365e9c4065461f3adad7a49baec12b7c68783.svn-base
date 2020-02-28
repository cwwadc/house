package com.msz.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.msz.common.RespEntity;
import com.msz.model.*;
import com.msz.service.*;
import com.msz.util.UUIDUtils;
import com.msz.util.wexin.PayUtil;
import com.msz.util.wexin.WechatConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Maoyy
 * @Description 生活不止眼前的苟且
 * @Date 2019/7/22 17:28
 */
@Api(value = "/baleacePay", description = "余额支付 接口; Responseble:Maoyy")
@RestController
@RequestMapping("/baleacePay")
public class BaleacePayController {

    @Autowired
    MszPayInfoService payInfoService;
    @Autowired
    MszOrderInfoService orderInfoService;
    @Autowired
    MszOrderHistoryService historyService;
    @Autowired
    MszAccountService mszAccountService;
    @Autowired
    MszWalletService walletService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "payOrderId", value = "支付订单ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "total", value = "支付金额", required = true, paramType = "query")
    })
    @ApiOperation(value = "余额支付")
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public RespEntity wxPay(String payOrderId, Integer total) {
        try {
            MszOrderInfo orderInfo = orderInfoService.selectOne(new EntityWrapper<MszOrderInfo>().eq("no", payOrderId));
            MszOrderHistory orderHistory = historyService.selectOne(new EntityWrapper<MszOrderHistory>().eq("no", payOrderId));
            Integer tenantId = null;
            if (orderInfo != null){
                tenantId= orderInfo.getTenantId();
            }

            if (orderHistory != null){
                tenantId= orderHistory.getTenantId();
            }


            MszWallet wallets = walletService.selectOne(new EntityWrapper<MszWallet>().eq("accountId", tenantId));
            if (wallets.getBalance().compareTo(new BigDecimal(total)) == -1) {
                RespEntity res = new RespEntity();
                res.setStatusCode("55");
                res.setStatusMessage("支付失败,余额不足");
                return RespEntity.ok();
            } else {
                wallets.setBalance(wallets.getBalance().subtract(new BigDecimal(total)));
            }
            walletService.updateById(wallets);
            if (orderInfo != null) {
                MszAccount account = mszAccountService.selectById(orderInfo.getLandlordId());
                MszPayInfo payInfo = new MszPayInfo();
                String uuid = UUIDUtils.getUUID32();
                payInfo.setId(uuid);//30位uuid
                payInfo.setAccountId(account.getId());//支付发起账户
                payInfo.setAccountName(account.getName());//支付发起账户名称
                payInfo.setChannel("1");//支付渠道：0钱包余额，1微信支付
                payInfo.setCreateTime(new Date());
                payInfo.setOrderId(orderInfo.getId());//订单ID
                payInfo.setOrderNo(payOrderId);//商户订单编号
                payInfo.setStatus("1");//0创建、1成功、2失败
                payInfo.setType("1");//场景：1支付押金和租金2.支付租金和水电费
                BigDecimal addFee = new BigDecimal(total);
                payInfo.setAmt(new BigDecimal(total));//支付金额
                payInfoService.insert(payInfo);
                //给房东账户钱包加钱
                MszWallet wallet = walletService.selectOne(new EntityWrapper<MszWallet>().eq("accountId", account.getId()));
                wallet.setBalance(wallet.getBalance().add(addFee));
                walletService.updateById(wallet);
            } else {
                if (orderHistory != null) {
                    if (orderHistory.getArrearsFlag().equals("1")) {
                        MszAccount account = mszAccountService.selectById(orderHistory.getLandlordId());
                        MszPayInfo payInfo = new MszPayInfo();
                        String uuid = UUIDUtils.getUUID32();
                        payInfo.setId(uuid);//30位uuid
                        payInfo.setAccountId(account.getId());//支付发起账户
                        payInfo.setAccountName(account.getName());//支付发起账户名称
                        payInfo.setChannel("1");//支付渠道：0钱包余额，1微信支付
                        payInfo.setCreateTime(new Date());
                        payInfo.setOrderId(orderHistory.getId());//订单ID
                        payInfo.setOrderNo(payOrderId);//商户订单编号
                        payInfo.setStatus("1");//0创建、1成功、2失败
                        payInfo.setType("1");//场景：1支付押金和租金2.支付租金和水电费
                        BigDecimal addFee = new BigDecimal(total);
                        payInfo.setAmt(new BigDecimal(total));//支付金额
                        payInfoService.insert(payInfo);
                        //给房东账户钱包加钱
                        MszWallet wallet = walletService.selectOne(new EntityWrapper<MszWallet>().eq("accountId", account.getId()));
                        wallet.setBalance(wallet.getBalance().add(addFee));
                        walletService.updateById(wallet);
                    } else {
                        MszAccount account = mszAccountService.selectById(orderHistory.getLandlordId());
                        MszPayInfo payInfo = new MszPayInfo();
                        String uuid = UUIDUtils.getUUID32();
                        payInfo.setId(uuid);//30位uuid
                        payInfo.setAccountId(account.getId());//支付发起账户
                        payInfo.setAccountName(account.getName());//支付发起账户名称
                        payInfo.setChannel("1");//支付渠道：0钱包余额，1微信支付
                        payInfo.setCreateTime(new Date());
                        payInfo.setOrderId(orderHistory.getId());//订单ID
                        payInfo.setOrderNo(payOrderId);//商户订单编号
                        payInfo.setStatus("1");//0创建、1成功、2失败
                        payInfo.setType("1");//场景：1支付押金和租金2.支付租金和水电费
                        payInfo.setAmt(new BigDecimal(total));//支付金额
                        payInfoService.insert(payInfo);
                    }
                }
            }
            return RespEntity.ok();
        } catch (Exception e) {
            RespEntity res = new RespEntity();
            res.setStatusCode("55");
            res.setStatusMessage("支付失败");
            return RespEntity.ok();
        }
    }
}
