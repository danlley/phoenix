/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.repository;

import java.util.List;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;

/**
 * 商品概要管理仓储
 * 
 * @author min.weixm
 * @version $Id: PxGoodsRepository.java, v 0.1 Jul 26, 2018 11:49:21 AM min.weixm Exp $
 */
public interface PxGoodsRepository {

    /**
     * 删除商品概要
     * 
     * @param pxGoodsModel
     * @throws PxManageException 
     */
    public void removeGoodsInfo(PxGoodsModel pxGoodsModel) throws PxManageException;

    /**
     * 修改商品概要信息
     * 
     * @param pxGoodsModel
     * @return
     * @throws PxManageException 
     */
    public PxGoodsModel modifyGoodsInfo(PxGoodsModel pxGoodsModel) throws PxManageException;

    /**
     * 保存商品概要模型
     * 
     * @param pxGoodsModel
     * @return
     * @throws PxManageException 
     */
    public PxGoodsModel saveGoodsInfo(PxGoodsModel pxGoodsModel) throws PxManageException;

    /**
     * 查询所有商品概要信息
     * 
     * @return
     * @throws PxManageException 
     */
    public List<PxGoodsModel> findAll() throws PxManageException;

    /**
     * 查询单个商品概要信息
     * 
     * @param goodsId
     * @return
     * @throws PxManageException
     */
    public PxGoodsModel findSingleGoods(String goodsId) throws PxManageException;

    /**
     * 通过店铺查询商品概要信息列表
     * 
     * @param shopId
     * @return
     * @throws PxManageException
     */
    public List<PxGoodsModel> findGoodsByShopId(String shopId) throws PxManageException;

    /**
     * 通过店铺查询已发布商品概要信息列表
     * 
     * @param shopId
     * @return
     * @throws PxManageException
     */
    public List<PxGoodsModel> findShopOnlineGoodsByShopId(String shopId) throws PxManageException;
}
