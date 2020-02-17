/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.core.service.tools.PxEventPublishTool;
import com.tc.phoenix.common.util.log.LoggerNames;

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
    private static final Logger logger = LoggerFactory.getLogger(LoggerNames.PX_TASK);

    /** 事件发送组件 */
    @Autowired
    private PxEventPublishTool  pxEventPublishTool;

    /**
     * Setter method for property <tt>pxEventPublishTool</tt>.
     * 
     * @param pxEventPublishTool value to be assigned to property pxEventPublishTool
     */
    public void setPxEventPublishTool(PxEventPublishTool pxEventPublishTool) {
        this.pxEventPublishTool = pxEventPublishTool;
    }

}
