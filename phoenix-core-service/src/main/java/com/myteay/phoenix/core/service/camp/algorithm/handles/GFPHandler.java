/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm.handles;

import org.springframework.util.CollectionUtils;

import com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmModel;

/**
 * 按时段、频度分布规则
 * 
 * @author danlley
 * @version $Id: GFPHandler.java, v 0.1 Jan 3, 2019 11:20:24 PM danlley Exp $
 */
public class GFPHandler implements Handler {

    /** 
     * @see com.myteay.phoenix.core.service.camp.algorithm.handles.Handler#doDistribution(com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmModel)
     */
    @Override
    public boolean doDistribution(CampAlgorithmModel campAlgorithmModel) {
        if (campAlgorithmModel == null || campAlgorithmModel.getDistributionModel() == null
            || CollectionUtils.isEmpty(campAlgorithmModel.getDistributionModel().getGfpModelMap())) {
            return false;
        }
        return false;
    }

}
