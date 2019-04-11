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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;

import com.myteay.phoenix.common.dal.camp.daointerface.CampAlgorithmDAO;
import com.myteay.phoenix.common.dal.camp.dataobject.CampAlgorithmDO;
import com.myteay.phoenix.core.service.camp.algorithm.enums.CampAlgorithmStatusEnum;
import com.myteay.phoenix.core.service.camp.algorithm.enums.CampAlgorithmTypeEnum;
import com.myteay.phoenix.core.service.camp.algorithm.handles.GDHandler;
import com.myteay.phoenix.core.service.camp.algorithm.handles.GFPHandler;
import com.myteay.phoenix.core.service.camp.algorithm.handles.GPHandler;
import com.myteay.phoenix.core.service.camp.algorithm.handles.Handler;
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
    public static final Logger                logger     = Logger.getLogger(CampAlgorithmComponentImpl.class);

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
     * @see com.myteay.phoenix.core.service.camp.algorithm.CampAlgorithmComponent#execute(java.lang.String)
     */
    @Override
    public CampAlgorithmResult<CampAlgorithmModel> execute(String campId) {
        logger.warn("开始抽奖 campId=" + campId);
        List<CampAlgorithmModel> list = campAlgorithmCacheComponent.findPrizeListByCampId(campId).getResult();

        if (CollectionUtils.isEmpty(list)) {
            logger.warn("当前活动未找到对应的奖品信息 campId=" + campId);
            return new CampAlgorithmResult<>();
        }

        Collections.sort(list);

        for (CampAlgorithmModel campAlgorithmModel : list) {
            if (doAlgorithm(campAlgorithmModel)) {
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
    private boolean doAlgorithm(CampAlgorithmModel campAlgorithmModel) {
        int percent = campAlgorithmModel.getPercent();
        if (percent <= 0) {
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
     * @see com.myteay.phoenix.core.service.camp.algorithm.CampAlgorithmComponent#initAlgorithm(com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmModel, int)
     */
    @Override
    public CampAlgorithmResult<CampAlgorithmModel> initAlgorithm(CampAlgorithmModel campAlgorithmModel, int operationLevel) {
        logger.warn("开始准备抽奖数据 campAlgorithmModel=" + campAlgorithmModel + " operationLevel=" + operationLevel);

        CampAlgorithmModel freshAlgorithmModel = null;
        switch (operationLevel) {
            case 1:
                return saveAlgorithmResult(campAlgorithmModel);
            case 2:
                logger.warn("当前不支持的数据操作等级 campAlgorithmModel=" + campAlgorithmModel + " operationLevel=" + operationLevel);
                break;
            case 3:
                logger.warn("当前不支持的数据操作等级 campAlgorithmModel=" + campAlgorithmModel + " operationLevel=" + operationLevel);
                break;

            default:
                logger.warn("当前不支持的数据操作等级 campAlgorithmModel=" + campAlgorithmModel + " operationLevel=" + operationLevel);
                break;
        }

        return new CampAlgorithmResult<>(freshAlgorithmModel);
    }

    /**
     * 保存抽奖算法数据
     * 
     * @param campAlgorithmModel
     * @return
     */
    private CampAlgorithmResult<CampAlgorithmModel> saveAlgorithmResult(CampAlgorithmModel campAlgorithmModel) {
        logger.warn("开始保存抽奖算法数据 campAlgorithmModel=" + campAlgorithmModel);

        String prizeId = campAlgorithmModel.getPrizeId();
        if (isNeedAlgorithmSave(prizeId)) {
            CampAlgorithmDO campAlgorithmDO = constructAlgorithmDO(campAlgorithmModel);
            campAlgorithmDAO.insert(campAlgorithmDO);
        }

        CampAlgorithmDO freshAlgorithmDO = campAlgorithmDAO.findCampAlgorithmByPrizeId(prizeId);
        CampAlgorithmModel freshAlgorithmModel = constructAlgorithmModel(freshAlgorithmDO);

        refreshAlgorithmCache(freshAlgorithmModel);

        return new CampAlgorithmResult<>(freshAlgorithmModel);
    }

    /**
     * 检查是否需要对当前奖品进行奖品算法表的写入动作
     * 
     * @param prizeId
     * @return
     */
    private boolean isNeedAlgorithmSave(String prizeId) {
        if (StringUtils.isBlank(prizeId)) {
            return false;
        }

        CampAlgorithmDO freshAlgorithmDO = campAlgorithmDAO.findCampAlgorithmByPrizeId(prizeId);
        if (freshAlgorithmDO == null) {
            return true;
        }

        return false;
    }

    /**
     * 刷新缓存
     * 
     * @param freshAlgorithmModel
     */
    private void refreshAlgorithmCache(CampAlgorithmModel freshAlgorithmModel) {
        campAlgorithmCacheComponent.initCache(freshAlgorithmModel);
    }

    /**
     * 获取算法模型
     * 
     * @param freshAlgorithmDO
     * @return
     */
    private CampAlgorithmModel constructAlgorithmModel(CampAlgorithmDO freshAlgorithmDO) {
        if (freshAlgorithmDO == null) {
            return null;
        }

        CampAlgorithmModel campAlgorithmModel = new CampAlgorithmModel();
        campAlgorithmModel.setAlgorithmStatus(CampAlgorithmStatusEnum.getByValue(freshAlgorithmDO.getAlgorithmStatus()));
        campAlgorithmModel.setCampId(freshAlgorithmDO.getCampId());
        campAlgorithmModel.setDistribution(freshAlgorithmDO.getDistribution());
        campAlgorithmModel.setPercent(freshAlgorithmDO.getPercent());
        campAlgorithmModel.setPrizeAmount(freshAlgorithmDO.getPrizeAmount());
        campAlgorithmModel.setPrizeId(freshAlgorithmDO.getPrizeId());
        campAlgorithmModel.setPrizeLevel(freshAlgorithmDO.getPrizeLevel());
        campAlgorithmModel.setGmtCreated(freshAlgorithmDO.getGmtCreated());
        campAlgorithmModel.setGmtModified(freshAlgorithmDO.getGmtModified());

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
