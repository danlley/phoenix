/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.camp.dataobject;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 店内营销活动奖品数据模型
 * 
 * @author danlley
 * @version $Id: CampPrizeDO.java, v 0.1 Dec 17, 2018 6:32:59 PM danlley Exp $
 */
public class CampPrizeDO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -3631822148203466634L;

    /** 奖品ID */
    private String            prizeId;

    /** 奖品名称 */
    private String            prizeName;

    /** 奖品类型 */
    private String            prizeType;

    /** 奖品使用人群 */
    private String            prizeLimit;

    /** 活动ID */
    private String            campId;

    /** 店铺ID */
    private String            shopId;

    /** 奖品等级 */
    private String            prizeLevel;

    /** 奖品比率 */
    private String            prizePercent;

    /** 奖位分布 */
    private String            distribution;

    /** 奖品单位价值 */
    private String            price;

    /** 奖品数量 */
    private String            prizeAmount;

    /** 奖品状态 */
    private String            prizeStatus;

    /** 奖品起效时间 */
    private Date              prizeEffictive;

    /** 奖品过期时间 */
    private Date              prizeExpired;

    /** 创建时间 */
    private Date              gmtCreated;

    /** 最后修改时间 */
    private Date              gmtModified;

    /**
     * Getter method for property <tt>prizeType</tt>.
     * 
     * @return property value of prizeType
     */
    public String getPrizeType() {
        return prizeType;
    }

    /**
     * Setter method for property <tt>prizeType</tt>.
     * 
     * @param prizeType value to be assigned to property prizeType
     */
    public void setPrizeType(String prizeType) {
        this.prizeType = prizeType;
    }

    /**
     * Getter method for property <tt>prizeLimit</tt>.
     * 
     * @return property value of prizeLimit
     */
    public String getPrizeLimit() {
        return prizeLimit;
    }

    /**
     * Setter method for property <tt>prizeLimit</tt>.
     * 
     * @param prizeLimit value to be assigned to property prizeLimit
     */
    public void setPrizeLimit(String prizeLimit) {
        this.prizeLimit = prizeLimit;
    }

    /**
     * Getter method for property <tt>prizeStatus</tt>.
     * 
     * @return property value of prizeStatus
     */
    public String getPrizeStatus() {
        return prizeStatus;
    }

    /**
     * Setter method for property <tt>prizeStatus</tt>.
     * 
     * @param prizeStatus value to be assigned to property prizeStatus
     */
    public void setPrizeStatus(String prizeStatus) {
        this.prizeStatus = prizeStatus;
    }

    /**
     * Getter method for property <tt>prizeId</tt>.
     * 
     * @return property value of prizeId
     */
    public String getPrizeId() {
        return prizeId;
    }

    /**
     * Setter method for property <tt>prizeId</tt>.
     * 
     * @param prizeId value to be assigned to property prizeId
     */
    public void setPrizeId(String prizeId) {
        this.prizeId = prizeId;
    }

    /**
     * Getter method for property <tt>prizeName</tt>.
     * 
     * @return property value of prizeName
     */
    public String getPrizeName() {
        return prizeName;
    }

    /**
     * Setter method for property <tt>prizeName</tt>.
     * 
     * @param prizeName value to be assigned to property prizeName
     */
    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

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
     * Getter method for property <tt>prizeLevel</tt>.
     * 
     * @return property value of prizeLevel
     */
    public String getPrizeLevel() {
        return prizeLevel;
    }

    /**
     * Setter method for property <tt>prizeLevel</tt>.
     * 
     * @param prizeLevel value to be assigned to property prizeLevel
     */
    public void setPrizeLevel(String prizeLevel) {
        this.prizeLevel = prizeLevel;
    }

    /**
     * Getter method for property <tt>prizePercent</tt>.
     * 
     * @return property value of prizePercent
     */
    public String getPrizePercent() {
        return prizePercent;
    }

    /**
     * Setter method for property <tt>prizePercent</tt>.
     * 
     * @param prizePercent value to be assigned to property prizePercent
     */
    public void setPrizePercent(String prizePercent) {
        this.prizePercent = prizePercent;
    }

    /**
     * Getter method for property <tt>distribution</tt>.
     * 
     * @return property value of distribution
     */
    public String getDistribution() {
        return distribution;
    }

    /**
     * Setter method for property <tt>distribution</tt>.
     * 
     * @param distribution value to be assigned to property distribution
     */
    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    /**
     * Getter method for property <tt>price</tt>.
     * 
     * @return property value of price
     */
    public String getPrice() {
        return price;
    }

    /**
     * Setter method for property <tt>price</tt>.
     * 
     * @param price value to be assigned to property price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Getter method for property <tt>prizeAmount</tt>.
     * 
     * @return property value of prizeAmount
     */
    public String getPrizeAmount() {
        return prizeAmount;
    }

    /**
     * Setter method for property <tt>prizeAmount</tt>.
     * 
     * @param prizeAmount value to be assigned to property prizeAmount
     */
    public void setPrizeAmount(String prizeAmount) {
        this.prizeAmount = prizeAmount;
    }

    /**
     * Getter method for property <tt>prizeEffictive</tt>.
     * 
     * @return property value of prizeEffictive
     */
    public Date getPrizeEffictive() {
        return prizeEffictive;
    }

    /**
     * Setter method for property <tt>prizeEffictive</tt>.
     * 
     * @param prizeEffictive value to be assigned to property prizeEffictive
     */
    public void setPrizeEffictive(Date prizeEffictive) {
        this.prizeEffictive = prizeEffictive;
    }

    /**
     * Getter method for property <tt>prizeExpired</tt>.
     * 
     * @return property value of prizeExpired
     */
    public Date getPrizeExpired() {
        return prizeExpired;
    }

    /**
     * Setter method for property <tt>prizeExpired</tt>.
     * 
     * @param prizeExpired value to be assigned to property prizeExpired
     */
    public void setPrizeExpired(Date prizeExpired) {
        this.prizeExpired = prizeExpired;
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
