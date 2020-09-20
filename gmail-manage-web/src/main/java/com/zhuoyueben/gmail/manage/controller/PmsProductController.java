package com.zhuoyueben.gmail.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhuoyueben.gmail.entities.PmsBaseSaleAttr;
import com.zhuoyueben.gmail.entities.PmsProductInfo;
import com.zhuoyueben.gmail.entities.PmsProductSaleAttr;
import com.zhuoyueben.gmail.manage.util.UploadImageUtil;
import com.zhuoyueben.gmail.service.PmsProductInfoService;
import org.csource.common.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-11 14:49
 */
@Controller
@CrossOrigin
public class PmsProductController {
    @Reference
    PmsProductInfoService productInfoService;
    @RequestMapping(value = "/spuList")
    @ResponseBody
    public List<PmsProductInfo> getSpuList(String catalog3Id){
        return productInfoService.getSpuList(catalog3Id);
    }
    @RequestMapping(value = "/baseSaleAttrList")
    @ResponseBody
    public List<PmsBaseSaleAttr> getSaleAttrList(){
        return productInfoService.getSaleAttrList();
    }
    @RequestMapping(value = "/spuSaleAttrList")
    @ResponseBody
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId){
        return productInfoService.getSpuSaleAttrList(spuId);
    }
    @RequestMapping(value = "/fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile) throws IOException, MyException {
        String uploadurl = new UploadImageUtil().uploadImage(multipartFile);
        return uploadurl;
    }
    @RequestMapping(value = "/saveSpuInfo")
    @ResponseBody
    private String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo){
        productInfoService.saveSpuInfo(pmsProductInfo);
        return "success";
    }
}
