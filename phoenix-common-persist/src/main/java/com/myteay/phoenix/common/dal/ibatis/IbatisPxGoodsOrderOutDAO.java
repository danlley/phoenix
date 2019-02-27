/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.ibatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.myteay.phoenix.common.dal.daointerface.PxGoodsOrderOutDAO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsOrderOutDO;

/**
 * 订单流水操作DAO
 * 
 * @author danlley
 * @version $Id: IbatisPxGoodsOrderOutDAO.java, v 0.1 Feb 26, 2019 4:57:23 PM danlley Exp $
 */
public class IbatisPxGoodsOrderOutDAO extends SqlSessionDaoSupport implements PxGoodsOrderOutDAO {

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsOrderOutDAO#insert(com.myteay.phoenix.common.dal.dataobject.PxGoodsOrderOutDO)
     */
    @Override
    public String insert(PxGoodsOrderOutDO pxGoodsOrderOutDO) {
        if (pxGoodsOrderOutDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        this.getSqlSession().insert("PX-GOODS-ORDER-OUT-INSERT", pxGoodsOrderOutDO);

        return pxGoodsOrderOutDO.getGoodsId();
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsOrderOutDAO#updatePxGoods(com.myteay.phoenix.common.dal.dataobject.PxGoodsOrderOutDO)
     */
    @Override
    public void updatePxGoods(PxGoodsOrderOutDO pxGoodsOrderOutDO) {
        if (pxGoodsOrderOutDO == null) {
            throw new IllegalArgumentException("Can't update by a null data object.");
        }

        this.getSqlSession().update("PX-GOODS-ORDER-OUT-STATUS-UPDATE-GET-BY-ORDER-NO", pxGoodsOrderOutDO);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsOrderOutDAO#deleteByOrderNo(java.lang.String)
     */
    @Override
    public void deleteByOrderNo(String orderNo) {
        this.getSqlSession().delete("PX-GOODS-ORDER-OUT-DELETE-GET-BY-ID", orderNo);
    }
}
