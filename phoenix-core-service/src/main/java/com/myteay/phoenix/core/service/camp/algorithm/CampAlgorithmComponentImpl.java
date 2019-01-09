/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.myteay.phoenix.common.dal.camp.daointerface.CampAlgorithmDAO;
import com.myteay.phoenix.common.dal.camp.dataobject.CampAlgorithmDO;
import com.myteay.phoenix.core.service.camp.algorithm.enums.CampAlgorithmStatusEnum;
import com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmModel;
import com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmResult;

/**
 * 抽奖算法组件
 * 
 * @author danlley
 * @version $Id: CampAlgorithmComponentImpl.java, v 0.1 Jan 7, 2019 1:11:08 PM danlley Exp $
 */
public class CampAlgorithmComponentImpl implements CampAlgorithmComponent {

    /** 日志 */
    public static final Logger          logger = Logger.getLogger(CampAlgorithmComponentImpl.class);

    /**  */
    private CampAlgorithmDAO            campAlgorithmDAO;

    /**  */
    private CampAlgorithmCacheComponent campAlgorithmCacheComponent;

    /** 
     * @see com.myteay.phoenix.core.service.camp.algorithm.CampAlgorithmComponent#execute(java.lang.String)
     */
    @Override
    public CampAlgorithmResult<CampAlgorithmModel> execute(String campId) {
        logger.warn("开始抽奖 campId=" + campId);
        List<CampAlgorithmModel> list = campAlgorithmCacheComponent.findPrizeListByCampId(campId).getResult();
        Collections.sort(list);

        for (CampAlgorithmModel campAlgorithmModel : list) {
            if (doAlgorithm(campAlgorithmModel)) {
                return new CampAlgorithmResult<>(campAlgorithmModel);
            }
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
        if (currentPercent < percent) {
            return false;
        }

        // step 2: 奖位分布不中奖的情况

        // step 3: 检查结束，直接出奖
        return true;
    }

    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.nextInt(100));
        System.out.println(random.nextInt(100));
        System.out.println(random.nextInt(100));
        System.out.println(random.nextInt(100));
        System.out.println(random.nextInt(100));
        System.out.println(random.nextInt(100));
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

        CampAlgorithmDO campAlgorithmDO = constructAlgorithmDO(campAlgorithmModel);
        String prizeId = campAlgorithmDAO.insert(campAlgorithmDO);

        CampAlgorithmDO freshAlgorithmDO = campAlgorithmDAO.findCampAlgorithmByPrizeId(prizeId);
        CampAlgorithmModel freshAlgorithmModel = constructAlgorithmModel(freshAlgorithmDO);

        refreshAlgorithmCache(freshAlgorithmModel);

        return new CampAlgorithmResult<>(freshAlgorithmModel);
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

}
