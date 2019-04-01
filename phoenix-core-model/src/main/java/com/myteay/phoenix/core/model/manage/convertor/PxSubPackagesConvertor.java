/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.convertor;

import org.apache.commons.lang.StringUtils;

import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.common.dal.dataobject.PxSubPackagesDO;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.common.util.manage.enums.PxSubPackagesTypeEnum;
import com.myteay.phoenix.core.model.manage.PxSubPackagesModel;
import com.myteay.phoenix.core.model.manage.tools.PxManageValidateTool;

/**
 * 子套餐模型转换器
 * 
 * @author min.weixm
 * @version $Id: PxSubPackagesConvertor.java, v 0.1 Jul 28, 2018 10:59:40 AM min.weixm Exp $
 */
public class PxSubPackagesConvertor {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /**
     * convert DO to model
     * 
     * @param pxSubPackagesDO
     * @return
     * @throws PxManageException
     */
    public static PxSubPackagesModel convertDO2Model(PxSubPackagesDO pxSubPackagesDO) throws PxManageException {
        if (pxSubPackagesDO == null) {
            logger.warn("当前子套餐模型不可用，无法保存子套餐信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_INVALID);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("准备将数据模型转为对应的子套餐模型 pxSubPackagesDO=" + pxSubPackagesDO);
        }

        PxSubPackagesModel pxSubPackagesModel = new PxSubPackagesModel();

        pxSubPackagesModel.setGmtCreated(pxSubPackagesDO.getGmtCreated());
        pxSubPackagesModel.setGmtModified(pxSubPackagesDO.getGmtModified());
        pxSubPackagesModel.setPackagesDetailId((pxSubPackagesDO.getPackagesDetailId()));
        pxSubPackagesModel.setSubPackagePrice(pxSubPackagesDO.getSubPackagePrice());
        pxSubPackagesModel.setSubPackagesAmount(pxSubPackagesDO.getSubPackagesAmount());
        pxSubPackagesModel.setSubPackagesId(pxSubPackagesDO.getSubPackagesId());
        pxSubPackagesModel.setSubPackagesName(pxSubPackagesDO.getSubPackagesName());

        if (StringUtils.isNotBlank(pxSubPackagesDO.getSubPackagesType())) {
            pxSubPackagesModel.setSubPackagesType(PxSubPackagesTypeEnum.getByValue(pxSubPackagesDO.getSubPackagesType()));
        }

        if (logger.isInfoEnabled()) {
            logger.info("子套餐模型转换结束 pxSubPackagesModel=" + pxSubPackagesModel);
        }

        PxManageValidateTool.validatePxSubPackagesModel(pxSubPackagesModel);

        return pxSubPackagesModel;
    }

    /**
     * convert model to DO
     * 
     * @param pxSubPackagesModel
     * @return
     * @throws PxManageException
     */
    public static PxSubPackagesDO convertModel2DO(PxSubPackagesModel pxSubPackagesModel) throws PxManageException {
        if (pxSubPackagesModel == null) {
            logger.warn("当前子套餐模型不可用，无法保存子套餐信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_INVALID);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("准备将子套餐模型转为对应的数据模型 pxSubPackagesModel=" + pxSubPackagesModel);
        }

        PxSubPackagesDO pxSubPackagesDO = new PxSubPackagesDO();
        pxSubPackagesDO.setGmtCreated(pxSubPackagesModel.getGmtCreated());
        pxSubPackagesDO.setGmtModified(pxSubPackagesModel.getGmtModified());
        pxSubPackagesDO.setPackagesDetailId(pxSubPackagesModel.getPackagesDetailId());
        pxSubPackagesDO.setSubPackagePrice(pxSubPackagesModel.getSubPackagePrice());
        pxSubPackagesDO.setSubPackagesAmount(pxSubPackagesModel.getSubPackagesAmount());
        pxSubPackagesDO.setSubPackagesId(pxSubPackagesModel.getSubPackagesId());
        pxSubPackagesDO.setSubPackagesName(pxSubPackagesModel.getSubPackagesName());

        if (pxSubPackagesModel.getSubPackagesType() != null) {
            pxSubPackagesDO.setSubPackagesType(pxSubPackagesModel.getSubPackagesType().getValue());
        }

        if (logger.isInfoEnabled()) {
            logger.info("数据模型转换结束 pxSubPackagesDO=" + pxSubPackagesDO);
        }

        PxManageValidateTool.validatePxGoodsPackageDetailDO(pxSubPackagesDO);

        return pxSubPackagesDO;
    }
}
