/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.camp.integration;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.camp.CampCashierModel;
import com.tc.trade.orm.model.TcTradeModel;

/**
 * 优惠券服务客户端
 * 
 * @author min.weixm
 * @version $Id: PxCampPrizeServiceIntg.java, v 0.1 2019年7月6日 下午5:08:29 min.weixm Exp $
 */
public interface PxCampPrizeServiceIntg {

    /**
     * 
     * @param orderNo
     * @return
     */
    public MtOperateResult<String> consumePrize(String orderNo);

    /**
     * 
     * @param pxGoodsOrderModel
     * @return
     */
    public MtOperateResult<String> markPrize(PxGoodsOrderModel pxGoodsOrderModel);

    /**
     * 
     * @param pxGoodsOrderModel
     * @return
     */
    public CampCashierModel doCamp(PxGoodsOrderModel pxGoodsOrderModel);

    /**
     * 
     * @param tcTradeModel
     * @return
     */
    public MtOperateResult<CampCashierModel> doCamp(TcTradeModel tcTradeModel);
}
