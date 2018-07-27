/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.tools;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.myteay.phoenix.common.dal.dataobject.PxGoodsDO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsPackageDetailDO;
import com.myteay.phoenix.common.dal.dataobject.PxShopDO;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel;
import com.myteay.phoenix.core.model.manage.PxShopModel;

/**
 * 后台管理模型合法性检查工具
 * 
 * @author min.weixm
 * @version $Id: PxManageValidateTool.java, v 0.1 Jul 24, 2018 11:38:27 AM min.weixm Exp $
 */
public class PxManageValidateTool {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(PxManageValidateTool.class);

    /**
     * 检查商品概要模型合法性
     * 
     * @param pxGoodsModel
     */
    public static void validatePxGoodsModel(PxGoodsModel pxGoodsModel) throws PxManageException {
        if (StringUtils.isBlank(pxGoodsModel.getShopId()) || StringUtils.isBlank(pxGoodsModel.getGoodsImage()) || StringUtils.isBlank(pxGoodsModel.getGoodsTitle()) || StringUtils.isBlank(pxGoodsModel.getGoodsDesc()) || StringUtils.isBlank(pxGoodsModel.getGoodsPrice()) || StringUtils.isBlank(pxGoodsModel.getGoodsCommPrice()) || StringUtils.isBlank(pxGoodsModel.getGoodsOnlineTime()) || pxGoodsModel.getOrderType() == null || pxGoodsModel.getIsHuiyuan() == null || pxGoodsModel.getIsQuan() == null || pxGoodsModel.getIsTuan() == null || pxGoodsModel.getGmtExpired() == null) {
            logger.warn("商品概要模型关键信息不可用 pxGoodsModel=" + pxGoodsModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_ERR);
        }
    }

    /**
     * 检查商品概要数据模型合法性
     * 
     * @param pxGoodsDO
     */
    public static void validatePxGoodsDO(PxGoodsDO pxGoodsDO) throws PxManageException {
        if (StringUtils.isBlank(pxGoodsDO.getShopId()) || StringUtils.isBlank(pxGoodsDO.getGoodsImage()) || StringUtils.isBlank(pxGoodsDO.getGoodsTitle()) || StringUtils.isBlank(pxGoodsDO.getGoodsDesc()) || StringUtils.isBlank(pxGoodsDO.getGoodsPrice()) || StringUtils.isBlank(pxGoodsDO.getGoodsCommPrice()) || StringUtils.isBlank(pxGoodsDO.getGoodsOnlineTime()) || pxGoodsDO.getOrderType() == null || StringUtils.isBlank(pxGoodsDO.getIsHuiyuan()) || StringUtils.isBlank(pxGoodsDO.getIsQuan()) || StringUtils.isBlank(pxGoodsDO.getIsTuan()) || pxGoodsDO.getGmtExpired() == null) {
            logger.warn("商品概要数据模型关键信息不可用 pxGoodsDO=" + pxGoodsDO);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_ERR);
        }
    }

    /**
     * 检查店铺模型合法性
     * 
     * @param pxShopModel
     * @throws PxManageException
     */
    public static void validatePxShopModel(PxShopModel pxShopModel) throws PxManageException {
        if (StringUtils.isBlank(pxShopModel.getOwnerIdcard()) || StringUtils.isBlank(pxShopModel.getOwnerName()) || StringUtils.isBlank(pxShopModel.getOwnerPhone()) || StringUtils.isBlank(pxShopModel.getShopAddress()) || StringUtils.isBlank(pxShopModel.getShopName()) || pxShopModel.getShopStatus() == null || StringUtils.isBlank(pxShopModel.getShopTel()) || StringUtils.isBlank(pxShopModel.getWaiterName())) {
            logger.warn("店铺关键信息不可用 pxShopModel=" + pxShopModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SHOP_MODEL_ERR);
        }
    }

    /**
     * 检查店铺数据模型合法性
     * 
     * @param pxShopDO
     * @throws PxManageException
     */
    public static void validatePxShopDO(PxShopDO pxShopDO) throws PxManageException {
        if (StringUtils.isBlank(pxShopDO.getOwnerIdcard()) || StringUtils.isBlank(pxShopDO.getOwnerName()) || StringUtils.isBlank(pxShopDO.getOwnerPhone()) || StringUtils.isBlank(pxShopDO.getShopAddress()) || StringUtils.isBlank(pxShopDO.getShopName()) || StringUtils.isBlank(pxShopDO.getShopStatus()) || StringUtils.isBlank(pxShopDO.getShopTel()) || StringUtils.isBlank(pxShopDO.getWaiterName())) {
            logger.warn("店铺数据模型关键信息不可用 pxShopDO=" + pxShopDO);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SHOP_MODEL_ERR);
        }
    }

    /**
     * 检查套餐包模型合法性
     * 
     * @param pxGoodsPackagesDetailModel
     * @throws PxManageException 
     */
    public static void validatePxGoodsPackagesDetailModel(PxGoodsPackagesDetailModel pxGoodsPackagesDetailModel) throws PxManageException {
        if (StringUtils.isBlank(pxGoodsPackagesDetailModel.getGoodsId()) || StringUtils.isBlank(pxGoodsPackagesDetailModel.getPackageDetailName())) {
            logger.warn("套餐包模型关键信息不可用 pxGoodsPackagesDetailModel=" + pxGoodsPackagesDetailModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_DETAIL_MODEL_ERR);
        }
    }

    /**
     * 检查套餐包数据模型合法性
     * 
     * @param pxGoodsPackageDetailDO
     * @throws PxManageException 
     */
    public static void validatePxGoodsPackageDetailDO(PxGoodsPackageDetailDO pxGoodsPackageDetailDO) throws PxManageException {
        if (StringUtils.isBlank(pxGoodsPackageDetailDO.getGoodsId()) || StringUtils.isBlank(pxGoodsPackageDetailDO.getPackageDetailName())) {
            logger.warn("套餐包模型关键信息不可用 pxGoodsPackageDetailDO=" + pxGoodsPackageDetailDO);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_DETAIL_MODEL_ERR);
        }
    }
}
