/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.camp.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopPrizeDAO;
import com.myteay.phoenix.common.dal.camp.dataobject.CampPrizeDO;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.camp.CampPrizeModel;
import com.myteay.phoenix.core.model.camp.convertor.CampPrizeConvertor;
import com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRepository;
import com.myteay.phoenix.core.model.camp.tools.CampValidateTool;

/**
 * 店内营销活动奖池仓储
 * 
 * @author danlley
 * @version $Id: CampShopPrizeRepositoryImpl.java, v 0.1 Dec 17, 2018 10:35:22 PM danlley Exp $
 */
public class CampShopPrizeRepositoryImpl implements CampShopPrizeRepository {

    /** 日志 */
    private static final Logger    logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /** 店内营销活动奖品操作DAO */
    private CampSingleShopPrizeDAO campSingleShopPrizeDAO;

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRepository#removeCampPrizeInfo(com.myteay.phoenix.core.model.camp.CampPrizeModel)
     */
    @Override
    public void removeCampPrizeInfo(CampPrizeModel campPrizeModel) throws PxManageException {
        if (campPrizeModel == null) {
            logger.warn("当前营销基本信息模型不可用，无法删除");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_INVALID);
        }

        // TODO 删除前，需要检查是否存在奖品关联，如果存在，则不允许进行删除
        campSingleShopPrizeDAO.deleteById(campPrizeModel.getPrizeId());
    }

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRepository#modifyCampPrizeInfo(com.myteay.phoenix.core.model.camp.CampPrizeModel)
     */
    @Override
    public CampPrizeModel modifyCampPrizeInfo(CampPrizeModel campPrizeModel) throws PxManageException {
        CampPrizeDO campPrizeDO = CampPrizeConvertor.convertModel2DO(campPrizeModel, false);

        campSingleShopPrizeDAO.updateCampPrize(campPrizeDO);

        CampPrizeDO freshCampPrizeDO = campSingleShopPrizeDAO.findCampPrizeById(campPrizeDO.getPrizeId());
        return CampPrizeConvertor.convertDO2Model(freshCampPrizeDO);
    }

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRepository#saveCampPrizeInfo(com.myteay.phoenix.core.model.camp.CampPrizeModel)
     */
    @Override
    public CampPrizeModel saveCampPrizeInfo(CampPrizeModel campPrizeModel) throws PxManageException {
        if (campPrizeModel == null) {
            logger.warn("当前营销基本信息模型不可用，无法保存营销基本信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_INVALID);
        }

        CampValidateTool.validateCampPrizeModel(campPrizeModel);

        CampPrizeDO campPrizeDO = CampPrizeConvertor.convertModel2DO(campPrizeModel, true);
        String prizeId = campSingleShopPrizeDAO.insert(campPrizeDO);

        CampPrizeDO freshCampPrizeDO = campSingleShopPrizeDAO.findCampPrizeById(prizeId);

        return CampPrizeConvertor.convertDO2Model(freshCampPrizeDO);
    }

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRepository#findAll()
     */
    @Override
    public List<CampPrizeModel> findAll() throws PxManageException {
        List<CampPrizeDO> list = campSingleShopPrizeDAO.findCampPrizeAll();

        List<CampPrizeModel> modelList = new ArrayList<>();
        CampPrizeModel campPrizeModel = null;
        for (CampPrizeDO campPrizeDO : list) {
            campPrizeModel = CampPrizeConvertor.convertDO2Model(campPrizeDO);
            if (campPrizeModel != null) {
                modelList.add(campPrizeModel);
            }
        }

        return modelList;
    }

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRepository#findCampPrizeExpired()
     */
    @Override
    public List<CampPrizeModel> findCampPrizeExpired() throws PxManageException {
        List<CampPrizeDO> list = campSingleShopPrizeDAO.findCampPrizeExpired();

        List<CampPrizeModel> modelList = new ArrayList<>();
        CampPrizeModel campPrizeModel = null;
        for (CampPrizeDO campPrizeDO : list) {
            campPrizeModel = CampPrizeConvertor.convertDO2Model(campPrizeDO);
            if (campPrizeModel != null) {
                modelList.add(campPrizeModel);
            }
        }

        return modelList;
    }

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRepository#findSingleCampPrizeById(java.lang.String)
     */
    @Override
    public CampPrizeModel findSingleCampPrizeById(String prizeId) throws PxManageException {
        CampPrizeDO freshCampPrizeDO = campSingleShopPrizeDAO.findCampPrizeById(prizeId);
        return CampPrizeConvertor.convertDO2Model(freshCampPrizeDO);
    }

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRepository#findCampPrizeByCampId(java.lang.String)
     */
    @Override
    public List<CampPrizeModel> findCampPrizeByCampId(String campId) throws PxManageException {
        List<CampPrizeDO> campPrizeDOs = campSingleShopPrizeDAO.findCampPrizeByCampId(campId);
        if (CollectionUtils.isEmpty(campPrizeDOs)) {
            return null;
        }

        List<CampPrizeModel> modelList = new ArrayList<>();
        for (CampPrizeDO campPrizeDO : campPrizeDOs) {
            modelList.add(CampPrizeConvertor.convertDO2Model(campPrizeDO));
        }

        return modelList;
    }

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRepository#findShopOnlinePrizeByCampId(java.lang.String)
     */
    @Override
    public List<CampPrizeModel> findShopOnlinePrizeByCampId(String campId) throws PxManageException {
        List<CampPrizeDO> campPrizeDOs = campSingleShopPrizeDAO.findCampPrizeOnlineByCampId(campId);
        if (CollectionUtils.isEmpty(campPrizeDOs)) {
            return null;
        }

        List<CampPrizeModel> modelList = new ArrayList<>();
        for (CampPrizeDO campPrizeDO : campPrizeDOs) {
            modelList.add(CampPrizeConvertor.convertDO2Model(campPrizeDO));
        }

        return modelList;
    }

    /**
     * Setter method for property <tt>campSingleShopPrizeDAO</tt>.
     * 
     * @param campSingleShopPrizeDAO value to be assigned to property campSingleShopPrizeDAO
     */
    public void setCampSingleShopPrizeDAO(CampSingleShopPrizeDAO campSingleShopPrizeDAO) {
        this.campSingleShopPrizeDAO = campSingleShopPrizeDAO;
    }

}
