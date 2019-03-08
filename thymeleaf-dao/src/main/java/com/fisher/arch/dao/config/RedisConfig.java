package com.fisher.arch.dao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis相关配置
 */
@Configuration
public class RedisConfig {

    @Value("${thymeleaf-web.redis.host}")
    private String hostName;

    @Value("${thymeleaf-web.redis.port}")
    private int port;

    @Bean
    public StringRedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(connectionFactory);
        redisTemplate.setKeySerializer(StringRedisSerializer.UTF_8);
        redisTemplate.setHashKeySerializer(StringRedisSerializer.UTF_8);
        redisTemplate.setDefaultSerializer(StringRedisSerializer.UTF_8);

        redisTemplate.setValueSerializer(StringRedisSerializer.UTF_8);
        redisTemplate.setHashValueSerializer(StringRedisSerializer.UTF_8);
        return redisTemplate;
    }

    @Bean
    public RedisConnectionFactory connectionFactory(JedisPoolConfig poolConfig) {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(hostName);
        configuration.setPort(port);

        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder builder
                 = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();

        builder.poolConfig(poolConfig);
        JedisClientConfiguration jedisClientConfiguration = builder.build();
        return new JedisConnectionFactory(configuration , jedisClientConfiguration);
    }


    @Bean
    public JedisPoolConfig poolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大连接数
        jedisPoolConfig.setMaxTotal(100);
        // 最小空闲连接数
        jedisPoolConfig.setMinIdle(20);
        // 当池内没有可用的连接时，最大等待时间
        jedisPoolConfig.setMaxWaitMillis(10000);
        return jedisPoolConfig;
    }

}
