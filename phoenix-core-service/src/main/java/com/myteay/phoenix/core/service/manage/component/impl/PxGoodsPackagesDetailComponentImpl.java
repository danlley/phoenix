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
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel;
import com.myteay.phoenix.core.model.manage.PxSubPackagesModel;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesDetailRepository;
import com.myteay.phoenix.core.model.manage.repository.PxSubPackagesRepository;
import com.myteay.phoenix.core.service.manage.component.PxGoodsComponent;
import com.myteay.phoenix.core.service.manage.component.PxGoodsPackagesDetailComponent;
import com.myteay.phoenix.core.service.manage.template.PxCommonCallback;
import com.myteay.phoenix.core.service.manage.template.PxCommonMngTemplate;
import com.myteay.phoenix.core.service.utils.PxMngUtil;

/**
 * 套餐包管理组件
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesDetailComponentImpl.java, v 0.1 Jul 27, 2018 8:58:18 PM min.weixm Exp $
 */
public class PxGoodsPackagesDetailComponentImpl implements PxGoodsPackagesDetailComponent {

    /** 日志 */
    public static final Logger                              logger = Logger.getLogger(PxGoodsPackagesDetailComponentImpl.class);

    /** 后台管理业务处理分流模板 */
    private PxCommonMngTemplate<PxGoodsPackagesDetailModel> pxCommonMngTemplate;

    /** 套餐包仓储 */
    private PxGoodsPackagesDetailRepository                 pxGoodsPackagesDetailRepository;

    /** 子套餐仓储 */
    private PxSubPackagesRepository                         pxSubPackagesRepository;

    /** 商品管理组件 */
    private PxGoodsComponent                                pxGoodsComponent;

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxGoodsPackagesDetailComponent#manageGoodsPackagesDetail(com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel)
     */
    @Override
    public MtOperateResult<PxGoodsPackagesDetailModel> manageGoodsPackagesDetail(PxGoodsPackagesDetailModel pxGoodsPackagesDetailModel) {

        if (pxGoodsPackagesDetailModel == null) {
            logger.warn("套餐包模型不可用，无法执行管理动作 pxGoodsPackagesDetailModel= " + pxGoodsPackagesDetailModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_DETAIL_MODEL_INVALID);
        }

        if (pxGoodsPackagesDetailModel.getOperationType() == null) {
            logger.warn("套餐包模型中的操作类型未知 pxGoodsPackagesDetailModel=" + pxGoodsPackagesDetailModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_TEMPLATE_OP_UNKNOW);
        }

        return switchOperation(pxGoodsPackagesDetailModel, pxGoodsPackagesDetailModel.getOperationType());
    }

    /**
     * 查询单条套餐包
     * 
     * @param pxGoodsPackagesDetailModel
     * @return
     */
    private MtOperateResult<PxGoodsPackagesDetailModel> querySingleGoods(PxGoodsPackagesDetailModel pxGoodsPackagesDetailModel) {
        MtOperateResult<PxGoodsPackagesDetailModel> result = new MtOperateResult<PxGoodsPackagesDetailModel>();

        PxGoodsPackagesDetailModel freshPxGoodsPackagesDetailModel = null;
        try {
            freshPxGoodsPackagesDetailModel = pxGoodsPackagesDetailRepository.findSingleGoodsPackagesDetail(pxGoodsPackagesDetailModel.getPackagesDetailId());
            result.setResult(freshPxGoodsPackagesDetailModel);
        } catch (PxManageException e) {
            logger.warn("保存套餐包信息发生异常 pxGoodsPackagesDetailModel=" + pxGoodsPackagesDetailModel, e);
            result = new MtOperateResult<PxGoodsPackagesDetailModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_DETAIL_QUERY_FAILD);
        }

        return result;
    }

    /**
     * 修改套餐包信息
     * 
     * @param pxGoodsPackagesDetailModel
     * @return
     */
    private MtOperateResult<PxGoodsPackagesDetailModel> modifyGoodsModel(PxGoodsPackagesDetailModel pxGoodsPackagesDetailModel) {
        MtOperateResult<PxGoodsPackagesDetailModel> result = new MtOperateResult<PxGoodsPackagesDetailModel>();

        if (!PxMngUtil.isCanDoOperation(pxGoodsComponent.queryGoodsModelByGoodsId(pxGoodsPackagesDetailModel.getGoodsId()))) {
            logger.warn("当前商品不满足追加套餐包条件，请检查商品是否已发布或已下线 pxGoodsPackagesDetailModel=" + pxGoodsPackagesDetailModel);
            return new MtOperateResult<PxGoodsPackagesDetailModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_ONLINE_MODIFY_ERR);
        }

        PxGoodsPackagesDetailModel freshPxGoodsPackagesDetailModel = null;
        try {
            freshPxGoodsPackagesDetailModel = pxGoodsPackagesDetailRepository.modifyGoodsPackagesDetailInfo(pxGoodsPackagesDetailModel);
            result.setResult(freshPxGoodsPackagesDetailModel);
        } catch (PxManageException e) {
            logger.warn("保存套餐包信息发生异常 pxGoodsPackagesDetailModel=" + pxGoodsPackagesDetailModel, e);
            result = new MtOperateResult<PxGoodsPackagesDetailModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_DETAIL_UPDATE_FAILD);
        }

        return result;
    }

    /**
     * 删除套餐包信息
     * 
     * @param pxGoodsPackagesDetailModel
     * @return
     */
    private MtOperateResult<PxGoodsPackagesDetailModel> deleteGoodsModel(PxGoodsPackagesDetailModel pxGoodsPackagesDetailModel) {

        try {
            List<PxSubPackagesModel> list = pxSubPackagesRepository.findSubPackagesByGoodsId(pxGoodsPackagesDetailModel.getPackagesDetailId());
            if (!CollectionUtils.isEmpty(list)) {
                logger.warn("商品套餐包含子项，无法删除 pxGoodsPackagesDetailModel=" + pxGoodsPackagesDetailModel);
                return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PACKAGES_DEL_ERR);
            }
        } catch (PxManageException e1) {
            logger.warn("检查套餐子项出错，无法删除套餐  pxGoodsPackagesDetailModel=" + pxGoodsPackagesDetailModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PACKAGES_DEL_VALIDATE_ERR);
        }

        MtOperateResult<PxGoodsPackagesDetailModel> result = new MtOperateResult<PxGoodsPackagesDetailModel>();
        try {
            pxGoodsPackagesDetailRepository.removeGoodsPackagesDetailInfo(pxGoodsPackagesDetailModel);
        } catch (PxManageException e) {
            logger.warn("保存套餐包信息发生异常 pxGoodsPackagesDetailModel=" + pxGoodsPackagesDetailModel, e);
            result = new MtOperateResult<PxGoodsPackagesDetailModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_DETAIL_DELETE_FAILD);
        }

        return result;
    }

    /**
     * 保存套餐包信息
     * 
     * @param pxGoodsPackagesDetailModel
     * @return
     */
    private MtOperateResult<PxGoodsPackagesDetailModel> saveGoodsModel(PxGoodsPackagesDetailModel pxGoodsPackagesDetailModel) {
        MtOperateResult<PxGoodsPackagesDetailModel> result = new MtOperateResult<PxGoodsPackagesDetailModel>();

        if (!PxMngUtil.isCanDoOperation(pxGoodsComponent.queryGoodsModelByGoodsId(pxGoodsPackagesDetailModel.getGoodsId()))) {
            logger.warn("当前商品不满足修改套餐包条件，请检查商品是否已发布或已下线 pxGoodsPackagesDetailModel=" + pxGoodsPackagesDetailModel);
            return new MtOperateResult<PxGoodsPackagesDetailModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_ONLINE_ADD_ERR);
        }

        PxGoodsPackagesDetailModel freshPxGoodsPackagesDetailModel = null;
        try {
            freshPxGoodsPackagesDetailModel = pxGoodsPackagesDetailRepository.saveGoodsPackagesDetailInfo(pxGoodsPackagesDetailModel);
            result.setResult(freshPxGoodsPackagesDetailModel);
        } catch (PxManageException e) {
            logger.warn("保存套餐包信息发生异常 pxGoodsPackagesDetailModel=" + pxGoodsPackagesDetailModel, e);
            return new MtOperateResult<PxGoodsPackagesDetailModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_DETAIL_SAVE_FAILD);
        }

        return result;
    }

    /**
     * 根据不同的管理目标执行管理动作
     * 
     * @param pxGoodsPackagesDetailModel
     * @param operationType
     * @return
     */
    private MtOperateResult<PxGoodsPackagesDetailModel> switchOperation(PxGoodsPackagesDetailModel pxGoodsPackagesDetailModel,
                                                                        PxOperationTypeEnum operationType) {

        return pxCommonMngTemplate.switchOperation(pxGoodsPackagesDetailModel, operationType, new PxCommonCallback<PxGoodsPackagesDetailModel>() {

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#querySingleModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxGoodsPackagesDetailModel> querySingleModel(PxGoodsPackagesDetailModel t) {
                return querySingleGoods(pxGoodsPackagesDetailModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#modifyModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxGoodsPackagesDetailModel> modifyModel(PxGoodsPackagesDetailModel t) {
                return modifyGoodsModel(pxGoodsPackagesDetailModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#deleteModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxGoodsPackagesDetailModel> deleteModel(PxGoodsPackagesDetailModel t) {
                return deleteGoodsModel(pxGoodsPackagesDetailModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#addModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<PxGoodsPackagesDetailModel> addModel(PxGoodsPackagesDetailModel t) {
                return saveGoodsModel(pxGoodsPackagesDetailModel);
            }

        });
    }

    /**
     * Setter method for property <tt>pxCommonMngTemplate</tt>.
     * 
     * @param pxCommonMngTemplate value to be assigned to property pxCommonMngTemplate
     */
    public void setPxCommonMngTemplate(PxCommonMngTemplate<PxGoodsPackagesDetailModel> pxCommonMngTemplate) {
        this.pxCommonMngTemplate = pxCommonMngTemplate;
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
     * Setter method for property <tt>pxSubPackagesRepository</tt>.
     * 
     * @param pxSubPackagesRepository value to be assigned to property pxSubPackagesRepository
     */
    public void setPxSubPackagesRepository(PxSubPackagesRepository pxSubPackagesRepository) {
        this.pxSubPackagesRepository = pxSubPackagesRepository;
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
