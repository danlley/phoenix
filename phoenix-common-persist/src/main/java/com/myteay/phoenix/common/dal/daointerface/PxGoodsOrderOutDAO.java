/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.daointerface;

import java.util.List;

import com.myteay.phoenix.common.dal.dataobject.PxGoodsOrderOutDO;

/**
 * 订单流水操作DAO
 * 
 * @author danlley
 * @version $Id: PxGoodsOrderOutDAO.java, v 0.1 Feb 26, 2019 4:45:01 PM danlley Exp $
 */
public interface PxGoodsOrderOutDAO {

    /**
     * 查询所有已经过期的店铺消费废单，为下一步进行清理做准备
     * 
     * @return
     */
    List<PxGoodsOrderOutDO> selectExpiredGoodsOrderOutDOs();

    /**
     * 查询当日零点之前已经完成支付的订单，每次100条
     * 
     * @return
     */
    List<PxGoodsOrderOutDO> selectPayedGoodsOrderOutDOs();

    /**
     * 插入订单流水信息
     * 
     * @param pxGoodsOrderOutDO   数据模型
     * @return
     */
    String insert(PxGoodsOrderOutDO pxGoodsOrderOutDO);

    /**
     * 更新订单流水配置信息
     * 
     * @param pxGoodsOrderOutDO
     */
    void updatePxGoods(PxGoodsOrderOutDO pxGoodsOrderOutDO);

    /**
     * 通过ID 删除订单流水信息
     * 
     * @param id
     */
    void deleteByIdWithStatus(String id);

    /**
     * 通过ID删除订单流水
     * 
     * @param id
     */
    public void deletePayedOrderOutById(String id);

}
