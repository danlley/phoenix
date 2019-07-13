/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.tools;

import org.apache.log4j.Logger;

import com.myteay.common.async.event.EventPublishService;
import com.myteay.common.async.event.MtEvent;
import com.myteay.common.async.event.MtEventException;
import com.myteay.phoenix.common.util.process.enums.PxEventTopicEnum;

/**
 * 异步事件处理工具类
 * 
 * @author danlley
 * @version $Id: PxEventPublishTool.java, v 0.1 Dec 20, 2018 6:01:12 PM danlley Exp $
 */
public class PxEventPublishTool {

    /** 日志 */
    public static final Logger          logger = Logger.getLogger(PxEventPublishTool.class);

    /** 事件发送组件 */
    private EventPublishService<String> eventPublishService;

    /**
     * 发布异步事件
     * 
     * @param topicEnum
     */
    public void publishEvent(PxEventTopicEnum topicEnum) {
        try {
            eventPublishService.publishEvent(new MtEvent<>(topicEnum.getValue(), topicEnum.getValue()));
        } catch (MtEventException e) {
            logger.error("发送异步事件过程中出现异常", e);
        } catch (Throwable e) {
            logger.error("发送异步事件过程中出现未知异常", e);
        }

    }

    /**
     * 发布异步事件
     * 
     * @param topicEnum
     * @param obj
     */
    public void publishEventWithObject(PxEventTopicEnum topicEnum, Object obj) {
        try {
            eventPublishService.publishEvent(new MtEvent<>(topicEnum.getValue(), obj));
        } catch (MtEventException e) {
            logger.error("发送异步事件过程中出现异常", e);
        } catch (Throwable e) {
            logger.error("发送异步事件过程中出现未知异常", e);
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
