package com.zhuoyueben.gmail.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhuoyueben.gmail.entities.PmsBaseCatalog1;
import com.zhuoyueben.gmail.entities.PmsBaseCatalog2;
import com.zhuoyueben.gmail.entities.PmsBaseCatalog3;
import com.zhuoyueben.gmail.manage.dao.Catelog1Dao;
import com.zhuoyueben.gmail.manage.dao.Catelog2Dao;
import com.zhuoyueben.gmail.manage.dao.Catelog3Dao;
import com.zhuoyueben.gmail.service.CatelogService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-08 19:22
 */
@Service
public class CatelogServiceImpl implements CatelogService {
    @Autowired
    private Catelog1Dao catelog1Dao;
    @Autowired
    private Catelog2Dao catelog2Dao;
    @Autowired
    private Catelog3Dao catelog3Dao;
    @Override
    public List<PmsBaseCatalog1> getCateLog1() {
        return catelog1Dao.selectAll();
    }

    @Override
    public List<PmsBaseCatalog2> getCateLog2(String cateLog1Id) {
        Example example = new Example(PmsBaseCatalog2.class);//实例化
        Example.Criteria criteria = example.createCriteria();
        criteria.orEqualTo("catalog1Id", cateLog1Id);
        List<PmsBaseCatalog2> catalog2s = catelog2Dao.selectByExample(example);

        /*PmsBaseCatalog2 catalog2 = new PmsBaseCatalog2().setCatalog1Id(cateLog1Id);
        List<PmsBaseCatalog2> catalog2s = catelog2Dao.select(catalog2);*/
        return catalog2s;
    }

    @Override
    public List<PmsBaseCatalog3> getCateLog3(String cateLog2Id) {
        PmsBaseCatalog3 catalog3 = new PmsBaseCatalog3().setCatalog2Id(cateLog2Id);
        List<PmsBaseCatalog3> catalog3s = catelog3Dao.select(catalog3);
        return catalog3s;
    }
}
