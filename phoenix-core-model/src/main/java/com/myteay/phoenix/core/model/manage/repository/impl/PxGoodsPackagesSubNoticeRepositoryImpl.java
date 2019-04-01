/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesSubNoticeDAO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsPackagesSubNoticeDO;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesSubNoticeModel;
import com.myteay.phoenix.core.model.manage.convertor.PxGoodsPackagesSubNoticeConvertor;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesSubNoticeRepository;
import com.myteay.phoenix.core.model.manage.tools.PxManageValidateTool;

/**
 * 温馨提醒子项管理仓储
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesSubNoticeRepositoryImpl.java, v 0.1 Aug 5, 2018 10:47:36 PM min.weixm Exp $
 */
public class PxGoodsPackagesSubNoticeRepositoryImpl implements PxGoodsPackagesSubNoticeRepository {

    /** 日志 */
    private static final Logger         logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /** 温馨提醒子项管理DAO */
    private PxGoodsPackagesSubNoticeDAO pxGoodsPackagesSubNoticeDAO;

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesSubNoticeRepository#removePackagesSubNoticeInfo(com.myteay.phoenix.core.model.manage.PxGoodsPackagesSubNoticeModel)
     */
    @Override
    public void removePackagesSubNoticeInfo(PxGoodsPackagesSubNoticeModel pxGoodsPackagesSubNoticeModel) throws PxManageException {
        if (pxGoodsPackagesSubNoticeModel == null) {
            logger.warn("当前温馨提醒子项模型不可用，无法删除温馨提醒子项信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_SUB_NOTICE_MODEL_INVALID);
        }

        // TODO 删除前，需要检查是否存在商品关联，如果存在，则不允许进行删除
        pxGoodsPackagesSubNoticeDAO.deleteById(pxGoodsPackagesSubNoticeModel.getPackagesSuNoticeId());
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesSubNoticeRepository#modifyPackagesSubNoticeInfo(com.myteay.phoenix.core.model.manage.PxGoodsPackagesSubNoticeModel)
     */
    @Override
    public PxGoodsPackagesSubNoticeModel modifyPackagesSubNoticeInfo(PxGoodsPackagesSubNoticeModel pxGoodsPackagesSubNoticeModel) throws PxManageException {
        PxGoodsPackagesSubNoticeDO pxGoodsPackagesSubNoticeDO = PxGoodsPackagesSubNoticeConvertor.convertModel2DO(pxGoodsPackagesSubNoticeModel);

        pxGoodsPackagesSubNoticeDAO.updatePxSubPackagesNotice(pxGoodsPackagesSubNoticeDO);

        PxGoodsPackagesSubNoticeDO freshPxGoodsPackagesSubNoticeDO = pxGoodsPackagesSubNoticeDAO
            .findPxSubPackagesNoticeById(pxGoodsPackagesSubNoticeDO.getPackagesSuNoticeId());
        return PxGoodsPackagesSubNoticeConvertor.convertDO2Model(freshPxGoodsPackagesSubNoticeDO);
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesSubNoticeRepository#savePackagesSubNoticeInfo(com.myteay.phoenix.core.model.manage.PxGoodsPackagesSubNoticeModel)
     */
    @Override
    public PxGoodsPackagesSubNoticeModel savePackagesSubNoticeInfo(PxGoodsPackagesSubNoticeModel pxGoodsPackagesSubNoticeModel) throws PxManageException {
        if (pxGoodsPackagesSubNoticeModel == null) {
            logger.warn("当前温馨提醒子项模型不可用，无法保存温馨提醒子项信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_SUB_NOTICE_MODEL_INVALID);
        }

        PxManageValidateTool.validatePxGoodsPackagesSubNoticeModel(pxGoodsPackagesSubNoticeModel);

        PxGoodsPackagesSubNoticeDO pxGoodsPackagesSubNoticeDO = PxGoodsPackagesSubNoticeConvertor.convertModel2DO(pxGoodsPackagesSubNoticeModel);
        String subPackagesId = pxGoodsPackagesSubNoticeDAO.insert(pxGoodsPackagesSubNoticeDO);

        PxGoodsPackagesSubNoticeDO freshPxGoodsPackagesSubNoticeDO = pxGoodsPackagesSubNoticeDAO.findPxSubPackagesNoticeById(subPackagesId);

        return PxGoodsPackagesSubNoticeConvertor.convertDO2Model(freshPxGoodsPackagesSubNoticeDO);
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesSubNoticeRepository#findSinglePackagesSubNotice(java.lang.String)
     */
    @Override
    public PxGoodsPackagesSubNoticeModel findSinglePackagesSubNotice(String packagesSubNoticeId) throws PxManageException {
        PxGoodsPackagesSubNoticeDO pxGoodsPackagesSubNoticeDO = pxGoodsPackagesSubNoticeDAO.findPxSubPackagesNoticeById(packagesSubNoticeId);
        return PxGoodsPackagesSubNoticeConvertor.convertDO2Model(pxGoodsPackagesSubNoticeDO);
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesSubNoticeRepository#findPackagesSubNoticeByGoodsId(java.lang.String)
     */
    @Override
    public List<PxGoodsPackagesSubNoticeModel> findPackagesSubNoticeByGoodsId(String packagesNoticeId) throws PxManageException {
        List<PxGoodsPackagesSubNoticeDO> pxGoodsPackagesSubNoticeDOs = pxGoodsPackagesSubNoticeDAO.findPxSubPackagesNoticeByNoticeId(packagesNoticeId);
        if (CollectionUtils.isEmpty(pxGoodsPackagesSubNoticeDOs)) {
            return null;
        }

        List<PxGoodsPackagesSubNoticeModel> modelList = new ArrayList<>();
        for (PxGoodsPackagesSubNoticeDO pxGoodsPackagesSubNoticeDO : pxGoodsPackagesSubNoticeDOs) {
            modelList.add(PxGoodsPackagesSubNoticeConvertor.convertDO2Model(pxGoodsPackagesSubNoticeDO));
        }

        return modelList;
    }

    /**
     * Setter method for property <tt>pxGoodsPackagesSubNoticeDAO</tt>.
     * 
     * @param pxGoodsPackagesSubNoticeDAO value to be assigned to property pxGoodsPackagesSubNoticeDAO
     */
    public void setPxGoodsPackagesSubNoticeDAO(PxGoodsPackagesSubNoticeDAO pxGoodsPackagesSubNoticeDAO) {
        this.pxGoodsPackagesSubNoticeDAO = pxGoodsPackagesSubNoticeDAO;
    }

}
