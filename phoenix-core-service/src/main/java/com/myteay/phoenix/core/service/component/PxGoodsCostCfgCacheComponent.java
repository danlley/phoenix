/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.component;

import com.myteay.common.util.lang.Money;

/**
 * 商品成本缓存
 * 
 * @author danlley
 * @version $Id: PxGoodsCostCfgCacheComponent.java, v 0.1 May 7, 2019 8:49:20 PM danlley Exp $
 */
public interface PxGoodsCostCfgCacheComponent {

    /**
     * 通过商品ID查询指定商品的成本
     * 
     * @param goodsId
     * @return
     */
    public Money queryGoodsCostCfgByGoodsId(String goodsId);

    /**
     * 初始化缓存信息
     */
    public void refreshGoodsCostCfgCache();
}
