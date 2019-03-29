/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.component;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel;

/**
 * 中奖流水管理组件
 * 
 * @author danlley
 * @version $Id: CampShopPrizeOutComponent.java, v 0.1 Mar 29, 2019 6:48:53 PM danlley Exp $
 */
public interface CampShopPrizeOutComponent {

    /**
     * 通过中奖号码查询中奖模型
     * 
     * @param campPrizeOutId
     * @return
     * @throws PxManageException
     */
    public MtOperateResult<CampShopPrizeOutModel> queryCampShopPrizeOutById(String campPrizeOutId);
}
