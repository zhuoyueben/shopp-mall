package com.zhuoyueben.gmail.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhuoyueben.gmail.entities.PmsProductImage;
import com.zhuoyueben.gmail.entities.PmsSkuImage;
import com.zhuoyueben.gmail.entities.PmsSkuInfo;
import com.zhuoyueben.gmail.service.PmsSkuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-13 16:28
 */
@Controller
@CrossOrigin
public class PmsSkuController {
    @Reference
    PmsSkuService pmsSkuService;
    @RequestMapping(value = "spuImageList")
    @ResponseBody
    public List<PmsProductImage> spuImageList(String spuId){
        return pmsSkuService.spuImageList(spuId);
    }
    @RequestMapping(value = "/saveSkuInfo")
    @ResponseBody
    public String saveSkuInfo(@RequestBody PmsSkuInfo skuForm){
        System.out.println(skuForm);
        pmsSkuService.saveSkuInfo(skuForm);
        return "success";
    }
}
