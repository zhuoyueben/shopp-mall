package com.zhuoyueben.gmail.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-06-17 10:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class PaymentInfo implements Serializable {
    /**
     * 编号
     */
    private String id;
    /**
     * 对外业务编号
     */
    private String orderSn;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 支付宝交易编号
     */
    private String alipayTradeNo;
    /**
     * 支付金额
     */
    private BigDecimal totalAmount;
    /**
     * 交易内容
     */
    private String subject;
    /**
     * 支付状态
     */
    private String paymentStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建时间
     */
    private Date confirmTime;
    /**
     * 回调信息
     */
    private String callbackContent;
    private Date callbackTime;

}
