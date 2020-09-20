package com.zhuoyueben.gmail.service;

import com.zhuoyueben.gmail.entities.PmsBaseAttrInfo;

import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-05-30 17:29
 */
public interface AttrService {
    List<PmsBaseAttrInfo> getAttrValueByValueId(String attrValues);
}
