/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.quartz.listeners;

import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.MtEvent;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.PxGoodsOrderOutModel;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsOrderOutHistoryRepository;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsOrderOutRepository;

/**
 * 扫描已支付待迁移订单
 * 
 * @author danlley
 * @version $Id: PxPayedOrderNeedMvListener.java, v 0.1 May 10, 2019 4:39:18 AM danlley Exp $
 */
public class PxPayedOrderNeedMvListener extends EventListener<String> {

    /** 订单流水仓储 */
    private PxGoodsOrderOutRepository        pxGoodsOrderOutRepository;

    /** 订单流水历史仓储 */
    private PxGoodsOrderOutHistoryRepository pxGoodsOrderOutHistoryRepository;

    /** 事务模板 */
    private TransactionTemplate              pxTransactionTemplate;

    /** 
     * @see com.myteay.common.async.event.EventListener#handleEvent(com.myteay.common.async.event.MtEvent)
     */
    @Override
    public String handleEvent(MtEvent<?> event) {
        List<PxGoodsOrderOutModel> pxGoodsOrderOutModels = pxGoodsOrderOutRepository.findAllPayedOrder();
        if (CollectionUtils.isEmpty(pxGoodsOrderOutModels)) {
            if (logger.isInfoEnabled()) {
                logger.info("未找到待迁移的已支付订单列表");
            }
            return null;
        }

        for (PxGoodsOrderOutModel pxGoodsOrderOutModel : pxGoodsOrderOutModels) {
            logger.warn("准备迁移已经完成支付的待迁移订单 pxGoodsOrderOutModel=" + pxGoodsOrderOutModel);
            moveCampOrderOut2History(pxGoodsOrderOutModel);
        }

        return null;
    }

    /**
     * 订单流水迁移历史表
     * 
     * @param pxGoodsOrderOutModel
     */
    private void moveCampOrderOut2History(PxGoodsOrderOutModel pxGoodsOrderOutModel) {

        if (logger.isInfoEnabled()) {
            logger.info("迁移指定奖品 pxGoodsOrderOutModel = " + pxGoodsOrderOutModel);
        }

        // 事务执行
        this.pxTransactionTemplate.execute(new TransactionCallback<String>() {

            /** 
             * @see org.springframework.transaction.support.TransactionCallback#doInTransaction(org.springframework.transaction.TransactionStatus)
             */
            @Override
            public String doInTransaction(TransactionStatus ts) {
                try {

                    //迁移奖品至历史表
                    pxGoodsOrderOutHistoryRepository.saveGoodsOrderOutHistory(pxGoodsOrderOutModel);

                    // 删除流水表中的奖品信息
                    pxGoodsOrderOutRepository.deletePayedOrderOutById(pxGoodsOrderOutModel.getId());
                } catch (PxManageException e) {
                    logger.warn("中奖流水迁移过程中发生业务处理异常 pxGoodsOrderOutModel=" + pxGoodsOrderOutModel, e);

                    // 回滚事务
                    ts.setRollbackOnly();
                }
                return null;
            }
        });

    }

    /**
     * Setter method for property <tt>pxGoodsOrderOutRepository</tt>.
     * 
     * @param pxGoodsOrderOutRepository value to be assigned to property pxGoodsOrderOutRepository
     */
    public void setPxGoodsOrderOutRepository(PxGoodsOrderOutRepository pxGoodsOrderOutRepository) {
        this.pxGoodsOrderOutRepository = pxGoodsOrderOutRepository;
    }

    /**
     * Setter method for property <tt>pxTransactionTemplate</tt>.
     * 
     * @param pxTransactionTemplate value to be assigned to property pxTransactionTemplate
     */
    public void setPxTransactionTemplate(TransactionTemplate pxTransactionTemplate) {
        this.pxTransactionTemplate = pxTransactionTemplate;
    }

    /**
     * Setter method for property <tt>pxGoodsOrderOutHistoryRepository</tt>.
     * 
     * @param pxGoodsOrderOutHistoryRepository value to be assigned to property pxGoodsOrderOutHistoryRepository
     */
    public void setPxGoodsOrderOutHistoryRepository(PxGoodsOrderOutHistoryRepository pxGoodsOrderOutHistoryRepository) {
        this.pxGoodsOrderOutHistoryRepository = pxGoodsOrderOutHistoryRepository;
    }

}
