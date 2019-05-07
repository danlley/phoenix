/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.ibatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.myteay.phoenix.common.dal.daointerface.PxGoodsCostDAO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsCostDO;

/**
 * 商品成本信息记录操作DAO
 * 
 * @author danlley
 * @version $Id: IbatisPxGoodsCostDAO.java, v 0.1 May 7, 2019 11:55:51 PM danlley Exp $
 */
public class IbatisPxGoodsCostDAO extends SqlSessionDaoSupport implements PxGoodsCostDAO {

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsCostDAO#insert(com.myteay.phoenix.common.dal.dataobject.PxGoodsCostDO)
     */
    @Override
    public String insert(PxGoodsCostDO pxGoodsCostDO) {
        if (pxGoodsCostDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        this.getSqlSession().insert("PX-GOODS-COST-INSERT", pxGoodsCostDO);

        return pxGoodsCostDO.getGoodsId();
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsCostDAO#findPxGoodsCostById(java.lang.String, java.lang.String)
     */
    @Override
    public PxGoodsCostDO findPxGoodsCostById(String goodsId, String reportDate) {
        PxGoodsCostDO pxGoodsCostDO = new PxGoodsCostDO();
        pxGoodsCostDO.setGoodsId(goodsId);
        pxGoodsCostDO.setReportDate(reportDate);
        return this.getSqlSession().selectOne("PX-GOODS-COST-GET-BY-GOODS-ID", pxGoodsCostDO);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsCostDAO#updatePxGoodsCost(com.myteay.phoenix.common.dal.dataobject.PxGoodsCostDO)
     */
    @Override
    public void updatePxGoodsCost(PxGoodsCostDO pxGoodsCostDO) {
        if (pxGoodsCostDO == null) {
            throw new IllegalArgumentException("Can't update by a null data object.");
        }

        this.getSqlSession().update("PX-GOODS-COST-UPDATE-GET-BY-ID", pxGoodsCostDO);
    }

}
