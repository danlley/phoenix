/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.camp.ibatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopPrizeDAO;
import com.myteay.phoenix.common.dal.camp.dataobject.CampPrizeDO;

/**
 * 店内营销活动奖品操作DAO
 * 
 * @author danlley
 * @version $Id: IbatisCampSingleShopPrizeDAO.java, v 0.1 Dec 17, 2018 6:47:57 PM danlley Exp $
 */
public class IbatisCampSingleShopPrizeDAO extends SqlSessionDaoSupport implements CampSingleShopPrizeDAO {

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopPrizeDAO#insert(com.myteay.phoenix.common.dal.camp.dataobject.CampPrizeDO)
     */
    @Override
    public String insert(CampPrizeDO campPrizeDO) {
        if (campPrizeDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        this.getSqlSession().insert("CAMP-PRIZE-INSERT", campPrizeDO);

        return campPrizeDO.getCampId();
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopPrizeDAO#findCampPrizeAll()
     */
    @Override
    public List<CampPrizeDO> findCampPrizeAll() {
        return this.getSqlSession().selectList("CAMP-PRIZE-SELECT-ALL");
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopPrizeDAO#findCampPrizeById(java.lang.String)
     */
    @Override
    public CampPrizeDO findCampPrizeById(String prizeId) {
        return this.getSqlSession().selectOne("CAMP-PRIZE-SELECT-GET-BY-ID", prizeId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopPrizeDAO#findCampPrizeByCampId(java.lang.String)
     */
    @Override
    public List<CampPrizeDO> findCampPrizeByCampId(String campId) {
        return this.getSqlSession().selectList("CAMP-PRIZE-SELECT-GET-BY-CAMP-ID", campId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopPrizeDAO#findCampPrizeOnlineByCampId(java.lang.String)
     */
    @Override
    public List<CampPrizeDO> findCampPrizeOnlineByCampId(String campId) {
        return this.getSqlSession().selectList("CAMP-PRIZE-ONLINE-SELECT-ALL", campId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopPrizeDAO#updateCampPrize(com.myteay.phoenix.common.dal.camp.dataobject.CampPrizeDO)
     */
    @Override
    public void updateCampPrize(CampPrizeDO campPrizeDO) {
        if (campPrizeDO == null) {
            throw new IllegalArgumentException("Can't update by a null data object.");
        }

        this.getSqlSession().update("CAMP-PRIZE-DELETE-BY-ID", campPrizeDO);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopPrizeDAO#deleteById(java.lang.String)
     */
    @Override
    public void deleteById(String prizeId) {
        this.getSqlSession().delete("CAMP-SHOP-BASE-DELETE-GET-BY-ID", prizeId);
    }
}
