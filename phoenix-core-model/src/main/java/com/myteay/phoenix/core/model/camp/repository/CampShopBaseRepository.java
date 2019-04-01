/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.camp.repository;

import java.util.List;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.camp.CampBaseModel;

/**
 * 针对单个店铺店内消费到场营销活动操作仓储
 * 
 * @author danlley
 * @version $Id: CampShopBaseRepository.java, v 0.1 Dec 16, 2018 10:40:26 PM danlley Exp $
 */
public interface CampShopBaseRepository {

    /**
     * 查询已经过期的营销活动
     * 
     * @return
     * @throws PxManageException
     */
    public List<CampBaseModel> findCampBaseOnlineExpired() throws PxManageException;

    /**
     * 删除单个店铺店内消费到场营销活动
     * 
     * @param campBaseModel
     * @throws PxManageException 
     */
    public void removeGoodsInfo(CampBaseModel campBaseModel) throws PxManageException;

    /**
     * 修改单个店铺店内消费到场营销活动信息
     * 
     * @param campBaseModel
     * @return
     * @throws PxManageException 
     */
    public CampBaseModel modifyGoodsInfo(CampBaseModel campBaseModel) throws PxManageException;

    /**
     * 保存单个店铺店内消费到场营销活动模型
     * 
     * @param campBaseModel
     * @return
     * @throws PxManageException 
     */
    public CampBaseModel saveGoodsInfo(CampBaseModel campBaseModel) throws PxManageException;

    /**
     * 查询所有单个店铺店内消费到场营销活动信息
     * 
     * @return
     * @throws PxManageException 
     */
    public List<CampBaseModel> findAll() throws PxManageException;

    /**
     * 查询所有单个店铺店内消费到场营销活动信息
     * 
     * @return
     * @throws PxManageException 
     */
    public List<CampBaseModel> findCampBaseOnlineAll() throws PxManageException;

    /**
     * 查询单个单个店铺店内消费到场营销活动信息
     * 
     * @param campId
     * @return
     * @throws PxManageException
     */
    public CampBaseModel findSingleCampBase(String campId) throws PxManageException;

    /**
     * 通过店铺查询单个店铺店内消费到场营销活动信息列表
     * 
     * @param shopId
     * @return
     * @throws PxManageException
     */
    public List<CampBaseModel> findCampBaseByShopId(String shopId) throws PxManageException;

    /**
     * 通过店铺查询已发布单个店铺店内消费到场营销活动信息列表
     * 
     * @param shopId
     * @return
     * @throws PxManageException
     */
    public List<CampBaseModel> findShopOnlineCampByShopId(String shopId) throws PxManageException;
}
