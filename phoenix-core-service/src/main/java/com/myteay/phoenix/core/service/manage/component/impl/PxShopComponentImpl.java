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
import com.myteay.phoenix.core.model.manage.PxShopModel;
import com.myteay.phoenix.core.model.manage.repository.PxShopRepository;
import com.myteay.phoenix.core.service.manage.component.PxShopComponent;
import com.myteay.phoenix.core.service.manage.template.PxCommonCallback;
import com.myteay.phoenix.core.service.manage.template.PxCommonMngTemplate;

/**
 * 店铺管理组件
 * 
 * @author min.weixm
 * @version $Id: PxShopComponentImpl.java, v 0.1 Jul 25, 2018 9:25:01 AM min.weixm Exp $
 */
public class PxShopComponentImpl implements PxShopComponent {

    /** 日志 */
    public static final Logger               logger = Logger.getLogger(PxShopComponentImpl.class);

    /** 店铺管理仓储 */
    private PxShopRepository                 pxShopRepository;

    /** 后台管理业务处理分流模板 */
    private PxCommonMngTemplate<PxShopModel> pxCommonMngTemplate;

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxShopComponent#manageShop(com.myteay.phoenix.core.model.manage.PxShopModel)
     */
    public MtOperateResult<PxShopModel> manageShop(PxShopModel pxShopModel) {

        if (pxShopModel == null) {
            logger.warn("店铺模型不可用，无法执行管理动作pxShopModel= " + pxShopModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SHOP_MODEL_INVALID);
        }

        if (pxShopModel.getOperationType() == null) {
            logger.warn("店铺模型中的操作类型未知pxShopModel=" + pxShopModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SHOP_OP_UNKNOW);
        }

        return switchOperation(pxShopModel, pxShopModel.getOperationType());
    }

    /**
     * 根据不同的管理目标执行管理动作
     * 
     * @param pxShopModel
     * @param operationType
     * @return
     */
    private MtOperateResult<PxShopModel> switchOperation(PxShopModel pxShopModel, PxOperationTypeEnum operationType) {

        return pxCommonMngTemplate.switchOperation(pxShopModel, operationType, new PxCommonCallback<PxShopModel>() {

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#querySingleModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxShopModel> querySingleModel(PxShopModel t) {
                return querySingleShop(pxShopModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#modifyModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxShopModel> modifyModel(PxShopModel t) {
                return modifyShopModel(pxShopModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#deleteModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxShopModel> deleteModel(PxShopModel t) {
                return deleteShopModel(pxShopModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#addModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxShopModel> addModel(PxShopModel t) {
                return saveShopModel(pxShopModel);
            }
        });
    }

    /**
     * 修改店铺
     * 
     * @param pxShopModel
     * @return
     */
    private MtOperateResult<PxShopModel> modifyShopModel(PxShopModel pxShopModel) {
        MtOperateResult<PxShopModel> result = new MtOperateResult<PxShopModel>();
        PxShopModel freshPxShopModel = null;
        try {
            freshPxShopModel = pxShopRepository.modifyShopInfo(pxShopModel);
            result.setResult(freshPxShopModel);
        } catch (PxManageException e) {
            logger.warn("保存店铺信息发生异常 pxShopModel=" + pxShopModel, e);
            result = new MtOperateResult<PxShopModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SHOP_UPDATE_FAILD);
        }

        return result;
    }

    /**
     * 删除店铺
     * 
     * @param pxShopModel
     * @return
     */
    private MtOperateResult<PxShopModel> deleteShopModel(PxShopModel pxShopModel) {
        MtOperateResult<PxShopModel> result = new MtOperateResult<PxShopModel>();
        try {
            pxShopRepository.removeShopInfo(pxShopModel);
        } catch (PxManageException e) {
            logger.warn("保存店铺信息发生异常 pxShopModel=" + pxShopModel, e);
            result = new MtOperateResult<PxShopModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SHOP_DELETE_FAILD);
        }

        return result;
    }

    /**
     * 保存店铺模型
     * 
     * @param pxShopModel
     * @return
     */
    private MtOperateResult<PxShopModel> saveShopModel(PxShopModel pxShopModel) {
        MtOperateResult<PxShopModel> result = new MtOperateResult<PxShopModel>();
        PxShopModel freshPxShopModel = null;
        try {
            freshPxShopModel = pxShopRepository.saveShopInfo(pxShopModel);
            result.setResult(freshPxShopModel);
        } catch (PxManageException e) {
            logger.warn("保存店铺信息发生异常 pxShopModel=" + pxShopModel, e);
            result = new MtOperateResult<PxShopModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SHOP_SAVE_FAILD);
        }

        return result;
    }

    /**
     * 查询单条店铺信息
     * 
     * @param pxShopModel
     * @return
     */
    private MtOperateResult<PxShopModel> querySingleShop(PxShopModel pxShopModel) {
        MtOperateResult<PxShopModel> result = new MtOperateResult<PxShopModel>();
        PxShopModel freshPxShopModel = null;
        try {
            freshPxShopModel = pxShopRepository.findSingleShop(pxShopModel.getShopId());
            result.setResult(freshPxShopModel);
        } catch (PxManageException e) {
            logger.warn("保存店铺信息发生异常 pxShopModel=" + pxShopModel, e);
            result = new MtOperateResult<PxShopModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SHOP_QUERY_FAILD);
        }

        return result;
    }

    /**
     * Setter method for property <tt>pxCommonMngTemplate</tt>.
     * 
     * @param pxCommonMngTemplate value to be assigned to property pxCommonMngTemplate
     */
    public void setPxCommonMngTemplate(PxCommonMngTemplate<PxShopModel> pxCommonMngTemplate) {
        this.pxCommonMngTemplate = pxCommonMngTemplate;
    }

    /**
     * Setter method for property <tt>pxShopRepository</tt>.
     * 
     * @param pxShopRepository value to be assigned to property pxShopRepository
     */
    public void setPxShopRepository(PxShopRepository pxShopRepository) {
        this.pxShopRepository = pxShopRepository;
    }

}