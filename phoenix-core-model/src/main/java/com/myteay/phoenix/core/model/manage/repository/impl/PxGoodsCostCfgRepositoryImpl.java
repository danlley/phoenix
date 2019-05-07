/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.common.dal.daointerface.PxGoodsCostCfgDAO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsCostCfgAdvDO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsCostCfgDO;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsStatusEnum;
import com.myteay.phoenix.core.model.manage.PxGoodsCostCfgAdvModel;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsCostCfgRepository;

/**
 * 商品成本管理仓储
 * 
 * @author danlley
 * @version $Id: PxGoodsCostCfgRepositoryImpl.java, v 0.1 May 7, 2019 12:52:02 PM danlley Exp $
 */
public class PxGoodsCostCfgRepositoryImpl implements PxGoodsCostCfgRepository {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(LoggerNames.PX_CASHIER_DEFAULT);

    /** 商品成本管理操作DAO */
    private PxGoodsCostCfgDAO   pxGoodsCostCfgDAO;

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsCostCfgRepository#modifyGoodsCostCfgInfo(com.myteay.phoenix.core.model.manage.PxGoodsCostCfgAdvModel)
     */
    @Override
    public PxGoodsCostCfgAdvModel modifyGoodsCostCfgInfo(PxGoodsCostCfgAdvModel pxGoodsCostCfgAdvModel) throws PxManageException {

        PxGoodsCostCfgDO pxGoodsCostCfgDO = constructModel2PxGoodsCostCfgDO(pxGoodsCostCfgAdvModel);
        if (pxGoodsCostCfgDO == null || StringUtils.isBlank(pxGoodsCostCfgDO.getGoodsId())) {
            logger.warn("成本信息更新所需关键信息不可用 pxGoodsCostCfgAdvModel=" + pxGoodsCostCfgAdvModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.COST_MODEL_ERR);
        }

        pxGoodsCostCfgDAO.updatePxGoodsCostCfg(pxGoodsCostCfgDO);

        return findSingleGoodsCostCfg(pxGoodsCostCfgDO.getGoodsId());
    }

    /**
     * 成本高级模型转换为成本配置模型
     * 
     * @param pxGoodsCostCfgAdvModel
     * @return
     */
    private PxGoodsCostCfgDO constructModel2PxGoodsCostCfgDO(PxGoodsCostCfgAdvModel pxGoodsCostCfgAdvModel) {
        if (pxGoodsCostCfgAdvModel == null) {
            return null;
        }
        PxGoodsCostCfgDO pxGoodsCostCfgDO = new PxGoodsCostCfgDO();

        pxGoodsCostCfgDO.setGoodsCost(pxGoodsCostCfgAdvModel.getGoodsCost());
        pxGoodsCostCfgDO.setGoodsDesc(pxGoodsCostCfgAdvModel.getGoodsDesc());
        pxGoodsCostCfgDO.setGoodsId(pxGoodsCostCfgAdvModel.getGoodsId());
        pxGoodsCostCfgDO.setGoodsImage(pxGoodsCostCfgAdvModel.getGoodsImage());
        pxGoodsCostCfgDO.setGoodsTitle(pxGoodsCostCfgAdvModel.getGoodsTitle());
        pxGoodsCostCfgDO.setShopId(pxGoodsCostCfgAdvModel.getShopId());

        return pxGoodsCostCfgDO;
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsCostCfgRepository#saveGoodsCostCfgInfo(com.myteay.phoenix.core.model.manage.PxGoodsCostCfgAdvModel)
     */
    @Override
    public PxGoodsCostCfgAdvModel saveGoodsCostCfgInfo(PxGoodsCostCfgAdvModel pxGoodsCostCfgAdvModel) throws PxManageException {
        PxGoodsCostCfgDO pxGoodsCostCfgDO = constructModel2PxGoodsCostCfgDO(pxGoodsCostCfgAdvModel);

        if (pxGoodsCostCfgDO == null || StringUtils.isBlank(pxGoodsCostCfgDO.getGoodsId())) {
            logger.warn("无法保存商品成本信息  pxGoodsCostCfgAdvModel=" + pxGoodsCostCfgAdvModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.COST_MODEL_ERR);
        }

        String goodsId = pxGoodsCostCfgDAO.insert(pxGoodsCostCfgDO);

        return findSingleGoodsCostCfg(goodsId);
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsCostCfgRepository#findAllGoodsCostCfg(java.lang.String)
     */
    @Override
    public List<PxGoodsCostCfgAdvModel> findAllGoodsCostCfg(String shopId) throws PxManageException {

        List<PxGoodsCostCfgAdvDO> pxGoodsCostCfgAdvDOs = pxGoodsCostCfgDAO.findPxGoodsCostCfgAll(shopId);

        if (CollectionUtils.isEmpty(pxGoodsCostCfgAdvDOs)) {
            logger.warn("查询成本配置清单为空，请检查相关配置是否正确");
            return null;
        }
        List<PxGoodsCostCfgAdvModel> pxGoodsCostCfgAdvModels = new ArrayList<PxGoodsCostCfgAdvModel>();
        PxGoodsCostCfgAdvModel pxGoodsCostCfgAdvModel = null;
        for (PxGoodsCostCfgAdvDO pxGoodsCostCfgAdvDO : pxGoodsCostCfgAdvDOs) {
            pxGoodsCostCfgAdvModel = constructPxGoodsCostCfgAdvDO2Model(pxGoodsCostCfgAdvDO);
            if (pxGoodsCostCfgAdvModel == null) {
                continue;
            }

            pxGoodsCostCfgAdvModels.add(pxGoodsCostCfgAdvModel);
        }

        return pxGoodsCostCfgAdvModels;
    }

    /**
     * 
     * @param pxGoodsCostCfgAdvDO
     * @return
     */
    private PxGoodsCostCfgAdvModel constructPxGoodsCostCfgAdvDO2Model(PxGoodsCostCfgAdvDO pxGoodsCostCfgAdvDO) {
        if (pxGoodsCostCfgAdvDO == null) {
            return null;
        }

        PxGoodsCostCfgAdvModel pxGoodsCostCfgAdvModel = new PxGoodsCostCfgAdvModel();

        pxGoodsCostCfgAdvModel.setGmtCreated(pxGoodsCostCfgAdvDO.getGmtCreated());
        pxGoodsCostCfgAdvModel.setGmtExpired(pxGoodsCostCfgAdvDO.getGmtExpired());

        if (StringUtils.isNotBlank(pxGoodsCostCfgAdvDO.getGoodsCost())) {
            pxGoodsCostCfgAdvModel.setGoodsCost(pxGoodsCostCfgAdvDO.getGoodsCost());
        }
        pxGoodsCostCfgAdvModel.setGoodsDesc(pxGoodsCostCfgAdvDO.getGoodsDesc());
        pxGoodsCostCfgAdvModel.setGoodsId(pxGoodsCostCfgAdvDO.getGoodsId());
        pxGoodsCostCfgAdvModel.setGoodsImage(pxGoodsCostCfgAdvDO.getGoodsImage());
        pxGoodsCostCfgAdvModel.setGoodsPrice(pxGoodsCostCfgAdvDO.getGoodsPrice());

        if (StringUtils.isNotBlank(pxGoodsCostCfgAdvDO.getGoodsStatus())) {
            pxGoodsCostCfgAdvModel.setGoodsStatus(PxGoodsStatusEnum.getByValue(pxGoodsCostCfgAdvDO.getGoodsStatus()));
        }
        pxGoodsCostCfgAdvModel.setGoodsTitle(pxGoodsCostCfgAdvDO.getGoodsTitle());
        pxGoodsCostCfgAdvModel.setShopId(pxGoodsCostCfgAdvDO.getShopId());

        return pxGoodsCostCfgAdvModel;
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsCostCfgRepository#findSingleGoodsCostCfg(java.lang.String)
     */
    @Override
    public PxGoodsCostCfgAdvModel findSingleGoodsCostCfg(String goodsId) throws PxManageException {

        PxGoodsCostCfgDO pxGoodsCostCfgDO = pxGoodsCostCfgDAO.findPxGoodsCostCfgById(goodsId);

        return constructPxGoodsCostCfgDO2Model(pxGoodsCostCfgDO);
    }

    /**
     * 将商品成本配置基本模型转换成商品成本高级模型
     * 
     * @param pxGoodsCostCfgDO
     * @return
     */
    private PxGoodsCostCfgAdvModel constructPxGoodsCostCfgDO2Model(PxGoodsCostCfgDO pxGoodsCostCfgDO) {
        if (pxGoodsCostCfgDO == null) {
            return null;
        }

        PxGoodsCostCfgAdvModel pxGoodsCostCfgAdvModel = new PxGoodsCostCfgAdvModel();

        pxGoodsCostCfgAdvModel.setGmtCreated(pxGoodsCostCfgDO.getGmtCreated());
        pxGoodsCostCfgAdvModel.setGmtModified(pxGoodsCostCfgDO.getGmtModified());

        if (StringUtils.isNotBlank(pxGoodsCostCfgDO.getGoodsCost())) {
            pxGoodsCostCfgAdvModel.setGoodsCost(pxGoodsCostCfgDO.getGoodsCost());
        }
        pxGoodsCostCfgAdvModel.setGoodsDesc(pxGoodsCostCfgDO.getGoodsDesc());
        pxGoodsCostCfgAdvModel.setGoodsId(pxGoodsCostCfgDO.getGoodsId());
        pxGoodsCostCfgAdvModel.setGoodsImage(pxGoodsCostCfgDO.getGoodsImage());
        pxGoodsCostCfgAdvModel.setGoodsTitle(pxGoodsCostCfgDO.getGoodsTitle());
        pxGoodsCostCfgAdvModel.setShopId(pxGoodsCostCfgDO.getShopId());

        return pxGoodsCostCfgAdvModel;
    }

    /**
     * Setter method for property <tt>pxGoodsCostCfgDAO</tt>.
     * 
     * @param pxGoodsCostCfgDAO value to be assigned to property pxGoodsCostCfgDAO
     */
    public void setPxGoodsCostCfgDAO(PxGoodsCostCfgDAO pxGoodsCostCfgDAO) {
        this.pxGoodsCostCfgDAO = pxGoodsCostCfgDAO;
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsCostCfgRepository#findAllGoodsCostCfg()
     */
    @Override
    public List<PxGoodsCostCfgAdvModel> findAllGoodsCostCfg() throws PxManageException {

        List<PxGoodsCostCfgAdvDO> pxGoodsCostCfgAdvDOs = pxGoodsCostCfgDAO.findPxGoodsCostCfgsAll();

        if (CollectionUtils.isEmpty(pxGoodsCostCfgAdvDOs)) {
            logger.warn("findAllGoodsCostCfg()查询成本配置清单为空，请检查相关配置是否正确");
            return null;
        }
        List<PxGoodsCostCfgAdvModel> pxGoodsCostCfgAdvModels = new ArrayList<PxGoodsCostCfgAdvModel>();
        PxGoodsCostCfgAdvModel pxGoodsCostCfgAdvModel = null;
        for (PxGoodsCostCfgAdvDO pxGoodsCostCfgAdvDO : pxGoodsCostCfgAdvDOs) {
            pxGoodsCostCfgAdvModel = constructPxGoodsCostCfgAdvDO2Model(pxGoodsCostCfgAdvDO);
            if (pxGoodsCostCfgAdvModel == null) {
                continue;
            }

            pxGoodsCostCfgAdvModels.add(pxGoodsCostCfgAdvModel);
        }

        return pxGoodsCostCfgAdvModels;
    }

}
