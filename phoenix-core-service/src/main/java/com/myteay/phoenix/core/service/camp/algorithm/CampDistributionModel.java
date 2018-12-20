/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm;

import java.io.Serializable;

/**
 * 奖位段模型
 * 
 * @author danlley
 * @version $Id: CampDistributionModel.java, v 0.1 Dec 21, 2018 12:37:26 AM danlley Exp $
 */
public class CampDistributionModel implements Serializable {

    /** serialVersionUID */
    private static final long     serialVersionUID = 723541011899072430L;

    /** 抽奖算法类型 */
    private CampAlgorithmTypeEnum algorithmType;

    /**  */
    private int                   dAmount;
}
