package com.msz.common;

import com.msz.dao.SysLogMapper;
import com.msz.model.SysLog;
import com.msz.service.SysLogService;
import com.msz.util.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * csg
 */

@Component
public class SysLogUtil {

    @Autowired
    private SysLogService logService;

    public void insertSysLog(HttpServletRequest request, Integer userId, String username, String info){
        SysLog sysLog = new SysLog();
        sysLog.setCreateTime(new Date());
        sysLog.setType(1);
        sysLog.setIp(IpUtils.getIpAddr(request));
        sysLog.setUserId(userId);
        sysLog.setInfo(username + info);
        logService.insert(sysLog);
    }

}
