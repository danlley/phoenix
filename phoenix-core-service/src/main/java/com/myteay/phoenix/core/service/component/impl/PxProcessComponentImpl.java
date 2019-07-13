/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.component.impl;

import org.apache.log4j.Logger;

import com.myteay.phoenix.common.service.camp.integration.PxCampPrizeServiceIntg;
import com.myteay.phoenix.common.service.trade.integration.PxTradeServiceIntg;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.process.enums.PxEventTopicEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.camp.CampCashierModel;
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

    /**  */
    private PxTradeServiceIntg     pxTradeServiceIntg;

    /**  */
    private PxCampPrizeServiceIntg pxCampPrizeServiceIntg;

    /**  */
    private PxEventPublishTool     pxEventPublishTool;

    /** 
     * @see com.myteay.phoenix.core.service.component.PxProcessComponent#doProcess(com.myteay.phoenix.core.model.PxGoodsOrderModel)
     */
    @Override
    public MtOperateResult<CampCashierModel> doProcess(PxGoodsOrderModel pxGoodsOrderModel) {

        if (pxGoodsOrderModel == null) {
            logger.warn("订单请求不合法 pxGoodsOrderModel is null");
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
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
        CampCashierModel campCashierModel = pxCampPrizeServiceIntg.doCamp(orderResult.getResult());

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

}
