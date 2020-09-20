package com.zhuoyueben.gmail.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-08 21:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain=true)
public class PmsBaseAttrInfo implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected String id;
    /**
     * 属性名称
     */
    @Column
    protected String attrName;
    @Column
    protected String catalog3Id;
    /**
     * 启用：1 停用：0
     */
    @Column
    protected String isEnabled;

    @Transient
    private List<PmsBaseAttrValue> attrValueList;
}
