/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.biz.service.impl.MtServiceResult;
import com.myteay.phoenix.common.service.integration.PxShopIntg;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxShopModel;
import com.tc.phoenix.common.util.log.LoggerNames;

/**
 * 店铺管理
 * 
 * @author min.weixm
 * @version $Id: PxShopController.java, v 0.1 Jul 24, 2018 12:35:57 PM min.weixm Exp $
 */
@RestController
@RequestMapping(value = "myteay/api/phoenix/admin/manage/shop")
public class PxShopController {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /** 后台一般性简单业务管理组件 */
    @Autowired
    private PxShopIntg          pxShopIntg;

    /**
     * 查询所有店铺信息
     * 
     * @return
     */
    @RequestMapping(value = "/all", method = { RequestMethod.GET })
    public MtServiceResult<List<PxShopModel>> queryShopAll() {
        MtServiceResult<List<PxShopModel>> result = null;

        MtOperateResult<List<PxShopModel>> componentResult = null;
        try {
            componentResult = pxShopIntg.queryShopAll();
            result = new MtServiceResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询店铺信息发生未知异常 " + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * 店铺管理服务（增、删、改、单条查询）
     * 
     * @param pxShopModel
     * @return
     */
    @RequestMapping(value = "/manage", method = { RequestMethod.POST })
    public MtServiceResult<PxShopModel> manageShop(@RequestBody PxShopModel pxShopModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始保存店铺信息 pxShopModel=" + pxShopModel);
        }
        MtServiceResult<PxShopModel> result = null;
        try {
            MtOperateResult<PxShopModel> innerResult = pxShopIntg.manageShop(pxShopModel);
            result = new MtServiceResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("保存店铺信息发生异常" + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

}
