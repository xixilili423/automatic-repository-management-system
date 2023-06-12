package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(scanBasePackages = "com" )
//@Configuration
//@ComponentScan({"com.service"})
@MapperScan({"com.mapper"})
public class DepositApplication {

    public static void main(String[] args) {

        SpringApplication.run(DepositApplication.class, args);
    }

}
