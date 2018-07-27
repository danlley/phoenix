/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.daointerface;

import java.util.List;

import com.myteay.phoenix.common.dal.dataobject.PxGoodsDO;

/**
 * 商品概要操作DAO
 * 
 * @author min.weixm
 * @version $Id: PxGoodsDAO.java, v 0.1 Jul 26, 2018 10:33:13 AM min.weixm Exp $
 */
public interface PxGoodsDAO {
    /**
     * 插入商品概要信息
     * 
     * @param pxGoodsDO   数据模型
     * @return
     */
    String insert(PxGoodsDO pxGoodsDO);

    /**
     * 查询所有商品概要信息
     * 
     * @return
     */
    List<PxGoodsDO> findPxGoodsAll();

    /**
     * 通过商品概要ID查询指定的商品概要信息
     * 
     * @param goodsId
     * @return
     */
    PxGoodsDO findPxGoodsById(String goodsId);

    /**
     * 通过店铺ID查询指定的商品概要信息列表
     * 
     * @param shopId
     * @return
     */
    List<PxGoodsDO> findPxGoodsByShopId(String shopId);

    /**
     * 更新商品概要配置信息
     * 
     * @param pxGoodsDO
     */
    void updatePxGoods(PxGoodsDO pxGoodsDO);

    /**
     * 通过ID删除商品概要信息
     * 
     * @param goodsId
     */
    void deleteById(String goodsId);
}
