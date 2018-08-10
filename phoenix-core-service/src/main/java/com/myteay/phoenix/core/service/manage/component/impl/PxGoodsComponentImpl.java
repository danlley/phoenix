/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.enums.PxOperationTypeEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsStatusEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesImageModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesNoticeModel;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesDetailRepository;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesImageRepository;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesNoticeRepository;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsRepository;
import com.myteay.phoenix.core.service.manage.component.PxGoodsComponent;
import com.myteay.phoenix.core.service.manage.template.PxCommonCallback;
import com.myteay.phoenix.core.service.manage.template.PxCommonMngTemplate;

/**
 * 商品摘要管理组件
 * 
 * @author min.weixm
 * @version $Id: PxGoodsComponentImpl.java, v 0.1 Jul 26, 2018 1:58:49 PM min.weixm Exp $
 */
public class PxGoodsComponentImpl implements PxGoodsComponent {

    /** 日志 */
    public static final Logger                logger = Logger.getLogger(PxGoodsComponentImpl.class);

    /** 后台管理业务处理分流模板 */
    private PxCommonMngTemplate<PxGoodsModel> pxCommonMngTemplate;

    /** 商品概要管理仓储 */
    private PxGoodsRepository                 pxGoodsRepository;

    /** 套餐包仓储 */
    private PxGoodsPackagesDetailRepository   pxGoodsPackagesDetailRepository;

    /** 商品详情图片管理仓储 */
    private PxGoodsPackagesImageRepository    pxGoodsPackagesImageRepository;

    /** 温馨提醒摘要管理仓储 */
    private PxGoodsPackagesNoticeRepository   pxGoodsPackagesNoticeRepository;

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxGoodsComponent#modifyGoodsModel(com.myteay.phoenix.core.model.manage.PxGoodsModel)
     */
    public MtOperateResult<PxGoodsModel> modifyGoodsModel(PxGoodsModel pxGoodsModel) {
        MtOperateResult<PxGoodsModel> result = new MtOperateResult<PxGoodsModel>();
        PxGoodsModel freshPxGoodsModel = null;
        try {
            freshPxGoodsModel = pxGoodsRepository.modifyGoodsInfo(pxGoodsModel);
            result.setResult(freshPxGoodsModel);
        } catch (PxManageException e) {
            logger.warn("保存商品概要信息发生异常 pxGoodsModel=" + pxGoodsModel, e);
            result = new MtOperateResult<PxGoodsModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_UPDATE_FAILD);
        }

        return result;
    }

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxGoodsComponent#manageGoods(com.myteay.phoenix.core.model.manage.PxGoodsModel)
     */
    @Override
    public MtOperateResult<PxGoodsModel> manageGoods(PxGoodsModel pxGoodsModel) {

        if (pxGoodsModel == null) {
            logger.warn("商品概要模型不可用，无法执行管理动作 pxGoodsModel= " + pxGoodsModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_INVALID);
        }

        if (pxGoodsModel.getOperationType() == null) {
            logger.warn("商品概要模型中的操作类型未知 pxGoodsModel=" + pxGoodsModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_TEMPLATE_OP_UNKNOW);
        }

        return switchOperation(pxGoodsModel, pxGoodsModel.getOperationType());
    }

    /**
     * 查询单条商品概要
     * 
     * @param pxGoodsModel
     * @return
     */
    private MtOperateResult<PxGoodsModel> querySingleGoods(PxGoodsModel pxGoodsModel) {
        MtOperateResult<PxGoodsModel> result = new MtOperateResult<PxGoodsModel>();
        PxGoodsModel freshPxGoodsModel = null;
        try {
            freshPxGoodsModel = pxGoodsRepository.findSingleGoods(pxGoodsModel.getGoodsId());
            result.setResult(freshPxGoodsModel);
        } catch (PxManageException e) {
            logger.warn("保存商品概要信息发生异常 pxGoodsModel=" + pxGoodsModel, e);
            result = new MtOperateResult<PxGoodsModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_QUERY_FAILD);
        }

        return result;
    }

    /**
     * 删除商品概要信息
     * 
     * @param pxGoodsModel
     * @return
     */
    private MtOperateResult<PxGoodsModel> deleteGoodsModel(PxGoodsModel pxGoodsModel) {

        // step 1: 商品状态为已发布、已下线，则不允许进行删除
        try {
            PxGoodsModel freshPxGoodsModel = pxGoodsRepository.findSingleGoods(pxGoodsModel.getGoodsId());
            if (freshPxGoodsModel != null && (freshPxGoodsModel.getGoodsStatus() == PxGoodsStatusEnum.PX_GOODS_OFFLINE || freshPxGoodsModel
                .getGoodsStatus() == PxGoodsStatusEnum.PX_GOODS_ONLINE)) {
                logger.warn("商品状态为已发布、已下线，则不允许进行删除 pxGoodsModel=" + pxGoodsModel);
                return new MtOperateResult<PxGoodsModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_DEL_STATUS_ERR);
            }
        } catch (PxManageException e) {
            logger.warn("查询单个商品概要信息发生异常 pxGoodsModel=" + pxGoodsModel, e);
            return new MtOperateResult<PxGoodsModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_DELETE_FAILD);
        }

        // step 2: 商品包含子项内容，则不允许删除
        if (!isCanDeleteGoods(pxGoodsModel.getGoodsId())) {
            logger.warn("商品包含子项，无法删除 pxGoodsModel=" + pxGoodsModel);
            return new MtOperateResult<PxGoodsModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_HAS_CHILD_ERR);
        }

        // step 3: 执行删除动作
        MtOperateResult<PxGoodsModel> result = new MtOperateResult<PxGoodsModel>();
        try {
            pxGoodsRepository.removeGoodsInfo(pxGoodsModel);
        } catch (PxManageException e) {
            logger.warn("删除商品概要信息发生异常 pxGoodsModel=" + pxGoodsModel, e);
            result = new MtOperateResult<PxGoodsModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_DELETE_FAILD);
        }

        return result;
    }

    /**
     * 检查是否允许删除商品
     * 
     * @param goodsId
     * @return
     */
    private boolean isCanDeleteGoods(String goodsId) {

        if (isNoPackageDetail(goodsId) && isNoPackageDetailImage(goodsId) && isNoPackageNotice(goodsId)) {
            return true;
        }

        return false;
    }

    /**
     * 检查当前商品是否包含温馨提醒
     * 
     * @param goodsId
     * @return
     */
    private boolean isNoPackageNotice(String goodsId) {
        try {
            List<PxGoodsPackagesNoticeModel> list = pxGoodsPackagesNoticeRepository.findGoodsPackagesNoticeByGoodsId(goodsId);
            if (CollectionUtils.isEmpty(list)) {
                return true;
            }
        } catch (PxManageException e) {
            logger.warn("套餐温馨提醒信息查询发生异常", e);
        }
        return false;
    }

    /**
     * 检查当前商品是否包含套餐详情图片
     * 
     * @param goodsId
     * @return
     */
    private boolean isNoPackageDetailImage(String goodsId) {
        try {
            List<PxGoodsPackagesImageModel> list = pxGoodsPackagesImageRepository.findGoodsPackagesImageByGoodsId(goodsId);
            if (CollectionUtils.isEmpty(list)) {
                return true;
            }
        } catch (PxManageException e) {
            logger.warn("套餐详情图片信息查询发生异常", e);
        }
        return false;
    }

    /**
     * 检查当前商品是否包含套餐包信息
     * 
     * @param goodsId
     * @return
     */
    private boolean isNoPackageDetail(String goodsId) {
        try {
            List<PxGoodsPackagesDetailModel> list = pxGoodsPackagesDetailRepository.findGoodsPackagesDetailByGoodsId(goodsId);
            if (CollectionUtils.isEmpty(list)) {
                return true;
            }
        } catch (PxManageException e) {
            logger.warn("套餐包信息查询发生异常", e);
        }
        return false;
    }

    /**
     * 保存商品概要信息
     * 
     * @param pxGoodsModel
     * @return
     */
    private MtOperateResult<PxGoodsModel> saveGoodsModel(PxGoodsModel pxGoodsModel) {
        MtOperateResult<PxGoodsModel> result = new MtOperateResult<PxGoodsModel>();
        PxGoodsModel freshPxGoodsModel = null;
        try {
            freshPxGoodsModel = pxGoodsRepository.saveGoodsInfo(pxGoodsModel);
            result.setResult(freshPxGoodsModel);
        } catch (PxManageException e) {
            logger.warn("保存商品概要信息发生异常 pxGoodsModel=" + pxGoodsModel, e);
            result = new MtOperateResult<PxGoodsModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_SAVE_FAILD);
        }

        return result;
    }

    /**
     * 根据不同的管理目标执行管理动作
     * 
     * @param pxGoodsModel
     * @param operationType
     * @return
     */
    private MtOperateResult<PxGoodsModel> switchOperation(PxGoodsModel pxGoodsModel, PxOperationTypeEnum operationType) {

        return pxCommonMngTemplate.switchOperation(pxGoodsModel, operationType, new PxCommonCallback<PxGoodsModel>() {

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#querySingleModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxGoodsModel> querySingleModel(PxGoodsModel t) {
                return querySingleGoods(pxGoodsModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#modifyModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxGoodsModel> modifyModel(PxGoodsModel t) {
                return modifyGoodsModel(pxGoodsModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#deleteModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxGoodsModel> deleteModel(PxGoodsModel t) {
                return deleteGoodsModel(pxGoodsModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#addModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxGoodsModel> addModel(PxGoodsModel t) {
                return saveGoodsModel(pxGoodsModel);
            }

        });
    }

    /**
     * Setter method for property <tt>pxCommonMngTemplate</tt>.
     * 
     * @param pxCommonMngTemplate value to be assigned to property pxCommonMngTemplate
     */
    public void setPxCommonMngTemplate(PxCommonMngTemplate<PxGoodsModel> pxCommonMngTemplate) {
        this.pxCommonMngTemplate = pxCommonMngTemplate;
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
     * Setter method for property <tt>pxGoodsPackagesDetailRepository</tt>.
     * 
     * @param pxGoodsPackagesDetailRepository value to be assigned to property pxGoodsPackagesDetailRepository
     */
    public void setPxGoodsPackagesDetailRepository(PxGoodsPackagesDetailRepository pxGoodsPackagesDetailRepository) {
        this.pxGoodsPackagesDetailRepository = pxGoodsPackagesDetailRepository;
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
     * Setter method for property <tt>pxGoodsPackagesNoticeRepository</tt>.
     * 
     * @param pxGoodsPackagesNoticeRepository value to be assigned to property pxGoodsPackagesNoticeRepository
     */
    public void setPxGoodsPackagesNoticeRepository(PxGoodsPackagesNoticeRepository pxGoodsPackagesNoticeRepository) {
        this.pxGoodsPackagesNoticeRepository = pxGoodsPackagesNoticeRepository;
    }
}
