/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.camp.ibatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopPrizeGoodsRefDAO;
import com.myteay.phoenix.common.dal.camp.dataobject.CampPrizeGoodsRefDO;

/**
 * 奖品关联商品操作DAO
 * 
 * @author danlley
 * @version $Id: IbatisCampSingleShopPrizeGoodsRefDAO.java, v 0.1 Dec 19, 2018 10:42:36 PM danlley Exp $
 */
public class IbatisCampSingleShopPrizeGoodsRefDAO extends SqlSessionDaoSupport implements CampSingleShopPrizeGoodsRefDAO {

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopPrizeGoodsRefDAO#insert(com.myteay.phoenix.common.dal.camp.dataobject.CampPrizeGoodsRefDO)
     */
    @Override
    public String insert(CampPrizeGoodsRefDO campPrizeGoodsRefDO) {
        if (campPrizeGoodsRefDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        this.getSqlSession().insert("CAMP-SHOP-PRIZE-REF-GOODS-INSERT", campPrizeGoodsRefDO);

        return campPrizeGoodsRefDO.getPrizeId();
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopPrizeGoodsRefDAO#findPrizeGoodsRefByPrizeId(java.lang.String)
     */
    @Override
    public List<CampPrizeGoodsRefDO> findPrizeGoodsRefByPrizeId(String prizeId) {
        return this.getSqlSession().selectList("CAMP-SHOP-PRIZE-REF-GOODS-SELECT-GET-BY-ID", prizeId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopPrizeGoodsRefDAO#deleteById(java.lang.String)
     */
    @Override
    public void deleteById(String prizeId) {
        this.getSqlSession().delete("CAMP-PRIZE-REF-GOODS-DELETE-GET-BY-ID", prizeId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopPrizeGoodsRefDAO#findPrizeGoodsRefByGoodsId(java.lang.String)
     */
    @Override
    public List<CampPrizeGoodsRefDO> findPrizeGoodsRefByGoodsId(String goodsId) {
        return this.getSqlSession().selectList("CAMP-SHOP-PRIZE-REF-GOODS-SELECT-GET-BY-GOODS-ID", goodsId);
    }

}
