/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2021 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tc.ccopass.logger.Logger;
import com.tc.ccopass.logger.LoggerFactory;
import com.tc.dbcenter.common.orm.model.PxGoodsPackagesDetailModel;
import com.tc.phoenix.common.service.integration.PxGoodsPackagesDetailIntg;
import com.tc.phoenix.common.util.MtOperateResult;
import com.tc.phoenix.common.util.enums.MtOperateExResultEnum;
import com.tc.phoenix.common.util.enums.MtOperateResultEnum;
import com.tc.phoenix.common.util.log.LoggerNames;

/**
 * 套餐包管理
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesDetailController.java, v 0.1 Jul 27, 2018 9:14:49 PM min.weixm Exp $
 */
@RestController
@RequestMapping(value = "myteay/api/phoenix/admin/manage/pkgs")
public class PxGoodsPackagesDetailController {

    /** 日志 */
    private static final Logger       logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /** 后台一般性简单业务管理组件 */
    @Autowired
    private PxGoodsPackagesDetailIntg pxGoodsPackagesDetailIntg;

    /**
     * 查询所有套餐包信息
     * 
     * @return
     */
    @RequestMapping(value = "/all", method = { RequestMethod.GET })
    public MtOperateResult<List<PxGoodsPackagesDetailModel>> queryPackagesDetailListAll() {
        MtOperateResult<List<PxGoodsPackagesDetailModel>> result = null;

        MtOperateResult<List<PxGoodsPackagesDetailModel>> componentResult = null;
        try {
            componentResult = pxGoodsPackagesDetailIntg.queryPackagesDetailListAll();
            result = new MtOperateResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询套餐包信息发生未知异常 " + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * 通过商品摘要ID查询店铺下的所有套餐包列表
     * 
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/list/goods/{goodsId}", method = { RequestMethod.GET })
    public MtOperateResult<List<PxGoodsPackagesDetailModel>> queryGoodsByShopId(@PathVariable String goodsId) {
        MtOperateResult<List<PxGoodsPackagesDetailModel>> result = null;

        MtOperateResult<List<PxGoodsPackagesDetailModel>> componentResult = null;
        try {
            componentResult = pxGoodsPackagesDetailIntg.queryPackagesDetailListByGoodsId(goodsId);
            result = new MtOperateResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询套餐包信息发生未知异常 " + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * 套餐包管理服务（增、删、改、单条查询）
     * 
     * @param pxShopModel
     * @return
     */
    @RequestMapping(value = "/manage", method = { RequestMethod.POST })
    public MtOperateResult<PxGoodsPackagesDetailModel> manageGoods(@RequestBody PxGoodsPackagesDetailModel pxGoodsPackagesDetailModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始保存套餐包信息 pxGoodsPackagesDetailModel=" + pxGoodsPackagesDetailModel);
        }
        MtOperateResult<PxGoodsPackagesDetailModel> result = null;
        try {
            MtOperateResult<PxGoodsPackagesDetailModel> innerResult = pxGoodsPackagesDetailIntg.manageGoodsPackagesDetail(pxGoodsPackagesDetailModel);
            result = new MtOperateResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("保存套餐包信息发生异常" + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

}
