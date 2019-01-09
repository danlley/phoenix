/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm.model;

import java.io.Serializable;

import com.myteay.common.util.tools.ToStringUtil;
import com.myteay.phoenix.core.service.camp.algorithm.enums.CampAlgorithmTypeEnum;

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
    private GPModel               gpModel;

    /** GFP模式的抽奖算法 */
    private GfpModel              gfpModel;

    /**
     * Getter method for property <tt>algorithmType</tt>.
     * 
     * @return property value of algorithmType
     */
    public CampAlgorithmTypeEnum getAlgorithmType() {
        return algorithmType;
    }

    /**
     * Setter method for property <tt>algorithmType</tt>.
     * 
     * @param algorithmType value to be assigned to property algorithmType
     */
    public void setAlgorithmType(CampAlgorithmTypeEnum algorithmType) {
        this.algorithmType = algorithmType;
    }

    /**
     * Getter method for property <tt>gdModel</tt>.
     * 
     * @return property value of gdModel
     */
    public GDModel getGdModel() {
        return gdModel;
    }

    /**
     * Setter method for property <tt>gdModel</tt>.
     * 
     * @param gdModel value to be assigned to property gdModel
     */
    public void setGdModel(GDModel gdModel) {
        this.gdModel = gdModel;
    }

    /**
     * Getter method for property <tt>gpModel</tt>.
     * 
     * @return property value of gpModel
     */
    public GPModel getGpModel() {
        return gpModel;
    }

    /**
     * Setter method for property <tt>gpModel</tt>.
     * 
     * @param gpModel value to be assigned to property gpModel
     */
    public void setGpModel(GPModel gpModel) {
        this.gpModel = gpModel;
    }

    /**
     * Getter method for property <tt>gfpModel</tt>.
     * 
     * @return property value of gfpModel
     */
    public GfpModel getGfpModel() {
        return gfpModel;
    }

    /**
     * Setter method for property <tt>gfpModel</tt>.
     * 
     * @param gfpModel value to be assigned to property gfpModel
     */
    public void setGfpModel(GfpModel gfpModel) {
        this.gfpModel = gfpModel;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
