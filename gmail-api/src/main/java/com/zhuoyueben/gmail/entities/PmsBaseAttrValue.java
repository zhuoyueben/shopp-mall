package com.zhuoyueben.gmail.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-09 11:36
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Accessors(chain=true)
public class PmsBaseAttrValue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected String id;
    /**
     * 属性值名称
     */
    protected String valueName;
    /**
     * 属性id
     */
    protected String attrId;
    /**
     * 启用：1 停用：0 1
     */
    protected String isEnabled;
}
