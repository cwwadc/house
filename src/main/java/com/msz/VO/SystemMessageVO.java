package com.msz.VO;

import lombok.Data;

import java.util.Date;

/**
 * @Author Maoyy
 * @Description 生活不止眼前的苟且
 * @Date 2019/7/12 21:04
 */
@Data
public class SystemMessageVO {
    private Integer id;
    /**
     * 发布信息的账号
     */
    private Integer promulgatorId;
    /**
     * 发布信息的名称
     */
    private String promulgatorName;
    /**
     * 接收信息的账号
     */
    private Integer receiverId;
    /**
     * 是否是 sys_user表的消息 0不是 1是
     */
    private String isUser;
    /**
     * 消息类型，0：系统消息，1：租约消息 , 2 : 提现 3：退款
     */
    private String type;
    /**
     * 标题
     */
    private String title;
    /**
     * 文本内容
     */
    private String contentText;
    /**
     * 关联ID
     */
    private Integer relId;
    /**
     * 0未读 1已读
     */
    private Integer isRead;
    /**
     * 0正常 1冻结
     */
    private String isDel;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 消息创建时间
     */
    private Date createTime;


}
