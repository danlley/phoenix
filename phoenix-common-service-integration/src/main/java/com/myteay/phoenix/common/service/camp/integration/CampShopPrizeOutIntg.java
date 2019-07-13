/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.camp.integration;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel;

/**
 * 
 * @author min.weixm
 * @version $Id: CampShopPrizeOutIntg.java, v 0.1 Jun 19, 2019 3:56:16 PM min.weixm Exp $
 */
public interface CampShopPrizeOutIntg {

    /**
     * 查询已发放奖品信息
     * 
     * @param prizeOutId
     * @return
     */
    public MtOperateResult<CampShopPrizeOutModel> queryShopPrizeOutById(String prizeOutId);

}
