/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm;

import java.util.List;

import com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmModel;
import com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmResult;

/**
 * 抽奖算法缓存组件
 * 
 * @author danlley
 * @version $Id: CampAlgorithmCacheComponent.java, v 0.1 Jan 10, 2019 12:55:31 AM danlley Exp $
 */
public interface CampAlgorithmCacheComponent {

    /**
     * 初始化抽奖缓存
     * 
     * @param freshAlgorithmModel
     * @return
     */
    public CampAlgorithmResult<CampAlgorithmModel> initCache(CampAlgorithmModel freshAlgorithmModel);

    /**
     * 获取当前可参与抽奖的奖品列表
     * 
     * @param campId
     * @return
     */
    public CampAlgorithmResult<List<CampAlgorithmModel>> findPrizeListByCampId(String campId);
}
