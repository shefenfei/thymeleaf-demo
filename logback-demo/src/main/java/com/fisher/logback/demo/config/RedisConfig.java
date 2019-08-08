package com.fisher.logback.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Bean
    public Config config() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379")
                .setRetryAttempts(2)
                .setConnectTimeout(10);

        Codec codec = new JsonJacksonCodec();
        config.setCodec(codec);
        return config;
    }


    @Bean
    public RedissonClient redissonClient(Config config) {
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }

}
