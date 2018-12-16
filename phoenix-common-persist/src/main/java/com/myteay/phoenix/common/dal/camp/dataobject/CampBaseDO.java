/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.camp.dataobject;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 针对单个店铺店内消费到场营销活动基本信息数据模型
 * 
 * @author danlley
 * @version $Id: CampBaseDO.java, v 0.1 Dec 16, 2018 6:47:48 PM danlley Exp $
 */
public class CampBaseDO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 5696414965456967300L;

    /** 活动ID */
    private String            campId;

    /** 活动名称 */
    private String            campName;

    /** 店铺ID */
    private String            shopId;

    /** 店铺名称 */
    private String            shopName;

    /** 活动状态 */
    private String            campStatus;

    /** 活动开始时间 */
    private Date              campStart;

    /** 活动结束时间 */
    private Date              campEnd;

    /** 创建时间 */
    private Date              gmtCreated;

    /** 最后修改时间 */
    private Date              gmtModified;

    /**
     * Getter method for property <tt>campId</tt>.
     * 
     * @return property value of campId
     */
    public String getCampId() {
        return campId;
    }

    /**
     * Setter method for property <tt>campId</tt>.
     * 
     * @param campId value to be assigned to property campId
     */
    public void setCampId(String campId) {
        this.campId = campId;
    }

    /**
     * Getter method for property <tt>campName</tt>.
     * 
     * @return property value of campName
     */
    public String getCampName() {
        return campName;
    }

    /**
     * Setter method for property <tt>campName</tt>.
     * 
     * @param campName value to be assigned to property campName
     */
    public void setCampName(String campName) {
        this.campName = campName;
    }

    /**
     * Getter method for property <tt>shopId</tt>.
     * 
     * @return property value of shopId
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * Setter method for property <tt>shopId</tt>.
     * 
     * @param shopId value to be assigned to property shopId
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    /**
     * Getter method for property <tt>shopName</tt>.
     * 
     * @return property value of shopName
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * Setter method for property <tt>shopName</tt>.
     * 
     * @param shopName value to be assigned to property shopName
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * Getter method for property <tt>campStatus</tt>.
     * 
     * @return property value of campStatus
     */
    public String getCampStatus() {
        return campStatus;
    }

    /**
     * Setter method for property <tt>campStatus</tt>.
     * 
     * @param campStatus value to be assigned to property campStatus
     */
    public void setCampStatus(String campStatus) {
        this.campStatus = campStatus;
    }

    /**
     * Getter method for property <tt>campStart</tt>.
     * 
     * @return property value of campStart
     */
    public Date getCampStart() {
        return campStart;
    }

    /**
     * Setter method for property <tt>campStart</tt>.
     * 
     * @param campStart value to be assigned to property campStart
     */
    public void setCampStart(Date campStart) {
        this.campStart = campStart;
    }

    /**
     * Getter method for property <tt>campEnd</tt>.
     * 
     * @return property value of campEnd
     */
    public Date getCampEnd() {
        return campEnd;
    }

    /**
     * Setter method for property <tt>campEnd</tt>.
     * 
     * @param campEnd value to be assigned to property campEnd
     */
    public void setCampEnd(Date campEnd) {
        this.campEnd = campEnd;
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
