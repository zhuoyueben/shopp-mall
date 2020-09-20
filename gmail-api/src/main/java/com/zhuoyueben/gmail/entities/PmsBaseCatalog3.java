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
 * @Date 2020-04-08 18:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain=true)
public class PmsBaseCatalog3 implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected String id;
    /**
     * 分类名称
     */
    @Column
    protected String name;

    @Column
    protected String catalog2Id;

    @Transient
    private List<PmsBaseAttrInfo> AttrInfolist;
}
