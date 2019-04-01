/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.quartz.listeners;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.MtEvent;
import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.PxGoodsOrderOutModel;
import com.myteay.phoenix.core.service.cashier.component.PxGoodsOrderOutCompoonent;

/**
 * 店内消费的废单检测处理
 * 
 * @author danlley
 * @version $Id: PxShopExpiredOrderScanListener.java, v 0.1 Mar 21, 2019 1:45:18 PM danlley Exp $
 */
public class PxShopExpiredOrderScanListener extends EventListener<String> {

    /** 日志 */
    private static final Logger       logger = LoggerFactory.getLogger(LoggerNames.PX_TASK);

    /** 订单流水操作组件 */
    private PxGoodsOrderOutCompoonent pxGoodsOrderOutCompoonent;

    /** 
     * @see com.myteay.common.async.event.EventListener#handleEvent(com.myteay.common.async.event.MtEvent)
     */
    @Override
    public String handleEvent(MtEvent<?> event) {
        logger.warn("[店内消费废单检测] 收到废单检测请求，准备处理废单");

        MtOperateResult<PxGoodsOrderModel> result = pxGoodsOrderOutCompoonent.findAllShopExpiredOrder();
        if (result == null || result.getOperateResult() != MtOperateResultEnum.CAMP_OPERATE_SUCCESS) {
            logger.warn("[店内消费废单检测] 获取废单列表失败 result = " + result);
            return null;
        }

        List<PxGoodsOrderOutModel> list = (result.getResult() == null ? null : result.getResult().getPxGoodsOrderOutModelList());
        if (CollectionUtils.isEmpty(list)) {
            logger.warn("当前没有需要处理的废单列表 result=" + result);
            return null;
        }

        if (logger.isInfoEnabled()) {
            logger.info("开始进行废单处理：result= " + result);
        }

        pxGoodsOrderOutCompoonent.deleteExpiredOrder(result.getResult());
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
