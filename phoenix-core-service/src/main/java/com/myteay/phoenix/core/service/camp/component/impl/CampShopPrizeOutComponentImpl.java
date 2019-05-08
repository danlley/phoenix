/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.component.impl;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampPrizeModel;
import com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel;
import com.myteay.phoenix.core.model.camp.repository.CampShopPrizeOutHistoryRepository;
import com.myteay.phoenix.core.model.camp.repository.CampShopPrizeOutRepository;
import com.myteay.phoenix.core.service.camp.component.CampShopCacheComponnet;
import com.myteay.phoenix.core.service.camp.component.CampShopPrizeOutComponent;

/**
 * 中奖流水管理组件
 * 
 * @author danlley
 * @version $Id: CampShopPrizeOutComponentImpl.java, v 0.1 Mar 29, 2019 7:20:32 PM danlley Exp $
 */
public class CampShopPrizeOutComponentImpl implements CampShopPrizeOutComponent {

    /** 日志 */
    public static final Logger                logger = Logger.getLogger(CampShopPrizeOutComponentImpl.class);

    /** 抽奖流水操作仓储 */
    private CampShopPrizeOutRepository        campShopPrizeOutRepository;

    /** 抽奖流水操作仓储 */
    private CampShopPrizeOutHistoryRepository campShopPrizeOutHistoryRepository;

    /** 到店消费营销活动基础数据缓存 */
    private CampShopCacheComponnet            campShopCacheComponnet;

    /** 事务模板 */
    private TransactionTemplate               pxTransactionTemplate;

    /** 
     * @see com.myteay.phoenix.core.service.camp.component.CampShopPrizeOutComponent#moveCampShopPrizeOut2History(java.lang.String)
     */
    @Override
    public MtOperateResult<String> moveCampShopPrizeOut2History(String campPrizeOutId) {

        if (logger.isInfoEnabled()) {
            logger.info("开始迁移指定奖品 campPrizeOutId = " + campPrizeOutId);
        }

        CampShopPrizeOutModel campShopPrizeOutModel = queryCampShopPrizeOutById(campPrizeOutId).getResult();

        if (logger.isInfoEnabled()) {
            logger.info("迁移指定奖品 campShopPrizeOutModel = " + campShopPrizeOutModel);
        }

        // 事务执行
        String result = this.pxTransactionTemplate.execute(new TransactionCallback<String>() {

            /** 
             * @see org.springframework.transaction.support.TransactionCallback#doInTransaction(org.springframework.transaction.TransactionStatus)
             */
            @Override
            public String doInTransaction(TransactionStatus ts) {
                try {

                    //迁移奖品至历史表
                    campShopPrizeOutHistoryRepository.saveCampShopPrizeOutHistory(campShopPrizeOutModel);

                    // 删除流水表中的奖品信息
                    campShopPrizeOutRepository.removeCampShopPrizeOutById(campPrizeOutId, campShopPrizeOutModel.getPrizeOutStatus());
                } catch (PxManageException e) {
                    logger.warn("中奖流水迁移过程中发生业务处理异常 campPrizeOutId=" + campPrizeOutId, e);

                    // 回滚事务
                    ts.setRollbackOnly();
                }
                return campPrizeOutId;
            }
        });

        return new MtOperateResult<String>(result);
    }

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

        if (campShopPrizeOutModel != null) {
            CampPrizeModel campPrizeModel = campShopCacheComponnet.queryCampPrizeModelFromCache(campShopPrizeOutModel.getCampId(),
                campShopPrizeOutModel.getPrizeId());
            campShopPrizeOutModel.setCampPrizeModel(campPrizeModel);
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

    /**
     * Setter method for property <tt>campShopCacheComponnet</tt>.
     * 
     * @param campShopCacheComponnet value to be assigned to property campShopCacheComponnet
     */
    public void setCampShopCacheComponnet(CampShopCacheComponnet campShopCacheComponnet) {
        this.campShopCacheComponnet = campShopCacheComponnet;
    }

    /**
     * Setter method for property <tt>campShopPrizeOutHistoryRepository</tt>.
     * 
     * @param campShopPrizeOutHistoryRepository value to be assigned to property campShopPrizeOutHistoryRepository
     */
    public void setCampShopPrizeOutHistoryRepository(CampShopPrizeOutHistoryRepository campShopPrizeOutHistoryRepository) {
        this.campShopPrizeOutHistoryRepository = campShopPrizeOutHistoryRepository;
    }

    /**
     * Setter method for property <tt>pxTransactionTemplate</tt>.
     * 
     * @param pxTransactionTemplate value to be assigned to property pxTransactionTemplate
     */
    public void setPxTransactionTemplate(TransactionTemplate pxTransactionTemplate) {
        this.pxTransactionTemplate = pxTransactionTemplate;
    }

}
