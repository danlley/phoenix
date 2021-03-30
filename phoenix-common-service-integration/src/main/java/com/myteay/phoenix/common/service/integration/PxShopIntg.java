/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.integration;

import java.util.List;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.tc.shop.orm.model.PxShopModel;

/**
 * 
 * @author min.weixm
 * @version $Id: PxShopIntg.java, v 0.1 Jun 12, 2019 12:05:00 AM min.weixm Exp $
 */
public interface PxShopIntg {

    /**
     * 查询所有店铺信息
     * 
     * @return
     */
    MtOperateResult<List<PxShopModel>> queryShopAll();

    /**
     * 店铺管理服务（增、删、改、单条查询）
     * 
     * @param pxShopModel
     * @return
     */
    MtOperateResult<PxShopModel> manageShop(PxShopModel pxShopModel);

    /**
     * 查询店铺营销活动配置
     * 
     * @param shopId
     * @return
     */
    public String queryCampConfig(String shopId);
}
