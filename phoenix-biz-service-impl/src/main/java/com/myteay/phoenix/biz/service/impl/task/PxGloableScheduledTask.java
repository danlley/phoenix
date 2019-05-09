/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.enums.PxEventTopicEnum;
import com.myteay.phoenix.core.service.tools.PxEventPublishTool;

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
     * 定期刷新手机端商品列表每30分钟执行一次
     */
    @Scheduled(cron = "0 */30 * * * ?")
    public void refreshMobileGoodsListCacheByCron() {
        logger.warn("[定时任务]商品列表定时加载任务启动");
        pxEventPublishTool.publishEvent(PxEventTopicEnum.PX_TASK_MOBILE_GOODS_CACHE_LOAD);
    }

    /**
     * 定期刷新手机端商品列表每小时整点执行一次
     */
    @Scheduled(cron = "0 0 * * * ?")
    public void scanExpiredShopByCron() {
        logger.warn("[定时任务]监控店铺过期时间");
        pxEventPublishTool.publishEvent(PxEventTopicEnum.PX_TASK_SHOP_EXPIRED);
    }

    /**
     * 定期刷新手机端商品列表每小时整点执行一次
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void scanShopExpiredOrdersByCron() {
        logger.warn("[定时任务]监控店铺过期订单");
        pxEventPublishTool.publishEvent(PxEventTopicEnum.PX_TASK_SHOP_EXPIRED_ORDERS);
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public void scanPayedOrdersByCron() {
        pxEventPublishTool.publishEvent(PxEventTopicEnum.PX_TASK_SHOP_PAYED_ORDERS);
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
