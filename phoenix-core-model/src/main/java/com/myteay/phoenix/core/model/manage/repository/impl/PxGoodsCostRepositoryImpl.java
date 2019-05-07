/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.repository.impl;

import com.myteay.common.util.lang.Money;
import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.common.dal.daointerface.PxGoodsCostDAO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsCostDO;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxGoodsCostModel;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsCostRepository;

/**
 * 商品成本记录管理仓储
 * 
 * @author danlley
 * @version $Id: PxGoodsCostRepositoryImpl.java, v 0.1 May 8, 2019 12:11:39 AM danlley Exp $
 */
public class PxGoodsCostRepositoryImpl implements PxGoodsCostRepository {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(LoggerNames.PX_CASHIER_DEFAULT);

    /** 商品成本信息记录操作DAO */
    private PxGoodsCostDAO      pxGoodsCostDAO;

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsCostRepository#modifyGoodsCostInfo(com.myteay.phoenix.core.model.manage.PxGoodsCostModel)
     */
    @Override
    public PxGoodsCostModel modifyGoodsCostInfo(PxGoodsCostModel pxGoodsCostModel) throws PxManageException {
        PxGoodsCostDO pxGoodsCostDO = constructModel2DO(pxGoodsCostModel);

        if (pxGoodsCostDO == null) {
            logger.warn("当前商品成本的数据模型构建失败，无法完成更新动作 pxGoodsCostModel = " + pxGoodsCostModel);
            return null;
        }

        pxGoodsCostDAO.updatePxGoodsCost(pxGoodsCostDO);
        return pxGoodsCostModel;
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsCostRepository#saveGoodsCostInfo(com.myteay.phoenix.core.model.manage.PxGoodsCostModel)
     */
    @Override
    public PxGoodsCostModel saveGoodsCostInfo(PxGoodsCostModel pxGoodsCostModel) throws PxManageException {

        PxGoodsCostDO pxGoodsCostDO = constructModel2DO(pxGoodsCostModel);
        if (pxGoodsCostDO == null) {
            logger.warn("当前商品成本的数据模型构建失败，无法完成插入动作 pxGoodsCostModel = " + pxGoodsCostModel);
            return null;
        }

        pxGoodsCostDAO.insert(pxGoodsCostDO);
        return pxGoodsCostModel;
    }

    /**
     * convert model to data object
     * 
     * @param pxGoodsCostModel
     * @return
     */
    private PxGoodsCostDO constructModel2DO(PxGoodsCostModel pxGoodsCostModel) {

        if (pxGoodsCostModel == null) {
            return null;
        }

        PxGoodsCostDO pxGoodsCostDO = new PxGoodsCostDO();

        if (pxGoodsCostModel.getActrualCost() != null) {
            pxGoodsCostDO.setActrualCost(pxGoodsCostModel.getActrualCost().toString());
        }

        if (pxGoodsCostModel.getActrualSellerPrice() != null) {
            pxGoodsCostDO.setActrualSellerPrice(pxGoodsCostModel.getActrualSellerPrice().toString());
        }
        pxGoodsCostDO.setGmtCreated(pxGoodsCostModel.getGmtCreated());
        pxGoodsCostDO.setGmtModified(pxGoodsCostModel.getGmtModified());
        pxGoodsCostDO.setGoodsDesc(pxGoodsCostModel.getGoodsDesc());
        pxGoodsCostDO.setGoodsId(pxGoodsCostModel.getGoodsId());
        pxGoodsCostDO.setGoodsTitle(pxGoodsCostModel.getGoodsTitle());
        pxGoodsCostDO.setReportDate(pxGoodsCostModel.getReportDate());
        pxGoodsCostDO.setShopId(pxGoodsCostModel.getShopId());

        return pxGoodsCostDO;
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsCostRepository#findSingleGoodsCost(java.lang.String, java.lang.String)
     */
    @Override
    public PxGoodsCostModel findSingleGoodsCost(String goodsId, String reportDate) throws PxManageException {
        PxGoodsCostDO pxGoodsCostDO = pxGoodsCostDAO.findPxGoodsCostById(goodsId, reportDate);

        if (pxGoodsCostDO == null) {
            return null;
        }

        return constructDO2Model(pxGoodsCostDO);
    }

    /**
     * convert DO to model
     * 
     * @param pxGoodsCostDO
     * @return
     */
    private PxGoodsCostModel constructDO2Model(PxGoodsCostDO pxGoodsCostDO) {
        if (pxGoodsCostDO == null) {
            return null;
        }

        PxGoodsCostModel pxGoodsCostModel = new PxGoodsCostModel();

        pxGoodsCostModel.setActrualCost(new Money(pxGoodsCostDO.getActrualCost()));
        pxGoodsCostModel.setActrualSellerPrice(new Money(pxGoodsCostDO.getActrualSellerPrice()));
        pxGoodsCostModel.setGmtCreated(pxGoodsCostDO.getGmtCreated());
        pxGoodsCostModel.setGmtModified(pxGoodsCostDO.getGmtModified());
        pxGoodsCostModel.setGoodsDesc(pxGoodsCostDO.getGoodsDesc());
        pxGoodsCostModel.setGoodsId(pxGoodsCostDO.getGoodsId());
        pxGoodsCostModel.setGoodsTitle(pxGoodsCostDO.getGoodsTitle());
        pxGoodsCostModel.setReportDate(pxGoodsCostDO.getReportDate());
        pxGoodsCostModel.setShopId(pxGoodsCostDO.getShopId());

        return pxGoodsCostModel;
    }

    /**
     * Setter method for property <tt>pxGoodsCostDAO</tt>.
     * 
     * @param pxGoodsCostDAO value to be assigned to property pxGoodsCostDAO
     */
    public void setPxGoodsCostDAO(PxGoodsCostDAO pxGoodsCostDAO) {
        this.pxGoodsCostDAO = pxGoodsCostDAO;
    }

}
