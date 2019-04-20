/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.checkers;

import static com.myteay.phoenix.core.service.camp.algorithm.handles.SinglePrizeChecker.logger;

import com.myteay.common.util.lang.Money;
import com.myteay.phoenix.common.util.camp.enums.CampPrizeTypeEnum;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.camp.CampPrizeModel;
import com.myteay.phoenix.core.service.camp.algorithm.handles.SinglePrizeChecker;
import com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmModel;
import com.myteay.phoenix.core.service.camp.component.CampShopCacheComponnet;
import com.myteay.phoenix.core.service.utils.PxCashierUtil;

/**
 * 订单总金额抽奖检查器
 * 
 * 注：该检查器采用充血方式实现，防止出现多线程紊乱问题
 * 
 * @author danlley
 * @version $Id: OrderTotalAmountChecker.java, v 0.1 Apr 20, 2019 8:57:00 PM danlley Exp $
 */
public class OrderTotalAmountChecker extends SinglePrizeChecker {

    /** 订单模型 */
    private PxGoodsOrderModel      pxGoodsOrderModel;

    /** 缓存实例 */
    private CampShopCacheComponnet campShopCacheComponnet;

    /** 奖品模型 */
    private CampPrizeModel         campPrizeModel;

    /** 当前执行等级（注：0标识最高级） */
    private static final int       LOCAL_PRIORITY = 0;

    /**
     * 默认构造方法
     * 
     * @param pxGoodsOrderModel
     */
    public OrderTotalAmountChecker(PxGoodsOrderModel pxGoodsOrderModel, CampShopCacheComponnet campShopCacheComponnet) {
        this.pxGoodsOrderModel = pxGoodsOrderModel;

        this.campShopCacheComponnet = campShopCacheComponnet;
        this.setPriority(LOCAL_PRIORITY);
    }

    /** 
     * @see com.myteay.phoenix.core.service.camp.algorithm.handles.SinglePrizeChecker#compareTo(com.myteay.phoenix.core.service.camp.algorithm.handles.SinglePrizeChecker)
     */
    @Override
    public int compareTo(SinglePrizeChecker o) {
        return this.getPriority() - o.getPriority();
    }

    /** 
     * @see com.myteay.phoenix.core.service.camp.algorithm.handles.SinglePrizeChecker#doCheck(com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmModel)
     */
    public boolean doCheck(CampAlgorithmModel campAlgorithmModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始执行前置订单金额检查 pxGoodsOrderModel=" + this.pxGoodsOrderModel);
        }

        this.campPrizeModel = campShopCacheComponnet.queryCampPrizeModelFromCache(campAlgorithmModel.getCampId(), campAlgorithmModel.getPrizeId());
        if (this.campPrizeModel == null) {
            logger.warn("奖品模型不可用，无法完成订单检查 pxGoodsOrderModel=" + this.pxGoodsOrderModel);
            return false;
        }

        CampPrizeTypeEnum prizeType = this.campPrizeModel.getPrizeType();
        if (CampPrizeTypeEnum.CAMP_COMMON_PRIZE == prizeType) {
            return true;
        }

        Money orderPriceAmount = PxCashierUtil.calculateOrderPriceAmount(pxGoodsOrderModel);
        if (CampPrizeTypeEnum.CAMP_PRICE_LIMIT == prizeType && orderPriceAmount.isNotSmallThan(new Money(this.campPrizeModel.getOrderTotalAmount()))) {
            return true;
        }

        return false;
    }

    /**
     * Setter method for property <tt>pxGoodsOrderModel</tt>.
     * 
     * @param pxGoodsOrderModel value to be assigned to property pxGoodsOrderModel
     */
    public void setPxGoodsOrderModel(PxGoodsOrderModel pxGoodsOrderModel) {
        this.pxGoodsOrderModel = pxGoodsOrderModel;
    }

    /**
     * Setter method for property <tt>campPrizeModel</tt>.
     * 
     * @param campPrizeModel value to be assigned to property campPrizeModel
     */
    public void setCampPrizeModel(CampPrizeModel campPrizeModel) {
        this.campPrizeModel = campPrizeModel;
    }

}
