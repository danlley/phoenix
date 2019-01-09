/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.camp.ibatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.myteay.phoenix.common.dal.camp.daointerface.CampAlgorithmDAO;
import com.myteay.phoenix.common.dal.camp.dataobject.CampAlgorithmDO;

/**
 * 抽奖算法管理DAO
 * 
 * @author danlley
 * @version $Id: IbatisCampAlgorithmDAO.java, v 0.1 Jan 9, 2019 10:58:56 PM danlley Exp $
 */
public class IbatisCampAlgorithmDAO extends SqlSessionDaoSupport implements CampAlgorithmDAO {

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampAlgorithmDAO#insert(com.myteay.phoenix.common.dal.camp.dataobject.CampAlgorithmDO)
     */
    @Override
    public String insert(CampAlgorithmDO campAlgorithmDO) {
        if (campAlgorithmDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        this.getSqlSession().insert("CAMP-ALGORITHM-INSERT", campAlgorithmDO);

        return campAlgorithmDO.getPrizeId();
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampAlgorithmDAO#findCampAlgorithmByPrizeId(java.lang.String)
     */
    @Override
    public CampAlgorithmDO findCampAlgorithmByPrizeId(String prizeId) {
        // TODO Auto-generated method stub
        return this.getSqlSession().selectOne("CAMP-ALGORITHM-SELECT-GET-BY-ID", prizeId);
    }

}
