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
import com.myteay.phoenix.core.service.camp.component.CampShopPrizeOutComponent;

/**
 * 对已消费的奖品进行迁移处理
 * 
 * @author danlley
 * @version $Id: CampPrizeOutConsumedMvListener.java, v 0.1 May 8, 2019 11:17:12 PM danlley Exp $
 */
public class CampPrizeOutConsumedMvListener extends EventListener<Object> {

    /** 中奖流水管理组件 */
    private CampShopPrizeOutComponent  campShopPrizeOutComponent;

    /** 抽奖流水操作仓储 */
    private CampShopPrizeOutRepository campShopPrizeOutRepository;

    /** 
     * @see com.myteay.common.async.event.EventListener#handleEvent(com.myteay.common.async.event.MtEvent)
     */
    @Override
    public Object handleEvent(MtEvent<?> event) {
        List<CampShopPrizeOutModel> campShopPrizeOutModels = null;
        try {
            campShopPrizeOutModels = campShopPrizeOutRepository.selectCampShopPrizeOutListByStatus(CampPrizeOutStatusEnum.CAMP_PRIZE_OUT_CONSUMED);
        } catch (PxManageException e) {
            logger.warn("查询已消费的奖品流水信息模型失败 " + e.getMessage(), e);
        }

        if (CollectionUtils.isEmpty(campShopPrizeOutModels)) {
            if (logger.isInfoEnabled()) {
                logger.info("当前未找到已消费的奖品，本次定时扫描结束!");
            }
            return null;
        }

        for (CampShopPrizeOutModel campShopPrizeOutModel : campShopPrizeOutModels) {
            campShopPrizeOutComponent.moveCampShopPrizeOut2History(campShopPrizeOutModel.getCampPrizeOutId());
        }

        return null;
    }

    /**
     * Setter method for property <tt>campShopPrizeOutComponent</tt>.
     * 
     * @param campShopPrizeOutComponent value to be assigned to property campShopPrizeOutComponent
     */
    public void setCampShopPrizeOutComponent(CampShopPrizeOutComponent campShopPrizeOutComponent) {
        this.campShopPrizeOutComponent = campShopPrizeOutComponent;
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
