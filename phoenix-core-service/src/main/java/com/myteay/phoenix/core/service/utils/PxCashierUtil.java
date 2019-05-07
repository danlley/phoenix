/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.utils;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.myteay.common.util.lang.Money;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;

/**
 * 收银台工具类
 * 
 * @author danlley
 * @version $Id: PxCashierUtil.java, v 0.1 Apr 20, 2019 5:46:15 PM danlley Exp $
 */
public class PxCashierUtil {

    /**
     * 计算订单实付金额
     * 
     * @param pxGoodsOrderModel
     * @return
     */
    public static Money calculateOrderPriceAmount(PxGoodsOrderModel pxGoodsOrderModel) {

        //计算未优惠前商品订单价格
        Money priceAmountWithoutCamp = calculateOrderPriceAmountWithoutCamp(pxGoodsOrderModel);
        Money campPriceAmount = calculatorCampPriceAmount(pxGoodsOrderModel);

        // 抵扣优惠金额
        priceAmountWithoutCamp.reduce(campPriceAmount);

        return priceAmountWithoutCamp;
    }

    /**
     * 计算没有优惠的情况下商品订单的销售总价
     * 
     * @param pxGoodsOrderModel
     * @return
     */
    public static Money calculateOrderPriceAmountWithoutCamp(PxGoodsOrderModel pxGoodsOrderModel) {

        if (pxGoodsOrderModel == null || CollectionUtils.isEmpty(pxGoodsOrderModel.getPxGoodsModelList())) {
            return new Money();
        }

        List<PxGoodsModel> goodsList = pxGoodsOrderModel.getPxGoodsModelList();
        Money totalPriceAmount = new Money();
        Money goodsPrice = null;
        int goodsAmount = 0;
        for (PxGoodsModel pxGoodsModel : goodsList) {
            if (pxGoodsModel == null || StringUtils.isBlank(pxGoodsModel.getGoodsPrice())) {
                continue;
            }

            goodsPrice = new Money(pxGoodsModel.getGoodsPrice());
            goodsAmount = Integer.parseInt(pxGoodsModel.getGoodsSellAmount());
            goodsPrice.multiply(goodsAmount);
            totalPriceAmount.add(goodsPrice);
        }

        return totalPriceAmount;
    }

    /**
     * 获取优惠抵扣金额
     * 
     * @param pxGoodsOrderModel
     * @return
     */
    public static Money calculatorCampPriceAmount(PxGoodsOrderModel pxGoodsOrderModel) {
        if (pxGoodsOrderModel == null || pxGoodsOrderModel.getCampShopPrizeOutModel() == null) {
            return new Money();
        }

        CampShopPrizeOutModel campShopPrizeOutModel = pxGoodsOrderModel.getCampShopPrizeOutModel();

        return new Money(campShopPrizeOutModel.getPrice());
    }
}
