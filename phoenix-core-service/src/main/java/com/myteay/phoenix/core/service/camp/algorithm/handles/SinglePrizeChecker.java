/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm.handles;

import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmModel;

/**
 * 奖品的个性化抽奖检查器
 * 
 * @author danlley
 * @version $Id: SinglePrizeChecker.java, v 0.1 Apr 20, 2019 8:45:11 PM danlley Exp $
 */
public abstract class SinglePrizeChecker implements Comparable<SinglePrizeChecker> {

    /** 日志 */
    public static final Logger logger   = LoggerFactory.getLogger(LoggerNames.PX_CAMP);

    /** 执行等级 */
    private int                priority = 999;

    /** 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public abstract int compareTo(SinglePrizeChecker o);

    /**
     * 执行对奖品的个性化抽奖检查
     * 
     * 注： 具体实现由外部模型完成
     * 
     * @param campAlgorithmModel
     * @return
     */
    public boolean doCheck(CampAlgorithmModel campAlgorithmModel) {
        return true;
    }

    /**
     * Getter method for property <tt>priority</tt>.
     * 
     * @return property value of priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Setter method for property <tt>priority</tt>.
     * 
     * @param priority value to be assigned to property priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

}
