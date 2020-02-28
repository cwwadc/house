package com.msz.VO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Maoyy
 * @Description 生活不止眼前的苟且
 * @Date 2019/7/14 15:36
 */
@Data
public class MszOrderHistoryVO {
    private Integer id;
    private Integer accountId;
    private String accountName;
    private BigDecimal amt;
    private String status;
    private Date updateTime;
    private Date createTime;
}
