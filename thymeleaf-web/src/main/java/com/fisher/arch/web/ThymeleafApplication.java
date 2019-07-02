package com.fisher.arch.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.fisher.arch.*")
@MapperScan(basePackages = "com.fisher.arch.dao")
@EnableSwagger2
//最佳实践: Redis 跟 es的repository包 分别开来
@EnableRedisRepositories(basePackages = "com.fisher.arch.dao.repository.redis")
@EnableElasticsearchRepositories(basePackages = "com.fisher.arch.dao.repository.es")
//@EnableAdminServer
public class ThymeleafApplication {


    public static void main(String[] args) {
        SpringApplication.run(ThymeleafApplication.class , args);
    }
}
