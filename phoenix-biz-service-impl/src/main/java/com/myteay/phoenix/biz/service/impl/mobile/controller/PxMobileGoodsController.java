///**
// * Myteay.com Inc.
// * Copyright (c) 2015-2018 All Rights Reserved.
// */
//package com.myteay.phoenix.biz.service.impl.mobile.controller;
//
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.myteay.phoenix.biz.service.impl.MtServiceResult;
//import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
//import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
//import com.myteay.phoenix.common.util.exception.PxManageException;
//import com.myteay.phoenix.core.model.MtOperateResult;
//import com.myteay.phoenix.core.model.mobile.PxMobileGoodsModel;
//import com.myteay.phoenix.core.service.component.PxMobileGoodsComponnet;
//
///**
// * 手机端商品管理
// * 
// * @author min.weixm
// * @version $Id: PxMobileGoodsController.java, v 0.1 Aug 17, 2018 2:37:43 AM min.weixm Exp $
// */
//@RestController
//@RequestMapping(value = "myteay/api/phoenix/mobile/goods")
//public class PxMobileGoodsController {
//
//    /** 日志 */
//    public static final Logger     logger = Logger.getLogger(PxMobileGoodsController.class);
//
//    /** 商品管理组件 */
//    @Autowired
//    private PxMobileGoodsComponnet pxMobileGoodsComponnet;
//
//    /**
//     * 查询商品列表
//     * 
//     * @param pxShopModel
//     * @return
//     */
//    @RequestMapping(value = "/list/", method = { RequestMethod.POST })
//    public MtServiceResult<List<PxMobileGoodsModel>> queryMobileGoodsList(@RequestBody List<String> excludeGoodsIds) {
//
//        if (logger.isInfoEnabled()) {
//            logger.info("[手机端]查询商品列表 excludeGoodsIds=" + excludeGoodsIds);
//        }
//        MtServiceResult<List<PxMobileGoodsModel>> result = null;
//        try {
//            MtOperateResult<List<PxMobileGoodsModel>> innerResult = pxMobileGoodsComponnet.queryNextGoodsList(excludeGoodsIds);
//            result = new MtServiceResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
//            result.setResult(innerResult.getResult());
//        } catch (PxManageException e) {
//            logger.warn("[手机端]查询商品列表过程发生业务异常" + e.getMessage(), e);
//            result = new MtServiceResult<>(e.getResultEnum(), e.getExResultEnum());
//        } catch (Exception e) {
//            logger.warn("[手机端]查询商品列表过程发生未知异常" + e.getMessage(), e);
//            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
//        }
//
//        return result;
//    }
//
//    /**
//     * 查询单个商品详情
//     * 
//     * @param pxShopModel
//     * @return
//     */
//    @RequestMapping(value = "/single/{goodsId}", method = { RequestMethod.POST })
//    public MtServiceResult<PxMobileGoodsModel> querySingleGoodsDetail(@PathVariable String goodsId) {
//
//        if (logger.isInfoEnabled()) {
//            logger.info("[手机端]查询商品详情 goodsId=" + goodsId);
//        }
//        MtServiceResult<PxMobileGoodsModel> result = null;
//        try {
//            MtOperateResult<PxMobileGoodsModel> innerResult = pxMobileGoodsComponnet.queryGoodsDetail(goodsId);
//            result = new MtServiceResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
//            result.setResult(innerResult.getResult());
//        } catch (PxManageException e) {
//            logger.warn("[手机端]查询商品详情过程发生业务异常" + e.getMessage(), e);
//            result = new MtServiceResult<>(e.getResultEnum(), e.getExResultEnum());
//        } catch (Exception e) {
//            logger.warn("[手机端]查询商品详情过程发生未知异常" + e.getMessage(), e);
//            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
//        }
//
//        return result;
//    }
//
//    /**
//     * Setter method for property <tt>pxMobileGoodsComponnet</tt>.
//     * 
//     * @param pxMobileGoodsComponnet value to be assigned to property pxMobileGoodsComponnet
//     */
//    public void setPxMobileGoodsComponnet(PxMobileGoodsComponnet pxMobileGoodsComponnet) {
//        this.pxMobileGoodsComponnet = pxMobileGoodsComponnet;
//    }
//
//}
