/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.cashier.component;

import com.myteay.phoenix.common.util.enums.PxOrderStatusEnum;
import com.myteay.phoenix.common.util.enums.PxPayTypeEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.camp.CampCashierModel;
import com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel;

/**
 * 订单流水操作组件
 * 
 * @author danlley
 * @version $Id: PxGoodsOrderOutCompoonent.java, v 0.1 Feb 27, 2019 1:15:31 PM danlley Exp $
 */
public interface PxGoodsOrderOutCompoonent {

    /**
     * 订单废单清理组件
     * 
     * @param pxGoodsOrderModel
     * @return
     */
    public MtOperateResult<String> deleteExpiredOrder(PxGoodsOrderModel pxGoodsOrderModel);

    /**
     * 执行流水落地
     * 
     * @param pxGoodsOrderModel
     * @return
     */
    public MtOperateResult<CampCashierModel> execute(PxGoodsOrderModel pxGoodsOrderModel);

    /**
     * 查询所有店内消费的过期订单
     * 
     * @return
     */
    public MtOperateResult<PxGoodsOrderModel> findAllShopExpiredOrder();

    /**
     * 修改订单状态
     * 
     * @param orderNo
     * @param pxPayTypeEnum
     * @param pxOrderStatusEnum
     * @param campShopPrizeOutModel
     * @return
     */
    public MtOperateResult<String> modifyGoodsOrderOut(String orderNo, PxPayTypeEnum pxPayTypeEnum, PxOrderStatusEnum pxOrderStatusEnum,
                                                       CampShopPrizeOutModel campShopPrizeOutModel);
}
