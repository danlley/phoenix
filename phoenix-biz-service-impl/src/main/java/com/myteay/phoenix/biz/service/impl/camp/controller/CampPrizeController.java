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
import com.myteay.phoenix.core.model.camp.CampPrizeModel;
import com.myteay.phoenix.core.service.camp.component.CampShopPrizeComponent;
import com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent;

/**
 * 到店消费营销活动奖品维护
 * 
 * @author danlley
 * @version $Id: CampPrizeController.java, v 0.1 Dec 17, 2018 11:23:22 PM danlley Exp $
 */
@RestController
@RequestMapping(value = "myteay/api/phoenix/camp/manage/prize")
public class CampPrizeController {

    /** 日志 */
    public static final Logger      logger = Logger.getLogger(CampBaseController.class);

    /** 店内营销活动基本信息管理组件 */
    @Autowired
    private CampShopPrizeComponent  campShopPrizeComponent;

    /** 后台一般性简单业务管理组件 */
    @Autowired
    private PxCommonManageComponent pxCommonManageComponent;

    /**
     * 通过店铺ID查询店铺下的所有店内营销活动
     * 
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/list/prize/{campId}", method = { RequestMethod.GET })
    public MtServiceResult<List<CampPrizeModel>> queryGoodsByShopId(@PathVariable String campId) {
        MtServiceResult<List<CampPrizeModel>> result = null;

        MtOperateResult<List<CampPrizeModel>> componentResult = null;
        try {
            componentResult = pxCommonManageComponent.queryCampPrizeListByCampId(campId);
            result = new MtServiceResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询所有店内营销活动信息发生未知异常 " + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * 店内营销活动管理服务（增、删、改、单条查询）
     * 
     * @param campPrizeModel
     * @return
     */
    @RequestMapping(value = "/manage", method = { RequestMethod.POST })
    public MtServiceResult<CampPrizeModel> manageGoods(@RequestBody CampPrizeModel campPrizeModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始管理店内营销活动信息 campPrizeModel=" + campPrizeModel);
        }
        MtServiceResult<CampPrizeModel> result = null;
        try {
            MtOperateResult<CampPrizeModel> innerResult = campShopPrizeComponent.manageCampPrize(campPrizeModel);
            result = new MtServiceResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("管理店内营销活动信息发生异常" + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

}
