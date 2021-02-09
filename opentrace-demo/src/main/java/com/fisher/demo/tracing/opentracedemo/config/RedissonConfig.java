package com.fisher.demo.tracing.opentracedemo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public Config config() {
        Config config = new Config();
        String address = "redis://localhost:6379";
        config.useSingleServer().setDatabase(0).setAddress(address);
        return config;
    }

    @Bean
    public RedissonClient redisClient(Config config) {
        return Redisson.create(config);
    }
}
