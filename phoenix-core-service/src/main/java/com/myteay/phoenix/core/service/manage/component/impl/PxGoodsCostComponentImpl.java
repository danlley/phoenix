/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component.impl;

import java.util.Date;
import java.util.List;

import com.myteay.common.util.lang.Money;
import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.common.util.tools.DateUtil;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsCostModel;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsCostRepository;
import com.myteay.phoenix.core.service.manage.component.PxGoodsCostComponent;

/**
 * 店铺商品经营成本统计管理组件
 * 
 * @author danlley
 * @version $Id: PxGoodsCostComponentImpl.java, v 0.1 May 8, 2019 12:33:51 AM danlley Exp $
 */
public class PxGoodsCostComponentImpl implements PxGoodsCostComponent {

    /** 日志 */
    private static final Logger   logger = LoggerFactory.getLogger(LoggerNames.PX_CASHIER_DEFAULT);

    /** 商品成本记录管理仓储 */
    private PxGoodsCostRepository pxGoodsCostRepository;

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxGoodsCostComponent#manageGoodsCostInfo(com.myteay.phoenix.core.model.manage.PxGoodsCostModel)
     */
    @Override
    public MtOperateResult<PxGoodsCostModel> manageGoodsCostInfo(PxGoodsCostModel pxGoodsCostModel) throws PxManageException {
        PxGoodsCostModel pxGoodsCostModelFresh = pxGoodsCostRepository.findSingleGoodsCost(pxGoodsCostModel.getGoodsId(),
            DateUtil.format(new Date(), DateUtil.shortFormat));

        // 如果数据库中不存在预期记录，则增加一条新记录
        if (pxGoodsCostModelFresh == null) {
            pxGoodsCostModelFresh = pxGoodsCostRepository.saveGoodsCostInfo(pxGoodsCostModel);
            return new MtOperateResult<PxGoodsCostModel>(pxGoodsCostModelFresh);
        }

        Money originalCost = pxGoodsCostModelFresh.getActrualCost();
        Money originalSellerPrice = pxGoodsCostModelFresh.getActrualSellerPrice();

        originalCost.add(pxGoodsCostModel.getActrualCost());
        originalSellerPrice.add(pxGoodsCostModel.getActrualSellerPrice());

        pxGoodsCostRepository.modifyGoodsCostInfo(pxGoodsCostModelFresh);

        return new MtOperateResult<>(pxGoodsCostModelFresh);
    }

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxGoodsCostComponent#findPxGoodsCostByShopId(java.lang.String, java.lang.String)
     */
    @Override
    public MtOperateResult<List<PxGoodsCostModel>> findPxGoodsCostByShopId(String shopId, String reportDate) {
        return new MtOperateResult<List<PxGoodsCostModel>>(pxGoodsCostRepository.findPxGoodsCostByShopId(shopId, reportDate));
    }

    /**
     * Setter method for property <tt>pxGoodsCostRepository</tt>.
     * 
     * @param pxGoodsCostRepository value to be assigned to property pxGoodsCostRepository
     */
    public void setPxGoodsCostRepository(PxGoodsCostRepository pxGoodsCostRepository) {
        this.pxGoodsCostRepository = pxGoodsCostRepository;
    }

}
