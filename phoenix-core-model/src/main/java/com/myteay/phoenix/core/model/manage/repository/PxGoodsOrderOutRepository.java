/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.repository;

import com.myteay.common.util.exception.MtException;
import com.myteay.phoenix.common.util.enums.PxOrderStatusEnum;
import com.myteay.phoenix.common.util.enums.PxPayTypeEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.PxGoodsOrderOutModel;

/**
 * 订单流水仓储
 * 
 * @author danlley
 * @version $Id: PxGoodsOrderOutRepository.java, v 0.1 Feb 26, 2019 6:52:41 PM danlley Exp $
 */
public interface PxGoodsOrderOutRepository {

    /**
     * 清理系统废单
     * 
     * @param pxGoodsOrderOutModel
     * @return
     * @throws MtException 
     */
    public String deleteExpiredOrder(PxGoodsOrderOutModel pxGoodsOrderOutModel) throws MtException;

    /**
     * 查询所有已经过期、废弃的订单信息为下一步进行清理做准备
     * 
     * @return
     */
    public PxGoodsOrderModel findAllShopExpiredOrder();

    /**
     * 保存订单流水
     * 
     * @param pxGoodsOrderModel
     * @return
     * @throws PxManageException 
     */
    public String saveGoodsOrderOut(PxGoodsOrderModel pxGoodsOrderModel) throws PxManageException;

    /**
     * 订单流水状态更新
     * 
     * @param orderNo
     * @param pxPayTypeEnum
     * @param pxOrderStatusEnum
     * @return
     * @throws PxManageException
     */
    public String modifyGoodsOrderOut(String orderNo, PxPayTypeEnum pxPayTypeEnum, PxOrderStatusEnum pxOrderStatusEnum) throws PxManageException;
}
