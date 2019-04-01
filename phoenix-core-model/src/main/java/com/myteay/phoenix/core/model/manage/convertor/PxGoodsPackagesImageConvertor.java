/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.convertor;

import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsPackagesImageDO;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesImageModel;
import com.myteay.phoenix.core.model.manage.tools.PxManageValidateTool;

/**
 * 套餐详情图片模型非法
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesImageConvertor.java, v 0.1 Aug 1, 2018 12:32:08 PM min.weixm Exp $
 */
public class PxGoodsPackagesImageConvertor {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /**
     * convert DO to model
     * 
     * @param pxGoodsPackagesImageDO
     * @return
     * @throws PxManageException
     */
    public static PxGoodsPackagesImageModel convertDO2Model(PxGoodsPackagesImageDO pxGoodsPackagesImageDO) throws PxManageException {
        if (pxGoodsPackagesImageDO == null) {
            logger.warn("当前套餐详情图片模型不可用，无法保存套餐详情图片信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_IMAGE_MODEL_INVALID);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("准备将数据模型转为对应的套餐详情图片模型 pxGoodsPackagesImageDO=" + pxGoodsPackagesImageDO);
        }

        PxGoodsPackagesImageModel pxGoodsPackagesImageModel = new PxGoodsPackagesImageModel();

        pxGoodsPackagesImageModel.setGmtCreated(pxGoodsPackagesImageDO.getGmtCreated());
        pxGoodsPackagesImageModel.setGmtModified(pxGoodsPackagesImageDO.getGmtModified());
        pxGoodsPackagesImageModel.setGoodsId(pxGoodsPackagesImageDO.getGoodsId());
        pxGoodsPackagesImageModel.setImage((pxGoodsPackagesImageDO.getImage()));
        pxGoodsPackagesImageModel.setImageId((pxGoodsPackagesImageDO.getImageId()));

        if (logger.isInfoEnabled()) {
            logger.info("套餐详情图片模型转换结束 pxGoodsPackagesImageModel=" + pxGoodsPackagesImageModel);
        }

        PxManageValidateTool.validatePxGoodsPackagesImageModel(pxGoodsPackagesImageModel);

        return pxGoodsPackagesImageModel;
    }

    /**
     * convert model to DO
     * 
     * @param pxGoodsPackagesImageModel
     * @return
     * @throws PxManageException
     */
    public static PxGoodsPackagesImageDO convertModel2DO(PxGoodsPackagesImageModel pxGoodsPackagesImageModel) throws PxManageException {
        if (pxGoodsPackagesImageModel == null) {
            logger.warn("当前套餐详情图片模型不可用，无法保存套餐详情图片信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_IMAGE_MODEL_INVALID);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("准备将套餐详情图片模型转为对应的数据模型 pxGoodsPackagesImageModel=" + pxGoodsPackagesImageModel);
        }

        PxGoodsPackagesImageDO pxGoodsPackagesImageDO = new PxGoodsPackagesImageDO();
        pxGoodsPackagesImageDO.setGmtCreated(pxGoodsPackagesImageModel.getGmtCreated());
        pxGoodsPackagesImageDO.setGmtModified(pxGoodsPackagesImageModel.getGmtModified());
        pxGoodsPackagesImageDO.setGoodsId(pxGoodsPackagesImageModel.getGoodsId());
        pxGoodsPackagesImageDO.setImage(pxGoodsPackagesImageModel.getImage());
        pxGoodsPackagesImageDO.setImageId(pxGoodsPackagesImageModel.getImageId());

        if (logger.isInfoEnabled()) {
            logger.info("数据模型转换结束 pxGoodsPackagesImageDO=" + pxGoodsPackagesImageDO);
        }

        PxManageValidateTool.validatePxGoodsPackagesImageDO(pxGoodsPackagesImageDO);

        return pxGoodsPackagesImageDO;
    }
}
