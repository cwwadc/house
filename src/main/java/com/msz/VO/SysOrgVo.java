package com.msz.VO;

import lombok.Data;

import java.util.Date;

@Data
public class SysOrgVo {

    private Integer id;
    /**
     * 网点编号
     */
    private String code;
    /**
     * 网点名称
     */
    private String name;
    /**
     * 上级网点ID
     */
    private Integer parentId;
    /**
     * 负责人
     */
    private Integer stafId;

    /**
     * 负责人名称
     */
    private String stafName;

    /**
     * 是否删除
     */
    private String isDel;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 地址
     */
    private String address;
    /**
     * 电话
     */
    private String tel;
    /**
     * 模糊搜索字段
     */
    private String searchField;

}
