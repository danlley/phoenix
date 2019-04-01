/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.camp.daointerface;

import java.util.List;

import com.myteay.phoenix.common.dal.camp.dataobject.CampPrizeDO;

/**
 * 店内营销活动奖品操作DAO
 * 
 * @author danlley
 * @version $Id: CampSingleShopPrizeDAO.java, v 0.1 Dec 17, 2018 6:31:48 PM danlley Exp $
 */
public interface CampSingleShopPrizeDAO {

    /**
     * 查询店铺中已经过期的奖品
     * 
     * @return
     */
    public List<CampPrizeDO> findCampPrizeExpired();

    /**
     * 插入店内营销活动奖品信息
     * 
     * @param campPrizeDO   数据模型
     * @return
     */
    String insert(CampPrizeDO campPrizeDO);

    /**
     * 查询所有店内营销活动奖品信息(用于定时任务定时下架奖品)
     * 
     * @return
     */
    List<CampPrizeDO> findCampPrizeAll();

    /**
     * 通过店内营销活动奖品ID查询指定的店内营销活动奖品信息
     * 
     * @param prizeId
     * @return
     */
    CampPrizeDO findCampPrizeById(String prizeId);

    /**
     * 通过活动ID查询指定的店内营销活动奖品信息列表
     * 
     * @param campId
     * @return
     */
    List<CampPrizeDO> findCampPrizeByCampId(String campId);

    /**
     * 通过活动ID查询指定的已发布店内营销活动奖品信息列表(用于抽奖主流程对活动缓存进行初始化)
     * 
     * @param campId
     * @return
     */
    List<CampPrizeDO> findCampPrizeOnlineByCampId(String campId);

    /**
     * 更新店内营销活动奖品配置信息
     * 
     * @param campPrizeDO
     */
    void updateCampPrize(CampPrizeDO campPrizeDO);

    /**
     * 通过ID删除店内营销活动奖品信息
     * 
     * @param prizeId
     */
    void deleteById(String prizeId);
}
