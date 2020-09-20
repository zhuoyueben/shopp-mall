package com.zhuoyueben.gmail.manage.service.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author TeaBen
 * @Date 2020-04-10 15:52
 */
public class demo {


    public static void main(String[] args) {
        List<Integer> num1 = Arrays.asList(3,  2, 31, 7, 35, 5);
        List<Integer> num2 = Arrays.asList(3,  2, 31, 7);
        List arrList = new ArrayList(num1);
        for (Integer ps:num1){
            int i = num2.stream().filter(s -> s == ps).collect(Collectors.toList()).size();
            System.out.println(i);
            if(i==0){
                arrList.remove(ps);
            }
        }
        System.out.println(arrList);
    }
}
