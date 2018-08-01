/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.repository;

import java.util.List;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesImageModel;

/**
 * 商品详情图片管理仓储
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesImageRepository.java, v 0.1 Aug 1, 2018 12:21:47 PM min.weixm Exp $
 */
public interface PxGoodsPackagesImageRepository {

    /**
     * 删除商品详情图片
     * 
     * @param pxGoodsPackagesImageModel
     * @throws PxManageException 
     */
    public void removeGoodsPackagesImageInfo(PxGoodsPackagesImageModel pxGoodsPackagesImageModel) throws PxManageException;

    /**
     * 保存商品详情图片模型
     * 
     * @param pxGoodsPackagesImageModel
     * @return
     * @throws PxManageException 
     */
    public PxGoodsPackagesImageModel saveGoodsPackagesImageInfo(PxGoodsPackagesImageModel pxGoodsPackagesImageModel) throws PxManageException;

    /**
     * 查询单个商品详情图片信息
     * 
     * @param imageId
     * @return
     * @throws PxManageException
     */
    public PxGoodsPackagesImageModel findSingleGoodsPackagesImage(String imageId) throws PxManageException;

    /**
     * 通过店铺查询商品详情图片信息列表
     * 
     * @param goodsId
     * @return
     * @throws PxManageException
     */
    public List<PxGoodsPackagesImageModel> findGoodsPackagesImageByGoodsId(String goodsId) throws PxManageException;
}
