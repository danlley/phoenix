/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component.impl;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.enums.PxOperationTypeEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.common.util.manage.enums.PxShopStatusEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;
import com.myteay.phoenix.core.model.manage.PxShopModel;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsRepository;
import com.myteay.phoenix.core.model.manage.repository.PxShopRepository;
import com.myteay.phoenix.core.service.manage.component.PxGoodsComponent;
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
    private static final Logger              logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /** 店铺管理仓储 */
    private PxShopRepository                 pxShopRepository;

    /** 后台管理业务处理分流模板 */
    private PxCommonMngTemplate<PxShopModel> pxCommonMngTemplate;

    /** 商品摘要管理仓储 */
    private PxGoodsRepository                pxGoodsRepository;

    /** 商品摘要管理组件 */
    private PxGoodsComponent                 pxGoodsComponent;

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
            logger.warn("修改店铺信息发生异常 pxShopModel=" + pxShopModel, e);
            result = new MtOperateResult<PxShopModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SHOP_UPDATE_FAILD);
        }

        // 修改后如果店铺状态为已下线，则将店铺下所有已在线商品下架
        if (freshPxShopModel != null && freshPxShopModel.getShopStatus() == PxShopStatusEnum.PX_SHOP_EXPIRED) {
            pxGoodsComponent.shutdownGoodsByShopId(freshPxShopModel.getShopId());
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

        if (isShopOnline(pxShopModel.getShopId())) {
            logger.warn("店铺状态在线，无法删除 pxShopModel=" + pxShopModel);
            return new MtOperateResult<PxShopModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SHOP_ONLINE_DEL_ERR);
        }

        if (!isCanDeleteShop(pxShopModel.getShopId())) {
            logger.warn("店铺包含商品，无法删除 pxShopModel=" + pxShopModel);
            return new MtOperateResult<PxShopModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SHOP_HAS_GOODS_ERR);
        }

        MtOperateResult<PxShopModel> result = new MtOperateResult<PxShopModel>();
        try {
            pxShopRepository.removeShopInfo(pxShopModel);
        } catch (PxManageException e) {
            logger.warn("删除店铺信息发生异常 pxShopModel=" + pxShopModel, e);
            result = new MtOperateResult<PxShopModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SHOP_DELETE_FAILD);
        }

        return result;
    }

    /**
     * 检查当前店铺是否在线
     * 
     * @param shopId
     * @return
     */
    private boolean isShopOnline(String shopId) {
        PxShopModel freshPxShopModel = null;
        try {
            freshPxShopModel = pxShopRepository.findSingleShop(shopId);
            if (freshPxShopModel != null && freshPxShopModel.getShopStatus() == PxShopStatusEnum.PX_SHOP_ONLINE) {
                return true;
            }
        } catch (PxManageException e) {
            logger.warn("查找店铺信息发生异常 shopId=" + shopId, e);
        }
        return false;
    }

    /**
     * 检查当前店铺是否允许删除
     * 
     * @param shopId
     * @return
     */
    private boolean isCanDeleteShop(String shopId) {
        try {
            List<PxGoodsModel> list = pxGoodsRepository.findGoodsByShopId(shopId);
            if (CollectionUtils.isEmpty(list)) {
                return true;
            }
        } catch (PxManageException e) {
            logger.warn("保存店铺信息发生异常 shopId=" + shopId, e);
        }

        return false;
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
            logger.warn("查找店铺信息发生异常 pxShopModel=" + pxShopModel, e);
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
