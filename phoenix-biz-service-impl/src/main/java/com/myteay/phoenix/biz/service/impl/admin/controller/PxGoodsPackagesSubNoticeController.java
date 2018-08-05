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
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesSubNoticeModel;
import com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent;
import com.myteay.phoenix.core.service.manage.component.PxGoodsPackagesSubNoticeComponent;

/**
 * 温馨提醒子项管理
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesSubNoticeController.java, v 0.1 Aug 5, 2018 11:33:25 PM min.weixm Exp $
 */
@RestController
@RequestMapping(value = "myteay/api/phoenix/admin/manage/pkgs/sub/notice")
public class PxGoodsPackagesSubNoticeController {

    /** 日志 */
    public static final Logger                logger = Logger.getLogger(PxGoodsPackagesSubNoticeController.class);

    /** 后台一般性简单业务管理组件 */
    @Autowired
    private PxCommonManageComponent           pxCommonManageComponent;

    /** 温馨提醒子项管理组件 */
    @Autowired
    private PxGoodsPackagesSubNoticeComponent pxGoodsPackagesSubNoticeComponent;

    /**
     * 通过温馨提醒子项ID查询店铺下的所有温馨提醒子项列表
     * 
     * @param packagesNoticeId
     * @return
     */
    @RequestMapping(value = "/list/notice/{packagesNoticeId}", method = { RequestMethod.GET })
    public MtServiceResult<List<PxGoodsPackagesSubNoticeModel>> queryGoodsByShopId(@PathVariable String packagesNoticeId) {
        MtServiceResult<List<PxGoodsPackagesSubNoticeModel>> result = null;

        MtOperateResult<List<PxGoodsPackagesSubNoticeModel>> componentResult = null;
        try {
            componentResult = pxCommonManageComponent.queryPackagesNoticeListByNoticeId(packagesNoticeId);
            result = new MtServiceResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询温馨提醒子项信息发生未知异常 " + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * 温馨提醒子项管理服务（增、删、改、单条查询）
     * 
     * @param pxGoodsPackagesSubNoticeModel
     * @return
     */
    @RequestMapping(value = "/manage", method = { RequestMethod.POST })
    public MtServiceResult<PxGoodsPackagesSubNoticeModel> manageGoods(@RequestBody PxGoodsPackagesSubNoticeModel pxGoodsPackagesSubNoticeModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始保存温馨提醒子项信息 pxGoodsPackagesSubNoticeModel=" + pxGoodsPackagesSubNoticeModel);
        }
        MtServiceResult<PxGoodsPackagesSubNoticeModel> result = null;
        try {
            MtOperateResult<PxGoodsPackagesSubNoticeModel> innerResult = pxGoodsPackagesSubNoticeComponent.manageSubPackages(pxGoodsPackagesSubNoticeModel);
            result = new MtServiceResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("保存温馨提醒子项信息发生异常" + e.getMessage(), e);
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
     * Setter method for property <tt>pxGoodsPackagesSubNoticeComponent</tt>.
     * 
     * @param pxGoodsPackagesSubNoticeComponent value to be assigned to property pxGoodsPackagesSubNoticeComponent
     */
    public void setPxGoodsPackagesSubNoticeComponent(PxGoodsPackagesSubNoticeComponent pxGoodsPackagesSubNoticeComponent) {
        this.pxGoodsPackagesSubNoticeComponent = pxGoodsPackagesSubNoticeComponent;
    }

}
