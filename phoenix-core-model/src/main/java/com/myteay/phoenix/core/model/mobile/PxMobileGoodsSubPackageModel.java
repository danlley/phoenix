/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.mobile;

import java.io.Serializable;

import com.myteay.common.util.tools.ToStringUtil;
import com.myteay.phoenix.common.util.manage.enums.PxSubPackagesTypeEnum;

/**
 * 手机端子套餐模型
 * 
 * @author danlley
 * @version $Id: PxMobileGoodsSubPackageModel.java, v 0.1 Aug 19, 2018 9:14:48 PM danlley Exp $
 */
public class PxMobileGoodsSubPackageModel implements Serializable {

    /** serialVersionUID */
    private static final long     serialVersionUID = -1254223297370917584L;

    /** 子套餐ID */
    private String                subPackagesId;

    /** 子套餐名称 */
    private String                subPackagesName;

    /** 子套餐数量 */
    private String                subPackagesAmount;

    /** 子套餐类型 */
    private PxSubPackagesTypeEnum subPackagesType;

    /** 子套餐包类型说明 */
    private String                subPackagesTypeShow;

    /** 子套餐价格 */
    private String                subPackagePrice;

    /**
     * Getter method for property <tt>subPackagesTypeShow</tt>.
     * 
     * @return property value of subPackagesTypeShow
     */
    public String getSubPackagesTypeShow() {
        return subPackagesTypeShow;
    }

    /**
     * Setter method for property <tt>subPackagesTypeShow</tt>.
     * 
     * @param subPackagesTypeShow value to be assigned to property subPackagesTypeShow
     */
    public void setSubPackagesTypeShow(String subPackagesTypeShow) {
        this.subPackagesTypeShow = subPackagesTypeShow;
    }

    /**
     * Getter method for property <tt>subPackagesId</tt>.
     * 
     * @return property value of subPackagesId
     */
    public String getSubPackagesId() {
        return subPackagesId;
    }

    /**
     * Setter method for property <tt>subPackagesId</tt>.
     * 
     * @param subPackagesId value to be assigned to property subPackagesId
     */
    public void setSubPackagesId(String subPackagesId) {
        this.subPackagesId = subPackagesId;
    }

    /**
     * Getter method for property <tt>subPackagesName</tt>.
     * 
     * @return property value of subPackagesName
     */
    public String getSubPackagesName() {
        return subPackagesName;
    }

    /**
     * Setter method for property <tt>subPackagesName</tt>.
     * 
     * @param subPackagesName value to be assigned to property subPackagesName
     */
    public void setSubPackagesName(String subPackagesName) {
        this.subPackagesName = subPackagesName;
    }

    /**
     * Getter method for property <tt>subPackagesAmount</tt>.
     * 
     * @return property value of subPackagesAmount
     */
    public String getSubPackagesAmount() {
        return subPackagesAmount;
    }

    /**
     * Setter method for property <tt>subPackagesAmount</tt>.
     * 
     * @param subPackagesAmount value to be assigned to property subPackagesAmount
     */
    public void setSubPackagesAmount(String subPackagesAmount) {
        this.subPackagesAmount = subPackagesAmount;
    }

    /**
     * Getter method for property <tt>subPackagesType</tt>.
     * 
     * @return property value of subPackagesType
     */
    public PxSubPackagesTypeEnum getSubPackagesType() {
        return subPackagesType;
    }

    /**
     * Setter method for property <tt>subPackagesType</tt>.
     * 
     * @param subPackagesType value to be assigned to property subPackagesType
     */
    public void setSubPackagesType(PxSubPackagesTypeEnum subPackagesType) {
        this.subPackagesType = subPackagesType;

        if (subPackagesType != null) {
            this.setSubPackagesTypeShow(subPackagesType.getMessage());
        }
    }

    /**
     * Getter method for property <tt>subPackagePrice</tt>.
     * 
     * @return property value of subPackagePrice
     */
    public String getSubPackagePrice() {
        return subPackagePrice;
    }

    /**
     * Setter method for property <tt>subPackagePrice</tt>.
     * 
     * @param subPackagePrice value to be assigned to property subPackagePrice
     */
    public void setSubPackagePrice(String subPackagePrice) {
        this.subPackagePrice = subPackagePrice;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }

}
