/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.ibatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.myteay.phoenix.common.dal.daointerface.PxSubPackagesDAO;
import com.myteay.phoenix.common.dal.dataobject.PxSubPackagesDO;

/**
 * 子套餐管理DAO
 * 
 * @author min.weixm
 * @version $Id: IbatisPxSubPackagesDAO.java, v 0.1 Jul 28, 2018 9:28:47 AM min.weixm Exp $
 */
public class IbatisPxSubPackagesDAO extends SqlSessionDaoSupport implements PxSubPackagesDAO {

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxSubPackagesDAO#insert(com.myteay.phoenix.common.dal.dataobject.PxSubPackagesDO)
     */
    @Override
    public String insert(PxSubPackagesDO pxSubPackagesDO) {
        if (pxSubPackagesDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        this.getSqlSession().insert("PX-SUB-PACKAGE-INSERT", pxSubPackagesDO);

        return pxSubPackagesDO.getPackagesDetailId();
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxSubPackagesDAO#findPxSubPackagesAll()
     */
    @Override
    public List<PxSubPackagesDO> findPxSubPackagesAll() {
        return this.getSqlSession().selectList("PX-SUB-PACKAGE-SELECT-ALL");
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxSubPackagesDAO#findPxSubPackagesById(java.lang.String)
     */
    @Override
    public PxSubPackagesDO findPxSubPackagesById(String subPackagesId) {
        return this.getSqlSession().selectOne("PX-SUB-PACKAGE-SELECT-SINGLE-BY-ID", subPackagesId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxSubPackagesDAO#findPxSubPackagesByGoodsId(java.lang.String)
     */
    @Override
    public List<PxSubPackagesDO> findPxSubPackagesByGoodsId(String packagesDetailId) {
        return this.getSqlSession().selectList("PX-SUB-PACKAGE-SELECT-LIST-BY-ID", packagesDetailId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxSubPackagesDAO#updatePxSubPackages(com.myteay.phoenix.common.dal.dataobject.PxSubPackagesDO)
     */
    @Override
    public void updatePxSubPackages(PxSubPackagesDO pxSubPackagesDO) {
        if (pxSubPackagesDO == null) {
            throw new IllegalArgumentException("Can't update by a null data object.");
        }

        this.getSqlSession().update("PX-SUB-PACKAGE-UPDATE-BY-ID", pxSubPackagesDO);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxSubPackagesDAO#deleteById(java.lang.String)
     */
    @Override
    public void deleteById(String subPackagesId) {
        this.getSqlSession().delete("PX-SUB-PACKAGE-DELETE-BY-ID", subPackagesId);
    }

}
