/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.strategy.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.myteay.phoenix.common.util.PxConstants;
import com.myteay.phoenix.core.model.mobile.PxMobileGoodsModel;
import com.myteay.phoenix.core.service.component.PxMobileGoodsCacheComponent;
import com.myteay.phoenix.core.service.component.impl.PxMobileGoodsComponnetImpl;
import com.myteay.phoenix.core.service.strategy.PxGoodsStrategy;

/**
 * 手机端商品策略
 * 
 * @author min.weixm
 * @version $Id: PxGoodsStrategyImpl.java, v 0.1 Aug 17, 2018 1:59:09 AM min.weixm Exp $
 */
public class PxGoodsStrategyImpl implements PxGoodsStrategy {

    /** 日志 */
    public static final Logger          logger = Logger.getLogger(PxMobileGoodsComponnetImpl.class);

    /** 手机端商品缓存管理组件 */
    private PxMobileGoodsCacheComponent pxMobileGoodsCacheComponent;

    /** 
     * @see com.myteay.phoenix.core.service.strategy.PxGoodsStrategy#queryGoodsListFromCache(java.util.List)
     */
    @Override
    public List<PxMobileGoodsModel> queryGoodsListFromCache(List<String> excludeGoodsIds) {
        Map<String, PxMobileGoodsModel> map = pxMobileGoodsCacheComponent.queryCacheData();
        if (CollectionUtils.isEmpty(map)) {
            logger.warn("未能从缓存中查询到商品信息  map is null! ");
            return null;
        }

        return generateGoodsList(sortGoodsList(map, excludeGoodsIds));
    }

    /**
     * 获取目标请求数量的商品列表（已去重）
     * 
     * @param goodsList
     * @return
     */
    private List<PxMobileGoodsModel> generateGoodsList(List<PxMobileGoodsModel> goodsList) {
        if (CollectionUtils.isEmpty(goodsList)) {
            return null;
        }

        List<PxMobileGoodsModel> list = new ArrayList<>();
        int size = PxConstants.MAX_MOBILE_QUERY_PAGE_SIZE;
        for (PxMobileGoodsModel pxMobileGoodsModel : goodsList) {
            if (pxMobileGoodsModel == null) {
                continue;
            }

            size--;
            if (size <= 0) {
                return list;
            }

            list.add(pxMobileGoodsModel);
        }

        return list;
    }

    /**
     * 获取过滤后的商品列表
     * 
     * @param goodsMap
     * @param excludeGoodsIds
     * @return
     */
    private List<PxMobileGoodsModel> sortGoodsList(Map<String, PxMobileGoodsModel> goodsMap, List<String> excludeGoodsIds) {

        if (CollectionUtils.isEmpty(goodsMap)) {
            return null;
        }

        if (CollectionUtils.isEmpty(excludeGoodsIds)) {
            return goodsMap.values().stream().collect(Collectors.toList());
        }

        for (String goodsId : excludeGoodsIds) {
            goodsMap.remove(goodsId);
        }

        return goodsMap.values().stream().collect(Collectors.toList());
    }

    /**
     * Setter method for property <tt>pxMobileGoodsCacheComponent</tt>.
     * 
     * @param pxMobileGoodsCacheComponent value to be assigned to property pxMobileGoodsCacheComponent
     */
    public void setPxMobileGoodsCacheComponent(PxMobileGoodsCacheComponent pxMobileGoodsCacheComponent) {
        this.pxMobileGoodsCacheComponent = pxMobileGoodsCacheComponent;
    }

}
