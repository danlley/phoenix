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
import com.myteay.phoenix.core.model.manage.PxSubPackagesModel;
import com.myteay.phoenix.core.model.manage.repository.PxSubPackagesRepository;
import com.myteay.phoenix.core.service.manage.component.PxSubPackagesComponent;
import com.myteay.phoenix.core.service.manage.template.PxCommonCallback;
import com.myteay.phoenix.core.service.manage.template.PxCommonMngTemplate;

/**
 * 子套餐管理组件
 * 
 * @author min.weixm
 * @version $Id: PxSubPackagesComponentImpl.java, v 0.1 Jul 28, 2018 11:27:54 AM min.weixm Exp $
 */
public class PxSubPackagesComponentImpl implements PxSubPackagesComponent {

    /** 日志 */
    public static final Logger                      logger = Logger.getLogger(PxSubPackagesComponentImpl.class);

    /** 后台管理业务处理分流模板 */
    private PxCommonMngTemplate<PxSubPackagesModel> pxCommonMngTemplate;

    /** 子套餐仓储 */
    private PxSubPackagesRepository                 pxSubPackagesRepository;

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxSubPackagesComponent#manageSubPackages(com.myteay.phoenix.core.model.manage.PxSubPackagesModel)
     */
    @Override
    public MtOperateResult<PxSubPackagesModel> manageSubPackages(PxSubPackagesModel pxSubPackagesModel) {
        if (pxSubPackagesModel == null) {
            logger.warn("子套餐模型不可用，无法执行管理动作 pxSubPackagesModel= " + pxSubPackagesModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SUB_PKG_MODEL_INVALID);
        }

        if (pxSubPackagesModel.getOperationType() == null) {
            logger.warn("子套餐模型中的操作类型未知 pxSubPackagesModel=" + pxSubPackagesModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_TEMPLATE_OP_UNKNOW);
        }

        return switchOperation(pxSubPackagesModel, pxSubPackagesModel.getOperationType());
    }

    /**
     * 查询单条子套餐
     * 
     * @param pxSubPackagesModel
     * @return
     */
    private MtOperateResult<PxSubPackagesModel> querySingleGoods(PxSubPackagesModel pxSubPackagesModel) {
        MtOperateResult<PxSubPackagesModel> result = new MtOperateResult<PxSubPackagesModel>();
        PxSubPackagesModel freshPxSubPackagesModel = null;
        try {
            freshPxSubPackagesModel = pxSubPackagesRepository.findSingleSubPackages(pxSubPackagesModel.getSubPackagesId());
            result.setResult(freshPxSubPackagesModel);
        } catch (PxManageException e) {
            logger.warn("保存子套餐信息发生异常 pxSubPackagesModel=" + pxSubPackagesModel, e);
            result = new MtOperateResult<PxSubPackagesModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SUB_PKG_QUERY_FAILD);
        }

        return result;
    }

    /**
     * 修改子套餐信息
     * 
     * @param pxSubPackagesModel
     * @return
     */
    private MtOperateResult<PxSubPackagesModel> modifyGoodsModel(PxSubPackagesModel pxSubPackagesModel) {
        MtOperateResult<PxSubPackagesModel> result = new MtOperateResult<PxSubPackagesModel>();
        PxSubPackagesModel freshPxSubPackagesModel = null;
        try {
            freshPxSubPackagesModel = pxSubPackagesRepository.modifySubPackagesInfo(pxSubPackagesModel);
            result.setResult(freshPxSubPackagesModel);
        } catch (PxManageException e) {
            logger.warn("保存子套餐信息发生异常 pxSubPackagesModel=" + pxSubPackagesModel, e);
            result = new MtOperateResult<PxSubPackagesModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SUB_PKG_UPDATE_FAILD);
        }

        return result;
    }

    /**
     * 删除子套餐信息
     * 
     * @param pxSubPackagesModel
     * @return
     */
    private MtOperateResult<PxSubPackagesModel> deleteGoodsModel(PxSubPackagesModel pxSubPackagesModel) {
        MtOperateResult<PxSubPackagesModel> result = new MtOperateResult<PxSubPackagesModel>();
        try {
            pxSubPackagesRepository.removeSubPackagesInfo(pxSubPackagesModel);
        } catch (PxManageException e) {
            logger.warn("保存子套餐信息发生异常 pxSubPackagesModel=" + pxSubPackagesModel, e);
            result = new MtOperateResult<PxSubPackagesModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SUB_PKG_DELETE_FAILD);
        }

        return result;
    }

    /**
     * 保存子套餐信息
     * 
     * @param pxSubPackagesModel
     * @return
     */
    private MtOperateResult<PxSubPackagesModel> saveGoodsModel(PxSubPackagesModel pxSubPackagesModel) {
        MtOperateResult<PxSubPackagesModel> result = new MtOperateResult<PxSubPackagesModel>();
        PxSubPackagesModel freshPxSubPackagesModel = null;
        try {
            freshPxSubPackagesModel = pxSubPackagesRepository.saveSubPackagesInfo(pxSubPackagesModel);
            result.setResult(freshPxSubPackagesModel);
        } catch (PxManageException e) {
            logger.warn("保存子套餐信息发生异常 pxSubPackagesModel=" + pxSubPackagesModel, e);
            result = new MtOperateResult<PxSubPackagesModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SUB_PKG_SAVE_FAILD);
        }

        return result;
    }

    /**
     * 根据不同的管理目标执行管理动作
     * 
     * @param pxSubPackagesModel
     * @param operationType
     * @return
     */
    private MtOperateResult<PxSubPackagesModel> switchOperation(PxSubPackagesModel pxSubPackagesModel, PxOperationTypeEnum operationType) {

        return pxCommonMngTemplate.switchOperation(pxSubPackagesModel, operationType, new PxCommonCallback<PxSubPackagesModel>() {

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#querySingleModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxSubPackagesModel> querySingleModel(PxSubPackagesModel t) {
                return querySingleGoods(pxSubPackagesModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#modifyModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxSubPackagesModel> modifyModel(PxSubPackagesModel t) {
                return modifyGoodsModel(pxSubPackagesModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#deleteModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxSubPackagesModel> deleteModel(PxSubPackagesModel t) {
                return deleteGoodsModel(pxSubPackagesModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#addModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxSubPackagesModel> addModel(PxSubPackagesModel t) {
                return saveGoodsModel(pxSubPackagesModel);
            }

        });
    }

    /**
     * Setter method for property <tt>pxCommonMngTemplate</tt>.
     * 
     * @param pxCommonMngTemplate value to be assigned to property pxCommonMngTemplate
     */
    public void setPxCommonMngTemplate(PxCommonMngTemplate<PxSubPackagesModel> pxCommonMngTemplate) {
        this.pxCommonMngTemplate = pxCommonMngTemplate;
    }

    /**
     * Setter method for property <tt>pxSubPackagesRepository</tt>.
     * 
     * @param pxSubPackagesRepository value to be assigned to property pxSubPackagesRepository
     */
    public void setPxSubPackagesRepository(PxSubPackagesRepository pxSubPackagesRepository) {
        this.pxSubPackagesRepository = pxSubPackagesRepository;
    }

}
