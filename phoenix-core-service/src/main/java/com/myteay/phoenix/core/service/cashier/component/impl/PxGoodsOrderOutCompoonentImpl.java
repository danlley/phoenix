/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.cashier.component.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.myteay.common.util.exception.MtException;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.enums.PxOrderContextKeyEnum;
import com.myteay.phoenix.common.util.enums.PxOrderStatusEnum;
import com.myteay.phoenix.common.util.enums.PxPayTypeEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.PxGoodsOrderOutModel;
import com.myteay.phoenix.core.model.manage.PxShopModel;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsOrderOutRepository;
import com.myteay.phoenix.core.service.camp.algorithm.CampAlgorithmComponent;
import com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmModel;
import com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmResult;
import com.myteay.phoenix.core.service.camp.component.CampShopCacheComponnet;
import com.myteay.phoenix.core.service.cashier.component.PxGoodsOrderOutCompoonent;

/**
 * 订单流水操作组件
 * 
 * @author danlley
 * @version $Id: PxGoodsOrderOutCompoonentImpl.java, v 0.1 Feb 27, 2019 2:01:18 PM danlley Exp $
 */
public class PxGoodsOrderOutCompoonentImpl implements PxGoodsOrderOutCompoonent {

    /** 日志 */
    public static final Logger        logger = Logger.getLogger(PxGoodsOrderOutCompoonentImpl.class);

    /** 订单流水仓储 */
    private PxGoodsOrderOutRepository pxGoodsOrderOutRepository;

    /** 订单流水仓储 */
    private CampShopCacheComponnet    campShopCacheComponnet;

    /** 抽奖算法组件 */
    private CampAlgorithmComponent    campAlgorithmComponent;

    /** 
     * @see com.myteay.phoenix.core.service.cashier.component.PxGoodsOrderOutCompoonent#deleteExpiredOrder(com.myteay.phoenix.core.model.PxGoodsOrderModel)
     */
    @Override
    public MtOperateResult<String> deleteExpiredOrder(PxGoodsOrderModel pxGoodsOrderModel) {

        if (pxGoodsOrderModel == null || CollectionUtils.isEmpty(pxGoodsOrderModel.getPxGoodsOrderOutModelList())) {
            logger.warn("订单模型不合法，无法完成清理废单的动作 pxGoodsOrderModel=" + pxGoodsOrderModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_UN_SUPPORT);
        }

        if (logger.isInfoEnabled()) {
            logger.info("开始清理废单 pxGoodsOrderModel=" + pxGoodsOrderModel);
        }

        List<PxGoodsOrderOutModel> orderOutModelList = pxGoodsOrderModel.getPxGoodsOrderOutModelList();

        try {
            for (PxGoodsOrderOutModel pxGoodsOrderOutModel : orderOutModelList) {
                pxGoodsOrderOutRepository.deleteExpiredOrder(pxGoodsOrderOutModel);
            }
        } catch (MtException e) {
            logger.warn("废单清理失败： " + e.getMessage(), e);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_OPERATE_FAILED);
        }

        return new MtOperateResult<String>();
    }

    /** 
     * @see com.myteay.phoenix.core.service.cashier.component.PxGoodsOrderOutCompoonent#modifyGoodsOrderOut(java.lang.String, com.myteay.phoenix.common.util.enums.PxPayTypeEnum, com.myteay.phoenix.common.util.enums.PxOrderStatusEnum)
     */
    @Override
    public MtOperateResult<String> modifyGoodsOrderOut(String orderNo, PxPayTypeEnum pxPayTypeEnum, PxOrderStatusEnum pxOrderStatusEnum) {
        String result = null;
        try {
            result = pxGoodsOrderOutRepository.modifyGoodsOrderOut(orderNo, pxPayTypeEnum, pxOrderStatusEnum);
        } catch (PxManageException e) {
            logger.warn("修改订单流水状态发生异常 orderNo=" + orderNo + " pxPayTypeEnum=" + pxPayTypeEnum + " pxPayTypeEnum=" + pxPayTypeEnum, e);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_ORDER_OUT_OP_ERR);
        }

        return new MtOperateResult<>(result);
    }

    /** 
     * @see com.myteay.phoenix.core.service.cashier.component.PxGoodsOrderOutCompoonent#findAllShopExpiredOrder()
     */
    @Override
    public MtOperateResult<PxGoodsOrderModel> findAllShopExpiredOrder() {
        PxGoodsOrderModel pxGoodsOrderModel = null;
        try {
            pxGoodsOrderModel = pxGoodsOrderOutRepository.findAllShopExpiredOrder();
        } catch (Exception e) {
            logger.warn("查询店内消费的过期单据失败 " + e.getMessage(), e);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_BASE_DEL_STATUS_ERR);
        }
        return new MtOperateResult<>(pxGoodsOrderModel);
    }

    /** 
     * @see com.myteay.phoenix.core.service.cashier.component.PxGoodsOrderOutCompoonent#execute(com.myteay.phoenix.core.model.PxGoodsOrderModel)
     */
    @Override
    public MtOperateResult<String> execute(PxGoodsOrderModel pxGoodsOrderModel) {

        PxShopModel pxShopModel = campShopCacheComponnet.queryShopModelFromCache(pxGoodsOrderModel.getPxGoodsModelList().get(0).getShopId());
        pxGoodsOrderModel.setShopName(pxShopModel.getShopName());

        String orderNo = null;
        try {
            orderNo = pxGoodsOrderOutRepository.saveGoodsOrderOut(pxGoodsOrderModel);
        } catch (PxManageException e) {
            logger.warn("保存订单流水发生异常 pxGoodsOrderModel=" + pxGoodsOrderModel, e);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_ORDER_OUT_OP_ERR);
        }

        doCamp(pxGoodsOrderModel);

        return new MtOperateResult<>(orderNo);
    }

    /**
     * 完成抽奖动作
     * 
     * @param pxGoodsOrderModel
     */
    private void doCamp(PxGoodsOrderModel pxGoodsOrderModel) {
        if (pxGoodsOrderModel == null || CollectionUtils.isEmpty(pxGoodsOrderModel.getOrderContext())) {
            logger.warn("订单上下文不可用，无法进行抽奖 pxGoodsOrderModel=" + pxGoodsOrderModel);
            return;
        }

        String campId = pxGoodsOrderModel.getOrderContext().get(PxOrderContextKeyEnum.PX_ORDER_CAMP_ID.getValue());
        if (StringUtils.isBlank(campId)) {
            logger.warn("订单上下文中未找到合法的活动ID，无法参与抽奖活动");
            return;
        }

        CampAlgorithmResult<CampAlgorithmModel> result = campAlgorithmComponent.execute(campId);
        logger.warn("订单抽奖结束 campId = " + campId + " ，抽奖结果： " + result);
    }

    /**
     * Setter method for property <tt>campAlgorithmComponent</tt>.
     * 
     * @param campAlgorithmComponent value to be assigned to property campAlgorithmComponent
     */
    public void setCampAlgorithmComponent(CampAlgorithmComponent campAlgorithmComponent) {
        this.campAlgorithmComponent = campAlgorithmComponent;
    }

    /**
     * Setter method for property <tt>pxGoodsOrderOutRepository</tt>.
     * 
     * @param pxGoodsOrderOutRepository value to be assigned to property pxGoodsOrderOutRepository
     */
    public void setPxGoodsOrderOutRepository(PxGoodsOrderOutRepository pxGoodsOrderOutRepository) {
        this.pxGoodsOrderOutRepository = pxGoodsOrderOutRepository;
    }

    /**
     * Setter method for property <tt>campShopCacheComponnet</tt>.
     * 
     * @param campShopCacheComponnet value to be assigned to property campShopCacheComponnet
     */
    public void setCampShopCacheComponnet(CampShopCacheComponnet campShopCacheComponnet) {
        this.campShopCacheComponnet = campShopCacheComponnet;
    }

}
