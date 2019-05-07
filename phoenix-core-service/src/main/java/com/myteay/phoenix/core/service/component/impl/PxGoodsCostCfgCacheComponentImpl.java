/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.component.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;

import com.myteay.common.util.lang.Money;
import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxGoodsCostCfgAdvModel;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsCostCfgRepository;
import com.myteay.phoenix.core.service.component.PxGoodsCostCfgCacheComponent;

/**
 * 商品成本缓存
 * 
 * @author danlley
 * @version $Id: PxGoodsCostCfgCacheComponentImpl.java, v 0.1 May 7, 2019 8:52:11 PM danlley Exp $
 */
public class PxGoodsCostCfgCacheComponentImpl implements PxGoodsCostCfgCacheComponent, InitializingBean {

    /** 日志 */
    private static final Logger             logger                   = LoggerFactory.getLogger(LoggerNames.PX_CASHIER_DEFAULT);

    /** 商品成本缓存 */
    private static final Map<String, Money> GOODS_COST_CFG_CACHE_MAP = Collections.synchronizedMap(new HashMap<String, Money>());

    /** 商品成本管理仓储 */
    private PxGoodsCostCfgRepository        pxGoodsCostCfgRepository;

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        refreshGoodsCostCfgCache();
    }

    /** 
     * @see com.myteay.phoenix.core.service.component.PxGoodsCostCfgCacheComponent#queryGoodsCostCfgByGoodsId(java.lang.String)
     */
    @Override
    public Money queryGoodsCostCfgByGoodsId(String goodsId) {
        return (GOODS_COST_CFG_CACHE_MAP.get(goodsId) == null ? new Money() : GOODS_COST_CFG_CACHE_MAP.get(goodsId));
    }

    /** 
     * @see com.myteay.phoenix.core.service.component.PxGoodsCostCfgCacheComponent#refreshGoodsCostCfgCache()
     */
    @Override
    public void refreshGoodsCostCfgCache() {
        List<PxGoodsCostCfgAdvModel> list = null;
        try {
            list = pxGoodsCostCfgRepository.findAllGoodsCostCfg();
        } catch (PxManageException e) {
            logger.warn("初始化商品成本缓存配置过程中，查询配置信息出错 " + e.getMessage(), e);
        }

        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        Map<String, Money> tmpCacheMap = new HashMap<>();
        for (PxGoodsCostCfgAdvModel pxGoodsCostCfgAdvModel : list) {
            if (pxGoodsCostCfgAdvModel == null) {
                continue;
            }

            tmpCacheMap.put(pxGoodsCostCfgAdvModel.getGoodsId(), new Money(pxGoodsCostCfgAdvModel.getGoodsCost()));
        }

        synchronized (GOODS_COST_CFG_CACHE_MAP) {
            GOODS_COST_CFG_CACHE_MAP.putAll(tmpCacheMap);
        }

        if (logger.isInfoEnabled()) {
            logger.info("商品成本缓存初始化结束 GOODS_COST_CFG_CACHE_MAP = " + GOODS_COST_CFG_CACHE_MAP);
        }
    }

    /**
     * Setter method for property <tt>pxGoodsCostCfgRepository</tt>.
     * 
     * @param pxGoodsCostCfgRepository value to be assigned to property pxGoodsCostCfgRepository
     */
    public void setPxGoodsCostCfgRepository(PxGoodsCostCfgRepository pxGoodsCostCfgRepository) {
        this.pxGoodsCostCfgRepository = pxGoodsCostCfgRepository;
    }

}
