package com.zhuoyueben.gmail.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-06-06 18:36
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OmsCompanyAddress {
   private String id;
    /**
     * 地址名称
     */
   private String addressName;
    /**
     * 默认发货地址：0->否；1->是
     */
   private Integer sendStatus;
    /**
     * 是否默认收货地址：0->否；1->是
     */
   private Integer receiveStatus;
    /**
     * 收发货人姓名
     */
   private String name;
    /**
     * 收货人电话
     */
   private String phone;
    /**
     * 省/直辖市
     */
   private String province;
    /**
     * 市
     */
   private String city;
    /**
     * 区
     */
   private String region;
    /**
     * 详细地址
     */
   private String detailAddress;
}
