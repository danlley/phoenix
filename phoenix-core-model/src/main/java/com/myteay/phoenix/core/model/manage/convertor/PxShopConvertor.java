/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.convertor;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.myteay.phoenix.common.dal.dataobject.PxShopDO;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.common.util.manage.enums.PxShopStatusEnum;
import com.myteay.phoenix.core.model.manage.PxShopModel;
import com.myteay.phoenix.core.model.manage.tools.PxManageValidateTool;

/**
 * 店铺模型转换器
 * 
 * @author min.weixm
 * @version $Id: PxShopConvertor.java, v 0.1 Jul 24, 2018 11:53:59 AM min.weixm Exp $
 */
public class PxShopConvertor {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(PxShopConvertor.class);

    /**
     * convert DO to model
     * 
     * @param pxShopDO
     * @return
     * @throws PxManageException
     */
    public static PxShopModel convertDO2Model(PxShopDO pxShopDO) throws PxManageException {
        if (pxShopDO == null) {
            logger.warn("当前店铺模型不可用，无法保存店铺信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SHOP_MODEL_INVALID);
        }

        if (logger.isInfoEnabled()) {
            logger.info("准备将数据模型转为对应的店铺模型 pxShopDO=" + pxShopDO);
        }

        PxShopModel pxShopModel = new PxShopModel();

        if (pxShopDO.getGmtCreated() != null) {
            pxShopModel.setGmtCreated(pxShopDO.getGmtCreated());
        }

        if (pxShopDO.getGmtExpired() != null) {
            pxShopModel.setGmtExpired(pxShopDO.getGmtExpired());
        }

        if (pxShopDO.getGmtModified() != null) {
            pxShopModel.setGmtModified(pxShopDO.getGmtModified());
        }

        pxShopModel.setOwnerIdcard(pxShopDO.getOwnerIdcard());
        pxShopModel.setOwnerName(pxShopDO.getOwnerName());
        pxShopModel.setOwnerPhone(pxShopDO.getOwnerPhone());
        pxShopModel.setShopAddress(pxShopDO.getShopAddress());
        pxShopModel.setShopId(pxShopDO.getShopId());
        pxShopModel.setShopName(pxShopDO.getShopName());

        if (StringUtils.isNotBlank(pxShopDO.getShopStatus())) {
            pxShopModel.setShopStatus(PxShopStatusEnum.getByCode(pxShopDO.getShopStatus()));
        }
        pxShopModel.setShopTel(pxShopDO.getShopTel());
        pxShopModel.setWaiterName(pxShopDO.getWaiterName());

        if (logger.isInfoEnabled()) {
            logger.info("店铺模型转换结束 pxShopModel=" + pxShopModel);
        }

        PxManageValidateTool.validatePxShopModel(pxShopModel);

        return pxShopModel;
    }

    /**
     * convert model to DO
     * 
     * @param pxShopModel
     * @return
     * @throws PxManageException
     */
    public static PxShopDO convertModel2DO(PxShopModel pxShopModel) throws PxManageException {
        if (pxShopModel == null) {
            logger.warn("当前店铺模型不可用，无法保存店铺信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SHOP_MODEL_INVALID);
        }

        if (logger.isInfoEnabled()) {
            logger.info("准备将店铺模型转为对应的数据模型 pxShopModel=" + pxShopModel);
        }

        PxShopDO pxShopDO = new PxShopDO();

        if (pxShopModel.getGmtCreated() != null) {
            pxShopDO.setGmtCreated(pxShopModel.getGmtCreated());
        }

        if (pxShopModel.getGmtExpired() != null) {
            pxShopDO.setGmtExpired(pxShopModel.getGmtExpired());
        }

        if (pxShopModel.getGmtModified() != null) {
            pxShopDO.setGmtModified(pxShopModel.getGmtModified());
        }
        pxShopDO.setOwnerIdcard(pxShopModel.getOwnerIdcard());
        pxShopDO.setOwnerName(pxShopModel.getOwnerName());
        pxShopDO.setOwnerPhone(pxShopModel.getOwnerPhone());
        pxShopDO.setShopAddress(pxShopModel.getShopAddress());

        if (StringUtils.isNotBlank(pxShopModel.getShopId())) {
            pxShopDO.setShopId(pxShopModel.getShopId());
        }

        pxShopDO.setShopName(pxShopModel.getShopName());

        if (pxShopModel.getShopStatus() != null) {
            pxShopDO.setShopStatus(pxShopModel.getShopStatus().getValue());
        }

        pxShopDO.setShopTel(pxShopModel.getShopTel());
        pxShopDO.setWaiterName(pxShopModel.getWaiterName());

        if (logger.isInfoEnabled()) {
            logger.info("数据模型转换结束 pxShopDO=" + pxShopDO);
        }

        PxManageValidateTool.validatePxShopDO(pxShopDO);

        return pxShopDO;
    }
}
