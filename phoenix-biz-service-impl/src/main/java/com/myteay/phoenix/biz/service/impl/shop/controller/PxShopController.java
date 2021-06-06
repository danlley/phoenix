/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2021 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.phoenix.common.service.integration.PxShopIntg;
import com.myteay.phoenix.common.util.MtOperateResult;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.tc.ccopass.logger.Logger;
import com.tc.ccopass.logger.LoggerFactory;
import com.tc.phoenix.common.util.log.LoggerNames;
import com.tc.shop.orm.model.PxShopModel;

/**
 * 店铺管理
 * 
 * @author min.weixm
 * @version $Id: PxShopController.java, v 0.1 Jul 24, 2018 12:35:57 PM min.weixm Exp $
 */
@RestController
@RequestMapping(value = "tiancan/api/shop/services/op")
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
    public MtOperateResult<List<PxShopModel>> queryShopAll() {
        MtOperateResult<List<PxShopModel>> result = null;

        MtOperateResult<List<PxShopModel>> componentResult = null;
        try {
            componentResult = pxShopIntg.queryShopAll();
            result = new MtOperateResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询店铺信息发生未知异常 " + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
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
    public MtOperateResult<PxShopModel> manageShop(@RequestBody PxShopModel pxShopModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始保存店铺信息 pxShopModel=" + pxShopModel);
        }
        MtOperateResult<PxShopModel> result = null;
        try {
            MtOperateResult<PxShopModel> innerResult = pxShopIntg.manageShop(pxShopModel);
            result = new MtOperateResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("保存店铺信息发生异常" + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

}
