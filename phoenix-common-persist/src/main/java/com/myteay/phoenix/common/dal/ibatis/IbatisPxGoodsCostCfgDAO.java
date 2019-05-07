/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.ibatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.myteay.phoenix.common.dal.daointerface.PxGoodsCostCfgDAO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsCostCfgAdvDO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsCostCfgDO;

/**
 * 商品成本管理操作DAO
 * 
 * @author danlley
 * @version $Id: IbatisPxGoodsCostCfgDAO.java, v 0.1 May 6, 2019 11:19:50 PM danlley Exp $
 */
public class IbatisPxGoodsCostCfgDAO extends SqlSessionDaoSupport implements PxGoodsCostCfgDAO {

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsCostCfgDAO#insert(com.myteay.phoenix.common.dal.dataobject.PxGoodsCostCfgDO)
     */
    @Override
    public String insert(PxGoodsCostCfgDO pxGoodsCostCfgDO) {
        if (pxGoodsCostCfgDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        this.getSqlSession().insert("PX-GOODS-CFG-INSERT", pxGoodsCostCfgDO);

        return pxGoodsCostCfgDO.getGoodsId();
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsCostCfgDAO#findPxGoodsCostCfgById(java.lang.String)
     */
    @Override
    public PxGoodsCostCfgDO findPxGoodsCostCfgById(String goodsId) {
        return this.getSqlSession().selectOne("PX-GOODS-COST-CFG-GET-BY-GOODS-ID", goodsId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsCostCfgDAO#findPxGoodsCostCfgAll(java.lang.String)
     */
    @Override
    public List<PxGoodsCostCfgAdvDO> findPxGoodsCostCfgAll(String shopId) {
        return this.getSqlSession().selectList("PX-GOODS-COST-CFG-SELECT-ALL-BY-SHOP", shopId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsCostCfgDAO#updatePxGoodsCostCfg(com.myteay.phoenix.common.dal.dataobject.PxGoodsCostCfgDO)
     */
    @Override
    public void updatePxGoodsCostCfg(PxGoodsCostCfgDO pxGoodsCostCfgDO) {
        if (pxGoodsCostCfgDO == null) {
            throw new IllegalArgumentException("Can't update by a null data object.");
        }

        this.getSqlSession().update("PX-GOODS-COST-CFG-UPDATE-GET-BY-ID", pxGoodsCostCfgDO);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsCostCfgDAO#findPxGoodsCostCfgsAll()
     */
    @Override
    public List<PxGoodsCostCfgAdvDO> findPxGoodsCostCfgsAll() {
        return this.getSqlSession().selectList("PX-GOODS-COST-CFG-SELECT-ALL");
    }

}
