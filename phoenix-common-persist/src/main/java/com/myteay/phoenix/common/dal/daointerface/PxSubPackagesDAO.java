/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.daointerface;

import java.util.List;

import com.myteay.phoenix.common.dal.dataobject.PxSubPackagesDO;

/**
 * 自套餐操作DAO
 * 
 * @author min.weixm
 * @version $Id: PxSubPackagesDAO.java, v 0.1 Jul 28, 2018 9:23:41 AM min.weixm Exp $
 */
public interface PxSubPackagesDAO {

    /**
     * 插入子套餐信息
     * 
     * @param pxSubPackagesDO   数据模型
     * @return
     */
    String insert(PxSubPackagesDO pxSubPackagesDO);

    /**
     * 查询所有子套餐信息
     * 
     * @return
     */
    List<PxSubPackagesDO> findPxSubPackagesAll();

    /**
     * 通过子套餐ID查询指定的子套餐信息
     * 
     * @param subPackagesId
     * @return
     */
    PxSubPackagesDO findPxSubPackagesById(String subPackagesId);

    /**
     * 通过商品ID查询指定的子套餐信息列表
     * 
     * @param goodsId
     * @return
     */
    List<PxSubPackagesDO> findPxSubPackagesByGoodsId(String packagesDetailId);

    /**
     * 更新子套餐配置信息
     * 
     * @param pxSubPackagesDO
     */
    void updatePxSubPackages(PxSubPackagesDO pxSubPackagesDO);

    /**
     * 通过ID删除子套餐信息
     * 
     * @param subPackagesId
     */
    void deleteById(String subPackagesId);
}
