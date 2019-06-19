/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.camp.integration;

import java.util.List;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampPrizeRefGoodsModel;

/**
 * 
 * @author min.weixm
 * @version $Id: CampPrizeRefGoodsIntg.java, v 0.1 Jun 19, 2019 3:23:00 PM min.weixm Exp $
 */
public interface CampPrizeRefGoodsIntg {

    /**
     * 通过奖品ID查询店内营销活动关联奖品信息
     * 
     * @param prizeId
     * @return
     */
    public MtOperateResult<List<CampPrizeRefGoodsModel>> queryPrizeRefGoodsByPrizeId(String prizeId);

    /**
     * 店内营销活动关联奖品信息管理
     * 
     * @param prizeId
     * @param campPrizeRefGoodsModelList
     * @return
     */
    public MtOperateResult<List<CampPrizeRefGoodsModel>> managePrizeGoodsRefList(String prizeId, List<CampPrizeRefGoodsModel> campPrizeRefGoodsModelList);
}
