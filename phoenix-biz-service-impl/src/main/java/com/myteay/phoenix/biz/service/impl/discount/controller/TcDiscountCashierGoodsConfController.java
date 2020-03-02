/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2015-2020 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.discount.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.common.util.lang.Money;
import com.myteay.phoenix.common.service.discount.integration.TcDiscountGoodsConfMngIntg;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.tc.discount.core.model.TcDiscountGoodsOrderModel;

/**
 * 
 * 
 * @author min.weixm
 * @version $Id: TcDiscountCashierGoodsConfController.java, v 0.1 Mar 1, 2020 11:37:40 AM min.weixm Exp $
 */
@RestController
@RequestMapping(value = "tiancan/api/discount/cashier")
public class TcDiscountCashierGoodsConfController {

    /** 日志 */
    public static final Logger         logger = Logger.getLogger(TcDiscountGoodsConfController.class);

    /** 店铺商品折扣管理组件 */
    @Autowired
    private TcDiscountGoodsConfMngIntg tcDiscountGoodsConfMngIntg;

    /**
     * 应用折扣价格
     * 
     * @param goodsList
     * @return
     */
    @RequestMapping(value = "/query/price/", method = { RequestMethod.POST })
    public MtOperateResult<TcDiscountGoodsOrderModel<PxGoodsOrderModel, Money>> aplayDiscount(@RequestBody TcDiscountGoodsOrderModel<PxGoodsOrderModel, Money> orderModel) {

        if (logger.isInfoEnabled()) {
            logger.warn("收到订单折扣请求 orderModel=" + orderModel);
        }

        return tcDiscountGoodsConfMngIntg.aplayDiscount(orderModel);
    }
}
