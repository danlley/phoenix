/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.biz.service.impl.MtServiceResult;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxGoodsCostCfgAdvModel;
import com.myteay.phoenix.core.service.manage.component.PxGoodsCostCfgComponent;

/**
 * 商品成本配置信息管理
 * 
 * @author danlley
 * @version $Id: PxGoodsCostCfgController.java, v 0.1 May 7, 2019 7:25:10 PM danlley Exp $
 */
@RestController
@RequestMapping(value = "myteay/api/phoenix/admin/manage/goods/cost")
public class PxGoodsCostCfgController {

    /** 日志 */
    private static final Logger     logger = LoggerFactory.getLogger(LoggerNames.PX_CASHIER_DEFAULT);

    /** 商品成本管理组件 */
    @Autowired
    private PxGoodsCostCfgComponent pxGoodsCostCfgComponent;

    /**
     * 修改商品成本信息
     * 
     * @param pxGoodsCostCfgAdvModel
     * @return
     * @throws PxManageException 
     */
    @RequestMapping(value = "/manage", method = { RequestMethod.POST })
    public MtServiceResult<PxGoodsCostCfgAdvModel> manageGoodsCostCfgInfo(PxGoodsCostCfgAdvModel pxGoodsCostCfgAdvModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始管理商品成本 pxGoodsCostCfgAdvModel=" + pxGoodsCostCfgAdvModel);
        }

        return new MtServiceResult<>(pxGoodsCostCfgComponent.manageGoodsCostCfgInfo(pxGoodsCostCfgAdvModel));
    }

    /**
     * 查询所有商品成本信息
     * 
     * @return
     * @throws PxManageException 
     */
    @RequestMapping(value = "/all", method = { RequestMethod.GET })
    public MtServiceResult<List<PxGoodsCostCfgAdvModel>> queryAllGoodsCostCfg() throws PxManageException {

        return new MtServiceResult<>(pxGoodsCostCfgComponent.queryAllGoodsCostCfg());
    }
}
