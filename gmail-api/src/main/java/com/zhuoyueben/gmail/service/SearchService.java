package com.zhuoyueben.gmail.service;

import com.zhuoyueben.gmail.entities.PmsSearchParam;
import com.zhuoyueben.gmail.entities.PmsSearchSkuInfo;

import java.io.IOException;
import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-05-28 11:32
 */
public interface SearchService {
    List<PmsSearchSkuInfo> list(PmsSearchParam pmsSearchParam) throws IOException;

    void incrHotScore(String skuId);
}
