/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component.impl;

import org.apache.commons.lang.StringUtils;

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
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel;
import com.myteay.phoenix.core.model.manage.PxSubPackagesModel;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesDetailRepository;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsRepository;
import com.myteay.phoenix.core.model.manage.repository.PxSubPackagesRepository;
import com.myteay.phoenix.core.service.manage.component.PxGoodsComponent;
import com.myteay.phoenix.core.service.manage.component.PxSubPackagesComponent;
import com.myteay.phoenix.core.service.manage.template.PxCommonCallback;
import com.myteay.phoenix.core.service.manage.template.PxCommonMngTemplate;
import com.myteay.phoenix.core.service.utils.PxMngUtil;

/**
 * 子套餐管理组件
 * 
 * @author min.weixm
 * @version $Id: PxSubPackagesComponentImpl.java, v 0.1 Jul 28, 2018 11:27:54 AM min.weixm Exp $
 */
public class PxSubPackagesComponentImpl implements PxSubPackagesComponent {

    /** 日志 */
    private static final Logger                     logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /** 后台管理业务处理分流模板 */
    private PxCommonMngTemplate<PxSubPackagesModel> pxCommonMngTemplate;

    /** 子套餐仓储 */
    private PxSubPackagesRepository                 pxSubPackagesRepository;

    /** 套餐包仓储 */
    private PxGoodsPackagesDetailRepository         pxGoodsPackagesDetailRepository;

    /** 商品概要管理仓储 */
    private PxGoodsRepository                       pxGoodsRepository;

    /** 商品管理组件 */
    private PxGoodsComponent                        pxGoodsComponent;

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
        if (!isCanDelete(pxSubPackagesModel)) {
            logger.warn("子套餐对应的商品状态为已发布或已下架，不允许删除 pxSubPackagesModel=" + pxSubPackagesModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SUB_PKGS_DEL_VALIDATE_ERR);
        }

        MtOperateResult<PxSubPackagesModel> result = new MtOperateResult<PxSubPackagesModel>();
        try {
            pxSubPackagesRepository.removeSubPackagesInfo(pxSubPackagesModel);
        } catch (PxManageException e) {
            logger.warn("删除子套餐信息发生异常 pxSubPackagesModel=" + pxSubPackagesModel, e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SUB_PKG_DELETE_FAILD);
        }

        return result;
    }

    /**
     * 判断当前子套餐是否允许删除
     * 
     * @param pxSubPackagesModel
     * @return
     */
    private boolean isCanDelete(PxSubPackagesModel pxSubPackagesModel) {
        PxGoodsModel goodsModel = null;
        try {
            goodsModel = queryGoodsBySubPackages(pxSubPackagesModel);
        } catch (PxManageException e) {
            logger.warn("通过子套餐查询对应的商品信息失败 pxSubPackagesModel=" + pxSubPackagesModel, e);
            return false;
        }

        if (goodsModel == null) {
            return false;
        }

        if (goodsModel.getGoodsStatus() == null || goodsModel.getGoodsStatus() == PxGoodsStatusEnum.PX_GOODS_DRAFT) {
            return true;
        }

        return false;
    }

    /**
     * 通过子套餐查询对应的商品信息
     * 
     * @param pxSubPackagesModel
     * @return
     * @throws PxManageException
     */
    private PxGoodsModel queryGoodsBySubPackages(PxSubPackagesModel pxSubPackagesModel) throws PxManageException {
        String packageDetailId = pxSubPackagesModel.getPackagesDetailId();
        if (StringUtils.isBlank(packageDetailId)) {
            packageDetailId = pxSubPackagesRepository.findSingleSubPackages(pxSubPackagesModel.getSubPackagesId()).getPackagesDetailId();
        }
        PxGoodsPackagesDetailModel pxGoodsPackagesDetailModel = pxGoodsPackagesDetailRepository.findSingleGoodsPackagesDetail(packageDetailId);
        if (pxGoodsPackagesDetailModel == null || StringUtils.isBlank(pxGoodsPackagesDetailModel.getGoodsId())) {
            logger.warn("当前子套餐无对应的套餐包，无法找到对应商品pxSubPackagesModel=" + pxSubPackagesModel);
            return null;
        }

        return pxGoodsRepository.findSingleGoods(pxGoodsPackagesDetailModel.getGoodsId());
    }

    /**
     * 保存子套餐信息
     * 
     * @param pxSubPackagesModel
     * @return
     */
    private MtOperateResult<PxSubPackagesModel> saveGoodsModel(PxSubPackagesModel pxSubPackagesModel) {
        MtOperateResult<PxSubPackagesModel> result = new MtOperateResult<PxSubPackagesModel>();

        if (!PxMngUtil.isCanDoOperation(pxGoodsComponent.queryGoodsModelByGoodsId(queryGoodsIdBySubPackageModel(pxSubPackagesModel)))) {
            logger.warn("当前商品不满足追加子套餐条件，请检查商品是否已发布或已下线 pxSubPackagesModel=" + pxSubPackagesModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SUBPKG_ONLINE_ADD_ERR);
        }

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
     * 查找商品ID
     * 
     * @param pxSubPackagesModel
     * @return
     */
    private String queryGoodsIdBySubPackageModel(PxSubPackagesModel pxSubPackagesModel) {
        PxGoodsModel goodsModel = null;
        try {
            goodsModel = queryGoodsBySubPackages(pxSubPackagesModel);
        } catch (PxManageException e) {
            logger.warn("通过子套餐查询对应的商品信息失败 pxSubPackagesModel=" + pxSubPackagesModel, e);
        }

        if (goodsModel == null) {
            return null;
        }

        return goodsModel.getGoodsId();
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

    /**
     * Setter method for property <tt>pxGoodsPackagesDetailRepository</tt>.
     * 
     * @param pxGoodsPackagesDetailRepository value to be assigned to property pxGoodsPackagesDetailRepository
     */
    public void setPxGoodsPackagesDetailRepository(PxGoodsPackagesDetailRepository pxGoodsPackagesDetailRepository) {
        this.pxGoodsPackagesDetailRepository = pxGoodsPackagesDetailRepository;
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
