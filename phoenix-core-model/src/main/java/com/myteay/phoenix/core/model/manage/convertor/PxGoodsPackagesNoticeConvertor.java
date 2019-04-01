/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.convertor;

import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsPackagesNoticeDO;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesNoticeModel;
import com.myteay.phoenix.core.model.manage.tools.PxManageValidateTool;

/**
 * 温馨提醒摘要模型转换器
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesNoticeConvertor.java, v 0.1 Aug 5, 2018 9:10:15 PM min.weixm Exp $
 */
public class PxGoodsPackagesNoticeConvertor {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /**
     * convert DO to model
     * 
     * @param pxGoodsPackagesNoticeDO
     * @return
     * @throws PxManageException
     */
    public static PxGoodsPackagesNoticeModel convertDO2Model(PxGoodsPackagesNoticeDO pxGoodsPackagesNoticeDO) throws PxManageException {
        if (pxGoodsPackagesNoticeDO == null) {
            logger.warn("当前温馨提醒摘要模型不可用，无法保存温馨提醒摘要信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_INVALID);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("准备将数据模型转为对应的温馨提醒摘要模型 pxGoodsPackagesNoticeDO=" + pxGoodsPackagesNoticeDO);
        }

        PxGoodsPackagesNoticeModel pxGoodsPackagesNoticeModel = new PxGoodsPackagesNoticeModel();

        pxGoodsPackagesNoticeModel.setGmtCreated(pxGoodsPackagesNoticeDO.getGmtCreated());
        pxGoodsPackagesNoticeModel.setGmtModified(pxGoodsPackagesNoticeDO.getGmtModified());
        pxGoodsPackagesNoticeModel.setGoodsId(pxGoodsPackagesNoticeDO.getGoodsId());
        pxGoodsPackagesNoticeModel.setPackagesNoticeName(pxGoodsPackagesNoticeDO.getPackagesNoticeName());
        pxGoodsPackagesNoticeModel.setPackagesNoticeId(pxGoodsPackagesNoticeDO.getPackagesNoticeId());

        if (logger.isInfoEnabled()) {
            logger.info("温馨提醒摘要模型转换结束 pxGoodsPackagesNoticeModel=" + pxGoodsPackagesNoticeModel);
        }

        PxManageValidateTool.validatePxGoodsPackagesNoticeModel(pxGoodsPackagesNoticeModel);

        return pxGoodsPackagesNoticeModel;
    }

    /**
     * convert model to DO
     * 
     * @param pxGoodsPackagesNoticeModel
     * @return
     * @throws PxManageException
     */
    public static PxGoodsPackagesNoticeDO convertModel2DO(PxGoodsPackagesNoticeModel pxGoodsPackagesNoticeModel) throws PxManageException {
        if (pxGoodsPackagesNoticeModel == null) {
            logger.warn("当前温馨提醒摘要模型不可用，无法保存温馨提醒摘要信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_INVALID);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("准备将温馨提醒摘要模型转为对应的数据模型 pxGoodsPackagesNoticeModel=" + pxGoodsPackagesNoticeModel);
        }

        PxGoodsPackagesNoticeDO pxGoodsPackagesNoticeDO = new PxGoodsPackagesNoticeDO();
        pxGoodsPackagesNoticeDO.setGmtCreated(pxGoodsPackagesNoticeModel.getGmtCreated());
        pxGoodsPackagesNoticeDO.setGmtModified(pxGoodsPackagesNoticeModel.getGmtModified());
        pxGoodsPackagesNoticeDO.setGoodsId(pxGoodsPackagesNoticeModel.getGoodsId());
        pxGoodsPackagesNoticeDO.setPackagesNoticeName(pxGoodsPackagesNoticeModel.getPackagesNoticeName());
        pxGoodsPackagesNoticeDO.setPackagesNoticeId(pxGoodsPackagesNoticeModel.getPackagesNoticeId());

        if (logger.isInfoEnabled()) {
            logger.info("数据模型转换结束 pxGoodsPackagesNoticeDO=" + pxGoodsPackagesNoticeDO);
        }

        PxManageValidateTool.validatePxGoodsPackagesNoticeDO(pxGoodsPackagesNoticeDO);

        return pxGoodsPackagesNoticeDO;
    }
}
