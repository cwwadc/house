package com.msz.common;

import com.msz.model.SysUser;
import com.msz.util.RedisUtil;
import org.elasticsearch.client.Requests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @author: cww
 * @date: 2019/7/6 13:58
 */
@Component
public class UserCommon {


    @Autowired
    private RedisUtil redisUtil;

    public SysUser getUser(){
        SysUser user =(SysUser) redisUtil.get("user");
        if (user!= null){
            return user;
        }
        return null;
    }

    public static SysUser getCurrentUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SysUser user = (SysUser) request.getAttribute("currentUser");
        return user;
    }

}
