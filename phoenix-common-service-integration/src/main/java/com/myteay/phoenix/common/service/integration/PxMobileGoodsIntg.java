/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.integration;

import java.util.List;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.mobile.PxMobileGoodsModel;

/**
 * 
 * @author min.weixm
 * @version $Id: PxMobileGoodsIntg.java, v 0.1 Jun 12, 2019 12:29:36 AM min.weixm Exp $
 */
public interface PxMobileGoodsIntg {
    /**
     * 查询商品列表
     * 
     * @param excludeGoodsIds
     * @return
     */
    MtOperateResult<List<PxMobileGoodsModel>> queryNextGoodsList(List<String> excludeGoodsIds);

    /**
     * 查询单个商品详情
     * 
     * @param goodsId
     * @return
     */
    MtOperateResult<PxMobileGoodsModel> queryGoodsDetail(String goodsId);
}
