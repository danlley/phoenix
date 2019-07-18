/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.trade.integration;

import com.myteay.phoenix.common.util.enums.PxOrderStatusEnum;
import com.myteay.phoenix.common.util.enums.PxPayTypeEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;

/**
 * 交易订单管理客户端
 * 
 * @author min.weixm
 * @version $Id: TradeServiceIntg.java, v 0.1 Jul 6, 2019 9:28:20 PM min.weixm Exp $
 */
public interface PxTradeServiceIntg {

    /**
     * 订单支付状态及订单状态变更
     * 
     * @param orderNo
     * @param pxPayTypeEnum
     * @param pxOrderStatusEnum
     * @return
     */
    public MtOperateResult<String> modifyGoodsOrderOut(String orderNo, PxPayTypeEnum pxPayTypeEnum, PxOrderStatusEnum pxOrderStatusEnum);

    /**
     * 创建订单
     * 
     * @param pxGoodsOrderModel
     * @return
     */
    public MtOperateResult<PxGoodsOrderModel> createGoodsOrderOut(PxGoodsOrderModel pxGoodsOrderModel);
}
