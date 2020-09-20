package com.zhuoyueben.gmail.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-13 16:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain=true)
public class PmsSkuSaleAttrValue implements Serializable {
    /**
     * id
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    /**
     * 库存单元id
     */
    private String skuId;
    /**
     * 销售属性id（冗余)
     */
    private String saleAttrId;
    /**
     * 销售属性值id
     */
    private String saleAttrValueId;
    /**
     * 销售属性名称(冗余)
     */
    private String saleAttrName;
    /**
     * 销售属性值名称(冗余)
     */
    private String saleAttrValueName;
}
