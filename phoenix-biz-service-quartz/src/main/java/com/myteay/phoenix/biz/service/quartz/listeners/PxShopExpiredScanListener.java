/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.quartz.listeners;

import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.MtEvent;

/**
 * 店铺过期时间扫描监听器
 * 
 * @author danlley
 * @version $Id: PxShopExpiredScanListener.java, v 0.1 Aug 18, 2018 10:32:18 PM danlley Exp $
 */
public class PxShopExpiredScanListener extends EventListener<String> {

    /** 
     * @see com.myteay.common.async.event.EventListener#handleEvent(com.myteay.common.async.event.MtEvent)
     */
    @Override
    public String handleEvent(MtEvent<?> event) {
        if (logger.isInfoEnabled()) {
            logger.info("[店铺过期时间扫描监听器]开始扫描已过期店铺 event=" + event);
        }
        return null;
    }

}
