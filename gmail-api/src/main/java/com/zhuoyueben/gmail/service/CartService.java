package com.zhuoyueben.gmail.service;

import com.zhuoyueben.gmail.entities.OmsCartItem;

import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-06-08 15:37
 */
public interface CartService {
    OmsCartItem ifCartExistByUser(String memberId, String skuId);

    void addCart(OmsCartItem omsCartItem);

    void updateCart(OmsCartItem omsCartItemFromDb);

    void flushCartCache(String memberId);

    List<OmsCartItem> cartList(String memberId);

    void checkCart(OmsCartItem omsCartItem);
}
