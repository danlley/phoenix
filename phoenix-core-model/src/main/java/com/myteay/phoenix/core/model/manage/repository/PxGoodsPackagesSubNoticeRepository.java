/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.repository;

import java.util.List;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesSubNoticeModel;

/**
 * 温馨提醒子项管理仓储
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesSubNoticeRepository.java, v 0.1 Aug 5, 2018 10:44:42 PM min.weixm Exp $
 */
public interface PxGoodsPackagesSubNoticeRepository {

    /**
     * 删除温馨提醒子项
     * 
     * @param pxGoodsPackagesSubNoticeModel
     * @throws PxManageException 
     */
    public void removePackagesSubNoticeInfo(PxGoodsPackagesSubNoticeModel pxGoodsPackagesSubNoticeModel) throws PxManageException;

    /**
     * 修改温馨提醒子项信息
     * 
     * @param pxGoodsPackagesSubNoticeModel
     * @return
     * @throws PxManageException 
     */
    public PxGoodsPackagesSubNoticeModel modifyPackagesSubNoticeInfo(PxGoodsPackagesSubNoticeModel pxGoodsPackagesSubNoticeModel) throws PxManageException;

    /**
     * 保存温馨提醒子项模型
     * 
     * @param pxGoodsPackagesSubNoticeModel
     * @return
     * @throws PxManageException 
     */
    public PxGoodsPackagesSubNoticeModel savePackagesSubNoticeInfo(PxGoodsPackagesSubNoticeModel pxGoodsPackagesSubNoticeModel) throws PxManageException;

    /**
     * 查询单个温馨提醒子项信息
     * 
     * @param packagesSubNoticeId
     * @return
     * @throws PxManageException
     */
    public PxGoodsPackagesSubNoticeModel findSinglePackagesSubNotice(String packagesSubNoticeId) throws PxManageException;

    /**
     * 通过店铺查询温馨提醒子项信息列表
     * 
     * @param packagesNoticeId
     * @return
     * @throws PxManageException
     */
    public List<PxGoodsPackagesSubNoticeModel> findPackagesSubNoticeByGoodsId(String packagesNoticeId) throws PxManageException;
}
