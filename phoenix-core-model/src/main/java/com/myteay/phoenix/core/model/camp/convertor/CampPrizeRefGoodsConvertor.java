/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.camp.convertor;

import com.myteay.phoenix.common.dal.camp.dataobject.CampPrizeGoodsRefDO;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.camp.CampPrizeRefGoodsModel;
import com.myteay.phoenix.core.model.camp.tools.CampPrizeRefGoodsValidateTool;

/**
 * 奖品关联商品转换器
 * 
 * @author danlley
 * @version $Id: CampPrizeRefGoodsConvertor.java, v 0.1 Dec 20, 2018 1:21:49 AM danlley Exp $
 */
public class CampPrizeRefGoodsConvertor {

    /**
     * convert model to DO
     * 
     * @param campPrizeRefGoodsModel
     * @param isValidate
     * @return
     * @throws PxManageException
     */
    public static CampPrizeGoodsRefDO convertModel2DO(CampPrizeRefGoodsModel campPrizeRefGoodsModel, boolean isValidate) throws PxManageException {

        if (campPrizeRefGoodsModel == null) {
            return null;
        }

        CampPrizeGoodsRefDO campPrizeGoodsRefDO = new CampPrizeGoodsRefDO();
        campPrizeGoodsRefDO.setPrizeId(campPrizeRefGoodsModel.getPrizeId());
        campPrizeGoodsRefDO.setGoodsId(campPrizeRefGoodsModel.getGoodsId());

        if (isValidate) {
            CampPrizeRefGoodsValidateTool.validateCampPrizeRefGoodsDO(campPrizeGoodsRefDO);
        }

        return campPrizeGoodsRefDO;

    }

    /**
     * convert DO to model
     * 
     * @param campPrizeGoodsRefDO
     * @return
     */
    public static CampPrizeRefGoodsModel convertDO2Model(CampPrizeGoodsRefDO campPrizeGoodsRefDO) {
        if (campPrizeGoodsRefDO == null) {
            return null;
        }

        CampPrizeRefGoodsModel campPrizeRefGoodsModel = new CampPrizeRefGoodsModel();
        campPrizeRefGoodsModel.setGmtCreated(campPrizeGoodsRefDO.getGmtCreated());
        campPrizeRefGoodsModel.setGmtModified(campPrizeGoodsRefDO.getGmtModified());
        campPrizeRefGoodsModel.setGoodsId(campPrizeGoodsRefDO.getGoodsId());
        campPrizeRefGoodsModel.setPrizeId(campPrizeGoodsRefDO.getPrizeId());

        return campPrizeRefGoodsModel;
    }

}
