package com.msz.VO;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Maoyy
 * @Description 生活不止眼前的苟且
 * @Date 2019/6/14 10:48
 */
@Data
public class MszWalletReceive {

    private Integer id;
    /**
     * 账号id
     */
    private Integer accountId;
    /**
     * 余额（分）
     */
    private BigDecimal balance;
    /**
     * 微信支付订单号
     */
    private BigDecimal transactionId;
    /**
     * 商户订单号
     */
    private BigDecimal outTradeNo;

}
