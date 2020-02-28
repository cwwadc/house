package com.msz.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.msz.common.RespEntity;
import com.msz.dao.MszOrderInfoMapper;
import com.msz.model.*;
import com.msz.service.*;
import com.msz.util.UUIDUtils;
import com.msz.util.wexin.PayUtil;
import com.msz.util.wexin.WechatConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Description:
 * @author: cww
 * @date: 2019/7/4 22:42
 */
@Api(value = "/pay", description = "微信支付 接口; Responseble:cww")
@RestController
@RequestMapping("/pay")
public class PayController {

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
            @ApiImplicitParam(name = "openId", value = "用户OpendId", required = true, paramType = "query"),
            @ApiImplicitParam(name = "payOrderId", value = "支付订单ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "total", value = "支付金额", required = true, paramType = "query")
    })
    @ApiOperation(value = "请求支付接口")
    @RequestMapping(value = "/balancePay", method = RequestMethod.POST)
    public RespEntity balancePay(HttpServletRequest request, String openId, String payOrderId, Integer total) {
        return null;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "用户OpendId", required = true, paramType = "query"),
            @ApiImplicitParam(name = "payOrderId", value = "支付订单ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "total", value = "支付金额", required = true, paramType = "query")
    })
    @ApiOperation(value = "请求支付接口")
    @RequestMapping(value = "/wxPay", method = RequestMethod.POST)
    public RespEntity wxPay(HttpServletRequest request, String openId, String payOrderId, Integer total) {
//        total = 1;
        try {
            //生成的随机字符串
            String nonce_str = getRandomStringByLength(32);
            //商品名称
            String body = "房客支付租金";
            //获取客户端的ip地址
            /*  String spbill_create_ip = getIpAddr(request);*/
            String spbill_create_ip = "118.190.58.103";
            //组装参数，用户生成统一下单接口的签名
            Map<String, String> packageParams = new HashMap<>();
            packageParams.put("appid", WechatConfig.appid);//小程序appid
            packageParams.put("mch_id", WechatConfig.mch_id);//商户id
            packageParams.put("nonce_str", nonce_str);//随机字符串
            packageParams.put("body", body);//商品描述
            packageParams.put("out_trade_no", payOrderId + "");//商户订单号,自己的订单ID
            packageParams.put("total_fee", total + "");//支付金额(单位为分)，这边需要转成字符串类型，否则后面的签名会失败
            packageParams.put("spbill_create_ip", spbill_create_ip);//终端IP(外网ip)
            packageParams.put("notify_url", WechatConfig.notify_url);//支付成功后的回调地址
            packageParams.put("trade_type", WechatConfig.TRADETYPE);//支付方式
            packageParams.put("openid", openId + "");//用户的openID，自己获取

            String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串

            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            String mysign = PayUtil.sign(prestr, WechatConfig.key, "utf-8").toUpperCase();

            String xml = "<xml>" + "<appid>" + WechatConfig.appid + "</appid>"
                    + "<body><![CDATA[" + body + "]]></body>"
                    + "<mch_id>" + WechatConfig.mch_id + "</mch_id>"
                    + "<nonce_str>" + nonce_str + "</nonce_str>"
                    + "<notify_url>" + WechatConfig.notify_url + "</notify_url>"
                    + "<openid>" + openId + "</openid>"
                    + "<out_trade_no>" + payOrderId + "</out_trade_no>"
                    + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
                    + "<total_fee>" + total + "</total_fee>"//支付的金额，单位：分
                    + "<trade_type>" + WechatConfig.TRADETYPE + "</trade_type>"
                    + "<sign>" + mysign + "</sign>"
                    + "</xml>";

            //调用统一下单接口，并接受返回的结果
            String result = PayUtil.httpRequest(WechatConfig.pay_url, "POST", xml);
            // 将解析结果存储在HashMap中
            Map map = PayUtil.doXMLParse(result);
            String return_code = (String) map.get("return_code");//返回状态码
            String result_code = (String) map.get("result_code");//返回状态码
            Map<String, Object> response = new HashMap<>();//返回给小程序端需要的参数
            if (return_code.equals("SUCCESS") && return_code.equals(result_code)) {
                String prepay_id = (String) map.get("prepay_id");//返回的预付单信息
                response.put("nonceStr", nonce_str);
                response.put("package", "prepay_id=" + prepay_id);
                Long timeStamp = System.currentTimeMillis() / 1000;
                response.put("timeStamp", timeStamp + ""); //这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
                //拼接签名需要的参数
                String stringSignTemp =
                        "appId=" + WechatConfig.appid +
                                "&nonceStr=" + nonce_str +
                                "&package=prepay_id=" + prepay_id +
                                "&signType=MD5" +
                                "&timeStamp=" + timeStamp;
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                String paySign = PayUtil.sign(stringSignTemp, WechatConfig.key, "utf-8").toUpperCase();
                response.put("paySign", paySign);
            }
            response.put("appid", WechatConfig.appid);
            return RespEntity.ok().setResponseContent(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //这里是支付回调接口，微信支付成功后会自动调用
    @RequestMapping(value = "/wxNotify", method = RequestMethod.POST)
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notityXml = sb.toString();
        String resXml = "";
        Map map = PayUtil.doXMLParse(notityXml);
        System.out.println("返回的map是:----------------------" + map);
        String returnCode = (String) map.get("return_code");
        System.out.println("返回的code码是：------------------" + returnCode);
        String sign = (String) map.get("sign");
        System.out.println("微信的签名是：---------------------" + sign);
        System.out.println("微信的签名是：---------------------" + sign);
        System.out.println("微信的签名是：---------------------" + sign);
        System.out.println("微信的签名是：---------------------" + sign);
        System.out.println("微信的签名是：---------------------" + sign);
        if ("SUCCESS".equals(returnCode)) {
            //验证签名是否正确
            map.remove("sign");
            String prestr = PayUtil.createLinkString(map);
            String mysign = PayUtil.sign(prestr, WechatConfig.key, "utf-8").toUpperCase();
            System.out.println("新的签名是：---------------------" + mysign);
            System.out.println("新的签名是：---------------------" + mysign);
            System.out.println("新的签名是：---------------------" + mysign);
            System.out.println("新的签名是：---------------------" + mysign);
            System.out.println("新的签名是：---------------------" + mysign);
            //根据微信官网的介绍，此处不仅对回调的参数进行验签，还需要对返回的金额与系统订单的金额进行比对等
            if (mysign.equals(sign)) {//延签通过
                /**此处添加自己的业务逻辑代码start**/
                String no = (String) map.get("out_trade_no");//商户订单号
                MszOrderInfo orderInfo = orderInfoService.selectOne(new EntityWrapper<MszOrderInfo>().eq("no", no));
                MszOrderHistory orderHistory = historyService.selectOne(new EntityWrapper<MszOrderHistory>().eq("no", no));
                if(orderInfo != null) {
                    MszAccount account = mszAccountService.selectById(orderInfo.getLandlordId());
                    MszPayInfo payInfo = new MszPayInfo();
                    String uuid = UUIDUtils.getUUID32();
                    payInfo.setId(uuid);//30位uuid
                    payInfo.setAccountId(account.getId());//支付发起账户
                    payInfo.setAccountName(account.getName());//支付发起账户名称
                    payInfo.setChannel("1");//支付渠道：0钱包余额，1微信支付
                    payInfo.setCreateTime(new Date());
                    payInfo.setOrderId(orderInfo.getId());//订单ID
                    payInfo.setOrderNo(no);//商户订单编号
                    payInfo.setStatus("1");//0创建、1成功、2失败
                    payInfo.setType("1");//场景：1支付押金和租金2.支付租金和水电费
                    String total = (String) map.get("total_fee");
                    BigDecimal addFee = new BigDecimal(total).divide(new BigDecimal(100));
                    payInfo.setAmt(new BigDecimal(total).divide(new BigDecimal(100)));//支付金额
                    payInfoService.insert(payInfo);
                    //给房东账户钱包加钱
                    MszWallet wallet = walletService.selectOne(new EntityWrapper<MszWallet>().eq("accountId", account.getId()));
                    wallet.setBalance(wallet.getBalance().add(addFee));
                    walletService.updateById(wallet);
                }else {
                    if (orderHistory != null){
                        MszAccount account = mszAccountService.selectById(orderInfo.getLandlordId());
                        MszPayInfo payInfo = new MszPayInfo();
                        String uuid =  UUIDUtils.getUUID32();
                        payInfo.setId(uuid);//30位uuid
                        payInfo.setAccountId(account.getId());//支付发起账户
                        payInfo.setAccountName(account.getName());//支付发起账户名称
                        payInfo.setChannel("1");//支付渠道：0钱包余额，1微信支付
                        payInfo.setCreateTime(new Date());
                        payInfo.setOrderId(orderInfo.getId());//订单ID
                        payInfo.setOrderNo(no);//商户订单编号
                        payInfo.setStatus("1");//0创建、1成功、2失败
                        payInfo.setType("1");//场景：1支付押金和租金2.支付租金和水电费
                        String total = (String) map.get("total_fee");
                        payInfo.setAmt(new BigDecimal(total).divide(new BigDecimal(100)));//支付金额
                        payInfoService.insert(payInfo);
                    }
                }
                //注意要判断微信支付重复回调，支付成功后微信会重复的进行回调

                /**此处添加自己的业务逻辑代码end**/
                //通知微信服务器已经支付成功
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            }
        } else {
            String no = (String) map.get("out_trade_no");//商户订单号
            MszOrderInfo orderInfo = orderInfoService.selectOne(new EntityWrapper<MszOrderInfo>().eq("no", no));
            MszAccount account = mszAccountService.selectById(orderInfo.getLandlordId());
            MszPayInfo payInfo = new MszPayInfo();
            String uuid = UUIDUtils.getUUID32();
            payInfo.setId(uuid);//30位uuid
            payInfo.setAccountId(account.getId());//支付发起账户
            payInfo.setAccountName(account.getName());//支付发起账户名称
            payInfo.setChannel("1");//支付渠道：0钱包余额，1微信支付
            payInfo.setCreateTime(new Date());
            payInfo.setOrderId(orderInfo.getId());//订单ID
            payInfo.setOrderNo(no);//商户订单编号
            payInfo.setStatus("2");//0创建、1成功、2失败
            payInfo.setType("1");//场景：1支付押金和租金2.支付租金和水电费
            String total = (String) map.get("total_fee");
            payInfo.setAmt(new BigDecimal(total).divide(new BigDecimal(100)));//支付金额
            payInfoService.insert(payInfo);
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }

        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }

    //获取随机字符串
    private String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    //获取IP
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
}
