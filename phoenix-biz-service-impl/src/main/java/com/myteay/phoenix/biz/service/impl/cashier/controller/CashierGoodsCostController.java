///**
// * Myteay.com Inc.
// * Copyright (c) 2015-2019 All Rights Reserved.
// */
//package com.myteay.phoenix.biz.service.impl.cashier.controller;
//
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.myteay.common.util.log.Logger;
//import com.myteay.common.util.log.LoggerFactory;
//import com.myteay.common.util.tools.DateUtil;
//import com.myteay.phoenix.biz.service.impl.MtServiceResult;
//import com.myteay.phoenix.common.logs.LoggerNames;
//import com.myteay.phoenix.core.model.manage.PxGoodsCostModel;
//import com.myteay.phoenix.core.service.manage.component.PxGoodsCostComponent;
//
///**
// * 店铺商品成本管理
// * 
// * @author danlley
// * @version $Id: CashierGoodsCostController.java, v 0.1 May 8, 2019 3:15:35 AM danlley Exp $
// */
//@RestController
//@RequestMapping(value = "myteay/api/phoenix/cashier/order/cost")
//public class CashierGoodsCostController {
//
//    /** 日志 */
//    private static final Logger  logger = LoggerFactory.getLogger(LoggerNames.PX_CASHIER_DEFAULT);
//
//    /** 店铺商品经营成本统计管理组件 */
//    @Autowired
//    private PxGoodsCostComponent pxGoodsCostComponent;
//
//    /**
//     * 查询当日店铺商品销售成本清单
//     * 
//     * @param shopId
//     * @return
//     */
//    @RequestMapping(value = "/list/{shopId}", method = { RequestMethod.GET })
//    public MtServiceResult<List<PxGoodsCostModel>> queryCurrentGoodsCost(@PathVariable String shopId) {
//        String reportDate = DateUtil.format(new Date(), DateUtil.shortFormat);
//
//        if (logger.isInfoEnabled()) {
//            logger.info("queryCurrentGoodsCost list of shopId = " + shopId + " reportDate = " + reportDate);
//        }
//
//        return new MtServiceResult<>(pxGoodsCostComponent.findPxGoodsCostByShopId(shopId, reportDate));
//    }
//
//    /**
//     * Setter method for property <tt>pxGoodsCostComponent</tt>.
//     * 
//     * @param pxGoodsCostComponent value to be assigned to property pxGoodsCostComponent
//     */
//    public void setPxGoodsCostComponent(PxGoodsCostComponent pxGoodsCostComponent) {
//        this.pxGoodsCostComponent = pxGoodsCostComponent;
//    }
//
//}
