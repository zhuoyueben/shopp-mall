package com.zhuoyueben.gmail.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-05-28 12:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class PmsSearchParam implements Serializable {
    private String catalog3Id;

    private String keyword;

    private String[] valueId;

//    private List<PmsSkuAttrValue> skuAttrValueList;
}
