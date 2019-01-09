/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm.model;

import java.io.Serializable;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 按时段及频度出奖配置
 * 
 * @author danlley
 * @version $Id: GfpModel.java, v 0.1 Dec 21, 2018 1:28:51 AM danlley Exp $
 */
public class GfpModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 2668435642933470765L;

    /** 出奖时段 */
    private int               hour;

    /** 出奖时段出奖数量 */
    private int               hourAmount;

    /** 出奖频度 */
    private int               minute;

    /** 出奖频度允许最大出奖个数 */
    private int               minuteAmount;

    /**
     * Getter method for property <tt>hour</tt>.
     * 
     * @return property value of hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Setter method for property <tt>hour</tt>.
     * 
     * @param hour value to be assigned to property hour
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * Getter method for property <tt>hourAmount</tt>.
     * 
     * @return property value of hourAmount
     */
    public int getHourAmount() {
        return hourAmount;
    }

    /**
     * Setter method for property <tt>hourAmount</tt>.
     * 
     * @param hourAmount value to be assigned to property hourAmount
     */
    public void setHourAmount(int hourAmount) {
        this.hourAmount = hourAmount;
    }

    /**
     * Getter method for property <tt>minute</tt>.
     * 
     * @return property value of minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Setter method for property <tt>minute</tt>.
     * 
     * @param minute value to be assigned to property minute
     */
    public void setMinute(int minute) {
        this.minute = minute;
    }

    /**
     * Getter method for property <tt>minuteAmount</tt>.
     * 
     * @return property value of minuteAmount
     */
    public int getMinuteAmount() {
        return minuteAmount;
    }

    /**
     * Setter method for property <tt>minuteAmount</tt>.
     * 
     * @param minuteAmount value to be assigned to property minuteAmount
     */
    public void setMinuteAmount(int minuteAmount) {
        this.minuteAmount = minuteAmount;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
