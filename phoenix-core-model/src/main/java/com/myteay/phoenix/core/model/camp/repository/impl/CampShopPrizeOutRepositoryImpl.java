/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.camp.repository.impl;

import org.apache.log4j.Logger;

import com.myteay.phoenix.common.dal.camp.daointerface.CampShopPrizeOutDAO;
import com.myteay.phoenix.common.dal.camp.dataobject.CampShopPrizeOutDO;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel;
import com.myteay.phoenix.core.model.camp.repository.CampShopPrizeOutRepository;

/**
 * 抽奖流水操作仓储
 * 
 * @author danlley
 * @version $Id: CampShopPrizeOutRepositoryImpl.java, v 0.1 Mar 28, 2019 6:38:34 PM danlley Exp $
 */
public class CampShopPrizeOutRepositoryImpl implements CampShopPrizeOutRepository {

    /** 日志 */
    public static final Logger  logger = Logger.getLogger(CampShopPrizeOutRepositoryImpl.class);

    /** 奖品流水操作DAO */
    private CampShopPrizeOutDAO campShopPrizeOutDAO;

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopPrizeOutRepository#saveCampShopPrizeOut(com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel)
     */
    @Override
    public String saveCampShopPrizeOut(CampShopPrizeOutModel campShopPrizeOutModel) throws PxManageException {

        if (campShopPrizeOutModel == null) {
            logger.warn("奖品流水模型不可用，无法保存奖品流水 campShopPrizeOutModel is null");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_OPERATE_FAILED);
        }

        CampShopPrizeOutDO campShopPrizeOutDO = convertModel2DO(campShopPrizeOutModel);
        if (campShopPrizeOutDO == null) {
            logger.warn("奖品流水模型不合法，无法得到相应的奖品流水数据模型 campShopPrizeOutModel=" + campShopPrizeOutModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_OPERATE_FAILED);
        }

        String id = null;
        try {
            id = campShopPrizeOutDAO.insert(campShopPrizeOutDO);
        } catch (Throwable e) {
            logger.warn("保存奖品流水数据模型失败  campShopPrizeOutModel" + campShopPrizeOutModel, e);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_OPERATE_FAILED);
        }
        return id;
    }

    /**
     * convert model to data object
     * 
     * @param campShopPrizeOutModel
     * @return
     */
    private CampShopPrizeOutDO convertModel2DO(CampShopPrizeOutModel campShopPrizeOutModel) {
        if (campShopPrizeOutModel == null) {
            return null;
        }
        CampShopPrizeOutDO campShopPrizeOutDO = new CampShopPrizeOutDO();

        campShopPrizeOutDO.setCampId(campShopPrizeOutModel.getCampId());
        campShopPrizeOutDO.setCampName(campShopPrizeOutModel.getCampName());
        campShopPrizeOutDO.setCampPrizeOutId(campShopPrizeOutModel.getCampPrizeOutId());
        campShopPrizeOutDO.setMobileNo(campShopPrizeOutModel.getMobileNo());
        campShopPrizeOutDO.setOrderNo(campShopPrizeOutModel.getOrderNo());
        campShopPrizeOutDO.setPrice(campShopPrizeOutModel.getPrice());
        campShopPrizeOutDO.setPrizeEffictive(campShopPrizeOutModel.getPrizeEffictive());
        campShopPrizeOutDO.setPrizeExpired(campShopPrizeOutModel.getPrizeExpired());
        campShopPrizeOutDO.setPrizeId(campShopPrizeOutModel.getPrizeId());
        campShopPrizeOutDO.setPrizeLevel(campShopPrizeOutModel.getPrizeLevel());
        campShopPrizeOutDO.setPrizeName(campShopPrizeOutModel.getPrizeName());

        if (campShopPrizeOutModel.getPrizeOutStatus() != null) {
            campShopPrizeOutDO.setPrizeOutStatus(campShopPrizeOutModel.getPrizeOutStatus().getValue());
        }

        if (campShopPrizeOutModel.getPrizeStatus() != null) {
            campShopPrizeOutDO.setPrizeStatus(campShopPrizeOutModel.getPrizeStatus().getValue());
        }

        campShopPrizeOutDO.setShopId(campShopPrizeOutModel.getShopId());
        campShopPrizeOutDO.setShopName(campShopPrizeOutModel.getShopName());
        campShopPrizeOutDO.setUserId(campShopPrizeOutModel.getUserId());

        return campShopPrizeOutDO;
    }

    /**
     * Setter method for property <tt>campShopPrizeOutDAO</tt>.
     * 
     * @param campShopPrizeOutDAO value to be assigned to property campShopPrizeOutDAO
     */
    public void setCampShopPrizeOutDAO(CampShopPrizeOutDAO campShopPrizeOutDAO) {
        this.campShopPrizeOutDAO = campShopPrizeOutDAO;
    }

}
