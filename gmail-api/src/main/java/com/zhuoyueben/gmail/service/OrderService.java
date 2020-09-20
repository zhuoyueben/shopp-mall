package com.zhuoyueben.gmail.service;

import com.zhuoyueben.gmail.entities.OmsOrder;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-06-12 10:57
 */
public interface OrderService {
    String genTradeCode(String memberId);

    String checkTradeCode(String memberId,String tradeCode);

    void saveOrder(OmsOrder omsOrder);
}
