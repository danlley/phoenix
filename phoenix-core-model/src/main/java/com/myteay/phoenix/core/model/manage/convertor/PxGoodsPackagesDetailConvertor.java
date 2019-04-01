/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.convertor;

import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsPackageDetailDO;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel;
import com.myteay.phoenix.core.model.manage.tools.PxManageValidateTool;

/**
 * 套餐包模型转换器
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesDetailConvertor.java, v 0.1 Jul 27, 2018 8:22:51 PM min.weixm Exp $
 */
public class PxGoodsPackagesDetailConvertor {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /**
     * convert DO to model
     * 
     * @param pxGoodsPackageDetailDO
     * @return
     * @throws PxManageException
     */
    public static PxGoodsPackagesDetailModel convertDO2Model(PxGoodsPackageDetailDO pxGoodsPackageDetailDO) throws PxManageException {
        if (pxGoodsPackageDetailDO == null) {
            logger.warn("当前套餐包模型不可用，无法保存套餐包信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_INVALID);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("准备将数据模型转为对应的套餐包模型 pxGoodsPackageDetailDO=" + pxGoodsPackageDetailDO);
        }

        PxGoodsPackagesDetailModel pxGoodsPackagesDetailModel = new PxGoodsPackagesDetailModel();

        pxGoodsPackagesDetailModel.setGmtCreated(pxGoodsPackageDetailDO.getGmtCreated());
        pxGoodsPackagesDetailModel.setGmtModified(pxGoodsPackageDetailDO.getGmtModified());
        pxGoodsPackagesDetailModel.setGoodsId(pxGoodsPackageDetailDO.getGoodsId());
        pxGoodsPackagesDetailModel.setPackageDetailName((pxGoodsPackageDetailDO.getPackageDetailName()));
        pxGoodsPackagesDetailModel.setPackagesDetailId((pxGoodsPackageDetailDO.getPackagesDetailId()));

        if (logger.isInfoEnabled()) {
            logger.info("套餐包模型转换结束 pxGoodsPackagesDetailModel=" + pxGoodsPackagesDetailModel);
        }

        PxManageValidateTool.validatePxGoodsPackagesDetailModel(pxGoodsPackagesDetailModel);

        return pxGoodsPackagesDetailModel;
    }

    /**
     * convert model to DO
     * 
     * @param pxGoodsPackagesDetailModel
     * @return
     * @throws PxManageException
     */
    public static PxGoodsPackageDetailDO convertModel2DO(PxGoodsPackagesDetailModel pxGoodsPackagesDetailModel) throws PxManageException {
        if (pxGoodsPackagesDetailModel == null) {
            logger.warn("当前套餐包模型不可用，无法保存套餐包信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_INVALID);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("准备将套餐包模型转为对应的数据模型 pxGoodsPackagesDetailModel=" + pxGoodsPackagesDetailModel);
        }

        PxGoodsPackageDetailDO pxGoodsPackageDetailDO = new PxGoodsPackageDetailDO();
        pxGoodsPackageDetailDO.setGmtCreated(pxGoodsPackagesDetailModel.getGmtCreated());
        pxGoodsPackageDetailDO.setGmtModified(pxGoodsPackagesDetailModel.getGmtModified());
        pxGoodsPackageDetailDO.setGoodsId(pxGoodsPackagesDetailModel.getGoodsId());
        pxGoodsPackageDetailDO.setPackageDetailName(pxGoodsPackagesDetailModel.getPackageDetailName());
        pxGoodsPackageDetailDO.setPackagesDetailId(pxGoodsPackagesDetailModel.getPackagesDetailId());

        if (logger.isInfoEnabled()) {
            logger.info("数据模型转换结束 pxGoodsPackageDetailDO=" + pxGoodsPackageDetailDO);
        }

        PxManageValidateTool.validatePxGoodsPackageDetailDO(pxGoodsPackageDetailDO);

        return pxGoodsPackageDetailDO;
    }
}
