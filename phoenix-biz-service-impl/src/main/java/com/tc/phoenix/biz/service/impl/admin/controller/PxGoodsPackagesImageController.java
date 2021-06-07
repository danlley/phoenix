/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2021 All Rights Reserved.
 */
package com.tc.phoenix.biz.service.impl.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tc.ccopass.logger.Logger;
import com.tc.ccopass.logger.LoggerFactory;
import com.tc.dbcenter.common.orm.enums.PxOperationTypeEnum;
import com.tc.dbcenter.common.orm.model.PxGoodsPackagesImageModel;
import com.tc.phoenix.common.service.integration.PxGoodsPackagesImageIntg;
import com.tc.phoenix.common.util.MtFileUtils;
import com.tc.phoenix.common.util.MtOperateResult;
import com.tc.phoenix.common.util.enums.MtOperateExResultEnum;
import com.tc.phoenix.common.util.enums.MtOperateResultEnum;
import com.tc.phoenix.common.util.log.LoggerNames;

/**
 * 套餐详情图片管理
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesImageController.java, v 0.1 Aug 1, 2018 1:30:25 PM min.weixm Exp $
 */
@RestController
@RequestMapping(value = "myteay/api/phoenix/admin/manage/pkgs/image")
public class PxGoodsPackagesImageController {

    /** 日志 */
    private static final Logger      logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /** 后台一般性简单业务管理组件 */
    @Autowired
    private PxGoodsPackagesImageIntg pxGoodsPackagesImageIntg;

    /** 套餐详情图片管理 */
    @Autowired
    private Environment              env;

    /**
     * 通过商品摘要ID查询店铺下的所有套餐详情图片列表
     * 
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/list/goods/{goodsId}", method = { RequestMethod.GET })
    public MtOperateResult<List<PxGoodsPackagesImageModel>> queryGoodsImageByGoodsId(@PathVariable String goodsId) {
        MtOperateResult<List<PxGoodsPackagesImageModel>> result = null;

        MtOperateResult<List<PxGoodsPackagesImageModel>> componentResult = null;
        try {
            componentResult = pxGoodsPackagesImageIntg.queryPackagesImageListByGoodsId(goodsId);
            result = new MtOperateResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询套餐详情图片信息发生未知异常 " + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * 套餐详情图片管理服务（增、删、改、单条查询）
     * 
     * @param pxShopModel
     * @return
     */
    @RequestMapping(value = "/manage/goods/{goodsId}", method = { RequestMethod.POST })
    public MtOperateResult<PxGoodsPackagesImageModel> manageGoodsImage(@PathVariable String goodsId, @RequestParam(value = "file") MultipartFile file,
                                                                       HttpServletRequest request, HttpServletResponse response) {

        if (logger.isInfoEnabled()) {
            logger.info("开始保存套餐详情图片信息 goodsId=" + goodsId);
        }

        MtOperateResult<PxGoodsPackagesImageModel> result = null;
        PxGoodsPackagesImageModel pxGoodsPackagesImageModel = upload(goodsId, file);

        if (pxGoodsPackagesImageModel == null) {
            logger.warn("图片上传失败，关键信息不可用  pxGoodsPackagesImageModel is null");
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        try {
            MtOperateResult<PxGoodsPackagesImageModel> innerResult = pxGoodsPackagesImageIntg.manageGoodsPackagesImage(pxGoodsPackagesImageModel);
            result = new MtOperateResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("保存套餐详情图片信息发生异常" + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * 套餐详情图片管理服务（增、删、改、单条查询）
     * 
     * @param imageId
     * @return
     */
    @RequestMapping(value = "/manage/goods/remove/{imageId}", method = { RequestMethod.POST })
    public MtOperateResult<PxGoodsPackagesImageModel> delGoodsImage(@PathVariable String imageId) {

        if (logger.isInfoEnabled()) {
            logger.info("开始删除套餐详情图片信息 imageId=" + imageId);
        }

        MtOperateResult<PxGoodsPackagesImageModel> result = null;
        PxGoodsPackagesImageModel pxGoodsPackagesImageModel = new PxGoodsPackagesImageModel();

        pxGoodsPackagesImageModel.setImageId(imageId);
        pxGoodsPackagesImageModel.setOperationType(PxOperationTypeEnum.PX_DELETE);

        try {
            MtOperateResult<PxGoodsPackagesImageModel> innerResult = pxGoodsPackagesImageIntg.manageGoodsPackagesImage(pxGoodsPackagesImageModel);
            result = new MtOperateResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("保存套餐详情图片信息发生异常" + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * 上传文件
     * 
     * @param goodsId
     * @param file
     * @return
     */
    private PxGoodsPackagesImageModel upload(String goodsId, MultipartFile file) {
        String image = null;
        try {
            image = MtFileUtils.upload(file, env.getProperty("uploadpic.path"));
        } catch (Throwable e) {
            logger.warn("文件上传过程发生异常 goodsId=" + goodsId, e);
        }

        if (StringUtils.isBlank(image) || StringUtils.isBlank(goodsId)) {
            logger.warn("图片上传失败，关键信息不可用 image=" + image + " goodsId=" + goodsId);
            return null;
        }

        PxGoodsPackagesImageModel pxGoodsPackagesImageModel = new PxGoodsPackagesImageModel();

        pxGoodsPackagesImageModel.setGoodsId(goodsId);
        pxGoodsPackagesImageModel.setOperationType(PxOperationTypeEnum.PX_ADD);
        pxGoodsPackagesImageModel.setImage(image);
        logger.warn("文件路径： image=" + image);

        return pxGoodsPackagesImageModel;
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
