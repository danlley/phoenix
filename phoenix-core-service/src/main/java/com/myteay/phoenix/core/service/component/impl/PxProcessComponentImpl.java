/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.myteay.common.util.lang.Money;
import com.myteay.phoenix.common.service.camp.integration.CampShopPrizeOutIntg;
import com.myteay.phoenix.common.service.camp.integration.PxCampPrizeServiceIntg;
import com.myteay.phoenix.common.service.trade.integration.PxTradeServiceIntg;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.enums.PxOrderStatusEnum;
import com.myteay.phoenix.common.util.enums.PxPayTypeEnum;
import com.myteay.phoenix.common.util.process.enums.PxEventTopicEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.PxGoodsOrderOutModel;
import com.myteay.phoenix.core.model.camp.CampCashierModel;
import com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel;
import com.myteay.phoenix.core.service.component.PxProcessComponent;
import com.myteay.phoenix.core.service.tools.PxEventPublishTool;

/**
 * 
 * @author min.weixm
 * @version $Id: PxProcessComponentImpl.java, v 0.1 Jul 12, 2019 6:49:09 PM min.weixm Exp $
 */
public class PxProcessComponentImpl implements PxProcessComponent {

    /** 日志 */
    public static final Logger     logger = Logger.getLogger(PxProcessComponentImpl.class);

    /** 交易订单管理客户端 */
    private PxTradeServiceIntg     pxTradeServiceIntg;

    /** 优惠券服务客户端 */
    private PxCampPrizeServiceIntg pxCampPrizeServiceIntg;

    /** 异步事件处理工具类 */
    private PxEventPublishTool     pxEventPublishTool;

    /** 营销管理优惠券 */
    private CampShopPrizeOutIntg   campShopPrizeOutIntg;

    /** 
     * @see com.myteay.phoenix.core.service.component.PxProcessComponent#doPay(java.lang.String, com.myteay.phoenix.common.util.enums.PxPayTypeEnum, com.myteay.phoenix.common.util.enums.PxOrderStatusEnum)
     */
    @Override
    public MtOperateResult<String> doPay(String orderNo, PxPayTypeEnum pxPayTypeEnum, PxOrderStatusEnum pxOrderStatusEnum) {
        if (StringUtils.isBlank(orderNo) || pxPayTypeEnum == null || pxOrderStatusEnum == null) {
            logger.warn("订单支付请求不合法 orderNo=" + orderNo + " pxPayTypeEnum=" + pxPayTypeEnum + " pxOrderStatusEnum=" + pxOrderStatusEnum);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
        }

        // step 1: 完成订单状态变更
        MtOperateResult<String> tradeResult = pxTradeServiceIntg.modifyGoodsOrderOut(orderNo, pxPayTypeEnum, pxOrderStatusEnum);
        if (tradeResult == null || tradeResult.getOperateResult() != MtOperateResultEnum.CAMP_OPERATE_SUCCESS
                || tradeResult.getOperateExResult() != MtOperateExResultEnum.CAMP_OPERATE_SUCCESS) {
            logger.warn("订单支付失败orderNo=" + orderNo + " pxPayTypeEnum=" + pxPayTypeEnum + " pxOrderStatusEnum=" + pxOrderStatusEnum);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_OPERATE_FAILED);
        }

        // step 2: 变更成本计算状态
        pxEventPublishTool.publishEventWithObject(PxEventTopicEnum.PX_GOODS_COST_COVERED, orderNo);

        // step 3: 抵扣优惠券
        pxEventPublishTool.publishEventWithObject(PxEventTopicEnum.PX_CAMP_PRIZE_CONSUME, orderNo);

        return tradeResult;
    }

    /** 
     * @see com.myteay.phoenix.core.service.component.PxProcessComponent#doProcess(com.myteay.phoenix.core.model.PxGoodsOrderModel)
     */
    @Override
    public MtOperateResult<CampCashierModel> doProcess(PxGoodsOrderModel pxGoodsOrderModel) {

        if (pxGoodsOrderModel == null) {
            logger.warn("订单请求不合法 pxGoodsOrderModel is null");
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
        }

        if (isOrderInvalid(pxGoodsOrderModel)) {
            logger.warn("订单创建过程中，事前检查未通过 pxGoodsOrderModel=" + pxGoodsOrderModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_OPERATE_FAILED);
        }

        // step 1: 创建收银台订单
        MtOperateResult<PxGoodsOrderModel> orderResult = null;
        try {
            orderResult = pxTradeServiceIntg.createGoodsOrderOut(pxGoodsOrderModel);
        } catch (Throwable e) {
            logger.warn("创建订单过程发生异常 pxGoodsOrderModel=" + pxGoodsOrderModel, e);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_OPERATE_FAILED);
        }

        if (orderResult == null) {
            logger.warn("创建订单过程发生未知异常 pxGoodsOrderModel=" + pxGoodsOrderModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_OPERATE_FAILED);
        }

        // step 2: 同步进行收银台抽奖出券动作
        CampCashierModel campCashierModel = null;
        try {
            campCashierModel = pxCampPrizeServiceIntg.doCamp(orderResult.getResult());
        } catch (Throwable e) {
            logger.warn("当前订单抽奖失败 orderResult=" + orderResult, e);
        }

        if (campCashierModel == null) {
            campCashierModel = new CampCashierModel();
            campCashierModel.setOrderNo(orderResult.getResult().getOrderNo());
            campCashierModel.setUserId(orderResult.getResult().getUserId());
        }

        if (logger.isInfoEnabled()) {
            logger.info("针对指定订单抽奖 orderResult=" + orderResult + " campCashierModel=" + campCashierModel);
        }

        // step 3: 记录成本信息
        pxEventPublishTool.publishEventWithObject(PxEventTopicEnum.PX_GOODS_COST_MARAK, orderResult.getResult());

        // step 4: 对需要进行抵扣的优惠券进行打标
        pxEventPublishTool.publishEventWithObject(PxEventTopicEnum.PX_CAMP_PRIZE_MARK, orderResult.getResult());

        return new MtOperateResult<>(campCashierModel);
    }

    /**
     * 订单创建前的检查工作
     * 
     * @param pxGoodsOrderModel
     * @return
     */
    private boolean isOrderInvalid(PxGoodsOrderModel pxGoodsOrderModel) {

        // step 1: 检查优惠券有效性
        List<CampShopPrizeOutModel> prizeList = constructPrizeList(pxGoodsOrderModel);
        if (!CollectionUtils.isEmpty(prizeList) && isPrizeListInvalid(prizeList)) {
            return true;
        }

        // step 2: 检查订单金额有效性

        return false;
    }

    /**
     * 检查奖品列表是否可用
     * 
     * @param prizeList
     * @return
     */
    private boolean isPrizeListInvalid(List<CampShopPrizeOutModel> prizeList) {
        // 优惠券列表为空，则优惠券检查通过
        if (CollectionUtils.isEmpty(prizeList)) {
            return false;
        }

        for (CampShopPrizeOutModel campShopPrizeOutModel : prizeList) {
            if (campShopPrizeOutModel == null) {
                continue;
            }

            if (isSinglePrizeInvalid(campShopPrizeOutModel)) {
                return true;
            }

        }

        return false;
    }

    /**
     * 检查单个优惠券是否可用
     * 
     * @param campShopPrizeOutModel
     * @return
     */
    private boolean isSinglePrizeInvalid(CampShopPrizeOutModel campShopPrizeOutModel) {

        if (campShopPrizeOutModel == null) {
            return false;
        }

        MtOperateResult<CampShopPrizeOutModel> result = campShopPrizeOutIntg.queryShopPrizeOutById(campShopPrizeOutModel.getCampPrizeOutId());

        //优惠券查询失败，则检查不通过
        if (result.getOperateResult() != MtOperateResultEnum.CAMP_OPERATE_SUCCESS || result.getOperateExResult() != MtOperateExResultEnum.CAMP_OPERATE_SUCCESS
                || result.getResult() == null) {
            return true;
        }

        CampShopPrizeOutModel originalPrize = result.getResult();

        //检查优惠券金额合法性
        Money currentPrizePrice = new Money(campShopPrizeOutModel.getPrice());
        Money originalPrizePrice = new Money(originalPrize.getPrice());
        if (!originalPrizePrice.isEqual(currentPrizePrice)) {
            return true;
        }

        return false;
    }

    /**
     * 构建待抵扣优惠券清单
     * 
     * @param pxGoodsOrderModel
     * @return
     */
    private List<CampShopPrizeOutModel> constructPrizeList(PxGoodsOrderModel pxGoodsOrderModel) {

        if (pxGoodsOrderModel == null || CollectionUtils.isEmpty(pxGoodsOrderModel.getPxGoodsOrderOutModelList())) {
            logger.warn("参数不合法，无法构建可用的待打标优惠券清单 pxGoodsOrderModel=" + pxGoodsOrderModel);
            return null;
        }

        List<PxGoodsOrderOutModel> pxGoodsOrderOutModels = pxGoodsOrderModel.getPxGoodsOrderOutModelList();
        List<CampShopPrizeOutModel> campShopPrizeOutModels = new ArrayList<>();
        for (PxGoodsOrderOutModel pxGoodsOrderOutModel : pxGoodsOrderOutModels) {
            if (pxGoodsOrderOutModel.getCampShopPrizeOutModel() == null) {
                continue;
            }
            campShopPrizeOutModels.add(pxGoodsOrderOutModel.getCampShopPrizeOutModel());
        }

        return campShopPrizeOutModels;
    }

    /**
     * Setter method for property <tt>pxTradeServiceIntg</tt>.
     * 
     * @param pxTradeServiceIntg value to be assigned to property pxTradeServiceIntg
     */
    public void setPxTradeServiceIntg(PxTradeServiceIntg pxTradeServiceIntg) {
        this.pxTradeServiceIntg = pxTradeServiceIntg;
    }

    /**
     * Setter method for property <tt>pxCampPrizeServiceIntg</tt>.
     * 
     * @param pxCampPrizeServiceIntg value to be assigned to property pxCampPrizeServiceIntg
     */
    public void setPxCampPrizeServiceIntg(PxCampPrizeServiceIntg pxCampPrizeServiceIntg) {
        this.pxCampPrizeServiceIntg = pxCampPrizeServiceIntg;
    }

    /**
     * Setter method for property <tt>pxEventPublishTool</tt>.
     * 
     * @param pxEventPublishTool value to be assigned to property pxEventPublishTool
     */
    public void setPxEventPublishTool(PxEventPublishTool pxEventPublishTool) {
        this.pxEventPublishTool = pxEventPublishTool;
    }

    /**
     * Setter method for property <tt>campShopPrizeOutIntg</tt>.
     * 
     * @param campShopPrizeOutIntg value to be assigned to property campShopPrizeOutIntg
     */
    public void setCampShopPrizeOutIntg(CampShopPrizeOutIntg campShopPrizeOutIntg) {
        this.campShopPrizeOutIntg = campShopPrizeOutIntg;
    }

}
