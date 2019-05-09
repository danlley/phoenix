/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.daointerface;

import com.myteay.phoenix.common.dal.dataobject.PxGoodsOrderOutHistoryDO;

/**
 * 订单流水历史操作DAO
 * 
 * @author danlley
 * @version $Id: PxGoodsOrderOutHistoryDAO.java, v 0.1 Feb 26, 2019 4:45:01 PM danlley Exp $
 */
public interface PxGoodsOrderOutHistoryDAO {

    /**
     * 插入订单流水历史信息
     * 
     * @param pxGoodsOrderOutHistoryDO   数据模型
     * @return
     */
    String insert(PxGoodsOrderOutHistoryDO pxGoodsOrderOutHistoryDO);

}
