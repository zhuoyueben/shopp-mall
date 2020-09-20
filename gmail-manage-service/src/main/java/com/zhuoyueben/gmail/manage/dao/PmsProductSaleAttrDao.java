package com.zhuoyueben.gmail.manage.dao;

import com.zhuoyueben.gmail.entities.PmsProductSaleAttr;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-11 15:28
 */
public interface PmsProductSaleAttrDao extends Mapper<PmsProductSaleAttr> {
    List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(@Param("productId") String productId,@Param("skuId") String skuId);
}
