/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.camp.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.phoenix.biz.service.impl.MtServiceResult;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampPrizeRefGoodsModel;
import com.myteay.phoenix.core.service.camp.component.CampShopPrizeRefGoodsComponent;

/**
 * 店内营销活动奖品关联商品对外服务
 * 
 * @author danlley
 * @version $Id: CampPrizeRefGoodsController.java, v 0.1 Dec 20, 2018 2:18:35 AM danlley Exp $
 */
@RestController
@RequestMapping(value = "myteay/api/phoenix/camp/manage/prize/ref/")
public class CampPrizeRefGoodsController {

    /** 日志 */
    public static final Logger             logger = Logger.getLogger(CampPrizeRefGoodsController.class);

    /** 店内到场营销活动奖品关联商品管理组件 */
    @Autowired
    private CampShopPrizeRefGoodsComponent campShopPrizeRefGoodsComponent;

    /**
     * 通过奖品ID查询店内营销活动关联奖品信息
     * 
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/list/{prizeId}", method = { RequestMethod.GET })
    public MtServiceResult<List<CampPrizeRefGoodsModel>> queryGoodsByShopId(@PathVariable String prizeId) {
        MtServiceResult<List<CampPrizeRefGoodsModel>> result = null;

        MtOperateResult<List<CampPrizeRefGoodsModel>> componentResult = null;
        try {
            componentResult = campShopPrizeRefGoodsComponent.findPrizeRefGoodsByPrizeId(prizeId);
            result = new MtServiceResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询所有店内营销活动关联奖品信息发生未知异常  prizeId=" + prizeId + " " + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_PRIZE_REF_GOODS_QRY_ERR);
        }

        return result;
    }

    /**
     * 店内营销活动关联奖品信息管理
     * 
     * @param campPrizeModel
     * @return
     */
    @RequestMapping(value = "/manage", method = { RequestMethod.POST })
    public MtServiceResult<List<CampPrizeRefGoodsModel>> manageGoods(@RequestBody List<CampPrizeRefGoodsModel> campPrizeRefGoodsModelList) {

        if (logger.isInfoEnabled()) {
            logger.info("开始管理店内营销活动关联奖品信息 campPrizeRefGoodsModelList=" + campPrizeRefGoodsModelList);
        }
        MtServiceResult<List<CampPrizeRefGoodsModel>> result = null;
        try {
            MtOperateResult<List<CampPrizeRefGoodsModel>> innerResult = campShopPrizeRefGoodsComponent.managePrizeRefGoodsInfo(campPrizeRefGoodsModelList);
            result = new MtServiceResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("管理店内营销活动关联奖品信息发生异常" + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }
}
