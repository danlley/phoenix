/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.phoenix.biz.service.impl.MtServiceResult;
import com.myteay.phoenix.common.service.integration.PxGoodsPackagesDetailIntg;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel;
import com.tc.common.lang.logger.Logger;
import com.tc.common.lang.logger.LoggerFactory;
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
    public MtServiceResult<List<PxGoodsPackagesDetailModel>> queryPackagesDetailListAll() {
        MtServiceResult<List<PxGoodsPackagesDetailModel>> result = null;

        MtOperateResult<List<PxGoodsPackagesDetailModel>> componentResult = null;
        try {
            componentResult = pxGoodsPackagesDetailIntg.queryPackagesDetailListAll();
            result = new MtServiceResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询套餐包信息发生未知异常 " + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
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
    public MtServiceResult<List<PxGoodsPackagesDetailModel>> queryGoodsByShopId(@PathVariable String goodsId) {
        MtServiceResult<List<PxGoodsPackagesDetailModel>> result = null;

        MtOperateResult<List<PxGoodsPackagesDetailModel>> componentResult = null;
        try {
            componentResult = pxGoodsPackagesDetailIntg.queryPackagesDetailListByGoodsId(goodsId);
            result = new MtServiceResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询套餐包信息发生未知异常 " + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
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
    public MtServiceResult<PxGoodsPackagesDetailModel> manageGoods(@RequestBody PxGoodsPackagesDetailModel pxGoodsPackagesDetailModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始保存套餐包信息 pxGoodsPackagesDetailModel=" + pxGoodsPackagesDetailModel);
        }
        MtServiceResult<PxGoodsPackagesDetailModel> result = null;
        try {
            MtOperateResult<PxGoodsPackagesDetailModel> innerResult = pxGoodsPackagesDetailIntg.manageGoodsPackagesDetail(pxGoodsPackagesDetailModel);
            result = new MtServiceResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("保存套餐包信息发生异常" + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

}
