/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2021 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.camp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.phoenix.common.service.camp.integration.CampBaseIntg;
import com.tc.ccopass.logger.Logger;
import com.tc.ccopass.logger.LoggerFactory;
import com.tc.phoenix.common.util.MtOperateResult;
import com.tc.phoenix.common.util.enums.MtOperateExResultEnum;
import com.tc.phoenix.common.util.enums.MtOperateResultEnum;
import com.tc.phoenix.common.util.log.LoggerNames;
import com.tc.promocore.common.orm.model.CampBaseModel;

/**
 * 店内营销活动
 * 
 * @author danlley
 * @version $Id: CampBaseController.java, v 0.1 Dec 17, 2018 12:37:03 AM danlley Exp $
 */
@RestController
@RequestMapping(value = "myteay/api/phoenix/camp/manage/base")
public class CampBaseController {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /** 店内营销活动基本信息管理组件 */
    @Autowired
    private CampBaseIntg        campBaseIntg;

    /**
     * 通过店铺ID查询店铺下的所有店内营销活动
     * 
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/list/shop/{shopId}", method = { RequestMethod.GET })
    public MtOperateResult<List<CampBaseModel>> queryGoodsByShopId(@PathVariable String shopId) {
        MtOperateResult<List<CampBaseModel>> result = null;

        MtOperateResult<List<CampBaseModel>> componentResult = null;
        try {
            componentResult = campBaseIntg.queryAllCampBaseByShopId(shopId);
            result = new MtOperateResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询所有店内营销活动信息发生未知异常 " + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * 店内营销活动管理服务（增、删、改、单条查询）
     * 
     * @param campBaseModel
     * @return
     */
    @RequestMapping(value = "/manage", method = { RequestMethod.POST })
    public MtOperateResult<CampBaseModel> manageGoods(@RequestBody CampBaseModel campBaseModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始管理店内营销活动信息 campBaseModel=" + campBaseModel);
        }
        MtOperateResult<CampBaseModel> result = null;
        try {
            MtOperateResult<CampBaseModel> innerResult = campBaseIntg.manageCampBase(campBaseModel);
            result = new MtOperateResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("管理店内营销活动信息发生异常" + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * 商品下架及商品发布
     * 
     * @param campBaseModel
     * @return
     */
    @RequestMapping(value = "/status/", method = { RequestMethod.POST })
    public MtOperateResult<CampBaseModel> changeCampBaseStatus(@RequestBody CampBaseModel campBaseModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始执行店内营销活动下架及店内营销活动发布 campBaseModel=" + campBaseModel);
        }
        MtOperateResult<CampBaseModel> result = null;
        try {
            MtOperateResult<CampBaseModel> innerResult = campBaseIntg.changeCampBaseStatus(campBaseModel);
            result = new MtOperateResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("店内营销活动下架及店内营销活动发布过程发生未知异常" + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }
}
