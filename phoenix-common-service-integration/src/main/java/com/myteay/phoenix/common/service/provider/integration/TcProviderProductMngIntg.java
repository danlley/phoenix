/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.provider.integration;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.myteay.phoenix.biz.service.impl.MtServiceResult;
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
    public MtServiceResult<TcProviderProductModel> manageProviderProductInfo(@RequestBody TcProviderProductModel tcProviderProductModel);

    /**
     * 查询店铺下所有的原材料信息
     * 
     * @param shopId
     * @return
     */
    public MtServiceResult<List<TcProviderProductModel>> queryProviderProductByShopId(@PathVariable String shopId);

    /**
     * 原材料图片管理
     * 
     * @param tcProviderProductImagesModel
     * @return
     */
    public MtServiceResult<TcProviderProductImagesModel> manageProductImagesInfo(TcProviderProductImagesModel tcProviderProductImagesModel);

    /**
     * 原材料询价管理
     * 
     * @param tcProviderProductPriceModel
     * @return
     */
    public MtServiceResult<TcProviderProductPriceModel> manageProviderProductPriceInfo(TcProviderProductPriceModel tcProviderProductPriceModel);

    /**
     * 原材料操作手册管理
     * 
     * @param tcProviderProductOpManualModel
     * @return
     */
    public MtServiceResult<TcProviderProductOpManualModel> manageProviderProductOpManualInfo(TcProviderProductOpManualModel tcProviderProductOpManualModel);

    /**
     * 原材料营养配比管理
     * 
     * @param tcProviderProductNutritionalModel
     * @return
     */
    public MtServiceResult<TcProviderProductNutritionalModel> manageProviderProductNutritionalInfo(TcProviderProductNutritionalModel tcProviderProductNutritionalModel);

    /**
     * 查询店铺下所有的原材料信息
     * 
     * @param shopId
     * @return
     */
    public MtServiceResult<List<TcProviderProductPriceModel>> queryProductAllPriceByProductId(@PathVariable String productId);

    /**
     * 查询原材料对应的所有营养配比
     * 
     * @param productId
     * @return
     */
    public MtServiceResult<List<TcProviderProductNutritionalModel>> queryProductAllNutritionalByProductId(@PathVariable String productId);

    /**
     * 查询原材料对应的所有帮助手册
     * 
     * @param productId
     * @return
     */
    public MtServiceResult<List<TcProviderProductOpManualModel>> queryProductAllOpManualByProductId(@PathVariable String productId);

    /**
     * 查询原材料图片
     * 
     * @param productId
     * @return
     */
    public MtServiceResult<List<TcProviderProductImagesModel>> queryProductAllImagesByProductId(@PathVariable String productId);
}