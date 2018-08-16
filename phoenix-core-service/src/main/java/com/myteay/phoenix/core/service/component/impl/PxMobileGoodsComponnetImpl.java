/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.component.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.mobile.PxMobileGoodsModel;
import com.myteay.phoenix.core.service.component.PxMobileGoodsCacheComponent;
import com.myteay.phoenix.core.service.component.PxMobileGoodsComponnet;
import com.myteay.phoenix.core.service.strategy.PxGoodsStrategy;

/**
 * 手机端商品信息管理组件
 * 
 * @author min.weixm
 * @version $Id: PxMobileGoodsComponnetImpl.java, v 0.1 Aug 17, 2018 1:49:17 AM min.weixm Exp $
 */
public class PxMobileGoodsComponnetImpl implements PxMobileGoodsComponnet {

    /** 日志 */
    public static final Logger          logger = Logger.getLogger(PxMobileGoodsComponnetImpl.class);

    /** 手机端商品缓存管理组件 */
    private PxMobileGoodsCacheComponent pxMobileGoodsCacheComponent;

    /** 手机端商品策略 */
    private PxGoodsStrategy             pxGoodsStrategy;

    /** 
     * @see com.myteay.phoenix.core.service.component.PxMobileGoodsComponnet#queryNextGoodsList(java.util.List)
     */
    @Override
    public MtOperateResult<List<PxMobileGoodsModel>> queryNextGoodsList(List<String> excludeGoodsIds) {
        return new MtOperateResult<>(pxGoodsStrategy.queryGoodsListFromCache(excludeGoodsIds));
    }

    /** 
     * @see com.myteay.phoenix.core.service.component.PxMobileGoodsComponnet#queryGoodsDetail(java.lang.String)
     */
    @Override
    public MtOperateResult<PxMobileGoodsModel> queryGoodsDetail(String goodsId) {
        return new MtOperateResult<PxMobileGoodsModel>(pxMobileGoodsCacheComponent.queryMobileSingleGoodsByGoodsId(goodsId));
    }

    /**
     * Setter method for property <tt>pxMobileGoodsCacheComponent</tt>.
     * 
     * @param pxMobileGoodsCacheComponent value to be assigned to property pxMobileGoodsCacheComponent
     */
    public void setPxMobileGoodsCacheComponent(PxMobileGoodsCacheComponent pxMobileGoodsCacheComponent) {
        this.pxMobileGoodsCacheComponent = pxMobileGoodsCacheComponent;
    }

    /**
     * Setter method for property <tt>pxGoodsStrategy</tt>.
     * 
     * @param pxGoodsStrategy value to be assigned to property pxGoodsStrategy
     */
    public void setPxGoodsStrategy(PxGoodsStrategy pxGoodsStrategy) {
        this.pxGoodsStrategy = pxGoodsStrategy;
    }

}
