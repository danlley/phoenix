/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.camp.tools;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.myteay.phoenix.common.dal.camp.dataobject.CampPrizeGoodsRefDO;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;

/**
 * 奖品关联商品校验工具类
 * 
 * @author danlley
 * @version $Id: CampPrizeRefGoodsValidateTool.java, v 0.1 Dec 20, 2018 1:26:11 AM danlley Exp $
 */
public class CampPrizeRefGoodsValidateTool {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(CampPrizeRefGoodsValidateTool.class);

    /**
     * 校验奖品关联商品数据模型
     * 
     * @param campPrizeGoodsRefDO
     * @throws PxManageException
     */
    public static void validateCampPrizeRefGoodsDO(CampPrizeGoodsRefDO campPrizeGoodsRefDO) throws PxManageException {
        if (campPrizeGoodsRefDO == null || StringUtils.isBlank(campPrizeGoodsRefDO.getGoodsId()) || StringUtils.isBlank(campPrizeGoodsRefDO.getPrizeId())) {

            logger.warn("奖品关联商品数据模型校验失败 campPrizeGoodsRefDO=" + campPrizeGoodsRefDO);

            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_PRIZE_REF_GOODS_MODEL_ERR);
        }
    }

}
