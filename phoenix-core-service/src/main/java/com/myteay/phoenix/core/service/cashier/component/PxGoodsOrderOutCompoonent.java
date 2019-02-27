/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.cashier.component;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;

/**
 * 订单流水操作组件
 * 
 * @author danlley
 * @version $Id: PxGoodsOrderOutCompoonent.java, v 0.1 Feb 27, 2019 1:15:31 PM danlley Exp $
 */
public interface PxGoodsOrderOutCompoonent {

    /**
     * 执行流水落地
     * 
     * @param pxGoodsOrderModel
     * @return
     */
    public MtOperateResult<String> execute(PxGoodsOrderModel pxGoodsOrderModel);
}
