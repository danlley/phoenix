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
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesSubNoticeModel;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesSubNoticeRepository;
import com.myteay.phoenix.core.service.manage.component.PxGoodsPackagesSubNoticeComponent;
import com.myteay.phoenix.core.service.manage.template.PxCommonCallback;
import com.myteay.phoenix.core.service.manage.template.PxCommonMngTemplate;

/**
 * 温馨提醒子项管理组件
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesSubNoticeComponentImpl.java, v 0.1 Aug 5, 2018 11:19:51 PM min.weixm Exp $
 */
public class PxGoodsPackagesSubNoticeComponentImpl implements PxGoodsPackagesSubNoticeComponent {

    /** 日志 */
    public static final Logger                                 logger = Logger.getLogger(PxGoodsPackagesSubNoticeComponentImpl.class);

    /** 后台管理业务处理分流模板 */
    private PxCommonMngTemplate<PxGoodsPackagesSubNoticeModel> pxCommonMngTemplate;

    /** 温馨提醒子项管理仓储 */
    private PxGoodsPackagesSubNoticeRepository                 pxGoodsPackagesSubNoticeRepository;

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxGoodsPackagesSubNoticeComponent#manageSubPackages(com.myteay.phoenix.core.model.manage.PxGoodsPackagesSubNoticeModel)
     */
    @Override
    public MtOperateResult<PxGoodsPackagesSubNoticeModel> manageSubPackages(PxGoodsPackagesSubNoticeModel pxGoodsPackagesSubNoticeModel) {
        if (pxGoodsPackagesSubNoticeModel == null) {
            logger.warn("温馨提醒子项模型不可用，无法执行管理动作 pxGoodsPackagesSubNoticeModel= " + pxGoodsPackagesSubNoticeModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_SUB_NOTICE_MODEL_INVALID);
        }

        if (pxGoodsPackagesSubNoticeModel.getOperationType() == null) {
            logger.warn("温馨提醒子项模型中的操作类型未知 pxGoodsPackagesSubNoticeModel=" + pxGoodsPackagesSubNoticeModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_TEMPLATE_OP_UNKNOW);
        }

        return switchOperation(pxGoodsPackagesSubNoticeModel, pxGoodsPackagesSubNoticeModel.getOperationType());
    }

    /**
     * 查询单条温馨提醒子项
     * 
     * @param pxGoodsPackagesSubNoticeModel
     * @return
     */
    private MtOperateResult<PxGoodsPackagesSubNoticeModel> querySingleGoods(PxGoodsPackagesSubNoticeModel pxGoodsPackagesSubNoticeModel) {
        MtOperateResult<PxGoodsPackagesSubNoticeModel> result = new MtOperateResult<PxGoodsPackagesSubNoticeModel>();
        PxGoodsPackagesSubNoticeModel freshPxGoodsPackagesSubNoticeModel = null;
        try {
            freshPxGoodsPackagesSubNoticeModel = pxGoodsPackagesSubNoticeRepository.findSinglePackagesSubNotice(pxGoodsPackagesSubNoticeModel
                .getPackagesSuNoticeId());
            result.setResult(freshPxGoodsPackagesSubNoticeModel);
        } catch (PxManageException e) {
            logger.warn("保存温馨提醒子项信息发生异常 pxGoodsPackagesSubNoticeModel=" + pxGoodsPackagesSubNoticeModel, e);
            result = new MtOperateResult<PxGoodsPackagesSubNoticeModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED,
                MtOperateExResultEnum.PX_PKG_SUB_NOTICE_QUERY_FAILD);
        }

        return result;
    }

    /**
     * 修改温馨提醒子项信息
     * 
     * @param pxGoodsPackagesSubNoticeModel
     * @return
     */
    private MtOperateResult<PxGoodsPackagesSubNoticeModel> modifyGoodsModel(PxGoodsPackagesSubNoticeModel pxGoodsPackagesSubNoticeModel) {
        MtOperateResult<PxGoodsPackagesSubNoticeModel> result = new MtOperateResult<PxGoodsPackagesSubNoticeModel>();
        PxGoodsPackagesSubNoticeModel freshPxGoodsPackagesSubNoticeModel = null;
        try {
            freshPxGoodsPackagesSubNoticeModel = pxGoodsPackagesSubNoticeRepository.modifyPackagesSubNoticeInfo(pxGoodsPackagesSubNoticeModel);
            result.setResult(freshPxGoodsPackagesSubNoticeModel);
        } catch (PxManageException e) {
            logger.warn("保存温馨提醒子项信息发生异常 pxGoodsPackagesSubNoticeModel=" + pxGoodsPackagesSubNoticeModel, e);
            result = new MtOperateResult<PxGoodsPackagesSubNoticeModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED,
                MtOperateExResultEnum.PX_PKG_SUB_NOTICE_UPDATE_FAILD);
        }

        return result;
    }

    /**
     * 删除温馨提醒子项信息
     * 
     * @param pxGoodsPackagesSubNoticeModel
     * @return
     */
    private MtOperateResult<PxGoodsPackagesSubNoticeModel> deleteGoodsModel(PxGoodsPackagesSubNoticeModel pxGoodsPackagesSubNoticeModel) {
        MtOperateResult<PxGoodsPackagesSubNoticeModel> result = new MtOperateResult<PxGoodsPackagesSubNoticeModel>();
        try {
            pxGoodsPackagesSubNoticeRepository.removePackagesSubNoticeInfo(pxGoodsPackagesSubNoticeModel);
        } catch (PxManageException e) {
            logger.warn("保存温馨提醒子项信息发生异常 pxGoodsPackagesSubNoticeModel=" + pxGoodsPackagesSubNoticeModel, e);
            result = new MtOperateResult<PxGoodsPackagesSubNoticeModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED,
                MtOperateExResultEnum.PX_PKG_SUB_NOTICE_DELETE_FAILD);
        }

        return result;
    }

    /**
     * 保存温馨提醒子项信息
     * 
     * @param pxGoodsPackagesSubNoticeModel
     * @return
     */
    private MtOperateResult<PxGoodsPackagesSubNoticeModel> saveGoodsModel(PxGoodsPackagesSubNoticeModel pxGoodsPackagesSubNoticeModel) {
        MtOperateResult<PxGoodsPackagesSubNoticeModel> result = new MtOperateResult<PxGoodsPackagesSubNoticeModel>();
        PxGoodsPackagesSubNoticeModel freshPxGoodsPackagesSubNoticeModel = null;
        try {
            freshPxGoodsPackagesSubNoticeModel = pxGoodsPackagesSubNoticeRepository.savePackagesSubNoticeInfo(pxGoodsPackagesSubNoticeModel);
            result.setResult(freshPxGoodsPackagesSubNoticeModel);
        } catch (PxManageException e) {
            logger.warn("保存温馨提醒子项信息发生异常 pxGoodsPackagesSubNoticeModel=" + pxGoodsPackagesSubNoticeModel, e);
            result = new MtOperateResult<PxGoodsPackagesSubNoticeModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED,
                MtOperateExResultEnum.PX_PKG_SUB_NOTICE_SAVE_FAILD);
        }

        return result;
    }

    /**
     * 根据不同的管理目标执行管理动作
     * 
     * @param pxGoodsPackagesSubNoticeModel
     * @param operationType
     * @return
     */
    private MtOperateResult<PxGoodsPackagesSubNoticeModel> switchOperation(PxGoodsPackagesSubNoticeModel pxGoodsPackagesSubNoticeModel,
                                                                           PxOperationTypeEnum operationType) {

        return pxCommonMngTemplate.switchOperation(pxGoodsPackagesSubNoticeModel, operationType, new PxCommonCallback<PxGoodsPackagesSubNoticeModel>() {

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#querySingleModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxGoodsPackagesSubNoticeModel> querySingleModel(PxGoodsPackagesSubNoticeModel t) {
                return querySingleGoods(pxGoodsPackagesSubNoticeModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#modifyModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxGoodsPackagesSubNoticeModel> modifyModel(PxGoodsPackagesSubNoticeModel t) {
                return modifyGoodsModel(pxGoodsPackagesSubNoticeModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#deleteModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxGoodsPackagesSubNoticeModel> deleteModel(PxGoodsPackagesSubNoticeModel t) {
                return deleteGoodsModel(pxGoodsPackagesSubNoticeModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#addModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxGoodsPackagesSubNoticeModel> addModel(PxGoodsPackagesSubNoticeModel t) {
                return saveGoodsModel(pxGoodsPackagesSubNoticeModel);
            }

        });
    }

    /**
     * Setter method for property <tt>pxCommonMngTemplate</tt>.
     * 
     * @param pxCommonMngTemplate value to be assigned to property pxCommonMngTemplate
     */
    public void setPxCommonMngTemplate(PxCommonMngTemplate<PxGoodsPackagesSubNoticeModel> pxCommonMngTemplate) {
        this.pxCommonMngTemplate = pxCommonMngTemplate;
    }

    /**
     * Setter method for property <tt>pxGoodsPackagesSubNoticeRepository</tt>.
     * 
     * @param pxGoodsPackagesSubNoticeRepository value to be assigned to property pxGoodsPackagesSubNoticeRepository
     */
    public void setPxGoodsPackagesSubNoticeRepository(PxGoodsPackagesSubNoticeRepository pxGoodsPackagesSubNoticeRepository) {
        this.pxGoodsPackagesSubNoticeRepository = pxGoodsPackagesSubNoticeRepository;
    }

}
