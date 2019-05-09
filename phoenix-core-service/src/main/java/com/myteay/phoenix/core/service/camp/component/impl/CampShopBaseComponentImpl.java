/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

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
import com.myteay.phoenix.core.model.camp.repository.CampShopBaseRepository;
import com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRepository;
import com.myteay.phoenix.core.service.camp.algorithm.CampAlgorithmComponent;
import com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmModel;
import com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmResult;
import com.myteay.phoenix.core.service.camp.component.CampShopBaseComponent;
import com.myteay.phoenix.core.service.manage.template.PxCommonCallback;
import com.myteay.phoenix.core.service.manage.template.PxCommonMngTemplate;
import com.myteay.phoenix.core.service.tools.PxEventPublishTool;

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

    /** 针对单个店铺店内消费到场营销活动操作仓储 */
    private CampShopPrizeRepository            campShopPrizeRepository;

    /** 后台管理业务处理分流模板 */
    private PxCommonMngTemplate<CampBaseModel> pxCommonMngTemplate;

    /** 抽奖组件 */
    private CampAlgorithmComponent             campAlgorithmComponent;

    /** 事件发送组件 */
    @Autowired
    private PxEventPublishTool                 pxEventPublishTool;

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
        boolean isErr = false;
        try {

            if (!this.validatePrize(campBaseModel)) {
                logger.warn("活动状态变更前的关联性检查未通过 campBaseModel=" + campBaseModel);
                return new MtOperateResult<CampBaseModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_BASE_UPDATE_REF_ERR);
            }

            freshCampBaseModel = campShopBaseRepository.modifyGoodsInfo(campBaseModel);
            result.setResult(freshCampBaseModel);
            //异步更新缓存信息
            pxEventPublishTool.publishEventWithObject(PxEventTopicEnum.CAMP_STATUS_CHANGED, campBaseModel);
        } catch (PxManageException e) {
            logger.warn("修改店内营销活动信息信息发生异常 campBaseModel=" + campBaseModel, e);
            result = new MtOperateResult<CampBaseModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_BASE_UPDATE_FAILD);
            isErr = true;
        }

        if (!isErr && freshCampBaseModel != null && freshCampBaseModel.getCampStatus() == CampStatusEnum.CAMP_ONLINE) {
            try {
                initPrizeProcessData(freshCampBaseModel);
            } catch (PxManageException e) {
                logger.warn("初始化抽奖主流程数据出错 freshCampBaseModel: " + freshCampBaseModel, e);
            }
        }

        if (campBaseModel.getCampStatus() == CampStatusEnum.CAMP_OFFLINE) {
            campAlgorithmComponent.closeCertainCamp(campBaseModel.getCampId());
        }

        return result;
    }

    /**
     * 启动活动检查
     * 
     * @param campBaseModel
     * @return
     * @throws PxManageException
     */
    private boolean validatePrize(CampBaseModel campBaseModel) throws PxManageException {
        List<CampPrizeModel> list = campShopPrizeRepository.findCampPrizeByCampId(campBaseModel.getCampId());

        CampBaseModel model = campShopBaseRepository.findSingleCampBase(campBaseModel.getCampId());

        // 未发生状态变更，不做检查
        if (model != null && model.getCampStatus() == campBaseModel.getCampStatus()) {
            return true;
        }

        List<CampPrizeModel> campPrizeModels = new ArrayList<>();
        for (CampPrizeModel campPrizeModel : list) {

            // 为已上架奖品的检查做准备工作
            if (campPrizeModel.getPrizeStatus() == CampPrizeStatusEnum.CAMP_PRIZE_ONLINE) {
                campPrizeModels.add(campPrizeModel);
            }

            // 奖品的状态如果存在草稿状态的，则不允许启动活动
            if (campPrizeModel.getPrizeStatus() == CampPrizeStatusEnum.CAMP_PRIZE_DRAFT && campBaseModel.getCampStatus() == CampStatusEnum.CAMP_ONLINE) {
                return false;
            }
        }

        // 启动活动的首要条件是，该活动中必须包含已上架的奖品
        if (CollectionUtils.isEmpty(campPrizeModels) && campBaseModel.getCampStatus() == CampStatusEnum.CAMP_ONLINE) {
            return false;
        }

        return true;
    }

    /**
     * 初始化抽奖主流程数据
     * 
     * @param campBaseModel
     * @throws PxManageException
     */
    private void initPrizeProcessData(CampBaseModel campBaseModel) throws PxManageException {
        List<CampPrizeModel> list = campShopPrizeRepository.findCampPrizeByCampId(campBaseModel.getCampId());

        CampBaseModel model = campShopBaseRepository.findSingleCampBase(campBaseModel.getCampId());

        // 非启动状态下的刷新不做营销活动主流程数据初始化动作
        if (model != null && model.getCampStatus() == campBaseModel.getCampStatus() && campBaseModel.getCampStatus() != CampStatusEnum.CAMP_ONLINE) {
            return;
        }

        List<CampPrizeModel> campPrizeModels = new ArrayList<>();
        for (CampPrizeModel campPrizeModel : list) {

            // 为已上架奖品的检查做准备工作
            if (campPrizeModel.getPrizeStatus() == CampPrizeStatusEnum.CAMP_PRIZE_ONLINE) {
                campPrizeModels.add(campPrizeModel);
            }

            // 奖品的状态如果存在草稿状态的，则不允许对营销活动主流程数据进行初始化
            if (campPrizeModel.getPrizeStatus() == CampPrizeStatusEnum.CAMP_PRIZE_DRAFT && campBaseModel.getCampStatus() == CampStatusEnum.CAMP_ONLINE) {
                return;
            }
        }

        // 活动为发起启动活动，且存在奖品列表，则初始化抽奖主流程
        if (!CollectionUtils.isEmpty(campPrizeModels) && campBaseModel.getCampStatus() == CampStatusEnum.CAMP_ONLINE) {//&& model.getCampStatus() != CampStatusEnum.CAMP_ONLINE

            List<CampAlgorithmModel> params = constructAlgorithmParams(campBaseModel, campPrizeModels);

            CampAlgorithmResult<List<CampAlgorithmModel>> result = campAlgorithmComponent.initAlgorithm(campBaseModel.getCampId(), params, 1);

            if (logger.isInfoEnabled()) {
                logger.info("抽奖主流程初始化数据结果： result = " + result + " campBaseModel.getCampId()=" + campBaseModel.getCampId());
            }
        }

    }

    /**
     * 构建抽奖算法初始化数据接口入参
     * 
     * @param campBaseModel
     * @param campPrizeModels
     * @return
     */
    private List<CampAlgorithmModel> constructAlgorithmParams(CampBaseModel campBaseModel, List<CampPrizeModel> campPrizeModels) {

        if (CollectionUtils.isEmpty(campPrizeModels)) {
            return null;
        }

        List<CampAlgorithmModel> list = new ArrayList<>();

        CampAlgorithmModel campAlgorithmModel = null;

        for (CampPrizeModel prizeModel : campPrizeModels) {
            campAlgorithmModel = new CampAlgorithmModel();
            campAlgorithmModel.setCampId(campBaseModel.getCampId());
            campAlgorithmModel.setDistribution(prizeModel.getDistribution());
            campAlgorithmModel.setPercent(Integer.parseInt(prizeModel.getPrizePercent()));
            campAlgorithmModel.setPrizeAmount(Integer.parseInt(prizeModel.getPrizeAmount()));
            campAlgorithmModel.setPrizeId(prizeModel.getPrizeId());
            campAlgorithmModel.setPrizeLevel(Integer.parseInt(prizeModel.getPrizeLevel()));

            list.add(campAlgorithmModel);
        }

        return list;

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
        try {
            if (!isCanDeleteCampBase(campBaseModel.getCampId())) {
                logger.warn("店内活动包含子项，无法删除 campBaseModel=" + campBaseModel);
                return new MtOperateResult<CampBaseModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_BASE_HAS_CHILD_ERR);
            }
        } catch (PxManageException e1) {
            logger.warn("删除店内营销活动信息进行关联性检查时发生异常 campBaseModel=" + campBaseModel, e1);
            return new MtOperateResult<CampBaseModel>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_BASE_DELETE_FAILD);
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
     * @throws PxManageException 
     */
    private boolean isCanDeleteCampBase(String campId) throws PxManageException {

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
     * @throws PxManageException 
     */
    private boolean isNoPrizeDetail(String campId) throws PxManageException {
        if (CollectionUtils.isEmpty(campShopPrizeRepository.findCampPrizeByCampId(campId))) {
            return true;
        }
        return false;
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
     * Setter method for property <tt>campShopPrizeRepository</tt>.
     * 
     * @param campShopPrizeRepository value to be assigned to property campShopPrizeRepository
     */
    public void setCampShopPrizeRepository(CampShopPrizeRepository campShopPrizeRepository) {
        this.campShopPrizeRepository = campShopPrizeRepository;
    }

    /**
     * Setter method for property <tt>campAlgorithmComponent</tt>.
     * 
     * @param campAlgorithmComponent value to be assigned to property campAlgorithmComponent
     */
    public void setCampAlgorithmComponent(CampAlgorithmComponent campAlgorithmComponent) {
        this.campAlgorithmComponent = campAlgorithmComponent;
    }

    /**
     * Setter method for property <tt>pxEventPublishTool</tt>.
     * 
     * @param pxEventPublishTool value to be assigned to property pxEventPublishTool
     */
    public void setPxEventPublishTool(PxEventPublishTool pxEventPublishTool) {
        this.pxEventPublishTool = pxEventPublishTool;
    }

}
