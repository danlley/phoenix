/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2015-2020 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.prodtrans.integration.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.myteay.phoenix.common.service.prodtrans.integration.TcProdtransConfigMngIntg;
import com.myteay.phoenix.common.util.HttpClientUtil;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.tc.prodtrans.core.model.TcPtsUserShopProdConfigModel;

/**
 * 产品账配置信息管理
 * 
 * @author min.weixm
 * @version $Id: TcProdtransConfigMngIntgImpl.java, v 0.1 Mar 4, 2020 11:15:31 PM min.weixm Exp $
 */
public class TcProdtransConfigMngIntgImpl implements TcProdtransConfigMngIntg {

    /** 环境变量 */
    @Autowired
    private Environment env;

    /** 
     * @see com.myteay.phoenix.common.service.prodtrans.integration.TcProdtransConfigMngIntg#savePtsUserShopProdConfig(com.tc.prodtrans.core.model.TcPtsUserShopProdConfigModel)
     */
    @Override
    public MtOperateResult<String> savePtsUserShopProdConfig(TcPtsUserShopProdConfigModel tcPtsUserShopProdConfigModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.prodtrans.path.prefix");
        String url = pathPrefix + "/tiancan/api/prodtrans/manage/save/";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(tcPtsUserShopProdConfigModel));
        return (MtOperateResult<String>) JSON.parseObject(result, new TypeReference<MtOperateResult<String>>() {
        });
    }

    /** 
     * @see com.myteay.phoenix.common.service.prodtrans.integration.TcProdtransConfigMngIntg#queryPtsUserShopProdConfigByShop(java.lang.String)
     */
    @Override
    public MtOperateResult<List<TcPtsUserShopProdConfigModel>> queryPtsUserShopProdConfigByShop(String shopId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.prodtrans.path.prefix");
        String url = pathPrefix + "/tiancan/api/prodtrans/manage/query/shop/" + shopId;
        String result = HttpClientUtil.insureResponsePost(url, null);
        return (MtOperateResult<List<TcPtsUserShopProdConfigModel>>) JSON.parseObject(result, new TypeReference<MtOperateResult<List<TcPtsUserShopProdConfigModel>>>() {
        });
    }

    /** 
     * @see com.myteay.phoenix.common.service.prodtrans.integration.TcProdtransConfigMngIntg#modifyPtsUserShopProdConfig(com.tc.prodtrans.core.model.TcPtsUserShopProdConfigModel)
     */
    @Override
    public MtOperateResult<String> modifyPtsUserShopProdConfig(TcPtsUserShopProdConfigModel tcPtsUserShopProdConfigModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.prodtrans.path.prefix");
        String url = pathPrefix + "/tiancan/api/prodtrans/manage/modify/";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(tcPtsUserShopProdConfigModel));
        return (MtOperateResult<String>) JSON.parseObject(result, new TypeReference<MtOperateResult<String>>() {
        });
    }

    /** 
     * @see com.myteay.phoenix.common.service.prodtrans.integration.TcProdtransConfigMngIntg#removePtsUserShopProdConfigById(java.lang.String, java.lang.String)
     */
    @Override
    public MtOperateResult<String> removePtsUserShopProdConfigById(String prodtransId, String shopId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.prodtrans.path.prefix");
        String url = pathPrefix + "/tiancan/api/prodtrans/manage/remove/shop/" + shopId + "/prodtrans/" + prodtransId;
        String result = HttpClientUtil.insureResponsePost(url, null);
        return (MtOperateResult<String>) JSON.parseObject(result, new TypeReference<MtOperateResult<String>>() {
        });
    }

}
