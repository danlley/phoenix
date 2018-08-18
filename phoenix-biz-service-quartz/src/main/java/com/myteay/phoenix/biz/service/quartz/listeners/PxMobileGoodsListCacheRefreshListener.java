/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.quartz.listeners;

import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.MtEvent;
import com.myteay.phoenix.core.service.component.PxMobileGoodsCacheComponent;

/**
 * 商品列表定时加载监听器
 * 
 * @author min.weixm
 * @version $Id: PxMobileGoodsListCacheRefreshListener.java, v 0.1 Aug 18, 2018 6:03:02 PM min.weixm Exp $
 */
public class PxMobileGoodsListCacheRefreshListener extends EventListener<String> {

    /** 手机端商品缓存管理组件 */
    private PxMobileGoodsCacheComponent pxMobileGoodsCacheComponent;

    /** 
     * @see com.myteay.common.async.event.EventListener#handleEvent(com.myteay.common.async.event.MtEvent)
     */
    @Override
    public String handleEvent(MtEvent<?> event) {

        if (logger.isInfoEnabled()) {
            logger.info("[商品列表定时加载监听器]刷新商品列表缓存 event=" + event);
        }

        try {
            pxMobileGoodsCacheComponent.initCache();
        } catch (Throwable e) {
            logger.warn("[商品列表定时加载监听器]刷新商品列表缓存发生未知异常 " + e.getMessage(), e);
        }
        return null;
    }

    /**
     * Setter method for property <tt>pxMobileGoodsCacheComponent</tt>.
     * 
     * @param pxMobileGoodsCacheComponent value to be assigned to property pxMobileGoodsCacheComponent
     */
    public void setPxMobileGoodsCacheComponent(PxMobileGoodsCacheComponent pxMobileGoodsCacheComponent) {
        this.pxMobileGoodsCacheComponent = pxMobileGoodsCacheComponent;
    }

}
