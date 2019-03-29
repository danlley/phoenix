/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.component.impl;

import org.apache.log4j.Logger;

import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel;
import com.myteay.phoenix.core.model.camp.repository.CampShopPrizeOutRepository;
import com.myteay.phoenix.core.service.camp.component.CampShopPrizeOutComponent;

/**
 * 中奖流水管理组件
 * 
 * @author danlley
 * @version $Id: CampShopPrizeOutComponentImpl.java, v 0.1 Mar 29, 2019 7:20:32 PM danlley Exp $
 */
public class CampShopPrizeOutComponentImpl implements CampShopPrizeOutComponent {

    /** 日志 */
    public static final Logger         logger = Logger.getLogger(CampShopPrizeOutComponentImpl.class);

    /** 抽奖流水操作仓储 */
    private CampShopPrizeOutRepository campShopPrizeOutRepository;

    /** 
     * @see com.myteay.phoenix.core.service.camp.component.CampShopPrizeOutComponent#queryCampShopPrizeOutById(java.lang.String)
     */
    @Override
    public MtOperateResult<CampShopPrizeOutModel> queryCampShopPrizeOutById(String campPrizeOutId) {

        if (logger.isInfoEnabled()) {
            logger.info("开始查询中奖信息 campPrizeOutId=" + campPrizeOutId);
        }

        CampShopPrizeOutModel campShopPrizeOutModel = null;
        try {
            campShopPrizeOutModel = campShopPrizeOutRepository.queryCampShopPrizeOutById(campPrizeOutId);
        } catch (PxManageException e) {
            logger.warn("通过campPrizeOutId查询中奖模型失败 " + e.getMessage(), e);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_OPERATE_FAILED);
        }
        return new MtOperateResult<>(campShopPrizeOutModel);
    }

    /**
     * Setter method for property <tt>campShopPrizeOutRepository</tt>.
     * 
     * @param campShopPrizeOutRepository value to be assigned to property campShopPrizeOutRepository
     */
    public void setCampShopPrizeOutRepository(CampShopPrizeOutRepository campShopPrizeOutRepository) {
        this.campShopPrizeOutRepository = campShopPrizeOutRepository;
    }

}
