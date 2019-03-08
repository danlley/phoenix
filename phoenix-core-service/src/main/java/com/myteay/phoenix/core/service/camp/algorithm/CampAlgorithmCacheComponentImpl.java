/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;

import com.myteay.phoenix.common.dal.camp.daointerface.CampAlgorithmDAO;
import com.myteay.phoenix.common.dal.camp.dataobject.CampAlgorithmDO;
import com.myteay.phoenix.core.service.camp.algorithm.enums.CampAlgorithmStatusEnum;
import com.myteay.phoenix.core.service.camp.algorithm.enums.CampAlgorithmTypeEnum;
import com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmModel;
import com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmResult;
import com.myteay.phoenix.core.service.camp.algorithm.model.CampDistributionModel;
import com.myteay.phoenix.core.service.camp.algorithm.model.GDModel;
import com.myteay.phoenix.core.service.camp.algorithm.model.GPModel;
import com.myteay.phoenix.core.service.camp.algorithm.model.GfpModel;

/**
 * 抽奖算法缓存组件
 * 
 * @author danlley
 * @version $Id: CampAlgorithmCacheComponentImpl.java, v 0.1 Jan 10, 2019 12:57:54 AM danlley Exp $
 */
public class CampAlgorithmCacheComponentImpl implements CampAlgorithmCacheComponent, InitializingBean {

    /** 日志 */
    public static final Logger                                        logger                  = Logger.getLogger(CampAlgorithmCacheComponentImpl.class);

    /** 查询缓存 */
    private static final Map<String, Map<String, CampAlgorithmModel>> ALGORITHM_CACHE         = Collections.synchronizedMap(new HashMap<>());

    /** 抽奖主流程缓存 */
    private static final Map<String, List<CampAlgorithmModel>>        ALGORITHM_PROCESS_CACHE = Collections.synchronizedMap(new HashMap<>());

    /** 抽奖算法管理DAO */
    private CampAlgorithmDAO                                          campAlgorithmDAO;

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        refresh();
    }

    /** 
     * @see com.myteay.phoenix.core.service.camp.algorithm.CampAlgorithmCacheComponent#initCache(com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmModel)
     */
    @Override
    public CampAlgorithmResult<CampAlgorithmModel> initCache(CampAlgorithmModel freshAlgorithmModel) {
        refresh();
        return new CampAlgorithmResult<>(freshAlgorithmModel);
    }

    /** 
     * @see com.myteay.phoenix.core.service.camp.algorithm.CampAlgorithmCacheComponent#findPrizeListByCampId(java.lang.String)
     */
    @Override
    public CampAlgorithmResult<List<CampAlgorithmModel>> findPrizeListByCampId(String campId) {

        if (CollectionUtils.isEmpty(ALGORITHM_PROCESS_CACHE)) {
            return new CampAlgorithmResult<>();
        }

        return new CampAlgorithmResult<>(ALGORITHM_PROCESS_CACHE.get(campId));
    }

    /**
     * 整体刷新
     */
    private void refresh() {
        List<CampAlgorithmDO> freshAlgorithmDOs = campAlgorithmDAO.findCampAlgorithmStart();
        CampAlgorithmModel algorithmModel = null;
        Map<String, Map<String, CampAlgorithmModel>> map = Collections.synchronizedMap(new HashMap<>());
        Map<String, List<CampAlgorithmModel>> processMap = Collections.synchronizedMap(new HashMap<>());
        for (CampAlgorithmDO campAlgorithmDO : freshAlgorithmDOs) {
            algorithmModel = constructAlgorithmModel(campAlgorithmDO);

            if (CollectionUtils.isEmpty(map) || CollectionUtils.isEmpty(map.get(algorithmModel.getCampId()))) {
                map.put(algorithmModel.getCampId(), new HashMap<>());
            }
            map.get(algorithmModel.getCampId()).put(algorithmModel.getPrizeId(), algorithmModel);

            if (CollectionUtils.isEmpty(processMap) || CollectionUtils.isEmpty(processMap.get(algorithmModel.getCampId()))) {
                processMap.put(algorithmModel.getCampId(), new ArrayList<>());
            }
            processMap.get(algorithmModel.getCampId()).add(algorithmModel);
            Collections.sort(processMap.get(algorithmModel.getCampId()));
        }

        synchronized (ALGORITHM_CACHE) {
            ALGORITHM_CACHE.clear();
            ALGORITHM_CACHE.putAll(map);
        }

        synchronized (ALGORITHM_PROCESS_CACHE) {
            ALGORITHM_PROCESS_CACHE.clear();
            ALGORITHM_PROCESS_CACHE.putAll(processMap);
        }
        logger.warn("当前抽奖算法缓存 ALGORITHM_CACHE=" + ALGORITHM_CACHE + " ALGORITHM_PROCESS_CACHE=" + ALGORITHM_PROCESS_CACHE);
    }

    /**
     * 获取算法模型
     * 
     * @param campAlgorithmDO
     * @return
     */
    private CampAlgorithmModel constructAlgorithmModel(CampAlgorithmDO campAlgorithmDO) {
        if (campAlgorithmDO == null) {
            return null;
        }

        CampAlgorithmModel campAlgorithmModel = new CampAlgorithmModel();
        campAlgorithmModel.setAlgorithmStatus(CampAlgorithmStatusEnum.getByValue(campAlgorithmDO.getAlgorithmStatus()));
        campAlgorithmModel.setCampId(campAlgorithmDO.getCampId());
        campAlgorithmModel.setDistribution(campAlgorithmDO.getDistribution());
        campAlgorithmModel.setPercent(campAlgorithmDO.getPercent());
        campAlgorithmModel.setPrizeAmount(campAlgorithmDO.getPrizeAmount());
        campAlgorithmModel.setPrizeId(campAlgorithmDO.getPrizeId());
        campAlgorithmModel.setPrizeLevel(campAlgorithmDO.getPrizeLevel());
        campAlgorithmModel.setGmtCreated(campAlgorithmDO.getGmtCreated());
        campAlgorithmModel.setGmtModified(campAlgorithmDO.getGmtModified());

        initDistributionModel(campAlgorithmModel);

        return campAlgorithmModel;
    }

    /**
     * 初始化奖位分布模型
     * 
     * @param campAlgorithmModel
     */
    private void initDistributionModel(CampAlgorithmModel campAlgorithmModel) {
        String distribution = campAlgorithmModel.getDistribution();

        //奖位分布配置错误，不做出奖分布初始化
        if (StringUtils.isBlank(distribution)) {
            return;
        }

        CampDistributionModel campDistributionModel = null;

        // 按时段、频度分布规则
        if (distribution.indexOf("GFD") == 0) {
            campDistributionModel = new CampDistributionModel();
            campDistributionModel.setAlgorithmType(CampAlgorithmTypeEnum.CAMP_GFD);

            distribution = distribution.replaceAll("GFD\\:\\{\\{", "");
            distribution = distribution.replaceAll("}}", "");
            String[] arr = distribution.split("},\\{");
            if (arr == null || arr.length == 0) {
                return;
            }

            GfpModel gfpModel = null;
            for (String singleDis : arr) {
                arr = singleDis.split(",");
                gfpModel = new GfpModel();

                String[] hourData = arr[0].replaceAll("D:", "").split("/");
                gfpModel.setHourAmount(Integer.parseInt(hourData[0]));
                gfpModel.setHour(Integer.parseInt(hourData[1]));

                String[] minuteData = arr[1].replaceAll("P:", "").split("/");
                gfpModel.setMinuteAmount(Integer.parseInt(minuteData[0]));
                gfpModel.setMinute(Integer.parseInt(minuteData[1]));
                campDistributionModel.getGfpModelMap().put(Integer.toString(gfpModel.getHour()), gfpModel);
            }

            campAlgorithmModel.setDistributionModel(campDistributionModel);
            return;
        }

        // 按频度分布规则
        if (distribution.indexOf("GP") == 0) {
            campDistributionModel = new CampDistributionModel();
            campDistributionModel.setAlgorithmType(CampAlgorithmTypeEnum.CAMP_GP);

            distribution = distribution.replaceAll("GP:\\{", "");
            distribution = distribution.replaceAll("}", "");
            distribution = distribution.replaceAll("P:", "");
            String[] minuteData = distribution.split("/");
            GPModel gpModel = new GPModel();
            gpModel.setMinute(Integer.parseInt(minuteData[1]));
            gpModel.setMinuteAmount(Integer.parseInt(minuteData[0]));
            campDistributionModel.setGpModel(gpModel);

            campAlgorithmModel.setDistributionModel(campDistributionModel);
            return;
        }

        // 按时段分布规则
        if (distribution.indexOf("GD") == 0) {
            campDistributionModel = new CampDistributionModel();
            campDistributionModel.setAlgorithmType(CampAlgorithmTypeEnum.CAMP_GD);

            distribution = distribution.replaceAll("GD:\\{", "");
            distribution = distribution.replaceAll("}", "");
            String[] arr = distribution.split(",");
            GDModel gdModel = null;
            for (String hourData : arr) {
                hourData = hourData.replaceAll("D:", "");
                String[] hourDataArr = hourData.split("/");
                gdModel = new GDModel();
                gdModel.setHour(Integer.parseInt(hourDataArr[1]));
                gdModel.setHourAmount(Integer.parseInt(hourDataArr[0]));
                campDistributionModel.getGdModelMap().put(Integer.toString(gdModel.getHour()), gdModel);
            }

            campAlgorithmModel.setDistributionModel(campDistributionModel);
            return;
        }
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
