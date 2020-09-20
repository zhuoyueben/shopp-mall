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
 * @Date 2020-04-13 16:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain=true)
public class PmsSkuImage implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    /**
     * 商品id
     */
    private String skuId;
    /**
     * 图片名称（冗余）
     */
    private String imgName;
    /**
     * 图片路径(冗余)
     */
    private String imgUrl;
    /**
     * 商品图片id
     */
    private String productImgId;
    /**
     * 是否默认
     */
    private String isDefault;
}
