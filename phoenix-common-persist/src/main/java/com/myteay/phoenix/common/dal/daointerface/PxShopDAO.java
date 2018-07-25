/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.daointerface;

import java.util.List;

import com.myteay.phoenix.common.dal.dataobject.PxShopDO;

/**
 * 店铺管理DAO
 * 
 * @author min.weixm
 * @version $Id: PxShopDAO.java, v 0.1 Jul 24, 2018 9:20:23 AM min.weixm Exp $
 */
public interface PxShopDAO {

    /**
     * 插入店铺信息
     * 
     * @param pxShopDO   数据模型
     * @return
     */
    String insert(PxShopDO pxShopDO);

    /**
     * 查询所有店铺信息
     * 
     * @return
     */
    List<PxShopDO> findPxShopAll();

    /**
     * 通过店铺ID查询指定的店铺信息
     * 
     * @param shopId
     * @return
     */
    PxShopDO findPxShopById(String shopId);

    /**
     * 更新店铺配置信息
     * 
     * @param pxShopDO
     */
    void updatePxShop(PxShopDO pxShopDO);

    /**
     * 通过ID删除店铺信息
     * 
     * @param shopId
     */
    void deleteById(String shopId);
}
