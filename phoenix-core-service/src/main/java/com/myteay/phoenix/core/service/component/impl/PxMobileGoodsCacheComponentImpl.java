/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.component.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesImageModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesNoticeModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesSubNoticeModel;
import com.myteay.phoenix.core.model.manage.PxSubPackagesModel;
import com.myteay.phoenix.core.model.mobile.PxMobileGoodsImagesModel;
import com.myteay.phoenix.core.model.mobile.PxMobileGoodsModel;
import com.myteay.phoenix.core.model.mobile.PxMobileGoodsNoticeModel;
import com.myteay.phoenix.core.model.mobile.PxMobileGoodsPackageModel;
import com.myteay.phoenix.core.model.mobile.PxMobileGoodsSubNoticeModel;
import com.myteay.phoenix.core.model.mobile.PxMobileGoodsSubPackageModel;
import com.myteay.phoenix.core.model.mobile.repository.PxMobileGoodsRepository;
import com.myteay.phoenix.core.service.component.PxMobileGoodsCacheComponent;
import com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent;
import com.myteay.phoenix.core.service.manage.component.PxGoodsPackagesDetailComponent;
import com.myteay.phoenix.core.service.manage.component.PxGoodsPackagesImageComponent;
import com.myteay.phoenix.core.service.manage.component.PxGoodsPackagesNoticeComponent;
import com.myteay.phoenix.core.service.manage.component.PxGoodsPackagesSubNoticeComponent;
import com.myteay.phoenix.core.service.manage.component.PxSubPackagesComponent;

/**
 * 手机端商品缓存管理组件
 * 
 * @author min.weixm
 * @version $Id: PxMobileGoodsCacheComponentImpl.java, v 0.1 Aug 17, 2018 1:02:02 AM min.weixm Exp $
 */
public class PxMobileGoodsCacheComponentImpl implements PxMobileGoodsCacheComponent, InitializingBean {

    /** 日志 */
    public static final Logger                     logger         = Logger.getLogger(PxMobileGoodsCacheComponentImpl.class);

    /** 商品列表缓存 */
    private static Map<String, PxMobileGoodsModel> PX_GOODS_CACHE = Collections.synchronizedMap(new HashMap<>());

    /** 手机端商品管理仓储 */
    private PxMobileGoodsRepository                pxMobileGoodsRepository;

    /** 后台简单业务管理组件 */
    private PxCommonManageComponent                pxCommonManageComponent;

    /** 套餐包组件 */
    private PxGoodsPackagesDetailComponent         pxGoodsPackagesDetailComponent;

    /** 子套餐管理组件 */
    private PxSubPackagesComponent                 pxSubPackagesComponent;

    /** 套餐详情图片管理组件 */
    private PxGoodsPackagesImageComponent          pxGoodsPackagesImageComponent;

    /** 温馨提醒分类管理组件 */
    private PxGoodsPackagesNoticeComponent         pxGoodsPackagesNoticeComponent;

    /** 温馨提醒子项管理组件 */
    private PxGoodsPackagesSubNoticeComponent      pxGoodsPackagesSubNoticeComponent;

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        initCache();
    }

    /** 
     * @see com.myteay.phoenix.core.service.component.PxMobileGoodsCacheComponent#queryCacheData()
     */
    @Override
    public Map<String, PxMobileGoodsModel> queryCacheData() {
        if (CollectionUtils.isEmpty(PX_GOODS_CACHE)) {
            return null;
        }

        Map<String, PxMobileGoodsModel> map = new HashMap<>();
        map.putAll(PX_GOODS_CACHE);

        return map;
    }

    /** 
     * @see com.myteay.phoenix.core.service.component.PxMobileGoodsCacheComponent#queryMobileSingleGoodsByGoodsId(java.lang.String)
     */
    @Override
    public PxMobileGoodsModel queryMobileSingleGoodsByGoodsId(String goodsId) {

        if (CollectionUtils.isEmpty(PX_GOODS_CACHE) || StringUtils.isBlank(goodsId)) {
            logger.warn("商品缓存列表不可用，或goodsId不合法 goodsId=" + goodsId);
            return null;
        }

        return PX_GOODS_CACHE.get(goodsId);
    }

    /** 
     * @see com.myteay.phoenix.core.service.component.PxMobileGoodsCacheComponent#initCache()
     */
    @Override
    public void initCache() {

        // step 1: 获取缓存数据
        List<PxMobileGoodsModel> goodsList = queryGoodsCacheList();

        // step 2: 检查缓存数据合法性
        if (CollectionUtils.isEmpty(goodsList)) {
            logger.warn("未获取到合法的商品缓存列表，当前缓存中的商品列表将被清空");
            PX_GOODS_CACHE = Collections.synchronizedMap(new HashMap<>());
            return;
        }

        // step 3: 初始化缓存信息
        for (PxMobileGoodsModel pxMobileGoodsModel : goodsList) {
            if (pxMobileGoodsModel == null) {
                continue;
            }

            fillDetailInGoodsModel(pxMobileGoodsModel);
            PX_GOODS_CACHE.put(pxMobileGoodsModel.getGoodsId(), pxMobileGoodsModel);
        }

        // step 4: 打印刷新结果
        if (logger.isInfoEnabled()) {
            logger.info("当前缓存中的商品信息 PX_GOODS_CACHE=" + PX_GOODS_CACHE);
        }
    }

    /**
     * 填充商品模型的详细内容
     * 
     * @param pxMobileGoodsModel
     */
    private void fillDetailInGoodsModel(PxMobileGoodsModel pxMobileGoodsModel) {

        if (pxMobileGoodsModel == null) {
            logger.warn("商品模型不可用，无法完成填充动作");
            return;
        }

        fillGoodsPackage(pxMobileGoodsModel);

        fillGoodsDetailImages(pxMobileGoodsModel);

        fillGoodsNotice(pxMobileGoodsModel);

    }

    /**
     * 填充商品的套餐包信息
     * 
     * @param pxMobileGoodsModel
     */
    private void fillGoodsPackage(PxMobileGoodsModel pxMobileGoodsModel) {

        MtOperateResult<List<PxGoodsPackagesDetailModel>> result = pxCommonManageComponent.queryPackagesDetailListByGoodsId(pxMobileGoodsModel.getGoodsId());
        if (logger.isInfoEnabled()) {
            logger.info("得到商品对应的套餐包信息：result=" + result);
        }

        List<PxGoodsPackagesDetailModel> models = (result == null ? null : result.getResult());
        if (CollectionUtils.isEmpty(models)) {
            logger.warn("未找到商品对应套餐包的配置信息，无法完成缓存初始化 pxMobileGoodsModel=" + pxMobileGoodsModel);
            return;
        }

        PxMobileGoodsPackageModel packageModel = null;
        List<PxMobileGoodsPackageModel> list = new ArrayList<>();
        for (PxGoodsPackagesDetailModel model : models) {

            if (model == null) {
                continue;
            }

            packageModel = new PxMobileGoodsPackageModel();
            packageModel.setPackageDetailName(model.getPackageDetailName());
            packageModel.setPackagesDetailId(model.getPackagesDetailId());

            fillGoodsSubPackage(packageModel);
            list.add(packageModel);
        }

        pxMobileGoodsModel.setPxMobileGoodsPackageModels(list);
    }

    /**
     * 填充子套餐信息
     * 
     * @param pxMobileGoodsPackageModel
     */
    private void fillGoodsSubPackage(PxMobileGoodsPackageModel pxMobileGoodsPackageModel) {
        MtOperateResult<List<PxSubPackagesModel>> result = pxCommonManageComponent
            .querySubPackagesByPackagesId(pxMobileGoodsPackageModel.getPackagesDetailId());
        if (logger.isInfoEnabled()) {
            logger.info("得到商品对应的子套餐包信息：result=" + result);
        }

        List<PxSubPackagesModel> models = (result == null ? null : result.getResult());
        if (CollectionUtils.isEmpty(models)) {
            logger.warn("未找到商品对应子套餐的配置信息，无法完成缓存初始化 pxMobileGoodsPackageModel=" + pxMobileGoodsPackageModel);
            return;
        }

        PxMobileGoodsSubPackageModel subPackageModel = null;
        List<PxMobileGoodsSubPackageModel> list = new ArrayList<>();
        for (PxSubPackagesModel model : models) {

            if (model == null) {
                continue;
            }

            subPackageModel = new PxMobileGoodsSubPackageModel();

            subPackageModel.setSubPackagePrice(model.getSubPackagePrice());
            subPackageModel.setSubPackagesAmount(model.getSubPackagesAmount());
            subPackageModel.setSubPackagesId(model.getSubPackagesId());
            subPackageModel.setSubPackagesName(model.getSubPackagesName());
            subPackageModel.setSubPackagesType(model.getSubPackagesType());

            list.add(subPackageModel);
        }

        pxMobileGoodsPackageModel.setSubPackages(list);
    }

    /**
     * 填充商品的详情图片信息
     * 
     * @param pxMobileGoodsModel
     */
    private void fillGoodsDetailImages(PxMobileGoodsModel pxMobileGoodsModel) {
        MtOperateResult<List<PxGoodsPackagesImageModel>> result = pxCommonManageComponent.queryPackagesImageListByGoodsId(pxMobileGoodsModel.getGoodsId());
        if (logger.isInfoEnabled()) {
            logger.info("得到商品对应的商品详情图片信息：result=" + result);
        }

        List<PxGoodsPackagesImageModel> models = (result == null ? null : result.getResult());
        if (CollectionUtils.isEmpty(models)) {
            logger.warn("未找到商品对应商品详情图片的配置信息，无法完成缓存初始化 pxMobileGoodsModel=" + pxMobileGoodsModel);
            return;
        }

        PxMobileGoodsImagesModel imagesModel = null;
        List<PxMobileGoodsImagesModel> list = new ArrayList<>();
        for (PxGoodsPackagesImageModel model : models) {

            if (model == null) {
                continue;
            }

            imagesModel = new PxMobileGoodsImagesModel();
            imagesModel.setImage(model.getImage());
            imagesModel.setImageId(model.getImageId());
            list.add(imagesModel);
        }

        pxMobileGoodsModel.setPxMobileGoodsImagesModels(list);
    }

    /**
     * 填充商品的温馨提醒
     * 
     * @param pxMobileGoodsModel
     */
    private void fillGoodsNotice(PxMobileGoodsModel pxMobileGoodsModel) {
        MtOperateResult<List<PxGoodsPackagesNoticeModel>> result = pxCommonManageComponent.queryPackagesNoticeListByGoodsId(pxMobileGoodsModel.getGoodsId());
        if (logger.isInfoEnabled()) {
            logger.info("得到商品对应的商品温馨提醒信息：result=" + result);
        }

        List<PxGoodsPackagesNoticeModel> models = (result == null ? null : result.getResult());
        if (CollectionUtils.isEmpty(models)) {
            logger.warn("未找到商品对应温馨提醒的配置信息，无法完成缓存初始化 pxMobileGoodsModel=" + pxMobileGoodsModel);
            return;
        }

        PxMobileGoodsNoticeModel goodsNoticeModel = null;
        List<PxMobileGoodsNoticeModel> list = new ArrayList<>();
        for (PxGoodsPackagesNoticeModel model : models) {

            if (model == null) {
                continue;
            }

            goodsNoticeModel = new PxMobileGoodsNoticeModel();
            goodsNoticeModel.setPackagesNoticeId(model.getPackagesNoticeId());
            goodsNoticeModel.setPackagesNoticeName(model.getPackagesNoticeName());
            fillGoodsSubNotice(goodsNoticeModel);
            list.add(goodsNoticeModel);
        }

        pxMobileGoodsModel.setPxMobileGoodsNotices(list);
    }

    /**
     * 填充温馨子提醒内容
     * 
     * @param pxMobileGoodsNotice
     */
    private void fillGoodsSubNotice(PxMobileGoodsNoticeModel pxMobileGoodsNotice) {
        if (pxMobileGoodsNotice == null) {
            logger.warn("当前商品的温馨提醒模型不可用，无法通过温馨提醒模型获取提醒内容列表 pxMobileGoodsNotice is null ");
            return;
        }

        MtOperateResult<List<PxGoodsPackagesSubNoticeModel>> result = pxCommonManageComponent
            .queryPackagesNoticeListByNoticeId(pxMobileGoodsNotice.getPackagesNoticeId());
        if (logger.isInfoEnabled()) {
            logger.info("得到商品对应的商品温馨子提醒信息：result=" + result);
        }

        List<PxGoodsPackagesSubNoticeModel> models = (result == null ? null : result.getResult());
        if (CollectionUtils.isEmpty(models)) {
            logger.warn("未找到商品对应温馨子提醒的配置信息，无法完成缓存初始化 pxMobileGoodsNotice=" + pxMobileGoodsNotice);
            return;
        }

        PxMobileGoodsSubNoticeModel subNotice = null;
        List<PxMobileGoodsSubNoticeModel> list = new ArrayList<>();
        for (PxGoodsPackagesSubNoticeModel model : models) {

            if (model == null) {
                continue;
            }

            subNotice = new PxMobileGoodsSubNoticeModel();
            subNotice.setPackagesSuNoticeId(model.getPackagesSuNoticeId());
            subNotice.setSubNoticeDetail(model.getSubNoticeDetail());
            list.add(subNotice);
        }

        pxMobileGoodsNotice.setSubNotices(list);

    }

    /**
     * 获取商品缓存列表
     * 
     * @return
     */
    private List<PxMobileGoodsModel> queryGoodsCacheList() {
        List<PxMobileGoodsModel> goodsList = null;
        try {
            goodsList = pxMobileGoodsRepository.findAll();
        } catch (PxManageException e) {
            logger.warn("获取商品缓存列表出错", e);
            return null;
        }

        return goodsList;
    }

    /**
     * Setter method for property <tt>pxMobileGoodsRepository</tt>.
     * 
     * @param pxMobileGoodsRepository value to be assigned to property pxMobileGoodsRepository
     */
    public void setPxMobileGoodsRepository(PxMobileGoodsRepository pxMobileGoodsRepository) {
        this.pxMobileGoodsRepository = pxMobileGoodsRepository;
    }

    /**
     * Setter method for property <tt>pxGoodsPackagesDetailComponent</tt>.
     * 
     * @param pxGoodsPackagesDetailComponent value to be assigned to property pxGoodsPackagesDetailComponent
     */
    public void setPxGoodsPackagesDetailComponent(PxGoodsPackagesDetailComponent pxGoodsPackagesDetailComponent) {
        this.pxGoodsPackagesDetailComponent = pxGoodsPackagesDetailComponent;
    }

    /**
     * Setter method for property <tt>pxSubPackagesComponent</tt>.
     * 
     * @param pxSubPackagesComponent value to be assigned to property pxSubPackagesComponent
     */
    public void setPxSubPackagesComponent(PxSubPackagesComponent pxSubPackagesComponent) {
        this.pxSubPackagesComponent = pxSubPackagesComponent;
    }

    /**
     * Setter method for property <tt>pxGoodsPackagesImageComponent</tt>.
     * 
     * @param pxGoodsPackagesImageComponent value to be assigned to property pxGoodsPackagesImageComponent
     */
    public void setPxGoodsPackagesImageComponent(PxGoodsPackagesImageComponent pxGoodsPackagesImageComponent) {
        this.pxGoodsPackagesImageComponent = pxGoodsPackagesImageComponent;
    }

    /**
     * Setter method for property <tt>pxGoodsPackagesNoticeComponent</tt>.
     * 
     * @param pxGoodsPackagesNoticeComponent value to be assigned to property pxGoodsPackagesNoticeComponent
     */
    public void setPxGoodsPackagesNoticeComponent(PxGoodsPackagesNoticeComponent pxGoodsPackagesNoticeComponent) {
        this.pxGoodsPackagesNoticeComponent = pxGoodsPackagesNoticeComponent;
    }

    /**
     * Setter method for property <tt>pxGoodsPackagesSubNoticeComponent</tt>.
     * 
     * @param pxGoodsPackagesSubNoticeComponent value to be assigned to property pxGoodsPackagesSubNoticeComponent
     */
    public void setPxGoodsPackagesSubNoticeComponent(PxGoodsPackagesSubNoticeComponent pxGoodsPackagesSubNoticeComponent) {
        this.pxGoodsPackagesSubNoticeComponent = pxGoodsPackagesSubNoticeComponent;
    }

    /**
     * Setter method for property <tt>pxCommonManageComponent</tt>.
     * 
     * @param pxCommonManageComponent value to be assigned to property pxCommonManageComponent
     */
    public void setPxCommonManageComponent(PxCommonManageComponent pxCommonManageComponent) {
        this.pxCommonManageComponent = pxCommonManageComponent;
    }

}
