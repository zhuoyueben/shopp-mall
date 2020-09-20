package com.zhuoyueben.gmail.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-11 10:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain=true)
public class PmsProductInfo implements Serializable {
    /**
     * 商品id
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    /**
     * 商品名称
     */
    @Column(name = "product_name")
    private String spuName;
    /**
     * 商品描述(后台简述）
     */
    private String description;
    /**
     * 三级分类id
     */
    private String catalog3Id;
    /**
     * 品牌id
     */
    private Integer tmId;

    @Transient
    private List<PmsProductImage> spuImageList;
    @Transient
    private List<PmsProductSaleAttr> spuSaleAttrList;
}
