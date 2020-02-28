package com.msz.util.aop;

import com.msz.common.UserCommon;
import com.msz.model.SysLog;
import com.msz.service.SysLogService;
import com.msz.util.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志：切面处理类
 */
@Aspect
@Component
public class SyslogAspect {

    @Autowired
    private SysLogService sysLogService;
    @Autowired
    UserCommon userCommon;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(com.msz.util.aop.MyLog)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        SysLog sysLog = new SysLog();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //1.获取用户id
        //TODO
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        /*Integer userId = UserCommon.getUserId();
        sysLog.setUserId(userId);*/
        //获取用户id
        Integer userId = UserCommon.getCurrentUser().getId();
        sysLog.setUserId(userId);
        //2.日志类型
        sysLog.setType(1);
        //3.操作IP
        sysLog.setIp(IpUtils.getIpAddr(request));
        //4.操作详情
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            String value = myLog.value();
            sysLog.setInfo(value);
        }
        //5.操作时间
        sysLog.setCreateTime(new Date());
        //6.调用service保存SysLog实体类到数据库
        sysLogService.insert(sysLog);
    }

/*    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        System.out.println("切面。。。。。");
        //保存日志
        SysLog sysLog = new SysLog();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            String value = myLog.value();
            sysLog.setOperation(value);//保存获取的操作
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSON.toJSONString(args);
        sysLog.setParams(params);

        sysLog.setCreateDate(new Date());
        //获取用户名
        //TODO
        sysLog.setUsername("wanwan");
        //获取用户ip地址
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        sysLog.setIp(IpUtils.getIpAddr(request));
        //记录操作结果
       *//* RespEntity respEntity =new RespEntity();
        String statusMessage = respEntity.ok().getStatusMessage();
        sysLog.setMsg(statusMessage);*//*
        //调用service保存SysLog实体类到数据库
        sysLogService.insert(sysLog);
    }*/

}