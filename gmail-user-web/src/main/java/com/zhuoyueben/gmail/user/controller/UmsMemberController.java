package com.zhuoyueben.gmail.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhuoyueben.gmail.entities.UmsMember;
import com.zhuoyueben.gmail.entities.UmsMemberReceiveAddress;
import com.zhuoyueben.gmail.service.MemberService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-08 11:30
 */
@Controller
public class UmsMemberController {
    @Reference
    MemberService userService;

    @RequestMapping("getReceiveAddressByMemberId")
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId){

        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = userService.getReceiveAddressByMemberId(memberId);

        return umsMemberReceiveAddresses;
    }


    @RequestMapping("getAllUser")
    @ResponseBody
    public List<UmsMember> getAllUser(){

        List<UmsMember> umsMembers = userService.getAllUser();

        return umsMembers;
    }
}
