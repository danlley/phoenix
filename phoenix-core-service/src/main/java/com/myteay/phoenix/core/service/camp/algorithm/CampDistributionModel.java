/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm;

import java.io.Serializable;

/**
 * 奖位段模型
 * <pre>
 *      说明：使用时，根据algorithmType属性的实际值，选取相应的模型数据执行抽奖算法
 * 
 * 
 * @author danlley
 * @version $Id: CampDistributionModel.java, v 0.1 Dec 21, 2018 12:37:26 AM danlley Exp $
 */
public class CampDistributionModel implements Serializable {

    /** serialVersionUID */
    private static final long     serialVersionUID = 723541011899072430L;

    /** 抽奖算法类型 */
    private CampAlgorithmTypeEnum algorithmType;

    /** GD模式的抽奖算法 */
    private GDModel               gdModel;

    /** GP模式的抽奖算法 */
    private GpModel               gpModel;

    /** GFP模式的抽奖算法 */
    private GfpModel              gfpModel;

}
