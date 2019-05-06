/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.camp.repository;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel;

/**
 * 中奖历史管理仓储
 * 
 * @author danlley
 * @version $Id: CampShopPrizeOutHistoryRepository.java, v 0.1 Apr 22, 2019 12:23:23 AM danlley Exp $
 */
public interface CampShopPrizeOutHistoryRepository {

    /**
     * 保存抽奖历史
     * 
     * @param campShopPrizeOutModel
     * @return
     */
    public String saveCampShopPrizeOutHistory(CampShopPrizeOutModel campShopPrizeOutModel) throws PxManageException;

    /**
     * 查询历史中奖优惠券信息
     * 
     * @param campPrizeOutId
     * @return
     * @throws PxManageException
     */
    public CampShopPrizeOutModel queryCampShopPrizeOutHistoryById(String campPrizeOutId) throws PxManageException;
}
