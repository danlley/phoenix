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
import com.tc.provider.orm.model.TcProviderProductModel;

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
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductMngIntg#queryGoodsByShopId(java.lang.String)
     */
    @Override
    public MtServiceResult<List<TcProviderProductModel>> queryGoodsByShopId(String shopId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/manage/list/shop/" + shopId;

        String result = HttpClientUtil.insureResponseGet(url);
        MtServiceResult<List<TcProviderProductModel>> obj = (MtServiceResult<List<TcProviderProductModel>>) JSON.parseObject(result,
            new TypeReference<MtServiceResult<List<TcProviderProductModel>>>() {
            });
        return obj;
    }

}
