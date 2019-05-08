/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.camp.repository.impl;

import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.common.dal.camp.daointerface.CampShopPrizeOutHistoryDAO;
import com.myteay.phoenix.common.dal.camp.dataobject.CampShopPrizeOutHistoryDO;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel;
import com.myteay.phoenix.core.model.camp.repository.CampShopPrizeOutHistoryRepository;

/**
 * 中奖历史管理仓储
 * 
 * @author danlley
 * @version $Id: CampShopPrizeOutHistoryRepositoryImpl.java, v 0.1 Apr 22, 2019 12:24:57 AM danlley Exp $
 */
public class CampShopPrizeOutHistoryRepositoryImpl implements CampShopPrizeOutHistoryRepository {

    /** 日志 */
    private static final Logger        logger = LoggerFactory.getLogger(LoggerNames.PX_CAMP);

    /**  */
    private CampShopPrizeOutHistoryDAO campShopPrizeOutHistoryDAO;

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopPrizeOutHistoryRepository#saveCampShopPrizeOutHistory(com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel)
     */
    @Override
    public String saveCampShopPrizeOutHistory(CampShopPrizeOutModel campShopPrizeOutModel) throws PxManageException {
        CampShopPrizeOutHistoryDO campShopPrizeOutHistoryDO = constructCampShopPrizeOutHistoryDO(campShopPrizeOutModel);
        campShopPrizeOutHistoryDAO.insert(campShopPrizeOutHistoryDO);
        return campShopPrizeOutHistoryDO.getCampPrizeOutId();
    }

    /**
     * 构建中奖历史数据模型
     * 
     * @param campShopPrizeOutModel
     * @return
     */
    private CampShopPrizeOutHistoryDO constructCampShopPrizeOutHistoryDO(CampShopPrizeOutModel campShopPrizeOutModel) {
        if (campShopPrizeOutModel == null) {
            return null;
        }
        CampShopPrizeOutHistoryDO campShopPrizeOutHistoryDO = new CampShopPrizeOutHistoryDO();

        campShopPrizeOutHistoryDO.setCampId(campShopPrizeOutModel.getCampId());
        campShopPrizeOutHistoryDO.setCampName(campShopPrizeOutModel.getCampName());
        campShopPrizeOutHistoryDO.setCampPrizeOutId(campShopPrizeOutModel.getCampPrizeOutId());
        campShopPrizeOutHistoryDO.setMobileNo(campShopPrizeOutModel.getMobileNo());
        campShopPrizeOutHistoryDO.setOrderNo(campShopPrizeOutModel.getOrderNo());
        campShopPrizeOutHistoryDO.setPrice(campShopPrizeOutModel.getPrice());
        campShopPrizeOutHistoryDO.setPrizeEffictive(campShopPrizeOutModel.getPrizeEffictive());
        campShopPrizeOutHistoryDO.setPrizeExpired(campShopPrizeOutModel.getPrizeExpired());
        campShopPrizeOutHistoryDO.setPrizeId(campShopPrizeOutModel.getPrizeId());
        campShopPrizeOutHistoryDO.setPrizeLevel(campShopPrizeOutModel.getPrizeLevel());
        campShopPrizeOutHistoryDO.setPrizeName(campShopPrizeOutModel.getPrizeName());
        campShopPrizeOutHistoryDO.setGmtCreated(campShopPrizeOutModel.getGmtCreated());
        campShopPrizeOutHistoryDO.setGmtModified(campShopPrizeOutModel.getGmtModified());

        if (campShopPrizeOutModel.getPrizeOutStatus() != null) {
            campShopPrizeOutHistoryDO.setPrizeOutStatus(campShopPrizeOutModel.getPrizeOutStatus().getValue());
        }

        if (campShopPrizeOutModel.getPrizeStatus() != null) {
            campShopPrizeOutHistoryDO.setPrizeStatus(campShopPrizeOutModel.getPrizeStatus().getValue());
        }

        campShopPrizeOutHistoryDO.setShopId(campShopPrizeOutModel.getShopId());
        campShopPrizeOutHistoryDO.setShopName(campShopPrizeOutModel.getShopName());
        campShopPrizeOutHistoryDO.setUserId(campShopPrizeOutModel.getUserId());

        return campShopPrizeOutHistoryDO;
    }

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopPrizeOutHistoryRepository#queryCampShopPrizeOutHistoryById(java.lang.String)
     */
    @Override
    public CampShopPrizeOutModel queryCampShopPrizeOutHistoryById(String campPrizeOutId) throws PxManageException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Setter method for property <tt>campShopPrizeOutHistoryDAO</tt>.
     * 
     * @param campShopPrizeOutHistoryDAO value to be assigned to property campShopPrizeOutHistoryDAO
     */
    public void setCampShopPrizeOutHistoryDAO(CampShopPrizeOutHistoryDAO campShopPrizeOutHistoryDAO) {
        this.campShopPrizeOutHistoryDAO = campShopPrizeOutHistoryDAO;
    }

}
