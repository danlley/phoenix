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
import com.myteay.phoenix.common.dal.daointerface.PxGoodsPackageDetailDAO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsPackageDetailDO;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel;
import com.myteay.phoenix.core.model.manage.convertor.PxGoodsPackagesDetailConvertor;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesDetailRepository;
import com.myteay.phoenix.core.model.manage.tools.PxManageValidateTool;

/**
 * 套餐包管理仓储
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesDetailRepositoryImpl.java, v 0.1 Jul 27, 2018 8:12:50 PM min.weixm Exp $
 */
public class PxGoodsPackagesDetailRepositoryImpl implements PxGoodsPackagesDetailRepository {

    /** 日志 */
    private static final Logger     logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /** 套餐包管理DAO */
    private PxGoodsPackageDetailDAO pxGoodsPackageDetailDAO;

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesDetailRepository#removeGoodsPackagesDetailInfo(com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel)
     */
    @Override
    public void removeGoodsPackagesDetailInfo(PxGoodsPackagesDetailModel packagesDetailModel) throws PxManageException {
        if (packagesDetailModel == null) {
            logger.warn("当前套餐包模型不可用，无法删除套餐包信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_DETAIL_MODEL_INVALID);
        }

        // TODO 删除前，需要检查是否存在商品关联，如果存在，则不允许进行删除
        pxGoodsPackageDetailDAO.deleteById(packagesDetailModel.getPackagesDetailId());
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesDetailRepository#modifyGoodsPackagesDetailInfo(com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel)
     */
    @Override
    public PxGoodsPackagesDetailModel modifyGoodsPackagesDetailInfo(PxGoodsPackagesDetailModel packagesDetailModel) throws PxManageException {
        PxGoodsPackageDetailDO pxGoodsPackageDetailDO = PxGoodsPackagesDetailConvertor.convertModel2DO(packagesDetailModel);

        pxGoodsPackageDetailDAO.updatePxGoodsPackageDetail(pxGoodsPackageDetailDO);

        PxGoodsPackageDetailDO freshPxGoodsPackageDetailDO = pxGoodsPackageDetailDAO.findPxGoodsPackageDetailById(pxGoodsPackageDetailDO.getPackagesDetailId());
        return PxGoodsPackagesDetailConvertor.convertDO2Model(freshPxGoodsPackageDetailDO);
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesDetailRepository#saveGoodsPackagesDetailInfo(com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel)
     */
    @Override
    public PxGoodsPackagesDetailModel saveGoodsPackagesDetailInfo(PxGoodsPackagesDetailModel packagesDetailModel) throws PxManageException {
        if (packagesDetailModel == null) {
            logger.warn("当前套餐包模型不可用，无法保存套餐包信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_DETAIL_MODEL_INVALID);
        }

        PxManageValidateTool.validatePxGoodsPackagesDetailModel(packagesDetailModel);

        PxGoodsPackageDetailDO pxGoodsPackageDetailDO = PxGoodsPackagesDetailConvertor.convertModel2DO(packagesDetailModel);
        String goodsPackageDetailId = pxGoodsPackageDetailDAO.insert(pxGoodsPackageDetailDO);

        PxGoodsPackageDetailDO freshPxGoodsPackageDetailDO = pxGoodsPackageDetailDAO.findPxGoodsPackageDetailById(goodsPackageDetailId);

        return PxGoodsPackagesDetailConvertor.convertDO2Model(freshPxGoodsPackageDetailDO);
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesDetailRepository#findAll()
     */
    @Override
    public List<PxGoodsPackagesDetailModel> findAll() throws PxManageException {
        List<PxGoodsPackageDetailDO> list = pxGoodsPackageDetailDAO.findPxGoodsPackageDetailAll();

        List<PxGoodsPackagesDetailModel> modelList = new ArrayList<>();
        PxGoodsPackagesDetailModel packagesDetailModel = null;
        for (PxGoodsPackageDetailDO pxGoodsPackageDetailDO : list) {
            packagesDetailModel = PxGoodsPackagesDetailConvertor.convertDO2Model(pxGoodsPackageDetailDO);
            if (packagesDetailModel != null) {
                modelList.add(packagesDetailModel);
            }
        }

        return modelList;
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesDetailRepository#findSingleGoodsPackagesDetail(java.lang.String)
     */
    @Override
    public PxGoodsPackagesDetailModel findSingleGoodsPackagesDetail(String packagesDetailId) throws PxManageException {
        PxGoodsPackageDetailDO freshPxGoodsPackageDetailDO = pxGoodsPackageDetailDAO.findPxGoodsPackageDetailById(packagesDetailId);
        return PxGoodsPackagesDetailConvertor.convertDO2Model(freshPxGoodsPackageDetailDO);
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesDetailRepository#findGoodsPackagesDetailByGoodsId(java.lang.String)
     */
    @Override
    public List<PxGoodsPackagesDetailModel> findGoodsPackagesDetailByGoodsId(String goodsId) throws PxManageException {
        List<PxGoodsPackageDetailDO> pxGoodsPackageDetailDOs = pxGoodsPackageDetailDAO.findPxGoodsPackageDetailByGoodsId(goodsId);
        if (CollectionUtils.isEmpty(pxGoodsPackageDetailDOs)) {
            return null;
        }

        List<PxGoodsPackagesDetailModel> modelList = new ArrayList<>();
        for (PxGoodsPackageDetailDO pxGoodsPackageDetailDO : pxGoodsPackageDetailDOs) {
            modelList.add(PxGoodsPackagesDetailConvertor.convertDO2Model(pxGoodsPackageDetailDO));
        }

        return modelList;
    }

    /**
     * Setter method for property <tt>pxGoodsPackageDetailDAO</tt>.
     * 
     * @param pxGoodsPackageDetailDAO value to be assigned to property pxGoodsPackageDetailDAO
     */
    public void setPxGoodsPackageDetailDAO(PxGoodsPackageDetailDAO pxGoodsPackageDetailDAO) {
        this.pxGoodsPackageDetailDAO = pxGoodsPackageDetailDAO;
    }

}
