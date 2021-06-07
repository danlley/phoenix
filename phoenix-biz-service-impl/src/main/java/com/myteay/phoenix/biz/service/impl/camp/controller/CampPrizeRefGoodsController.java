/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2021 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.camp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.phoenix.common.service.camp.integration.CampPrizeRefGoodsIntg;
import com.tc.ccopass.logger.Logger;
import com.tc.ccopass.logger.LoggerFactory;
import com.tc.dbcenter.common.orm.model.PxGoodsModel;
import com.tc.phoenix.common.util.MtOperateResult;
import com.tc.phoenix.common.util.enums.MtOperateExResultEnum;
import com.tc.phoenix.common.util.enums.MtOperateResultEnum;
import com.tc.phoenix.common.util.log.LoggerNames;
import com.tc.promocore.common.orm.model.CampPrizeRefGoodsModel;

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
    private static final Logger   logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /** 店内到场营销活动奖品关联商品管理组件 */
    @Autowired
    private CampPrizeRefGoodsIntg campPrizeRefGoodsIntg;

    /**
     * 通过奖品ID查询店内营销活动关联奖品信息
     * 
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/list/{prizeId}", method = { RequestMethod.GET })
    public MtOperateResult<List<CampPrizeRefGoodsModel<PxGoodsModel>>> queryGoodsByShopId(@PathVariable String prizeId) {
        MtOperateResult<List<CampPrizeRefGoodsModel<PxGoodsModel>>> result = null;

        MtOperateResult<List<CampPrizeRefGoodsModel<PxGoodsModel>>> componentResult = null;
        try {
            componentResult = campPrizeRefGoodsIntg.queryPrizeRefGoodsByPrizeId(prizeId);
            result = new MtOperateResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Throwable e) {
            logger.warn("查询所有店内营销活动关联奖品信息发生未知异常  prizeId=" + prizeId + " " + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_OPERATE_FAILED);
        }

        return result;
    }

    /**
     * 店内营销活动关联奖品信息管理
     * 
     * 调试专用JSON： [{"goodsId":"32","prizeId":"14"},{"goodsId":"33","prizeId":"14"}]
     * 
     * @param campPrizeModel
     * @return
     */
    @RequestMapping(value = "/manage/{prizeId}", method = { RequestMethod.POST })
    public MtOperateResult<List<CampPrizeRefGoodsModel<PxGoodsModel>>> managePrizeGoodsRefList(@PathVariable String prizeId,
                                                                                               @RequestBody List<CampPrizeRefGoodsModel<PxGoodsModel>> campPrizeRefGoodsModelList) {

        if (logger.isInfoEnabled()) {
            logger.info("开始管理店内营销活动关联奖品信息 campPrizeRefGoodsModelList=" + campPrizeRefGoodsModelList);
        }
        MtOperateResult<List<CampPrizeRefGoodsModel<PxGoodsModel>>> result = null;
        try {
            MtOperateResult<List<CampPrizeRefGoodsModel<PxGoodsModel>>> innerResult = campPrizeRefGoodsIntg.managePrizeGoodsRefList(prizeId,
                campPrizeRefGoodsModelList);
            result = new MtOperateResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Throwable e) {
            logger.warn("管理店内营销活动关联奖品信息发生异常" + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }
}
