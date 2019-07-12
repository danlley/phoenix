/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.component;

import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.camp.CampCashierModel;

/**
 * 
 * @author min.weixm
 * @version $Id: CampServiceComponent.java, v 0.1 Jul 12, 2019 6:36:49 PM min.weixm Exp $
 */
public interface CampServiceComponent {

    /**
     * 
     * @param pxGoodsOrderModel
     * @return
     */
    public CampCashierModel doCamp(PxGoodsOrderModel pxGoodsOrderModel);
}
