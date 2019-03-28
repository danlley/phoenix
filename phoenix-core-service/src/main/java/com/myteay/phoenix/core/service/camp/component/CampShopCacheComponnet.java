/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.component;

import com.myteay.phoenix.core.model.camp.CampBaseModel;
import com.myteay.phoenix.core.model.camp.CampPrizeModel;
import com.myteay.phoenix.core.model.manage.PxShopModel;

/**
 * 到店消费营销活动基础数据缓存
 * 
 * @author danlley
 * @version $Id: CampShopCacheComponnet.java, v 0.1 Dec 20, 2018 9:48:04 PM danlley Exp $
 */
public interface CampShopCacheComponnet {

    /**
     * 在缓存中查找活动基本信息模型
     * 
     * @param campId
     * @return
     */
    public CampBaseModel queryCampBaseModelFromCache(String campId);

    /**
     * 在缓存中查询特定的奖品信息模型
     * 
     * @param campId
     * @param prizeId
     * @return
     */
    public CampPrizeModel queryCampPrizeModelFromCache(String campId, String prizeId);

    /**
     * 刷新活动缓存
     * 
     * 刷新条件：1、当活动状态从草稿状态变更为启动状态
     *          2、当活动状态从启动状态变更为停止状态
     *          3、当活动状态从启动状态变更为过期状态
     *          4、当启动状态的活动奖品从上架变更为下架
     *          5、当启动状态的活动奖品从上架变更为过期
     *          6、活动状态从停止状态变更为启动状态
     */
    public void refreshCampShopCache(String campId);

    /**
     * 从缓存中获取店铺模型
     * 
     * @param shopId
     * @return
     */
    public PxShopModel queryShopModelFromCache(String shopId);
}
