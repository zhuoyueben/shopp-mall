package com.zhuoyueben.gmail.service;

import com.zhuoyueben.gmail.entities.UmsMember;
import com.zhuoyueben.gmail.entities.UmsMemberReceiveAddress;

import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-05 14:14
 */
public interface MemberService {
    List<UmsMember> getAllUser();

    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);

    UmsMember login(UmsMember umsMember);

    void addUserToken(String token, String memberId);

    UmsMember checkOauthUser(UmsMember umsCheck);

    UmsMember addOauthUser(UmsMember umsMember);

    UmsMemberReceiveAddress getReceiveAddressById(String receiveAddressId);
}
