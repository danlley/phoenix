/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.listener;

import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.MtEvent;
import com.myteay.phoenix.core.service.component.PxGoodsCostCfgCacheComponent;

/**
 * 商品成本变更事件监听器
 * 
 * @author danlley
 * @version $Id: PxGoodsCostCfgChangedListener.java, v 0.1 May 8, 2019 6:04:56 PM danlley Exp $
 */
public class PxGoodsCostCfgChangedListener extends EventListener<Object> {

    /** 商品成本缓存 */
    private PxGoodsCostCfgCacheComponent pxGoodsCostCfgCacheComponent;

    /** 
     * @see com.myteay.common.async.event.EventListener#handleEvent(com.myteay.common.async.event.MtEvent)
     */
    @Override
    public Object handleEvent(MtEvent<?> event) {

        if (logger.isInfoEnabled()) {
            logger.info("监听到奖品成本变更事件，开始刷新商品成本缓存 event=" + event);
        }

        pxGoodsCostCfgCacheComponent.refreshGoodsCostCfgCache();
        return null;
    }

    /**
     * Setter method for property <tt>pxGoodsCostCfgCacheComponent</tt>.
     * 
     * @param pxGoodsCostCfgCacheComponent value to be assigned to property pxGoodsCostCfgCacheComponent
     */
    public void setPxGoodsCostCfgCacheComponent(PxGoodsCostCfgCacheComponent pxGoodsCostCfgCacheComponent) {
        this.pxGoodsCostCfgCacheComponent = pxGoodsCostCfgCacheComponent;
    }

}
