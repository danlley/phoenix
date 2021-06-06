/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.provider.integration;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.myteay.phoenix.common.util.MtOperateResult;
import com.tc.provider.orm.model.TcProviderProductImagesModel;
import com.tc.provider.orm.model.TcProviderProductModel;
import com.tc.provider.orm.model.TcProviderProductNutritionalModel;
import com.tc.provider.orm.model.TcProviderProductOpManualModel;
import com.tc.provider.orm.model.TcProviderProductPriceModel;

/**
 * 原材料管理
 * 
 * @author min.weixm
 * @version $Id: TcProviderProductMngIntg.java, v 0.1 Aug 28, 2019 10:58:23 AM min.weixm Exp $
 */
public interface TcProviderProductMngIntg {

    /**
     * 原材料配置信息管理
     * 
     * @param tcProviderProductModel
     * @return
     */
    public MtOperateResult<TcProviderProductModel> manageProviderProductInfo(@RequestBody TcProviderProductModel tcProviderProductModel);

    /**
     * 查询店铺下所有的原材料信息
     * 
     * @param shopId
     * @return
     */
    public MtOperateResult<List<TcProviderProductModel>> queryProviderProductByShopId(String shopId);

    /**
     * 原材料图片管理
     * 
     * @param tcProviderProductImagesModel
     * @return
     */
    public MtOperateResult<TcProviderProductImagesModel> manageProductImagesInfo(TcProviderProductImagesModel tcProviderProductImagesModel);

    /**
     * 原材料询价管理
     * 
     * @param tcProviderProductPriceModel
     * @return
     */
    public MtOperateResult<TcProviderProductPriceModel> manageProviderProductPriceInfo(TcProviderProductPriceModel tcProviderProductPriceModel);

    /**
     * 原材料操作手册管理
     * 
     * @param tcProviderProductOpManualModel
     * @return
     */
    public MtOperateResult<TcProviderProductOpManualModel> manageProviderProductOpManualInfo(TcProviderProductOpManualModel tcProviderProductOpManualModel);

    /**
     * 原材料营养配比管理
     * 
     * @param tcProviderProductNutritionalModel
     * @return
     */
    public MtOperateResult<TcProviderProductNutritionalModel> manageProviderProductNutritionalInfo(TcProviderProductNutritionalModel tcProviderProductNutritionalModel);

    /**
     * 查询店铺下所有的原材料信息
     * 
     * @param shopId
     * @return
     */
    public MtOperateResult<List<TcProviderProductPriceModel>> queryProductAllPriceByProductId(String productId);

    /**
     * 查询原材料对应的所有营养配比
     * 
     * @param productId
     * @return
     */
    public MtOperateResult<List<TcProviderProductNutritionalModel>> queryProductAllNutritionalByProductId(String productId);

    /**
     * 查询原材料对应的所有帮助手册
     * 
     * @param productId
     * @return
     */
    public MtOperateResult<List<TcProviderProductOpManualModel>> queryProductAllOpManualByProductId(String productId);

    /**
     * 查询原材料图片
     * 
     * @param productId
     * @return
     */
    public MtOperateResult<List<TcProviderProductImagesModel>> queryProductAllImagesByProductId(String productId);

    /**
     * 
     * @param shopId
     * @param productName
     * @return
     */
    public MtOperateResult<List<TcProviderProductModel>> findTcProviderProductByCondition(String shopId, String productName);
}
