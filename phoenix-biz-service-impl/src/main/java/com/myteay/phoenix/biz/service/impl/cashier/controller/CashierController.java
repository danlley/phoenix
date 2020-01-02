/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.cashier.controller;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.common.util.tools.DateUtil;
import com.myteay.phoenix.biz.service.impl.MtServiceResult;
import com.myteay.phoenix.biz.service.impl.PxGoodsOrderContextUtil;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.PxOrderNoUtil;
import com.myteay.phoenix.common.util.QRCodeUtil;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.enums.PxOrderStatusEnum;
import com.myteay.phoenix.common.util.enums.PxPayTypeEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.PxGoodsOrderOutModel;
import com.myteay.phoenix.core.model.PxOrderPayedResultModel;
import com.myteay.phoenix.core.model.camp.CampCashierModel;
import com.myteay.phoenix.core.service.component.PxProcessComponent;

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
    private static final Logger               logger         = LoggerFactory.getLogger(LoggerNames.PX_CASHIER_DEFAULT);

    /** 摘要日志 */
    private static final Logger               logger_cashier = LoggerFactory.getLogger(LoggerNames.PX_CASHIER_DIGEST);

    private static final Map<String, Integer> SHOP_TRADE_NO  = Collections.synchronizedMap(new HashMap<String, Integer>());

    /** 当前订单编号 */
    private static int                        currentNo      = 1;

    /** 当前日期 */
    private static String                     currentDate;

    /** 收银台主流程处理组件 */
    @Autowired
    private PxProcessComponent                pxProcessComponent;

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
    public MtServiceResult<String> modifyGoodsOrderOut(String orderNo, PxPayTypeEnum pxPayTypeEnum, PxOrderStatusEnum pxOrderStatusEnum) {

        if (logger_cashier.isInfoEnabled()) {
            logger_cashier.info("[REQCONSUME," + orderNo + "," + pxPayTypeEnum + "," + pxOrderStatusEnum + "]");
        }

        MtOperateResult<String> innerResult = pxProcessComponent.doPay(orderNo, pxPayTypeEnum, pxOrderStatusEnum);
        MtServiceResult<String> result = new MtServiceResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
        result.setResult(innerResult.getResult());

        if (logger_cashier.isInfoEnabled()) {
            logger_cashier.info("[REQCONSUMERES," + orderNo + "," + pxPayTypeEnum + "," + pxOrderStatusEnum + "," + result.getOperateResult() + ","
                                + result.getOperateExResult() + "]");
        }

        return result;
    }

    /**
     * 创建订单并完成付款
     * 
     * @param pxGoodsOrderModel
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/order/create/payed/", method = { RequestMethod.POST })
    public MtServiceResult<PxOrderPayedResultModel> createGoodsOrderOutPayed(@RequestBody PxGoodsOrderModel pxGoodsOrderModel, HttpServletRequest request,
                                                                             HttpServletResponse response) {

        if (logger.isInfoEnabled()) {
            logger.info("收到订单请求 pxGoodsOrderModel=" + pxGoodsOrderModel);
        }

        //step 1: 生成订单号
        pxGoodsOrderModel.setOrderNo(PxOrderNoUtil.getUUID());
        MtServiceResult<CampCashierModel> result = new MtServiceResult<>();

        //step 2: 填充上下文
        PxGoodsOrderContextUtil.fillOrderContext(pxGoodsOrderModel, request);

        // step 3: 异常参数校验
        if (StringUtils.isBlank(pxGoodsOrderModel.getUserId()) || StringUtils.isBlank(pxGoodsOrderModel.getShopName())
            || StringUtils.isBlank(pxGoodsOrderModel.getOrderNo()) || CollectionUtils.isEmpty(pxGoodsOrderModel.getPxGoodsOrderOutModelList())) {
            logger_cashier
                .warn("[REQGENERR," + pxGoodsOrderModel.getUserId() + "," + pxGoodsOrderModel.getShopName() + "," + pxGoodsOrderModel.getOrderNo() + "]");
            return new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
        }

        // step 4: 记录订单请求的摘要日志
        if (logger_cashier.isInfoEnabled()) {
            logger_cashier.info("[REQGEN," + pxGoodsOrderModel.getUserId() + "," + pxGoodsOrderModel.getShopName() + "," + pxGoodsOrderModel.getOrderNo() + ","
                                + pxGoodsOrderModel.getPxGoodsOrderOutModelList().size() + "]");
        }

        // step 5: 执行订单流水落地业务
        MtOperateResult<CampCashierModel> innerResult = pxProcessComponent.doProcess(pxGoodsOrderModel);

        // step 6: 记录订单流水落地结果
        if (logger_cashier.isInfoEnabled()) {
            logger_cashier.info("[RESULTGEN," + pxGoodsOrderModel.getUserId() + "," + pxGoodsOrderModel.getShopName() + "," + pxGoodsOrderModel.getOrderNo()
                                + "," + innerResult.getOperateResult() + "," + innerResult.getOperateExResult() + "]");
        }

        String tradeNo = pxGoodsOrderModel.getOrderNo();

        if (logger_cashier.isInfoEnabled()) {
            logger_cashier.info("[REQCONSUME," + tradeNo + "," + pxGoodsOrderModel.getPayType() + "," + PxOrderStatusEnum.PX_ORDER_PAYED + "]");
        }

        MtOperateResult<String> innerPayedResult = pxProcessComponent.doPay(tradeNo, pxGoodsOrderModel.getPayType(), PxOrderStatusEnum.PX_ORDER_PAYED);
        result.setResult(innerResult.getResult());

        if (logger_cashier.isInfoEnabled()) {
            logger_cashier.info("[REQCONSUMERES," + tradeNo + "," + pxGoodsOrderModel.getPayType() + "," + PxOrderStatusEnum.PX_ORDER_PAYED + ","
                                + result.getOperateResult() + "," + result.getOperateExResult() + "]");
        }

        List<PxGoodsOrderOutModel> pxGoodsOrderOutModelList = pxGoodsOrderModel.getPxGoodsOrderOutModelList();
        String shopId = pxGoodsOrderModel.getShopId();
        String shopName = pxGoodsOrderModel.getShopName();
        String orderNo = getOrderNoByShop(shopId);

        CampCashierModel campCashierModel = innerResult.getResult();
        PxOrderPayedResultModel pxOrderPayedResultModel = new PxOrderPayedResultModel();
        pxOrderPayedResultModel.setCampCashierModel(campCashierModel);
        pxOrderPayedResultModel.setOrderNo(orderNo);
        pxOrderPayedResultModel.setOrderTime(new Date());
        pxOrderPayedResultModel.setPxGoodsOrderOutModelList(pxGoodsOrderOutModelList);
        pxOrderPayedResultModel.setShopId(shopId);
        pxOrderPayedResultModel.setShopLogo("logo.jpg");
        pxOrderPayedResultModel.setShopName(shopName);
        pxOrderPayedResultModel.setTradeNo(tradeNo);
        pxOrderPayedResultModel.setShopTel("15294395456");

        MtServiceResult<PxOrderPayedResultModel> operateResult = new MtServiceResult<>(innerPayedResult.getOperateResult(),
            innerPayedResult.getOperateExResult());
        operateResult.setResult(pxOrderPayedResultModel);

        return operateResult;
    }

    /**
     * 获取店铺当前序号
     * 
     * @param shopId
     * @return
     */
    private String getOrderNoByShop(String shopId) {
        Integer number = SHOP_TRADE_NO.get(shopId);
        if (number == null || number.intValue() <= 0) {
            SHOP_TRADE_NO.put(shopId, 1);
            number = SHOP_TRADE_NO.get(shopId);
        }

        String currOrderNo = number.toString();
        SHOP_TRADE_NO.put(shopId, (number.intValue()) + 1);

        return currOrderNo;
    }

    /**
     * 获取订单编号
     * 
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/list/shop/order/number/ii/{shopId}", method = { RequestMethod.GET })
    public MtServiceResult<String> getOrderNoByShopId(@PathVariable String shopId) {
        String orderNum = getOrderNoByShop(shopId);
        return new MtServiceResult<>(orderNum);
    }

    /**
     * 创建订单流水v2.0
     * 
     * @param pxGoodsOrderModel
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/order/ii/", method = { RequestMethod.POST })
    public MtServiceResult<CampCashierModel> createGoodsOrderOutII(@RequestBody PxGoodsOrderModel pxGoodsOrderModel, HttpServletRequest request,
                                                                   HttpServletResponse response) {

        if (logger.isInfoEnabled()) {
            logger.info("收到订单请求 pxGoodsOrderModel=" + pxGoodsOrderModel);
        }

        //step 1: 生成订单号
        pxGoodsOrderModel.setOrderNo(PxOrderNoUtil.getUUID());
        MtServiceResult<CampCashierModel> result = new MtServiceResult<>();

        //step 2: 填充上下文
        PxGoodsOrderContextUtil.fillOrderContext(pxGoodsOrderModel, request);

        // step 3: 异常参数校验
        if (StringUtils.isBlank(pxGoodsOrderModel.getUserId()) || StringUtils.isBlank(pxGoodsOrderModel.getShopName())
            || StringUtils.isBlank(pxGoodsOrderModel.getOrderNo()) || CollectionUtils.isEmpty(pxGoodsOrderModel.getPxGoodsModelList())) {
            logger_cashier
                .warn("[REQGENERR," + pxGoodsOrderModel.getUserId() + "," + pxGoodsOrderModel.getShopName() + "," + pxGoodsOrderModel.getOrderNo() + "]");
            return new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
        }

        // step 4: 记录订单请求的摘要日志
        if (logger_cashier.isInfoEnabled()) {
            logger_cashier.info("[REQGEN," + pxGoodsOrderModel.getUserId() + "," + pxGoodsOrderModel.getShopName() + "," + pxGoodsOrderModel.getOrderNo() + ","
                                + pxGoodsOrderModel.getPxGoodsModelList().size() + "]");
        }

        // step 5: 执行订单流水落地业务
        MtOperateResult<CampCashierModel> innerResult = pxProcessComponent.doProcess(pxGoodsOrderModel);

        // step 6: 记录订单流水落地结果
        if (logger_cashier.isInfoEnabled()) {
            logger_cashier.info("[RESULTGEN," + pxGoodsOrderModel.getUserId() + "," + pxGoodsOrderModel.getShopName() + "," + pxGoodsOrderModel.getOrderNo()
                                + "," + innerResult.getOperateResult() + "," + innerResult.getOperateExResult() + "]");
        }

        result.setResult(innerResult.getResult());
        return result;
    }

    /**
     * 生成小票中的二维码
     * 
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/list/shop/order/camp/image/{prizeOutId}", method = { RequestMethod.GET })
    public void queryOrderNum(@PathVariable String prizeOutId, HttpServletRequest request, HttpServletResponse response) {
        try {
            QRCodeUtil.encodeWithoutPath(prizeOutId, null, true, response);
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
        } catch (Exception e) {
            logger.warn("生成二维码失败，当前处理失败 " + e.getMessage(), e);
        }
    }

    /**
     * 生成小票中的订单编号（仅支持单一店铺，多店铺需要重新设计）
     * 
     * @return
     */
    @RequestMapping(value = "/list/shop/order/number", method = { RequestMethod.GET })
    public MtServiceResult<String> queryOrderNum() {
        MtServiceResult<String> result = new MtServiceResult<>();

        String reportDate = DateUtil.format(new Date(), DateUtil.shortFormat);
        if (!StringUtils.equals(currentDate, reportDate)) {
            currentDate = reportDate;
            currentNo = 1;
        }

        currentNo++;
        result.setResult(currentNo + "");

        return result;
    }

    /**
     * Setter method for property <tt>pxProcessComponent</tt>.
     * 
     * @param pxProcessComponent value to be assigned to property pxProcessComponent
     */
    public void setPxProcessComponent(PxProcessComponent pxProcessComponent) {
        this.pxProcessComponent = pxProcessComponent;
    }

}
