/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.cashier.component.impl;

import org.apache.log4j.Logger;

import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.manage.PxShopModel;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsOrderOutRepository;
import com.myteay.phoenix.core.service.camp.component.CampShopCacheComponnet;
import com.myteay.phoenix.core.service.cashier.component.PxGoodsOrderOutCompoonent;

/**
 * 订单流水操作组件
 * 
 * @author danlley
 * @version $Id: PxGoodsOrderOutCompoonentImpl.java, v 0.1 Feb 27, 2019 2:01:18 PM danlley Exp $
 */
public class PxGoodsOrderOutCompoonentImpl implements PxGoodsOrderOutCompoonent {

    /** 日志 */
    public static final Logger        logger = Logger.getLogger(PxGoodsOrderOutCompoonentImpl.class);

    /** 订单流水仓储 */
    private PxGoodsOrderOutRepository pxGoodsOrderOutRepository;

    /** 订单流水仓储 */
    private CampShopCacheComponnet    campShopCacheComponnet;

    /** 
     * @see com.myteay.phoenix.core.service.cashier.component.PxGoodsOrderOutCompoonent#execute(com.myteay.phoenix.core.model.PxGoodsOrderModel)
     */
    @Override
    public MtOperateResult<String> execute(PxGoodsOrderModel pxGoodsOrderModel) {

        PxShopModel pxShopModel = campShopCacheComponnet.queryShopModelFromCache(pxGoodsOrderModel.getPxGoodsModelList().get(0).getShopId());
        pxGoodsOrderModel.setShopName(pxShopModel.getShopName());

        String orderNo = null;
        try {
            orderNo = pxGoodsOrderOutRepository.saveGoodsOrderOut(pxGoodsOrderModel);
        } catch (PxManageException e) {
            logger.warn("保存订单流水发生异常 pxGoodsOrderModel=" + pxGoodsOrderModel, e);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_ORDER_OUT_OP_ERR);
        }

        return new MtOperateResult<>(orderNo);
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
     * Setter method for property <tt>campShopCacheComponnet</tt>.
     * 
     * @param campShopCacheComponnet value to be assigned to property campShopCacheComponnet
     */
    public void setCampShopCacheComponnet(CampShopCacheComponnet campShopCacheComponnet) {
        this.campShopCacheComponnet = campShopCacheComponnet;
    }

}
