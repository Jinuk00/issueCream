package com.clova.issuecream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class IssueCreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(IssueCreamApplication.class, args);
    }

}
