/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2021 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.admin.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tc.ccopass.logger.Logger;
import com.tc.ccopass.logger.LoggerFactory;
import com.tc.dbcenter.common.orm.enums.PxOperationTypeEnum;
import com.tc.dbcenter.common.orm.model.PxGoodsAdvModel;
import com.tc.dbcenter.common.orm.model.PxGoodsModel;
import com.tc.phoenix.common.service.integration.PxGoodsControllerIntg;
import com.tc.phoenix.common.util.MtFileUtils;
import com.tc.phoenix.common.util.MtOperateResult;
import com.tc.phoenix.common.util.enums.MtOperateExResultEnum;
import com.tc.phoenix.common.util.enums.MtOperateResultEnum;
import com.tc.phoenix.common.util.log.LoggerNames;

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
    private static final Logger   logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /** 后台一般性简单业务管理组件 */
    @Autowired
    private PxGoodsControllerIntg pxGoodsControllerIntg;

    /** 套餐详情图片管理 */
    @Autowired
    private Environment           env;

    /**
     * 查询所有商品概要信息
     * 
     * @return
     */
    @RequestMapping(value = "/all", method = { RequestMethod.GET })
    public MtOperateResult<List<PxGoodsModel>> queryShopAll() {
        MtOperateResult<List<PxGoodsModel>> result = null;

        MtOperateResult<List<PxGoodsModel>> componentResult = null;
        try {
            componentResult = pxGoodsControllerIntg.queryGoodsAll();
            result = new MtOperateResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询商品摘要信息发生未知异常 " + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
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
    public MtOperateResult<List<PxGoodsModel>> queryGoodsByShopId(@PathVariable String shopId) {
        MtOperateResult<List<PxGoodsModel>> result = null;

        MtOperateResult<List<PxGoodsModel>> componentResult = null;
        try {
            componentResult = pxGoodsControllerIntg.queryGoodsListByShopId(shopId);
            result = new MtOperateResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询商品摘要信息发生未知异常 " + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
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
    public MtOperateResult<PxGoodsAdvModel> querySingleAdv(@PathVariable String goodsId) {

        if (logger.isInfoEnabled()) {
            logger.info("开始执行商品高阶版查询 goodsId=" + goodsId);
        }
        MtOperateResult<PxGoodsAdvModel> result = null;
        try {
            MtOperateResult<PxGoodsAdvModel> innerResult = pxGoodsControllerIntg.queryGoodsAdvAll(goodsId);
            result = new MtOperateResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("保存商品概要信息发生异常" + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * 多条件查询商品概要信息
     * 
     * @param pxShopModel
     * @return
     */
    @RequestMapping(value = "/query/goods/condition/", method = { RequestMethod.POST })
    public MtOperateResult<List<PxGoodsModel>> querySingleAdv(String shopId, String goodsType, String goodsTitle) {

        if (logger.isInfoEnabled()) {
            logger.info("开始执行商品多条件查询 shopId=" + shopId + " goodsType=" + goodsType + " goodsTitle=" + goodsTitle);
        }
        MtOperateResult<List<PxGoodsModel>> result = null;
        try {
            MtOperateResult<List<PxGoodsModel>> innerResult = pxGoodsControllerIntg.findPxShopOnlineGoodsByCondition(shopId, goodsType, goodsTitle);
            result = new MtOperateResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("执行商品多条件查询发生异常" + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
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
    public MtOperateResult<PxGoodsModel> manageGoods(@RequestParam(value = "file", required = false) MultipartFile file, PxGoodsModel pxGoodsModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始保存商品概要信息 pxGoodsModel=" + pxGoodsModel);
        }
        MtOperateResult<PxGoodsModel> result = null;
        try {
            uploadFile(file, pxGoodsModel);
            MtOperateResult<PxGoodsModel> innerResult = pxGoodsControllerIntg.manageGoods(pxGoodsModel);
            result = new MtOperateResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("保存商品概要信息发生异常" + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * 商品下架及商品发布
     * 
     * @param pxShopModel
     * @return
     */
    @RequestMapping(value = "/status/", method = { RequestMethod.POST })
    public MtOperateResult<PxGoodsModel> onlineGoods(@RequestBody PxGoodsModel pxGoodsModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始执行商品下架及商品发布 pxGoodsModel=" + pxGoodsModel);
        }
        MtOperateResult<PxGoodsModel> result = null;
        try {
            MtOperateResult<PxGoodsModel> innerResult = pxGoodsControllerIntg.manageGoodsStatus(pxGoodsModel);
            result = new MtOperateResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("商品下架及商品发布过程发生未知异常" + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * 根据数据保存结果，确定是否需要保存文件
     * 
     * @param result
     */
    private void uploadFile(MultipartFile file, PxGoodsModel pxGoodsModel) {
        if (!isNeedUploadFile(pxGoodsModel) || file == null) {
            logger.warn("当前商品不满足文件上传条件，无法保存文件 pxGoodsModel=" + pxGoodsModel);
            return;
        }

        String image = null;
        try {
            image = MtFileUtils.upload(file, env.getProperty("uploadpic.path"));
        } catch (Throwable e) {
            logger.warn("文件上传过程发生异常 pxGoodsModel=" + pxGoodsModel, e);
        }

        pxGoodsModel.setGoodsImage(image);
    }

    /**
     * 判断当前更新动作是否需要更新文件信息
     * 
     * @param pxGoodsModel
     * @return
     */
    private boolean isNeedUploadFile(PxGoodsModel pxGoodsModel) {
        if (pxGoodsModel == null || pxGoodsModel.getOperationType() == null) {
            logger.warn("当前商品摘要信息操作类型不可用，无法完成文件上传动作 pxGoodsModel=" + pxGoodsModel);
            return false;
        }

        PxOperationTypeEnum operationTypeEnum = pxGoodsModel.getOperationType();

        // 新加的商品，默认直接上传
        if (operationTypeEnum == PxOperationTypeEnum.PX_ADD) {
            return true;
        }

        if (operationTypeEnum == PxOperationTypeEnum.PX_MODIFY && isNeedUpdateFileName(pxGoodsModel)) {
            return true;
        }

        return false;
    }

    /**
     * 判断更新请求中的图片信息是否需要一起更新
     * 
     * @param pxGoodsModel
     * @return
     */
    private boolean isNeedUpdateFileName(PxGoodsModel pxGoodsModel) {
        PxGoodsModel queryModel = new PxGoodsModel();
        queryModel.setOperationType(PxOperationTypeEnum.PX_QUERY_ONE);
        queryModel.setGoodsId(pxGoodsModel.getGoodsId());
        MtOperateResult<PxGoodsModel> innerResult = pxGoodsControllerIntg.manageGoods(queryModel);
        if (innerResult == null || innerResult.getResult() == null) {
            return false;
        }

        if (!StringUtils.equals(innerResult.getResult().getGoodsImage(), pxGoodsModel.getGoodsImage())) {
            return true;
        }

        return false;
    }

    /**
     * Setter method for property <tt>env</tt>.
     * 
     * @param env value to be assigned to property env
     */
    public void setEnv(Environment env) {
        this.env = env;
    }
}
