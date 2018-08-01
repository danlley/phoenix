/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesImageDAO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsPackagesImageDO;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesImageModel;
import com.myteay.phoenix.core.model.manage.convertor.PxGoodsPackagesImageConvertor;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesImageRepository;
import com.myteay.phoenix.core.model.manage.tools.PxManageValidateTool;

/**
 * 商品详情图片管理仓储
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesImageRepositoryImpl.java, v 0.1 Aug 1, 2018 12:24:33 PM min.weixm Exp $
 */
public class PxGoodsPackagesImageRepositoryImpl implements PxGoodsPackagesImageRepository {

    /** 日志 */
    public static final Logger      logger = Logger.getLogger(PxGoodsPackagesImageRepositoryImpl.class);

    /** 商品详情图片管理 */
    private PxGoodsPackagesImageDAO pxGoodsPackagesImageDAO;

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesImageRepository#removeGoodsPackagesImageInfo(com.myteay.phoenix.core.model.manage.PxGoodsPackagesImageModel)
     */
    @Override
    public void removeGoodsPackagesImageInfo(PxGoodsPackagesImageModel pxGoodsPackagesImageModel) throws PxManageException {
        if (pxGoodsPackagesImageModel == null) {
            logger.warn("当前套餐包模型不可用，无法删除套餐包信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_IMAGE_MODEL_INVALID);
        }

        // TODO 删除前，需要检查是否存在商品关联，如果存在，则不允许进行删除
        pxGoodsPackagesImageDAO.deleteById(pxGoodsPackagesImageModel.getImageId());
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesImageRepository#saveGoodsPackagesImageInfo(com.myteay.phoenix.core.model.manage.PxGoodsPackagesImageModel)
     */
    @Override
    public PxGoodsPackagesImageModel saveGoodsPackagesImageInfo(PxGoodsPackagesImageModel pxGoodsPackagesImageModel) throws PxManageException {
        if (pxGoodsPackagesImageModel == null) {
            logger.warn("当前套餐包模型不可用，无法保存套餐包信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_IMAGE_MODEL_INVALID);
        }

        PxManageValidateTool.validatePxGoodsPackagesImageModel(pxGoodsPackagesImageModel);

        PxGoodsPackagesImageDO pxGoodsPackagesImageDO = PxGoodsPackagesImageConvertor.convertModel2DO(pxGoodsPackagesImageModel);
        String imageId = pxGoodsPackagesImageDAO.insert(pxGoodsPackagesImageDO);

        PxGoodsPackagesImageDO freshPxGoodsPackagesImageDO = pxGoodsPackagesImageDAO.findPxGoodsPackageImageById(imageId);

        return PxGoodsPackagesImageConvertor.convertDO2Model(freshPxGoodsPackagesImageDO);
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesImageRepository#findSingleGoodsPackagesImage(java.lang.String)
     */
    @Override
    public PxGoodsPackagesImageModel findSingleGoodsPackagesImage(String imageId) throws PxManageException {
        PxGoodsPackagesImageDO freshPxGoodsPackagesImageDO = pxGoodsPackagesImageDAO.findPxGoodsPackageImageById(imageId);
        return PxGoodsPackagesImageConvertor.convertDO2Model(freshPxGoodsPackagesImageDO);
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesImageRepository#findGoodsPackagesImageByGoodsId(java.lang.String)
     */
    @Override
    public List<PxGoodsPackagesImageModel> findGoodsPackagesImageByGoodsId(String goodsId) throws PxManageException {
        List<PxGoodsPackagesImageDO> pxGoodsPackagesImageDOs = pxGoodsPackagesImageDAO.findPxGoodsPackageImageByGoodsId(goodsId);
        if (CollectionUtils.isEmpty(pxGoodsPackagesImageDOs)) {
            return null;
        }

        List<PxGoodsPackagesImageModel> modelList = new ArrayList<>();
        for (PxGoodsPackagesImageDO pxGoodsPackagesImageDO : pxGoodsPackagesImageDOs) {
            modelList.add(PxGoodsPackagesImageConvertor.convertDO2Model(pxGoodsPackagesImageDO));
        }

        return modelList;
    }

    /**
     * Setter method for property <tt>pxGoodsPackagesImageDAO</tt>.
     * 
     * @param pxGoodsPackagesImageDAO value to be assigned to property pxGoodsPackagesImageDAO
     */
    public void setPxGoodsPackagesImageDAO(PxGoodsPackagesImageDAO pxGoodsPackagesImageDAO) {
        this.pxGoodsPackagesImageDAO = pxGoodsPackagesImageDAO;
    }

}
