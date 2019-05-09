/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.ibatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.myteay.phoenix.common.dal.daointerface.PxGoodsOrderOutHistoryDAO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsOrderOutHistoryDO;

/**
 * 订单流水历史操作DAO
 * 
 * @author danlley
 * @version $Id: IbatisPxGoodsOrderOutHistoryDAO.java, v 0.1 May 10, 2019 4:12:16 AM danlley Exp $
 */
public class IbatisPxGoodsOrderOutHistoryDAO extends SqlSessionDaoSupport implements PxGoodsOrderOutHistoryDAO {

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsOrderOutHistoryDAO#insert(com.myteay.phoenix.common.dal.dataobject.PxGoodsOrderOutHistoryDO)
     */
    @Override
    public String insert(PxGoodsOrderOutHistoryDO pxGoodsOrderOutHistoryDO) {
        if (pxGoodsOrderOutHistoryDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        this.getSqlSession().insert("PX-GOODS-ORDER-OUT-HISTORY-INSERT", pxGoodsOrderOutHistoryDO);

        return pxGoodsOrderOutHistoryDO.getGoodsId();
    }

}
