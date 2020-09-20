package com.zhuoyueben.gmail.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-05-30 22:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class PmsSearchCrumbs implements Serializable {
    private String urlParam;
    private String valueName;
    private String valueId;
}
