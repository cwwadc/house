package com.msz.VO;

import lombok.Data;

/**
 * @Author Maoyy
 * @Description 生活不止眼前的苟且
 * @Date 2019/7/2 16:19
 */
@Data
public class UserInfoReceive {
    private String nickName; //昵称
    private String gender; //性别
    private String avatarUrl; //头像
    private String openId; //openId
}
