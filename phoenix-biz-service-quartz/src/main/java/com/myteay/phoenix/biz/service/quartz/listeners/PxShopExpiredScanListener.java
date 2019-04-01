/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.quartz.listeners;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.myteay.common.async.event.EventListener;
import com.myteay.common.async.event.MtEvent;
import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.enums.PxOperationTypeEnum;
import com.myteay.phoenix.common.util.manage.enums.PxShopStatusEnum;
import com.myteay.phoenix.core.model.manage.PxShopModel;
import com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent;
import com.myteay.phoenix.core.service.manage.component.PxShopComponent;

/**
 * 店铺过期时间扫描监听器
 * 
 * @author danlley
 * @version $Id: PxShopExpiredScanListener.java, v 0.1 Aug 18, 2018 10:32:18 PM danlley Exp $
 */
public class PxShopExpiredScanListener extends EventListener<String> {

    /** 日志 */
    private static final Logger     logger = LoggerFactory.getLogger(LoggerNames.PX_TASK);

    /** 后台一般性简单业务管理组件 */
    private PxCommonManageComponent pxCommonManageComponent;

    /** 店铺管理组件 */
    private PxShopComponent         pxShopComponent;

    /** 
     * @see com.myteay.common.async.event.EventListener#handleEvent(com.myteay.common.async.event.MtEvent)
     */
    @Override
    public String handleEvent(MtEvent<?> event) {
        if (logger.isInfoEnabled()) {
            logger.info("[店铺过期时间扫描监听器]开始扫描已过期店铺 event=" + event);
        }

        List<PxShopModel> list = pxCommonManageComponent.queryShopExpiredAll();
        if (CollectionUtils.isEmpty(list)) {
            logger.warn("当前没有过期店铺，无需变更店铺状态");
            return null;
        }

        for (PxShopModel pxShopModel : list) {
            modifyShopStatus(pxShopModel);
        }

        return null;
    }

    /**
     * 执行下架店铺动作
     * 
     * @param pxShopModel
     */
    private void modifyShopStatus(PxShopModel pxShopModel) {
        if (pxShopModel == null || StringUtils.isBlank(pxShopModel.getShopId())) {
            logger.warn("当前待下架店铺模型不可用 pxShopModel=" + pxShopModel);
            return;
        }

        if (logger.isInfoEnabled()) {
            logger.info("准备下架店铺 pxShopModel=" + pxShopModel);
        }

        pxShopModel.setOperationType(PxOperationTypeEnum.PX_MODIFY);
        pxShopModel.setShopStatus(PxShopStatusEnum.PX_SHOP_EXPIRED);

        pxShopComponent.manageShop(pxShopModel);
    }

    /**
     * Setter method for property <tt>pxCommonManageComponent</tt>.
     * 
     * @param pxCommonManageComponent value to be assigned to property pxCommonManageComponent
     */
    public void setPxCommonManageComponent(PxCommonManageComponent pxCommonManageComponent) {
        this.pxCommonManageComponent = pxCommonManageComponent;
    }

    /**
     * Setter method for property <tt>pxShopComponent</tt>.
     * 
     * @param pxShopComponent value to be assigned to property pxShopComponent
     */
    public void setPxShopComponent(PxShopComponent pxShopComponent) {
        this.pxShopComponent = pxShopComponent;
    }

}
