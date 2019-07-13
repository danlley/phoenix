/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.listeners;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.MtEvent;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.PxGoodsOrderOutModel;
import com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel;

/**
 * 成本打标监听组件
 * 
 * @author min.weixm
 * @version $Id: PxGoodsCostMarkListener.java, v 0.1 Jul 13, 2019 6:07:52 PM min.weixm Exp $
 */
public class PxGoodsCostMarkListener extends EventListener<Object> {

    /** 
     * @see com.myteay.common.async.event.EventListener#handleEvent(com.myteay.common.async.event.MtEvent)
     */
    @Override
    public Object handleEvent(MtEvent<?> event) {
        // step 1: 合法性检查
        if (event == null) {
            logger.warn("当前对象不可用，无法完成营销活动奖品打标动作 event is null");
            return null;
        }

        if (!(event.getData() instanceof PxGoodsOrderModel)) {
            logger.warn("优惠券打标事件对象不合法 instance must be of PxGoodsOrderModel");
            return null;
        }

        PxGoodsOrderModel pxGoodsOrderModel = (PxGoodsOrderModel) event.getData();
        fillPrizeList(pxGoodsOrderModel);

        if (logger.isInfoEnabled()) {
            logger.info("收到成本打标请求 pxGoodsOrderModel = " + pxGoodsOrderModel);
        }

        return null;
    }

    /**
     * 填充订单模型
     * 
     * @param pxGoodsOrderModel
     */
    private void fillPrizeList(PxGoodsOrderModel pxGoodsOrderModel) {

        if (pxGoodsOrderModel == null || StringUtils.isBlank(pxGoodsOrderModel.getOrderNo())
                || CollectionUtils.isEmpty(pxGoodsOrderModel.getPxGoodsOrderOutModelList())) {
            logger.warn("参数不合法，无法构建可用的待打标优惠券清单 pxGoodsOrderModel=" + pxGoodsOrderModel);
            return;
        }

        String orderNo = pxGoodsOrderModel.getOrderNo();
        List<PxGoodsOrderOutModel> pxGoodsOrderOutModels = pxGoodsOrderModel.getPxGoodsOrderOutModelList();
        CampShopPrizeOutModel campShopPrizeOutModel = null;
        for (PxGoodsOrderOutModel pxGoodsOrderOutModel : pxGoodsOrderOutModels) {
            if (pxGoodsOrderOutModel.getCampShopPrizeOutModel() == null) {
                continue;
            }

            if (StringUtils.isBlank(pxGoodsOrderOutModel.getId())) {
                logger.warn("订单流水号不可用，无法完成优惠券打标的准备工作 pxGoodsOrderOutModel=" + pxGoodsOrderOutModel);
                return;
            }

            campShopPrizeOutModel = pxGoodsOrderOutModel.getCampShopPrizeOutModel();
            campShopPrizeOutModel.setConsumedOrderNo(orderNo);
            campShopPrizeOutModel.setConsumedOrderId(pxGoodsOrderOutModel.getId());
        }
    }
}
