/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.camp.daointerface;

import com.myteay.phoenix.common.dal.camp.dataobject.CampShopPrizeOutDO;

/**
 * 奖品流水操作DAO
 * 
 * @author danlley
 * @version $Id: CampShopPrizeOutDAO.java, v 0.1 Mar 27, 2019 10:28:42 PM danlley Exp $
 */
public interface CampShopPrizeOutDAO {

    /**
     * 插入抽奖结果流水
     * 
     * @param campShopPrizeOutDO
     * @return
     */
    String insert(CampShopPrizeOutDO campShopPrizeOutDO);
}
