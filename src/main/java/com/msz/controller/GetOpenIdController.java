package com.msz.controller;

import com.alibaba.fastjson.JSON;
import com.msz.util.wexin.AesCbcUtil;
import com.msz.util.wexin.SendRequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Maoyy
 * @Description 生活不止眼前的苟且
 * @Date 2019/7/29 14:48
 */
@Api(value = "/getOpenid", description = "得到openId 接口; Responseble:Maoyy")
@RestController
@RequestMapping("/getOpenid")
public class GetOpenIdController {

    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "code", required = true, paramType = "query"),
            @ApiImplicitParam(name = "appid", value = "小程序唯一标识", required = true, paramType = "query"),
            @ApiImplicitParam(name = "secret", value = "程序的 app secret", required = true, paramType = "query"),
            @ApiImplicitParam(name = "grant_type", value = "授权", required = true, paramType = "query"),
    })
    @ApiOperation(value = "获取Openid-------小程序@Author=Maoyy")
    @PostMapping(value = "/getOpenid")
    public Map getOpenid(String code, String appid, String secret, String grant_type) {//接收用户传过来的code，required=false表明如果这个参数没有传过来也可以。

        Map map = new HashMap();
        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }
        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
        //请求参数
        String params = "appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String sr = SendRequestUtil.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(sr);
        //获取会话密钥（session_key）
        map.put("session_key", json.get("session_key").toString());
        //用户的唯一标识（openid）
        map.put("openid", json.get("openid"));

        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
//        try {
//            String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
//            if (null != result && result.length() > 0) {
//                map.put("status", 1);
//                map.put("msg", "解密成功");
//
//                JSONObject userInfoJSON = JSONObject.parseObject(result);
//                Map userInfo = new HashMap();
//                userInfo.put("openId", userInfoJSON.get("openId"));
//                userInfo.put("nickName", userInfoJSON.get("nickName"));
//                userInfo.put("gender", userInfoJSON.get("gender"));
//                userInfo.put("city", userInfoJSON.get("city"));
//                userInfo.put("province", userInfoJSON.get("province"));
//                userInfo.put("country", userInfoJSON.get("country"));
//                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
//                userInfo.put("unionId", userInfoJSON.get("unionId"));
//                map.put("userInfo", userInfo);
//                return map;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return map;
    }
}
