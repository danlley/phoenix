/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.component;

import com.myteay.phoenix.common.util.enums.PxOrderStatusEnum;
import com.myteay.phoenix.common.util.enums.PxPayTypeEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.camp.CampCashierModel;

/**
 * 收银台主流程处理组件
 * 
 * @author min.weixm
 * @version $Id: PxProcessComponent.java, v 0.1 Jul 12, 2019 6:47:41 PM min.weixm Exp $
 */
public interface PxProcessComponent {

    /**
     * 订单支付状态及订单状态变更
     * 
     * @param orderNo
     * @param pxPayTypeEnum
     * @param pxOrderStatusEnum
     * @return
     */
    public MtOperateResult<String> doPay(String orderNo, PxPayTypeEnum pxPayTypeEnum, PxOrderStatusEnum pxOrderStatusEnum);

    /**
     * 
     * @param pxGoodsOrderModel
     * @return
     */
    public MtOperateResult<CampCashierModel> doProcess(PxGoodsOrderModel pxGoodsOrderModel);
}
