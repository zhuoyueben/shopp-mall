package com.zhuoyueben.gmail.manage.dao;

import com.zhuoyueben.gmail.entities.PmsBaseAttrInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-08 22:11
 */
public interface PmsBaseAttrInfoDao extends Mapper<PmsBaseAttrInfo> {
    List<PmsBaseAttrInfo> getAttrValueByValueId(@Param("attrValues") String attrValues);
}
