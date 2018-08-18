/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.listener;

import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.MtEvent;
import com.myteay.phoenix.core.service.component.PxMobileGoodsCacheComponent;

/**
 * 店铺时间监听
 * 
 * @author min.weixm
 * @version $Id: PxGoodsStatusChangedEventListener.java, v 0.1 Jul 24, 2018 5:54:41 PM min.weixm Exp $
 */
public class PxGoodsStatusChangedEventListener extends EventListener<Object> {

    /** 手机端商品列表缓存组件 */
    private PxMobileGoodsCacheComponent pxMobileGoodsCacheComponent;

    /** 
     * @see com.myteay.common.async.event.EventListener#handleEvent(com.myteay.common.async.event.MtEvent)
     */
    @Override
    public Object handleEvent(MtEvent<?> event) {

        if (logger.isInfoEnabled()) {
            logger.info("商品状态发生变更，开始刷新手机端商品列表缓存 event=" + event);
        }

        pxMobileGoodsCacheComponent.initCache();

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
