package com.zhuoyueben.gmail.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-06-12 10:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class OmsOrderItem {
    private String id;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 订单编号
     */
    private String orderSn;
    private String productId;
    private String productPic;
    private String productName;
    private String productBrand;
    private String productSn;
    /**
     * 销售价格
     */
    private BigDecimal productPrice;
    /**
     * 购买数量
     */
    private BigDecimal productQuantity;
    /**
     * 商品sku编号
     */
    private String productSkuId;
    /**
     * 商品sku条码
     */
    private String productSkuCode;
    /**
     * 商品分类id
     */
    private String productCategoryId;
    /**
     * 商品的销售属性
     */
    private String sp1;
    private String sp2;
    private String sp3;
    /**
     * 商品促销名称
     */
    private String promotionName;
    /**
     * 商品促销分解金额
     */
    private BigDecimal promotionAmount;
    /**
     * 优惠券优惠分解金额
     */
    private BigDecimal couponAmount;
    /**
     * 积分优惠分解金额
     */
    private BigDecimal integrationAmount;
    /**
     * 该商品经过优惠后的分解金额
     */
    private BigDecimal realAmount;
    private String giftIntegration;
    private String giftGrowth;
    /**
     * 商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]
     */
    private String productAttr;
}
