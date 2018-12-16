/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.camp.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopBaseDAO;
import com.myteay.phoenix.common.dal.camp.dataobject.CampBaseDO;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.camp.CampBaseModel;
import com.myteay.phoenix.core.model.camp.convertor.CampBaseConvertor;
import com.myteay.phoenix.core.model.camp.repository.CampShopBaseRepository;
import com.myteay.phoenix.core.model.camp.tools.CampValidateTool;

/**
 * 针对单个店铺店内消费到场营销活动操作仓储
 * 
 * @author danlley
 * @version $Id: CampShopBaseRepositoryImpl.java, v 0.1 Dec 16, 2018 10:41:26 PM danlley Exp $
 */
public class CampShopBaseRepositoryImpl implements CampShopBaseRepository {

    /** 日志 */
    public static final Logger    logger = Logger.getLogger(CampShopBaseRepositoryImpl.class);

    /** 针对单个店铺店内消费到场营销活动操作DAO */
    private CampSingleShopBaseDAO campSingleShopBaseDAO;

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopBaseRepository#removeGoodsInfo(com.myteay.phoenix.core.model.camp.CampBaseModel)
     */
    @Override
    public void removeGoodsInfo(CampBaseModel campBaseModel) throws PxManageException {
        if (campBaseModel == null) {
            logger.warn("当前营销基本信息模型不可用，无法删除");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_INVALID);
        }

        // TODO 删除前，需要检查是否存在奖品关联，如果存在，则不允许进行删除
        campSingleShopBaseDAO.deleteById(campBaseModel.getCampId());
    }

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopBaseRepository#modifyGoodsInfo(com.myteay.phoenix.core.model.camp.CampBaseModel)
     */
    @Override
    public CampBaseModel modifyGoodsInfo(CampBaseModel campBaseModel) throws PxManageException {
        CampBaseDO campBaseDO = CampBaseConvertor.convertModel2DO(campBaseModel, false);

        campSingleShopBaseDAO.updateCampBase(campBaseDO);

        CampBaseDO freshCampBaseDO = campSingleShopBaseDAO.findCampBaseById(campBaseDO.getCampId());
        return CampBaseConvertor.convertDO2Model(freshCampBaseDO);
    }

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopBaseRepository#saveGoodsInfo(com.myteay.phoenix.core.model.camp.CampBaseModel)
     */
    @Override
    public CampBaseModel saveGoodsInfo(CampBaseModel campBaseModel) throws PxManageException {
        if (campBaseModel == null) {
            logger.warn("当前营销基本信息模型不可用，无法保存营销基本信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_INVALID);
        }

        CampValidateTool.validateCampBaseModel(campBaseModel);

        CampBaseDO campBaseDO = CampBaseConvertor.convertModel2DO(campBaseModel, true);
        String campId = campSingleShopBaseDAO.insert(campBaseDO);

        CampBaseDO freshCampBaseDO = campSingleShopBaseDAO.findCampBaseById(campId);

        return CampBaseConvertor.convertDO2Model(freshCampBaseDO);
    }

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopBaseRepository#findAll()
     */
    @Override
    public List<CampBaseModel> findAll() throws PxManageException {
        List<CampBaseDO> list = campSingleShopBaseDAO.findCampBaseAll();

        List<CampBaseModel> modelList = new ArrayList<>();
        CampBaseModel campBaseModel = null;
        for (CampBaseDO campBaseDO : list) {
            campBaseModel = CampBaseConvertor.convertDO2Model(campBaseDO);
            if (campBaseModel != null) {
                modelList.add(campBaseModel);
            }
        }

        return modelList;
    }

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopBaseRepository#findSingleCampBase(java.lang.String)
     */
    @Override
    public CampBaseModel findSingleCampBase(String campId) throws PxManageException {
        CampBaseDO freshCampBaseDO = campSingleShopBaseDAO.findCampBaseById(campId);
        return CampBaseConvertor.convertDO2Model(freshCampBaseDO);

    }

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopBaseRepository#findCampBaseByShopId(java.lang.String)
     */
    @Override
    public List<CampBaseModel> findCampBaseByShopId(String shopId) throws PxManageException {
        List<CampBaseDO> campBaseDOs = campSingleShopBaseDAO.findCampBaseByShopId(shopId);
        if (CollectionUtils.isEmpty(campBaseDOs)) {
            return null;
        }

        List<CampBaseModel> modelList = new ArrayList<>();
        for (CampBaseDO campBaseDO : campBaseDOs) {
            modelList.add(CampBaseConvertor.convertDO2Model(campBaseDO));
        }

        return modelList;
    }

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopBaseRepository#findShopOnlineCampByShopId(java.lang.String)
     */
    @Override
    public List<CampBaseModel> findShopOnlineCampByShopId(String shopId) throws PxManageException {
        List<CampBaseDO> campBaseDOs = campSingleShopBaseDAO.findCampBaseOnlineByShopId(shopId);
        if (CollectionUtils.isEmpty(campBaseDOs)) {
            return null;
        }

        List<CampBaseModel> modelList = new ArrayList<>();
        for (CampBaseDO campBaseDO : campBaseDOs) {
            modelList.add(CampBaseConvertor.convertDO2Model(campBaseDO));
        }

        return modelList;
    }

    /**
     * Setter method for property <tt>campSingleShopBaseDAO</tt>.
     * 
     * @param campSingleShopBaseDAO value to be assigned to property campSingleShopBaseDAO
     */
    public void setCampSingleShopBaseDAO(CampSingleShopBaseDAO campSingleShopBaseDAO) {
        this.campSingleShopBaseDAO = campSingleShopBaseDAO;
    }

}
