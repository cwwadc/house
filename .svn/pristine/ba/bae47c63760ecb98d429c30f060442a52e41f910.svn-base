package com.msz.VO;

import com.msz.model.MszOrderCharge;
import com.msz.model.MszRoom;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class MszOrderInfoVo {

    private MszRoom mszRoom;
    private Integer id; // 交费ID
    private String no; // 缴费编号
    private Integer leaseId; // 租约ID
    private Integer roomId; //  房间ID
    private Integer landlordId; // 房东ID
    private Integer tenantId; // 房客ID
    private String tenantName; // 房客姓名
    private String billDay; // 账单日
    private String collectMoneyDay; // 租金交费日
    private BigDecimal rentPrice; // 租金
    private BigDecimal paidRentPrice; // 实收租金
    private BigDecimal depositPrice; // 押金
    private BigDecimal paidDepositPrice; // 实收押金
    private BigDecimal schedulePrice; // 预付定金(应收)
    private BigDecimal paidSchedulePrice; // 预付定金(实收)
    private BigDecimal receiveTotal; // 应收总费用
    private BigDecimal paidTotal; // 实收总费用
    private BigDecimal oweTotal; // 欠收总费用
    private String arrearsFlag; // 0 不欠租  1 欠租
    private String available;// 0 不应用到每期交费  1 应用到每期交费
    private String flag; //  0 不修改金额提醒  1 修改金额提醒
    private BigDecimal breachPrice; // 违约金
    private Date endTime; // 结束时间
    private  String endTimeBegin;
    private  String endTimeEnd;
    private String status; // 缴费状态 0未缴费 1已缴费
    private String isDel; //  是否删除
    private Date updateTime;// 更新时间
    private Date createTime;// 生成时间
    private Integer orgId;// 所属网点id
    private Integer operatorId; // 操作人ID
    private String operatorName; // 操作人名称
    private String scheduleDesc; // 定金描述
    private Integer createId; // 创建人ID
    private List<MszOrderCharge> chargeList;

}
