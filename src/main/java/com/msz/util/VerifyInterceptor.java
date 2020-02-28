//package com.build.arahitecture.util;
//
//import com.build.arahitecture.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.filter.Cookie;
//import javax.servlet.filter.HttpServletRequest;
//import javax.servlet.filter.HttpServletResponse;
//
//@Component
//public class VerifyInterceptor extends HandlerInterceptorAdapter {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        // 标记，用于最后返回值
//        boolean flag = false;
//
//        // 从请求报文cookies中获取token
//        String token = null;
//        Cookie[] cookies = request.getCookies();
//        // 结果非空时，从数组中查找名为token的cookie
//        if (null != cookies) {
//            for (Cookie cookie : cookies) {
//                if ("token".equals(cookie.getName())) {
//                    token = cookie.getValue();
//                    break;
//                }
//            }
//        }
//
//        // 开发时无需token，测试or部署时需要改成false
//        if (null == token || "".equals(token)) {
//            System.out.println("empty token");
//            flag = true;
//        } else {
//            try {
//                Map<String, Claim> map = JwtTokenUtils.vertifyToken(token);
//                String name = map.get("username").asString();
//                flag = managerService.isUsernameExist(name);
//            } catch (RuntimeException e) {
//                e.printStackTrace();
//                System.out.println("权限认证失败");
//                logger.debug("权限认证失败");
//            }
//        }
//        if (!flag) {    // 验证失败时设置response错误码
//            response.setStatus(401);
//        }
//        return flag;
//    }
//
//
//
//}
