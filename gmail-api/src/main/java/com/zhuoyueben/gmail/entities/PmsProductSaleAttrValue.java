package com.zhuoyueben.gmail.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-11 10:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain=true)
public class PmsProductSaleAttrValue implements Serializable {
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
     * 销售属性值名称
     */
    private String saleAttrValueName;
    @Transient
    String isChecked;

}
