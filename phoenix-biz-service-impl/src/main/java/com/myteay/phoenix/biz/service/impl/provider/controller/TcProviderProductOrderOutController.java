/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.biz.service.impl.MtServiceResult;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.service.provider.integration.TcProviderProductOrderOutIntg;
import com.tc.provider.orm.model.TcProviderProductOutModel;

/**
 * 进货清单管理
 * 
 * @author min.weixm
 * @version $Id: TcProviderProductOrderOutController.java, v 0.1 Aug 27, 2019 3:19:03 PM min.weixm Exp $
 */
@RestController
@RequestMapping(value = "tiancan/api/provider/order")
public class TcProviderProductOrderOutController {

    /** 日志 */
    private static final Logger           logger = LoggerFactory.getLogger(LoggerNames.PX_CASHIER_DEFAULT);

    /** 原材料管理组件 */
    @Autowired
    private TcProviderProductOrderOutIntg tcProviderProductOrderOutIntg;

    /**
     * 原材料配置信息管理
     * 
     * @param tcProviderProductModel
     * @return
     */
    @RequestMapping(value = "/out/mng", method = { RequestMethod.POST })
    public MtServiceResult<TcProviderProductOutModel> manageProviderProductOrderOutInfo(@RequestBody TcProviderProductOutModel tcProviderProductOutModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始管理进货清单 tcProviderProductOutModel=" + tcProviderProductOutModel);
        }

        return tcProviderProductOrderOutIntg.manageProviderProductOrderOutInfo(tcProviderProductOutModel);

    }

    /**
     * Setter method for property <tt>tcProviderProductOrderOutIntg</tt>.
     * 
     * @param tcProviderProductOrderOutIntg value to be assigned to property tcProviderProductOrderOutIntg
     */
    public void setTcProviderProductOrderOutIntg(TcProviderProductOrderOutIntg tcProviderProductOrderOutIntg) {
        this.tcProviderProductOrderOutIntg = tcProviderProductOrderOutIntg;
    }

}
