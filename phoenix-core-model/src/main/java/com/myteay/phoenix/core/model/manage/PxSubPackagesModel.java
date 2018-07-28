/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;
import com.myteay.phoenix.common.util.enums.PxOperationTypeEnum;
import com.myteay.phoenix.common.util.manage.enums.PxSubPackagesTypeEnum;

/**
 * 子套餐模型
 * 
 * @author min.weixm
 * @version $Id: PxSubPackagesModel.java, v 0.1 Jul 28, 2018 10:46:27 AM min.weixm Exp $
 */
public class PxSubPackagesModel implements Serializable {

    /** serialVersionUID */
    private static final long     serialVersionUID = -5344719670918811033L;

    /** 套餐子商品ID */
    private String                subPackagesId;

    /** 套餐包ID */
    private String                packagesDetailId;

    /** 子商品名称 */
    private String                subPackagesName;

    /** 子商品数量 */
    private String                subPackagesAmount;

    /** 套餐类型，如：大份、小份等 */
    private PxSubPackagesTypeEnum subPackagesType;

    /** 子商品单价 */
    private String                subPackagePrice;

    /** 操作类型 */
    private PxOperationTypeEnum   operationType;

    /** 创建时间 */
    private Date                  gmtCreated;

    /** 最后修改时间 */
    private Date                  gmtModified;

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
     * Getter method for property <tt>packagesDetailId</tt>.
     * 
     * @return property value of packagesDetailId
     */
    public String getPackagesDetailId() {
        return packagesDetailId;
    }

    /**
     * Setter method for property <tt>packagesDetailId</tt>.
     * 
     * @param packagesDetailId value to be assigned to property packagesDetailId
     */
    public void setPackagesDetailId(String packagesDetailId) {
        this.packagesDetailId = packagesDetailId;
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
     * Getter method for property <tt>operationType</tt>.
     * 
     * @return property value of operationType
     */
    public PxOperationTypeEnum getOperationType() {
        return operationType;
    }

    /**
     * Setter method for property <tt>operationType</tt>.
     * 
     * @param operationType value to be assigned to property operationType
     */
    public void setOperationType(PxOperationTypeEnum operationType) {
        this.operationType = operationType;
    }

    /**
     * Getter method for property <tt>gmtCreated</tt>.
     * 
     * @return property value of gmtCreated
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * Setter method for property <tt>gmtCreated</tt>.
     * 
     * @param gmtCreated value to be assigned to property gmtCreated
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * Getter method for property <tt>gmtModified</tt>.
     * 
     * @return property value of gmtModified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * Setter method for property <tt>gmtModified</tt>.
     * 
     * @param gmtModified value to be assigned to property gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
