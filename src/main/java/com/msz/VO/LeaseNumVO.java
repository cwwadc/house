package com.msz.VO;

import lombok.Data;

@Data
public class LeaseNumVO {

    private int appointmentNum;//0待生效
    private int executeNum;//1执行中
    private int endNum;//2已结束
    private int exceptionNum;//3异常

}
