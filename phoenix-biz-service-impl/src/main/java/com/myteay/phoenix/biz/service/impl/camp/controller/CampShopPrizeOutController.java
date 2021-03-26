/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.camp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.phoenix.biz.service.impl.MtServiceResult;
import com.myteay.phoenix.common.service.camp.integration.CampShopPrizeOutIntg;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel;
import com.tc.common.lang.logger.Logger;
import com.tc.common.lang.logger.LoggerFactory;
import com.tc.phoenix.common.util.log.LoggerNames;

/**
 * 
 * @author danlley
 * @version $Id: CampShopPrizeOutController.java, v 0.1 Mar 29, 2019 9:20:55 PM danlley Exp $
 */
@RestController
@RequestMapping(value = "myteay/api/phoenix/camp/prize/out/")
public class CampShopPrizeOutController {

    /** 日志 */
    private static final Logger  logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /** 中奖流水管理组件 */
    @Autowired
    private CampShopPrizeOutIntg campShopPrizeOutIntg;

    /**
     * 查询已发放奖品信息
     *  
     * @param prizeId
     * @return
     */
    @RequestMapping(value = "/list/{prizeOutId}", method = { RequestMethod.GET })
    public MtServiceResult<CampShopPrizeOutModel> queryShopPrizeOutById(@PathVariable String prizeOutId) {
        MtServiceResult<CampShopPrizeOutModel> result = null;

        MtOperateResult<CampShopPrizeOutModel> componentResult = null;
        try {
            componentResult = campShopPrizeOutIntg.queryShopPrizeOutById(prizeOutId);
            result = new MtServiceResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询已发放奖品信息发生未知异常  prizeOutId=" + prizeOutId + " " + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_PRIZE_REF_GOODS_QRY_ERR);
        }

        return result;
    }

}
