/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.daointerface;

import java.util.List;

import com.myteay.phoenix.common.dal.dataobject.PxGoodsPackageDetailDO;

/**
 * 套餐包摘要管理
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackageDetailDAO.java, v 0.1 Jul 27, 2018 11:13:51 AM min.weixm Exp $
 */
public interface PxGoodsPackageDetailDAO {

    /**
     * 插入套餐包信息
     * 
     * @param pxGoodsPackageDetailDO   数据模型
     * @return
     */
    String insert(PxGoodsPackageDetailDO pxGoodsPackageDetailDO);

    /**
     * 查询所有套餐包信息
     * 
     * @return
     */
    List<PxGoodsPackageDetailDO> findPxGoodsPackageDetailAll();

    /**
     * 通过套餐包ID查询指定的套餐包信息
     * 
     * @param packagesDetailId
     * @return
     */
    PxGoodsPackageDetailDO findPxGoodsPackageDetailById(String packagesDetailId);

    /**
     * 通过商品ID查询指定的套餐包信息列表
     * 
     * @param goodsId
     * @return
     */
    List<PxGoodsPackageDetailDO> findPxGoodsPackageDetailByGoodsId(String goodsId);

    /**
     * 更新套餐包配置信息
     * 
     * @param pxGoodsPackageDetailDO
     */
    void updatePxGoodsPackageDetail(PxGoodsPackageDetailDO pxGoodsPackageDetailDO);

    /**
     * 通过ID删除套餐包信息
     * 
     * @param packagesDetailId
     */
    void deleteById(String packagesDetailId);
}
