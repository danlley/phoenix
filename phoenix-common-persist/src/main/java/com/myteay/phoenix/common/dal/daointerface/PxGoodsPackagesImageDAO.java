/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.daointerface;

import java.util.List;

import com.myteay.phoenix.common.dal.dataobject.PxGoodsPackagesImageDO;

/**
 * 商品详情图片管理
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesImageDAO.java, v 0.1 Aug 1, 2018 11:39:10 AM min.weixm Exp $
 */
public interface PxGoodsPackagesImageDAO {

    /**
     * 插入商品详情图片信息
     * 
     * @param pxGoodsPackageImageDO   数据模型
     * @return
     */
    String insert(PxGoodsPackagesImageDO pxGoodsPackageImageDO);

    /**
     * 通过商品详情图片ID查询指定的商品详情图片信息
     * 
     * @param imageId
     * @return
     */
    PxGoodsPackagesImageDO findPxGoodsPackageImageById(String imageId);

    /**
     * 通过商品ID查询指定的商品详情图片信息列表
     * 
     * @param goodsId
     * @return
     */
    List<PxGoodsPackagesImageDO> findPxGoodsPackageImageByGoodsId(String goodsId);

    /**
     * 通过ID删除商品详情图片信息
     * 
     * @param imageId
     */
    void deleteById(String imageId);
}
