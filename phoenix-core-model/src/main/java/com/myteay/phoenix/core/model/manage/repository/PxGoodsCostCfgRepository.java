/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.repository;

import java.util.List;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxGoodsCostCfgAdvModel;

/**
 * 商品成本管理仓储
 * 
 * @author danlley
 * @version $Id: PxGoodsCostCfgRepository.java, v 0.1 May 7, 2019 12:41:36 PM danlley Exp $
 */
public interface PxGoodsCostCfgRepository {

    /**
     * 修改商品成本信息
     * 
     * @param pxGoodsCostCfgAdvModel
     * @return
     * @throws PxManageException 
     */
    public PxGoodsCostCfgAdvModel modifyGoodsCostCfgInfo(PxGoodsCostCfgAdvModel pxGoodsCostCfgAdvModel) throws PxManageException;

    /**
     * 保存商品成本模型
     * 
     * @param pxGoodsCostCfgAdvModel
     * @return
     * @throws PxManageException 
     */
    public PxGoodsCostCfgAdvModel saveGoodsCostCfgInfo(PxGoodsCostCfgAdvModel pxGoodsCostCfgAdvModel) throws PxManageException;

    /**
     * 查询所有商品成本信息
     * 
     * @param shopId
     * @return
     * @throws PxManageException
     */
    public List<PxGoodsCostCfgAdvModel> findAllGoodsCostCfg(String shopId) throws PxManageException;

    /**
     * 查询所有商品成本信息
     * 
     * @return
     * @throws PxManageException
     */
    public List<PxGoodsCostCfgAdvModel> findAllGoodsCostCfg() throws PxManageException;

    /**
     * 查询单个商品成本信息
     * 
     * @param goodsId
     * @return
     * @throws PxManageException
     */
    public PxGoodsCostCfgAdvModel findSingleGoodsCostCfg(String goodsId) throws PxManageException;
}
