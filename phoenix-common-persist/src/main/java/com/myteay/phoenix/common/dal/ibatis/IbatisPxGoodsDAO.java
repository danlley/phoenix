/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.myteay.phoenix.common.dal.daointerface.PxGoodsDAO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsDO;

/**
 * 商品概要操作DAO
 * 
 * @author min.weixm
 * @version $Id: IbatisPxGoodsDAO.java, v 0.1 Jul 26, 2018 10:36:48 AM min.weixm Exp $
 */
public class IbatisPxGoodsDAO extends SqlSessionDaoSupport implements PxGoodsDAO {

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsDAO#insert(com.myteay.phoenix.common.dal.dataobject.PxGoodsDO)
     */
    @Override
    public String insert(PxGoodsDO pxGoodsDO) {
        if (pxGoodsDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        this.getSqlSession().insert("PX-GOODS-INSERT", pxGoodsDO);

        return pxGoodsDO.getGoodsId();
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsDAO#findPxGoodsAll()
     */
    @Override
    public List<PxGoodsDO> findPxGoodsAll() {
        return this.getSqlSession().selectList("PX-GOODS-SELECT-ALL");
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsDAO#findPxGoodsById(java.lang.String)
     */
    @Override
    public PxGoodsDO findPxGoodsById(String goodsId) {
        return this.getSqlSession().selectOne("PX-GOODS-SELECT-GET-BY-ID", goodsId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsDAO#updatePxGoods(com.myteay.phoenix.common.dal.dataobject.PxGoodsDO)
     */
    @Override
    public void updatePxGoods(PxGoodsDO pxGoodsDO) {
        if (pxGoodsDO == null) {
            throw new IllegalArgumentException("Can't update by a null data object.");
        }

        this.getSqlSession().update("PX-GOODS-UPDATE-GET-BY-ID", pxGoodsDO);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsDAO#deleteById(java.lang.String)
     */
    @Override
    public void deleteById(String goodsId) {
        this.getSqlSession().delete("PX-GOODS-DELETE-GET-BY-ID", goodsId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsDAO#findPxGoodsByShopId(java.lang.String)
     */
    @Override
    public List<PxGoodsDO> findPxGoodsByShopId(String shopId) {
        return this.getSqlSession().selectList("PX-GOODS-SELECT-GET-BY-SHOP-ID", shopId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsDAO#findPxShopOnlineGoodsByShopId(java.lang.String)
     */
    @Override
    public List<PxGoodsDO> findPxShopOnlineGoodsByShopId(String shopId) {
        return this.getSqlSession().selectList("PX-SHOP-ONLINE-GOODS-SELECT-ALL", shopId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsDAO#findPxShopOnlineGoodsByCondition(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public List<PxGoodsDO> findPxShopOnlineGoodsByCondition(String shopId, String goodsType, String goodsTitle) {
        Map<String, String> param = new HashMap<>();
        param.put("shopId", shopId);
        param.put("goodsType", goodsType);
        param.put("goodsTitle", goodsTitle);
        return this.getSqlSession().selectList("PX-GOODS-SELECT-GET-BY-CONDITION", param);
    }

}
