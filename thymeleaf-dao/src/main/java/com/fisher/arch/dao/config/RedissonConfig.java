package com.fisher.arch.dao.config;

import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;

//@Configuration
public class RedissonConfig {

    @Bean
    public Config config() {
        Config config = new Config();
        return config;
    }
}
