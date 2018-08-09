/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.convertor;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.myteay.phoenix.common.dal.dataobject.PxGoodsDO;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsHuiyuanEnum;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsOrderTypeEnum;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsQuanEnum;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsStatusEnum;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsTuanEnum;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;
import com.myteay.phoenix.core.model.manage.tools.PxManageValidateTool;

/**
 * 商品概要模型转换器
 * 
 * @author min.weixm
 * @version $Id: PxGoodsConvertor.java, v 0.1 Jul 26, 2018 12:01:50 PM min.weixm Exp $
 */
public class PxGoodsConvertor {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(PxGoodsConvertor.class);

    /**
     * convert DO to model
     * 
     * @param pxGoodsDO
     * @return
     * @throws PxManageException
     */
    public static PxGoodsModel convertDO2Model(PxGoodsDO pxGoodsDO) throws PxManageException {
        if (pxGoodsDO == null) {
            logger.warn("当前商品概要模型不可用，无法保存商品概要信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_INVALID);
        }

        if (logger.isInfoEnabled()) {
            logger.info("准备将数据模型转为对应的商品概要模型 pxGoodsDO=" + pxGoodsDO);
        }

        PxGoodsModel pxGoodsModel = new PxGoodsModel();
        pxGoodsModel.setGmtCreated(pxGoodsDO.getGmtCreated());
        pxGoodsModel.setGmtExpired(pxGoodsDO.getGmtExpired());
        pxGoodsModel.setGmtModified(pxGoodsDO.getGmtModified());
        pxGoodsModel.setGoodsCommPrice(pxGoodsDO.getGoodsCommPrice());
        pxGoodsModel.setGoodsDesc(pxGoodsDO.getGoodsDesc());
        pxGoodsModel.setGoodsId(pxGoodsDO.getGoodsId());
        pxGoodsModel.setGoodsImage(pxGoodsDO.getGoodsImage());
        pxGoodsModel.setGoodsOnlineTime(pxGoodsDO.getGoodsOnlineTime());
        pxGoodsModel.setGoodsPrice(pxGoodsDO.getGoodsPrice());
        pxGoodsModel.setGoodsSellAmount(pxGoodsDO.getGoodsSellAmount());
        pxGoodsModel.setGoodsTitle(pxGoodsDO.getGoodsTitle());

        if (StringUtils.isNotBlank(pxGoodsDO.getGoodsStatus())) {
            pxGoodsModel.setGoodsStatus(PxGoodsStatusEnum.getByValue(pxGoodsDO.getGoodsStatus()));
        }

        if (StringUtils.isNotBlank(pxGoodsDO.getIsHuiyuan())) {
            pxGoodsModel.setIsHuiyuan(PxGoodsHuiyuanEnum.getByValue(pxGoodsDO.getIsHuiyuan()));
        }

        if (StringUtils.isNotBlank(pxGoodsDO.getIsQuan())) {
            pxGoodsModel.setIsQuan(PxGoodsQuanEnum.getByValue(pxGoodsDO.getIsQuan()));
        }

        if (StringUtils.isNotBlank(pxGoodsDO.getIsTuan())) {
            pxGoodsModel.setIsTuan(PxGoodsTuanEnum.getByValue(pxGoodsDO.getIsTuan()));
        }

        if (StringUtils.isNotBlank(pxGoodsDO.getOrderType())) {
            pxGoodsModel.setOrderType(PxGoodsOrderTypeEnum.getByValue(pxGoodsDO.getOrderType()));
        }

        pxGoodsModel.setShopId(pxGoodsDO.getShopId());

        if (logger.isInfoEnabled()) {
            logger.info("商品概要模型转换结束 pxGoodsModel=" + pxGoodsModel);
        }

        PxManageValidateTool.validatePxGoodsModel(pxGoodsModel);

        return pxGoodsModel;
    }

    /**
     * convert model to DO
     * 
     * @param pxGoodsModel
     * @return
     * @throws PxManageException
     */
    public static PxGoodsDO convertModel2DO(PxGoodsModel pxGoodsModel) throws PxManageException {
        if (pxGoodsModel == null) {
            logger.warn("当前商品概要模型不可用，无法保存商品概要信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_INVALID);
        }

        if (logger.isInfoEnabled()) {
            logger.info("准备将商品概要模型转为对应的数据模型 pxGoodsModel=" + pxGoodsModel);
        }

        PxGoodsDO pxGoodsDO = new PxGoodsDO();
        pxGoodsDO.setGmtCreated(pxGoodsModel.getGmtCreated());
        pxGoodsDO.setGmtExpired(pxGoodsModel.getGmtExpired());
        pxGoodsDO.setGmtModified(pxGoodsModel.getGmtModified());
        pxGoodsDO.setGoodsCommPrice(pxGoodsModel.getGoodsCommPrice());
        pxGoodsDO.setGoodsDesc(pxGoodsModel.getGoodsDesc());
        pxGoodsDO.setGoodsId(pxGoodsModel.getGoodsId());
        pxGoodsDO.setGoodsImage(pxGoodsModel.getGoodsImage());
        pxGoodsDO.setGoodsOnlineTime(pxGoodsModel.getGoodsOnlineTime());
        pxGoodsDO.setGoodsPrice(pxGoodsModel.getGoodsPrice());
        pxGoodsDO.setGoodsSellAmount(pxGoodsModel.getGoodsSellAmount());
        pxGoodsDO.setGoodsTitle(pxGoodsModel.getGoodsTitle());

        if (pxGoodsModel.getGoodsStatus() != null) {
            pxGoodsDO.setGoodsStatus(pxGoodsModel.getGoodsStatus().getValue());
        }

        if (pxGoodsModel.getIsHuiyuan() != null) {
            pxGoodsDO.setIsHuiyuan(pxGoodsModel.getIsHuiyuan().getValue());
        }

        if (pxGoodsModel.getIsQuan() != null) {
            pxGoodsDO.setIsQuan(pxGoodsModel.getIsQuan().getValue());
        }

        if (pxGoodsModel.getIsTuan() != null) {
            pxGoodsDO.setIsTuan(pxGoodsModel.getIsTuan().getValue());
        }

        if (pxGoodsModel.getOrderType() != null) {
            pxGoodsDO.setOrderType(pxGoodsModel.getOrderType().getValue());
        }

        pxGoodsDO.setShopId(pxGoodsModel.getShopId());

        if (logger.isInfoEnabled()) {
            logger.info("数据模型转换结束 pxGoodsDO=" + pxGoodsDO);
        }

        PxManageValidateTool.validatePxGoodsDO(pxGoodsDO);

        return pxGoodsDO;
    }
}
