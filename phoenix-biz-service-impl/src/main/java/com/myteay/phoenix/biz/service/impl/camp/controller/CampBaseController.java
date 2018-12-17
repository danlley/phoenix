/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.camp.controller;

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
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampBaseModel;
import com.myteay.phoenix.core.service.camp.component.CampShopBaseComponent;
import com.myteay.phoenix.core.service.camp.component.CampShopBaseStatusComponent;
import com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent;

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
    public static final Logger          logger = Logger.getLogger(CampBaseController.class);

    /** 店内营销活动基本信息管理组件 */
    @Autowired
    private CampShopBaseComponent       campShopBaseComponent;

    /** 店内营销活动状态管理 */
    @Autowired
    private CampShopBaseStatusComponent campShopBaseStatusComponent;

    /** 后台一般性简单业务管理组件 */
    @Autowired
    private PxCommonManageComponent     pxCommonManageComponent;

    /**
     * 通过店铺ID查询店铺下的所有店内营销活动
     * 
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/list/shop/{shopId}", method = { RequestMethod.GET })
    public MtServiceResult<List<CampBaseModel>> queryGoodsByShopId(@PathVariable String shopId) {
        MtServiceResult<List<CampBaseModel>> result = null;

        MtOperateResult<List<CampBaseModel>> componentResult = null;
        try {
            componentResult = pxCommonManageComponent.queryCampBaseListByShopId(shopId);
            result = new MtServiceResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询所有店内营销活动信息发生未知异常 " + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
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
    public MtServiceResult<CampBaseModel> manageGoods(@RequestBody CampBaseModel campBaseModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始管理店内营销活动信息 campBaseModel=" + campBaseModel);
        }
        MtServiceResult<CampBaseModel> result = null;
        try {
            MtOperateResult<CampBaseModel> innerResult = campShopBaseComponent.manageCampBase(campBaseModel);
            result = new MtServiceResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("管理店内营销活动信息发生异常" + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
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
    public MtServiceResult<CampBaseModel> changeCampBaseStatus(@RequestBody CampBaseModel campBaseModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始执行店内营销活动下架及店内营销活动发布 campBaseModel=" + campBaseModel);
        }
        MtServiceResult<CampBaseModel> result = null;
        try {
            MtOperateResult<CampBaseModel> innerResult = campShopBaseStatusComponent.changeCampStatus(campBaseModel);
            result = new MtServiceResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (PxManageException e) {
            logger.warn("店内营销活动下架及店内营销活动发布过程发生业务异常" + e.getMessage(), e);
            result = new MtServiceResult<>(e.getResultEnum(), e.getExResultEnum());
        } catch (Exception e) {
            logger.warn("店内营销活动下架及店内营销活动发布过程发生未知异常" + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }
}
