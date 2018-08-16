/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.strategy;

import java.util.List;

import com.myteay.phoenix.core.model.mobile.PxMobileGoodsModel;

/**
 * 手机端商品策略
 * 
 * @author min.weixm
 * @version $Id: PxGoodsStrategy.java, v 0.1 Aug 17, 2018 1:58:23 AM min.weixm Exp $
 */
public interface PxGoodsStrategy {

    /**
     * 从缓存中获取目标页商品列表（排除手机端已展示的商品）
     * 
     * @param excludeGoodsIds
     * @return
     */
    public List<PxMobileGoodsModel> queryGoodsListFromCache(List<String> excludeGoodsIds);
}
