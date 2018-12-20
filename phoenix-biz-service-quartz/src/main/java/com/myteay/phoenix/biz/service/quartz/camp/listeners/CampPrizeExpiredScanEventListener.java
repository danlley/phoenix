/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.quartz.camp.listeners;

import java.util.Date;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.MtEvent;
import com.myteay.common.util.tools.DateUtil;
import com.myteay.phoenix.common.util.camp.enums.CampPrizeStatusEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.camp.CampPrizeModel;
import com.myteay.phoenix.core.model.camp.repository.CampShopPrizeRepository;

/**
 * 定时处理已经过期的营销活动
 * 
 * @author danlley
 * @version $Id: CampPrizeExpiredScanEventListener.java, v 0.1 Dec 20, 2018 6:14:26 PM danlley Exp $
 */
public class CampPrizeExpiredScanEventListener extends EventListener<String> {

    /** 店内营销活动奖池仓储 */
    private CampShopPrizeRepository campShopPrizeRepository;

    /** 
     * @see com.myteay.common.async.event.EventListener#handleEvent(com.myteay.common.async.event.MtEvent)
     */
    @Override
    public String handleEvent(MtEvent<?> event) {
        if (logger.isInfoEnabled()) {
            logger.info("开始检查当前运行中已经过期的活动奖品，对已过期的奖品执行下架动作" + event);
        }

        List<CampPrizeModel> list = findCampPrizeList();
        if (CollectionUtils.isEmpty(list)) {
            if (logger.isInfoEnabled()) {
                logger.info("当前运行中活动无已经过期的活动奖品，无需处理" + event);
            }
            return null;
        }

        for (CampPrizeModel campPrizeModel : list) {
            if (campPrizeModel.getPrizeStatus() == CampPrizeStatusEnum.CAMP_PRIZE_ONLINE) {
                campPrizeModel = shutdownCampPrize(campPrizeModel);
                if (logger.isInfoEnabled()) {
                    logger.info("定时任务对营销活动的活动奖品执行过期处理结果：" + campPrizeModel);
                }
            }
        }

        return null;
    }

    /**
     * 执行奖品过期动作
     * 
     * @param campPrizeModel
     * @return
     */
    private CampPrizeModel shutdownCampPrize(CampPrizeModel campPrizeModel) {

        if (logger.isInfoEnabled()) {
            logger.info("奖品过期处理 campPrizeModel=" + campPrizeModel);
        }

        Date expireTime = campPrizeModel.getPrizeExpired();
        if (!DateUtil.isBeforeNow(expireTime)) {
            if (logger.isInfoEnabled()) {
                logger.info("当前奖品未到过期时间，无需进行过期处理 campPrizeModel=" + campPrizeModel);
            }
            return campPrizeModel;
        }

        campPrizeModel.setPrizeStatus(CampPrizeStatusEnum.CAMP_PRIZE_EXPIRED);
        try {
            campPrizeModel = campShopPrizeRepository.modifyCampPrizeInfo(campPrizeModel);
        } catch (PxManageException e) {
            logger.warn("定时任务对营销活动的奖品执行过期处理出错 " + e.getMessage(), e);
        } catch (Throwable e) {
            logger.warn("定时任务对营销活动的奖品执行过期处理发生未知异常 " + e.getMessage(), e);
        }

        return campPrizeModel;
    }

    /**
     * 查询所有的奖品列表
     * 
     * @return
     */
    private List<CampPrizeModel> findCampPrizeList() {

        List<CampPrizeModel> list = null;
        try {
            list = campShopPrizeRepository.findAll();
        } catch (PxManageException e) {
            logger.warn("定时任务对营销活动的奖品执行过期处理出错 " + e.getMessage(), e);
        } catch (Throwable e) {
            logger.warn("定时任务对营销活动的奖品执行过期处理发生未知异常 " + e.getMessage(), e);
        }

        return list;
    }

    /**
     * Setter method for property <tt>campShopPrizeRepository</tt>.
     * 
     * @param campShopPrizeRepository value to be assigned to property campShopPrizeRepository
     */
    public void setCampShopPrizeRepository(CampShopPrizeRepository campShopPrizeRepository) {
        this.campShopPrizeRepository = campShopPrizeRepository;
    }

}
