/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.integration.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.myteay.phoenix.common.service.integration.PxMobileGoodsIntg;
import com.myteay.phoenix.common.util.HttpClientUtil;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.mobile.PxMobileGoodsModel;

/**
 * 
 * @author min.weixm
 * @version $Id: PxMobileGoodsIntgImpl.java, v 0.1 Jun 12, 2019 12:29:54 AM min.weixm Exp $
 */
public class PxMobileGoodsIntgImpl implements PxMobileGoodsIntg {

    /** 环境变量 */
    @Autowired
    private Environment env;

    /** 
     * @see com.myteay.phoenix.common.service.integration.PxMobileGoodsIntg#queryNextGoodsList(java.util.List)
     */
    @Override
    public MtOperateResult<List<PxMobileGoodsModel>> queryNextGoodsList(List<String> excludeGoodsIds) {
        String pathPrefix = env.getProperty("tiancan.phoenix.dbcenter.path.prefix");
        String url = pathPrefix + "/myteay/api/phoenix/mobile/goods/list/";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(excludeGoodsIds));
        MtOperateResult<List<PxMobileGoodsModel>> obj = (MtOperateResult<List<PxMobileGoodsModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<PxMobileGoodsModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.integration.PxMobileGoodsIntg#queryGoodsDetail(java.lang.String)
     */
    @Override
    public MtOperateResult<PxMobileGoodsModel> queryGoodsDetail(String goodsId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.dbcenter.path.prefix");
        String url = pathPrefix + "/myteay/api/phoenix/mobile/goods/single/" + goodsId;
        String result = HttpClientUtil.insureResponsePost(url, null);
        MtOperateResult<PxMobileGoodsModel> obj = (MtOperateResult<PxMobileGoodsModel>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<PxMobileGoodsModel>>() {
            });
        return obj;
    }

}
