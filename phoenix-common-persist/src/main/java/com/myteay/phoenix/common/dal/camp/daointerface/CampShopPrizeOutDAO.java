/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.camp.daointerface;

import java.util.List;

import com.myteay.phoenix.common.dal.camp.dataobject.CampShopPrizeOutDO;

/**
 * 奖品流水操作DAO
 * 
 * @author danlley
 * @version $Id: CampShopPrizeOutDAO.java, v 0.1 Mar 27, 2019 10:28:42 PM danlley Exp $
 */
public interface CampShopPrizeOutDAO {

    /**
     * 根据中奖流水号删除指定的奖品信息
     * 
     * @param campPrizeOutId
     * @param prizeOutStatus
     */
    public void deleteById(String campPrizeOutId, String prizeOutStatus);

    /**
     * 插入抽奖结果流水
     * 
     * @param campShopPrizeOutDO
     * @return
     */
    String insert(CampShopPrizeOutDO campShopPrizeOutDO);

    /**
     * 通过奖品流水ID，查询指定的奖品信息
     * 
     * @param campPrizeOutId
     * @return
     */
    CampShopPrizeOutDO selectCampShopPrizeOutById(String campPrizeOutId);

    /**
     * 通过状态查询奖品列表，每次查询100条
     * 
     * @param campPrizeOutId
     * @return
     */
    List<CampShopPrizeOutDO> selectCampShopPrizeOutListById(String prizeOutStatus);

    /**
     * 变更中奖流水状态
     * 
     * @param campShopPrizeOutDO
     */
    public void updateCampShopPrizeOut(CampShopPrizeOutDO campShopPrizeOutDO);
}
