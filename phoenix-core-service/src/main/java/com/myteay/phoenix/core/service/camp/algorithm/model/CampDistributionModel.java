/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
    private Map<String, GDModel>  gdModelMap       = new HashMap<>();

    /** GP模式的抽奖算法 */
    private GPModel               gpModel;

    /** GFP模式的抽奖算法 */
    private Map<String, GfpModel> gfpModelMap      = new HashMap<>();

    /**
     * Getter method for property <tt>gdModelMap</tt>.
     * 
     * @return property value of gdModelMap
     */
    public Map<String, GDModel> getGdModelMap() {
        return gdModelMap;
    }

    /**
     * Setter method for property <tt>gdModelMap</tt>.
     * 
     * @param gdModelMap value to be assigned to property gdModelMap
     */
    public void setGdModelMap(Map<String, GDModel> gdModelMap) {
        this.gdModelMap = gdModelMap;
    }

    /**
     * Getter method for property <tt>gfpModelMap</tt>.
     * 
     * @return property value of gfpModelMap
     */
    public Map<String, GfpModel> getGfpModelMap() {
        return gfpModelMap;
    }

    /**
     * Setter method for property <tt>gfpModelMap</tt>.
     * 
     * @param gfpModelMap value to be assigned to property gfpModelMap
     */
    public void setGfpModelMap(Map<String, GfpModel> gfpModelMap) {
        this.gfpModelMap = gfpModelMap;
    }

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
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
