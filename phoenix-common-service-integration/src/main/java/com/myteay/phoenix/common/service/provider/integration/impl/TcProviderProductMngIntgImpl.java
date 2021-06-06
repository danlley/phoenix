/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.provider.integration.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg;
import com.myteay.phoenix.common.util.HttpClientUtil;
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
    public MtOperateResult<TcProviderProductModel> manageProviderProductInfo(TcProviderProductModel tcProviderProductModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/manage/do/mng";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(tcProviderProductModel));
        return (MtOperateResult<TcProviderProductModel>) JSON.parseObject(result, new TypeReference<MtOperateResult<TcProviderProductModel>>() {
        });
    }

    /** 
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg#queryProviderProductByShopId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<TcProviderProductModel>> queryProviderProductByShopId(String shopId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/manage/list/shop/" + shopId;

        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<TcProviderProductModel>> obj = (MtOperateResult<List<TcProviderProductModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<TcProviderProductModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg#manageProductImagesInfo(com.tc.provider.orm.model.TcProviderProductImagesModel)
     */
    @Override
    public MtOperateResult<TcProviderProductImagesModel> manageProductImagesInfo(TcProviderProductImagesModel tcProviderProductImagesModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/manage/do/images/mng";

        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(tcProviderProductImagesModel));
        MtOperateResult<TcProviderProductImagesModel> obj = (MtOperateResult<TcProviderProductImagesModel>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<TcProviderProductImagesModel>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg#manageProviderProductPriceInfo(com.tc.provider.orm.model.TcProviderProductPriceModel)
     */
    @Override
    public MtOperateResult<TcProviderProductPriceModel> manageProviderProductPriceInfo(TcProviderProductPriceModel tcProviderProductPriceModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/manage/do/price/mng";

        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(tcProviderProductPriceModel));
        MtOperateResult<TcProviderProductPriceModel> obj = (MtOperateResult<TcProviderProductPriceModel>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<TcProviderProductPriceModel>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg#manageProviderProductNutritionalInfo(com.tc.provider.orm.model.TcProviderProductNutritionalModel)
     */
    @Override
    public MtOperateResult<TcProviderProductNutritionalModel> manageProviderProductNutritionalInfo(TcProviderProductNutritionalModel tcProviderProductNutritionalModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/manage/do/nutritional/mng";

        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(tcProviderProductNutritionalModel));
        MtOperateResult<TcProviderProductNutritionalModel> obj = (MtOperateResult<TcProviderProductNutritionalModel>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<TcProviderProductNutritionalModel>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg#manageProviderProductOpManualInfo(com.tc.provider.orm.model.TcProviderProductOpManualModel)
     */
    @Override
    public MtOperateResult<TcProviderProductOpManualModel> manageProviderProductOpManualInfo(TcProviderProductOpManualModel tcProviderProductOpManualModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/manage/do/op/manual/mng" + tcProviderProductOpManualModel;

        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(tcProviderProductOpManualModel));
        MtOperateResult<TcProviderProductOpManualModel> obj = (MtOperateResult<TcProviderProductOpManualModel>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<TcProviderProductOpManualModel>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg#queryProductAllPriceByProductId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<TcProviderProductPriceModel>> queryProductAllPriceByProductId(String productId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/manage/list/price/" + productId;

        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<TcProviderProductPriceModel>> obj = (MtOperateResult<List<TcProviderProductPriceModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<TcProviderProductPriceModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg#queryProductAllNutritionalByProductId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<TcProviderProductNutritionalModel>> queryProductAllNutritionalByProductId(String productId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/manage/list/nutritional/" + productId;

        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<TcProviderProductNutritionalModel>> obj = (MtOperateResult<List<TcProviderProductNutritionalModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<TcProviderProductNutritionalModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg#queryProductAllOpManualByProductId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<TcProviderProductOpManualModel>> queryProductAllOpManualByProductId(String productId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/manage/list/op/manual/" + productId;

        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<TcProviderProductOpManualModel>> obj = (MtOperateResult<List<TcProviderProductOpManualModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<TcProviderProductOpManualModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg#queryProductAllImagesByProductId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<TcProviderProductImagesModel>> queryProductAllImagesByProductId(String productId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/manage/list/images/" + productId;

        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<TcProviderProductImagesModel>> obj = (MtOperateResult<List<TcProviderProductImagesModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<TcProviderProductImagesModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg#findTcProviderProductByCondition(java.lang.String, java.lang.String)
     */
    @Override
    public MtOperateResult<List<TcProviderProductModel>> findTcProviderProductByCondition(String shopId, String productName) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");

        StringBuffer subUrl = new StringBuffer();
        if (StringUtils.isNotBlank(shopId)) {
            subUrl.append("shopId=" + shopId);
        }
        if (StringUtils.isNotBlank(productName)) {
            try {
                subUrl.append("&productName=" + URLEncoder.encode(productName, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        String url = pathPrefix + "/tiancan/api/provider/manage/list/condition/shop/?" + subUrl.toString();
        String result = HttpClientUtil.insureResponsePost(url, null);
        MtOperateResult<List<TcProviderProductModel>> obj = (MtOperateResult<List<TcProviderProductModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<TcProviderProductModel>>>() {
            });
        return obj;
    }

}
