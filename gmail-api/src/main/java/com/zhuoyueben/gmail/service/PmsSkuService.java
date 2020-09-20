package com.zhuoyueben.gmail.service;

import com.zhuoyueben.gmail.entities.PmsProductImage;
import com.zhuoyueben.gmail.entities.PmsSkuImage;
import com.zhuoyueben.gmail.entities.PmsSkuInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-13 16:30
 */
public interface PmsSkuService {
    List<PmsProductImage> spuImageList(String spuId);

    void saveSkuInfo(PmsSkuInfo skuInfo);

    PmsSkuInfo getSkuById(String skuId);

    List<PmsSkuInfo> getAllSku(String catalog3Id);

    String testRedisson();

    boolean checkPrice(String productSkuId, BigDecimal price);
}
