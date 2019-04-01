/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.camp.repository;

import java.util.List;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.camp.CampPrizeModel;

/**
 * 店内营销活动奖池仓储
 * 
 * @author danlley
 * @version $Id: CampShopPrizeRepository.java, v 0.1 Dec 17, 2018 10:30:43 PM danlley Exp $
 */
public interface CampShopPrizeRepository {

    /**
     * 查询店铺中已经过期的奖品
     * 
     * @return
     * @throws PxManageException
     */
    public List<CampPrizeModel> findCampPrizeExpired() throws PxManageException;

    /**
     * 删除单个店铺店内消费到场营销活动奖品
     * 
     * @param campPrizeModel
     * @throws PxManageException 
     */
    public void removeCampPrizeInfo(CampPrizeModel campPrizeModel) throws PxManageException;

    /**
     * 修改单个店铺店内消费到场营销活动奖品信息
     * 
     * @param campPrizeModel
     * @return
     * @throws PxManageException 
     */
    public CampPrizeModel modifyCampPrizeInfo(CampPrizeModel campPrizeModel) throws PxManageException;

    /**
     * 保存单个店铺店内消费到场营销活动奖品模型
     * 
     * @param campPrizeModel
     * @return
     * @throws PxManageException 
     */
    public CampPrizeModel saveCampPrizeInfo(CampPrizeModel campPrizeModel) throws PxManageException;

    /**
     * 查询所有单个店铺店内消费到场营销活动奖品信息
     * 
     * @return
     * @throws PxManageException 
     */
    public List<CampPrizeModel> findAll() throws PxManageException;

    /**
     * 查询单个单个店铺店内消费到场营销活动奖品信息
     * 
     * @param prizeId
     * @return
     * @throws PxManageException
     */
    public CampPrizeModel findSingleCampPrizeById(String prizeId) throws PxManageException;

    /**
     * 通过店铺查询单个店铺店内消费到场营销活动奖品信息列表
     * 
     * @param campId
     * @return
     * @throws PxManageException
     */
    public List<CampPrizeModel> findCampPrizeByCampId(String campId) throws PxManageException;

    /**
     * 通过店铺查询已发布单个店铺店内消费到场营销活动奖品信息列表
     * 
     * @param campId
     * @return
     * @throws PxManageException
     */
    public List<CampPrizeModel> findShopOnlinePrizeByCampId(String campId) throws PxManageException;
}
