/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.camp.convertor;

import com.myteay.phoenix.common.dal.camp.dataobject.CampBaseDO;
import com.myteay.phoenix.common.util.camp.enums.CampStatusEnum;
import com.myteay.phoenix.core.model.camp.CampBaseModel;
import com.myteay.phoenix.core.model.camp.tools.CampValidateTool;

/**
 * 营销活动基本信息转换器
 * 
 * @author danlley
 * @version $Id: CampBaseConvertor.java, v 0.1 Dec 16, 2018 11:01:47 PM danlley Exp $
 */
public class CampBaseConvertor {

    /**
     * convert model to DO
     * 
     * @param campBaseModel
     * @param isValidate
     * @return
     */
    public static CampBaseDO convertModel2DO(CampBaseModel campBaseModel, boolean isValidate) {
        if (campBaseModel == null) {
            return null;
        }

        CampBaseDO campBaseDO = new CampBaseDO();
        campBaseDO.setCampEnd(campBaseModel.getCampEnd());
        campBaseDO.setCampId(campBaseModel.getCampId());
        campBaseDO.setCampName(campBaseModel.getCampName());
        campBaseDO.setCampStart(campBaseModel.getCampStart());

        if (campBaseModel.getCampStatus() != null) {
            campBaseDO.setCampStatus(campBaseModel.getCampStatus().getValue());
        }
        campBaseDO.setGmtCreated(campBaseModel.getGmtCreated());
        campBaseDO.setGmtModified(campBaseModel.getGmtModified());
        campBaseDO.setShopId(campBaseModel.getShopId());
        campBaseDO.setShopName(campBaseModel.getShopName());

        if (isValidate) {
            CampValidateTool.validateCampBaseDO(campBaseDO);
        }

        return campBaseDO;
    }

    /**
     * convert DO to model
     * 
     * @param freshCampBaseDO
     * @return
     */
    public static CampBaseModel convertDO2Model(CampBaseDO freshCampBaseDO) {
        if (freshCampBaseDO == null) {
            return null;
        }

        CampBaseModel campBaseModel = new CampBaseModel();
        campBaseModel.setCampEnd(freshCampBaseDO.getCampEnd());
        campBaseModel.setCampId(freshCampBaseDO.getCampId());
        campBaseModel.setCampName(freshCampBaseDO.getCampName());
        campBaseModel.setCampStart(freshCampBaseDO.getCampStart());
        campBaseModel.setCampStatus(CampStatusEnum.getByCode(freshCampBaseDO.getCampStatus()));
        campBaseModel.setGmtCreated(freshCampBaseDO.getGmtCreated());
        campBaseModel.setGmtModified(freshCampBaseDO.getGmtModified());
        campBaseModel.setShopId(freshCampBaseDO.getShopId());
        campBaseModel.setShopName(freshCampBaseDO.getShopName());

        return campBaseModel;
    }

}
