/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.quartz.listeners;

import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.MtEvent;
import com.myteay.phoenix.core.service.cashier.component.PxGoodsOrderOutCompoonent;

/**
 * 店内消费的废单检测处理
 * 
 * @author danlley
 * @version $Id: PxShopExpiredOrderScanListener.java, v 0.1 Mar 21, 2019 1:45:18 PM danlley Exp $
 */
public class PxShopExpiredOrderScanListener extends EventListener<String> {

    /** 订单流水操作组件 */
    private PxGoodsOrderOutCompoonent pxGoodsOrderOutCompoonent;

    /** 
     * @see com.myteay.common.async.event.EventListener#handleEvent(com.myteay.common.async.event.MtEvent)
     */
    @Override
    public String handleEvent(MtEvent<?> event) {
        logger.warn("[店内消费废单检测] 收到废单检测请求，准备处理废单");
        return null;
    }

    /**
     * Setter method for property <tt>pxGoodsOrderOutCompoonent</tt>.
     * 
     * @param pxGoodsOrderOutCompoonent value to be assigned to property pxGoodsOrderOutCompoonent
     */
    public void setPxGoodsOrderOutCompoonent(PxGoodsOrderOutCompoonent pxGoodsOrderOutCompoonent) {
        this.pxGoodsOrderOutCompoonent = pxGoodsOrderOutCompoonent;
    }

}
