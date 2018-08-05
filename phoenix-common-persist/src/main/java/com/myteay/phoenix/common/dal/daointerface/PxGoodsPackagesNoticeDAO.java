/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.daointerface;

import java.util.List;

import com.myteay.phoenix.common.dal.dataobject.PxGoodsPackagesNoticeDO;

/**
 * 温馨提示摘要管理DAO
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesNoticeDAO.java, v 0.1 Aug 5, 2018 2:39:11 PM min.weixm Exp $
 */
public interface PxGoodsPackagesNoticeDAO {

    /**
     * 插入温馨提示摘要信息
     * 
     * @param pxGoodsPackagesNoticeDO   数据模型
     * @return
     */
    String insert(PxGoodsPackagesNoticeDO pxGoodsPackagesNoticeDO);

    /**
     * 查询所有温馨提示摘要信息
     * 
     * @return
     */
    List<PxGoodsPackagesNoticeDO> findPxGoodsPackagesNoticeAll();

    /**
     * 通过温馨提示摘要ID查询指定的温馨提示摘要信息
     * 
     * @param packageNoticeId
     * @return
     */
    PxGoodsPackagesNoticeDO findPxGoodsPackagesNoticeById(String packageNoticeId);

    /**
     * 通过商品ID查询指定的温馨提示摘要信息列表
     * 
     * @param goodsId
     * @return
     */
    List<PxGoodsPackagesNoticeDO> findPxGoodsPackagesNoticeByGoodsId(String goodsId);

    /**
     * 更新温馨提示摘要配置信息
     * 
     * @param pxGoodsPackagesNoticeDO
     */
    void updatePxGoodsPackagesNotice(PxGoodsPackagesNoticeDO pxGoodsPackagesNoticeDO);

    /**
     * 通过ID删除温馨提示摘要信息
     * 
     * @param packageNoticeId
     */
    void deleteById(String packageNoticeId);
}
