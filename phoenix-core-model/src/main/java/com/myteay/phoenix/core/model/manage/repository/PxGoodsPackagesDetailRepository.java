/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.repository;

import java.util.List;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel;

/**
 * 套餐包管理仓储
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesDetailRepository.java, v 0.1 Jul 27, 2018 5:05:36 PM min.weixm Exp $
 */
public interface PxGoodsPackagesDetailRepository {

    /**
     * 删除套餐包
     * 
     * @param packagesDetailModel
     * @throws PxManageException 
     */
    public void removeGoodsPackagesDetailInfo(PxGoodsPackagesDetailModel packagesDetailModel) throws PxManageException;

    /**
     * 修改套餐包信息
     * 
     * @param packagesDetailModel
     * @return
     * @throws PxManageException 
     */
    public PxGoodsPackagesDetailModel modifyGoodsPackagesDetailInfo(PxGoodsPackagesDetailModel packagesDetailModel) throws PxManageException;

    /**
     * 保存套餐包模型
     * 
     * @param packagesDetailModel
     * @return
     * @throws PxManageException 
     */
    public PxGoodsPackagesDetailModel saveGoodsPackagesDetailInfo(PxGoodsPackagesDetailModel packagesDetailModel) throws PxManageException;

    /**
     * 查询所有套餐包信息
     * 
     * @return
     * @throws PxManageException 
     */
    public List<PxGoodsPackagesDetailModel> findAll() throws PxManageException;

    /**
     * 查询单个套餐包信息
     * 
     * @param goodsId
     * @return
     * @throws PxManageException
     */
    public PxGoodsPackagesDetailModel findSingleGoodsPackagesDetail(String packagesDetailId) throws PxManageException;

    /**
     * 通过店铺查询套餐包信息列表
     * 
     * @param goodsId
     * @return
     * @throws PxManageException
     */
    public List<PxGoodsPackagesDetailModel> findGoodsPackagesDetailByGoodsId(String goodsId) throws PxManageException;
}
