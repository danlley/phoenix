/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.repository;

import java.util.List;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxSubPackagesModel;

/**
 * 子套餐管理仓储
 * 
 * @author min.weixm
 * @version $Id: PxSubPackagesRepository.java, v 0.1 Jul 28, 2018 10:45:31 AM min.weixm Exp $
 */
public interface PxSubPackagesRepository {

    /**
     * 删除子套餐
     * 
     * @param pxSubPackagesModel
     * @throws PxManageException 
     */
    public void removeSubPackagesInfo(PxSubPackagesModel pxSubPackagesModel) throws PxManageException;

    /**
     * 修改子套餐信息
     * 
     * @param pxSubPackagesModel
     * @return
     * @throws PxManageException 
     */
    public PxSubPackagesModel modifySubPackagesInfo(PxSubPackagesModel pxSubPackagesModel) throws PxManageException;

    /**
     * 保存子套餐模型
     * 
     * @param pxSubPackagesModel
     * @return
     * @throws PxManageException 
     */
    public PxSubPackagesModel saveSubPackagesInfo(PxSubPackagesModel pxSubPackagesModel) throws PxManageException;

    /**
     * 查询所有子套餐信息
     * 
     * @return
     * @throws PxManageException 
     */
    public List<PxSubPackagesModel> findAll() throws PxManageException;

    /**
     * 查询单个子套餐信息
     * 
     * @param subPackagesId
     * @return
     * @throws PxManageException
     */
    public PxSubPackagesModel findSingleSubPackages(String subPackagesId) throws PxManageException;

    /**
     * 通过店铺查询子套餐信息列表
     * 
     * @param packagesDetailId
     * @return
     * @throws PxManageException
     */
    public List<PxSubPackagesModel> findSubPackagesByGoodsId(String packagesDetailId) throws PxManageException;
}
