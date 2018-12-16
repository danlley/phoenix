/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.camp.ibatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopBaseDAO;
import com.myteay.phoenix.common.dal.camp.dataobject.CampBaseDO;

/**
 * 针对单个店铺店内消费到场营销活动操作DAO
 * 
 * @author danlley
 * @version $Id: IbatisCampSingleShopBaseDAO.java, v 0.1 Dec 16, 2018 7:16:51 PM danlley Exp $
 */
public class IbatisCampSingleShopBaseDAO extends SqlSessionDaoSupport implements CampSingleShopBaseDAO {

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopBaseDAO#insert(com.myteay.phoenix.common.dal.camp.dataobject.CampBaseDO)
     */
    @Override
    public String insert(CampBaseDO campBaseDO) {
        if (campBaseDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        this.getSqlSession().insert("CAMP-SHOP-BASE-INSERT", campBaseDO);

        return campBaseDO.getCampId();
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopBaseDAO#findPxGoodsAll()
     */
    @Override
    public List<CampBaseDO> findPxGoodsAll() {
        return this.getSqlSession().selectList("CAMP-SHOP-BASE-SELECT-ALL");
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopBaseDAO#findCampBaseOnlineByShopId(java.lang.String)
     */
    @Override
    public List<CampBaseDO> findCampBaseOnlineByShopId(String shopId) {
        return this.getSqlSession().selectList("CAMP-SHOP-ONLINE-CAMP-SELECT-ALL", shopId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopBaseDAO#findCampBaseById(java.lang.String)
     */
    @Override
    public CampBaseDO findCampBaseById(String campId) {
        return this.getSqlSession().selectOne("CAMP-SHOP-BASE-SELECT-GET-BY-ID", campId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopBaseDAO#findCampBaseByShopId(java.lang.String)
     */
    @Override
    public List<CampBaseDO> findCampBaseByShopId(String shopId) {
        return this.getSqlSession().selectList("CAMP-SHOP-BASE-SELECT-GET-BY-SHOP-ID", shopId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopBaseDAO#updateCampBase(com.myteay.phoenix.common.dal.camp.dataobject.CampBaseDO)
     */
    @Override
    public void updateCampBase(CampBaseDO campBaseDO) {
        if (campBaseDO == null) {
            throw new IllegalArgumentException("Can't update by a null data object.");
        }

        this.getSqlSession().update("CAMP-SHOP-BASE-UPDATE-GET-BY-ID", campBaseDO);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopBaseDAO#deleteById(java.lang.String)
     */
    @Override
    public void deleteById(String campId) {
        this.getSqlSession().delete("CAMP-SHOP-BASE-DELETE-GET-BY-ID", campId);
    }
}
