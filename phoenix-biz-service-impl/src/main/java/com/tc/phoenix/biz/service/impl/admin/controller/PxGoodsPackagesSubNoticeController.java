/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2021 All Rights Reserved.
 */
package com.tc.phoenix.biz.service.impl.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tc.ccopass.logger.Logger;
import com.tc.ccopass.logger.LoggerFactory;
import com.tc.dbcenter.common.orm.model.PxGoodsPackagesSubNoticeModel;
import com.tc.phoenix.common.service.integration.PxGoodsPackagesSubNoticeIntg;
import com.tc.phoenix.common.util.MtOperateResult;
import com.tc.phoenix.common.util.enums.MtOperateExResultEnum;
import com.tc.phoenix.common.util.enums.MtOperateResultEnum;
import com.tc.phoenix.common.util.log.LoggerNames;

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
    private static final Logger          logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /** 后台一般性简单业务管理组件 */
    @Autowired
    private PxGoodsPackagesSubNoticeIntg pxGoodsPackagesSubNoticeIntg;

    /**
     * 通过温馨提醒子项ID查询店铺下的所有温馨提醒子项列表
     * 
     * @param packagesNoticeId
     * @return
     */
    @RequestMapping(value = "/list/notice/{packagesNoticeId}", method = { RequestMethod.GET })
    public MtOperateResult<List<PxGoodsPackagesSubNoticeModel>> queryGoodsByShopId(@PathVariable String packagesNoticeId) {
        MtOperateResult<List<PxGoodsPackagesSubNoticeModel>> result = null;

        MtOperateResult<List<PxGoodsPackagesSubNoticeModel>> componentResult = null;
        try {
            componentResult = pxGoodsPackagesSubNoticeIntg.queryPackagesNoticeListByNoticeId(packagesNoticeId);
            result = new MtOperateResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询温馨提醒子项信息发生未知异常 " + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
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
    public MtOperateResult<PxGoodsPackagesSubNoticeModel> manageGoods(@RequestBody PxGoodsPackagesSubNoticeModel pxGoodsPackagesSubNoticeModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始保存温馨提醒子项信息 pxGoodsPackagesSubNoticeModel=" + pxGoodsPackagesSubNoticeModel);
        }
        MtOperateResult<PxGoodsPackagesSubNoticeModel> result = null;
        try {
            MtOperateResult<PxGoodsPackagesSubNoticeModel> innerResult = pxGoodsPackagesSubNoticeIntg.manageSubPackages(pxGoodsPackagesSubNoticeModel);
            result = new MtOperateResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("保存温馨提醒子项信息发生异常" + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

}
