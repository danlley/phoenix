/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm;

import org.apache.log4j.Logger;

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
    public CampAlgorithmResult<String> initAlgorithm(CampAlgorithmModel campAlgorithmModel, int operationLevel) {
        logger.warn("开始准备抽奖数据 campAlgorithmModel=" + campAlgorithmModel + " operationLevel=" + operationLevel);
        return null;
    }

}
