/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.component;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampPrizeModel;

/**
 * 店内营销活动奖品组件
 * 
 * @author danlley
 * @version $Id: CampShopPrizeComponent.java, v 0.1 Dec 17, 2018 11:03:09 PM danlley Exp $
 */
public interface CampShopPrizeComponent {

    /**
     * 店内营销活动奖品信息管理
     * 
     * @param campPrizeModel
     * @return
     */
    public MtOperateResult<CampPrizeModel> manageCampPrize(CampPrizeModel campPrizeModel);

    /**
     * 修改店内营销活动奖品信息信息
     * 
     * @param campPrizeModel
     * @return
     */
    public MtOperateResult<CampPrizeModel> modifyCampPrizeModel(CampPrizeModel campPrizeModel);

    /**
     * 通过活动ID查询店内营销活动奖品信息模型（内部组件公用）
     * 
     * @param prizeId
     * @return
     */
    public CampPrizeModel queryCampPrizeModelByPrizeId(String prizeId);
}
