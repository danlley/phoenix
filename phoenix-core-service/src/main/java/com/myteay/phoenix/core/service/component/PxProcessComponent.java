/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.component;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.camp.CampCashierModel;

/**
 * 
 * @author min.weixm
 * @version $Id: PxProcessComponent.java, v 0.1 Jul 12, 2019 6:47:41 PM min.weixm Exp $
 */
public interface PxProcessComponent {
    /**
     * 
     * @param pxGoodsOrderModel
     * @return
     */
    public MtOperateResult<CampCashierModel> doProcess(PxGoodsOrderModel pxGoodsOrderModel);
}
