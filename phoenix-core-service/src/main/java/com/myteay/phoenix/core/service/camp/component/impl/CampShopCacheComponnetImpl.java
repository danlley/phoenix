/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.component.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;

import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.common.util.manage.enums.PxShopStatusEnum;
import com.myteay.phoenix.core.model.camp.CampBaseModel;
import com.myteay.phoenix.core.model.camp.CampModel;
import com.myteay.phoenix.core.model.camp.CampPrizeModel;
import com.myteay.phoenix.core.model.camp.CampPrizeRefGoodsModel;
import com.myteay.phoenix.core.model.camp.repository.CampShopBaseRepository;
import com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRefGoodsRepository;
import com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRepository;
import com.myteay.phoenix.core.model.manage.PxShopModel;
import com.myteay.phoenix.core.model.manage.repository.PxShopRepository;
import com.myteay.phoenix.core.service.camp.component.CampShopCacheComponnet;

/**
 * 到店消费营销活动基础数据缓存
 * 
 * @author danlley
 * @version $Id: CampShopCacheComponnetImpl.java, v 0.1 Dec 20, 2018 9:51:18 PM danlley Exp $
 */
public class CampShopCacheComponnetImpl implements CampShopCacheComponnet, InitializingBean {

    /** 日志 */
    private static final Logger                     logger                 = LoggerFactory.getLogger(LoggerNames.PX_CACHE_DEFAULT);

    /** 活动缓存 */
    public static final Map<String, CampModel>      CAMP_MODEL_CACHE       = Collections.synchronizedMap(new HashMap<>());

    /** 活动奖品缓存 */
    public static final Map<String, CampPrizeModel> CAMP_PRIZE_MODEL_CACHE = Collections.synchronizedMap(new HashMap<>());

    /** 活动缓存 */
    public static final Map<String, PxShopModel>    SHOP_MODEL_CACHE       = Collections.synchronizedMap(new HashMap<>());

    /** 针对单个店铺店内消费到场营销活动操作仓储 */
    private CampShopBaseRepository                  campShopBaseRepository;

    /** 店内营销活动奖池仓储 */
    private CampShopPrizeRepository                 campShopPrizeRepository;

    /** 店铺管理仓储 */
    private PxShopRepository                        pxShopRepository;

    /** 店内消费营销活动奖品关联商品仓储 */
    private CampShopPrizeRefGoodsRepository         campShopPrizeRefGoodsRepository;

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化活动缓存
        initCache();
        initShopCache();
    }

    /** 
     * @see com.myteay.phoenix.core.service.camp.component.CampShopCacheComponnet#queryCampBaseModelFromCache(java.lang.String)
     */
    @Override
    public CampBaseModel queryCampBaseModelFromCache(String campId) {
        if (CollectionUtils.isEmpty(CAMP_MODEL_CACHE) || StringUtils.isBlank(campId)) {
            logger.warn("未找到对应的活动基本信息模型 campId=" + campId);
            return null;
        }

        return (CAMP_MODEL_CACHE.get(campId) == null ? null : CAMP_MODEL_CACHE.get(campId).getCampBaseModel());
    }

    /** 
     * @see com.myteay.phoenix.core.service.camp.component.CampShopCacheComponnet#queryCampPrizeModelFromCache(java.lang.String, java.lang.String)
     */
    @Override
    public CampPrizeModel queryCampPrizeModelFromCache(String campId, String prizeId) {

        if (CollectionUtils.isEmpty(CAMP_PRIZE_MODEL_CACHE) || StringUtils.isBlank(campId) || StringUtils.isBlank(prizeId)) {
            logger.warn("通过活动Id和奖品ID从缓存中查询奖品模型失败 campId=" + campId + " prizeId=" + prizeId);
            return null;
        }

        return CAMP_PRIZE_MODEL_CACHE.get(campId + "_" + prizeId);
    }

    /** 
     * @see com.myteay.phoenix.core.service.camp.component.CampShopCacheComponnet#queryShopModelFromCache(java.lang.String)
     */
    @Override
    public PxShopModel queryShopModelFromCache(String shopId) {
        return SHOP_MODEL_CACHE.get(shopId);
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
     * 初始化店铺缓存
     */
    private void initShopCache() {
        List<PxShopModel> shopModels = null;
        try {
            shopModels = pxShopRepository.findAll();
        } catch (PxManageException e) {
            logger.warn("查询所有店铺出错" + e.getMessage(), e);
        }

        if (CollectionUtils.isEmpty(shopModels)) {
            return;
        }

        Map<String, PxShopModel> map = new HashMap<>();
        for (PxShopModel pxShopModel : shopModels) {
            if (pxShopModel.getShopStatus() != PxShopStatusEnum.PX_SHOP_ONLINE) {
                continue;
            }
            map.put(pxShopModel.getShopId(), pxShopModel);
        }

        SHOP_MODEL_CACHE.putAll(map);
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

        initCampPrizeCache();
    }

    /**
     * 初始化奖品缓存
     */
    private void initCampPrizeCache() {
        List<CampBaseModel> campBaseModels = getOnlineCampBaseModels();
        if (CollectionUtils.isEmpty(campBaseModels)) {
            logger.warn("当前没有需要加载到缓存中的营销活动");
            return;
        }

        List<CampPrizeModel> campPrizeModels = null;
        Map<String, CampPrizeModel> campPrizeModelMap = new HashMap<>();
        String campId = null;
        String prizeId = null;
        String key = null;
        for (CampBaseModel campBaseModel : campBaseModels) {
            if (campBaseModel == null) {
                continue;
            }

            campId = campBaseModel.getCampId();
            campPrizeModels = getOnlineCampPrizeByCampId(campId);
            if (CollectionUtils.isEmpty(campPrizeModels)) {
                continue;
            }

            for (CampPrizeModel campPrizeModel : campPrizeModels) {
                List<CampPrizeRefGoodsModel> prizeRefGoodsModels = null;
                try {
                    prizeRefGoodsModels = campShopPrizeRefGoodsRepository.findPrizeRefGoodsByPrizeId(campPrizeModel.getPrizeId());
                } catch (PxManageException e) {
                    logger.warn("[构建奖品缓存]查询奖品关联商品列表出错 " + e.getMessage(), e);
                }

                campPrizeModel.setCampPrizeRefGoodsModels(prizeRefGoodsModels);

                prizeId = campPrizeModel.getPrizeId();
                key = campId + "_" + prizeId;
                campPrizeModelMap.put(key, campPrizeModel);
            }
        }
        CAMP_PRIZE_MODEL_CACHE.putAll(campPrizeModelMap);
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

    /**
     * Setter method for property <tt>pxShopRepository</tt>.
     * 
     * @param pxShopRepository value to be assigned to property pxShopRepository
     */
    public void setPxShopRepository(PxShopRepository pxShopRepository) {
        this.pxShopRepository = pxShopRepository;
    }

    /**
     * Setter method for property <tt>campShopPrizeRefGoodsRepository</tt>.
     * 
     * @param campShopPrizeRefGoodsRepository value to be assigned to property campShopPrizeRefGoodsRepository
     */
    public void setCampShopPrizeRefGoodsRepository(CampShopPrizeRefGoodsRepository campShopPrizeRefGoodsRepository) {
        this.campShopPrizeRefGoodsRepository = campShopPrizeRefGoodsRepository;
    }

}
