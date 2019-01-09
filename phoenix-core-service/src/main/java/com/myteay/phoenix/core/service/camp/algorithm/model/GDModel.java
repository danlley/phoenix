/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm.model;

import java.io.Serializable;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 按时段出奖
 * 
 * @author danlley
 * @version $Id: GDModel.java, v 0.1 Dec 21, 2018 1:26:15 AM danlley Exp $
 */
public class GDModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 4448146157443060259L;

    /** 出奖时段 */
    private int               hour;

    /** 出奖时段出奖数量 */
    private int               hourAmount;

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
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
