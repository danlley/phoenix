/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.integration.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.myteay.phoenix.common.service.integration.PxShopIntg;
import com.myteay.phoenix.common.util.HttpClientUtil;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxShopModel;

/**
 * 
 * @author min.weixm
 * @version $Id: PxShopIntgImpl.java, v 0.1 Jun 12, 2019 12:05:24 AM min.weixm Exp $
 */
public class PxShopIntgImpl implements PxShopIntg {

    /** 
     * @see com.myteay.phoenix.common.service.integration.PxShopIntg#queryShopAll()
     */
    @Override
    public MtOperateResult<List<PxShopModel>> queryShopAll() {
        String url = "http://192.168.0.101:40051/myteay/api/phoenix/admin/manage/shop/all";

        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<PxShopModel>> obj = (MtOperateResult<List<PxShopModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<PxShopModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.integration.PxShopIntg#manageShop(com.myteay.phoenix.core.model.manage.PxShopModel)
     */
    @Override
    public MtOperateResult<PxShopModel> manageShop(PxShopModel pxShopModel) {
        String url = "http://192.168.0.101:40051/myteay/api/phoenix/admin/manage/shop/manage";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(pxShopModel));
        MtOperateResult<PxShopModel> obj = (MtOperateResult<PxShopModel>) JSON.parseObject(result, new TypeReference<MtOperateResult<PxShopModel>>() {
        });
        return obj;
    }

}
