/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.camp.daointerface;

import java.util.List;

import com.myteay.phoenix.common.dal.camp.dataobject.CampPrizeGoodsRefDO;

/**
 * 奖品关联商品操作DAO
 * 
 * @author danlley
 * @version $Id: CampSingleShopPrizeGoodsRefDAO.java, v 0.1 Dec 19, 2018 10:30:37 PM danlley Exp $
 */
public interface CampSingleShopPrizeGoodsRefDAO {
    /**
     * 插入奖品关联的商品列表信息
     * 
     * @param campPrizeGoodsRefDO   数据模型
     * @return
     */
    String insert(CampPrizeGoodsRefDO campPrizeGoodsRefDO);

    /**
     * 通过奖品ID查询所有已经关联了该奖品的商品
     * 
     * @param prizeId
     * @return
     */
    List<CampPrizeGoodsRefDO> findPrizeGoodsRefByPrizeId(String prizeId);

    /**
     * 通过商品ID查询所有已发布营销活动中已经关联了奖品的商品
     * 
     * @param goodsId
     * @return
     */
    List<CampPrizeGoodsRefDO> findPrizeGoodsRefByGoodsId(String goodsId);

    /**
     * 通过奖品ID删除营销活动关联商品信息
     * 
     * @param prizeId
     */
    void deleteById(String prizeId);
}
