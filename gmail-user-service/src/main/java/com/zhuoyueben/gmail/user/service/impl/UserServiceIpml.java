package com.zhuoyueben.gmail.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.zhuoyueben.gmail.util.RedisUtil;
import com.zhuoyueben.gmail.entities.UmsMember;
import com.zhuoyueben.gmail.entities.UmsMemberReceiveAddress;
import com.zhuoyueben.gmail.service.MemberService;
import com.zhuoyueben.gmail.user.dao.UmsMemberDao;
import com.zhuoyueben.gmail.user.dao.UmsMemberReceiveAddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.List;


/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-08 10:56
 */
@Service
public class UserServiceIpml implements MemberService {
    @Autowired
    private UmsMemberDao umsMemberDao;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UmsMemberReceiveAddressDao umsMemberReceiveAddressDao;
    @Override
    public List<UmsMember> getAllUser() {
        return umsMemberDao.selectAll();
    }

    @Override
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {
        // 封装的参数对象
        UmsMemberReceiveAddress umsMemberReceiveAddress = new UmsMemberReceiveAddress();
        umsMemberReceiveAddress.setMemberId(memberId);
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = umsMemberReceiveAddressDao.select(umsMemberReceiveAddress);

        return umsMemberReceiveAddresses;
    }


    private final String userKey_prefix="user:";
    private final String userKeyInfo_suffix=":info";
    private final int userKeyTimeOut=24*60*60;
    @Override
    public UmsMember login(UmsMember umsMember) {
        String password = umsMember.getPassword();
        String digestAsHex = DigestUtils.md5DigestAsHex(password.getBytes());
        umsMember.setPassword(digestAsHex);

        String userKey=userKey_prefix+umsMember.getNickname()+":"+umsMember.getPassword()+userKeyInfo_suffix;
        UmsMember memberExists =null;

        Jedis jedis = redisUtil.getJedis();
        String userKeyInfo = jedis.get(userKey);
        if(StringUtils.isNotBlank(userKeyInfo)){
            memberExists = JSON.parseObject(userKeyInfo, UmsMember.class);
        }else{
            memberExists=umsMemberDao.selectOne(umsMember);
            String jsonString = JSON.toJSONString(memberExists);
            jedis.setex(userKey,userKeyTimeOut,jsonString);
        }
        jedis.close();
        return memberExists;
    }

    @Override
    public void addUserToken(String token, String memberId) {
        Jedis jedis = redisUtil.getJedis();

        jedis.setex("user:"+memberId+":token",60*60*2,token);

        jedis.close();
    }

    @Override
    public UmsMember checkOauthUser(UmsMember umsCheck) {
        UmsMember umsMember = umsMemberDao.selectOne(umsCheck);
        return umsMember;
    }

    @Override
    public UmsMember addOauthUser(UmsMember umsMember) {
        int i = umsMemberDao.insertSelective(umsMember);
        return umsMember;
    }

    @Override
    public UmsMemberReceiveAddress getReceiveAddressById(String receiveAddressId) {
        UmsMemberReceiveAddress umsMemberReceiveAddress=new UmsMemberReceiveAddress().setId(receiveAddressId);
        UmsMemberReceiveAddress ReceiveAddress = umsMemberReceiveAddressDao.selectOne(umsMemberReceiveAddress);
        return ReceiveAddress;
    }


}
