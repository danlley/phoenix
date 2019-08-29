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
import com.tc.provider.orm.model.TcProviderProductModel;

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
        return tcProviderProductMngIntg.queryGoodsByShopId(shopId);
    }
}
