/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.daointerface;

import java.util.List;

import com.myteay.phoenix.common.dal.dataobject.PxGoodsCostDO;

/**
 * 商品成本信息记录操作DAO
 * 
 * @author danlley
 * @version $Id: PxGoodsCostDAO.java, v 0.1 May 7, 2019 11:52:52 PM danlley Exp $
 */
public interface PxGoodsCostDAO {

    /**
     * 插入商品成本信息
     * 
     * @param pxGoodsCostDO   数据模型
     * @return
     */
    String insert(PxGoodsCostDO pxGoodsCostDO);

    /**
     * 通过商品ID查询指定的商品成本数据模型
     * 
     * @param goodsId
     * @param reportDate
     * @return
     */
    PxGoodsCostDO findPxGoodsCostById(String goodsId, String reportDate);

    /**
     * 通过店铺ID查询特定日期商品成本数据模型列表
     * 
     * @param shopId
     * @param reportDate
     * @return
     */
    List<PxGoodsCostDO> findPxGoodsCostByShopId(String shopId, String reportDate);

    /**
     * 更新商品成本信息
     * 
     * @param pxGoodsCostDO
     */
    void updatePxGoodsCost(PxGoodsCostDO pxGoodsCostDO);
}
