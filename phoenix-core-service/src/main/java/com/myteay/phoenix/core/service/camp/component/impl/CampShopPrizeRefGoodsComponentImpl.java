/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.component.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampPrizeRefGoodsModel;
import com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRefGoodsRepository;
import com.myteay.phoenix.core.service.camp.component.CampShopPrizeComponent;
import com.myteay.phoenix.core.service.camp.component.CampShopPrizeRefGoodsComponent;
import com.myteay.phoenix.core.service.manage.component.PxGoodsComponent;

/**
 * 店内到场营销活动奖品关联商品管理组件
 * 
 * @author danlley
 * @version $Id: CampShopPrizeRefGoodsComponentImpl.java, v 0.1 Dec 20, 2018 2:08:49 AM danlley Exp $
 */
public class CampShopPrizeRefGoodsComponentImpl implements CampShopPrizeRefGoodsComponent {

    /** 日志 */
    public static final Logger              logger = Logger.getLogger(CampShopPrizeRefGoodsComponentImpl.class);

    /** 店内消费营销活动奖品关联商品仓储 */
    private CampShopPrizeRefGoodsRepository campShopPrizeRefGoodsRepository;

    /** 商品摘要管理组件 */
    private PxGoodsComponent                pxGoodsComponent;

    /** 店内营销活动奖品组件 */
    private CampShopPrizeComponent          campShopPrizeComponent;

    /** 
     * @see com.myteay.phoenix.core.service.camp.component.CampShopPrizeRefGoodsComponent#managePrizeRefGoodsInfo(java.util.List)
     */
    @Override
    public MtOperateResult<List<CampPrizeRefGoodsModel>> managePrizeRefGoodsInfo(List<CampPrizeRefGoodsModel> campPrizeRefGoodsModelList) throws PxManageException {
        if (CollectionUtils.isEmpty(campPrizeRefGoodsModelList)) {
            logger.warn("当前奖品关联商品列表不可用，无法完成奖品关联商品修改动作 campPrizeRefGoodsModelList is null");
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_PRIZE_REF_GOODS_MNG_ERR);
        }

        for (CampPrizeRefGoodsModel e : campPrizeRefGoodsModelList) {
            double goodsPrice = Double.parseDouble(pxGoodsComponent.queryGoodsModelByGoodsId(e.getGoodsId()).getGoodsPrice());
            double prizePrice = Double.parseDouble(campShopPrizeComponent.queryCampPrizeModelByPrizeId(e.getPrizeId()).getPrice());
            if (prizePrice - goodsPrice > 0) {
                return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_PRIZE_REF_GOODS_OVERFLOW);
            }
        }

        MtOperateResult<List<CampPrizeRefGoodsModel>> result = new MtOperateResult<>();
        List<CampPrizeRefGoodsModel> list = null;
        try {
            list = campShopPrizeRefGoodsRepository.modifyPrizeRefGoodsInfo(campPrizeRefGoodsModelList);
            result.setResult(list);
        } catch (PxManageException e) {
            logger.warn("管理店内营销活动奖品关联商品发生异常 campPrizeRefGoodsModelList=" + campPrizeRefGoodsModelList, e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_PRIZE_REF_GOODS_MNG_ERR);
        }

        return result;
    }

    /** 
     * @see com.myteay.phoenix.core.service.camp.component.CampShopPrizeRefGoodsComponent#findPrizeRefGoodsByPrizeId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<CampPrizeRefGoodsModel>> findPrizeRefGoodsByPrizeId(String prizeId) throws PxManageException {
        MtOperateResult<List<CampPrizeRefGoodsModel>> result = new MtOperateResult<>();
        List<CampPrizeRefGoodsModel> list = null;
        try {
            list = campShopPrizeRefGoodsRepository.findPrizeRefGoodsByPrizeId(prizeId);
            result.setResult(list);
        } catch (PxManageException e) {
            logger.warn("查询店内营销活动奖品关联商品发生异常 prizeId=" + prizeId, e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_PRIZE_REF_GOODS_QRY_ERR);
        }

        return result;
    }

    /**
     * Setter method for property <tt>campShopPrizeRefGoodsRepository</tt>.
     * 
     * @param campShopPrizeRefGoodsRepository value to be assigned to property campShopPrizeRefGoodsRepository
     */
    public void setCampShopPrizeRefGoodsRepository(CampShopPrizeRefGoodsRepository campShopPrizeRefGoodsRepository) {
        this.campShopPrizeRefGoodsRepository = campShopPrizeRefGoodsRepository;
    }

    /**
     * Setter method for property <tt>pxGoodsComponent</tt>.
     * 
     * @param pxGoodsComponent value to be assigned to property pxGoodsComponent
     */
    public void setPxGoodsComponent(PxGoodsComponent pxGoodsComponent) {
        this.pxGoodsComponent = pxGoodsComponent;
    }

    /**
     * Setter method for property <tt>campShopPrizeComponent</tt>.
     * 
     * @param campShopPrizeComponent value to be assigned to property campShopPrizeComponent
     */
    public void setCampShopPrizeComponent(CampShopPrizeComponent campShopPrizeComponent) {
        this.campShopPrizeComponent = campShopPrizeComponent;
    }

}
