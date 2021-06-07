/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2015-2020 All Rights Reserved.
 */
package com.tc.phoenix.common.redis.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import com.tc.ccopass.logger.Logger;
import com.tc.ccopass.logger.LoggerFactory;

/**
 * 序列号生成工具
 * 
 * @author min.weixm
 * @version $Id: TcRedisSequenceTool.java, v 0.1 Jul 11, 2020 4:05:46 PM min.weixm Exp $
 */
public class TcRedisSequenceTool {

    /** 日志 */
    public static final Logger          logger        = LoggerFactory.getLogger(TcRedisSequenceTool.class);

    /** 交易ID自增编号标识符获取关键字 */
    private static final String         SEQUENCE_FLAG = "TRADE_ID_SEQUENCE_FLAG";

    /** 序列号尾号起始位置 */
    private static final Long           tailFix       = 100000L;

    /** RedisTemplate */
    @Autowired
    private RedisTemplate<String, Long> redisTemplate;

    /** TcRedisKeyFlagTool */
    @Autowired
    private TcRedisKeyFlagTool          tcRedisKeyFlagTool;

    /**
     * 生成ID
     * 
     * @return
     */
    public String getIdByBizKey(String bizKey) {
        String prefix = getPrefix();
        String tail = getTail(SEQUENCE_FLAG, bizKey);
        if (StringUtils.isBlank(prefix) || StringUtils.isBlank(tail)) {
            throw new IllegalArgumentException("交易ID生成失败 prefix=" + prefix + " tail=" + tail);
        }

        return prefix + tail;
    }

    /**
     * 构建交易ID尾部信息
     * 
     * @param key
     * @return
     */
    private String getTail(String key, String bizKey) {
        String value = tcRedisKeyFlagTool.getFlagByBiz(key, bizKey);
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("交易序列号关键字不可用");
        }

        if (logger.isInfoEnabled()) {
            logger.info("系统当前的redis序列号关键字 value=" + value + " key=" + key + " bizKey=" + bizKey);
        }

        long tail = getSequence(value);

        return Long.toString(tailFix + tail);
    }

    /**
     * 获取交易ID前缀
     * 
     * @return
     */
    private String getPrefix() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取当前的自增ID
     * 
     * @param key
     * @return
     */
    private Long getSequence(String key) {
        RedisConnectionFactory conn = redisTemplate.getConnectionFactory();
        return new RedisAtomicLong(key, conn).getAndIncrement();
    }

    //现在到今天结束的毫秒数
    public Long getCurrent2TodayEndMillisTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTimeInMillis() - new Date().getTime();
    }
}
