package com.zhuoyueben.gmail.user.dao;

import com.zhuoyueben.gmail.entities.UmsMember;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-08 10:54
 */
public interface UmsMemberDao extends Mapper<UmsMember> {
    List<UmsMember> selectAllUser();
}
