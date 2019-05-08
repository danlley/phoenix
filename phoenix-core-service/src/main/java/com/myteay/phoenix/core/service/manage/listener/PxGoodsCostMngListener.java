/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.MtEvent;
import com.myteay.common.util.lang.Money;
import com.myteay.common.util.tools.DateUtil;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.camp.CampPrizeRefGoodsModel;
import com.myteay.phoenix.core.model.manage.PxGoodsCostModel;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;
import com.myteay.phoenix.core.service.component.PxGoodsCostCfgCacheComponent;
import com.myteay.phoenix.core.service.manage.component.PxGoodsCostComponent;
import com.myteay.phoenix.core.service.utils.PxCashierUtil;

/**
 * 店铺商品成本管理异步监听器
 * 
 * @author danlley
 * @version $Id: PxGoodsCostMngListener.java, v 0.1 May 8, 2019 12:37:10 AM danlley Exp $
 */
public class PxGoodsCostMngListener extends EventListener<Object> {

    /** 店铺商品经营成本统计管理组件 */
    private PxGoodsCostComponent         pxGoodsCostComponent;

    /** 商品成本缓存 */
    private PxGoodsCostCfgCacheComponent pxGoodsCostCfgCacheComponent;

    /** 
     * @see com.myteay.common.async.event.EventListener#handleEvent(com.myteay.common.async.event.MtEvent)
     */
    @Override
    public Object handleEvent(MtEvent<?> event) {

        if (logger.isInfoEnabled()) {
            logger.info("[商品成本异步处理] event = " + event);
        }

        if (event == null || !(event.getData() instanceof PxGoodsOrderModel)) {
            logger.warn("当前事件模型不合法，无法完成商品成本异步变更动作 event = " + event);
            return null;
        }

        PxGoodsOrderModel pxGoodsOrderModel = (PxGoodsOrderModel) event.getData();
        Map<String, PxGoodsCostModel> pxGoodsCostMap = getPxGoodsCostModelMapByOrderWithCamp(pxGoodsOrderModel);
        if (CollectionUtils.isEmpty(pxGoodsCostMap)) {
            logger.warn("商品成本清单不可用，无法完成商品成本统计动作");
            return null;
        }

        Collection<PxGoodsCostModel> pxGoodsCostModels = pxGoodsCostMap.values();
        for (PxGoodsCostModel pxGoodsCostModel : pxGoodsCostModels) {
            try {
                pxGoodsCostComponent.manageGoodsCostInfo(pxGoodsCostModel);
            } catch (PxManageException e) {
                logger.warn("统计单个商品成本过程出错 pxGoodsCostModel = " + pxGoodsCostModel, e);
            }
        }

        return null;
    }

    /**
     * 构建优惠券抵扣后的商品成本清单
     * 
     * @param pxGoodsOrderModel
     * @return
     */
    private Map<String, PxGoodsCostModel> getPxGoodsCostModelMapByOrderWithCamp(PxGoodsOrderModel pxGoodsOrderModel) {

        Map<String, PxGoodsCostModel> pxGoodsCostMap = getPxGoodsCostModelMapByOrder(pxGoodsOrderModel);

        doDiscount(pxGoodsCostMap, pxGoodsOrderModel);

        return pxGoodsCostMap;
    }

    /**
     * 完成优惠券成本抵扣动作（只允许进行一次优惠券抵扣，并发生在最先发现的商品上）
     * 
     * @param pxGoodsCostMap
     * @param pxGoodsOrderModel
     */
    private void doDiscount(Map<String, PxGoodsCostModel> pxGoodsCostMap, PxGoodsOrderModel pxGoodsOrderModel) {
        List<String> prizeRefGoodsList = getPrizeRefGoodsList(pxGoodsOrderModel);

        if (CollectionUtils.isEmpty(prizeRefGoodsList)) {
            if (logger.isInfoEnabled()) {
                logger.info("优惠券关联的奖品列表不可用，无法完成商品抵扣动作");
            }
            return;
        }

        PxGoodsCostModel pxGoodsCostModel = null;
        for (String goodsId : prizeRefGoodsList) {
            pxGoodsCostModel = pxGoodsCostMap.get(goodsId);
            if (pxGoodsCostModel != null && pxGoodsCostModel.getActrualSellerPrice() != null) {
                pxGoodsCostModel.getActrualSellerPrice().reduce(PxCashierUtil.calculatorCampPriceAmount(pxGoodsOrderModel));
                return;
            }
        }
    }

    /**
     * 获取奖品关联的可抵扣商品列表
     * 
     * @param pxGoodsOrderModel
     * @return
     */
    private List<String> getPrizeRefGoodsList(PxGoodsOrderModel pxGoodsOrderModel) {
        if (pxGoodsOrderModel == null || pxGoodsOrderModel.getCampShopPrizeOutModel() == null
            || pxGoodsOrderModel.getCampShopPrizeOutModel().getCampPrizeModel() == null
            || CollectionUtils.isEmpty(pxGoodsOrderModel.getCampShopPrizeOutModel().getCampPrizeModel().getCampPrizeRefGoodsModels())) {
            return null;
        }

        List<CampPrizeRefGoodsModel> campPrizeRefGoodsModels = pxGoodsOrderModel.getCampShopPrizeOutModel().getCampPrizeModel().getCampPrizeRefGoodsModels();

        List<String> list = new ArrayList<>();
        for (CampPrizeRefGoodsModel campPrizeRefGoodsModel : campPrizeRefGoodsModels) {
            list.add(campPrizeRefGoodsModel.getGoodsId());
        }

        return list;
    }

    /**
     * 构建商品的成本模型清单
     * 
     * @param pxGoodsOrderModel
     * @return
     */
    private Map<String, PxGoodsCostModel> getPxGoodsCostModelMapByOrder(PxGoodsOrderModel pxGoodsOrderModel) {
        if (pxGoodsOrderModel == null || CollectionUtils.isEmpty(pxGoodsOrderModel.getPxGoodsModelList())) {
            return new HashMap<>();
        }

        List<PxGoodsModel> goodsList = pxGoodsOrderModel.getPxGoodsModelList();
        PxGoodsCostModel pxGoodsCostModel = null;
        Map<String, PxGoodsCostModel> map = new HashMap<>();
        for (PxGoodsModel pxGoodsModel : goodsList) {
            pxGoodsCostModel = constructPxGoodsCostModel(pxGoodsModel);
            if (pxGoodsCostModel == null) {
                continue;
            }

            map.put(pxGoodsCostModel.getGoodsId(), pxGoodsCostModel);
        }

        return map;
    }

    /**
     * 构建对应订单中单个商品的成本变更信息
     * 
     * @param pxGoodsModel
     * @return
     */
    private PxGoodsCostModel constructPxGoodsCostModel(PxGoodsModel pxGoodsModel) {

        if (pxGoodsModel == null) {
            return null;
        }

        PxGoodsCostModel pxGoodsCostModel = new PxGoodsCostModel();

        Money actrualCost = pxGoodsCostCfgCacheComponent.queryGoodsCostCfgByGoodsId(pxGoodsModel.getGoodsId());
        int amount = Integer.parseInt(pxGoodsModel.getGoodsSellAmount());
        actrualCost.multiply(amount);
        pxGoodsCostModel.setActrualCost(actrualCost);

        pxGoodsCostModel.setActrualSellerPrice(getGoodsOrderPrice(pxGoodsModel));
        pxGoodsCostModel.setGoodsDesc(pxGoodsModel.getGoodsDesc());
        pxGoodsCostModel.setGoodsId(pxGoodsModel.getGoodsId());
        pxGoodsCostModel.setGoodsTitle(pxGoodsModel.getGoodsTitle());

        pxGoodsCostModel.setReportDate(DateUtil.format(new Date(), DateUtil.shortFormat));
        pxGoodsCostModel.setShopId(pxGoodsModel.getShopId());

        return pxGoodsCostModel;
    }

    /**
     * 获取订单中单个商品的总销售额（单价*数量）
     * 
     * @param pxGoodsModel
     * @return
     */
    private Money getGoodsOrderPrice(PxGoodsModel pxGoodsModel) {
        if (pxGoodsModel == null || StringUtils.isBlank(pxGoodsModel.getGoodsPrice())) {
            new Money();
        }
        Money goodsPrice = new Money(pxGoodsModel.getGoodsPrice());
        int goodsAmount = Integer.parseInt(pxGoodsModel.getGoodsSellAmount());
        goodsPrice.multiply(goodsAmount);

        return goodsPrice;
    }

    /**
     * Setter method for property <tt>pxGoodsCostComponent</tt>.
     * 
     * @param pxGoodsCostComponent value to be assigned to property pxGoodsCostComponent
     */
    public void setPxGoodsCostComponent(PxGoodsCostComponent pxGoodsCostComponent) {
        this.pxGoodsCostComponent = pxGoodsCostComponent;
    }

    /**
     * Setter method for property <tt>pxGoodsCostCfgCacheComponent</tt>.
     * 
     * @param pxGoodsCostCfgCacheComponent value to be assigned to property pxGoodsCostCfgCacheComponent
     */
    public void setPxGoodsCostCfgCacheComponent(PxGoodsCostCfgCacheComponent pxGoodsCostCfgCacheComponent) {
        this.pxGoodsCostCfgCacheComponent = pxGoodsCostCfgCacheComponent;
    }

}
