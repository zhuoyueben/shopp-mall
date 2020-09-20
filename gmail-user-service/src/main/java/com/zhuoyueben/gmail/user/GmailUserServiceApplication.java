package com.zhuoyueben.gmail.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.zhuoyueben.gmail.user.dao")
@ComponentScan(value = "com.zhuoyueben.gmail")
public class GmailUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmailUserServiceApplication.class, args);
    }

}
