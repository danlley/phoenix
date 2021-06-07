/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.camp.integration;

import com.tc.dbcenter.common.orm.model.PxGoodsModel;
import com.tc.phoenix.common.util.MtOperateResult;
import com.tc.promocore.common.orm.model.CampCashierModel;
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
     * @param tcTradeModel
     * @return
     */
    public MtOperateResult<CampCashierModel<PxGoodsModel>> doCamp(TcTradeModel tcTradeModel);
}
