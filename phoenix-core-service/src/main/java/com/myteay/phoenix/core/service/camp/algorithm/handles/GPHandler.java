/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm.handles;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        if (campAlgorithmModel == null || campAlgorithmModel.getDistributionModel() == null || campAlgorithmModel.getDistributionModel().getGpModel() == null) {
            return false;
        }

        GPModel gpModel = campAlgorithmModel.getDistributionModel().getGpModel();
        String key = campAlgorithmModel.getCampId() + "_" + campAlgorithmModel.getPrizeId();

        GpControlModel gpControlModel = GP_CONTROL_MAP.get(key);
        if (gpControlModel == null) {
            gpControlModel = new GpControlModel();
            gpControlModel.setCampId(campAlgorithmModel.getCampId());
            gpControlModel.setPrizeId(campAlgorithmModel.getPrizeId());
            GP_CONTROL_MAP.put(key, gpControlModel);
        }

        int prizeAmount = gpControlModel.getPrizeAmount();
        if (gpModel.getMinuteAmount() > prizeAmount) {
            prizeAmount++;
            gpControlModel.setPrizeAmount(prizeAmount);
            return true;
        }

        if (gpModel.getMinuteAmount() <= prizeAmount) {
            Date lastTimeDate = gpControlModel.getLastPrizeTime();
            Date comparedDate = DateUtil.addMinutes(new Date(), -30);
            if (lastTimeDate != null && comparedDate.after(lastTimeDate)) {
                gpControlModel.setLastPrizeTime(new Date());
            }
        }

        return false;
    }

}
