/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2021 All Rights Reserved.
 */
package com.tc.phoenix.biz.service.impl.camp.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tc.ccopass.logger.Logger;
import com.tc.ccopass.logger.LoggerFactory;
import com.tc.dbcenter.common.orm.model.PxGoodsModel;
import com.tc.phoenix.common.service.camp.integration.CampShopPrizeOutIntg;
import com.tc.phoenix.common.service.camp.integration.PxCampPrizeServiceIntg;
import com.tc.phoenix.common.service.integration.PxShopIntg;
import com.tc.phoenix.common.util.MtOperateResult;
import com.tc.phoenix.common.util.enums.MtOperateExResultEnum;
import com.tc.phoenix.common.util.enums.MtOperateResultEnum;
import com.tc.phoenix.common.util.log.LoggerNames;
import com.tc.promocore.common.orm.model.CampCashierModel;
import com.tc.promocore.common.orm.model.CampShopPrizeOutModel;
import com.tc.trade.orm.model.TcTradeModel;

/**
 * 
 * @author danlley
 * @version $Id: CampShopPrizeOutController.java, v 0.1 Mar 29, 2019 9:20:55 PM danlley Exp $
 */
@RestController
@RequestMapping(value = "myteay/api/phoenix/camp/prize/out/")
public class CampShopPrizeOutController {

    /** 日志 */
    private static final Logger    logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /** 中奖流水管理组件 */
    @Autowired
    private CampShopPrizeOutIntg   campShopPrizeOutIntg;

    /**  */
    @Autowired
    private PxCampPrizeServiceIntg pxCampPrizeServiceIntg;

    /**  */
    @Autowired
    private PxShopIntg             pxShopIntg;

    /**
     * 查询已发放奖品信息
     *  
     * @param prizeId
     * @return
     */
    @RequestMapping(value = "/list/{prizeOutId}", method = { RequestMethod.GET })
    public MtOperateResult<CampShopPrizeOutModel<PxGoodsModel>> queryShopPrizeOutById(@PathVariable String prizeOutId) {
        MtOperateResult<CampShopPrizeOutModel<PxGoodsModel>> result = null;

        MtOperateResult<CampShopPrizeOutModel<PxGoodsModel>> componentResult = null;
        try {
            componentResult = campShopPrizeOutIntg.queryShopPrizeOutById(prizeOutId);
            result = new MtOperateResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询已发放奖品信息发生未知异常  prizeOutId=" + prizeOutId + " " + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_OPERATE_FAILED);
        }

        return result;
    }

    /**
     * 执行抽奖动作
     * 
     * @param tcTradeModel
     * @return
     */
    @RequestMapping(value = "/camp/trade/ii/do", method = { RequestMethod.POST })
    public MtOperateResult<CampCashierModel<PxGoodsModel>> doCamp(@RequestBody TcTradeModel tcTradeModel) {

        if (tcTradeModel == null || StringUtils.isBlank(tcTradeModel.getShopId())) {
            logger.warn("交易模型关键参数不可用，无法完成抽奖。 tcTradeModel=" + tcTradeModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
        }

        String campId = pxShopIntg.queryCampConfig(tcTradeModel.getShopId());
        if (StringUtils.isBlank(campId)) {
            logger.warn("当前店铺未设置营销活动，无法抽奖。 tcTradeModel=" + tcTradeModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_OPERATE_FAILED);
        }

        // 设置营销活动
        tcTradeModel.setCampId(campId);

        return pxCampPrizeServiceIntg.doCamp(tcTradeModel);
    }

}
