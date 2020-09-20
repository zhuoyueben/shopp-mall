package com.zhuoyueben.gmail.service;

import javax.jms.MapMessage;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-06-19 20:45
 */
public interface PaymentService {
    void sendPaymentResult(String orderId, String result);

    void handlePaymentResult(MapMessage mapMessage);
}
