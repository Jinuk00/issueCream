package com.clova.issuecream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class IssueCreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(IssueCreamApplication.class, args);
    }

}
