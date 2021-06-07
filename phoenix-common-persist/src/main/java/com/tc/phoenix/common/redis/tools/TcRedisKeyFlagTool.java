/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2015-2020 All Rights Reserved.
 */
package com.tc.phoenix.common.redis.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.tc.ccopass.logger.Logger;
import com.tc.ccopass.logger.LoggerFactory;

/**
 * redis关键字获取工具类
 * 
 * @author min.weixm
 * @version $Id: TcRedisKeyFlagTool.java, v 0.1 Jul 13, 2020 5:35:35 PM min.weixm Exp $
 */
public class TcRedisKeyFlagTool {

    /** 日志 */
    private static final Logger           logger  = LoggerFactory.getLogger(TcRedisKeyFlagTool.class);

    /** 关键字标识符格式 */
    private static final String           PATTERN = "yyyy-MM-dd";

    /** 系统重启标识 */
    private String                        keyFlag = null;

    /** RedisTemplate */
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /** 环境变量 */
    @Autowired
    private Environment                   env;

    /**
     * 获取带业务标识的ID关键字
     * 
     * @param key
     * @param bizFlag
     * @return
     */
    public String getFlagByBiz(String key, String bizFlag) {
        String flag = getFlag(key);

        if (StringUtils.isBlank(bizFlag)) {
            return flag;
        }

        // 获取当前值
        return (StringUtils.isBlank(flag) ? flag : bizFlag + "-" + flag);
    }

    /**
     * 获取关键字对应值
     * 
     *  注：如存在当前日期已经过期的情况，则会重新置值
     * 
     * @param key
     * @return
     */
    public String getFlag(String key) {

        String appName = env.getProperty("tiancan.app.name");

        String flag = genFlag(key);

        // 获取当前值
        return (StringUtils.isBlank(flag) ? flag : appName + "-" + flag);
    }

    /**
     * 生成关键标识
     * 
     * @param key
     * @return
     */
    private String genFlag(String key) {
        //对已经过期的flag进行重新设值
        if (isExpiredFlag(key)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN);
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, simpleDateFormat.format(new Date()), 36L, TimeUnit.HOURS);

            return getKeyFlagByKey(key);
        }

        // 获取当前值
        return getKeyFlagByInit(key);
    }

    /**
     * 判断当前flag是否已经过期
     * 
     * @param key
     * @return
     */
    private boolean isExpiredFlag(String key) {
        String flag = getKeyFlagByInit(key);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN);
        String currentFlag = simpleDateFormat.format(new Date());

        return !currentFlag.equals(flag);

    }

    /**
     * 带初始化获取当前标识，初始化值为当前日期
     * 
     * @param key
     * @return
     */
    private String getKeyFlagByInit(String key) {
        if (StringUtils.isNotBlank(keyFlag)) {
            return getKeyFlagByKey(key);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN);
        return getkeyFlagByKeyWithDefValue(key, simpleDateFormat.format(new Date()));
    }

    /**
     * 通过默认值进行初始化，过期时间设置为36小时，确保24小时内有效
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    private String getkeyFlagByKeyWithDefValue(String key, String defaultValue) {
        String flag = getKeyFlagByKey(key);

        if (StringUtils.isBlank(flag)) {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, defaultValue, 36L, TimeUnit.HOURS);

            return getKeyFlagByKey(key);
        }

        return flag;
    }

    /**
     * 直接从redis中获取关键标识
     * 
     * @param key
     * @return
     */
    private String getKeyFlagByKey(String key) {

        boolean exists = false;
        try {
            exists = redisTemplate.hasKey(key);
        } catch (Exception e) {
            logger.error("redis获取关键字发生异常", e);
        }
        if (exists) {
            keyFlag = redisTemplate.opsForValue().get(key);
            return keyFlag;
        }

        return null;
    }
}
