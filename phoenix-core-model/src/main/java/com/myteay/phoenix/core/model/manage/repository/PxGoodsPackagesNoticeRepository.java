/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.repository;

import java.util.List;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesNoticeModel;

/**
 * 温馨提醒摘要管理仓储
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesNoticeRepository.java, v 0.1 Aug 5, 2018 9:00:45 PM min.weixm Exp $
 */
public interface PxGoodsPackagesNoticeRepository {

    /**
     * 删除温馨提醒摘要
     * 
     * @param pxGoodsPackagesNoticeModel
     * @throws PxManageException 
     */
    public void removeGoodsPackagesNoticeInfo(PxGoodsPackagesNoticeModel pxGoodsPackagesNoticeModel) throws PxManageException;

    /**
     * 修改温馨提醒摘要信息
     * 
     * @param pxGoodsPackagesNoticeModel
     * @return
     * @throws PxManageException 
     */
    public PxGoodsPackagesNoticeModel modifyGoodsPackagesNoticeInfo(PxGoodsPackagesNoticeModel pxGoodsPackagesNoticeModel) throws PxManageException;

    /**
     * 保存温馨提醒摘要模型
     * 
     * @param pxGoodsPackagesNoticeModel
     * @return
     * @throws PxManageException 
     */
    public PxGoodsPackagesNoticeModel saveGoodsPackagesNoticeInfo(PxGoodsPackagesNoticeModel pxGoodsPackagesNoticeModel) throws PxManageException;

    /**
     * 查询单个温馨提醒摘要信息
     * 
     * @param packagesNoticeId
     * @return
     * @throws PxManageException
     */
    public PxGoodsPackagesNoticeModel findSingleGoodsPackagesNotice(String packagesNoticeId) throws PxManageException;

    /**
     * 通过店铺查询温馨提醒摘要信息列表
     * 
     * @param goodsId
     * @return
     * @throws PxManageException
     */
    public List<PxGoodsPackagesNoticeModel> findGoodsPackagesNoticeByGoodsId(String goodsId) throws PxManageException;
}
