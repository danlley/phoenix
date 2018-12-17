/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.component;

import org.apache.log4j.Logger;

import com.myteay.common.async.event.EventPublishService;
import com.myteay.common.async.event.MtEvent;
import com.myteay.phoenix.common.util.camp.enums.CampStatusEnum;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.enums.PxEventTopicEnum;
import com.myteay.phoenix.common.util.enums.PxOperationTypeEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampBaseModel;
import com.myteay.phoenix.core.model.camp.repository.CampShopBaseRepository;
import com.myteay.phoenix.core.service.manage.template.PxCommonCallback;
import com.myteay.phoenix.core.service.manage.template.PxCommonMngTemplate;

/**
 * 店内营销活动基本信息管理组件
 * 
 * @author danlley
 * @version $Id: CampShopBaseComponentImpl.java, v 0.1 Dec 17, 2018 12:07:38 AM danlley Exp $
 */
public class CampShopBaseComponentImpl implements CampShopBaseComponent {

    /** 日志 */
    public static final Logger                 logger = Logger.getLogger(CampShopBaseComponentImpl.class);

    /** 针对单个店铺店内消费到场营销活动操作仓储 */
    private CampShopBaseRepository             campShopBaseRepository;

    /** 后台管理业务处理分流模板 */
    private PxCommonMngTemplate<CampBaseModel> pxCommonMngTemplate;

    /** 事件发送组件 */
    private EventPublishService<String>        eventPublishService;

    /** 
     * @see com.myteay.phoenix.core.service.camp.component.CampShopBaseComponent#manageCampBase(com.myteay.phoenix.core.model.camp.CampBaseModel)
     */
    @Override
    public MtOperateResult<CampBaseModel> manageCampBase(CampBaseModel campBaseModel) {

        if (campBaseModel == null) {
            logger.warn("店内营销活动信息模型不可用，无法执行管理动作 campBaseModel= " + campBaseModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_BASE_MODEL_INVALID);
        }

        if (campBaseModel.getOperationType() == null) {
            logger.warn("店内营销活动信息模型中的操作类型未知 campBaseModel=" + campBaseModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_TEMPLATE_OP_UNKNOW);
        }

        return switchOperation(campBaseModel, campBaseModel.getOperationType());
    }

    /** 
     * @see com.myteay.phoenix.core.service.camp.component.CampShopBaseComponent#modifyCampBaseModel(com.myteay.phoenix.core.model.camp.CampBaseModel)
     */
    @Override
    public MtOperateResult<CampBaseModel> modifyCampBaseModel(CampBaseModel campBaseModel) {
        MtOperateResult<CampBaseModel> result = new MtOperateResult<CampBaseModel>();
        CampBaseModel freshCampBaseModel = null;
        try {
            freshCampBaseModel = campShopBaseRepository.modifyGoodsInfo(campBaseModel);
            result.setResult(freshCampBaseModel);
        } catch (PxManageException e) {
            logger.warn("修改店内营销活动信息信息发生异常 campBaseModel=" + campBaseModel, e);
            result = new MtOperateResult<CampBaseModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_BASE_UPDATE_FAILD);
        }

        if (freshCampBaseModel != null
            && (freshCampBaseModel.getCampStatus() == CampStatusEnum.CAMP_EXPIRED || freshCampBaseModel.getCampStatus() == CampStatusEnum.CAMP_OFFLINE
                || freshCampBaseModel.getCampStatus() == CampStatusEnum.CAMP_ONLINE)) {
            MtEvent<CampBaseModel> event = new MtEvent<CampBaseModel>(PxEventTopicEnum.PX_CAMP_BASE_STATUS_CHANGED.getValue(), freshCampBaseModel);
            //            try {
            //                eventPublishService.publishEvent(event);
            //            } catch (MtEventException e) {
            //                logger.warn("商品状态变更后，发送缓存刷新请求发生异常 " + e.getMessage(), e);
            //            }
        }

        return result;
    }

    /** 
     * @see com.myteay.phoenix.core.service.camp.component.CampShopBaseComponent#queryCampBaseModelByCampId(java.lang.String)
     */
    @Override
    public CampBaseModel queryCampBaseModelByCampId(String campId) {
        CampBaseModel freshCampBaseModel = null;
        try {
            freshCampBaseModel = campShopBaseRepository.findSingleCampBase(campId);
        } catch (PxManageException e) {
            logger.warn("查询店内营销活动信息信息发生异常 campId=" + campId, e);
        }

        return freshCampBaseModel;
    }

    /**
     * 查询单条店内活动基本信息模型
     * 
     * @param campBaseModel
     * @return
     */
    private MtOperateResult<CampBaseModel> querySingleCampBase(CampBaseModel campBaseModel) {
        MtOperateResult<CampBaseModel> result = new MtOperateResult<CampBaseModel>();
        CampBaseModel freshCampBaseModel = null;
        try {
            freshCampBaseModel = campShopBaseRepository.findSingleCampBase(campBaseModel.getCampId());
            result.setResult(freshCampBaseModel);
        } catch (PxManageException e) {
            logger.warn("查询店内营销活动信息信息发生异常 campBaseModel=" + campBaseModel, e);
            result = new MtOperateResult<CampBaseModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_BASE_QUERY_FAILD);
        }

        return result;
    }

    /**
     * 删除店内活动基本信息
     * 
     * @param campBaseModel
     * @return
     */
    private MtOperateResult<CampBaseModel> deleteCampBaseModel(CampBaseModel campBaseModel) {

        // step 1: 活动状态为已发布、已下线、已过期，则不允许进行删除
        try {
            CampBaseModel freshCampBaseModel = campShopBaseRepository.findSingleCampBase(campBaseModel.getCampId());
            if (freshCampBaseModel != null
                && (freshCampBaseModel.getCampStatus() == CampStatusEnum.CAMP_ONLINE || freshCampBaseModel.getCampStatus() == CampStatusEnum.CAMP_OFFLINE
                    || freshCampBaseModel.getCampStatus() == CampStatusEnum.CAMP_EXPIRED)) {
                logger.warn("商品状态为已发布、已下线，则不允许进行删除 campBaseModel=" + campBaseModel);
                return new MtOperateResult<CampBaseModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_BASE_DEL_STATUS_ERR);
            }
        } catch (PxManageException e) {
            logger.warn("查询单个店内营销活动信息信息发生异常 campBaseModel=" + campBaseModel, e);
            return new MtOperateResult<CampBaseModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_BASE_DELETE_FAILD);
        }

        // step 2: 店内活动包含子项内容，则不允许删除
        if (!isCanDeleteCampBase(campBaseModel.getCampId())) {
            logger.warn("店内活动包含子项，无法删除 campBaseModel=" + campBaseModel);
            return new MtOperateResult<CampBaseModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_BASE_HAS_CHILD_ERR);
        }

        // step 3: 执行删除动作
        MtOperateResult<CampBaseModel> result = new MtOperateResult<CampBaseModel>();
        try {
            campShopBaseRepository.removeGoodsInfo(campBaseModel);
        } catch (PxManageException e) {
            logger.warn("删除店内营销活动信息信息发生异常 campBaseModel=" + campBaseModel, e);
            result = new MtOperateResult<CampBaseModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_BASE_DELETE_FAILD);
        }

        return result;
    }

    /**
     * 检查是否允许删除商品
     * 
     * @param goodsId
     * @return
     */
    private boolean isCanDeleteCampBase(String campId) {

        if (isNoPrizeDetail(campId)) {
            return true;
        }

        return false;
    }

    /**
     * 检查当前商品是否包含套餐包信息
     * 
     * @param campId
     * @return
     */
    private boolean isNoPrizeDetail(String campId) {
        //        try {
        //            List<PxGoodsPackagesDetailModel> list = pxGoodsPackagesDetailRepository.findGoodsPackagesDetailByGoodsId(goodsId);
        //            if (CollectionUtils.isEmpty(list)) {
        //                return true;
        //            }
        //        } catch (PxManageException e) {
        //            logger.warn("套餐包信息查询发生异常", e);
        //        }
        return true;
    }

    /**
     * 保存店内营销活动信息信息
     * 
     * @param campBaseModel
     * @return
     */
    private MtOperateResult<CampBaseModel> saveCampBaseModel(CampBaseModel campBaseModel) {
        MtOperateResult<CampBaseModel> result = new MtOperateResult<CampBaseModel>();
        CampBaseModel freshCampBaseModel = null;
        try {
            freshCampBaseModel = campShopBaseRepository.saveGoodsInfo(campBaseModel);
            result.setResult(freshCampBaseModel);
        } catch (PxManageException e) {
            logger.warn("保存店内营销活动信息发生异常 campBaseModel=" + campBaseModel, e);
            result = new MtOperateResult<CampBaseModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_BASE_SAVE_FAILD);
        }

        return result;
    }

    /**
     * 根据不同的管理目标执行管理动作
     * 
     * @param campBaseModel
     * @param operationType
     * @return
     */
    private MtOperateResult<CampBaseModel> switchOperation(CampBaseModel campBaseModel, PxOperationTypeEnum operationType) {

        return pxCommonMngTemplate.switchOperation(campBaseModel, operationType, new PxCommonCallback<CampBaseModel>() {

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#querySingleModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<CampBaseModel> querySingleModel(CampBaseModel t) {
                return querySingleCampBase(campBaseModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#modifyModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<CampBaseModel> modifyModel(CampBaseModel t) {
                return modifyCampBaseModel(campBaseModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#deleteModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<CampBaseModel> deleteModel(CampBaseModel t) {
                return deleteCampBaseModel(campBaseModel);
            }

            /** 
             * @see com.myteay.phoenix.core.service.manage.template.PxCommonCallback#addModel(java.lang.Object)
             */
            @Override
            public MtOperateResult<CampBaseModel> addModel(CampBaseModel t) {
                return saveCampBaseModel(campBaseModel);
            }

        });
    }

    /**
     * Setter method for property <tt>pxCommonMngTemplate</tt>.
     * 
     * @param pxCommonMngTemplate value to be assigned to property pxCommonMngTemplate
     */
    public void setPxCommonMngTemplate(PxCommonMngTemplate<CampBaseModel> pxCommonMngTemplate) {
        this.pxCommonMngTemplate = pxCommonMngTemplate;
    }

    /**
     * Setter method for property <tt>campShopBaseRepository</tt>.
     * 
     * @param campShopBaseRepository value to be assigned to property campShopBaseRepository
     */
    public void setCampShopBaseRepository(CampShopBaseRepository campShopBaseRepository) {
        this.campShopBaseRepository = campShopBaseRepository;
    }

    /**
     * Setter method for property <tt>eventPublishService</tt>.
     * 
     * @param eventPublishService value to be assigned to property eventPublishService
     */
    public void setEventPublishService(EventPublishService<String> eventPublishService) {
        this.eventPublishService = eventPublishService;
    }

}
