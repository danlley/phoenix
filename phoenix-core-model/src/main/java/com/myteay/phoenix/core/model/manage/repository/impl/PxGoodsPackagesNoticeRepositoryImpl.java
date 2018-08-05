/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesNoticeDAO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsPackagesNoticeDO;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesNoticeModel;
import com.myteay.phoenix.core.model.manage.convertor.PxGoodsPackagesNoticeConvertor;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesNoticeRepository;
import com.myteay.phoenix.core.model.manage.tools.PxManageValidateTool;

/**
 * 温馨提醒摘要管理仓储
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesNoticeRepositoryImpl.java, v 0.1 Aug 5, 2018 9:04:04 PM min.weixm Exp $
 */
public class PxGoodsPackagesNoticeRepositoryImpl implements PxGoodsPackagesNoticeRepository {

    /** 日志 */
    public static final Logger       logger = Logger.getLogger(PxGoodsPackagesNoticeRepositoryImpl.class);

    /** 温馨提醒摘要管理操作DAO */
    private PxGoodsPackagesNoticeDAO pxGoodsPackagesNoticeDAO;

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesNoticeRepository#removeGoodsPackagesNoticeInfo(com.myteay.phoenix.core.model.manage.PxGoodsPackagesNoticeModel)
     */
    @Override
    public void removeGoodsPackagesNoticeInfo(PxGoodsPackagesNoticeModel pxGoodsPackagesNoticeModel) throws PxManageException {
        if (pxGoodsPackagesNoticeModel == null) {
            logger.warn("当前套餐包模型不可用，无法删除套餐包信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_DETAIL_MODEL_INVALID);
        }

        // TODO 删除前，需要检查是否存在商品关联，如果存在，则不允许进行删除
        pxGoodsPackagesNoticeDAO.deleteById(pxGoodsPackagesNoticeModel.getPackagesNoticeId());
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesNoticeRepository#modifyGoodsPackagesNoticeInfo(com.myteay.phoenix.core.model.manage.PxGoodsPackagesNoticeModel)
     */
    @Override
    public PxGoodsPackagesNoticeModel modifyGoodsPackagesNoticeInfo(PxGoodsPackagesNoticeModel pxGoodsPackagesNoticeModel) throws PxManageException {
        PxGoodsPackagesNoticeDO pxGoodsPackagesNoticeDO = PxGoodsPackagesNoticeConvertor.convertModel2DO(pxGoodsPackagesNoticeModel);

        pxGoodsPackagesNoticeDAO.updatePxGoodsPackagesNotice(pxGoodsPackagesNoticeDO);

        PxGoodsPackagesNoticeDO freshPxGoodsPackagesNoticeDO = pxGoodsPackagesNoticeDAO.findPxGoodsPackagesNoticeById(pxGoodsPackagesNoticeDO
            .getPackagesNoticeId());
        return PxGoodsPackagesNoticeConvertor.convertDO2Model(freshPxGoodsPackagesNoticeDO);
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesNoticeRepository#saveGoodsPackagesNoticeInfo(com.myteay.phoenix.core.model.manage.PxGoodsPackagesNoticeModel)
     */
    @Override
    public PxGoodsPackagesNoticeModel saveGoodsPackagesNoticeInfo(PxGoodsPackagesNoticeModel pxGoodsPackagesNoticeModel) throws PxManageException {
        if (pxGoodsPackagesNoticeModel == null) {
            logger.warn("当前套餐包模型不可用，无法保存套餐包信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_DETAIL_MODEL_INVALID);
        }

        PxManageValidateTool.validatePxGoodsPackagesNoticeModel(pxGoodsPackagesNoticeModel);

        PxGoodsPackagesNoticeDO pxGoodsPackagesNoticeDO = PxGoodsPackagesNoticeConvertor.convertModel2DO(pxGoodsPackagesNoticeModel);
        String goodsPackageDetailId = pxGoodsPackagesNoticeDAO.insert(pxGoodsPackagesNoticeDO);

        PxGoodsPackagesNoticeDO freshPxGoodsPackagesNoticeDO = pxGoodsPackagesNoticeDAO.findPxGoodsPackagesNoticeById(goodsPackageDetailId);

        return PxGoodsPackagesNoticeConvertor.convertDO2Model(freshPxGoodsPackagesNoticeDO);
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesNoticeRepository#findSingleGoodsPackagesNotice(java.lang.String)
     */
    @Override
    public PxGoodsPackagesNoticeModel findSingleGoodsPackagesNotice(String packagesNoticeId) throws PxManageException {
        PxGoodsPackagesNoticeDO freshPxGoodsPackagesNoticeDO = pxGoodsPackagesNoticeDAO.findPxGoodsPackagesNoticeById(packagesNoticeId);
        return PxGoodsPackagesNoticeConvertor.convertDO2Model(freshPxGoodsPackagesNoticeDO);
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesNoticeRepository#findGoodsPackagesNoticeByGoodsId(java.lang.String)
     */
    @Override
    public List<PxGoodsPackagesNoticeModel> findGoodsPackagesNoticeByGoodsId(String goodsId) throws PxManageException {
        List<PxGoodsPackagesNoticeDO> pxGoodsPackagesNoticeDOs = pxGoodsPackagesNoticeDAO.findPxGoodsPackagesNoticeByGoodsId(goodsId);
        if (CollectionUtils.isEmpty(pxGoodsPackagesNoticeDOs)) {
            return null;
        }

        List<PxGoodsPackagesNoticeModel> modelList = new ArrayList<>();
        for (PxGoodsPackagesNoticeDO pxGoodsPackagesNoticeDO : pxGoodsPackagesNoticeDOs) {
            modelList.add(PxGoodsPackagesNoticeConvertor.convertDO2Model(pxGoodsPackagesNoticeDO));
        }

        return modelList;
    }

    /**
     * Setter method for property <tt>pxGoodsPackagesNoticeDAO</tt>.
     * 
     * @param pxGoodsPackagesNoticeDAO value to be assigned to property pxGoodsPackagesNoticeDAO
     */
    public void setPxGoodsPackagesNoticeDAO(PxGoodsPackagesNoticeDAO pxGoodsPackagesNoticeDAO) {
        this.pxGoodsPackagesNoticeDAO = pxGoodsPackagesNoticeDAO;
    }

}
