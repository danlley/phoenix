/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.listener;

import org.apache.log4j.Logger;

import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.MtEvent;

/**
 * 店铺时间监听
 * 
 * @author min.weixm
 * @version $Id: PxShopSaveEventListener.java, v 0.1 Jul 24, 2018 5:54:41 PM min.weixm Exp $
 */
public class PxShopEventListener extends EventListener<Object> {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(PxShopEventListener.class);

    /** 
     * @see com.myteay.common.async.event.EventListener#handleEvent(com.myteay.common.async.event.MtEvent)
     */
    @Override
    public Object handleEvent(MtEvent<?> event) {

        //step 1: 通知刷新商品列表缓存

        return null;
    }

}
