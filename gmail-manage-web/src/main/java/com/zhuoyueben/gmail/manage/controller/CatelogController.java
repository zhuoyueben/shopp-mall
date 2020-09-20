package com.zhuoyueben.gmail.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhuoyueben.gmail.entities.PmsBaseCatalog1;
import com.zhuoyueben.gmail.entities.PmsBaseCatalog2;
import com.zhuoyueben.gmail.entities.PmsBaseCatalog3;
import com.zhuoyueben.gmail.service.CatelogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-08 18:49
 */
@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class CatelogController {
    @Reference
    CatelogService catelogService;
    @RequestMapping(value = "getCatalog1")
    @ResponseBody
    public List<PmsBaseCatalog1> getCateLog1(){
        List<PmsBaseCatalog1> Catelog1s=catelogService.getCateLog1();
        return Catelog1s;
    }
    @RequestMapping(value = "getCatalog2")
    @ResponseBody
    public List<PmsBaseCatalog2> getCateLog2(String catalog1Id){
        List<PmsBaseCatalog2> Catelog2s=catelogService.getCateLog2(catalog1Id);
        return Catelog2s;
    }
    @RequestMapping(value = "getCatalog3")
    @ResponseBody
    public List<PmsBaseCatalog3> getCateLog3(String cataLog2Id){
        List<PmsBaseCatalog3> Catelog3s=catelogService.getCateLog3(cataLog2Id);
        return Catelog3s;
    }

}
