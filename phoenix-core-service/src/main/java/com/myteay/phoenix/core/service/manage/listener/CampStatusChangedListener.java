/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.listener;

import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.MtEvent;
import com.myteay.phoenix.core.model.camp.CampBaseModel;
import com.myteay.phoenix.core.service.camp.component.CampShopCacheComponnet;

/**
 * 监听活动状态变更事件监听器
 * 
 * @author danlley
 * @version $Id: CampStatusChangedListener.java, v 0.1 May 10, 2019 12:59:54 AM danlley Exp $
 */
public class CampStatusChangedListener extends EventListener<Object> {

    /** 到店消费营销活动基础数据缓存 */
    private CampShopCacheComponnet campShopCacheComponnet;

    /** 
     * @see com.myteay.common.async.event.EventListener#handleEvent(com.myteay.common.async.event.MtEvent)
     */
    @Override
    public Object handleEvent(MtEvent<?> event) {

        if (event == null || !(event.getData() instanceof CampBaseModel)) {
            logger.warn("奖品缓存刷新模型不合法 event = " + event.getData());
            return null;
        }

        //异步推送奖品缓存信息重新刷新
        campShopCacheComponnet.refreshCampShopCache(((CampBaseModel) (event.getData())).getCampId());

        return null;
    }

    /**
     * Setter method for property <tt>campShopCacheComponnet</tt>.
     * 
     * @param campShopCacheComponnet value to be assigned to property campShopCacheComponnet
     */
    public void setCampShopCacheComponnet(CampShopCacheComponnet campShopCacheComponnet) {
        this.campShopCacheComponnet = campShopCacheComponnet;
    }

}
