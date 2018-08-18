/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.myteay.phoenix.common.util.exception.PxManageException;
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
    public MtOperateResult<List<PxMobileGoodsModel>> queryNextGoodsList(List<String> excludeGoodsIds) throws PxManageException {

        List<PxMobileGoodsModel> list = pxGoodsStrategy.queryGoodsListFromCache(excludeGoodsIds);
        if (CollectionUtils.isEmpty(list)) {
            return new MtOperateResult<>(list);
        }

        // 禁止商品列表查询携带过多商品信息消耗过多的带宽，提升接口的吞吐能力
        PxMobileGoodsModel tmPxMobileGoodsModel = null;
        List<PxMobileGoodsModel> resultList = new ArrayList<>();
        for (PxMobileGoodsModel model : list) {

            if (model == null) {
                continue;
            }

            tmPxMobileGoodsModel = new PxMobileGoodsModel();
            tmPxMobileGoodsModel.setGoodsId(model.getGoodsId());
            tmPxMobileGoodsModel.setPxMobileGoodsBaseModel(model.getPxMobileGoodsBaseModel());
            tmPxMobileGoodsModel.setPxMobileShopModel(model.getPxMobileShopModel());
            resultList.add(tmPxMobileGoodsModel);
        }

        return new MtOperateResult<>(resultList);
    }

    /** 
     * @see com.myteay.phoenix.core.service.component.PxMobileGoodsComponnet#queryGoodsDetail(java.lang.String)
     */
    @Override
    public MtOperateResult<PxMobileGoodsModel> queryGoodsDetail(String goodsId) throws PxManageException {
        return new MtOperateResult<>(pxMobileGoodsCacheComponent.queryMobileSingleGoodsByGoodsId(goodsId));
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