/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.camp;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;
import com.myteay.phoenix.common.util.camp.enums.CampPrizeOutStatusEnum;
import com.myteay.phoenix.common.util.camp.enums.CampPrizeStatusEnum;

/**
 * 中奖流水操作模型
 * 
 * @author danlley
 * @version $Id: CampShopPrizeOutModel.java, v 0.1 Mar 27, 2019 11:10:42 PM danlley Exp $
 */
public class CampShopPrizeOutModel implements Serializable {

    /** serialVersionUID */
    private static final long      serialVersionUID = 1959003033730190246L;

    /** 奖品流水 */
    private String                 campPrizeOutId;

    /** 会员ID */
    private String                 userId;

    /** 活动ID */
    private String                 campId;

    /** 活动名称 */
    private String                 campName;

    /** 奖品ID */
    private String                 prizeId;

    /** 奖品名称 */
    private String                 prizeName;

    /** 店铺ID */
    private String                 shopId;

    /** 店铺名称 */
    private String                 shopName;

    /** 奖品价值 */
    private String                 price;

    /** 奖品等级 */
    private String                 prizeLevel;

    /** 手机号 */
    private String                 mobileNo;

    /** 订单号(产生了优惠券的订单) */
    private String                 orderNo;

    /** 订单号(使用过优惠券的订单) */
    private String                 consumedOrderNo;

    /** 奖品状态 */
    private CampPrizeStatusEnum    prizeStatus;

    /** 奖品使用状态 */
    private CampPrizeOutStatusEnum prizeOutStatus;

    /** 奖品生效日期 */
    private Date                   prizeEffictive;

    /** 奖品过期时间 */
    private Date                   prizeExpired;

    /** 记录创建时间 */
    private Date                   gmtCreated;

    /** 记录最后修改时间 */
    private Date                   gmtModified;

    /** 奖品模型 */
    private CampPrizeModel         campPrizeModel;

    /**
     * Getter method for property <tt>consumedOrderNo</tt>.
     * 
     * @return property value of consumedOrderNo
     */
    public String getConsumedOrderNo() {
        return consumedOrderNo;
    }

    /**
     * Setter method for property <tt>consumedOrderNo</tt>.
     * 
     * @param consumedOrderNo value to be assigned to property consumedOrderNo
     */
    public void setConsumedOrderNo(String consumedOrderNo) {
        this.consumedOrderNo = consumedOrderNo;
    }

    /**
     * Getter method for property <tt>campPrizeModel</tt>.
     * 
     * @return property value of campPrizeModel
     */
    public CampPrizeModel getCampPrizeModel() {
        return campPrizeModel;
    }

    /**
     * Setter method for property <tt>campPrizeModel</tt>.
     * 
     * @param campPrizeModel value to be assigned to property campPrizeModel
     */
    public void setCampPrizeModel(CampPrizeModel campPrizeModel) {
        this.campPrizeModel = campPrizeModel;
    }

    /**
     * Getter method for property <tt>campPrizeOutId</tt>.
     * 
     * @return property value of campPrizeOutId
     */
    public String getCampPrizeOutId() {
        return campPrizeOutId;
    }

    /**
     * Setter method for property <tt>campPrizeOutId</tt>.
     * 
     * @param campPrizeOutId value to be assigned to property campPrizeOutId
     */
    public void setCampPrizeOutId(String campPrizeOutId) {
        this.campPrizeOutId = campPrizeOutId;
    }

    /**
     * Getter method for property <tt>userId</tt>.
     * 
     * @return property value of userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Setter method for property <tt>userId</tt>.
     * 
     * @param userId value to be assigned to property userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
     * Getter method for property <tt>mobileNo</tt>.
     * 
     * @return property value of mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * Setter method for property <tt>mobileNo</tt>.
     * 
     * @param mobileNo value to be assigned to property mobileNo
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * Getter method for property <tt>orderNo</tt>.
     * 
     * @return property value of orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * Setter method for property <tt>orderNo</tt>.
     * 
     * @param orderNo value to be assigned to property orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * Getter method for property <tt>prizeStatus</tt>.
     * 
     * @return property value of prizeStatus
     */
    public CampPrizeStatusEnum getPrizeStatus() {
        return prizeStatus;
    }

    /**
     * Setter method for property <tt>prizeStatus</tt>.
     * 
     * @param prizeStatus value to be assigned to property prizeStatus
     */
    public void setPrizeStatus(CampPrizeStatusEnum prizeStatus) {
        this.prizeStatus = prizeStatus;
    }

    /**
     * Getter method for property <tt>prizeOutStatus</tt>.
     * 
     * @return property value of prizeOutStatus
     */
    public CampPrizeOutStatusEnum getPrizeOutStatus() {
        return prizeOutStatus;
    }

    /**
     * Setter method for property <tt>prizeOutStatus</tt>.
     * 
     * @param prizeOutStatus value to be assigned to property prizeOutStatus
     */
    public void setPrizeOutStatus(CampPrizeOutStatusEnum prizeOutStatus) {
        this.prizeOutStatus = prizeOutStatus;
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
