/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.provider.integration.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.myteay.phoenix.biz.service.impl.MtServiceResult;
import com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg;
import com.myteay.phoenix.common.util.HttpClientUtil;
import com.tc.provider.orm.model.TcProviderProductImagesModel;
import com.tc.provider.orm.model.TcProviderProductModel;
import com.tc.provider.orm.model.TcProviderProductNutritionalModel;
import com.tc.provider.orm.model.TcProviderProductOpManualModel;
import com.tc.provider.orm.model.TcProviderProductPriceModel;

/**
 * 原材料管理
 * 
 * @author min.weixm
 * @version $Id: TcProviderProductMngIntgImpl.java, v 0.1 Aug 28, 2019 10:59:01 AM min.weixm Exp $
 */
public class TcProviderProductMngIntgImpl implements TcProviderProductMngIntg {

    /** 环境变量 */
    @Autowired
    private Environment env;

    /** 
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg#manageProviderProductInfo(com.tc.provider.orm.model.TcProviderProductModel)
     */
    @Override
    public MtServiceResult<TcProviderProductModel> manageProviderProductInfo(TcProviderProductModel tcProviderProductModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/manage/do/mng";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(tcProviderProductModel));
        return (MtServiceResult<TcProviderProductModel>) JSON.parseObject(result, new TypeReference<MtServiceResult<TcProviderProductModel>>() {
        });
    }

    /** 
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg#queryProviderProductByShopId(java.lang.String)
     */
    @Override
    public MtServiceResult<List<TcProviderProductModel>> queryProviderProductByShopId(String shopId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/manage/list/shop/" + shopId;

        String result = HttpClientUtil.insureResponseGet(url);
        MtServiceResult<List<TcProviderProductModel>> obj = (MtServiceResult<List<TcProviderProductModel>>) JSON.parseObject(result,
            new TypeReference<MtServiceResult<List<TcProviderProductModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg#manageProductImagesInfo(com.tc.provider.orm.model.TcProviderProductImagesModel)
     */
    @Override
    public MtServiceResult<TcProviderProductImagesModel> manageProductImagesInfo(TcProviderProductImagesModel tcProviderProductImagesModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/manage/do/images/mng";

        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(tcProviderProductImagesModel));
        MtServiceResult<TcProviderProductImagesModel> obj = (MtServiceResult<TcProviderProductImagesModel>) JSON.parseObject(result,
            new TypeReference<MtServiceResult<TcProviderProductImagesModel>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg#manageProviderProductPriceInfo(com.tc.provider.orm.model.TcProviderProductPriceModel)
     */
    @Override
    public MtServiceResult<TcProviderProductPriceModel> manageProviderProductPriceInfo(TcProviderProductPriceModel tcProviderProductPriceModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/manage/do/price/mng";

        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(tcProviderProductPriceModel));
        MtServiceResult<TcProviderProductPriceModel> obj = (MtServiceResult<TcProviderProductPriceModel>) JSON.parseObject(result,
            new TypeReference<MtServiceResult<TcProviderProductPriceModel>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg#manageProviderProductNutritionalInfo(com.tc.provider.orm.model.TcProviderProductNutritionalModel)
     */
    @Override
    public MtServiceResult<TcProviderProductNutritionalModel> manageProviderProductNutritionalInfo(TcProviderProductNutritionalModel tcProviderProductNutritionalModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/manage/do/nutritional/mng";

        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(tcProviderProductNutritionalModel));
        MtServiceResult<TcProviderProductNutritionalModel> obj = (MtServiceResult<TcProviderProductNutritionalModel>) JSON.parseObject(result,
            new TypeReference<MtServiceResult<TcProviderProductNutritionalModel>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg#manageProviderProductOpManualInfo(com.tc.provider.orm.model.TcProviderProductOpManualModel)
     */
    @Override
    public MtServiceResult<TcProviderProductOpManualModel> manageProviderProductOpManualInfo(TcProviderProductOpManualModel tcProviderProductOpManualModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/manage/do/op/manual/mng" + tcProviderProductOpManualModel;

        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(tcProviderProductOpManualModel));
        MtServiceResult<TcProviderProductOpManualModel> obj = (MtServiceResult<TcProviderProductOpManualModel>) JSON.parseObject(result,
            new TypeReference<MtServiceResult<TcProviderProductOpManualModel>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg#queryProductAllPriceByProductId(java.lang.String)
     */
    @Override
    public MtServiceResult<List<TcProviderProductPriceModel>> queryProductAllPriceByProductId(String productId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/manage/list/price/" + productId;

        String result = HttpClientUtil.insureResponseGet(url);
        MtServiceResult<List<TcProviderProductPriceModel>> obj = (MtServiceResult<List<TcProviderProductPriceModel>>) JSON.parseObject(result,
            new TypeReference<MtServiceResult<List<TcProviderProductPriceModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg#queryProductAllNutritionalByProductId(java.lang.String)
     */
    @Override
    public MtServiceResult<List<TcProviderProductNutritionalModel>> queryProductAllNutritionalByProductId(String productId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/manage/list/nutritional/" + productId;

        String result = HttpClientUtil.insureResponseGet(url);
        MtServiceResult<List<TcProviderProductNutritionalModel>> obj = (MtServiceResult<List<TcProviderProductNutritionalModel>>) JSON.parseObject(result,
            new TypeReference<MtServiceResult<List<TcProviderProductNutritionalModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg#queryProductAllOpManualByProductId(java.lang.String)
     */
    @Override
    public MtServiceResult<List<TcProviderProductOpManualModel>> queryProductAllOpManualByProductId(String productId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/manage/list/op/manual/" + productId;

        String result = HttpClientUtil.insureResponseGet(url);
        MtServiceResult<List<TcProviderProductOpManualModel>> obj = (MtServiceResult<List<TcProviderProductOpManualModel>>) JSON.parseObject(result,
            new TypeReference<MtServiceResult<List<TcProviderProductOpManualModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg#queryProductAllImagesByProductId(java.lang.String)
     */
    @Override
    public MtServiceResult<List<TcProviderProductImagesModel>> queryProductAllImagesByProductId(String productId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/manage/list/images/" + productId;

        String result = HttpClientUtil.insureResponseGet(url);
        MtServiceResult<List<TcProviderProductImagesModel>> obj = (MtServiceResult<List<TcProviderProductImagesModel>>) JSON.parseObject(result,
            new TypeReference<MtServiceResult<List<TcProviderProductImagesModel>>>() {
            });
        return obj;
    }

}
