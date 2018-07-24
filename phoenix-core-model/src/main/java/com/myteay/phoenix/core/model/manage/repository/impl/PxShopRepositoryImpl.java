/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.myteay.phoenix.common.dal.daointerface.PxShopDAO;
import com.myteay.phoenix.common.dal.dataobject.PxShopDO;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxShopModel;
import com.myteay.phoenix.core.model.manage.convertor.PxShopConvertor;
import com.myteay.phoenix.core.model.manage.repository.PxShopRepository;
import com.myteay.phoenix.core.model.manage.tools.PxManageValidateTool;

/**
 * 店铺管理仓储
 * 
 * @author min.weixm
 * @version $Id: PxShopRepositoryImpl.java, v 0.1 Jul 24, 2018 10:30:26 AM min.weixm Exp $
 */
public class PxShopRepositoryImpl implements PxShopRepository {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(PxShopRepositoryImpl.class);

    /** 店铺管理DAO */
    private PxShopDAO          pxShopDAO;

    /** 
     * @throws PxManageException 
     * @see com.myteay.phoenix.core.model.manage.repository.PxShopRepository#saveShopInfo(com.myteay.phoenix.core.model.manage.PxShopModel)
     */
    @Override
    public PxShopModel saveShopInfo(PxShopModel pxShopModel) throws PxManageException {
        if (pxShopModel == null) {
            logger.warn("当前店铺模型不可用，无法保存店铺信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SHOP_MODEL_INVALID);
        }

        PxManageValidateTool.validatePxShopModel(pxShopModel);

        PxShopDO pxShopDO = PxShopConvertor.convertModel2DO(pxShopModel);
        String shopId = pxShopDAO.insert(pxShopDO);

        PxShopDO freshPxShopDO = pxShopDAO.findPxShopById(shopId);

        return PxShopConvertor.convertDO2Model(freshPxShopDO);
    }

    /** 
     * @throws PxManageException 
     * @see com.myteay.phoenix.core.model.manage.repository.PxShopRepository#findAll()
     */
    @Override
    public List<PxShopModel> findAll() throws PxManageException {

        List<PxShopDO> list = pxShopDAO.findPxShopAll();

        List<PxShopModel> modelList = new ArrayList<>();
        PxShopModel pxShopModel = null;
        for (PxShopDO pxShopDO : list) {
            pxShopModel = PxShopConvertor.convertDO2Model(pxShopDO);
            if (pxShopModel != null) {
                modelList.add(pxShopModel);
            }
        }

        return modelList;
    }

    /**
     * Setter method for property <tt>pxShopDAO</tt>.
     * 
     * @param pxShopDAO value to be assigned to property pxShopDAO
     */
    public void setPxShopDAO(PxShopDAO pxShopDAO) {
        this.pxShopDAO = pxShopDAO;
    }

}
