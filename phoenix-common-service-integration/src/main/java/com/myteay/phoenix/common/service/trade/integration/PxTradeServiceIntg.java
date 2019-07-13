/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.trade.integration;

import com.myteay.phoenix.common.util.enums.PxOrderStatusEnum;
import com.myteay.phoenix.common.util.enums.PxPayTypeEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel;

/**
 * 
 * @author min.weixm
 * @version $Id: TradeServiceIntg.java, v 0.1 Jul 6, 2019 9:28:20 PM min.weixm Exp $
 */
public interface PxTradeServiceIntg {

    public MtOperateResult<String> modifyGoodsOrderOut(String orderNo, PxPayTypeEnum pxPayTypeEnum, PxOrderStatusEnum pxOrderStatusEnum,
                                                       CampShopPrizeOutModel campShopPrizeOutModel);

    public MtOperateResult<PxGoodsOrderModel> createGoodsOrderOut(PxGoodsOrderModel pxGoodsOrderModel);
}
