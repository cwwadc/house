package com.msz.VO;

import com.msz.model.MszAccount;
import lombok.Data;

/**
 * @Author Maoyy
 * @Description 生活不止眼前的苟且
 * @Date 2019/6/11 11:50
 */
@Data
public class WeProgramLoginVO {
    //是否登录成功
    private boolean isLogin;
    //登录成功后用户基本信息
    private MszAccount obj;
}
