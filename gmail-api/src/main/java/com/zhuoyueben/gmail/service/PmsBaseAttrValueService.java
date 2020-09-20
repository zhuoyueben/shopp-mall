package com.zhuoyueben.gmail.service;

import com.zhuoyueben.gmail.entities.PmsBaseAttrValue;

import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-09 11:35
 */
public interface PmsBaseAttrValueService {
    List<PmsBaseAttrValue> getAttrValueList(String attrId);
}
