/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm;

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
    public static final Logger logger = Logger.getLogger(CampAlgorithmComponentImpl.class);

    /**  */
    private CampAlgorithmDAO   campAlgorithmDAO;

    /** 
     * @see com.myteay.phoenix.core.service.camp.algorithm.CampAlgorithmComponent#execute(java.lang.String)
     */
    @Override
    public CampAlgorithmResult<CampAlgorithmModel> execute(String campId) {
        logger.warn("开始抽奖 campId=" + campId);
        return null;
    }

    /** 
     * @see com.myteay.phoenix.core.service.camp.algorithm.CampAlgorithmComponent#initAlgorithm(com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmModel, int)
     */
    @Override
    public CampAlgorithmResult<CampAlgorithmModel> initAlgorithm(CampAlgorithmModel campAlgorithmModel, int operationLevel) {
        logger.warn("开始准备抽奖数据 campAlgorithmModel=" + campAlgorithmModel + " operationLevel=" + operationLevel);

        CampAlgorithmDO campAlgorithmDO = constructAlgorithmDO(campAlgorithmModel);
        String prizeId = campAlgorithmDAO.insert(campAlgorithmDO);

        CampAlgorithmDO freshAlgorithmDO = campAlgorithmDAO.findCampAlgorithmByPrizeId(prizeId);
        CampAlgorithmModel freshAlgorithmModel = constructAlgorithmModel(freshAlgorithmDO);
        return new CampAlgorithmResult<>(freshAlgorithmModel);
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

}
