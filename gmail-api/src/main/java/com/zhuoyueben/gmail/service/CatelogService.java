package com.zhuoyueben.gmail.service;

import com.zhuoyueben.gmail.entities.PmsBaseCatalog1;
import com.zhuoyueben.gmail.entities.PmsBaseCatalog2;
import com.zhuoyueben.gmail.entities.PmsBaseCatalog3;

import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-08 19:09
 */
public interface CatelogService {
    List<PmsBaseCatalog1> getCateLog1();

    List<PmsBaseCatalog2> getCateLog2(String cateLog1Id);

    List<PmsBaseCatalog3> getCateLog3(String cateLog2Id);
}
