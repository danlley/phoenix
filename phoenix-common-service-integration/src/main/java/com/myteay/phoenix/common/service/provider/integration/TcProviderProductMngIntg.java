/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.provider.integration;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.myteay.phoenix.biz.service.impl.MtServiceResult;
import com.tc.provider.orm.model.TcProviderProductModel;

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
    public MtServiceResult<List<TcProviderProductModel>> queryGoodsByShopId(@PathVariable String shopId);
}
