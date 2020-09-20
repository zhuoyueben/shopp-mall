package com.zhuoyueben.gmail.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.zhuoyueben.gmail.util.RedisUtil;
import com.zhuoyueben.gmail.util.RedissonConfig;
import com.zhuoyueben.gmail.entities.*;
import com.zhuoyueben.gmail.manage.dao.*;
import com.zhuoyueben.gmail.service.PmsSkuService;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-13 16:39
 */
@Service
public class PmsSkuServiceImpl implements PmsSkuService {
    @Autowired
    PmsSkuImageDao pmsSkuImageDao;
    @Autowired
    PmsProductImageDao pmsProductImageDao;
    @Autowired
    PmsSkuInfoDao pmsSkuInfoDao;
    @Autowired
    PmsSkuAttrValueDao pmsSkuAttrValueDao;
    @Autowired
    PmsSkuSaleAttrValueDao pmsSkuSaleAttrValueDao;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    RedissonConfig redissonConfig;
    @Override
    public List<PmsProductImage> spuImageList(String spuId) {
        PmsProductImage pmsProductImage = new PmsProductImage().setProductId(spuId);
        return pmsProductImageDao.select(pmsProductImage);
    }

    @Override
    public void saveSkuInfo(PmsSkuInfo skuInfo) {
        List<PmsSkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
        List<PmsSkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        List<PmsSkuImage> skuImageList = skuInfo.getSkuImageList();
        int selective = pmsSkuInfoDao.insertSelective(skuInfo);
        String infoId = skuInfo.getId();
        if(skuAttrValueList.size()>0){
            for(PmsSkuAttrValue sav:skuAttrValueList){
                sav.setSkuId(infoId);
                int i = pmsSkuAttrValueDao.insertSelective(sav);
            }
        }
        if(skuSaleAttrValueList.size()>0){
            for (PmsSkuSaleAttrValue ssav:skuSaleAttrValueList){
                ssav.setSkuId(infoId);
                pmsSkuSaleAttrValueDao.insertSelective(ssav);
            }
        }
        if(skuImageList.size()>0){
            for(PmsSkuImage psi:skuImageList){
                psi.setSkuId(infoId);
                pmsSkuImageDao.insertSelective(psi);
            }
        }
    }
    public PmsSkuInfo getSkuByIdFromMysql(String skuId) {
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo().setId(skuId);
        PmsSkuInfo skuInfo = pmsSkuInfoDao.selectByPrimaryKey(pmsSkuInfo);

        PmsSkuImage skuImage = new PmsSkuImage().setSkuId(skuId);
        List<PmsSkuImage> skuImages = pmsSkuImageDao.select(skuImage);
        skuInfo.setSkuImageList(skuImages);

        PmsSkuAttrValue skuAttrValue = new PmsSkuAttrValue().setSkuId(skuId);
        List<PmsSkuAttrValue> skuAttrValues = pmsSkuAttrValueDao.select(skuAttrValue);
        skuInfo.setSkuAttrValueList(skuAttrValues);

        PmsSkuSaleAttrValue skuSaleAttrValue = new PmsSkuSaleAttrValue().setSkuId(skuId);
        List<PmsSkuSaleAttrValue> saleAttrValues = pmsSkuSaleAttrValueDao.select(skuSaleAttrValue);
        skuInfo.setSkuSaleAttrValueList(saleAttrValues);
        return skuInfo;
    }
    public PmsSkuInfo getSkuById(String skuId) {
        PmsSkuInfo skuInfo = new PmsSkuInfo();
        // 链接缓存
        Jedis jedis = redisUtil.getJedis();
        // 查询缓存
        String skuKey = "sku:"+skuId+":info";
        String skuJson = jedis.get(skuKey);
        if(StringUtils.isNoneBlank(skuJson)){
            skuInfo= JSON.parseObject(skuJson,PmsSkuInfo.class);
        }else {
            // 如果缓存中没有，查询mysql

            // 设置分布式锁
            String OK = jedis.set("sku:" + skuId + ":lock", "1", "nx", "px", 10);
            if(StringUtils.isNotBlank(OK)&&OK.equals("OK")){
                // 设置成功，有权在10秒的过期时间内访问数据库
                skuInfo =  getSkuByIdFromMysql(skuId);
                if(skuInfo!=null){
                    // mysql查询结果存入redis
                    jedis.set("sku:"+skuId+":info",JSON.toJSONString(skuInfo));
                }else{
                    // 数据库中不存在该sku
                    // 为了防止缓存穿透将，null或者空字符串值设置给redis
                    jedis.setex("sku:"+skuId+":info",60*3,JSON.toJSONString(""));
                }
            }else{
                // 设置失败，自旋（该线程在睡眠几秒后，重新尝试访问本方法）
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return getSkuById(skuId);
            }
        }
        return skuInfo;
    }

    @Override
    public List<PmsSkuInfo> getAllSku(String catalog3Id) {
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo().setCatalog3Id(catalog3Id);
        List<PmsSkuInfo> pmsSkuInfos = pmsSkuInfoDao.select(pmsSkuInfo);
        for (PmsSkuInfo ps:pmsSkuInfos) {
            String id = ps.getId();
            PmsSkuAttrValue pmsSkuAttrValue = new PmsSkuAttrValue().setSkuId(id);
            List<PmsSkuAttrValue> attrValues = pmsSkuAttrValueDao.select(pmsSkuAttrValue);
            ps.setSkuAttrValueList(attrValues);
        }
        return pmsSkuInfos;
    }

    @Override
    public String testRedisson(){
        Jedis jedis = redisUtil.getJedis();
        RLock lock = redissonConfig.redissonClient().getLock("flag");
//        RLock lock = redissonClient.getLock("lock");// 声明锁
        lock.lock();//上锁
        try {
            String v = jedis.get("k");
            if (StringUtils.isBlank(v)) {
                v = "1";
            }
            System.out.println("->" + v);
            jedis.set("k", (Integer.parseInt(v) + 1) + "");
        }finally {
            jedis.close();
            lock.unlock();// 解锁
        }
        return "success";
    }

    @Override
    public boolean checkPrice(String productSkuId, BigDecimal price) {
        boolean b = false;

        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        pmsSkuInfo.setId(productSkuId);
        PmsSkuInfo pmsSkuInfo1 = pmsSkuInfoDao.selectOne(pmsSkuInfo);

        BigDecimal productPrice = pmsSkuInfo1.getPrice();

        if(price.compareTo(productPrice)==0){
            b = true;
        }


        return b;
    }
}
