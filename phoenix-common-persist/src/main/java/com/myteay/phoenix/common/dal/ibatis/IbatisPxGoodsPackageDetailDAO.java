/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.ibatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.myteay.phoenix.common.dal.daointerface.PxGoodsPackageDetailDAO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsPackageDetailDO;

/**
 * 套餐包摘要管理
 * 
 * @author min.weixm
 * @version $Id: IbatisPxGoodsPackageDetailDAO.java, v 0.1 Jul 27, 2018 12:06:15 PM min.weixm Exp $
 */
public class IbatisPxGoodsPackageDetailDAO extends SqlSessionDaoSupport implements PxGoodsPackageDetailDAO {

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackageDetailDAO#insert(com.myteay.phoenix.common.dal.dataobject.PxGoodsPackageDetailDO)
     */
    @Override
    public String insert(PxGoodsPackageDetailDO pxGoodsPackageDetailDO) {
        if (pxGoodsPackageDetailDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        this.getSqlSession().insert("PX-GOODS-PACKAGE-DETAIL-INSERT", pxGoodsPackageDetailDO);

        return pxGoodsPackageDetailDO.getPackagesDetailId();
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackageDetailDAO#findPxGoodsPackageDetailAll()
     */
    @Override
    public List<PxGoodsPackageDetailDO> findPxGoodsPackageDetailAll() {
        return this.getSqlSession().selectList("PX-GOODS-PACKAGE-DETAIL-SELECT-ALL");
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackageDetailDAO#findPxGoodsPackageDetailById(java.lang.String)
     */
    @Override
    public PxGoodsPackageDetailDO findPxGoodsPackageDetailById(String packagesDetailId) {
        return this.getSqlSession().selectOne("PX-GOODS-PACKAGE-DETAIL-SELECT-SINGLE-BY-ID", packagesDetailId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackageDetailDAO#findPxGoodsPackageDetailByGoodsId(java.lang.String)
     */
    @Override
    public List<PxGoodsPackageDetailDO> findPxGoodsPackageDetailByGoodsId(String goodsId) {
        return this.getSqlSession().selectList("PX-GOODS-PACKAGE-DETAIL-SELECT-LIST-BY-ID", goodsId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackageDetailDAO#updatePxGoodsPackageDetail(com.myteay.phoenix.common.dal.dataobject.PxGoodsPackageDetailDO)
     */
    @Override
    public void updatePxGoodsPackageDetail(PxGoodsPackageDetailDO pxGoodsPackageDetailDO) {
        if (pxGoodsPackageDetailDO == null) {
            throw new IllegalArgumentException("Can't update by a null data object.");
        }

        this.getSqlSession().update("PX-GOODS-PACKAGE-DETAIL-DELETE-BY-ID", pxGoodsPackageDetailDO);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackageDetailDAO#deleteById(java.lang.String)
     */
    @Override
    public void deleteById(String packagesDetailId) {
        this.getSqlSession().delete("PX-GOODS-DELETE-GET-BY-ID", packagesDetailId);
    }

}
