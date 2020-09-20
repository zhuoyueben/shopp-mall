package com.zhuoyueben.gmail.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhuoyueben.gmail.entities.PmsBaseAttrInfo;
import com.zhuoyueben.gmail.entities.PmsBaseAttrValue;
import com.zhuoyueben.gmail.manage.dao.PmsBaseAttrInfoDao;
import com.zhuoyueben.gmail.manage.dao.PmsBaseAttrValueDao;
import com.zhuoyueben.gmail.service.AttrService;
import com.zhuoyueben.gmail.service.PmsBaseAttrInfoService;
import com.zhuoyueben.gmail.service.PmsBaseAttrValueService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-08 22:08
 */
@Service
public class PmsBaseAttrInfoServiceImpl implements PmsBaseAttrInfoService,PmsBaseAttrValueService,AttrService {
    @Autowired
    PmsBaseAttrInfoDao pmsBaseAttrInfoDao;
    @Autowired
    PmsBaseAttrValueDao pmsBaseAttrValueDao;
    @Override
    public List<PmsBaseAttrInfo> getBaseAttrInfo(String catelog3Id) {
        PmsBaseAttrInfo baseAttrInfo = new PmsBaseAttrInfo().setCatalog3Id(catelog3Id);
        List<PmsBaseAttrInfo> baseAttrInfos = pmsBaseAttrInfoDao.select(baseAttrInfo);
        for(PmsBaseAttrInfo bai:baseAttrInfos){
            List<PmsBaseAttrValue> pmsBaseAttrValues = new ArrayList<PmsBaseAttrValue>();
            PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
            pmsBaseAttrValue.setAttrId(bai.getId());
            pmsBaseAttrValues = pmsBaseAttrValueDao.select(pmsBaseAttrValue);
            bai.setAttrValueList(pmsBaseAttrValues);

            /*PmsBaseAttrValue baseAttrValue = new PmsBaseAttrValue().setAttrId(bai.getId());
            List<PmsBaseAttrValue> baseAttrValues = pmsBaseAttrValueDao.select(baseAttrValue);
            bai.setAttrValueList(baseAttrValues);*/
        }
        return baseAttrInfos;
    }

    @Override
    public int saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        /*if(pmsBaseAttrInfo.getAttrName()!=null){
            int insert =1;
            if(pmsBaseAttrInfoDao.select(pmsBaseAttrInfo).size()==0){
                insert=pmsBaseAttrInfoDao.insertSelective(pmsBaseAttrInfo);
            }
            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
            if(attrValueList.size()>0){
                if(attrValueList.size()<getAttrValueList(attrValueList.get(0).getAttrId()).size()){
                    List<PmsBaseAttrValue> delete=getAttrValueList(attrValueList.get(0).getAttrId());
                    for (PmsBaseAttrValue ps:getAttrValueList(attrValueList.get(0).getAttrId())){
                        int i = attrValueList.stream().filter(s -> s.getId().equals(ps.getId())).collect(Collectors.toList()).size();
                        if(i==0){
                            delete.remove(ps);
                        }
                    }
                    if(delete.size()>0&&delete!=null){
                        for(PmsBaseAttrValue ds:delete){
                            deleteAttrValue(ds);
                        }
                    }
                }
                attrValueList = attrValueList.stream().filter(s->s.getId()==null).collect(Collectors.toList());
                for(PmsBaseAttrValue s:attrValueList){
                    s.setAttrId(pmsBaseAttrInfoDao.select(pmsBaseAttrInfo).get(0).getId());
                    pmsBaseAttrValueDao.insert(s);
                }
            }

            return insert;
        }
        return 0;*/
        String id = pmsBaseAttrInfo.getId();
        if(StringUtils.isBlank(id)){
            // id为空，保存
            // 保存属性
            pmsBaseAttrInfoDao.insertSelective(pmsBaseAttrInfo);//insert insertSelective 是否将null插入数据库

            // 保存属性值
            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());

                pmsBaseAttrValueDao.insertSelective(pmsBaseAttrValue);
            }
        }else{
            // id不空，修改

            // 属性修改
            Example example = new Example(PmsBaseAttrInfo.class);
            example.createCriteria().andEqualTo("id",pmsBaseAttrInfo.getId());
            pmsBaseAttrInfoDao.updateByExampleSelective(pmsBaseAttrInfo,example);


            // 属性值修改
            // 按照属性id删除所有属性值
            PmsBaseAttrValue pmsBaseAttrValueDel = new PmsBaseAttrValue();
            pmsBaseAttrValueDel.setAttrId(pmsBaseAttrInfo.getId());
            pmsBaseAttrValueDao.delete(pmsBaseAttrValueDel);

            // 删除后，将新的属性值插入
            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValueDao.insertSelective(pmsBaseAttrValue);
            }

        }


        return 1;
    }
    public void deleteAttrValue(PmsBaseAttrValue pmsBaseAttrValue){
        pmsBaseAttrValueDao.delete(pmsBaseAttrValue);
    }
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        PmsBaseAttrValue attrValue = new PmsBaseAttrValue().setAttrId(attrId);
        return pmsBaseAttrValueDao.select(attrValue);
    }

    @Override
    public List<PmsBaseAttrInfo> getAttrValueByValueId(String attrValues) {
        List<PmsBaseAttrInfo> attrInfoByValueId = pmsBaseAttrInfoDao.getAttrValueByValueId(attrValues);
        return attrInfoByValueId;
    }
}
