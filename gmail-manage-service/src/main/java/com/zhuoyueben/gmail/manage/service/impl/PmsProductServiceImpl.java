package com.zhuoyueben.gmail.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.sun.xml.internal.fastinfoset.stax.events.Util;
import com.zhuoyueben.gmail.entities.*;
import com.zhuoyueben.gmail.manage.dao.*;
import com.zhuoyueben.gmail.service.PmsProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-11 14:55
 */
@Service
public class PmsProductServiceImpl implements PmsProductInfoService {
    @Autowired
    PmsProductInfoDao productInfoDao;
    @Autowired
    PmsProductSaleAttrDao productSaleAttrDao;
    @Autowired
    PmsProductImageDao productImageDao;
    @Autowired
    PmsBaseSaleAttrDao baseSaleAttrDao;
    @Autowired
    PmsProductSaleAttrValueDao productSaleAttrValueDao;

    @Override
    public List<PmsProductInfo> getSpuList(String catalog3Id) {
        PmsProductInfo productInfo = new PmsProductInfo().setCatalog3Id(catalog3Id);
        return productInfoDao.select(productInfo);
    }

    @Override
    public List<PmsBaseSaleAttr> getSaleAttrList() {
        return baseSaleAttrDao.selectAll();
    }

    @Override
    public List<PmsProductSaleAttr> getSpuSaleAttrList(String spuId) {
        PmsProductSaleAttr productSaleAttr = new PmsProductSaleAttr().setProductId(spuId);
        List<PmsProductSaleAttr> productSaleAttrs = productSaleAttrDao.select(productSaleAttr);
        for (PmsProductSaleAttr psa:productSaleAttrs){
            PmsProductSaleAttrValue productSaleAttrValue = new PmsProductSaleAttrValue().setSaleAttrId(psa.getSaleAttrId()).setProductId(psa.getProductId());
            List<PmsProductSaleAttrValue> saleAttrValues = productSaleAttrValueDao.select(productSaleAttrValue);
            psa.setSpuSaleAttrValueList(saleAttrValues);
        }
        return productSaleAttrs;
    }

    @Override
    public int saveSpuInfo(PmsProductInfo productInfo) {
        if(Util.isEmptyString(productInfo.getId())){
            int selective = productInfoDao.insertSelective(productInfo);
            List<PmsProductSaleAttr> saleAttrList = productInfo.getSpuSaleAttrList();
            List<PmsProductImage> imageList = productInfo.getSpuImageList();
            if(saleAttrList.size()>0){
                for (PmsProductSaleAttr pps:saleAttrList) {
                    pps.setProductId(productInfo.getId());
                    productSaleAttrDao.insertSelective(pps);
                    List<PmsProductSaleAttrValue> saleAttrValueList = pps.getSpuSaleAttrValueList();
                    if(saleAttrValueList.size()>0){
                        for(PmsProductSaleAttrValue psav:saleAttrValueList){
                            psav.setProductId(productInfo.getId());
                            productSaleAttrValueDao.insertSelective(psav);
                        }
                    }
                }
            }
            if(imageList.size()>0){
                for (PmsProductImage ppi:imageList) {
                    ppi.setProductId(productInfo.getId());
                    productImageDao.insertSelective(ppi);
                }
            }
            productInfo.getId();
        }

        return 0;
    }

    @Override
    public PmsProductInfo getSpuById(String productId) {
        PmsProductInfo productInfo = new PmsProductInfo().setId(productId);
        PmsProductInfo pmsProductInfo = productInfoDao.selectByPrimaryKey(productInfo);
        PmsProductSaleAttrValue productSaleAttrValue = new PmsProductSaleAttrValue().setProductId(productId);
        List<PmsProductSaleAttrValue> productSaleAttrValues = productSaleAttrValueDao.select(productSaleAttrValue);
        PmsProductSaleAttr productSaleAttr=new PmsProductSaleAttr().setProductId(productId);
        List<PmsProductSaleAttr> productSaleAttrs = productSaleAttrDao.select(productSaleAttr);

        return null;
    }

    @Override
    public List<PmsProductSaleAttr> getSpuSaleAttrById(String productId) {
        PmsProductSaleAttrValue productSaleAttrValue = new PmsProductSaleAttrValue().setProductId(productId);
        List<PmsProductSaleAttrValue> productSaleAttrValues = productSaleAttrValueDao.select(productSaleAttrValue);
        PmsProductSaleAttr productSaleAttr=new PmsProductSaleAttr().setProductId(productId);
        List<PmsProductSaleAttr> productSaleAttrs = productSaleAttrDao.select(productSaleAttr);
        for (PmsProductSaleAttr psa:productSaleAttrs){
            List<PmsProductSaleAttrValue> saleAttrValues = productSaleAttrValues.stream().filter(p -> p.getProductId().equals(psa.getProductId()) && p.getSaleAttrId().equals(psa.getSaleAttrId())).collect(Collectors.toList());
            psa.setSpuSaleAttrValueList(saleAttrValues);
        }
        return productSaleAttrs;
    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String productId,String skuId) {
        List<PmsProductSaleAttr> saleAttrs=productSaleAttrDao.spuSaleAttrListCheckBySku(productId,skuId);
        return saleAttrs;
    }
}
