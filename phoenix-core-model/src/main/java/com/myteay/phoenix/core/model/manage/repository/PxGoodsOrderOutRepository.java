/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.repository;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;

/**
 * 订单流水仓储
 * 
 * @author danlley
 * @version $Id: PxGoodsOrderOutRepository.java, v 0.1 Feb 26, 2019 6:52:41 PM danlley Exp $
 */
public interface PxGoodsOrderOutRepository {

    /**
     * 保存订单流水
     * 
     * @param pxGoodsOrderModel
     * @return
     * @throws PxManageException 
     */
    public String saveGoodsOrderOut(PxGoodsOrderModel pxGoodsOrderModel) throws PxManageException;
}
