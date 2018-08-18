/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myteay.common.async.event.EventPublishService;
import com.myteay.common.async.event.MtEvent;
import com.myteay.common.async.event.MtEventException;
import com.myteay.phoenix.common.util.enums.PxEventTopicEnum;

/**
 * 商品缓存定时加载
 * 
 * @author min.weixm
 * @version $Id: PxGloableScheduledTask.java, v 0.1 Aug 18, 2018 12:30:07 PM min.weixm Exp $
 */
@Component
@Configurable
@EnableScheduling
public class PxGloableScheduledTask {

    /** 日志 */
    public static final Logger          logger = Logger.getLogger(PxGloableScheduledTask.class);

    /** 套餐信息管理组件 */
    @Autowired
    private EventPublishService<String> eventPublishService;

    /**
     * 每1分钟执行一次
     */
    @Scheduled(cron = "*/10 * * * * ?")
    public void reportCurrentByCron() {
        logger.warn("[定时任务]商品列表定时加载任务启动");
        publishEvent(PxEventTopicEnum.PX_TASK_MOBILE_GOODS_CACHE_LOAD);
    }

    /**
     * 发布异步事件生成用户二维码（这里将吃掉所有异常，如生成二维码失败，则有后续的相关辅助流程协助解决）
     * 
     * @param request   请求
     * @param content   处理结果
     */
    private void publishEvent(PxEventTopicEnum topicEnum) {

        if (logger.isInfoEnabled()) {
            logger.info("开始执行用户注册过程中的扩展流程 topicEnum = " + topicEnum);
        }

        //异步事件处理组件
        try {
            eventPublishService.publishEvent(new MtEvent<>(topicEnum.getValue(), topicEnum.getValue()));
        } catch (MtEventException e) {
            logger.error("处理用户联系人信息过程中出现异常", e);
        }

    }

    /**
     * Setter method for property <tt>eventPublishService</tt>.
     * 
     * @param eventPublishService value to be assigned to property eventPublishService
     */
    public void setEventPublishService(EventPublishService<String> eventPublishService) {
        this.eventPublishService = eventPublishService;
    }

}
