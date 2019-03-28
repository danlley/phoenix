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
     * 保存抽奖流水
     * 
     * @param campShopPrizeOutModel
     * @return
     */
    public String saveCampShopPrizeOut(CampShopPrizeOutModel campShopPrizeOutModel) throws PxManageException;
}
