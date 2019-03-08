/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm.model;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 针对单个奖品的全局频次控制模型
 * 
 * @author danlley
 * @version $Id: GpControlModel.java, v 0.1 Mar 7, 2019 5:54:01 PM danlley Exp $
 */
public class GpControlModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 43543651985291534L;

    /** 当前时段的出奖数量 */
    private int               prizeAmount      = 0;

    /** 一次出奖周期的第一次出奖时间 */
    private Date              lastPrizeTime    = new Date();

    /** 活动Id */
    private String            campId;

    /** 奖品ID */
    private String            prizeId;

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
     * Getter method for property <tt>lastPrizeTime</tt>.
     * 
     * @return property value of lastPrizeTime
     */
    public Date getLastPrizeTime() {
        return lastPrizeTime;
    }

    /**
     * Setter method for property <tt>lastPrizeTime</tt>.
     * 
     * @param lastPrizeTime value to be assigned to property lastPrizeTime
     */
    public void setLastPrizeTime(Date lastPrizeTime) {
        this.lastPrizeTime = lastPrizeTime;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
