/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.camp.ibatis;

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

}
