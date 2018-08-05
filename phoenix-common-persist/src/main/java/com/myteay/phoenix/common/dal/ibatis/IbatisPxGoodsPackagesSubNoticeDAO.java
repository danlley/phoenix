/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.ibatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesSubNoticeDAO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsPackagesSubNoticeDO;

/**
 * 温馨提醒子项管理操作DAO
 * 
 * @author min.weixm
 * @version $Id: IbatisPxGoodsPackagesSubNoticeDAO.java, v 0.1 Aug 5, 2018 10:24:38 PM min.weixm Exp $
 */
public class IbatisPxGoodsPackagesSubNoticeDAO extends SqlSessionDaoSupport implements PxGoodsPackagesSubNoticeDAO {

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesSubNoticeDAO#insert(com.myteay.phoenix.common.dal.dataobject.PxGoodsPackagesSubNoticeDO)
     */
    @Override
    public String insert(PxGoodsPackagesSubNoticeDO pxGoodsPackagesSubNoticeDO) {
        if (pxGoodsPackagesSubNoticeDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        this.getSqlSession().insert("PX-PACKAGES-SUB-NOTICE-INSERT", pxGoodsPackagesSubNoticeDO);

        return pxGoodsPackagesSubNoticeDO.getPackagesSuNoticeId();
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesSubNoticeDAO#findPxSubPackagesNoticeAll()
     */
    @Override
    public List<PxGoodsPackagesSubNoticeDO> findPxSubPackagesNoticeAll() {
        return this.getSqlSession().selectList("PX-PACKAGES-SUB-NOTICE-SELECT-ALL");
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesSubNoticeDAO#findPxSubPackagesNoticeById(java.lang.String)
     */
    @Override
    public PxGoodsPackagesSubNoticeDO findPxSubPackagesNoticeById(String packagesSubNoticeId) {
        return this.getSqlSession().selectOne("PX-PACKAGES-SUB-NOTICE-SELECT-SINGLE-BY-ID", packagesSubNoticeId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesSubNoticeDAO#findPxSubPackagesNoticeByNoticeId(java.lang.String)
     */
    @Override
    public List<PxGoodsPackagesSubNoticeDO> findPxSubPackagesNoticeByNoticeId(String packagesNoticeId) {
        return this.getSqlSession().selectList("PX-PACKAGES-SUB-NOTICE-SELECT-LIST-BY-ID", packagesNoticeId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesSubNoticeDAO#updatePxSubPackagesNotice(com.myteay.phoenix.common.dal.dataobject.PxGoodsPackagesSubNoticeDO)
     */
    @Override
    public void updatePxSubPackagesNotice(PxGoodsPackagesSubNoticeDO pxGoodsPackagesSubNoticeDO) {
        if (pxGoodsPackagesSubNoticeDO == null) {
            throw new IllegalArgumentException("Can't update by a null data object.");
        }

        this.getSqlSession().update("PX-PACKAGES-SUB-NOTICE-UPDATE-BY-ID", pxGoodsPackagesSubNoticeDO);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesSubNoticeDAO#deleteById(java.lang.String)
     */
    @Override
    public void deleteById(String packagesSubNoticeId) {
        this.getSqlSession().delete("PX-PACKAGES-SUB-NOTICE-DELETE-BY-ID", packagesSubNoticeId);
    }

}
