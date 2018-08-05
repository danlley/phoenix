/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.daointerface;

import java.util.List;

import com.myteay.phoenix.common.dal.dataobject.PxGoodsPackagesSubNoticeDO;

/**
 * 温馨提醒子项管理操作DAO
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesSubNoticeDAO.java, v 0.1 Aug 5, 2018 10:17:21 PM min.weixm Exp $
 */
public interface PxGoodsPackagesSubNoticeDAO {

    /**
     * 插入温馨提醒子项信息
     * 
     * @param pxGoodsPackagesSubNoticeDO   数据模型
     * @return
     */
    String insert(PxGoodsPackagesSubNoticeDO pxGoodsPackagesSubNoticeDO);

    /**
     * 查询所有温馨提醒子项信息
     * 
     * @return
     */
    List<PxGoodsPackagesSubNoticeDO> findPxSubPackagesNoticeAll();

    /**
     * 通过温馨提醒子项ID查询指定的温馨提醒子项信息
     * 
     * @param packagesSubNoticeId
     * @return
     */
    PxGoodsPackagesSubNoticeDO findPxSubPackagesNoticeById(String packagesSubNoticeId);

    /**
     * 通过温馨提醒摘要ID查询指定的温馨提醒子项信息列表
     * 
     * @param packagesNoticeId
     * @return
     */
    List<PxGoodsPackagesSubNoticeDO> findPxSubPackagesNoticeByNoticeId(String packagesNoticeId);

    /**
     * 更新温馨提醒子项配置信息
     * 
     * @param pxGoodsPackagesSubNoticeDO
     */
    void updatePxSubPackagesNotice(PxGoodsPackagesSubNoticeDO pxGoodsPackagesSubNoticeDO);

    /**
     * 通过ID删除温馨提醒子项信息
     * 
     * @param packagesSubNoticeId
     */
    void deleteById(String packagesSubNoticeId);
}
