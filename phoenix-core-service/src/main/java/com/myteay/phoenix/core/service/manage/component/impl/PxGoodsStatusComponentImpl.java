/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.myteay.phoenix.common.util.PxConstants;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsStatusEnum;
import com.myteay.phoenix.common.util.manage.enums.PxShopStatusEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesImageModel;
import com.myteay.phoenix.core.model.manage.PxShopModel;
import com.myteay.phoenix.core.model.manage.PxSubPackagesModel;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesDetailRepository;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesImageRepository;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesNoticeRepository;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesSubNoticeRepository;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsRepository;
import com.myteay.phoenix.core.model.manage.repository.PxShopRepository;
import com.myteay.phoenix.core.model.manage.repository.PxSubPackagesRepository;
import com.myteay.phoenix.core.service.manage.component.PxGoodsStatusComponent;

/**
 * 商品状态维护组件
 * 
 * @author min.weixm
 * @version $Id: PxGoodsStatusComponentImpl.java, v 0.1 Aug 9, 2018 10:19:11 PM min.weixm Exp $
 */
public class PxGoodsStatusComponentImpl implements PxGoodsStatusComponent {

    /** 日志 */
    public static final Logger                 logger = Logger.getLogger(PxGoodsStatusComponentImpl.class);

    /** 商品概要管理仓储 */
    private PxGoodsRepository                  pxGoodsRepository;

    /** 店铺管理仓储 */
    private PxShopRepository                   pxShopRepository;

    /** 温馨提醒子项管理仓储 */
    private PxGoodsPackagesSubNoticeRepository pxGoodsPackagesSubNoticeRepository;

    /** 温馨提醒摘要管理仓储 */
    private PxGoodsPackagesNoticeRepository    pxGoodsPackagesNoticeRepository;

    /** 商品详情图片管理仓储 */
    private PxGoodsPackagesImageRepository     pxGoodsPackagesImageRepository;

    /** 套餐包仓储 */
    private PxGoodsPackagesDetailRepository    pxGoodsPackagesDetailRepository;

    /** 子套餐仓储 */
    private PxSubPackagesRepository            pxSubPackagesRepository;

    /** 
     * @throws PxManageException 
     * @see com.myteay.phoenix.core.service.manage.component.PxGoodsStatusComponent#manageGoodsStatus(com.myteay.phoenix.core.model.manage.PxGoodsModel)
     */
    @Override
    public MtOperateResult<PxGoodsModel> manageGoodsStatus(PxGoodsModel pxGoodsModel) throws PxManageException {

        if (pxGoodsModel == null || pxGoodsModel.getGoodsStatus() == null) {
            logger.warn("商品概要模型不可用，无法执行状态变更动作 pxGoodsModel= " + pxGoodsModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_INVALID);
        }

        // step 1: 商品摘要信息检查
        PxGoodsModel model = validateGoods(pxGoodsModel);

        // step 2: 店铺信息检查
        validateShop(model);

        // step 3: 商品详情图片信息检查
        validateGoodsPackagesImage(pxGoodsModel);

        // step 4: 检查套餐包信息
        validateGoodsPackagesDetail(pxGoodsModel);

        return null;
    }

    /**
     * 套餐包合法性检查
     * 
     * @param pxGoodsModel
     * @throws PxManageException
     */
    private void validateGoodsPackagesDetail(PxGoodsModel pxGoodsModel) throws PxManageException {
        List<PxGoodsPackagesDetailModel> list = pxGoodsPackagesDetailRepository.findGoodsPackagesDetailByGoodsId(pxGoodsModel.getGoodsId());

        if (CollectionUtils.isEmpty(list)) {
            logger.warn("商品套餐包信息不合法 pxGoodsModel=" + pxGoodsModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_PACKAGE_AMOUNT_ERR);
        }

        for (PxGoodsPackagesDetailModel pxGoodsPackagesDetailModel : list) {
            validateSingleGoodsPackage(pxGoodsPackagesDetailModel);
        }

    }

    /**
     * 单个套餐及子套餐信息的合法性检查
     * 
     * @param pxGoodsPackagesDetailModel
     * @throws PxManageException
     */
    private void validateSingleGoodsPackage(PxGoodsPackagesDetailModel pxGoodsPackagesDetailModel) throws PxManageException {
        if (pxGoodsPackagesDetailModel == null) {
            logger.warn("套餐包模型不可用，无法执行管理动作 pxGoodsPackagesDetailModel= " + pxGoodsPackagesDetailModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_DETAIL_MODEL_INVALID);
        }

        if (StringUtils.isBlank(pxGoodsPackagesDetailModel.getPackageDetailName()) || StringUtils.isBlank(pxGoodsPackagesDetailModel.getGoodsId())) {
            logger.warn("商品套餐包信息不合法 pxGoodsPackagesDetailModel=" + pxGoodsPackagesDetailModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_DETAIL_MODEL_INVALID);
        }

        // 检查子套餐合法性
        validateGoodsSubPackages(pxGoodsPackagesDetailModel);
    }

    /**
     * 检查子套餐合法性
     * 
     * @param pxGoodsPackagesDetailModel
     * @throws PxManageException
     */
    private void validateGoodsSubPackages(PxGoodsPackagesDetailModel pxGoodsPackagesDetailModel) throws PxManageException {
        List<PxSubPackagesModel> list = pxSubPackagesRepository.findSubPackagesByGoodsId(pxGoodsPackagesDetailModel.getPackagesDetailId());

        if (CollectionUtils.isEmpty(list)) {
            logger.warn("商品子套餐数量不合法 pxGoodsPackagesDetailModel=" + pxGoodsPackagesDetailModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_SUB_PACKAGE_AMOUNT_ERR);
        }

        for (PxSubPackagesModel pxSubPackagesModel : list) {
            validateGoodsSingleSubPackages(pxSubPackagesModel);
        }
    }

    /**
     * 检查单个子套餐信息合法性
     * 
     * @param pxSubPackagesModel
     * @throws PxManageException
     */
    private void validateGoodsSingleSubPackages(PxSubPackagesModel pxSubPackagesModel) throws PxManageException {
        if (pxSubPackagesModel == null) {
            logger.warn("子套餐模型不可用，无法执行管理动作 pxSubPackagesModel= " + pxSubPackagesModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SUB_PKG_MODEL_INVALID);
        }

        if (pxSubPackagesModel.getSubPackagesType() == null) {
            logger.warn("子套餐类型不合法，无法执行管理动作 pxSubPackagesModel= " + pxSubPackagesModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_SUB_PACKAGE_TYPE_ERR);
        }

        if (StringUtils.isBlank(pxSubPackagesModel.getSubPackagePrice())) {
            logger.warn("子套餐单价不合法，无法执行管理动作 pxSubPackagesModel= " + pxSubPackagesModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_SUB_PACKAGE_PRICE_ERR);
        }

        if (StringUtils.isBlank(pxSubPackagesModel.getSubPackagesAmount())) {
            logger.warn("子套餐数量不合法，无法执行管理动作 pxSubPackagesModel= " + pxSubPackagesModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_SUB_PACKAGE_TOTAL_ERR);
        }

        if (StringUtils.isBlank(pxSubPackagesModel.getSubPackagesName())) {
            logger.warn("子套餐名称不合法，无法执行管理动作 pxSubPackagesModel= " + pxSubPackagesModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_SUB_PACKAGE_NAME_ERR);
        }

    }

    /**
     * 验证商品详情图片合法性
     * 
     * @param pxGoodsModel
     * @throws PxManageException
     */
    private void validateGoodsPackagesImage(PxGoodsModel pxGoodsModel) throws PxManageException {
        List<PxGoodsPackagesImageModel> list = pxGoodsPackagesImageRepository.findGoodsPackagesImageByGoodsId(pxGoodsModel.getGoodsId());

        if (CollectionUtils.isEmpty(list) || list.size() != PxConstants.MAX_GOODS_DETAIL_IMAGE_AMOUNT) {
            logger.warn("商品详情图片数量不合法 pxGoodsModel=" + pxGoodsModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_IMG_AMOUNT_ERR);
        }

        for (PxGoodsPackagesImageModel image : list) {
            if (image == null) {
                continue;
            }

            if (validateSingleImageType(image.getImage())) {
                logger.warn("商品详情图片格式不合法 pxGoodsModel=" + pxGoodsModel);
                throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_IMG_DETAIL_TYPE_ERR);
            }
        }

    }

    /**
     * 检查商品中的图片格式
     * 
     * @param image
     * @return
     */
    private boolean validateSingleImageType(String image) {
        String reg = ".+(.JPEG|.jpeg|.JPG|.jpg|.PNG|.png)$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(image);
        return matcher.find();
    }

    /**
     * 验证店铺信息合法性
     * 
     * @param pxGoodsModel
     * @throws PxManageException
     */
    private void validateShop(PxGoodsModel pxGoodsModel) throws PxManageException {
        PxShopModel pxShopModel = pxShopRepository.findSingleShop(pxGoodsModel.getShopId());
        if (pxShopModel == null) {
            logger.warn("待操作目标商品不存在，无法完成相应的状态变更动作 pxGoodsModel=" + pxGoodsModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_NOTFOUND_ERR);
        }

        // TODO 需要对过期时间进行校验，如已经到过期时间，则不允许对商品进行发布操作
        if (pxShopModel.getGmtExpired() == null) {
            logger.warn("店铺过期时间不可用, pxShopModel= " + pxShopModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SHOP_EXPIRED_ERR);
        }

        if (pxShopModel.getShopStatus() == null || PxShopStatusEnum.PX_SHOP_ONLINE != pxShopModel.getShopStatus()) {
            logger.warn("店铺已下架, pxShopModel= " + pxShopModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SHOP_OFFLINE_ERR);
        }

    }

    /**
     * 商品摘要信息校验
     * 
     * @param pxGoodsModel
     * @return
     * @throws PxManageException
     */
    private PxGoodsModel validateGoods(PxGoodsModel pxGoodsModel) throws PxManageException {

        PxGoodsStatusEnum goodsStatusEnum = pxGoodsModel.getGoodsStatus();
        String goodsId = pxGoodsModel.getGoodsId();

        if (goodsStatusEnum == null || StringUtils.isBlank(goodsId)) {
            logger.warn("商品状态变更关键信息不可用 goodsId or goodsStatusEnum is null, pxGoodsModel= " + pxGoodsModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_INVALID);
        }

        PxGoodsModel model = pxGoodsRepository.findSingleGoods(goodsId);
        validateGoodsProperty(pxGoodsModel);

        PxGoodsStatusEnum targetGoodsStatusEnum = pxGoodsModel.getGoodsStatus();
        PxGoodsStatusEnum sourceGoodsStatusEnum = model.getGoodsStatus();
        if (targetGoodsStatusEnum == sourceGoodsStatusEnum) {
            logger.warn("当前的商品状态与变更预期一致，无需进行状态变更 model=" + model + " pxGoodsModel=" + pxGoodsModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_STATUS_EQUAL);
        }

        return pxGoodsModel;
    }

    /**
     * 检查商品属性完整性
     * 
     * @param pxGoodsModel
     * @throws PxManageException
     */
    private void validateGoodsProperty(PxGoodsModel pxGoodsModel) throws PxManageException {
        if (pxGoodsModel == null) {
            logger.warn("商品状态变更关键信息不可用, pxGoodsModel= " + pxGoodsModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_INVALID);
        }

        // TODO 需要对过期时间进行校验，如已经到过期时间，则不允许对商品进行发布操作
        if (pxGoodsModel.getGmtExpired() == null) {
            logger.warn("商品过期时间不可用, pxGoodsModel= " + pxGoodsModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_STATUS_EQUAL);
        }

        if (pxGoodsModel.getIsHuiyuan() == null || pxGoodsModel.getIsQuan() == null || pxGoodsModel.getIsTuan() == null || pxGoodsModel
            .getOrderType() == null) {
            logger.warn("商品团、券、会员支持、预约类型等信息不完整, pxGoodsModel= " + pxGoodsModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_TYPE_UNCOVERED);
        }

        if (StringUtils.isBlank(pxGoodsModel.getGoodsCommPrice()) || StringUtils.isBlank(pxGoodsModel.getGoodsPrice())) {
            logger.warn("商品价格信息不完整, pxGoodsModel= " + pxGoodsModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_PRICE_UNCOVERED);
        }

        if (StringUtils.isBlank(pxGoodsModel.getGoodsDesc()) || StringUtils.isBlank(pxGoodsModel.getGoodsTitle())) {
            logger.warn("商品标题及套餐信息不完整, pxGoodsModel= " + pxGoodsModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_TITLE_UNCOVERED);
        }

        if (StringUtils.isBlank(pxGoodsModel.getGoodsId()) || StringUtils.isBlank(pxGoodsModel.getShopId())) {
            logger.warn("商品Id及店铺Id信息缺失, pxGoodsModel= " + pxGoodsModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_ID_UNCOVERED);
        }

        if (StringUtils.isBlank(pxGoodsModel.getGoodsImage()) || StringUtils.isBlank(pxGoodsModel.getGoodsOnlineTime())) {
            logger.warn("商品图片或在线时间不完整, pxGoodsModel= " + pxGoodsModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_IMG_TIME_UNCOVERED);
        }

        if (validateSingleImageType(pxGoodsModel.getGoodsImage())) {
            logger.warn("商品摘要图片格式不合法 pxGoodsModel=" + pxGoodsModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_IMG_TYPE_ERR);
        }

    }

    /**
     * Setter method for property <tt>pxGoodsRepository</tt>.
     * 
     * @param pxGoodsRepository value to be assigned to property pxGoodsRepository
     */
    public void setPxGoodsRepository(PxGoodsRepository pxGoodsRepository) {
        this.pxGoodsRepository = pxGoodsRepository;
    }

    /**
     * Setter method for property <tt>pxShopRepository</tt>.
     * 
     * @param pxShopRepository value to be assigned to property pxShopRepository
     */
    public void setPxShopRepository(PxShopRepository pxShopRepository) {
        this.pxShopRepository = pxShopRepository;
    }

    /**
     * Setter method for property <tt>pxGoodsPackagesSubNoticeRepository</tt>.
     * 
     * @param pxGoodsPackagesSubNoticeRepository value to be assigned to property pxGoodsPackagesSubNoticeRepository
     */
    public void setPxGoodsPackagesSubNoticeRepository(PxGoodsPackagesSubNoticeRepository pxGoodsPackagesSubNoticeRepository) {
        this.pxGoodsPackagesSubNoticeRepository = pxGoodsPackagesSubNoticeRepository;
    }

    /**
     * Setter method for property <tt>pxGoodsPackagesNoticeRepository</tt>.
     * 
     * @param pxGoodsPackagesNoticeRepository value to be assigned to property pxGoodsPackagesNoticeRepository
     */
    public void setPxGoodsPackagesNoticeRepository(PxGoodsPackagesNoticeRepository pxGoodsPackagesNoticeRepository) {
        this.pxGoodsPackagesNoticeRepository = pxGoodsPackagesNoticeRepository;
    }

    /**
     * Setter method for property <tt>pxGoodsPackagesImageRepository</tt>.
     * 
     * @param pxGoodsPackagesImageRepository value to be assigned to property pxGoodsPackagesImageRepository
     */
    public void setPxGoodsPackagesImageRepository(PxGoodsPackagesImageRepository pxGoodsPackagesImageRepository) {
        this.pxGoodsPackagesImageRepository = pxGoodsPackagesImageRepository;
    }

    /**
     * Setter method for property <tt>pxGoodsPackagesDetailRepository</tt>.
     * 
     * @param pxGoodsPackagesDetailRepository value to be assigned to property pxGoodsPackagesDetailRepository
     */
    public void setPxGoodsPackagesDetailRepository(PxGoodsPackagesDetailRepository pxGoodsPackagesDetailRepository) {
        this.pxGoodsPackagesDetailRepository = pxGoodsPackagesDetailRepository;
    }

    /**
     * Setter method for property <tt>pxSubPackagesRepository</tt>.
     * 
     * @param pxSubPackagesRepository value to be assigned to property pxSubPackagesRepository
     */
    public void setPxSubPackagesRepository(PxSubPackagesRepository pxSubPackagesRepository) {
        this.pxSubPackagesRepository = pxSubPackagesRepository;
    }

}