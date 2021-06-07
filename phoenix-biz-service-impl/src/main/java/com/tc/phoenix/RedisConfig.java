/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2015-2020 All Rights Reserved.
 */
package com.tc.phoenix;

import java.io.Serializable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis 服务配置信息
 * 
 * @author min.weixm
 * @version $Id: RedisConfig.java, v 0.1 Jul 7, 2020 4:25:52 PM min.weixm Exp $
 */
@Configuration
public class RedisConfig {

    /**
     * RedisTemplate 服务初始化
     * 
     * @param jedisConnectionFactory
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Bean
    public RedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory) {

        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(jedisConnectionFactory);

        return redisTemplate;
    }
}
