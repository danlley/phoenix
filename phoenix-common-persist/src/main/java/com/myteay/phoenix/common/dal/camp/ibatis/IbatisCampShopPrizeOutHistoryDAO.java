/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.camp.ibatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.myteay.phoenix.common.dal.camp.daointerface.CampShopPrizeOutHistoryDAO;
import com.myteay.phoenix.common.dal.camp.dataobject.CampShopPrizeOutHistoryDO;

/**
 * 奖品流水历史操作DAO
 * 
 * @author danlley
 * @version $Id: IbatisCampShopPrizeOutHistoryDAO.java, v 0.1 Apr 21, 2019 6:37:14 PM danlley Exp $
 */
public class IbatisCampShopPrizeOutHistoryDAO extends SqlSessionDaoSupport implements CampShopPrizeOutHistoryDAO {

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampShopPrizeOutHistoryDAO#insert(com.myteay.phoenix.common.dal.camp.dataobject.CampShopPrizeOutHistoryDO)
     */
    @Override
    public String insert(CampShopPrizeOutHistoryDO campShopPrizeOutHistoryDO) {
        if (campShopPrizeOutHistoryDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        this.getSqlSession().insert("CAMP-SHOP-PRIZE-OUT-HIS-INSERT", campShopPrizeOutHistoryDO);
        return campShopPrizeOutHistoryDO.getCampPrizeOutId();
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampShopPrizeOutHistoryDAO#selectCampShopPrizeOutById(java.lang.String)
     */
    @Override
    public CampShopPrizeOutHistoryDO selectCampShopPrizeOutById(String campPrizeOutId) {
        return this.getSqlSession().selectOne("PX-SHOP-PRIZE-OUT-HIS-SELECT-GET-BY-ID", campPrizeOutId);
    }

}
