/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm.handles;

import com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmModel;

/**
 * 奖位处理Handler
 * 
 * @author danlley
 * @version $Id: Handler.java, v 0.1 Jan 3, 2019 11:19:20 PM danlley Exp $
 */
public interface Handler {

    /**
     * 执行奖位处理
     * 
     * @param campAlgorithmModel
     * @return
     */
    public boolean doDistribution(CampAlgorithmModel campAlgorithmModel);
}
