/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.quartz.camp.listeners;

import org.springframework.beans.factory.annotation.Autowired;

import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.MtEvent;
import com.myteay.phoenix.common.util.enums.PxEventTopicEnum;
import com.myteay.phoenix.core.service.tools.PxEventPublishTool;

/**
 * 中奖流水状态变更及迁移策略监听
 * 
 * @author danlley
 * @version $Id: CampPrizeOutExpiredScanEventListener.java, v 0.1 Apr 22, 2019 12:58:37 AM danlley Exp $
 */
public class CampPrizeOutExpiredScanEventListener extends EventListener<String> {

    /** 事件发送组件 */
    @Autowired
    private PxEventPublishTool pxEventPublishTool;

    /** 
     * @see com.myteay.common.async.event.EventListener#handleEvent(com.myteay.common.async.event.MtEvent)
     */
    @Override
    public String handleEvent(MtEvent<?> event) {

        // 对已发放且过期的优惠券进行过期处理
        pxEventPublishTool.publishEvent(PxEventTopicEnum.CAMP_PRIZE_OUT_EXPIRED);

        // 对已消费的优惠券进行迁移处理
        pxEventPublishTool.publishEvent(PxEventTopicEnum.CAMP_PRIZE_OUT_CONSUMED_MV);

        // 对已过期的优惠券进行迁移处理
        pxEventPublishTool.publishEvent(PxEventTopicEnum.CAMP_PRIZE_OUT_EXPIRED_MV);

        return null;
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
