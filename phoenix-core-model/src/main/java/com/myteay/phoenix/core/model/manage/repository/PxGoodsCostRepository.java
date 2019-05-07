/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.repository;

import java.util.List;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxGoodsCostModel;

/**
 * 商品成本记录管理仓储
 * 
 * @author danlley
 * @version $Id: PxGoodsCostRepository.java, v 0.1 May 8, 2019 12:01:12 AM danlley Exp $
 */
public interface PxGoodsCostRepository {

    /**
     * 查询店铺指定日期的商品销售成本清单
     * 
     * @param shopId
     * @param reportDate
     * @return
     */
    public List<PxGoodsCostModel> findPxGoodsCostByShopId(String shopId, String reportDate);

    /**
     * 修改商品成本信息
     * 
     * @param pxGoodsCostModel
     * @return
     * @throws PxManageException 
     */
    public PxGoodsCostModel modifyGoodsCostInfo(PxGoodsCostModel pxGoodsCostModel) throws PxManageException;

    /**
     * 保存商品成本模型
     * 
     * @param pxGoodsCostModel
     * @return
     * @throws PxManageException 
     */
    public PxGoodsCostModel saveGoodsCostInfo(PxGoodsCostModel pxGoodsCostModel) throws PxManageException;

    /**
     * 查询单个商品成本信息
     * 
     * @param goodsId
     * @param goodsId
     * @return
     * @throws PxManageException
     */
    public PxGoodsCostModel findSingleGoodsCost(String goodsId, String reportDate) throws PxManageException;
}
