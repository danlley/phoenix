/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.convertor;

import org.apache.log4j.Logger;

import com.myteay.phoenix.common.dal.dataobject.PxGoodsPackagesSubNoticeDO;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesSubNoticeModel;
import com.myteay.phoenix.core.model.manage.tools.PxManageValidateTool;

/**
 * 温馨提醒子项模型转换器
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesSubNoticeConvertor.java, v 0.1 Aug 5, 2018 11:06:19 PM min.weixm Exp $
 */
public class PxGoodsPackagesSubNoticeConvertor {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(PxGoodsPackagesSubNoticeConvertor.class);

    /**
     * convert DO to model
     * 
     * @param pxGoodsPackagesSubNoticeDO
     * @return
     * @throws PxManageException
     */
    public static PxGoodsPackagesSubNoticeModel convertDO2Model(PxGoodsPackagesSubNoticeDO pxGoodsPackagesSubNoticeDO) throws PxManageException {
        if (pxGoodsPackagesSubNoticeDO == null) {
            logger.warn("当前温馨提醒子项模型不可用，无法保存温馨提醒子项信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_INVALID);
        }

        if (logger.isInfoEnabled()) {
            logger.info("准备将数据模型转为对应的温馨提醒子项模型 pxGoodsPackagesSubNoticeDO=" + pxGoodsPackagesSubNoticeDO);
        }

        PxGoodsPackagesSubNoticeModel pxGoodsPackagesSubNoticeModel = new PxGoodsPackagesSubNoticeModel();

        pxGoodsPackagesSubNoticeModel.setGmtCreated(pxGoodsPackagesSubNoticeDO.getGmtCreated());
        pxGoodsPackagesSubNoticeModel.setGmtModified(pxGoodsPackagesSubNoticeDO.getGmtModified());
        pxGoodsPackagesSubNoticeModel.setPackagesNoticeId((pxGoodsPackagesSubNoticeDO.getPackagesNoticeId()));
        pxGoodsPackagesSubNoticeModel.setPackagesSuNoticeId(pxGoodsPackagesSubNoticeDO.getPackagesSuNoticeId());
        pxGoodsPackagesSubNoticeModel.setSubNoticeDetail(pxGoodsPackagesSubNoticeDO.getSubNoticeDetail());

        if (logger.isInfoEnabled()) {
            logger.info("温馨提醒子项模型转换结束 pxGoodsPackagesSubNoticeModel=" + pxGoodsPackagesSubNoticeModel);
        }

        PxManageValidateTool.validatePxGoodsPackagesSubNoticeModel(pxGoodsPackagesSubNoticeModel);

        return pxGoodsPackagesSubNoticeModel;
    }

    /**
     * convert model to DO
     * 
     * @param pxGoodsPackagesSubNoticeModel
     * @return
     * @throws PxManageException
     */
    public static PxGoodsPackagesSubNoticeDO convertModel2DO(PxGoodsPackagesSubNoticeModel pxGoodsPackagesSubNoticeModel) throws PxManageException {
        if (pxGoodsPackagesSubNoticeModel == null) {
            logger.warn("当前温馨提醒子项模型不可用，无法保存温馨提醒子项信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_INVALID);
        }

        if (logger.isInfoEnabled()) {
            logger.info("准备将温馨提醒子项模型转为对应的数据模型 pxGoodsPackagesSubNoticeModel=" + pxGoodsPackagesSubNoticeModel);
        }

        PxGoodsPackagesSubNoticeDO pxGoodsPackagesSubNoticeDO = new PxGoodsPackagesSubNoticeDO();
        pxGoodsPackagesSubNoticeDO.setGmtCreated(pxGoodsPackagesSubNoticeModel.getGmtCreated());
        pxGoodsPackagesSubNoticeDO.setGmtModified(pxGoodsPackagesSubNoticeModel.getGmtModified());
        pxGoodsPackagesSubNoticeDO.setPackagesNoticeId(pxGoodsPackagesSubNoticeModel.getPackagesNoticeId());
        pxGoodsPackagesSubNoticeDO.setPackagesSuNoticeId(pxGoodsPackagesSubNoticeModel.getPackagesSuNoticeId());
        pxGoodsPackagesSubNoticeDO.setSubNoticeDetail(pxGoodsPackagesSubNoticeModel.getSubNoticeDetail());

        if (logger.isInfoEnabled()) {
            logger.info("数据模型转换结束 pxGoodsPackagesSubNoticeDO=" + pxGoodsPackagesSubNoticeDO);
        }

        PxManageValidateTool.validatePxGoodsPackageDetailDO(pxGoodsPackagesSubNoticeDO);

        return pxGoodsPackagesSubNoticeDO;
    }
}
