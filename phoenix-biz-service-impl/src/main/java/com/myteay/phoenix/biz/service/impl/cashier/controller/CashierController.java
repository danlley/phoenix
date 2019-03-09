/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.cashier.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.phoenix.biz.service.impl.MtServiceResult;
import com.myteay.phoenix.biz.service.impl.PxGoodsOrderContextUtil;
import com.myteay.phoenix.common.util.PxOrderNoUtil;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.enums.PxOrderStatusEnum;
import com.myteay.phoenix.common.util.enums.PxPayTypeEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.camp.CampBaseModel;
import com.myteay.phoenix.core.service.camp.component.CampShopBaseComponent;
import com.myteay.phoenix.core.service.camp.component.CampShopBaseStatusComponent;
import com.myteay.phoenix.core.service.cashier.component.PxGoodsOrderOutCompoonent;
import com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent;

/**
 * 收银台商品查询及点餐入口
 * 
 * @author danlley
 * @version $Id: CashierController.java, v 0.1 Dec 17, 2018 12:37:03 AM danlley Exp $
 */
@RestController
@RequestMapping(value = "myteay/api/phoenix/cashier/manage/base")
public class CashierController {

    /** 日志 */
    public static final Logger          logger    = Logger.getLogger(CashierController.class);

    /** 店内营销活动基本信息管理组件 */
    @Autowired
    private CampShopBaseComponent       campShopBaseComponent;

    /** 订单流水操作组件 */
    @Autowired
    private PxGoodsOrderOutCompoonent   pxGoodsOrderOutCompoonent;

    /** 店内营销活动状态管理 */
    @Autowired
    private CampShopBaseStatusComponent campShopBaseStatusComponent;

    /** 后台一般性简单业务管理组件 */
    @Autowired
    private PxCommonManageComponent     pxCommonManageComponent;

    /** 当前订单编号 */
    private static int                  currentNo = 1;

    /**
     * 修改订单状态
     * 
     * @param orderNo
     * @param pxPayTypeEnum
     * @param pxOrderStatusEnum
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/order/change", method = { RequestMethod.POST })
    public MtServiceResult<String> modifyGoodsOrderOut(String orderNo, PxPayTypeEnum pxPayTypeEnum, PxOrderStatusEnum pxOrderStatusEnum,
                                                       HttpServletRequest request, HttpServletResponse response) {

        if (logger.isInfoEnabled()) {
            logger.info("收到订单请求 orderNo=" + orderNo + " pxPayTypeEnum=" + pxPayTypeEnum + " pxOrderStatusEnum=" + pxOrderStatusEnum);
        }

        MtOperateResult<String> innerResult = pxGoodsOrderOutCompoonent.modifyGoodsOrderOut(orderNo, pxPayTypeEnum, pxOrderStatusEnum);
        MtServiceResult<String> result = new MtServiceResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
        result.setResult(innerResult.getResult());

        return result;
    }

    /**
     * 
     * @param pxGoodsOrderModel
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/order/", method = { RequestMethod.POST })
    public MtServiceResult<String> manageGoodsOrderOut(@RequestBody PxGoodsOrderModel pxGoodsOrderModel, HttpServletRequest request,
                                                       HttpServletResponse response) {
        MtServiceResult<String> result = new MtServiceResult<>();

        //step 1: 填充上下文
        PxGoodsOrderContextUtil.fillOrderContext(pxGoodsOrderModel, request);

        //step 2: 生成订单号
        pxGoodsOrderModel.setOrderNo(PxOrderNoUtil.getUUID());

        if (logger.isInfoEnabled()) {
            logger.info("收到订单请求 pxGoodsOrderModel=" + pxGoodsOrderModel);
        }

        MtOperateResult<String> innerResult = pxGoodsOrderOutCompoonent.execute(pxGoodsOrderModel);
        result.setResult(innerResult.getResult());

        return result;
    }

    /**
     * 通过店铺ID查询店铺下的所有店内营销活动
     * 
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/list/shop/order/number", method = { RequestMethod.GET })
    public MtServiceResult<String> queryGoodsByShopId() {
        MtServiceResult<String> result = new MtServiceResult<>();

        try {
            currentNo++;
            result.setResult(currentNo + "");
        } catch (Exception e) {
            logger.warn("查询所有店内营销活动信息发生未知异常 " + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

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