package com.zhuoyueben.gmail.service;

import com.zhuoyueben.gmail.entities.PmsBaseAttrInfo;

import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-08 22:03
 */
public interface PmsBaseAttrInfoService {
    List<PmsBaseAttrInfo> getBaseAttrInfo(String catelog3Id);

    int saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);
}
