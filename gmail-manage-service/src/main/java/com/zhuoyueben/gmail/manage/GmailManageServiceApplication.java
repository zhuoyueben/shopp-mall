package com.zhuoyueben.gmail.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.zhuoyueben.gmail.manage.dao")
@ComponentScan(value = "com.zhuoyueben.gmail")
public class GmailManageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmailManageServiceApplication.class, args);
    }

}
