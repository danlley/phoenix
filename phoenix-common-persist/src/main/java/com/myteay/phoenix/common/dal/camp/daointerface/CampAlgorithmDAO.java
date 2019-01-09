/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.camp.daointerface;

import com.myteay.phoenix.common.dal.camp.dataobject.CampAlgorithmDO;

/**
 * 抽奖算法管理DAO
 * 
 * @author danlley
 * @version $Id: CampAlgorithmDAO.java, v 0.1 Jan 9, 2019 10:48:42 PM danlley Exp $
 */
public interface CampAlgorithmDAO {
    /**
     * 插入营销活动算法基本信息
     * 
     * @param campAlgorithmDO   数据模型
     * @return
     */
    String insert(CampAlgorithmDO campAlgorithmDO);

    /**
     * 通过营销活动奖品ID查询指定的营销活动算法信息
     * 
     * @param prizeId
     * @return
     */
    CampAlgorithmDO findCampAlgorithmByPrizeId(String prizeId);
}
