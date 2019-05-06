/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.camp.daointerface;

import com.myteay.phoenix.common.dal.camp.dataobject.CampShopPrizeOutHistoryDO;

/**
 * 奖品流水历史操作DAO
 * 
 * @author danlley
 * @version $Id: CampShopPrizeOutHistoryDAO.java, v 0.1 Apr 21, 2019 5:59:50 PM danlley Exp $
 */
public interface CampShopPrizeOutHistoryDAO {

    /**
     * 插入抽奖结果流水
     * 
     * @param campShopPrizeOutHistoryDO
     * @return
     */
    String insert(CampShopPrizeOutHistoryDO campShopPrizeOutHistoryDO);

    /**
     * 通过奖品流水ID，查询指定的奖品信息
     * 
     * @param campPrizeOutId
     * @return
     */
    CampShopPrizeOutHistoryDO selectCampShopPrizeOutById(String campPrizeOutId);
}
