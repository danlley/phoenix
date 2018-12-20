/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.component.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.camp.CampBaseModel;
import com.myteay.phoenix.core.model.camp.CampModel;
import com.myteay.phoenix.core.model.camp.CampPrizeModel;
import com.myteay.phoenix.core.model.camp.repository.CampShopBaseRepository;
import com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRepository;
import com.myteay.phoenix.core.service.camp.component.CampShopCacheComponnet;

/**
 * 到店消费营销活动基础数据缓存
 * 
 * @author danlley
 * @version $Id: CampShopCacheComponnetImpl.java, v 0.1 Dec 20, 2018 9:51:18 PM danlley Exp $
 */
public class CampShopCacheComponnetImpl implements CampShopCacheComponnet, InitializingBean {

    /** 日志 */
    public static final Logger                 logger           = Logger.getLogger(CampShopCacheComponnetImpl.class);

    /** 活动缓存 */
    public static final Map<String, CampModel> CAMP_MODEL_CACHE = Collections.synchronizedMap(new HashMap<>());

    /** 针对单个店铺店内消费到场营销活动操作仓储 */
    private CampShopBaseRepository             campShopBaseRepository;

    /** 店内营销活动奖池仓储 */
    private CampShopPrizeRepository            campShopPrizeRepository;

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化活动缓存
        initCache();
    }

    /** 
     * @see com.myteay.phoenix.core.service.camp.component.CampShopCacheComponnet#refreshCampShopCache(java.lang.String)
     */
    @Override
    public void refreshCampShopCache(String campId) {
        if (logger.isInfoEnabled()) {
            logger.info("活动发生变更，准备刷新缓存 campId=" + campId);
        }
        initCache();
    }

    /**
     * 初始化活动缓存
     */
    private void initCache() {
        Map<String, CampModel> campModelMap = getCampModelMap();
        if (CollectionUtils.isEmpty(campModelMap)) {
            return;
        }
        CAMP_MODEL_CACHE.putAll(campModelMap);
    }

    /**
     * 构建待初始化的营销活动缓存列表
     * 
     * @return
     */
    private Map<String, CampModel> getCampModelMap() {
        List<CampBaseModel> campBaseModels = getOnlineCampBaseModels();
        if (CollectionUtils.isEmpty(campBaseModels)) {
            logger.warn("当前没有需要加载到缓存中的营销活动");
            return null;
        }

        List<CampPrizeModel> campPrizeModels = null;
        CampModel campModel = null;
        Map<String, CampModel> campModelMap = new HashMap<>();
        for (CampBaseModel campBaseModel : campBaseModels) {
            if (campBaseModel == null) {
                continue;
            }

            campPrizeModels = getOnlineCampPrizeByCampId(campBaseModel.getCampId());
            campModel = new CampModel();
            campModel.setCampId(campBaseModel.getCampId());
            campModel.setCampBaseModel(campBaseModel);
            campModel.setCampPrizeModelList(campPrizeModels);
            campModelMap.put(campBaseModel.getCampId(), campModel);
        }

        return campModelMap;
    }

    /**
     * 通过活动ID，查询当前活动中所有已经上架的奖品列表
     * 
     * @param campId
     * @return
     */
    private List<CampPrizeModel> getOnlineCampPrizeByCampId(String campId) {
        List<CampPrizeModel> campPrizeModels = null;
        try {
            campPrizeModels = campShopPrizeRepository.findShopOnlinePrizeByCampId(campId);
        } catch (PxManageException e) {
            logger.warn("查询在线营销活动所有已经上架的奖品出错 campId= " + campId, e);
        } catch (Throwable e) {
            logger.warn("查询在线营销活动所有已经上架的奖品发生未知异常 campId=" + campId, e);
        }
        return campPrizeModels;
    }

    /**
     * 查询所有已经启动的营销活动
     * 
     * @return
     */
    private List<CampBaseModel> getOnlineCampBaseModels() {
        List<CampBaseModel> campBaseModels = null;
        try {
            campBaseModels = campShopBaseRepository.findCampBaseOnlineAll();
        } catch (PxManageException e) {
            logger.warn("查询所有营销活动出错 " + e.getMessage(), e);
        } catch (Throwable e) {
            logger.warn("查询所有营销活动发生未知异常 " + e.getMessage(), e);
        }

        return campBaseModels;
    }

    /**
     * Setter method for property <tt>campShopBaseRepository</tt>.
     * 
     * @param campShopBaseRepository value to be assigned to property campShopBaseRepository
     */
    public void setCampShopBaseRepository(CampShopBaseRepository campShopBaseRepository) {
        this.campShopBaseRepository = campShopBaseRepository;
    }

    /**
     * Setter method for property <tt>campShopPrizeRepository</tt>.
     * 
     * @param campShopPrizeRepository value to be assigned to property campShopPrizeRepository
     */
    public void setCampShopPrizeRepository(CampShopPrizeRepository campShopPrizeRepository) {
        this.campShopPrizeRepository = campShopPrizeRepository;
    }

}
