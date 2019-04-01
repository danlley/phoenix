/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.tools;

import org.apache.commons.lang.StringUtils;

import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsDO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsPackageDetailDO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsPackagesImageDO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsPackagesNoticeDO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsPackagesSubNoticeDO;
import com.myteay.phoenix.common.dal.dataobject.PxShopDO;
import com.myteay.phoenix.common.dal.dataobject.PxSubPackagesDO;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesImageModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesNoticeModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesSubNoticeModel;
import com.myteay.phoenix.core.model.manage.PxShopModel;
import com.myteay.phoenix.core.model.manage.PxSubPackagesModel;

/**
 * 后台管理模型合法性检查工具
 * 
 * @author min.weixm
 * @version $Id: PxManageValidateTool.java, v 0.1 Jul 24, 2018 11:38:27 AM min.weixm Exp $
 */
public class PxManageValidateTool {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /**
     * 检查商品概要模型合法性
     * 
     * @param pxGoodsModel
     */
    public static void validatePxGoodsModel(PxGoodsModel pxGoodsModel) throws PxManageException {
        if (StringUtils.isBlank(pxGoodsModel.getShopId()) || StringUtils.isBlank(pxGoodsModel.getGoodsImage())
            || StringUtils.isBlank(pxGoodsModel.getGoodsTitle()) || StringUtils.isBlank(pxGoodsModel.getGoodsDesc())
            || StringUtils.isBlank(pxGoodsModel.getGoodsPrice()) || StringUtils.isBlank(pxGoodsModel.getGoodsCommPrice())
            || StringUtils.isBlank(pxGoodsModel.getGoodsOnlineTime()) || pxGoodsModel.getOrderType() == null || pxGoodsModel.getIsHuiyuan() == null
            || pxGoodsModel.getIsQuan() == null || pxGoodsModel.getIsTuan() == null || pxGoodsModel.getGmtExpired() == null) {
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
        if (StringUtils.isBlank(pxGoodsDO.getShopId()) || StringUtils.isBlank(pxGoodsDO.getGoodsTitle()) || StringUtils.isBlank(pxGoodsDO.getGoodsDesc())
            || StringUtils.isBlank(pxGoodsDO.getGoodsPrice()) || StringUtils.isBlank(pxGoodsDO.getGoodsCommPrice())
            || StringUtils.isBlank(pxGoodsDO.getGoodsOnlineTime()) || pxGoodsDO.getOrderType() == null || StringUtils.isBlank(pxGoodsDO.getIsHuiyuan())
            || StringUtils.isBlank(pxGoodsDO.getIsQuan()) || StringUtils.isBlank(pxGoodsDO.getIsTuan()) || pxGoodsDO.getGmtExpired() == null) {
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
        if (StringUtils.isBlank(pxShopModel.getOwnerIdcard()) || StringUtils.isBlank(pxShopModel.getOwnerName())
            || StringUtils.isBlank(pxShopModel.getOwnerPhone()) || StringUtils.isBlank(pxShopModel.getShopAddress())
            || StringUtils.isBlank(pxShopModel.getShopName()) || pxShopModel.getShopStatus() == null || StringUtils.isBlank(pxShopModel.getShopTel())
            || StringUtils.isBlank(pxShopModel.getWaiterName())) {
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
        if (StringUtils.isBlank(pxShopDO.getOwnerIdcard()) || StringUtils.isBlank(pxShopDO.getOwnerName()) || StringUtils.isBlank(pxShopDO.getOwnerPhone())
            || StringUtils.isBlank(pxShopDO.getShopAddress()) || StringUtils.isBlank(pxShopDO.getShopName()) || StringUtils.isBlank(pxShopDO.getShopStatus())
            || StringUtils.isBlank(pxShopDO.getShopTel()) || StringUtils.isBlank(pxShopDO.getWaiterName())) {
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

    /**
     *  检查子套餐模型合法性
     * 
     * @param pxSubPackagesModel
     * @throws PxManageException 
     */
    public static void validatePxSubPackagesModel(PxSubPackagesModel pxSubPackagesModel) throws PxManageException {
        if (StringUtils.isBlank(pxSubPackagesModel.getPackagesDetailId()) || StringUtils.isBlank(pxSubPackagesModel.getSubPackagePrice())
            || StringUtils.isBlank(pxSubPackagesModel.getSubPackagesAmount()) || StringUtils.isBlank(pxSubPackagesModel.getSubPackagesName())
            || pxSubPackagesModel.getSubPackagesType() == null) {
            logger.warn("子套餐模型关键信息不可用 pxSubPackagesModel=" + pxSubPackagesModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SUB_PKG_MODEL_ERR);
        }
    }

    /**
     * 检查子套餐数据模型合法性
     * 
     * @param pxSubPackagesDO
     * @throws PxManageException 
     */
    public static void validatePxGoodsPackageDetailDO(PxSubPackagesDO pxSubPackagesDO) throws PxManageException {
        if (StringUtils.isBlank(pxSubPackagesDO.getPackagesDetailId()) || StringUtils.isBlank(pxSubPackagesDO.getSubPackagePrice())
            || StringUtils.isBlank(pxSubPackagesDO.getSubPackagesAmount()) || StringUtils.isBlank(pxSubPackagesDO.getSubPackagesName())
            || StringUtils.isBlank(pxSubPackagesDO.getSubPackagesType())) {
            logger.warn("子套餐数据模型关键信息不可用 pxSubPackagesDO=" + pxSubPackagesDO);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SUB_PKG_MODEL_ERR);
        }
    }

    /**
     * 检查套餐详情图片模型合法性
     * 
     * @param pxGoodsPackagesImageModel
     * @throws PxManageException 
     */
    public static void validatePxGoodsPackagesImageModel(PxGoodsPackagesImageModel pxGoodsPackagesImageModel) throws PxManageException {
        if (StringUtils.isBlank(pxGoodsPackagesImageModel.getImage())) {
            logger.warn("套餐详情图片模型关键信息不可用 pxGoodsPackagesImageModel=" + pxGoodsPackagesImageModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_IMAGE_MODEL_ERR);
        }
    }

    /**
     * 检查套餐详情图片数据模型合法性
     * 
     * @param pxGoodsPackagesImageDO
     * @throws PxManageException 
     */
    public static void validatePxGoodsPackagesImageDO(PxGoodsPackagesImageDO pxGoodsPackagesImageDO) throws PxManageException {
        if (StringUtils.isBlank(pxGoodsPackagesImageDO.getImage())) {
            logger.warn("套餐详情图片数据模型关键信息不可用 pxGoodsPackagesImageDO=" + pxGoodsPackagesImageDO);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_IMAGE_MODEL_ERR);
        }
    }

    /**
     * 检查温馨提醒摘要模型合法性
     * 
     * @param pxGoodsPackagesNoticeModel
     * @throws PxManageException 
     */
    public static void validatePxGoodsPackagesNoticeModel(PxGoodsPackagesNoticeModel pxGoodsPackagesNoticeModel) throws PxManageException {
        if (StringUtils.isBlank(pxGoodsPackagesNoticeModel.getGoodsId()) || StringUtils.isBlank(pxGoodsPackagesNoticeModel.getPackagesNoticeName())) {
            logger.warn("温馨提醒摘要模型关键信息不可用 pxGoodsPackagesDetailModel=" + pxGoodsPackagesNoticeModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_NOTICE_MODEL_ERR);
        }
    }

    /**
     * 检查温馨提醒摘要数据模型合法性
     * 
     * @param pxGoodsPackagesNoticeDO
     * @throws PxManageException 
     */
    public static void validatePxGoodsPackagesNoticeDO(PxGoodsPackagesNoticeDO pxGoodsPackagesNoticeDO) throws PxManageException {
        if (StringUtils.isBlank(pxGoodsPackagesNoticeDO.getGoodsId()) || StringUtils.isBlank(pxGoodsPackagesNoticeDO.getPackagesNoticeName())) {
            logger.warn("温馨提醒摘要数据模型关键信息不可用 pxGoodsPackagesDetailModel=" + pxGoodsPackagesNoticeDO);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_NOTICE_MODEL_ERR);
        }
    }

    /**
     * 检查温馨提醒子项模型合法性
     * 
     * @param pxGoodsPackagesSubNoticeModel
     * @throws PxManageException 
     */
    public static void validatePxGoodsPackagesSubNoticeModel(PxGoodsPackagesSubNoticeModel pxGoodsPackagesSubNoticeModel) throws PxManageException {
        if (StringUtils.isBlank(pxGoodsPackagesSubNoticeModel.getPackagesNoticeId())
            || StringUtils.isBlank(pxGoodsPackagesSubNoticeModel.getSubNoticeDetail())) {
            logger.warn("温馨提醒摘要模型关键信息不可用 pxGoodsPackagesSubNoticeModel=" + pxGoodsPackagesSubNoticeModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_SUB_NOTICE_MODEL_ERR);
        }
    }

    /**
     * 检查温馨提醒子项数据模型合法性
     * 
     * @param pxGoodsPackagesSubNoticeDO
     * @throws PxManageException 
     */
    public static void validatePxGoodsPackageDetailDO(PxGoodsPackagesSubNoticeDO pxGoodsPackagesSubNoticeDO) throws PxManageException {
        if (StringUtils.isBlank(pxGoodsPackagesSubNoticeDO.getPackagesNoticeId()) || StringUtils.isBlank(pxGoodsPackagesSubNoticeDO.getSubNoticeDetail())) {
            logger.warn("温馨提醒摘要数据模型关键信息不可用 pxGoodsPackagesSubNoticeDO=" + pxGoodsPackagesSubNoticeDO);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_SUB_NOTICE_MODEL_ERR);
        }
    }
}
