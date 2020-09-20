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
 * @Date 2020-05-26 15:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain=true)
public class PmsSearchSkuInfo implements Serializable {
    @Id
    private long id;
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
    private Double hotScore;
    /**
     * 三级分类id（冗余)
     */
    private String catalog3Id;
    /**
     * 默认显示图片(冗余)
     */
    private String skuDefaultImg;

    private List<PmsSkuAttrValue> skuAttrValueList;
}
