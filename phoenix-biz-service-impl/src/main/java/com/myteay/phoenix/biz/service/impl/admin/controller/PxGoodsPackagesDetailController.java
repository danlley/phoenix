///**
// * Myteay.com Inc.
// * Copyright (c) 2015-2018 All Rights Reserved.
// */
//package com.myteay.phoenix.biz.service.impl.admin.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.myteay.common.util.log.Logger;
//import com.myteay.common.util.log.LoggerFactory;
//import com.myteay.phoenix.biz.service.impl.MtServiceResult;
//import com.myteay.phoenix.common.logs.LoggerNames;
//import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
//import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
//import com.myteay.phoenix.core.model.MtOperateResult;
//import com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel;
//import com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent;
//import com.myteay.phoenix.core.service.manage.component.PxGoodsPackagesDetailComponent;
//
///**
// * 套餐包管理
// * 
// * @author min.weixm
// * @version $Id: PxGoodsPackagesDetailController.java, v 0.1 Jul 27, 2018 9:14:49 PM min.weixm Exp $
// */
//@RestController
//@RequestMapping(value = "myteay/api/phoenix/admin/manage/pkgs")
//public class PxGoodsPackagesDetailController {
//
//    /** 日志 */
//    private static final Logger            logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);
//
//    /** 后台一般性简单业务管理组件 */
//    @Autowired
//    private PxCommonManageComponent        pxCommonManageComponent;
//
//    /** 套餐包管理组件 */
//    @Autowired
//    private PxGoodsPackagesDetailComponent pxGoodsPackagesDetailComponent;
//
//    /**
//     * 查询所有套餐包信息
//     * 
//     * @return
//     */
//    @RequestMapping(value = "/all", method = { RequestMethod.GET })
//    public MtServiceResult<List<PxGoodsPackagesDetailModel>> queryShopAll() {
//        MtServiceResult<List<PxGoodsPackagesDetailModel>> result = null;
//
//        MtOperateResult<List<PxGoodsPackagesDetailModel>> componentResult = null;
//        try {
//            componentResult = pxCommonManageComponent.queryPackagesDetailListAll();
//            result = new MtServiceResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
//            result.setResult(componentResult.getResult());
//        } catch (Exception e) {
//            logger.warn("查询套餐包信息发生未知异常 " + e.getMessage(), e);
//            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
//        }
//
//        return result;
//    }
//
//    /**
//     * 通过商品摘要ID查询店铺下的所有套餐包列表
//     * 
//     * @param shopId
//     * @return
//     */
//    @RequestMapping(value = "/list/goods/{goodsId}", method = { RequestMethod.GET })
//    public MtServiceResult<List<PxGoodsPackagesDetailModel>> queryGoodsByShopId(@PathVariable String goodsId) {
//        MtServiceResult<List<PxGoodsPackagesDetailModel>> result = null;
//
//        MtOperateResult<List<PxGoodsPackagesDetailModel>> componentResult = null;
//        try {
//            componentResult = pxCommonManageComponent.queryPackagesDetailListByGoodsId(goodsId);
//            result = new MtServiceResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
//            result.setResult(componentResult.getResult());
//        } catch (Exception e) {
//            logger.warn("查询套餐包信息发生未知异常 " + e.getMessage(), e);
//            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
//        }
//
//        return result;
//    }
//
//    /**
//     * 套餐包管理服务（增、删、改、单条查询）
//     * 
//     * @param pxShopModel
//     * @return
//     */
//    @RequestMapping(value = "/manage", method = { RequestMethod.POST })
//    public MtServiceResult<PxGoodsPackagesDetailModel> manageGoods(@RequestBody PxGoodsPackagesDetailModel pxGoodsPackagesDetailModel) {
//
//        if (logger.isInfoEnabled()) {
//            logger.info("开始保存套餐包信息 pxGoodsPackagesDetailModel=" + pxGoodsPackagesDetailModel);
//        }
//        MtServiceResult<PxGoodsPackagesDetailModel> result = null;
//        try {
//            MtOperateResult<PxGoodsPackagesDetailModel> innerResult = pxGoodsPackagesDetailComponent.manageGoodsPackagesDetail(pxGoodsPackagesDetailModel);
//            result = new MtServiceResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
//            result.setResult(innerResult.getResult());
//        } catch (Exception e) {
//            logger.warn("保存套餐包信息发生异常" + e.getMessage(), e);
//            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
//        }
//
//        return result;
//    }
//
//    /**
//     * Setter method for property <tt>pxCommonManageComponent</tt>.
//     * 
//     * @param pxCommonManageComponent value to be assigned to property pxCommonManageComponent
//     */
//    public void setPxCommonManageComponent(PxCommonManageComponent pxCommonManageComponent) {
//        this.pxCommonManageComponent = pxCommonManageComponent;
//    }
//
//    /**
//     * Setter method for property <tt>pxGoodsPackagesDetailComponent</tt>.
//     * 
//     * @param pxGoodsPackagesDetailComponent value to be assigned to property pxGoodsPackagesDetailComponent
//     */
//    public void setPxGoodsPackagesDetailComponent(PxGoodsPackagesDetailComponent pxGoodsPackagesDetailComponent) {
//        this.pxGoodsPackagesDetailComponent = pxGoodsPackagesDetailComponent;
//    }
//
//}
