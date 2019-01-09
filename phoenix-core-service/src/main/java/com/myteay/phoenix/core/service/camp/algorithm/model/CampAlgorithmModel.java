/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm.model;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;
import com.myteay.phoenix.core.service.camp.algorithm.enums.CampAlgorithmStatusEnum;

/**
 * 抽奖算法模型
 * 
 * @author danlley
 * @version $Id: CampAlgorithmModel.java, v 0.1 Dec 21, 2018 12:20:43 AM danlley Exp $
 */
public class CampAlgorithmModel implements Serializable, Comparable<CampAlgorithmModel> {

    /** serialVersionUID */
    private static final long       serialVersionUID = -7156879174232726370L;

    /** 中奖几率 */
    private int                     percent;

    /** 活动ID */
    private String                  campId;

    /** 奖品ID */
    private String                  prizeId;

    /** 奖品ID */
    private CampAlgorithmStatusEnum algorithmStatus;

    /** 奖品等级 */
    private int                     prizeLevel;

    /** 奖位分布 */
    private String                  distribution;

    /** 奖品数量 */
    private int                     prizeAmount;

    /** 奖位分布模型 */
    private CampDistributionModel   distributionModel;

    /** 创建时间 */
    private Date                    gmtCreated;

    /** 最后修改时间 */
    private Date                    gmtModified;

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
     * Getter method for property <tt>algorithmStatus</tt>.
     * 
     * @return property value of algorithmStatus
     */
    public CampAlgorithmStatusEnum getAlgorithmStatus() {
        return algorithmStatus;
    }

    /**
     * Setter method for property <tt>algorithmStatus</tt>.
     * 
     * @param algorithmStatus value to be assigned to property algorithmStatus
     */
    public void setAlgorithmStatus(CampAlgorithmStatusEnum algorithmStatus) {
        this.algorithmStatus = algorithmStatus;
    }

    /**
     * Getter method for property <tt>distributionModel</tt>.
     * 
     * @return property value of distributionModel
     */
    public CampDistributionModel getDistributionModel() {
        return distributionModel;
    }

    /**
     * Setter method for property <tt>distributionModel</tt>.
     * 
     * @param distributionModel value to be assigned to property distributionModel
     */
    public void setDistributionModel(CampDistributionModel distributionModel) {
        this.distributionModel = distributionModel;
    }

    /**
     * Getter method for property <tt>percent</tt>.
     * 
     * @return property value of percent
     */
    public int getPercent() {
        return percent;
    }

    /**
     * Setter method for property <tt>percent</tt>.
     * 
     * @param percent value to be assigned to property percent
     */
    public void setPercent(int percent) {
        this.percent = percent;
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
     * Getter method for property <tt>prizeLevel</tt>.
     * 
     * @return property value of prizeLevel
     */
    public int getPrizeLevel() {
        return prizeLevel;
    }

    /**
     * Setter method for property <tt>prizeLevel</tt>.
     * 
     * @param prizeLevel value to be assigned to property prizeLevel
     */
    public void setPrizeLevel(int prizeLevel) {
        this.prizeLevel = prizeLevel;
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
     * Getter method for property <tt>prizeAmount</tt>.
     * 
     * @return property value of prizeAmount
     */
    public int getPrizeAmount() {
        return prizeAmount;
    }

    /**
     * Setter method for property <tt>prizeAmount</tt>.
     * 
     * @param prizeAmount value to be assigned to property prizeAmount
     */
    public void setPrizeAmount(int prizeAmount) {
        this.prizeAmount = prizeAmount;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }

    /** 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(CampAlgorithmModel o) {
        // TODO Auto-generated method stub
        return this.prizeLevel - o.prizeLevel;
    }
}
