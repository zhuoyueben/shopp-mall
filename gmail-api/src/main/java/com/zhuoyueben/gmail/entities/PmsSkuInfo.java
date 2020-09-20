package com.zhuoyueben.gmail.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-11 9:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain=true)
public class PmsSkuInfo implements Serializable {
    /**
     * 库存id(itemID)
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    /**
     * 商品id
     */
    private String productId;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * sku名称
     */
    private String skuName;
    /**
     * 商品规格描述
     */
    private String skuDesc;
    private BigDecimal weight;
    /**
     * 品牌(冗余)
     */
    private Integer tmId;
    /**
     * 三级分类id（冗余)
     */
    private String catalog3Id;
    /**
     * 默认显示图片(冗余)
     */
    private String skuDefaultImg;
    @Transient
    private List<PmsSkuAttrValue> skuAttrValueList;
    @Transient
    private List<PmsSkuSaleAttrValue> skuSaleAttrValueList;
    @Transient
    private List<PmsSkuImage> skuImageList;
}
