/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.provider.integration.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.myteay.phoenix.common.service.provider.integration.TcProviderProductOrderOutIntg;
import com.myteay.phoenix.common.util.HttpClientUtil;
import com.myteay.phoenix.common.util.MtOperateResult;
import com.tc.provider.orm.model.TcProviderProductOutModel;

/**
 * 进货清单管理
 * 
 * @author min.weixm
 * @version $Id: TcProviderProductOrderOutIntgImpl.java, v 0.1 Nov 21, 2019 1:21:56 PM min.weixm Exp $
 */
public class TcProviderProductOrderOutIntgImpl implements TcProviderProductOrderOutIntg {

    /** 环境变量 */
    @Autowired
    private Environment env;

    /** 
     * @see com.myteay.phoenix.common.service.provider.integration.TcProviderProductOrderOutIntg#manageProviderProductOrderOutInfo(com.tc.provider.orm.model.TcProviderProductOutModel)
     */
    @Override
    public MtOperateResult<TcProviderProductOutModel> manageProviderProductOrderOutInfo(TcProviderProductOutModel tcProviderProductOutModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.provider.path.prefix");
        String url = pathPrefix + "/tiancan/api/provider/order/out/mng";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(tcProviderProductOutModel));
        return (MtOperateResult<TcProviderProductOutModel>) JSON.parseObject(result, new TypeReference<MtOperateResult<TcProviderProductOutModel>>() {
        });
    }

}
