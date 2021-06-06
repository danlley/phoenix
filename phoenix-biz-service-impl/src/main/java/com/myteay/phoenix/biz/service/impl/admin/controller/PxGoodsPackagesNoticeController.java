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

import com.myteay.phoenix.common.service.integration.PxGoodsPackagesNoticeIntg;
import com.myteay.phoenix.common.util.MtOperateResult;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.tc.ccopass.logger.Logger;
import com.tc.ccopass.logger.LoggerFactory;
import com.tc.dbcenter.common.orm.model.PxGoodsPackagesNoticeModel;
import com.tc.phoenix.common.util.log.LoggerNames;

/**
 * 温馨提醒摘要管理
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesNoticeController.java, v 0.1 Aug 5, 2018 10:08:02 PM min.weixm Exp $
 */
@RestController
@RequestMapping(value = "myteay/api/phoenix/admin/manage/pkgs/notice")
public class PxGoodsPackagesNoticeController {

    /** 日志 */
    private static final Logger       logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /** 后台一般性简单业务管理组件 */
    @Autowired
    private PxGoodsPackagesNoticeIntg pxGoodsPackagesNoticeIntg;

    /**
     * 通过温馨提醒摘要ID查询店铺下的所有温馨提醒摘要列表
     * 
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/list/notice/{goodsId}", method = { RequestMethod.GET })
    public MtOperateResult<List<PxGoodsPackagesNoticeModel>> queryGoodsByShopId(@PathVariable String goodsId) {
        MtOperateResult<List<PxGoodsPackagesNoticeModel>> result = null;

        MtOperateResult<List<PxGoodsPackagesNoticeModel>> componentResult = null;
        try {
            componentResult = pxGoodsPackagesNoticeIntg.queryPackagesNoticeListByGoodsId(goodsId);
            result = new MtOperateResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询温馨提醒摘要信息发生未知异常 " + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * 温馨提醒摘要管理服务（增、删、改、单条查询）
     * 
     * @param pxGoodsPackagesNoticeModel
     * @return
     */
    @RequestMapping(value = "/manage", method = { RequestMethod.POST })
    public MtOperateResult<PxGoodsPackagesNoticeModel> manageGoods(@RequestBody PxGoodsPackagesNoticeModel pxGoodsPackagesNoticeModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始保存温馨提醒摘要信息 pxGoodsPackagesNoticeModel=" + pxGoodsPackagesNoticeModel);
        }
        MtOperateResult<PxGoodsPackagesNoticeModel> result = null;
        try {
            MtOperateResult<PxGoodsPackagesNoticeModel> innerResult = pxGoodsPackagesNoticeIntg.manageGoodsPackagesNotice(pxGoodsPackagesNoticeModel);
            result = new MtOperateResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("保存温馨提醒摘要信息发生异常" + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

}
