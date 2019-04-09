/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm.handles;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.myteay.common.util.tools.DateUtil;
import com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmModel;
import com.myteay.phoenix.core.service.camp.algorithm.model.GPModel;
import com.myteay.phoenix.core.service.camp.algorithm.model.GpControlModel;

/**
 * 按频度分布规则
 * 
 * @author danlley
 * @version $Id: GPHandler.java, v 0.1 Jan 3, 2019 11:20:01 PM danlley Exp $
 */
public class GPHandler implements Handler {

    /** 奖品奖位缓存 */
    private static final Map<String, GpControlModel> GP_CONTROL_MAP = Collections.synchronizedMap(new HashMap<>());

    /** 
     * @see com.myteay.phoenix.core.service.camp.algorithm.handles.Handler#doDistribution(com.myteay.phoenix.core.service.camp.algorithm.model.CampAlgorithmModel)
     */
    @Override
    public boolean doDistribution(CampAlgorithmModel campAlgorithmModel) {

        // 异常情况均不出奖
        if (campAlgorithmModel == null || campAlgorithmModel.getDistributionModel() == null || StringUtils.isBlank(campAlgorithmModel.getCampId())
            || StringUtils.isBlank(campAlgorithmModel.getPrizeId()) || campAlgorithmModel.getDistributionModel().getGpModel() == null) {
            return false;
        }

        GPModel gpModel = campAlgorithmModel.getDistributionModel().getGpModel();

        // 定义缓存关键字规则
        String key = campAlgorithmModel.getCampId() + "_" + campAlgorithmModel.getPrizeId();

        GpControlModel gpControlModel = GP_CONTROL_MAP.get(key);

        // 出奖周期缓存采用惰性加载机制，这里暂时不会出现并发问题，并发处理暂时忽略
        if (gpControlModel == null) {
            gpControlModel = new GpControlModel();
            gpControlModel.setCampId(campAlgorithmModel.getCampId());
            gpControlModel.setPrizeId(campAlgorithmModel.getPrizeId());
            GP_CONTROL_MAP.put(key, gpControlModel);
        }

        int prizeAmount = gpControlModel.getPrizeAmount();

        // 系统设定的奖品数量大于当前已经抵扣掉的奖品数量，则直接进行出奖，并将缓存中的奖品数量加一
        if (gpModel.getMinuteAmount() > prizeAmount) {
            prizeAmount++;
            gpControlModel.setPrizeAmount(prizeAmount);
            return true;
        }

        /*
         * 如果系统设定的奖品数量小于等于当前累计周期的数量，做如下处理：
         * 
         * 1、时间已经过了一个累计周期，刷新缓存中的累计时间起点，重新计算周期出奖数量，并出奖
         * 2、时间没有到累计周期，则不允许出奖
         */
        if (gpModel.getMinuteAmount() <= prizeAmount) {
            Date lastTimeDate = gpControlModel.getLastPrizeTime();
            Date comparedDate = DateUtil.addMinutes(new Date(), -30);
            if (lastTimeDate != null && comparedDate.after(lastTimeDate)) {
                gpControlModel.setLastPrizeTime(new Date());
                gpControlModel.setPrizeAmount(1);
                return true;
            }
            return false;
        }

        // 其他非可预期的情况均不出奖
        return false;
    }

}
