/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.repository.impl;

import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.common.dal.daointerface.PxGoodsOrderOutHistoryDAO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsOrderOutHistoryDO;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.PxGoodsOrderOutModel;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsOrderOutHistoryRepository;

/**
 * 订单流水历史操作仓储
 * 
 * @author danlley
 * @version $Id: PxGoodsOrderOutHistoryRepositoryImpl.java, v 0.1 May 10, 2019 4:50:27 AM danlley Exp $
 */
public class PxGoodsOrderOutHistoryRepositoryImpl implements PxGoodsOrderOutHistoryRepository {

    /** 日志 */
    private static final Logger       logger = LoggerFactory.getLogger(LoggerNames.PX_CASHIER_DEFAULT);

    /** 订单流水历史操作DAO */
    private PxGoodsOrderOutHistoryDAO pxGoodsOrderOutHistoryDAO;

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsOrderOutHistoryRepository#saveGoodsOrderOutHistory(com.myteay.phoenix.core.model.PxGoodsOrderOutModel)
     */
    @Override
    public String saveGoodsOrderOutHistory(PxGoodsOrderOutModel pxGoodsOrderOutModel) throws PxManageException {
        PxGoodsOrderOutHistoryDO pxGoodsOrderOutHistoryDO = constructModel2HistoryDO(pxGoodsOrderOutModel);

        if (pxGoodsOrderOutHistoryDO == null) {
            logger.warn("订单流水迁移历史过程发生异常，订单流水数据模型不可用 pxGoodsOrderOutModel=" + pxGoodsOrderOutModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
        }

        pxGoodsOrderOutHistoryDAO.insert(pxGoodsOrderOutHistoryDO);
        return null;
    }

    /**
     * convert model to history data object
     * 
     * @param pxGoodsOrderModel
     * @return
     */
    private PxGoodsOrderOutHistoryDO constructModel2HistoryDO(PxGoodsOrderOutModel pxGoodsOrderOutModel) {

        if (pxGoodsOrderOutModel == null) {
            return null;
        }

        PxGoodsOrderOutHistoryDO pxGoodsOrderOutHistoryDO = new PxGoodsOrderOutHistoryDO();
        pxGoodsOrderOutHistoryDO.setGmtCreated(pxGoodsOrderOutModel.getGmtCreated());
        pxGoodsOrderOutHistoryDO.setGmtModified(pxGoodsOrderOutModel.getGmtModified());
        pxGoodsOrderOutHistoryDO.setGoodsCommPrice(pxGoodsOrderOutModel.getGoodsCommPrice());
        pxGoodsOrderOutHistoryDO.setGoodsId(pxGoodsOrderOutModel.getGoodsId());
        pxGoodsOrderOutHistoryDO.setGoodsPrice(pxGoodsOrderOutModel.getGoodsPrice());
        pxGoodsOrderOutHistoryDO.setGoodsTitle(pxGoodsOrderOutModel.getGoodsTitle());

        if (pxGoodsOrderOutModel.getGoodsType() != null) {
            pxGoodsOrderOutHistoryDO.setGoodsType(pxGoodsOrderOutModel.getGoodsType().getValue());
        }

        pxGoodsOrderOutHistoryDO.setId(pxGoodsOrderOutModel.getId());
        pxGoodsOrderOutHistoryDO.setOrderNo(pxGoodsOrderOutModel.getOrderNo());

        if (pxGoodsOrderOutModel.getOrderStatus() != null) {
            pxGoodsOrderOutHistoryDO.setOrderStatus(pxGoodsOrderOutModel.getOrderStatus().getValue());
        }

        if (pxGoodsOrderOutModel.getPayType() != null) {
            pxGoodsOrderOutHistoryDO.setPayType(pxGoodsOrderOutModel.getPayType().getValue());
        }

        pxGoodsOrderOutHistoryDO.setSellerAmount(pxGoodsOrderOutModel.getSellerAmount());
        pxGoodsOrderOutHistoryDO.setShopId(pxGoodsOrderOutModel.getShopId());
        pxGoodsOrderOutHistoryDO.setShopName(pxGoodsOrderOutModel.getShopName());
        pxGoodsOrderOutHistoryDO.setUserId(pxGoodsOrderOutModel.getUserId());

        return pxGoodsOrderOutHistoryDO;
    }

    /**
     * Setter method for property <tt>pxGoodsOrderOutHistoryDAO</tt>.
     * 
     * @param pxGoodsOrderOutHistoryDAO value to be assigned to property pxGoodsOrderOutHistoryDAO
     */
    public void setPxGoodsOrderOutHistoryDAO(PxGoodsOrderOutHistoryDAO pxGoodsOrderOutHistoryDAO) {
        this.pxGoodsOrderOutHistoryDAO = pxGoodsOrderOutHistoryDAO;
    }

}
