/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component.impl;

import org.apache.log4j.Logger;

import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.enums.PxOperationTypeEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;
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
     * 修改商品概要信息
     * 
     * @param pxGoodsModel
     * @return
     */
    private MtOperateResult<PxGoodsModel> modifyGoodsModel(PxGoodsModel pxGoodsModel) {
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
     * 删除商品概要信息
     * 
     * @param pxGoodsModel
     * @return
     */
    private MtOperateResult<PxGoodsModel> deleteGoodsModel(PxGoodsModel pxGoodsModel) {
        MtOperateResult<PxGoodsModel> result = new MtOperateResult<PxGoodsModel>();
        try {
            pxGoodsRepository.removeGoodsInfo(pxGoodsModel);
        } catch (PxManageException e) {
            logger.warn("保存商品概要信息发生异常 pxGoodsModel=" + pxGoodsModel, e);
            result = new MtOperateResult<PxGoodsModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_DELETE_FAILD);
        }

        return result;
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

}
