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

import com.myteay.phoenix.common.util.enums.PxEventTopicEnum;
import com.myteay.phoenix.core.service.tools.PxEventPublishTool;

/**
 * 营销活动专用定时任务
 * 
 * @author danlley
 * @version $Id: CampGloableScheduledTask.java, v 0.1 Dec 20, 2018 5:58:17 PM danlley Exp $
 */
@Component
@Configurable
@EnableScheduling
public class CampGloableScheduledTask {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(PxGloableScheduledTask.class);

    /** 事件发送组件 */
    @Autowired
    private PxEventPublishTool pxEventPublishTool;

    /**
     * 定期检查营销活动列表中已经过期的营销活动每30分钟执行一次
     */
    @Scheduled(cron = "0 */30 * * * ?")
    public void checkExpiredShopCampByCron() {
        logger.warn("[定时任务]定期检查营销活动列表中已经过期的营销活动每30分钟执行一次");
        pxEventPublishTool.publishEvent(PxEventTopicEnum.CAMP_TASK_EXPIRED);
    }

    /**
     * 定期检查营销活动奖品的列表每小时整点的第5秒执行一次
     */
    @Scheduled(cron = "5 0 * * * ?")
    public void checkExpiredShopCampPrizeByCron() {
        logger.warn("[定时任务]定期检查营销活动奖品的列表每小时整点的第5秒执行一次");
        pxEventPublishTool.publishEvent(PxEventTopicEnum.CAMP_PRIZE_TASK_EXPIRED);
    }

    /**
     * Setter method for property <tt>pxEventPublishTool</tt>.
     * 
     * @param pxEventPublishTool value to be assigned to property pxEventPublishTool
     */
    public void setPxEventPublishTool(PxEventPublishTool pxEventPublishTool) {
        this.pxEventPublishTool = pxEventPublishTool;
    }

}
