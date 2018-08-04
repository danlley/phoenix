/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.admin.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.phoenix.biz.service.impl.MtServiceResult;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsAdvModel;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;
import com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent;
import com.myteay.phoenix.core.service.manage.component.PxGoodsComponent;

/**
 * 商品摘要管理
 * 
 * @author min.weixm
 * @version $Id: PxGoodsController.java, v 0.1 Jul 26, 2018 1:55:30 PM min.weixm Exp $
 */
@RestController
@RequestMapping(value = "myteay/api/phoenix/admin/manage/goods")
public class PxGoodsController {

    /** 日志 */
    public static final Logger      logger = Logger.getLogger(PxGoodsController.class);

    /** 后台一般性简单业务管理组件 */
    @Autowired
    private PxCommonManageComponent pxCommonManageComponent;

    /** 后台一般性简单业务管理组件 */
    @Autowired
    private PxGoodsComponent        pxGoodsComponent;

    /**
     * 查询所有商品概要信息
     * 
     * @return
     */
    @RequestMapping(value = "/all", method = { RequestMethod.GET })
    public MtServiceResult<List<PxGoodsModel>> queryShopAll() {
        MtServiceResult<List<PxGoodsModel>> result = null;

        MtOperateResult<List<PxGoodsModel>> componentResult = null;
        try {
            componentResult = pxCommonManageComponent.queryGoodsAll();
            result = new MtServiceResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询商品摘要信息发生未知异常 " + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * 通过店铺ID查询店铺下的所有商品列表
     * 
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/list/shop/{shopId}", method = { RequestMethod.GET })
    public MtServiceResult<List<PxGoodsModel>> queryGoodsByShopId(@PathVariable String shopId) {
        MtServiceResult<List<PxGoodsModel>> result = null;

        MtOperateResult<List<PxGoodsModel>> componentResult = null;
        try {
            componentResult = pxCommonManageComponent.queryGoodsListByShopId(shopId);
            result = new MtServiceResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询商品摘要信息发生未知异常 " + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * 商品概要管理服务（增、删、改、单条查询）
     * 
     * @param pxShopModel
     * @return
     */
    @RequestMapping(value = "/query/goods/adv/{goodsId}", method = { RequestMethod.POST })
    public MtServiceResult<PxGoodsAdvModel> querySingleAdv(@PathVariable String goodsId) {

        if (logger.isInfoEnabled()) {
            logger.info("开始执行商品高阶版查询 goodsId=" + goodsId);
        }
        MtServiceResult<PxGoodsAdvModel> result = null;
        try {
            MtOperateResult<PxGoodsAdvModel> innerResult = pxCommonManageComponent.queryGoodsAdvAll(goodsId);
            result = new MtServiceResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("保存商品概要信息发生异常" + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * 商品概要管理服务（增、删、改、单条查询）
     * 
     * @param pxShopModel
     * @return
     */
    @RequestMapping(value = "/manage", method = { RequestMethod.POST })
    public MtServiceResult<PxGoodsModel> manageGoods(@RequestBody PxGoodsModel pxGoodsModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始保存商品概要信息 pxGoodsModel=" + pxGoodsModel);
        }
        MtServiceResult<PxGoodsModel> result = null;
        try {
            MtOperateResult<PxGoodsModel> innerResult = pxGoodsComponent.manageGoods(pxGoodsModel);
            result = new MtServiceResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("保存商品概要信息发生异常" + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * Setter method for property <tt>pxCommonManageComponent</tt>.
     * 
     * @param pxCommonManageComponent value to be assigned to property pxCommonManageComponent
     */
    public void setPxCommonManageComponent(PxCommonManageComponent pxCommonManageComponent) {
        this.pxCommonManageComponent = pxCommonManageComponent;
    }

    /**
     * Setter method for property <tt>pxGoodsComponent</tt>.
     * 
     * @param pxGoodsComponent value to be assigned to property pxGoodsComponent
     */
    public void setPxGoodsComponent(PxGoodsComponent pxGoodsComponent) {
        this.pxGoodsComponent = pxGoodsComponent;
    }

}
