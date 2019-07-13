/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.listeners;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.MtEvent;
import com.myteay.phoenix.common.service.camp.integration.PxCampPrizeServiceIntg;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.PxGoodsOrderOutModel;
import com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel;

/**
 * 优惠券打标监听器
 * 
 * @author min.weixm
 * @version $Id: PxCampPrizeMarkListener.java, v 0.1 Jul 13, 2019 10:51:07 AM min.weixm Exp $
 */
public class PxCampPrizeMarkListener extends EventListener<Object> {

    /** 优惠券服务客户端 */
    private PxCampPrizeServiceIntg pxCampPrizeServiceIntg;

    /** 
     * @see com.myteay.common.async.event.EventListener#handleEvent(com.myteay.common.async.event.MtEvent)
     */
    @Override
    public Object handleEvent(MtEvent<?> event) {
        // step 1: 合法性检查
        if (event == null) {
            logger.warn("当前对象不可用，无法完成营销活动奖品打标动作 event is null");
            return null;
        }

        if (!(event.getData() instanceof PxGoodsOrderModel)) {
            logger.warn("优惠券打标事件对象不合法 instance must be of PxGoodsOrderModel");
            return null;
        }

        // step 2: 保存优惠券抵扣打标流水
        PxGoodsOrderModel pxGoodsOrderModel = (PxGoodsOrderModel) event.getData();
        List<CampShopPrizeOutModel> prizeList = constructPrizeList(pxGoodsOrderModel);
        if (CollectionUtils.isEmpty(prizeList)) {
            if (logger.isInfoEnabled()) {
                logger.info("当前订单不存在使用优惠券的情况，当前监听器不做优惠券处理 pxGoodsOrderModel=" + pxGoodsOrderModel);
            }
            return null;
        }

        // step 3: 优惠券打标
        MtOperateResult<String> markResult = pxCampPrizeServiceIntg.markPrize(pxGoodsOrderModel);

        // 打标失败则直接返回
        if (!isMarkedSuccess(markResult)) {
            logger.warn("优惠券打标失败 pxGoodsOrderModel=" + pxGoodsOrderModel + " markResult=" + markResult);
            return null;
        }

        // 打标成功，则更新当前优惠券流水状态为“已打标”
        if (logger.isInfoEnabled()) {
            logger.info("优惠券打标成功 pxGoodsOrderModel=" + pxGoodsOrderModel);
        }

        return null;
    }

    /**
     * 构建待抵扣优惠券清单
     * 
     * @param pxGoodsOrderModel
     * @return
     */
    private List<CampShopPrizeOutModel> constructPrizeList(PxGoodsOrderModel pxGoodsOrderModel) {

        if (pxGoodsOrderModel == null || StringUtils.isBlank(pxGoodsOrderModel.getOrderNo())
                || CollectionUtils.isEmpty(pxGoodsOrderModel.getPxGoodsOrderOutModelList())) {
            logger.warn("参数不合法，无法构建可用的待打标优惠券清单 pxGoodsOrderModel=" + pxGoodsOrderModel);
            return null;
        }

        String orderNo = pxGoodsOrderModel.getOrderNo();
        List<PxGoodsOrderOutModel> pxGoodsOrderOutModels = pxGoodsOrderModel.getPxGoodsOrderOutModelList();
        List<CampShopPrizeOutModel> campShopPrizeOutModels = new ArrayList<>();
        CampShopPrizeOutModel campShopPrizeOutModel = null;
        for (PxGoodsOrderOutModel pxGoodsOrderOutModel : pxGoodsOrderOutModels) {
            if (pxGoodsOrderOutModel.getCampShopPrizeOutModel() == null) {
                continue;
            }

            if (StringUtils.isBlank(pxGoodsOrderOutModel.getId())) {
                logger.warn("订单流水号不可用，无法完成优惠券打标的准备工作 pxGoodsOrderOutModel=" + pxGoodsOrderOutModel);
                return null;
            }

            campShopPrizeOutModel = pxGoodsOrderOutModel.getCampShopPrizeOutModel();
            campShopPrizeOutModel.setConsumedOrderNo(orderNo);
            campShopPrizeOutModel.setConsumedOrderId(pxGoodsOrderOutModel.getId());

            campShopPrizeOutModels.add(campShopPrizeOutModel);

        }

        return campShopPrizeOutModels;
    }

    /**
     * 检查优惠券打标动作是否执行成功
     * 
     * @param markResult
     * @return
     */
    private boolean isMarkedSuccess(MtOperateResult<String> markResult) {
        if (markResult == null || markResult.getOperateResult() != MtOperateResultEnum.CAMP_OPERATE_SUCCESS
                || markResult.getOperateExResult() != MtOperateExResultEnum.CAMP_OPERATE_SUCCESS) {
            return false;
        }
        return true;
    }

    /**
     * Setter method for property <tt>pxCampPrizeServiceIntg</tt>.
     * 
     * @param pxCampPrizeServiceIntg value to be assigned to property pxCampPrizeServiceIntg
     */
    public void setPxCampPrizeServiceIntg(PxCampPrizeServiceIntg pxCampPrizeServiceIntg) {
        this.pxCampPrizeServiceIntg = pxCampPrizeServiceIntg;
    }

}
