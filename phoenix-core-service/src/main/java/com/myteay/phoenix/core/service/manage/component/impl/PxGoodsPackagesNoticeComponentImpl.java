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
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesNoticeModel;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesNoticeRepository;
import com.myteay.phoenix.core.service.manage.component.PxGoodsPackagesNoticeComponent;
import com.myteay.phoenix.core.service.manage.template.PxCommonCallback;
import com.myteay.phoenix.core.service.manage.template.PxCommonMngTemplate;

/**
 * 温馨提醒摘要管理组件
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesNoticeComponentImpl.java, v 0.1 Aug 5, 2018 9:24:05 PM min.weixm Exp $
 */
public class PxGoodsPackagesNoticeComponentImpl implements PxGoodsPackagesNoticeComponent {

    /** 日志 */
    public static final Logger                              logger = Logger.getLogger(PxGoodsPackagesNoticeComponentImpl.class);

    /** 后台管理业务处理分流模板 */
    private PxCommonMngTemplate<PxGoodsPackagesNoticeModel> pxCommonMngTemplate;

    /** 温馨提醒摘要管理仓储 */
    private PxGoodsPackagesNoticeRepository                 pxGoodsPackagesNoticeRepository;

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxGoodsPackagesNoticeComponent#manageGoodsPackagesDetail(com.myteay.phoenix.core.model.manage.PxGoodsPackagesNoticeModel)
     */
    @Override
    public MtOperateResult<PxGoodsPackagesNoticeModel> manageGoodsPackagesDetail(PxGoodsPackagesNoticeModel pxGoodsPackagesNoticeModel) {
        if (pxGoodsPackagesNoticeModel == null) {
            logger.warn("温馨提醒摘要模型不可用，无法执行管理动作 pxGoodsPackagesNoticeModel= " + pxGoodsPackagesNoticeModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_NOTICE_MODEL_INVALID);
        }

        if (pxGoodsPackagesNoticeModel.getOperationType() == null) {
            logger.warn("温馨提醒摘要模型中的操作类型未知 pxGoodsPackagesNoticeModel=" + pxGoodsPackagesNoticeModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_TEMPLATE_OP_UNKNOW);
        }

        return switchOperation(pxGoodsPackagesNoticeModel, pxGoodsPackagesNoticeModel.getOperationType());
    }

    /**
     * 查询单条温馨提醒摘要
     * 
     * @param pxGoodsPackagesNoticeModel
     * @return
     */
    private MtOperateResult<PxGoodsPackagesNoticeModel> querySingleGoods(PxGoodsPackagesNoticeModel pxGoodsPackagesNoticeModel) {
        MtOperateResult<PxGoodsPackagesNoticeModel> result = new MtOperateResult<PxGoodsPackagesNoticeModel>();
        PxGoodsPackagesNoticeModel freshPxGoodsPackagesNoticeModel = null;
        try {
            freshPxGoodsPackagesNoticeModel = pxGoodsPackagesNoticeRepository.findSingleGoodsPackagesNotice(pxGoodsPackagesNoticeModel.getPackagesNoticeId());
            result.setResult(freshPxGoodsPackagesNoticeModel);
        } catch (PxManageException e) {
            logger.warn("保存温馨提醒摘要信息发生异常 pxGoodsPackagesNoticeModel=" + pxGoodsPackagesNoticeModel, e);
            result = new MtOperateResult<PxGoodsPackagesNoticeModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_NOTICE_QUERY_FAILD);
        }

        return result;
    }

    /**
     * 修改温馨提醒摘要信息
     * 
     * @param pxGoodsPackagesNoticeModel
     * @return
     */
    private MtOperateResult<PxGoodsPackagesNoticeModel> modifyGoodsModel(PxGoodsPackagesNoticeModel pxGoodsPackagesNoticeModel) {
        MtOperateResult<PxGoodsPackagesNoticeModel> result = new MtOperateResult<PxGoodsPackagesNoticeModel>();
        PxGoodsPackagesNoticeModel freshPxGoodsPackagesNoticeModel = null;
        try {
            freshPxGoodsPackagesNoticeModel = pxGoodsPackagesNoticeRepository.modifyGoodsPackagesNoticeInfo(pxGoodsPackagesNoticeModel);
            result.setResult(freshPxGoodsPackagesNoticeModel);
        } catch (PxManageException e) {
            logger.warn("保存温馨提醒摘要信息发生异常 pxGoodsPackagesNoticeModel=" + pxGoodsPackagesNoticeModel, e);
            result = new MtOperateResult<PxGoodsPackagesNoticeModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_NOTICE_UPDATE_FAILD);
        }

        return result;
    }

    /**
     * 删除温馨提醒摘要信息
     * 
     * @param pxGoodsPackagesNoticeModel
     * @return
     */
    private MtOperateResult<PxGoodsPackagesNoticeModel> deleteGoodsModel(PxGoodsPackagesNoticeModel pxGoodsPackagesNoticeModel) {
        MtOperateResult<PxGoodsPackagesNoticeModel> result = new MtOperateResult<PxGoodsPackagesNoticeModel>();
        try {
            pxGoodsPackagesNoticeRepository.removeGoodsPackagesNoticeInfo(pxGoodsPackagesNoticeModel);
        } catch (PxManageException e) {
            logger.warn("保存温馨提醒摘要信息发生异常 pxGoodsPackagesNoticeModel=" + pxGoodsPackagesNoticeModel, e);
            result = new MtOperateResult<PxGoodsPackagesNoticeModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_NOTICE_DELETE_FAILD);
        }

        return result;
    }

    /**
     * 保存温馨提醒摘要信息
     * 
     * @param pxGoodsPackagesNoticeModel
     * @return
     */
    private MtOperateResult<PxGoodsPackagesNoticeModel> saveGoodsModel(PxGoodsPackagesNoticeModel pxGoodsPackagesNoticeModel) {
        MtOperateResult<PxGoodsPackagesNoticeModel> result = new MtOperateResult<PxGoodsPackagesNoticeModel>();
        PxGoodsPackagesNoticeModel freshPxGoodsPackagesNoticeModel = null;
        try {
            freshPxGoodsPackagesNoticeModel = pxGoodsPackagesNoticeRepository.saveGoodsPackagesNoticeInfo(pxGoodsPackagesNoticeModel);
            result.setResult(freshPxGoodsPackagesNoticeModel);
        } catch (PxManageException e) {
            logger.warn("保存温馨提醒摘要信息发生异常 pxGoodsPackagesNoticeModel=" + pxGoodsPackagesNoticeModel, e);
            result = new MtOperateResult<PxGoodsPackagesNoticeModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_NOTICE_SAVE_FAILD);
        }

        return result;
    }

    /**
     * 根据不同的管理目标执行管理动作
     * 
     * @param pxGoodsPackagesNoticeModel
     * @param operationType
     * @return
     */
    private MtOperateResult<PxGoodsPackagesNoticeModel> switchOperation(PxGoodsPackagesNoticeModel pxGoodsPackagesNoticeModel,
                                                                        PxOperationTypeEnum operationType) {

        return pxCommonMngTemplate.switchOperation(pxGoodsPackagesNoticeModel, operationType, new PxCommonCallback<PxGoodsPackagesNoticeModel>() {

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#querySingleModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxGoodsPackagesNoticeModel> querySingleModel(PxGoodsPackagesNoticeModel t) {
                return querySingleGoods(pxGoodsPackagesNoticeModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#modifyModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxGoodsPackagesNoticeModel> modifyModel(PxGoodsPackagesNoticeModel t) {
                return modifyGoodsModel(pxGoodsPackagesNoticeModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#deleteModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxGoodsPackagesNoticeModel> deleteModel(PxGoodsPackagesNoticeModel t) {
                return deleteGoodsModel(pxGoodsPackagesNoticeModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#addModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxGoodsPackagesNoticeModel> addModel(PxGoodsPackagesNoticeModel t) {
                return saveGoodsModel(pxGoodsPackagesNoticeModel);
            }

        });
    }

    /**
     * Setter method for property <tt>pxCommonMngTemplate</tt>.
     * 
     * @param pxCommonMngTemplate value to be assigned to property pxCommonMngTemplate
     */
    public void setPxCommonMngTemplate(PxCommonMngTemplate<PxGoodsPackagesNoticeModel> pxCommonMngTemplate) {
        this.pxCommonMngTemplate = pxCommonMngTemplate;
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
