/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.admin.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.phoenix.biz.service.impl.MtServiceResult;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxShopModel;
import com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent;
import com.myteay.phoenix.core.service.manage.component.PxShopComponent;

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
    public static final Logger      logger = Logger.getLogger(PxShopController.class);

    /** 后台一般性简单业务管理组件 */
    @Autowired
    private PxCommonManageComponent pxCommonManageComponent;

    /** 后台一般性简单业务管理组件 */
    @Autowired
    private PxShopComponent         pxShopComponent;

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
            componentResult = pxCommonManageComponent.queryShopAll();
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
            MtOperateResult<PxShopModel> innerResult = pxShopComponent.manageShop(pxShopModel);
            result = new MtServiceResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("保存店铺信息发生异常" + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * Setter method for property <tt>pxCommonManageComponent</tt>.
     * 
     * @param pxCommonManageComponent value to be assigned to property pxCommonManageComponent
     */
    public void setPxCommonManageComponent(PxCommonManageComponent pxCommonManageComponent) {
        this.pxCommonManageComponent = pxCommonManageComponent;
    }

    /**
     * Setter method for property <tt>pxShopComponent</tt>.
     * 
     * @param pxShopComponent value to be assigned to property pxShopComponent
     */
    public void setPxShopComponent(PxShopComponent pxShopComponent) {
        this.pxShopComponent = pxShopComponent;
    }
}
