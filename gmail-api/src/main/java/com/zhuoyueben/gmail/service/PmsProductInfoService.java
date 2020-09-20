package com.zhuoyueben.gmail.service;

import com.zhuoyueben.gmail.entities.PmsBaseSaleAttr;
import com.zhuoyueben.gmail.entities.PmsProductInfo;
import com.zhuoyueben.gmail.entities.PmsProductSaleAttr;

import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-11 14:53
 */
public interface PmsProductInfoService {
    List<PmsProductInfo> getSpuList(String catalog3Id);

    List<PmsBaseSaleAttr> getSaleAttrList();

    List<PmsProductSaleAttr> getSpuSaleAttrList(String spuId);

    int saveSpuInfo(PmsProductInfo productInfo);

    PmsProductInfo getSpuById(String productId);

    List<PmsProductSaleAttr> getSpuSaleAttrById(String productId);

    List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String productId,String skuId);

}
