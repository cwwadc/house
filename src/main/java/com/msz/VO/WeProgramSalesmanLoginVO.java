package com.msz.VO;

import com.msz.model.SysUser;
import lombok.Data;

/**
 * @Author Maoyy
 * @Description 生活不止眼前的苟且
 * @Date 2019/6/19 16:12
 */
@Data
public class WeProgramSalesmanLoginVO {
    //是否登录成功
    private boolean isLogin;
    //登录成功后用户基本信息
    private SysUser obj;
}
