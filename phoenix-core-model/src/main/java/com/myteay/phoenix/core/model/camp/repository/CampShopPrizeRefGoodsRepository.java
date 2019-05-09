/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.camp.repository;

import java.util.List;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.camp.CampPrizeRefGoodsModel;

/**
 * 店内消费营销活动奖品关联商品仓储
 * 
 * @author danlley
 * @version $Id: CampShopPrizeRefGoodsRepository.java, v 0.1 Dec 19, 2018 10:56:15 PM danlley Exp $
 */
public interface CampShopPrizeRefGoodsRepository {

    /**
     * 修改店内到场营销活动奖品关联商品列表信息
     * 
     * @param campPrizeRefGoodsModelList
     * @return
     * @throws PxManageException 
     */
    public List<CampPrizeRefGoodsModel> modifyPrizeRefGoodsInfo(List<CampPrizeRefGoodsModel> campPrizeRefGoodsModelList) throws PxManageException;

    /**
     * 清空当前奖品关联的所有可抵扣商品
     * 
     * @param prizeId
     * @throws PxManageException
     */
    public void cleanPrizeRefGoodsInfo(String prizeId) throws PxManageException;

    /**
     * 通过奖品ID查询已发布店内到场营销活动奖品关联商品列表信息
     * 
     * @param prizeId
     * @return
     * @throws PxManageException
     */
    public List<CampPrizeRefGoodsModel> findPrizeRefGoodsByPrizeId(String prizeId) throws PxManageException;

    /**
     * 检查当前商品是否已经关联了运营中的营销活动，如果已经关联，返回true，反之为true
     * 
     * @param goodsId
     * @return
     */
    public boolean isRefedGoodsByCampPrize(String goodsId) throws PxManageException;
}
