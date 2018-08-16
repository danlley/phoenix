/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.component.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.mobile.PxMobileGoodsModel;
import com.myteay.phoenix.core.model.mobile.repository.PxMobileGoodsRepository;
import com.myteay.phoenix.core.service.component.PxMobileGoodsCacheComponent;

/**
 * 手机端商品缓存管理组件
 * 
 * @author min.weixm
 * @version $Id: PxMobileGoodsCacheComponentImpl.java, v 0.1 Aug 17, 2018 1:02:02 AM min.weixm Exp $
 */
public class PxMobileGoodsCacheComponentImpl implements PxMobileGoodsCacheComponent, InitializingBean {

    /** 日志 */
    public static final Logger                     logger         = Logger.getLogger(PxMobileGoodsCacheComponentImpl.class);

    /** 商品列表缓存 */
    private static Map<String, PxMobileGoodsModel> PX_GOODS_CACHE = Collections.synchronizedMap(new HashMap<>());

    /** 手机端商品管理仓储 */
    private PxMobileGoodsRepository                pxMobileGoodsRepository;

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        initCache();
    }

    /** 
     * @see com.myteay.phoenix.core.service.component.PxMobileGoodsCacheComponent#queryCacheData()
     */
    @Override
    public Map<String, PxMobileGoodsModel> queryCacheData() {
        if (CollectionUtils.isEmpty(PX_GOODS_CACHE)) {
            return null;
        }

        Map<String, PxMobileGoodsModel> map = new HashMap<>();
        map.putAll(PX_GOODS_CACHE);

        return map;
    }

    /** 
     * @see com.myteay.phoenix.core.service.component.PxMobileGoodsCacheComponent#queryMobileSingleGoodsByGoodsId(java.lang.String)
     */
    @Override
    public PxMobileGoodsModel queryMobileSingleGoodsByGoodsId(String goodsId) {

        if (CollectionUtils.isEmpty(PX_GOODS_CACHE) || StringUtils.isBlank(goodsId)) {
            logger.warn("商品缓存列表不可用，或goodsId不合法 goodsId=" + goodsId);
            return null;
        }

        return PX_GOODS_CACHE.get(goodsId);
    }

    /** 
     * @see com.myteay.phoenix.core.service.component.PxMobileGoodsCacheComponent#initCache()
     */
    @Override
    public void initCache() {

        // step 1: 获取缓存数据
        List<PxMobileGoodsModel> goodsList = queryGoodsCacheList();
        fillCacheModel(goodsList);

        // step 2: 检查缓存数据合法性
        if (CollectionUtils.isEmpty(goodsList)) {
            logger.warn("未获取到合法的商品缓存列表，当前缓存中的商品列表将被清空");
            PX_GOODS_CACHE = Collections.synchronizedMap(new HashMap<>());
            return;
        }

        // step 3: 初始化缓存信息
        for (PxMobileGoodsModel pxMobileGoodsModel : goodsList) {
            if (pxMobileGoodsModel == null) {
                continue;
            }

            PX_GOODS_CACHE.put(pxMobileGoodsModel.getGoodsId(), pxMobileGoodsModel);
        }

        // step 4: 打印刷新结果
        if (logger.isInfoEnabled()) {
            logger.info("当前缓存中的商品信息 PX_GOODS_CACHE=" + PX_GOODS_CACHE);
        }
    }

    /**
     * 填充模型
     * 
     * @param goodsList
     */
    private void fillCacheModel(List<PxMobileGoodsModel> goodsList) {
        if (CollectionUtils.isEmpty(goodsList)) {
            return;
        }
    }

    /**
     * 获取商品缓存列表
     * 
     * @return
     */
    private List<PxMobileGoodsModel> queryGoodsCacheList() {
        List<PxMobileGoodsModel> goodsList = null;
        try {
            goodsList = pxMobileGoodsRepository.findAll();
        } catch (PxManageException e) {
            logger.warn("获取商品缓存列表出错", e);
            return null;
        }

        return goodsList;
    }

    /**
     * Setter method for property <tt>pxMobileGoodsRepository</tt>.
     * 
     * @param pxMobileGoodsRepository value to be assigned to property pxMobileGoodsRepository
     */
    public void setPxMobileGoodsRepository(PxMobileGoodsRepository pxMobileGoodsRepository) {
        this.pxMobileGoodsRepository = pxMobileGoodsRepository;
    }

}
