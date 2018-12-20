/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.camp.daointerface;

import java.util.List;

import com.myteay.phoenix.common.dal.camp.dataobject.CampBaseDO;

/**
 * 针对单个店铺店内消费到场营销活动操作DAO
 * 
 * @author danlley
 * @version $Id: CampSingleShopBaseDAO.java, v 0.1 Dec 16, 2018 6:46:29 PM danlley Exp $
 */
public interface CampSingleShopBaseDAO {
    /**
     * 插入营销活动基本信息
     * 
     * @param campBaseDO   数据模型
     * @return
     */
    String insert(CampBaseDO campBaseDO);

    /**
     * 查询所有营销活动基本信息(用于定时任务定时关停过期活动)
     * 
     * @return
     */
    List<CampBaseDO> findCampBaseAll();

    /**
     * 通过营销活动基本ID查询指定的营销活动基本信息
     * 
     * @param campId
     * @return
     */
    CampBaseDO findCampBaseById(String campId);

    /**
     * 通过店铺ID查询指定的营销活动基本信息列表
     * 
     * @param shopId
     * @return
     */
    List<CampBaseDO> findCampBaseByShopId(String shopId);

    /**
     * 通过店铺ID查询指定的已发布营销活动基本信息列表(用于抽奖主流程对活动缓存进行初始化)
     * 
     * @param shopId
     * @return
     */
    List<CampBaseDO> findCampBaseOnlineByShopId(String shopId);

    /**
     * 查询所有已发布营销活动基本信息列表(用于抽奖主流程对活动缓存进行初始化)
     * 
     * @return
     */
    List<CampBaseDO> findCampBaseOnline();

    /**
     * 更新营销活动基本配置信息
     * 
     * @param campBaseDO
     */
    void updateCampBase(CampBaseDO campBaseDO);

    /**
     * 通过ID删除营销活动基本信息
     * 
     * @param campId
     */
    void deleteById(String campId);
}
