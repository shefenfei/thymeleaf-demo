package com.fisher.arch.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.fisher.arch.*" ,exclude = DataSourceAutoConfiguration.class)
//@MapperScan(basePackages = "com.fisher.arch.dao")
@EnableSwagger2
//@EnableAdminServer
public class ThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThymeleafApplication.class , args);
    }

}
