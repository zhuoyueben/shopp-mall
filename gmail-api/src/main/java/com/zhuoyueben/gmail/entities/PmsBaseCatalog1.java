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
 * @Date 2020-04-08 18:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain=true)
public class PmsBaseCatalog1 implements Serializable {
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
    @Transient
    private List<PmsBaseCatalog2> Catalog2list;
}
