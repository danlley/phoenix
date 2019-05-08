/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.camp.ibatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.myteay.phoenix.common.dal.camp.daointerface.CampShopPrizeOutDAO;
import com.myteay.phoenix.common.dal.camp.dataobject.CampShopPrizeOutDO;

/**
 * 奖品流水操作DAO
 * 
 * @author danlley
 * @version $Id: IbatisCampShopPrizeOutDAO.java, v 0.1 Mar 27, 2019 10:30:53 PM danlley Exp $
 */
public class IbatisCampShopPrizeOutDAO extends SqlSessionDaoSupport implements CampShopPrizeOutDAO {

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampShopPrizeOutDAO#insert(com.myteay.phoenix.common.dal.camp.dataobject.CampShopPrizeOutDO)
     */
    @Override
    public String insert(CampShopPrizeOutDO campShopPrizeOutDO) {
        if (campShopPrizeOutDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        this.getSqlSession().insert("CAMP-SHOP-PRIZE-OUT-INSERT", campShopPrizeOutDO);
        return campShopPrizeOutDO.getCampPrizeOutId();
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampShopPrizeOutDAO#selectCampShopPrizeOutById(java.lang.String)
     */
    @Override
    public CampShopPrizeOutDO selectCampShopPrizeOutById(String campPrizeOutId) {
        return this.getSqlSession().selectOne("PX-SHOP-PRIZE-OUT-SELECT-GET-BY-ID", campPrizeOutId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampShopPrizeOutDAO#updateCampShopPrizeOut(com.myteay.phoenix.common.dal.camp.dataobject.CampShopPrizeOutDO)
     */
    @Override
    public void updateCampShopPrizeOut(CampShopPrizeOutDO campShopPrizeOutDO) {
        if (campShopPrizeOutDO == null) {
            throw new IllegalArgumentException("Can't update by a null data object.");
        }

        this.getSqlSession().update("CAMP-SHOP-PRIZE-OUT-UPDATE-BY-ID", campShopPrizeOutDO);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampShopPrizeOutDAO#deleteById(java.lang.String, java.lang.String)
     */
    @Override
    public void deleteById(String campPrizeOutId, String prizeOutStatus) {
        this.getSqlSession().delete("PX-SHOP-PRIZE-OUT-DELETE-GET-BY-ID", campPrizeOutId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampShopPrizeOutDAO#selectCampShopPrizeOutListByStatus(java.lang.String)
     */
    @Override
    public List<CampShopPrizeOutDO> selectCampShopPrizeOutListByStatus(String prizeOutStatus) {
        return this.getSqlSession().selectList("PX-SHOP-PRIZE-OUT-SELECT-GET-OUT-STATUS", prizeOutStatus);
    }

}
