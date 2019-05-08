/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.quartz.camp.listeners;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.MtEvent;
import com.myteay.phoenix.common.util.camp.enums.CampPrizeOutStatusEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel;
import com.myteay.phoenix.core.model.camp.repository.CampShopPrizeOutRepository;

/**
 * 对已发放且过期的优惠券进行过期处理
 * 
 * @author danlley
 * @version $Id: CampPrizeOutExpiredEventListener.java, v 0.1 May 8, 2019 10:53:59 PM danlley Exp $
 */
public class CampPrizeOutExpiredEventListener extends EventListener<Object> {

    /** 抽奖流水操作仓储 */
    private CampShopPrizeOutRepository campShopPrizeOutRepository;

    /** 
     * @see com.myteay.common.async.event.EventListener#handleEvent(com.myteay.common.async.event.MtEvent)
     */
    @Override
    public String handleEvent(MtEvent<?> event) {
        List<CampShopPrizeOutModel> campShopPrizeOutModels = null;
        try {
            campShopPrizeOutModels = campShopPrizeOutRepository.selectCampShopPrizeOutListByStatus(CampPrizeOutStatusEnum.CAMP_PRIZE_OUT_GIVEN);
        } catch (PxManageException e) {
            logger.warn("查询已发放且已过期的奖品流水信息模型失败 " + e.getMessage(), e);
        }

        if (CollectionUtils.isEmpty(campShopPrizeOutModels)) {
            if (logger.isInfoEnabled()) {
                logger.info("当前未找到已发放的过期奖品，本次定时扫描结束!");
            }
            return null;
        }

        for (CampShopPrizeOutModel campShopPrizeOutModel : campShopPrizeOutModels) {
            campShopPrizeOutModel.setPrizeOutStatus(CampPrizeOutStatusEnum.CAMP_PRIZE_OUT_EXPIRED);
            try {
                campShopPrizeOutRepository.modifyCampShopPrizeOutStatusById(campShopPrizeOutModel);
            } catch (PxManageException e) {
                logger.warn("作废优惠券过程发生异常 campShopPrizeOutModel=" + campShopPrizeOutModel, e);
            }
        }

        return null;
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
