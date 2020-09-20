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
 * @Date 2020-04-11 10:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain=true)
public class PmsProductSaleAttr implements Serializable {
    /**
     * id
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
     * 销售属性id
     */

    private String saleAttrId;
    /**
     * 销售属性名称(冗余)
     */
    private String saleAttrName;
    @Transient
    private List<PmsProductSaleAttrValue> spuSaleAttrValueList;

}
