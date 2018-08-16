/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.component;

import java.util.Map;

import com.myteay.phoenix.core.model.mobile.PxMobileGoodsModel;

/**
 * 手机端商品缓存管理组件
 * 
 * @author min.weixm
 * @version $Id: PxMobileGoodsCacheComponent.java, v 0.1 Aug 17, 2018 1:01:26 AM min.weixm Exp $
 */
public interface PxMobileGoodsCacheComponent {

    /**
     * 查询当前缓存中的所有数据
     * 
     * @return
     */
    public Map<String, PxMobileGoodsModel> queryCacheData();

    /**
     * 通过商品ID查询指定商品模型
     * 
     * @param goodsId
     * @return
     */
    public PxMobileGoodsModel queryMobileSingleGoodsByGoodsId(String goodsId);

    /**
     * 初始化缓存信息
     */
    public void initCache();
}
