/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;

import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.common.dal.camp.daointerface.CampAlgorithmDAO;
import com.myteay.phoenix.common.dal.camp.dataobject.CampAlgorithmDO;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.core.service.camp.algorithm.enums.CampAlgorithmTypeEnum;
import com.myteay.phoenix.core.service.camp.algorithm.handles.GDHandler;
import com.myteay.phoenix.core.service.camp.algorithm.handles.GFPHandler;
import com.myteay.phoenix.core.service.camp.algorithm.handles.GPHandler;
import com.myteay.phoenix.core.service.camp.algorithm.handles.Handler;
import com.myteay.phoenix.core.service.camp.algorithm.handles.SinglePrizeChecker;
import com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmModel;
import com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmResult;

/**
 * 抽奖算法组件
 * 
 * @author danlley
 * @version $Id: CampAlgorithmComponentImpl.java, v 0.1 Jan 7, 2019 1:11:08 PM danlley Exp $
 */
public class CampAlgorithmComponentImpl implements CampAlgorithmComponent, InitializingBean {

    /** 日志 */
    public static final Logger                logger     = LoggerFactory.getLogger(LoggerNames.PX_CAMP);

    private static final Map<String, Handler> HANDLE_MAP = Collections.synchronizedMap(new HashMap<>());

    /** 抽奖算法管理DAO */
    private CampAlgorithmDAO                  campAlgorithmDAO;

    /** 抽奖算法缓存组件 */
    private CampAlgorithmCacheComponent       campAlgorithmCacheComponent;

    /** 按时段分布规则 */
    private GDHandler                         gDHandler;

    /** 按时段、频度分布规则 */
    private GFPHandler                        gFPHandler;

    /** 按频度分布规则 */
    private GPHandler                         gPHandler;

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        synchronized (HANDLE_MAP) {
            HANDLE_MAP.put(CampAlgorithmTypeEnum.CAMP_GD.getValue(), gDHandler);
            HANDLE_MAP.put(CampAlgorithmTypeEnum.CAMP_GFD.getValue(), gFPHandler);
            HANDLE_MAP.put(CampAlgorithmTypeEnum.CAMP_GP.getValue(), gPHandler);
        }
    }

    /** 
     * @see com.myteay.phoenix.core.service.camp.algorithm.CampAlgorithmComponent#execute(java.lang.String, java.util.List)
     */
    @Override
    public CampAlgorithmResult<CampAlgorithmModel> execute(String campId, List<SinglePrizeChecker> checkers) {
        logger.warn("开始抽奖 campId=" + campId);
        List<CampAlgorithmModel> list = campAlgorithmCacheComponent.findPrizeListByCampId(campId).getResult();

        if (CollectionUtils.isEmpty(list)) {
            logger.warn("当前活动未找到对应的奖品信息 campId=" + campId);
            return new CampAlgorithmResult<>();
        }

        Collections.sort(list);

        for (CampAlgorithmModel campAlgorithmModel : list) {
            if (doAlgorithm(campAlgorithmModel, checkers)) {
                logger.warn("当前抽奖请求命中奖品 campAlgorithmModel=" + campAlgorithmModel);
                return new CampAlgorithmResult<>(campAlgorithmModel);
            }
        }

        if (logger.isInfoEnabled()) {
            logger.info("当前活动抽奖执行结束，未中奖， campId=" + campId);
        }

        return new CampAlgorithmResult<>();
    }

    /**
     * 开始执行抽奖算法
     * 
     * @param campAlgorithmModel
     * @return
     */
    private boolean doAlgorithm(CampAlgorithmModel campAlgorithmModel, List<SinglePrizeChecker> checkers) {
        if (!doCheckLimit(campAlgorithmModel, checkers)) {
            logger.warn("奖品前置约束条件检查未通过，本次不出奖 campAlgorithmModel=" + campAlgorithmModel);
            return false;
        }

        int percent = campAlgorithmModel.getPercent();
        if (percent <= 0) {
            logger.warn("中奖概率不满足中奖条件，当前不出奖percent <= 0 campAlgorithmModel=" + campAlgorithmModel);
            return false;
        }

        Random random = new Random();
        int currentPercent = random.nextInt(100);

        // step 1: 中奖概率出奖为满足条件，不中奖的情况
        if (currentPercent >= percent) {
            return false;
        }

        // step 2: 奖位分布不中奖的情况
        return isHandler(campAlgorithmModel);

    }

    /**
     * 执行检查器列表
     * 
     * @param campAlgorithmModel
     * @param checkers
     * @return
     */
    private boolean doCheckLimit(CampAlgorithmModel campAlgorithmModel, List<SinglePrizeChecker> checkers) {

        if (CollectionUtils.isEmpty(checkers)) {
            return false;
        }

        for (SinglePrizeChecker checker : checkers) {
            if (!checker.doCheck(campAlgorithmModel)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 开始执行奖位段检查
     * 
     * @param campAlgorithmModel
     * @return
     */
    private boolean isHandler(CampAlgorithmModel campAlgorithmModel) {
        if (campAlgorithmModel == null || campAlgorithmModel.getDistributionModel() == null
            || campAlgorithmModel.getDistributionModel().getAlgorithmType() == null) {
            return false;
        }

        switch (campAlgorithmModel.getDistributionModel().getAlgorithmType()) {
            case CAMP_GD:

                return HANDLE_MAP.get(CampAlgorithmTypeEnum.CAMP_GD.getValue()).doDistribution(campAlgorithmModel);
            case CAMP_GFD:

                return HANDLE_MAP.get(CampAlgorithmTypeEnum.CAMP_GFD.getValue()).doDistribution(campAlgorithmModel);
            case CAMP_GP:

                return HANDLE_MAP.get(CampAlgorithmTypeEnum.CAMP_GP.getValue()).doDistribution(campAlgorithmModel);

            default:
                return false;
        }
    }

    /**
     * 初始化抽奖主模块奖品信息
     * 
     * @param campId
     * @param campAlgorithmModels
     */
    private void initAlgorithm(String campId, List<CampAlgorithmModel> campAlgorithmModels) {

        campAlgorithmDAO.deleteCampAlgorithmByCampId(campId);

        if (CollectionUtils.isEmpty(campAlgorithmModels)) {

            // 刷新缓存
            campAlgorithmCacheComponent.initCache();
            return;
        }

        for (CampAlgorithmModel campAlgorithmModel : campAlgorithmModels) {
            logger.warn("开始准备抽奖数据 campAlgorithmModel=" + campAlgorithmModel);
            saveAlgorithmResult(campAlgorithmModel);
        }

        // 刷新缓存
        campAlgorithmCacheComponent.initCache();
    }

    /** 
     * @see com.myteay.phoenix.core.service.camp.algorithm.CampAlgorithmComponent#closeCertainCamp(java.lang.String)
     */
    @Override
    public CampAlgorithmResult<String> closeCertainCamp(String campId) {
        //清理目标活动的奖品信息
        campAlgorithmDAO.deleteCampAlgorithmByCampId(campId);

        // 刷新缓存
        campAlgorithmCacheComponent.initCache();

        return new CampAlgorithmResult<>(campId);
    }

    /** 
     * @see com.myteay.phoenix.core.service.camp.algorithm.CampAlgorithmComponent#initAlgorithm(java.lang.String, java.util.List, int)
     */
    @Override
    public CampAlgorithmResult<List<CampAlgorithmModel>> initAlgorithm(String campId, List<CampAlgorithmModel> campAlgorithmModels, int operationLevel) {
        logger.warn("开始准备抽奖数据 campAlgorithmModels=" + campAlgorithmModels + " operationLevel=" + operationLevel);

        switch (operationLevel) {
            case 1:
                initAlgorithm(campId, campAlgorithmModels);
            case 2:
                logger.warn("当前不支持的数据操作等级 campAlgorithmModels=" + campAlgorithmModels + " operationLevel=" + operationLevel);
                break;
            case 3:
                logger.warn("当前不支持的数据操作等级 campAlgorithmModels=" + campAlgorithmModels + " operationLevel=" + operationLevel);
                break;

            default:
                logger.warn("当前不支持的数据操作等级 campAlgorithmModels=" + campAlgorithmModels + " operationLevel=" + operationLevel);
                break;
        }

        return new CampAlgorithmResult<>(campAlgorithmModels);
    }

    /**
     * 保存抽奖算法数据
     * 
     * @param campAlgorithmModel
     * @return
     */
    private CampAlgorithmModel saveAlgorithmResult(CampAlgorithmModel campAlgorithmModel) {
        logger.warn("开始保存抽奖算法数据 campAlgorithmModel=" + campAlgorithmModel);

        CampAlgorithmDO campAlgorithmDO = constructAlgorithmDO(campAlgorithmModel);
        campAlgorithmDAO.insert(campAlgorithmDO);

        return campAlgorithmModel;
    }

    /**
     * 获取数据模型
     * 
     * @param campAlgorithmModel
     * @return
     */
    private CampAlgorithmDO constructAlgorithmDO(CampAlgorithmModel campAlgorithmModel) {

        if (campAlgorithmModel == null) {
            return null;
        }

        CampAlgorithmDO campAlgorithmDO = new CampAlgorithmDO();
        if (campAlgorithmModel.getAlgorithmStatus() != null) {
            campAlgorithmDO.setAlgorithmStatus(campAlgorithmModel.getAlgorithmStatus().getValue());
        }
        campAlgorithmDO.setCampId(campAlgorithmModel.getCampId());
        campAlgorithmDO.setDistribution(campAlgorithmModel.getDistribution());
        campAlgorithmDO.setPrizeAmount(campAlgorithmModel.getPrizeAmount());
        campAlgorithmDO.setPercent(campAlgorithmModel.getPercent());
        campAlgorithmDO.setPrizeId(campAlgorithmModel.getPrizeId());
        campAlgorithmDO.setPrizeLevel(campAlgorithmModel.getPrizeLevel());
        campAlgorithmDO.setGmtCreated(campAlgorithmModel.getGmtCreated());
        campAlgorithmDO.setGmtModified(campAlgorithmModel.getGmtModified());

        return campAlgorithmDO;
    }

    /**
     * Setter method for property <tt>campAlgorithmDAO</tt>.
     * 
     * @param campAlgorithmDAO value to be assigned to property campAlgorithmDAO
     */
    public void setCampAlgorithmDAO(CampAlgorithmDAO campAlgorithmDAO) {
        this.campAlgorithmDAO = campAlgorithmDAO;
    }

    /**
     * Setter method for property <tt>campAlgorithmCacheComponent</tt>.
     * 
     * @param campAlgorithmCacheComponent value to be assigned to property campAlgorithmCacheComponent
     */
    public void setCampAlgorithmCacheComponent(CampAlgorithmCacheComponent campAlgorithmCacheComponent) {
        this.campAlgorithmCacheComponent = campAlgorithmCacheComponent;
    }

    /**
     * Setter method for property <tt>gDHandler</tt>.
     * 
     * @param gDHandler value to be assigned to property gDHandler
     */
    public void setgDHandler(GDHandler gDHandler) {
        this.gDHandler = gDHandler;
    }

    /**
     * Setter method for property <tt>gFPHandler</tt>.
     * 
     * @param gFPHandler value to be assigned to property gFPHandler
     */
    public void setgFPHandler(GFPHandler gFPHandler) {
        this.gFPHandler = gFPHandler;
    }

    /**
     * Setter method for property <tt>gPHandler</tt>.
     * 
     * @param gPHandler value to be assigned to property gPHandler
     */
    public void setgPHandler(GPHandler gPHandler) {
        this.gPHandler = gPHandler;
    }

}
