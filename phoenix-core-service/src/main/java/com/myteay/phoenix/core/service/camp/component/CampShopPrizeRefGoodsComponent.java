/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.component;

import java.util.List;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampPrizeRefGoodsModel;

/**
 * 店内到场营销活动奖品关联商品管理组件
 * 
 * @author danlley
 * @version $Id: CampShopPrizeRefGoodsComponent.java, v 0.1 Dec 20, 2018 1:56:51 AM danlley Exp $
 */
public interface CampShopPrizeRefGoodsComponent {

    /**
     * 管理店内到场营销活动奖品关联商品列表信息
     * 
     * @param prizeId
     * @param campPrizeRefGoodsModelList
     * @return
     * @throws PxManageException
     */
    public MtOperateResult<List<CampPrizeRefGoodsModel>> managePrizeRefGoodsInfo(String prizeId,
                                                                                 List<CampPrizeRefGoodsModel> campPrizeRefGoodsModelList) throws PxManageException;

    /**
     * 通过奖品ID查询已发布店内到场营销活动奖品关联商品列表信息
     * 
     * @param prizeId
     * @return
     * @throws PxManageException
     */
    public MtOperateResult<List<CampPrizeRefGoodsModel>> findPrizeRefGoodsByPrizeId(String prizeId) throws PxManageException;

}
