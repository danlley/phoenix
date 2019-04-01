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
import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.common.util.tools.DateUtil;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.camp.enums.CampStatusEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.camp.CampBaseModel;
import com.myteay.phoenix.core.model.camp.repository.CampShopBaseRepository;

/**
 * 定时处理已经过期的营销活动
 * 
 * @author danlley
 * @version $Id: CampExpiredScanEventListener.java, v 0.1 Dec 20, 2018 6:14:26 PM danlley Exp $
 */
public class CampExpiredScanEventListener extends EventListener<String> {

    /** 日志 */
    private static final Logger    logger = LoggerFactory.getLogger(LoggerNames.PX_TASK);

    /** 针对单个店铺店内消费到场营销活动操作仓储 */
    private CampShopBaseRepository campShopBaseRepository;

    /** 
     * @see com.myteay.common.async.event.EventListener#handleEvent(com.myteay.common.async.event.MtEvent)
     */
    @Override
    public String handleEvent(MtEvent<?> event) {

        if (logger.isInfoEnabled()) {
            logger.info("开始检查当前运行中已经过期的活动，对已过期的活动执行关停动作" + event);
        }

        List<CampBaseModel> list = findAllCampBaseOnline();
        if (CollectionUtils.isEmpty(list)) {
            if (logger.isInfoEnabled()) {
                logger.info("未找到待处理的营销活动 list is null");
            }
            return null;
        }

        for (CampBaseModel campBaseModel : list) {
            campBaseModel = shutdownCampBase(campBaseModel);
            if (logger.isInfoEnabled()) {
                logger.info("定时任务对营销活动执行过期处理结果：" + campBaseModel);
            }
        }

        return null;
    }

    /**
     * 关停已过期的营销活动
     * 
     * @param campBaseModel
     * @return
     */
    private CampBaseModel shutdownCampBase(CampBaseModel campBaseModel) {

        if (logger.isInfoEnabled()) {
            logger.info("当前活动过期处理 campBaseModel=" + campBaseModel);
        }

        Date expireTime = campBaseModel.getCampEnd();
        if (!DateUtil.isBeforeNow(expireTime)) {
            if (logger.isInfoEnabled()) {
                logger.info("当前活动未到过期时间，无需进行过期处理 campBaseModel=" + campBaseModel);
            }
            return campBaseModel;
        }

        campBaseModel.setCampStatus(CampStatusEnum.CAMP_EXPIRED);
        try {
            campBaseModel = campShopBaseRepository.modifyGoodsInfo(campBaseModel);
        } catch (PxManageException e) {
            logger.warn("定时任务对营销活动执行过期处理出错 " + campBaseModel, e);
        } catch (Throwable e) {
            logger.warn("定时任务对营销活动执行过期处理发生未知异常 " + campBaseModel, e);
        }
        return campBaseModel;
    }

    /**
     * 查找所有待处理的营销活动
     * 
     * @return
     */
    private List<CampBaseModel> findAllCampBaseOnline() {
        List<CampBaseModel> list = null;
        try {
            list = campShopBaseRepository.findCampBaseOnlineAll();
        } catch (PxManageException e) {
            logger.warn("定时任务查询所有营销活动出错 " + e.getMessage(), e);
        } catch (Throwable e) {
            logger.warn("定时任务查询所有营销活动发生未知异常 " + e.getMessage(), e);
        }

        return list;
    }

    /**
     * Setter method for property <tt>campShopBaseRepository</tt>.
     * 
     * @param campShopBaseRepository value to be assigned to property campShopBaseRepository
     */
    public void setCampShopBaseRepository(CampShopBaseRepository campShopBaseRepository) {
        this.campShopBaseRepository = campShopBaseRepository;
    }

}
