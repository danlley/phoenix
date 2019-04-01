/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component.impl;

import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.enums.PxOperationTypeEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsStatusEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesImageModel;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesImageRepository;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsRepository;
import com.myteay.phoenix.core.service.manage.component.PxGoodsComponent;
import com.myteay.phoenix.core.service.manage.component.PxGoodsPackagesImageComponent;
import com.myteay.phoenix.core.service.manage.template.PxCommonCallback;
import com.myteay.phoenix.core.service.manage.template.PxCommonMngTemplate;
import com.myteay.phoenix.core.service.utils.PxMngUtil;

/**
 * 套餐详情图片管理
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesImageComponentImpl.java, v 0.1 Aug 1, 2018 1:12:32 PM min.weixm Exp $
 */
public class PxGoodsPackagesImageComponentImpl implements PxGoodsPackagesImageComponent {

    /** 日志 */
    private static final Logger                            logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /** 后台管理业务处理分流模板 */
    private PxCommonMngTemplate<PxGoodsPackagesImageModel> pxCommonMngTemplate;

    /** 商品详情图片管理仓储 */
    private PxGoodsPackagesImageRepository                 pxGoodsPackagesImageRepository;

    /** 商品概要管理仓储 */
    private PxGoodsRepository                              pxGoodsRepository;

    /** 商品管理组件 */
    private PxGoodsComponent                               pxGoodsComponent;

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxGoodsPackagesImageComponent#manageGoodsPackagesImage(com.myteay.phoenix.core.model.manage.PxGoodsPackagesImageModel)
     */
    @Override
    public MtOperateResult<PxGoodsPackagesImageModel> manageGoodsPackagesImage(PxGoodsPackagesImageModel pxGoodsPackagesImageModel) {
        if (pxGoodsPackagesImageModel == null) {
            logger.warn("套餐详情图片模型不可用，无法执行管理动作 pxGoodsPackagesImageModel= " + pxGoodsPackagesImageModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_IMAGE_MODEL_INVALID);
        }

        if (pxGoodsPackagesImageModel.getOperationType() == null) {
            logger.warn("套餐详情图片模型中的操作类型未知 pxGoodsPackagesImageModel=" + pxGoodsPackagesImageModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_TEMPLATE_OP_UNKNOW);
        }

        return switchOperation(pxGoodsPackagesImageModel, pxGoodsPackagesImageModel.getOperationType());
    }

    /**
     * 查询单条套餐详情图片
     * 
     * @param pxGoodsPackagesImageModel
     * @return
     */
    private MtOperateResult<PxGoodsPackagesImageModel> querySingleImageById(PxGoodsPackagesImageModel pxGoodsPackagesImageModel) {
        MtOperateResult<PxGoodsPackagesImageModel> result = new MtOperateResult<PxGoodsPackagesImageModel>();
        PxGoodsPackagesImageModel freshPxGoodsPackagesImageModel = null;
        try {
            freshPxGoodsPackagesImageModel = pxGoodsPackagesImageRepository.findSingleGoodsPackagesImage(pxGoodsPackagesImageModel.getImageId());
            result.setResult(freshPxGoodsPackagesImageModel);
        } catch (PxManageException e) {
            logger.warn("保存套餐详情图片信息发生异常 pxGoodsPackagesImageModel=" + pxGoodsPackagesImageModel, e);
            result = new MtOperateResult<PxGoodsPackagesImageModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_IMAGE_QUERY_FAILD);
        }

        return result;
    }

    /**
     * 删除套餐详情图片信息
     * 
     * @param pxGoodsPackagesImageModel
     * @return
     */
    private MtOperateResult<PxGoodsPackagesImageModel> deleteGoodsModel(PxGoodsPackagesImageModel pxGoodsPackagesImageModel) {

        // step 1: 商品状态为已发布、已下线，则不允许进行删除
        try {
            PxGoodsModel freshPxGoodsModel = pxGoodsRepository
                .findSingleGoods(pxGoodsPackagesImageRepository.findSingleGoodsPackagesImage(pxGoodsPackagesImageModel.getImageId()).getGoodsId());
            if (freshPxGoodsModel != null && (freshPxGoodsModel.getGoodsStatus() == PxGoodsStatusEnum.PX_GOODS_OFFLINE
                                              || freshPxGoodsModel.getGoodsStatus() == PxGoodsStatusEnum.PX_GOODS_ONLINE)) {
                logger.warn("商品状态为已发布、已下线，则不允许进行删除 pxGoodsPackagesImageModel=" + pxGoodsPackagesImageModel);
                return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_DEL_STATUS_ERR);
            }
        } catch (PxManageException e) {
            logger.warn("查询单个商品概要信息发生异常 pxGoodsPackagesImageModel=" + pxGoodsPackagesImageModel, e);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_DELETE_FAILD);
        }

        MtOperateResult<PxGoodsPackagesImageModel> result = new MtOperateResult<PxGoodsPackagesImageModel>();
        try {
            pxGoodsPackagesImageRepository.removeGoodsPackagesImageInfo(pxGoodsPackagesImageModel);
        } catch (PxManageException e) {
            logger.warn("保存套餐详情图片信息发生异常 pxGoodsPackagesImageModel=" + pxGoodsPackagesImageModel, e);
            result = new MtOperateResult<PxGoodsPackagesImageModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_IMAGE_DELETE_FAILD);
        }

        return result;
    }

    /**
     * 通过图片模型查找当前商品ID
     * 
     * @param pxGoodsPackagesImageModel
     * @return
     */
    private String queryGoodsIdByImageModel(PxGoodsPackagesImageModel pxGoodsPackagesImageModel) {
        PxGoodsPackagesImageModel newModel = null;
        try {
            newModel = pxGoodsPackagesImageRepository.findSingleGoodsPackagesImage(pxGoodsPackagesImageModel.getImageId());
        } catch (PxManageException e) {
            logger.warn("查询当前图片模型失败 pxGoodsPackagesImageModel =" + pxGoodsPackagesImageModel, e);
        }

        if (newModel == null) {
            return null;
        }

        return newModel.getGoodsId();
    }

    /**
     * 保存套餐详情图片信息
     * 
     * @param pxGoodsPackagesImageModel
     * @return
     */
    private MtOperateResult<PxGoodsPackagesImageModel> saveGoodsModel(PxGoodsPackagesImageModel pxGoodsPackagesImageModel) {
        MtOperateResult<PxGoodsPackagesImageModel> result = new MtOperateResult<PxGoodsPackagesImageModel>();

        if (!PxMngUtil.isCanDoOperation(pxGoodsComponent.queryGoodsModelByGoodsId(pxGoodsPackagesImageModel.getGoodsId()))) {
            logger.warn("当前商品不满足追加详情图片条件，请检查商品是否已发布或已下线 pxGoodsPackagesImageModel=" + pxGoodsPackagesImageModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_IMG_ONLINE_ADD_ERR);
        }

        PxGoodsPackagesImageModel freshPxGoodsPackagesImageModel = null;
        try {
            freshPxGoodsPackagesImageModel = pxGoodsPackagesImageRepository.saveGoodsPackagesImageInfo(pxGoodsPackagesImageModel);
            result.setResult(freshPxGoodsPackagesImageModel);
        } catch (PxManageException e) {
            logger.warn("保存套餐详情图片信息发生异常 pxGoodsPackagesImageModel=" + pxGoodsPackagesImageModel, e);
            result = new MtOperateResult<PxGoodsPackagesImageModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_IMAGE_SAVE_FAILD);
        }

        return result;
    }

    /**
     * 根据不同的管理目标执行管理动作
     * 
     * @param pxGoodsPackagesImageModel
     * @param operationType
     * @return
     */
    private MtOperateResult<PxGoodsPackagesImageModel> switchOperation(PxGoodsPackagesImageModel pxGoodsPackagesImageModel, PxOperationTypeEnum operationType) {

        return pxCommonMngTemplate.switchOperation(pxGoodsPackagesImageModel, operationType, new PxCommonCallback<PxGoodsPackagesImageModel>() {

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#querySingleModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxGoodsPackagesImageModel> querySingleModel(PxGoodsPackagesImageModel t) {
                return querySingleImageById(pxGoodsPackagesImageModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#modifyModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxGoodsPackagesImageModel> modifyModel(PxGoodsPackagesImageModel t) {
                return new MtOperateResult<PxGoodsPackagesImageModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_UN_SUPPORT);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#deleteModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxGoodsPackagesImageModel> deleteModel(PxGoodsPackagesImageModel t) {
                return deleteGoodsModel(pxGoodsPackagesImageModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#addModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxGoodsPackagesImageModel> addModel(PxGoodsPackagesImageModel t) {
                return saveGoodsModel(pxGoodsPackagesImageModel);
            }

        });
    }

    /**
     * Setter method for property <tt>pxCommonMngTemplate</tt>.
     * 
     * @param pxCommonMngTemplate value to be assigned to property pxCommonMngTemplate
     */
    public void setPxCommonMngTemplate(PxCommonMngTemplate<PxGoodsPackagesImageModel> pxCommonMngTemplate) {
        this.pxCommonMngTemplate = pxCommonMngTemplate;
    }

    /**
     * Setter method for property <tt>pxGoodsPackagesImageRepository</tt>.
     * 
     * @param pxGoodsPackagesImageRepository value to be assigned to property pxGoodsPackagesImageRepository
     */
    public void setPxGoodsPackagesImageRepository(PxGoodsPackagesImageRepository pxGoodsPackagesImageRepository) {
        this.pxGoodsPackagesImageRepository = pxGoodsPackagesImageRepository;
    }

    /**
     * Setter method for property <tt>pxGoodsRepository</tt>.
     * 
     * @param pxGoodsRepository value to be assigned to property pxGoodsRepository
     */
    public void setPxGoodsRepository(PxGoodsRepository pxGoodsRepository) {
        this.pxGoodsRepository = pxGoodsRepository;
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
