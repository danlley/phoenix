/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.repository;

import java.util.List;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxShopModel;

/**
 * 店铺管理仓储
 * 
 * @author min.weixm
 * @version $Id: PxShopRepository.java, v 0.1 Jul 24, 2018 10:23:53 AM min.weixm Exp $
 */
public interface PxShopRepository {

    /**
     * 删除店铺
     * 
     * @param pxShopModel
     * @throws PxManageException 
     */
    public void removeShopInfo(PxShopModel pxShopModel) throws PxManageException;

    /**
     * 修改店铺信息
     * 
     * @param pxShopModel
     * @return
     * @throws PxManageException 
     */
    public PxShopModel modifyShopInfo(PxShopModel pxShopModel) throws PxManageException;

    /**
     * 保存店铺模型
     * 
     * @param pxShopModel
     * @return
     * @throws PxManageException 
     */
    public PxShopModel saveShopInfo(PxShopModel pxShopModel) throws PxManageException;

    /**
     * 查询所有店铺信息
     * 
     * @return
     * @throws PxManageException 
     */
    public List<PxShopModel> findAll() throws PxManageException;

    /**
     * 查询单个店铺信息
     * 
     * @param shopId
     * @return
     * @throws PxManageException
     */
    public PxShopModel findSingleShop(String shopId) throws PxManageException;
}
