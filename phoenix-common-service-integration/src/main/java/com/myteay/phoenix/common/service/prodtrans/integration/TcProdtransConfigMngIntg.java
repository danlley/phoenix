/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2015-2020 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.prodtrans.integration;

import java.util.List;

import com.tc.phoenix.common.util.MtOperateResult;
import com.tc.prodtrans.core.model.TcPtsUserShopProdConfigModel;

/**
 * 产品账配置信息管理
 * 
 * @author min.weixm
 * @version $Id: TcProdtransConfigMngIntg.java, v 0.1 Mar 4, 2020 11:12:08 PM min.weixm Exp $
 */
public interface TcProdtransConfigMngIntg {
    /**
     * 保存会员店铺产品账配置信息
     * 
     * @param tcPtsUserShopProdConfigModel
     * @return
     */
    public MtOperateResult<String> savePtsUserShopProdConfig(TcPtsUserShopProdConfigModel tcPtsUserShopProdConfigModel);

    /**
     * 查询当前店铺的会员店铺产品账配置信息
     * 
     * @param shopId
     * @return
     */
    public MtOperateResult<List<TcPtsUserShopProdConfigModel>> queryPtsUserShopProdConfigByShop(String shopId);

    /**
     * 修改当前店铺的会员店铺产品账配置信息
     * 
     * @param tcPtsUserShopProdConfigModel
     */
    public MtOperateResult<String> modifyPtsUserShopProdConfig(TcPtsUserShopProdConfigModel tcPtsUserShopProdConfigModel);

    /**
     * 移除当前店铺的会员店铺产品账配置信息
     * 
     * @param prodtransId
     * @param shopId
     */
    public MtOperateResult<String> removePtsUserShopProdConfigById(String prodtransId, String shopId);
}
