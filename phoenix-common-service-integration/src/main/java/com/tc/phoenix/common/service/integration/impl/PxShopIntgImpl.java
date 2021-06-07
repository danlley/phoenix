/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.tc.phoenix.common.service.integration.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.tc.phoenix.common.service.integration.PxShopIntg;
import com.tc.phoenix.common.util.HttpClientUtil;
import com.tc.phoenix.common.util.MtOperateResult;
import com.tc.shop.orm.model.PxShopModel;

/**
 * 
 * @author min.weixm
 * @version $Id: PxShopIntgImpl.java, v 0.1 Jun 12, 2019 12:05:24 AM min.weixm Exp $
 */
public class PxShopIntgImpl implements PxShopIntg {

    /** 环境变量 */
    @Autowired
    private Environment env;

    /** 
     * @see com.tc.phoenix.common.service.integration.PxShopIntg#queryShopAll()
     */
    @Override
    public MtOperateResult<List<PxShopModel>> queryShopAll() {
        String pathPrefix = env.getProperty("tiancan.phoenix.dbcenter.path.prefix");
        String url = pathPrefix + "/tiancan/api/shop/services/op/all";

        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<PxShopModel>> obj = (MtOperateResult<List<PxShopModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<PxShopModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.tc.phoenix.common.service.integration.PxShopIntg#manageShop(com.myteay.phoenix.core.model.manage.PxShopModel)
     */
    @Override
    public MtOperateResult<PxShopModel> manageShop(PxShopModel pxShopModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.shop.path.prefix");
        String url = pathPrefix + "/tiancan/api/shop/services/op/manage";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(pxShopModel));
        MtOperateResult<PxShopModel> obj = (MtOperateResult<PxShopModel>) JSON.parseObject(result, new TypeReference<MtOperateResult<PxShopModel>>() {
        });
        return obj;
    }

    /** 
     * @see com.tc.phoenix.common.service.integration.PxShopIntg#queryCampConfig(java.lang.String)
     */
    @Override
    public String queryCampConfig(String shopId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.shop.path.prefix");
        String url = pathPrefix + "/tiancan/api/shop/conf/services/op/query/camp/" + shopId;
        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<String> obj = (MtOperateResult<String>) JSON.parseObject(result, new TypeReference<MtOperateResult<String>>() {
        });
        return obj.getResult();
    }

}
