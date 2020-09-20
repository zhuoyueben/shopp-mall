package com.zhuoyueben.gmail.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-06-06 17:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class OmsCartItem implements Serializable {
    private String id;
    private String productId;
    private String productSkuId;
    private String memberId;
    /**
     * 购买数量
     */
    private BigDecimal quantity;
    /**
     * 添加到购物车的价格
     */
    private BigDecimal price;

    @Transient
    private BigDecimal totalPrice;
    /**
     * 销售属性1
     */
    private String sp1;
    /**
     * 销售属性2
     */
    private String sp2;
    /**
     * 销售属性3
     */
    private String sp3;
    /**
     * 商品主图
     */
    private String productPic;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品副标题（卖点）
     */
    private String productSubTitle;
    /**
     * 商品sku条码
     */
    private String productSkuCode;
    /**
     * 会员昵称
     */
    private String memberNickname;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 修改时间
     */
    private Date modifyDate;
    /**
     * 是否删除
     */
    private String deleteStatus;
    /**
     * 商品分类
     */
    private String productCategoryId;
    private String productBrand;
    private String productSn;
    private String isChecked;
    /**
     * 商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]
     */
    private String productAttr;
}
