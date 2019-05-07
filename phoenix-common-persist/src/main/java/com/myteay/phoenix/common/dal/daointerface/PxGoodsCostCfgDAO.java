/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.daointerface;

import java.util.List;

import com.myteay.phoenix.common.dal.dataobject.PxGoodsCostCfgAdvDO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsCostCfgDO;

/**
 * 商品成本管理操作DAO
 * 
 * @author danlley
 * @version $Id: PxGoodsCostCfgDAO.java, v 0.1 May 6, 2019 11:11:50 PM danlley Exp $
 */
public interface PxGoodsCostCfgDAO {

    /**
     * 插入商品成本信息
     * 
     * @param pxGoodsCostCfgDO   数据模型
     * @return
     */
    String insert(PxGoodsCostCfgDO pxGoodsCostCfgDO);

    /**
     * 通过商品ID查询指定的商品成本数据模型
     * 
     * @param goodsId
     * @return
     */
    PxGoodsCostCfgDO findPxGoodsCostCfgById(String goodsId);

    /**
     * 查询所有商品成本数据模型
     * 
     * @param shopId
     * @return
     */
    List<PxGoodsCostCfgAdvDO> findPxGoodsCostCfgAll(String shopId);

    /**
     * 更新商品成本信息
     * 
     * @param pxGoodsCostCfgDO
     */
    void updatePxGoodsCostCfg(PxGoodsCostCfgDO pxGoodsCostCfgDO);

}
