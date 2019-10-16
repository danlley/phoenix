/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.provider.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.phoenix.biz.service.impl.MtServiceResult;
import com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg;
import com.tc.provider.orm.model.TcProviderProductImagesModel;
import com.tc.provider.orm.model.TcProviderProductModel;
import com.tc.provider.orm.model.TcProviderProductNutritionalModel;
import com.tc.provider.orm.model.TcProviderProductOpManualModel;
import com.tc.provider.orm.model.TcProviderProductPriceModel;

/**
 * 原材料管理
 * 
 * @author min.weixm
 * @version $Id: TcProviderProductMngController.java, v 0.1 Aug 29, 2019 9:35:55 PM min.weixm Exp $
 */
@RestController
@RequestMapping(value = "tiancan/api/provider/manage")
public class TcProviderProductMngController {

    /** 日志 */
    public static final Logger       logger = Logger.getLogger(TcProviderProductMngController.class);

    /** 原材料管理 */
    @Autowired
    private TcProviderProductMngIntg tcProviderProductMngIntg;

    /**
     * 原材料配置信息管理
     * 
     * @param tcProviderProductModel
     * @return
     */
    @RequestMapping(value = "/do/mng", method = { RequestMethod.POST })
    public MtServiceResult<TcProviderProductModel> manageProviderProductInfo(@RequestBody TcProviderProductModel tcProviderProductModel) {
        return tcProviderProductMngIntg.manageProviderProductInfo(tcProviderProductModel);
    }

    /**
     * 查询店铺下所有的原材料信息
     * 
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/list/shop/{shopId}", method = { RequestMethod.GET })
    public MtServiceResult<List<TcProviderProductModel>> queryGoodsByShopId(@PathVariable String shopId) {
        return tcProviderProductMngIntg.queryProviderProductByShopId(shopId);
    }

    /**
     * 操作图片管理
     * 
     * @param tcProviderProductModel
     * @return
     */
    @RequestMapping(value = "/do/images/mng", method = { RequestMethod.POST })
    public MtServiceResult<TcProviderProductImagesModel> manageProviderProductImagesInfo(@RequestBody TcProviderProductImagesModel tcProviderProductImagesModel) {
        return tcProviderProductMngIntg.manageProductImagesInfo(tcProviderProductImagesModel);
    }

    /**
     * 产品询价管理
     * 
     * @param tcProviderProductModel
     * @return
     */
    @RequestMapping(value = "/do/price/mng", method = { RequestMethod.POST })
    public MtServiceResult<TcProviderProductPriceModel> manageProviderProductPriceInfo(@RequestBody TcProviderProductPriceModel tcProviderProductPriceModel) {
        return tcProviderProductMngIntg.manageProviderProductPriceInfo(tcProviderProductPriceModel);
    }

    /**
     * 营养配比管理
     * 
     * @param tcProviderProductModel
     * @return
     */
    @RequestMapping(value = "/do/nutritional/mng", method = { RequestMethod.POST })
    public MtServiceResult<TcProviderProductNutritionalModel> manageProviderProductNutritionalInfo(@RequestBody TcProviderProductNutritionalModel tcProviderProductNutritionalModel) {
        return tcProviderProductMngIntg.manageProviderProductNutritionalInfo(tcProviderProductNutritionalModel);
    }

    /**
     * 操作手册管理
     * 
     * @param tcProviderProductModel
     * @return
     */
    @RequestMapping(value = "/do/op/manual/mng", method = { RequestMethod.POST })
    public MtServiceResult<TcProviderProductOpManualModel> manageProviderProductOpManualInfo(@RequestBody TcProviderProductOpManualModel tcProviderProductOpManualModel) {
        return tcProviderProductMngIntg.manageProviderProductOpManualInfo(tcProviderProductOpManualModel);
    }

    /**
     * 查询店铺下所有的原材料信息
     * 
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/list/price/{productId}", method = { RequestMethod.GET })
    public MtServiceResult<List<TcProviderProductPriceModel>> queryProductAllPriceByProductId(@PathVariable String productId) {
        return tcProviderProductMngIntg.queryProductAllPriceByProductId(productId);
    }

    /**
     * 查询原材料对应的所有营养配比
     * 
     * @param productId
     * @return
     */
    @RequestMapping(value = "/list/nutritional/{productId}", method = { RequestMethod.GET })
    public MtServiceResult<List<TcProviderProductNutritionalModel>> queryProductAllNutritionalByProductId(@PathVariable String productId) {
        return tcProviderProductMngIntg.queryProductAllNutritionalByProductId(productId);
    }

    /**
     * 查询原材料对应的所有帮助手册
     * 
     * @param productId
     * @return
     */
    @RequestMapping(value = "/list/op/manual/{productId}", method = { RequestMethod.GET })
    public MtServiceResult<List<TcProviderProductOpManualModel>> queryProductAllOpManualByProductId(@PathVariable String productId) {
        return tcProviderProductMngIntg.queryProductAllOpManualByProductId(productId);
    }

    /**
     * 查询原材料图片
     * 
     * @param productId
     * @return
     */
    @RequestMapping(value = "/list/images/{productId}", method = { RequestMethod.GET })
    public MtServiceResult<List<TcProviderProductImagesModel>> queryProductAllImagesByProductId(@PathVariable String productId) {
        return tcProviderProductMngIntg.queryProductAllImagesByProductId(productId);
    }
}
