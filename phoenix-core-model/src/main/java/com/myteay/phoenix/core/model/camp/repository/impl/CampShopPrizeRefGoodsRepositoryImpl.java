/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.camp.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopPrizeGoodsRefDAO;
import com.myteay.phoenix.common.dal.camp.dataobject.CampPrizeGoodsRefDO;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.camp.CampPrizeRefGoodsModel;
import com.myteay.phoenix.core.model.camp.convertor.CampPrizeRefGoodsConvertor;
import com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRefGoodsRepository;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsRepository;

/**
 * 店内消费营销活动奖品关联商品仓储
 * 
 * @author danlley
 * @version $Id: CampShopPrizeRefGoodsRepositoryImpl.java, v 0.1 Dec 20, 2018 12:41:17 AM danlley Exp $
 */
public class CampShopPrizeRefGoodsRepositoryImpl implements CampShopPrizeRefGoodsRepository {

    /** 日志 */
    public static final Logger             logger = Logger.getLogger(CampShopPrizeRefGoodsRepositoryImpl.class);

    /** 奖品关联商品操作DAO */
    private CampSingleShopPrizeGoodsRefDAO campSingleShopPrizeGoodsRefDAO;

    /** 商品概要管理仓储 */
    private PxGoodsRepository              pxGoodsRepository;

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRefGoodsRepository#modifyPrizeRefGoodsInfo(java.util.List)
     */
    @Override
    public List<CampPrizeRefGoodsModel> modifyPrizeRefGoodsInfo(List<CampPrizeRefGoodsModel> campPrizeRefGoodsModelList) throws PxManageException {
        if (CollectionUtils.isEmpty(campPrizeRefGoodsModelList)) {
            logger.warn("当前奖品关联商品列表不可用，无法完成奖品关联商品修改动作 campPrizeRefGoodsModelList is null");
            return null;
        }

        String prizeId = campPrizeRefGoodsModelList.get(0).getPrizeId();
        if (StringUtils.isBlank(prizeId)) {
            logger.warn("奖品关联商品模型有误,无法完成历史关联记录清理");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_PRIZE_REF_GOODS_MODEL_ERR);
        }

        campSingleShopPrizeGoodsRefDAO.deleteById(prizeId);

        return savePrizeRefGoodsInfo(campPrizeRefGoodsModelList);
    }

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRefGoodsRepository#savePrizeRefGoodsInfo(java.util.List)
     */
    @Override
    public List<CampPrizeRefGoodsModel> savePrizeRefGoodsInfo(List<CampPrizeRefGoodsModel> campPrizeRefGoodsModelList) throws PxManageException {

        if (CollectionUtils.isEmpty(campPrizeRefGoodsModelList)) {
            logger.warn("当前奖品关联商品列表不可用，无法完成奖品关联商品保存动作 campPrizeRefGoodsModelList is null");
            return null;
        }

        CampPrizeGoodsRefDO campPrizeGoodsRefDO = null;
        String prizeId = null;
        for (CampPrizeRefGoodsModel campPrizeRefGoodsModel : campPrizeRefGoodsModelList) {
            campPrizeGoodsRefDO = CampPrizeRefGoodsConvertor.convertModel2DO(campPrizeRefGoodsModel, true);
            prizeId = campSingleShopPrizeGoodsRefDAO.insert(campPrizeGoodsRefDO);
        }

        return findPrizeRefGoodsByPrizeId(prizeId);
    }

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRefGoodsRepository#findPrizeRefGoodsByPrizeId(java.lang.String)
     */
    @Override
    public List<CampPrizeRefGoodsModel> findPrizeRefGoodsByPrizeId(String prizeId) throws PxManageException {
        List<CampPrizeGoodsRefDO> list = campSingleShopPrizeGoodsRefDAO.findPrizeGoodsRefByPrizeId(prizeId);

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        List<CampPrizeRefGoodsModel> modelList = new ArrayList<>();
        CampPrizeRefGoodsModel campPrizeRefGoodsModel = null;
        for (CampPrizeGoodsRefDO campPrizeGoodsRefDO : list) {
            campPrizeRefGoodsModel = CampPrizeRefGoodsConvertor.convertDO2Model(campPrizeGoodsRefDO);
            campPrizeRefGoodsModel.setPxGoodsModel(pxGoodsRepository.findSingleGoods(campPrizeRefGoodsModel.getGoodsId()));
            modelList.add(campPrizeRefGoodsModel);
        }

        return modelList;
    }

    /** 
     * @see com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRefGoodsRepository#isRefedGoodsByCampPrize(java.lang.String)
     */
    @Override
    public boolean isRefedGoodsByCampPrize(String goodsId) throws PxManageException {
        List<CampPrizeGoodsRefDO> list = campSingleShopPrizeGoodsRefDAO.findPrizeGoodsRefByGoodsId(goodsId);

        return !CollectionUtils.isEmpty(list);
    }

    /**
     * Setter method for property <tt>campSingleShopPrizeGoodsRefDAO</tt>.
     * 
     * @param campSingleShopPrizeGoodsRefDAO value to be assigned to property campSingleShopPrizeGoodsRefDAO
     */
    public void setCampSingleShopPrizeGoodsRefDAO(CampSingleShopPrizeGoodsRefDAO campSingleShopPrizeGoodsRefDAO) {
        this.campSingleShopPrizeGoodsRefDAO = campSingleShopPrizeGoodsRefDAO;
    }

    /**
     * Setter method for property <tt>pxGoodsRepository</tt>.
     * 
     * @param pxGoodsRepository value to be assigned to property pxGoodsRepository
     */
    public void setPxGoodsRepository(PxGoodsRepository pxGoodsRepository) {
        this.pxGoodsRepository = pxGoodsRepository;
    }

}
