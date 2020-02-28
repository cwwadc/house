package com.msz.util.wexin;

import lombok.Data;

/**
 * @Description: 微信配置类
 * @author: cww
 * @date: 2019/7/4 22:27
 */
@Data
public class WechatConfig {

    //小程序appid
    public static final String appid = "wxb96a1e40d9948579";
    //微信支付的商户id
    public static final String mch_id = "1542476831";
    //微信支付的商户密钥
    public static final String key = "vnoFdVJr76Sgz7TTFtMxvfpJ1OpLtVrm";
    //支付成功后的服务器回调url，这里填PayController里的回调函数地址
    public static final String notify_url = "http://api.homoriole.com:8080/pay/wxNotify/";
    //钱包充值回调url，这里填WalletPayController里的回调函数地址
    public static final String wallet_notify_url = "http://api.homoriole.com:8080/WalletPay/wxNotify/";
    //签名方式，固定值
    public static final String SIGNTYPE = "MD5";
    //交易类型，小程序支付的固定值为JSAPI
    public static final String TRADETYPE = "JSAPI";
    //微信统一下单接口地址
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
