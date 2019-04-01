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
import com.myteay.phoenix.common.dal.daointerface.PxSubPackagesDAO;
import com.myteay.phoenix.common.dal.dataobject.PxSubPackagesDO;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxSubPackagesModel;
import com.myteay.phoenix.core.model.manage.convertor.PxSubPackagesConvertor;
import com.myteay.phoenix.core.model.manage.repository.PxSubPackagesRepository;
import com.myteay.phoenix.core.model.manage.tools.PxManageValidateTool;

/**
 * 子套餐管理仓储
 * 
 * @author min.weixm
 * @version $Id: PxSubPackagesRepositoryImpl.java, v 0.1 Jul 28, 2018 10:51:31 AM min.weixm Exp $
 */
public class PxSubPackagesRepositoryImpl implements PxSubPackagesRepository {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /** 子套餐管理DAO */
    private PxSubPackagesDAO    pxSubPackagesDAO;

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxSubPackagesRepository#removeSubPackagesInfo(com.myteay.phoenix.core.model.manage.PxSubPackagesModel)
     */
    @Override
    public void removeSubPackagesInfo(PxSubPackagesModel pxSubPackagesModel) throws PxManageException {
        if (pxSubPackagesModel == null) {
            logger.warn("当前子套餐模型不可用，无法删除子套餐信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SUB_PKG_MODEL_INVALID);
        }

        // TODO 删除前，需要检查是否存在商品关联，如果存在，则不允许进行删除
        pxSubPackagesDAO.deleteById(pxSubPackagesModel.getSubPackagesId());
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxSubPackagesRepository#modifySubPackagesInfo(com.myteay.phoenix.core.model.manage.PxSubPackagesModel)
     */
    @Override
    public PxSubPackagesModel modifySubPackagesInfo(PxSubPackagesModel pxSubPackagesModel) throws PxManageException {
        PxSubPackagesDO pxSubPackagesDO = PxSubPackagesConvertor.convertModel2DO(pxSubPackagesModel);

        pxSubPackagesDAO.updatePxSubPackages(pxSubPackagesDO);

        PxSubPackagesDO freshPxSubPackagesDO = pxSubPackagesDAO.findPxSubPackagesById(pxSubPackagesDO.getSubPackagesId());
        return PxSubPackagesConvertor.convertDO2Model(freshPxSubPackagesDO);
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxSubPackagesRepository#saveSubPackagesInfo(com.myteay.phoenix.core.model.manage.PxSubPackagesModel)
     */
    @Override
    public PxSubPackagesModel saveSubPackagesInfo(PxSubPackagesModel pxSubPackagesModel) throws PxManageException {
        if (pxSubPackagesModel == null) {
            logger.warn("当前子套餐模型不可用，无法保存子套餐信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SUB_PKG_MODEL_INVALID);
        }

        PxManageValidateTool.validatePxSubPackagesModel(pxSubPackagesModel);

        PxSubPackagesDO pxSubPackagesDO = PxSubPackagesConvertor.convertModel2DO(pxSubPackagesModel);
        String subPackagesId = pxSubPackagesDAO.insert(pxSubPackagesDO);

        PxSubPackagesDO freshPxSubPackagesDO = pxSubPackagesDAO.findPxSubPackagesById(subPackagesId);

        return PxSubPackagesConvertor.convertDO2Model(freshPxSubPackagesDO);
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxSubPackagesRepository#findAll()
     */
    @Override
    public List<PxSubPackagesModel> findAll() throws PxManageException {
        List<PxSubPackagesDO> list = pxSubPackagesDAO.findPxSubPackagesAll();

        List<PxSubPackagesModel> modelList = new ArrayList<>();
        PxSubPackagesModel pxSubPackagesModel = null;
        for (PxSubPackagesDO pxSubPackagesDO : list) {
            pxSubPackagesModel = PxSubPackagesConvertor.convertDO2Model(pxSubPackagesDO);
            if (pxSubPackagesModel != null) {
                modelList.add(pxSubPackagesModel);
            }
        }

        return modelList;
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxSubPackagesRepository#findSingleSubPackages(java.lang.String)
     */
    @Override
    public PxSubPackagesModel findSingleSubPackages(String subPackagesId) throws PxManageException {
        PxSubPackagesDO freshPxSubPackagesDO = pxSubPackagesDAO.findPxSubPackagesById(subPackagesId);
        return PxSubPackagesConvertor.convertDO2Model(freshPxSubPackagesDO);
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxSubPackagesRepository#findSubPackagesByGoodsId(java.lang.String)
     */
    @Override
    public List<PxSubPackagesModel> findSubPackagesByGoodsId(String packagesDetailId) throws PxManageException {
        List<PxSubPackagesDO> pxSubPackagesDOs = pxSubPackagesDAO.findPxSubPackagesByGoodsId(packagesDetailId);
        if (CollectionUtils.isEmpty(pxSubPackagesDOs)) {
            return null;
        }

        List<PxSubPackagesModel> modelList = new ArrayList<>();
        for (PxSubPackagesDO pxSubPackagesDO : pxSubPackagesDOs) {
            modelList.add(PxSubPackagesConvertor.convertDO2Model(pxSubPackagesDO));
        }

        return modelList;
    }

    /**
     * Setter method for property <tt>pxSubPackagesDAO</tt>.
     * 
     * @param pxSubPackagesDAO value to be assigned to property pxSubPackagesDAO
     */
    public void setPxSubPackagesDAO(PxSubPackagesDAO pxSubPackagesDAO) {
        this.pxSubPackagesDAO = pxSubPackagesDAO;
    }

}
