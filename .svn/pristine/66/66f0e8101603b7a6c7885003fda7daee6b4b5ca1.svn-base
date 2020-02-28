package com.msz.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.msz.annotation.LoginRequired;
import com.msz.common.RespEntity;
import com.msz.model.MszToken;
import com.msz.model.SysUser;
import com.msz.service.MszTokenService;
import com.msz.service.SysUserService;
import com.msz.util.CookieUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiModelProperty;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Description:
 * @author: cww
 * @date: 2019/7/17 20:51
 */
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private MszTokenService tokenService;
    @Autowired
    SysUserService userService;

    private volatile T	responseContent	= null;
    private volatile String	prefix      	= null;

    //提供查询
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {}
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {}
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("statusCode","403");
        map.put("responseContent",responseContent);
        map.put("prefix",prefix);
        map.put("timestamp",new Date().getTime());
        //普通路径放行
        if ( "/admin/login".equals(request.getRequestURI())) {
            return true;}
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        // 有 @LoginRequired 注解，需要认证
        if (methodAnnotation != null) {
            //权限路径拦截
            request.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            response.setHeader("Access-Control-Allow-Origin","*");
            response.setHeader("Access-Control-Allow-Credentials","true");
            response.setHeader("Vary","Origin");
            ServletOutputStream outputStream = response.getOutputStream();
            final String headerToken = request.getHeader("token");
            //判断请求信息
            if (null == headerToken || headerToken.trim().equals("")) {
                map.put("statusMessage","你没有token,需要登录哦!");
                String json = JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue);
                outputStream.write(json.getBytes());
                /*outputStream.write("你没有token,需要登录".getBytes());*/
                outputStream.flush();
                outputStream.close();
                return false;
            }
            //解析Token信息
            try {
                Claims claims = Jwts.parser().setSigningKey("dahao").parseClaimsJws(headerToken).getBody();
                String tokenUserId = (String) claims.get("userid");
                int itokenUserId = Integer.parseInt(tokenUserId);
                //根据客户Token查找数据库Token
                MszToken myToken = tokenService.findByUserId(itokenUserId);
                SysUser user = userService.getLoginUserById(itokenUserId);
                //数据库没有Token记录
                if (null == myToken) {
                    map.put("statusMessage","我没有你的token？需要登录");
                    String json = JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue);
                    outputStream.write(json.getBytes());
                   /* outputStream.write("我没有你的token？,需要登录".getBytes());*/
                    outputStream.flush();
                    outputStream.close();
                    return false;
                }
                //数据库Token与客户Token比较
                if (!headerToken.equals(myToken.getToken())) {
                    map.put("statusMessage","账号在别的地方登录,请您重新登录");
                    String json = JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue);
                    outputStream.write(json.getBytes());
                    /*outputStream.write("你的token修改过？,需要登录".getBytes());*/
                    outputStream.flush();
                    outputStream.close();
                    return false;
                }
                //判断Token过期
                Date tokenDate = (Date) claims.getExpiration();
                int chaoshi = (int) (new Date().getTime() - tokenDate.getTime()) / 1000;
                if (chaoshi > 60 * 30) {
                    map.put("statusMessage","您的账号闲置过久,请您重新登录");
                    String json = JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue);
                    outputStream.write(json.getBytes());
                   /* outputStream.write("你的token过期了？,需要登录".getBytes());*/
                    outputStream.flush();
                    outputStream.close();
                    return false;
                }
                request.setAttribute("currentUser",user);
            } catch (Exception e) {
                e.printStackTrace();
                map.put("statusMessage","登录已过期,请重新登录");
                String json = JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue);
                outputStream.write(json.getBytes());
                /*outputStream.write("反正token不对,需要登录".getBytes());*/
                outputStream.flush();
                outputStream.close();
                return false;
            }
            return true;
        }
        //最后才放行
        return true;
    }

   /* public static String getOpenApiRequestData(HttpServletRequest request){
        try {

            int contentLength = request.getContentLength();
            if (contentLength < 0) {
                return null;
            }
            byte buffer[] = new byte[contentLength];
            for (int i = 0; i < contentLength;) {

                int readlen = request.getInputStream().read(buffer, i, contentLength - i);
                if (readlen == -1) {
                    break;
                }
                i += readlen;
            }

            String charEncoding = request.getCharacterEncoding();
            if (charEncoding == null) {
                charEncoding = "UTF-8";
            }
            return new String(buffer, charEncoding);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }*/
}

