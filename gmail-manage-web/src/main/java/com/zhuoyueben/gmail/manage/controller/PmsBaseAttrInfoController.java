package com.zhuoyueben.gmail.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhuoyueben.gmail.entities.PmsBaseAttrInfo;
import com.zhuoyueben.gmail.entities.PmsBaseAttrValue;
import com.zhuoyueben.gmail.service.PmsBaseAttrInfoService;
import com.zhuoyueben.gmail.service.PmsBaseAttrValueService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-08 21:47
 */
@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class PmsBaseAttrInfoController {
    @Reference
    private PmsBaseAttrInfoService pmsBaseAttrInfoService;
    @Reference
    private PmsBaseAttrValueService pmsBaseAttrValueService;

    @RequestMapping(value = "/attrInfoList")
    @ResponseBody
    public List<PmsBaseAttrInfo> getBaseAttrInfolist(String catalog3Id){
        return pmsBaseAttrInfoService.getBaseAttrInfo(catalog3Id);
    }
    @RequestMapping(value = "/getAttrInfoList")
    @ResponseBody
    public List<PmsBaseAttrInfo> getAttrInfoList(String catalog3Id){
        return pmsBaseAttrInfoService.getBaseAttrInfo(catalog3Id);
    }
    @RequestMapping(value = "/saveAttrInfo")
    @ResponseBody
    public int saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo){
        return pmsBaseAttrInfoService.saveAttrInfo(pmsBaseAttrInfo);
    }

    @RequestMapping(value = "/getAttrValueList")
    @ResponseBody
    public List<PmsBaseAttrValue> getAttrValueList(String attrId){
        return pmsBaseAttrValueService.getAttrValueList(attrId);
    }
}
