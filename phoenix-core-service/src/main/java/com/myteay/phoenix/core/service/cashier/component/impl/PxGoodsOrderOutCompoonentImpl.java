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
import com.myteay.phoenix.common.util.PxOrderNoUtil;
import com.myteay.phoenix.common.util.camp.enums.CampPrizeOutStatusEnum;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.enums.PxOrderContextKeyEnum;
import com.myteay.phoenix.common.util.enums.PxOrderStatusEnum;
import com.myteay.phoenix.common.util.enums.PxPayTypeEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.PxGoodsOrderOutModel;
import com.myteay.phoenix.core.model.camp.CampBaseModel;
import com.myteay.phoenix.core.model.camp.CampCashierModel;
import com.myteay.phoenix.core.model.camp.CampPrizeModel;
import com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel;
import com.myteay.phoenix.core.model.camp.repository.CampShopPrizeOutRepository;
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
    public static final Logger         logger = Logger.getLogger(PxGoodsOrderOutCompoonentImpl.class);

    /** 订单流水仓储 */
    private PxGoodsOrderOutRepository  pxGoodsOrderOutRepository;

    /** 订单流水仓储 */
    private CampShopCacheComponnet     campShopCacheComponnet;

    /** 抽奖算法组件 */
    private CampAlgorithmComponent     campAlgorithmComponent;

    /** 抽奖流水操作仓储 */
    private CampShopPrizeOutRepository campShopPrizeOutRepository;

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
     * @see com.myteay.phoenix.core.service.cashier.component.PxGoodsOrderOutCompoonent#modifyGoodsOrderOut(java.lang.String, com.myteay.phoenix.common.util.enums.PxPayTypeEnum, com.myteay.phoenix.common.util.enums.PxOrderStatusEnum, com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel)
     */
    @Override
    public MtOperateResult<String> modifyGoodsOrderOut(String orderNo, PxPayTypeEnum pxPayTypeEnum, PxOrderStatusEnum pxOrderStatusEnum,
                                                       CampShopPrizeOutModel campShopPrizeOutModel) {
        //需要放入同一个事务中处理

        // step 1: 变更订单流水记录
        String result = null;
        try {
            result = pxGoodsOrderOutRepository.modifyGoodsOrderOut(orderNo, pxPayTypeEnum, pxOrderStatusEnum);
        } catch (PxManageException e) {
            logger.warn("修改订单流水状态发生异常 orderNo=" + orderNo + " pxPayTypeEnum=" + pxPayTypeEnum + " pxPayTypeEnum=" + pxPayTypeEnum, e);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_ORDER_OUT_OP_ERR);
        }

        // step2: 变更抵扣优惠券的状态(不允许影响出流程处理)
        try {
            campShopPrizeOutRepository.modifyCampShopPrizeOutStatusById(campShopPrizeOutModel);
        } catch (PxManageException e) {
            logger.warn("PxManageException更新优惠券流水失败 campShopPrizeOutModel=" + campShopPrizeOutModel, e);
        } catch (Throwable e) {
            logger.warn("Throwable更新优惠券流水失败 campShopPrizeOutModel=" + campShopPrizeOutModel, e);
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
    public MtOperateResult<CampCashierModel> execute(PxGoodsOrderModel pxGoodsOrderModel) {
        String shopId = (CollectionUtils.isEmpty(pxGoodsOrderModel.getPxGoodsModelList()) ? null : pxGoodsOrderModel.getPxGoodsModelList().get(0).getShopId());

        PxShopModel pxShopModel = campShopCacheComponnet.queryShopModelFromCache(shopId);
        if (pxShopModel == null) {
            logger.warn("保存订单流水时，查询店铺缓存发生异常 pxGoodsOrderModel=" + pxGoodsOrderModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
        }

        pxGoodsOrderModel.setShopName(pxShopModel.getShopName());

        String orderNo = null;
        try {
            orderNo = pxGoodsOrderOutRepository.saveGoodsOrderOut(pxGoodsOrderModel);
        } catch (PxManageException e) {
            logger.warn("保存订单流水发生异常 pxGoodsOrderModel=" + pxGoodsOrderModel, e);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_ORDER_OUT_OP_ERR);
        }

        CampCashierModel campCashierModel = doCamp(pxGoodsOrderModel);
        // 解决抽奖失败的情况
        if (campCashierModel == null) {
            campCashierModel = new CampCashierModel();
            campCashierModel.setOrderNo(orderNo);
            campCashierModel.setUserId(pxGoodsOrderModel.getUserId());
            campCashierModel.setShopName(pxGoodsOrderModel.getShopName());
        }

        return new MtOperateResult<>(campCashierModel);
    }

    /**
     * 完成抽奖动作
     * 
     * @param pxGoodsOrderModel
     */
    private CampCashierModel doCamp(PxGoodsOrderModel pxGoodsOrderModel) {
        if (pxGoodsOrderModel == null || CollectionUtils.isEmpty(pxGoodsOrderModel.getOrderContext())) {
            logger.warn("订单上下文不可用，无法进行抽奖 pxGoodsOrderModel=" + pxGoodsOrderModel);
            return null;
        }

        String campId = pxGoodsOrderModel.getOrderContext().get(PxOrderContextKeyEnum.PX_ORDER_CAMP_ID.getValue());
        if (StringUtils.isBlank(campId)) {
            logger.warn("订单上下文中未找到合法的活动ID，无法参与抽奖活动");
            return null;
        }

        CampAlgorithmResult<CampAlgorithmModel> result = campAlgorithmComponent.execute(campId);
        logger.warn("订单抽奖结束 campId = " + campId + " ，抽奖结果： " + result);

        CampShopPrizeOutModel campShopPrizeOutModel = constructPrizeOutModel(pxGoodsOrderModel, result);
        if (campShopPrizeOutModel == null) {
            logger.warn("奖品流水不可用，无法保存奖品流水信息 campShopPrizeOutModel is null");
            return null;
        }

        String prizeOutId = null;
        try {
            prizeOutId = campShopPrizeOutRepository.saveCampShopPrizeOut(campShopPrizeOutModel);
        } catch (PxManageException e) {
            logger.warn("保存抽奖流水过程发生异常 " + e.getMessage(), e);
            return null;
        }

        CampCashierModel campCashierModel = new CampCashierModel();
        campCashierModel.setCampBaseModel(campShopCacheComponnet.queryCampBaseModelFromCache(result.getResult().getCampId()));
        campCashierModel
            .setCampPrizeModel(campShopCacheComponnet.queryCampPrizeModelFromCache(result.getResult().getCampId(), result.getResult().getPrizeId()));
        campCashierModel.setPrizeOutId(prizeOutId);
        campCashierModel.setOrderNo(campShopPrizeOutModel.getOrderNo());
        campCashierModel.setUserId(campShopPrizeOutModel.getUserId());
        campCashierModel.setShopName(campShopPrizeOutModel.getShopName());
        campCashierModel.setCampSuccess(true);

        return campCashierModel;
    }

    /**
     * 构建奖品流水模型
     * 
     * @param result
     * @return
     */
    private CampShopPrizeOutModel constructPrizeOutModel(PxGoodsOrderModel pxGoodsOrderModel, CampAlgorithmResult<CampAlgorithmModel> result) {
        if (result == null || result.getResult() == null) {
            return null;
        }

        CampAlgorithmModel campAlgorithmModel = result.getResult();
        CampShopPrizeOutModel campShopPrizeOutModel = new CampShopPrizeOutModel();
        campShopPrizeOutModel.setCampId(campAlgorithmModel.getCampId());
        campShopPrizeOutModel.setOrderNo(pxGoodsOrderModel.getOrderNo());
        campShopPrizeOutModel.setPrizeId(campAlgorithmModel.getPrizeId());
        campShopPrizeOutModel.setPrizeOutStatus(CampPrizeOutStatusEnum.CAMP_PRIZE_OUT_GIVEN);
        campShopPrizeOutModel.setShopId(pxGoodsOrderModel.getPxGoodsModelList().get(0).getShopId());
        campShopPrizeOutModel.setShopName(pxGoodsOrderModel.getShopName());
        campShopPrizeOutModel.setUserId(pxGoodsOrderModel.getUserId());

        CampBaseModel campBaseModel = campShopCacheComponnet.queryCampBaseModelFromCache(campAlgorithmModel.getCampId());
        campShopPrizeOutModel.setCampName(campBaseModel.getCampName());
        String campPrizeOutId = null;
        try {
            campPrizeOutId = PxOrderNoUtil.getPrizeOutId();
        } catch (PxManageException e) {
            logger.warn("构建中奖流水过程中，获取流水号发生异常 pxGoodsOrderModel=" + pxGoodsOrderModel + " result=" + result, e);
            return null;
        }
        campShopPrizeOutModel.setCampPrizeOutId(campPrizeOutId);

        CampPrizeModel campPrizeModel = campShopCacheComponnet.queryCampPrizeModelFromCache(campAlgorithmModel.getCampId(), campAlgorithmModel.getPrizeId());
        if (campPrizeModel != null) {
            campShopPrizeOutModel.setPrizeStatus(campPrizeModel.getPrizeStatus());
            campShopPrizeOutModel.setPrizeLevel(campPrizeModel.getPrizeLevel());
            campShopPrizeOutModel.setPrizeName(campPrizeModel.getPrizeName());

            campShopPrizeOutModel.setPrice(campPrizeModel.getPrice());
            campShopPrizeOutModel.setPrizeEffictive(campPrizeModel.getPrizeEffictive());
            campShopPrizeOutModel.setPrizeExpired(campPrizeModel.getPrizeExpired());
        }

        //        campShopPrizeOutModel.setMobileNo(mobileNo);
        return campShopPrizeOutModel;
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

    /**
     * Setter method for property <tt>campShopPrizeOutRepository</tt>.
     * 
     * @param campShopPrizeOutRepository value to be assigned to property campShopPrizeOutRepository
     */
    public void setCampShopPrizeOutRepository(CampShopPrizeOutRepository campShopPrizeOutRepository) {
        this.campShopPrizeOutRepository = campShopPrizeOutRepository;
    }

}
