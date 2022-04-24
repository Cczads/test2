package com.example.democommerce1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.democommerce1.dao")
public class Democommerce1Application {

    public static void main(String[] args) {
        SpringApplication.run(Democommerce1Application.class, args);
    }

}
