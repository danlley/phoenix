/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.component.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.myteay.common.async.event.EventPublishService;
import com.myteay.common.async.event.MtEvent;
import com.myteay.phoenix.common.util.camp.enums.CampPrizeStatusEnum;
import com.myteay.phoenix.common.util.camp.enums.CampStatusEnum;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.enums.PxEventTopicEnum;
import com.myteay.phoenix.common.util.enums.PxOperationTypeEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampBaseModel;
import com.myteay.phoenix.core.model.camp.CampPrizeModel;
import com.myteay.phoenix.core.model.camp.CampPrizeRefGoodsModel;
import com.myteay.phoenix.core.model.camp.repository.CampShopBaseRepository;
import com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRefGoodsRepository;
import com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRepository;
import com.myteay.phoenix.core.service.camp.component.CampShopPrizeComponent;
import com.myteay.phoenix.core.service.manage.template.PxCommonCallback;
import com.myteay.phoenix.core.service.manage.template.PxCommonMngTemplate;

/**
 * 店内营销活动奖品组件
 * 
 * @author danlley
 * @version $Id: CampShopPrizeComponentImpl.java, v 0.1 Dec 17, 2018 11:06:09 PM danlley Exp $
 */
public class CampShopPrizeComponentImpl implements CampShopPrizeComponent {

    /** 日志 */
    public static final Logger                  logger = Logger.getLogger(CampShopPrizeComponentImpl.class);

    /** 针对单个店铺店内消费到场营销活动操作仓储 */
    private CampShopPrizeRepository             campShopPrizeRepository;

    /** 店内消费营销活动奖品关联商品仓储 */
    private CampShopPrizeRefGoodsRepository     campShopPrizeRefGoodsRepository;

    /** 针对单个店铺店内消费到场营销活动操作仓储 */
    private CampShopBaseRepository              campShopBaseRepository;

    /** 后台管理业务处理分流模板 */
    private PxCommonMngTemplate<CampPrizeModel> pxCommonMngTemplate;

    /** 事件发送组件 */
    private EventPublishService<String>         eventPublishService;

    /** 
     * @see com.myteay.phoenix.core.service.camp.component.CampShopPrizeComponent#manageCampPrize(com.myteay.phoenix.core.model.camp.CampPrizeModel)
     */
    @Override
    public MtOperateResult<CampPrizeModel> manageCampPrize(CampPrizeModel campPrizeModel) {
        if (campPrizeModel == null) {
            logger.warn("店内营销活动信息模型不可用，无法执行管理动作 campPrizeModel= " + campPrizeModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_BASE_MODEL_INVALID);
        }

        if (campPrizeModel.getOperationType() == null) {
            logger.warn("店内营销活动信息模型中的操作类型未知 campPrizeModel=" + campPrizeModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_TEMPLATE_OP_UNKNOW);
        }

        return switchOperation(campPrizeModel, campPrizeModel.getOperationType());
    }

    /** 
     * @see com.myteay.phoenix.core.service.camp.component.CampShopPrizeComponent#modifyCampPrizeModel(com.myteay.phoenix.core.model.camp.CampPrizeModel)
     */
    @Override
    public MtOperateResult<CampPrizeModel> modifyCampPrizeModel(CampPrizeModel campPrizeModel) {
        MtOperateResult<CampPrizeModel> result = new MtOperateResult<CampPrizeModel>();

        CampPrizeModel freshCampPrizeModel = null;
        try {

            if (!validatePrizeBeforeUpdate(campPrizeModel)) {
                logger.warn("奖品状态变更前的关联性检查未通过 campPrizeModel=" + campPrizeModel);
                return new MtOperateResult<CampPrizeModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_PRIZE_UPDATE_REF_ERR);
            }

            freshCampPrizeModel = campShopPrizeRepository.modifyCampPrizeInfo(campPrizeModel);
            result.setResult(freshCampPrizeModel);
        } catch (PxManageException e) {
            logger.warn("修改店内营销活动信息信息发生异常 campPrizeModel=" + campPrizeModel, e);
            result = new MtOperateResult<CampPrizeModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_BASE_UPDATE_FAILD);
        }

        if (freshCampPrizeModel != null && (freshCampPrizeModel.getPrizeStatus() == CampPrizeStatusEnum.CAMP_PRIZE_EXPIRED
                                            || freshCampPrizeModel.getPrizeStatus() == CampPrizeStatusEnum.CAMP_PRIZE_OFFLINE
                                            || freshCampPrizeModel.getPrizeStatus() == CampPrizeStatusEnum.CAMP_PRIZE_ONLINE)) {
            MtEvent<CampPrizeModel> event = new MtEvent<CampPrizeModel>(PxEventTopicEnum.PX_CAMP_PRIZE_STATUS_CHANGED.getValue(), freshCampPrizeModel);
            //            try {
            //                eventPublishService.publishEvent(event);
            //            } catch (MtEventException e) {
            //                logger.warn("商品状态变更后，发送缓存刷新请求发生异常 " + e.getMessage(), e);
            //            }
        }

        return result;
    }

    /**
     * 对需要上架\下架的奖品进行关联性检查
     * 
     * @param campPrizeModel
     * @return
     * @throws PxManageException
     */
    private boolean validatePrizeBeforeUpdate(CampPrizeModel campPrizeModel) throws PxManageException {
        CampPrizeModel model = queryCampPrizeModelByPrizeId(campPrizeModel.getPrizeId());

        //状态无变更，则不进行检查
        if (model != null && model.getPrizeStatus() == campPrizeModel.getPrizeStatus()) {
            return true;
        }

        //上架奖品须保证奖品已经关联特定商品
        if (campPrizeModel.getPrizeStatus() == CampPrizeStatusEnum.CAMP_PRIZE_ONLINE
            && CollectionUtils.isEmpty(campShopPrizeRefGoodsRepository.findPrizeRefGoodsByPrizeId(campPrizeModel.getPrizeId()))) {
            return false;
        }

        //非上架状态的奖品不允许进行下架操作
        if (campPrizeModel.getPrizeStatus() == CampPrizeStatusEnum.CAMP_PRIZE_OFFLINE && model.getPrizeStatus() != CampPrizeStatusEnum.CAMP_PRIZE_ONLINE) {
            return false;
        }

        return true;
    }

    /** 
     * @see com.myteay.phoenix.core.service.camp.component.CampShopPrizeComponent#queryCampPrizeModelByPrizeId(java.lang.String)
     */
    @Override
    public CampPrizeModel queryCampPrizeModelByPrizeId(String prizeId) {
        CampPrizeModel freshCampPrizeModel = null;
        try {
            freshCampPrizeModel = campShopPrizeRepository.findSingleCampPrizeById(prizeId);
        } catch (PxManageException e) {
            logger.warn("查询店内营销活动信息信息发生异常 prizeId=" + prizeId, e);
        }

        return freshCampPrizeModel;
    }

    /**
     * 查询单条店内活动基本信息模型
     * 
     * @param campPrizeModel
     * @return
     */
    private MtOperateResult<CampPrizeModel> querySingleCampBase(CampPrizeModel campPrizeModel) {
        MtOperateResult<CampPrizeModel> result = new MtOperateResult<CampPrizeModel>();
        CampPrizeModel freshCampPrizeModel = null;
        try {
            freshCampPrizeModel = campShopPrizeRepository.findSingleCampPrizeById(campPrizeModel.getPrizeId());
            result.setResult(freshCampPrizeModel);
        } catch (PxManageException e) {
            logger.warn("查询店内营销活动信息信息发生异常 campPrizeModel=" + campPrizeModel, e);
            result = new MtOperateResult<CampPrizeModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_BASE_QUERY_FAILD);
        }

        return result;
    }

    /**
     * 删除店内活动基本信息
     * 
     * @param campPrizeModel
     * @return
     */
    private MtOperateResult<CampPrizeModel> deleteCampPrizeModel(CampPrizeModel campPrizeModel) {

        // step 1: 活动状态为已发布、已下线、已过期，则不允许进行删除
        try {
            CampPrizeModel freshCampPrizeModel = campShopPrizeRepository.findSingleCampPrizeById(campPrizeModel.getPrizeId());
            if (freshCampPrizeModel != null && (freshCampPrizeModel.getPrizeStatus() == CampPrizeStatusEnum.CAMP_PRIZE_ONLINE
                                                || freshCampPrizeModel.getPrizeStatus() == CampPrizeStatusEnum.CAMP_PRIZE_OFFLINE
                                                || freshCampPrizeModel.getPrizeStatus() == CampPrizeStatusEnum.CAMP_PRIZE_EXPIRED)) {
                logger.warn("商品状态为已发布、已下线，则不允许进行删除 campPrizeModel=" + campPrizeModel);
                return new MtOperateResult<CampPrizeModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_BASE_DEL_STATUS_ERR);
            }
        } catch (PxManageException e) {
            logger.warn("查询单个店内营销活动信息信息发生异常 campPrizeModel=" + campPrizeModel, e);
            return new MtOperateResult<CampPrizeModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_BASE_DELETE_FAILD);
        }

        // step 2: 店内活动包含子项内容，则不允许删除
        if (!isCanDeleteCampPrize(campPrizeModel.getPrizeId())) {
            logger.warn("店内活动奖品包含子项，无法删除 campPrizeModel=" + campPrizeModel);
            return new MtOperateResult<CampPrizeModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_PRIZE_HAS_CHILD_ERR);
        }

        // step 3: 执行删除动作
        MtOperateResult<CampPrizeModel> result = new MtOperateResult<CampPrizeModel>();
        try {
            campShopPrizeRepository.removeCampPrizeInfo(campPrizeModel);
        } catch (PxManageException e) {
            logger.warn("删除店内营销活动信息信息发生异常 campPrizeModel=" + campPrizeModel, e);
            result = new MtOperateResult<CampPrizeModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_BASE_DELETE_FAILD);
        }

        return result;
    }

    /**
     * 检查是否允许删除商品
     * 
     * @param prizeId
     * @return
     */
    private boolean isCanDeleteCampPrize(String prizeId) {

        if (isNoPrizeRefGoodsDetail(prizeId)) {
            return true;
        }

        return false;
    }

    /**
     * 检查当前商品是否包含套餐包信息
     * 
     * @param prizeId
     * @return
     */
    private boolean isNoPrizeRefGoodsDetail(String prizeId) {
        try {
            List<CampPrizeRefGoodsModel> list = campShopPrizeRefGoodsRepository.findPrizeRefGoodsByPrizeId(prizeId);
            if (CollectionUtils.isEmpty(list)) {
                return true;
            }
        } catch (PxManageException e) {
            logger.warn("套餐包信息查询发生异常", e);
        }
        return false;
    }

    /**
     * 保存店内营销活动信息信息
     * 
     * @param campPrizeModel
     * @return
     */
    private MtOperateResult<CampPrizeModel> saveCampPrizeModel(CampPrizeModel campPrizeModel) {
        MtOperateResult<CampPrizeModel> result = new MtOperateResult<CampPrizeModel>();
        CampPrizeModel freshCampPrizeModel = null;
        try {

            if (!validateCampBeforeSave(campPrizeModel)) {
                return new MtOperateResult<CampPrizeModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_BASE_ONLINE_ERR);
            }

            freshCampPrizeModel = campShopPrizeRepository.saveCampPrizeInfo(campPrizeModel);
            result.setResult(freshCampPrizeModel);
        } catch (PxManageException e) {
            logger.warn("保存店内营销活动信息发生异常 campPrizeModel=" + campPrizeModel, e);
            result = new MtOperateResult<CampPrizeModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_BASE_SAVE_FAILD);
        }

        return result;
    }

    /**
     * 对需要保存的奖品进行关联性检查
     * 
     * @param campPrizeModel
     * @return
     * @throws PxManageException
     */
    private boolean validateCampBeforeSave(CampPrizeModel campPrizeModel) throws PxManageException {
        CampBaseModel model = campShopBaseRepository.findSingleCampBase(campPrizeModel.getCampId());

        //非已过期或非已在线活动的活动允许添加奖品
        if (model != null && model.getCampStatus() != CampStatusEnum.CAMP_ONLINE && model.getCampStatus() != CampStatusEnum.CAMP_EXPIRED) {
            return true;
        }

        return false;
    }

    /**
     * 根据不同的管理目标执行管理动作
     * 
     * @param campPrizeModel
     * @param operationType
     * @return
     */
    private MtOperateResult<CampPrizeModel> switchOperation(CampPrizeModel campPrizeModel, PxOperationTypeEnum operationType) {

        return pxCommonMngTemplate.switchOperation(campPrizeModel, operationType, new PxCommonCallback<CampPrizeModel>() {

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#querySingleModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<CampPrizeModel> querySingleModel(CampPrizeModel t) {
                return querySingleCampBase(campPrizeModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#modifyModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<CampPrizeModel> modifyModel(CampPrizeModel t) {
                return modifyCampPrizeModel(campPrizeModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#deleteModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<CampPrizeModel> deleteModel(CampPrizeModel t) {
                return deleteCampPrizeModel(campPrizeModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#addModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<CampPrizeModel> addModel(CampPrizeModel t) {
                return saveCampPrizeModel(campPrizeModel);
            }

        });
    }

    /**
     * Setter method for property <tt>campShopPrizeRepository</tt>.
     * 
     * @param campShopPrizeRepository value to be assigned to property campShopPrizeRepository
     */
    public void setCampShopPrizeRepository(CampShopPrizeRepository campShopPrizeRepository) {
        this.campShopPrizeRepository = campShopPrizeRepository;
    }

    /**
     * Setter method for property <tt>pxCommonMngTemplate</tt>.
     * 
     * @param pxCommonMngTemplate value to be assigned to property pxCommonMngTemplate
     */
    public void setPxCommonMngTemplate(PxCommonMngTemplate<CampPrizeModel> pxCommonMngTemplate) {
        this.pxCommonMngTemplate = pxCommonMngTemplate;
    }

    /**
     * Setter method for property <tt>eventPublishService</tt>.
     * 
     * @param eventPublishService value to be assigned to property eventPublishService
     */
    public void setEventPublishService(EventPublishService<String> eventPublishService) {
        this.eventPublishService = eventPublishService;
    }

    /**
     * Setter method for property <tt>campShopPrizeRefGoodsRepository</tt>.
     * 
     * @param campShopPrizeRefGoodsRepository value to be assigned to property campShopPrizeRefGoodsRepository
     */
    public void setCampShopPrizeRefGoodsRepository(CampShopPrizeRefGoodsRepository campShopPrizeRefGoodsRepository) {
        this.campShopPrizeRefGoodsRepository = campShopPrizeRefGoodsRepository;
    }

    /**
     * Setter method for property <tt>campShopBaseRepository</tt>.
     * 
     * @param campShopBaseRepository value to be assigned to property campShopBaseRepository
     */
    public void setCampShopBaseRepository(CampShopBaseRepository campShopBaseRepository) {
        this.campShopBaseRepository = campShopBaseRepository;
    }

}
