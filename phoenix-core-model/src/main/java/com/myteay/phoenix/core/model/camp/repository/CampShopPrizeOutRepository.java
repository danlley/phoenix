/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.camp.repository;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel;

/**
 * 抽奖流水操作仓储
 * 
 * @author danlley
 * @version $Id: CampShopPrizeOutRepository.java, v 0.1 Mar 27, 2019 11:27:59 PM danlley Exp $
 */
public interface CampShopPrizeOutRepository {

    /**
     * 通过中奖流水ID，变更中奖记录状态
     * 
     * @param campShopPrizeOutModel
     * @throws PxManageException 
     */
    public void modifyCampShopPrizeOutStatusById(CampShopPrizeOutModel campShopPrizeOutModel) throws PxManageException;

    /**
     * 保存抽奖流水
     * 
     * @param campShopPrizeOutModel
     * @return
     */
    public String saveCampShopPrizeOut(CampShopPrizeOutModel campShopPrizeOutModel) throws PxManageException;

    /**
     * 查询中奖优惠券信息
     * 
     * @param campPrizeOutId
     * @return
     * @throws PxManageException
     */
    public CampShopPrizeOutModel queryCampShopPrizeOutById(String campPrizeOutId) throws PxManageException;
}