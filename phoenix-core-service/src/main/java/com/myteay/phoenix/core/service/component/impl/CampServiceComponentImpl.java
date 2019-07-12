/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.component.impl;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.myteay.phoenix.common.service.camp.integration.PxCampPrizeServiceIntg;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.camp.CampCashierModel;
import com.myteay.phoenix.core.service.component.CampServiceComponent;

/**
 * 营销服务组件
 * 
 * @author min.weixm
 * @version $Id: CampServiceComponentImpl.java, v 0.1 Jul 12, 2019 6:38:33 PM min.weixm Exp $
 */
public class CampServiceComponentImpl implements CampServiceComponent {

    /** 日志 */
    public static final Logger     logger = Logger.getLogger(CampServiceComponentImpl.class);

    /**  */
    private PxCampPrizeServiceIntg pxCampPrizeServiceIntg;

    /** 
     * @see com.myteay.phoenix.core.service.component.CampServiceComponent#doCamp(com.myteay.phoenix.core.model.PxGoodsOrderModel)
     */
    @Override
    public CampCashierModel doCamp(PxGoodsOrderModel pxGoodsOrderModel) {
        if (pxGoodsOrderModel == null || CollectionUtils.isEmpty(pxGoodsOrderModel.getOrderContext())) {
            logger.warn("订单上下文不可用，无法进行抽奖 pxGoodsOrderModel=" + pxGoodsOrderModel);
            return null;
        }

        CampCashierModel campCashierModel = pxCampPrizeServiceIntg.doCamp(pxGoodsOrderModel);
        // 解决抽奖失败的情况
        if (campCashierModel == null) {
            campCashierModel = new CampCashierModel();
            campCashierModel.setOrderNo(pxGoodsOrderModel.getOrderNo());
            campCashierModel.setUserId(pxGoodsOrderModel.getUserId());
            campCashierModel.setShopName(pxGoodsOrderModel.getShopName());
        }

        return campCashierModel;
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
