package com.zhuoyueben.gmail.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-06-12 10:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class OmsOrder implements Serializable {
    /**
     * 订单id
     */
    private String id;
    private String memberId;
    private String couponId;
    /**
     * 订单编号
     */
    private String orderSn;
    /**
     * 提交时间
     */
    private Date createTime;
    /**
     * 用户帐号
     */
    private String memberUsername;
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;
    /**
     * 应付金额（实际支付金额）
     */
    private BigDecimal payAmount;
    /**
     * 运费金额
     */
    private BigDecimal freightAmount;
    /**
     * 促销优化金额（促销价、满减、阶梯价）
     */
    private BigDecimal promotionAmount;
    /**
     * 积分抵扣金额
     */
    private BigDecimal integrationAmount;
    /**
     * 优惠券抵扣金额
     */
    private BigDecimal couponAmount;
    /**
     * 管理员后台调整订单使用的折扣金额
     */
    private BigDecimal discountAmount;
    /**
     * 支付方式：0->未支付；1->支付宝；2->微信
     */
    private String payType;
    /**
     * 订单来源：0->PC订单；1->app订单
     */
    private String sourceType;
    /**
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
     */
    private String status;
    /**
     * 订单类型：0->正常订单；1->秒杀订单
     */
    private String orderType;
    /**
     * 物流公司(配送方式)
     */
    private String deliveryCompany;
    /**
     * 物流单号
     */
    private String deliverySn;
    /**
     * 自动确认时间（天）
     */
    private String autoConfirmDay;
    /**
     * 可以获得的积分
     */
    private String integration;
    /**
     * 可以活动的成长值
     */
    private String growth;
    /**
     * 活动信息
     */
    private String promotionInfo;
    /**
     * 发票类型：0->不开发票；1->电子发票；2->纸质发票
     */
    private String billType;
    /**
     * 发票抬头
     */
    private String billHeader;
    /**
     * 发票内容
     */
    private String billContent;
    /**
     * 收票人电话
     */
    private String billReceiverPhone;
    /**
     * 收票人邮箱
     */
    private String billReceiverEmail;
    /**
     * 收货人姓名
     */
    private String receiverName;
    /**
     * 收货人电话
     */
    private String receiverPhone;
    /**
     * 收货人邮编
     */
    private String receiverPostCode;
    /**
     * 省份/直辖市
     */
    private String receiverProvince;
    /**
     * 城市
     */
    private String receiverCity;
    /**
     * 区
     */
    private String receiverRegion;
    /**
     * 详细地址
     */
    private String receiverDetailAddress;
    /**
     * 订单备注
     */
    private String note;
    /**
     * 确认收货状态：0->未确认；1->已确认
     */
    private String confirmStatus;
    /**
     * 删除状态：0->未删除；1->已删除
     */
    private String deleteStatus;
    /**
     * 下单时使用的积分
     */
    private String useIntegration;
    /**
     * 支付时间
     */
    private Date paymentTime;
    /**
     * 发货时间
     */
    private Date deliveryTime;
    /**
     * 确认收货时间
     */
    private Date receiveTime;
    /**
     * 评价时间
     */
    private Date commentTime;
    /**
     * 修改时间
     */
    private Date modifyTime;

    private List<OmsOrderItem> omsOrderItems;
}
