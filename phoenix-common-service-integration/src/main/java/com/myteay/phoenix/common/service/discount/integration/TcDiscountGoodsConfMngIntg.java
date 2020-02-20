/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2015-2020 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.discount.integration;

import java.util.List;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.tc.discount.core.model.TcDiscountGoodsConfigModel;

/**
 * 商品折扣管理客户端集成
 * 
 * @author min.weixm
 * @version $Id: TcDiscountGoodsConfMngIntg.java, v 0.1 Feb 20, 2020 2:20:50 PM min.weixm Exp $
 */
public interface TcDiscountGoodsConfMngIntg {

    /**
     * 查询店铺下所的折扣商品配置信息
     * 
     * @param shopId
     * @return
     */
    public MtOperateResult<List<TcDiscountGoodsConfigModel>> queryDiscountGoodsConfAll(String shopId);

    /**
     * 保存商品折扣配置信息
     * 
     * @param tcDiscountGoodsConfigModel
     * @return
     */
    public MtOperateResult<String> saveDiscountGoodsConfig(TcDiscountGoodsConfigModel tcDiscountGoodsConfigModel);

    /**
     * 修改商品折扣配置信息
     * 
     * @param tcDiscountGoodsConfigModel
     * @return
     */
    public MtOperateResult<TcDiscountGoodsConfigModel> modifyDiscountGoodsConfById(TcDiscountGoodsConfigModel tcDiscountGoodsConfigModel);

    /**
     * 删除商品折扣配置信息
     * 
     * @param tcDiscountGoodsConfigModel
     */
    public MtOperateResult<String> removeDiscountGoodsConfById(TcDiscountGoodsConfigModel tcDiscountGoodsConfigModel);

}
