/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.listeners;

import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.MtEvent;
import com.myteay.phoenix.common.service.camp.integration.PxCampPrizeServiceIntg;
import com.myteay.phoenix.core.model.MtOperateResult;

/**
 * 优惠券抵扣监听器
 * 
 * @author min.weixm
 * @version $Id: PxCampPrizeConsumeListener.java, v 0.1 Jul 18, 2019 9:22:53 PM min.weixm Exp $
 */
public class PxCampPrizeConsumeListener extends EventListener<Object> {

    /** 优惠券服务客户端 */
    private PxCampPrizeServiceIntg pxCampPrizeServiceIntg;

    /** 
     * @see com.myteay.common.async.event.EventListener#handleEvent(com.myteay.common.async.event.MtEvent)
     */
    @Override
    public Object handleEvent(MtEvent<?> event) {

        if (logger.isInfoEnabled()) {
            logger.info("收到优惠券抵扣请求 event=" + event);
        }

        if (event == null || event.getData() == null) {
            logger.warn("优惠券抵扣监听器事件对象不可用 event.obj is null");
            return null;
        }

        if (!(event.getData() instanceof String)) {
            logger.warn("优惠券抵扣监听器事件对象不合法  event=" + event);
            return null;
        }

        MtOperateResult<String> result = pxCampPrizeServiceIntg.consumePrize((String) event.getData());

        if (logger.isInfoEnabled()) {
            logger.info("优惠券抵扣结果 event=" + event + " result=" + result);
        }

        return null;
    }

    /**
     * Setter method for property <tt>pxCampPrizeServiceIntg</tt>.
     * 
     * @param pxCampPrizeServiceIntg value to be assigned to property pxCampPrizeServiceIntg
     */
    public void setPxCampPrizeServiceIntg(PxCampPrizeServiceIntg pxCampPrizeServiceIntg) {
        this.pxCampPrizeServiceIntg = pxCampPrizeServiceIntg;
    }

}
