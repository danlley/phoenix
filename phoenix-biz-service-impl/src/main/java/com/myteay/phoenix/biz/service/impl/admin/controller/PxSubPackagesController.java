/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.admin.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.phoenix.biz.service.impl.MtServiceResult;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxSubPackagesModel;
import com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent;
import com.myteay.phoenix.core.service.manage.component.PxSubPackagesComponent;

/**
 * 子套餐管理
 * 
 * @author min.weixm
 * @version $Id: PxSubPackagesController.java, v 0.1 Jul 28, 2018 11:51:31 AM min.weixm Exp $
 */
@RestController
@RequestMapping(value = "myteay/api/phoenix/admin/manage/sub/pkgs")
public class PxSubPackagesController {
    /** 日志 */
    public static final Logger      logger = Logger.getLogger(PxSubPackagesController.class);

    /** 后台一般性简单业务管理组件 */
    @Autowired
    private PxCommonManageComponent pxCommonManageComponent;

    /** 子套餐管理组件 */
    @Autowired
    private PxSubPackagesComponent  pxSubPackagesComponent;

    /**
     * 通过套餐包ID查询店铺下的所有子套餐列表
     * 
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/list/sub/packages/{packagesDetailId}", method = { RequestMethod.GET })
    public MtServiceResult<List<PxSubPackagesModel>> queryGoodsByShopId(@PathVariable String packagesDetailId) {
        MtServiceResult<List<PxSubPackagesModel>> result = null;

        MtOperateResult<List<PxSubPackagesModel>> componentResult = null;
        try {
            componentResult = pxCommonManageComponent.querySubPackagesByPackagesId(packagesDetailId);
            result = new MtServiceResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询子套餐信息发生未知异常 " + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * 子套餐管理服务（增、删、改、单条查询）
     * 
     * @param pxShopModel
     * @return
     */
    @RequestMapping(value = "/manage", method = { RequestMethod.POST })
    public MtServiceResult<PxSubPackagesModel> manageGoods(@RequestBody PxSubPackagesModel pxSubPackagesModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始保存子套餐信息 pxSubPackagesModel=" + pxSubPackagesModel);
        }
        MtServiceResult<PxSubPackagesModel> result = null;
        try {
            MtOperateResult<PxSubPackagesModel> innerResult = pxSubPackagesComponent.manageSubPackages(pxSubPackagesModel);
            result = new MtServiceResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("保存子套餐信息发生异常" + e.getMessage(), e);
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
     * Setter method for property <tt>pxSubPackagesComponent</tt>.
     * 
     * @param pxSubPackagesComponent value to be assigned to property pxSubPackagesComponent
     */
    public void setPxSubPackagesComponent(PxSubPackagesComponent pxSubPackagesComponent) {
        this.pxSubPackagesComponent = pxSubPackagesComponent;
    }

}