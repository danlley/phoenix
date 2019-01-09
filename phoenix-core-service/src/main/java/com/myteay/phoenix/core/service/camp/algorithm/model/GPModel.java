/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm.model;

import java.io.Serializable;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 按分钟为单位的频度出奖
 * 
 * @author danlley
 * @version $Id: GpModel.java, v 0.1 Dec 21, 2018 1:28:04 AM danlley Exp $
 */
public class GPModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -8194999971144098948L;

    /** 出奖频度 */
    private int               minute;

    /** 出奖频度允许最大出奖个数 */
    private int               minuteAmount;

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
