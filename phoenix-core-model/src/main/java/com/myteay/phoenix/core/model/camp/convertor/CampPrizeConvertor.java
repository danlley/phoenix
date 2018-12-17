/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.camp.convertor;

import org.apache.commons.lang.StringUtils;

import com.myteay.phoenix.common.dal.camp.dataobject.CampPrizeDO;
import com.myteay.phoenix.common.util.camp.enums.CampPrizeStatusEnum;
import com.myteay.phoenix.core.model.camp.CampPrizeModel;
import com.myteay.phoenix.core.model.camp.tools.CampValidateTool;

/**
 * 奖品模型转换器
 * 
 * @author danlley
 * @version $Id: CampPrizeConvertor.java, v 0.1 Dec 17, 2018 10:44:43 PM danlley Exp $
 */
public class CampPrizeConvertor {

    /**
     * convert DO to model
     * 
     * @param campPrizeDO
     * @return
     */
    public static CampPrizeModel convertDO2Model(CampPrizeDO campPrizeDO) {
        if (campPrizeDO == null) {
            return null;
        }

        CampPrizeModel campPrizeModel = new CampPrizeModel();

        campPrizeModel.setCampId(campPrizeDO.getCampId());
        campPrizeModel.setDistribution(campPrizeDO.getDistribution());
        campPrizeModel.setGmtCreated(campPrizeDO.getGmtCreated());
        campPrizeModel.setPrice(campPrizeDO.getPrice());
        campPrizeModel.setPrizeAmount(campPrizeDO.getPrizeAmount());
        campPrizeModel.setPrizeEffictive(campPrizeDO.getPrizeEffictive());
        campPrizeModel.setPrizeExpired(campPrizeDO.getPrizeExpired());
        campPrizeModel.setPrizeId(campPrizeDO.getPrizeId());
        campPrizeModel.setPrizeLevel(campPrizeDO.getPrizeLevel());
        campPrizeModel.setPrizeName(campPrizeDO.getPrizeName());
        campPrizeModel.setPrizePercent(campPrizeDO.getPrizePercent());
        if (StringUtils.isNotBlank(campPrizeDO.getPrizeStatus())) {
            campPrizeModel.setPrizeStatus(CampPrizeStatusEnum.getByCode(campPrizeDO.getPrizeStatus()));
        }
        campPrizeModel.setShopId(campPrizeDO.getShopId());

        return campPrizeModel;
    }

    /**
     * convert model to DO
     * 
     * @param campPrizeModel
     * @param isValidate
     * @return
     */
    public static CampPrizeDO convertModel2DO(CampPrizeModel campPrizeModel, boolean isValidate) {

        if (campPrizeModel == null) {
            return null;
        }

        CampPrizeDO campPrizeDO = new CampPrizeDO();
        if (campPrizeModel.getPrizeStatus() != null) {
            campPrizeDO.setPrizeStatus(campPrizeModel.getPrizeStatus().getValue());
        }
        campPrizeDO.setCampId(campPrizeModel.getCampId());
        campPrizeDO.setDistribution(campPrizeModel.getDistribution());
        campPrizeDO.setGmtCreated(campPrizeModel.getGmtCreated());
        campPrizeDO.setPrice(campPrizeModel.getPrice());
        campPrizeDO.setPrizeAmount(campPrizeModel.getPrizeAmount());
        campPrizeDO.setPrizeEffictive(campPrizeModel.getPrizeEffictive());
        campPrizeDO.setPrizeExpired(campPrizeModel.getPrizeExpired());
        campPrizeDO.setPrizeId(campPrizeModel.getPrizeId());
        campPrizeDO.setPrizeLevel(campPrizeModel.getPrizeLevel());
        campPrizeDO.setPrizeName(campPrizeModel.getPrizeName());
        campPrizeDO.setPrizePercent(campPrizeModel.getPrizePercent());

        campPrizeDO.setShopId(campPrizeModel.getShopId());

        if (isValidate) {
            CampValidateTool.validateCampPrizeDO(campPrizeDO);
        }

        return campPrizeDO;
    }

}
