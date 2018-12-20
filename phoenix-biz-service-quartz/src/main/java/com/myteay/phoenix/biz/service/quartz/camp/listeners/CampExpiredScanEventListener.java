/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.quartz.camp.listeners;

import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.MtEvent;

/**
 * 定时处理已经过期的营销活动
 * 
 * @author danlley
 * @version $Id: CampExpiredScanEventListener.java, v 0.1 Dec 20, 2018 6:14:26 PM danlley Exp $
 */
public class CampExpiredScanEventListener extends EventListener<String> {

    /** 
     * @see com.myteay.common.async.event.EventListener#handleEvent(com.myteay.common.async.event.MtEvent)
     */
    @Override
    public String handleEvent(MtEvent<?> event) {
        if (logger.isInfoEnabled()) {
            logger.info("开始检查当前运行中已经过期的活动，对已过期的活动执行关停动作" + event);
        }
        return null;
    }

}
