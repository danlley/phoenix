/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsAdvModel;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesImageModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesNoticeModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesSubNoticeModel;
import com.myteay.phoenix.core.model.manage.PxShopModel;
import com.myteay.phoenix.core.model.manage.PxSubPackagesModel;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesDetailRepository;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesImageRepository;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesNoticeRepository;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsPackagesSubNoticeRepository;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsRepository;
import com.myteay.phoenix.core.model.manage.repository.PxShopRepository;
import com.myteay.phoenix.core.model.manage.repository.PxSubPackagesRepository;
import com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent;

/**
 * 后台一般性简单业务管理组件
 * 
 * @author min.weixm
 * @version $Id: PxCommonManageComponentImpl.java, v 0.1 Jul 24, 2018 12:19:29 PM min.weixm Exp $
 */
public class PxCommonManageComponentImpl implements PxCommonManageComponent {

    /** 日志 */
    public static final Logger                 logger = Logger.getLogger(PxCommonManageComponentImpl.class);

    /** 店铺管理仓储 */
    private PxShopRepository                   pxShopRepository;

    /** 商品摘要管理仓储 */
    private PxGoodsRepository                  pxGoodsRepository;

    /** 套餐包仓储 */
    private PxGoodsPackagesDetailRepository    pxGoodsPackagesDetailRepository;

    /** 子套餐仓储 */
    private PxSubPackagesRepository            pxSubPackagesRepository;

    /** 商品详情图片管理仓储 */
    private PxGoodsPackagesImageRepository     pxGoodsPackagesImageRepository;

    /** 温馨提醒摘要管理仓储 */
    private PxGoodsPackagesNoticeRepository    pxGoodsPackagesNoticeRepository;

    /** 温馨提醒子项管理仓储 */
    private PxGoodsPackagesSubNoticeRepository pxGoodsPackagesSubNoticeRepository;

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent#queryPackagesNoticeListByNoticeId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<PxGoodsPackagesSubNoticeModel>> queryPackagesNoticeListByNoticeId(String packagesNoticeId) {
        MtOperateResult<List<PxGoodsPackagesSubNoticeModel>> result = new MtOperateResult<>();
        try {
            List<PxGoodsPackagesSubNoticeModel> pxGoodsPackagesSubNoticeModels = pxGoodsPackagesSubNoticeRepository.findPackagesSubNoticeByGoodsId(
                packagesNoticeId);
            result.setResult(pxGoodsPackagesSubNoticeModels);
        } catch (PxManageException e) {
            logger.warn("温馨提醒摘要信息查询发生异常", e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_SUB_NOTICE_QUERY_FAILD);
        }
        return result;
    }

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent#queryPackagesNoticeListByGoodsId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<PxGoodsPackagesNoticeModel>> queryPackagesNoticeListByGoodsId(String goodsId) {
        MtOperateResult<List<PxGoodsPackagesNoticeModel>> result = new MtOperateResult<>();
        try {
            List<PxGoodsPackagesNoticeModel> pxGoodsPackagesNoticeModels = pxGoodsPackagesNoticeRepository.findGoodsPackagesNoticeByGoodsId(goodsId);
            result.setResult(pxGoodsPackagesNoticeModels);
        } catch (PxManageException e) {
            logger.warn("温馨提醒摘要信息查询发生异常", e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_NOTICE_QUERY_FAILD);
        }
        return result;
    }

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent#queryGoodsAdvAll(java.lang.String)
     */
    @Override
    public MtOperateResult<PxGoodsAdvModel> queryGoodsAdvAll(String goodsId) {
        MtOperateResult<PxGoodsAdvModel> result = new MtOperateResult<>();

        try {
            PxGoodsAdvModel pxGoodsAdvModel = queryGoodsInfoAllbyGoodsId(goodsId);
            result.setResult(pxGoodsAdvModel);
        } catch (PxManageException e) {
            logger.warn("套餐高级查询失败 goodsId=" + goodsId, e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_ADV_QUERY_FAILD);
        }
        return result;
    }

    private PxGoodsAdvModel queryGoodsInfoAllbyGoodsId(String goodsId) throws PxManageException {

        PxGoodsAdvModel pxGoodsAdvModel = new PxGoodsAdvModel();

        // step 1: 填充商品摘要信息
        PxGoodsModel pxGoodsModel = pxGoodsRepository.findSingleGoods(goodsId);
        pxGoodsAdvModel.setPxGoodsModel(pxGoodsModel);

        // step 2: 填充套餐包信息（包含子套餐的填充）
        List<PxGoodsPackagesDetailModel> list = pxGoodsPackagesDetailRepository.findGoodsPackagesDetailByGoodsId(goodsId);
        if (!CollectionUtils.isEmpty(list)) {
            List<PxSubPackagesModel> subList = null;
            for (PxGoodsPackagesDetailModel pxGoodsPackagesDetailModel : list) {
                subList = pxSubPackagesRepository.findSubPackagesByGoodsId(pxGoodsPackagesDetailModel.getPackagesDetailId());
                pxGoodsPackagesDetailModel.setPxSubPackagesModels(subList);
            }
        }

        // step 3: 填充套餐图片信息
        List<PxGoodsPackagesImageModel> imageList = pxGoodsPackagesImageRepository.findGoodsPackagesImageByGoodsId(goodsId);
        pxGoodsAdvModel.setPxGoodsPackagesImageModels(imageList);

        return pxGoodsAdvModel;
    }

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent#queryPackagesImageListByGoodsId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<PxGoodsPackagesImageModel>> queryPackagesImageListByGoodsId(String goodsId) {
        MtOperateResult<List<PxGoodsPackagesImageModel>> result = new MtOperateResult<>();
        try {
            List<PxGoodsPackagesImageModel> list = pxGoodsPackagesImageRepository.findGoodsPackagesImageByGoodsId(goodsId);
            result.setResult(list);
        } catch (PxManageException e) {
            logger.warn("套餐详情图片信息查询发生异常", e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_IMAGE_QUERY_FAILD);
        }
        return result;
    }

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent#queryPackagesDetailListByGoodsId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<PxGoodsPackagesDetailModel>> queryPackagesDetailListByGoodsId(String goodsId) {
        MtOperateResult<List<PxGoodsPackagesDetailModel>> result = new MtOperateResult<>();
        try {
            List<PxGoodsPackagesDetailModel> list = pxGoodsPackagesDetailRepository.findGoodsPackagesDetailByGoodsId(goodsId);
            result.setResult(list);
        } catch (PxManageException e) {
            logger.warn("套餐包信息查询发生异常", e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_DETAIL_QUERY_FAILD);
        }
        return result;
    }

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent#queryPackagesDetailListAll()
     */
    @Override
    public MtOperateResult<List<PxGoodsPackagesDetailModel>> queryPackagesDetailListAll() {
        MtOperateResult<List<PxGoodsPackagesDetailModel>> result = new MtOperateResult<>();
        try {
            List<PxGoodsPackagesDetailModel> list = pxGoodsPackagesDetailRepository.findAll();
            result.setResult(list);
        } catch (PxManageException e) {
            logger.warn("套餐包信息查询发生异常", e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_PKG_DETAIL_QUERY_FAILD);
        }
        return result;
    }

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent#queryGoodsListByShopId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<PxGoodsModel>> queryGoodsListByShopId(String shopId) {
        MtOperateResult<List<PxGoodsModel>> result = new MtOperateResult<>();
        try {
            List<PxGoodsModel> list = pxGoodsRepository.findGoodsByShopId(shopId);
            result.setResult(list);
        } catch (PxManageException e) {
            logger.warn("店铺信息查询发生异常", e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_QUERY_FAILD);
        }
        return result;
    }

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent#queryShopAll()
     */
    @Override
    public MtOperateResult<List<PxShopModel>> queryShopAll() {
        List<PxShopModel> list = null;
        MtOperateResult<List<PxShopModel>> result = new MtOperateResult<List<PxShopModel>>();
        try {
            list = pxShopRepository.findAll();
            result.setResult(list);
        } catch (PxManageException e) {
            logger.warn("店铺信息查询发生异常", e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SHOP_QUERY_FAILD);
        }
        return result;
    }

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent#queryGoodsAll()
     */
    @Override
    public MtOperateResult<List<PxGoodsModel>> queryGoodsAll() {
        List<PxGoodsModel> list = null;
        MtOperateResult<List<PxGoodsModel>> result = new MtOperateResult<>();
        try {
            list = pxGoodsRepository.findAll();
            result.setResult(list);
        } catch (PxManageException e) {
            logger.warn("商品摘要信息查询发生异常", e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_QUERY_FAILD);
        }
        return result;
    }

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent#querySubPackagesByPackagesId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<PxSubPackagesModel>> querySubPackagesByPackagesId(String packagesDetailId) {
        MtOperateResult<List<PxSubPackagesModel>> result = new MtOperateResult<>();
        try {
            List<PxSubPackagesModel> list = pxSubPackagesRepository.findSubPackagesByGoodsId(packagesDetailId);
            result.setResult(list);
        } catch (PxManageException e) {
            logger.warn("子套餐信息查询发生异常", e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SUB_PKG_QUERY_FAILD);
        }
        return result;
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
     * Setter method for property <tt>pxShopRepository</tt>.
     * 
     * @param pxShopRepository value to be assigned to property pxShopRepository
     */
    public void setPxShopRepository(PxShopRepository pxShopRepository) {
        this.pxShopRepository = pxShopRepository;
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
     * Setter method for property <tt>pxSubPackagesRepository</tt>.
     * 
     * @param pxSubPackagesRepository value to be assigned to property pxSubPackagesRepository
     */
    public void setPxSubPackagesRepository(PxSubPackagesRepository pxSubPackagesRepository) {
        this.pxSubPackagesRepository = pxSubPackagesRepository;
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
     * Setter method for property <tt>pxGoodsPackagesNoticeRepository</tt>.
     * 
     * @param pxGoodsPackagesNoticeRepository value to be assigned to property pxGoodsPackagesNoticeRepository
     */
    public void setPxGoodsPackagesNoticeRepository(PxGoodsPackagesNoticeRepository pxGoodsPackagesNoticeRepository) {
        this.pxGoodsPackagesNoticeRepository = pxGoodsPackagesNoticeRepository;
    }

    /**
     * Setter method for property <tt>pxGoodsPackagesSubNoticeRepository</tt>.
     * 
     * @param pxGoodsPackagesSubNoticeRepository value to be assigned to property pxGoodsPackagesSubNoticeRepository
     */
    public void setPxGoodsPackagesSubNoticeRepository(PxGoodsPackagesSubNoticeRepository pxGoodsPackagesSubNoticeRepository) {
        this.pxGoodsPackagesSubNoticeRepository = pxGoodsPackagesSubNoticeRepository;
    }

}
