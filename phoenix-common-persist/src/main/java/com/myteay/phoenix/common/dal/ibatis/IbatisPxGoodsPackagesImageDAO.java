/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.ibatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesImageDAO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsPackagesImageDO;

/**
 * 商品详情图片管理
 * 
 * @author min.weixm
 * @version $Id: IbatisPxGoodsPackagesImageDAO.java, v 0.1 Aug 1, 2018 11:49:36 AM min.weixm Exp $
 */
public class IbatisPxGoodsPackagesImageDAO extends SqlSessionDaoSupport implements PxGoodsPackagesImageDAO {

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesImageDAO#insert(com.myteay.phoenix.common.dal.dataobject.PxGoodsPackagesImageDO)
     */
    @Override
    public String insert(PxGoodsPackagesImageDO pxGoodsPackageImageDO) {
        if (pxGoodsPackageImageDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        this.getSqlSession().insert("PX-GOODS-PACKAGE-IMAGE-INSERT", pxGoodsPackageImageDO);

        return pxGoodsPackageImageDO.getImageId();
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesImageDAO#findPxGoodsPackageImageById(java.lang.String)
     */
    @Override
    public PxGoodsPackagesImageDO findPxGoodsPackageImageById(String imageId) {
        return this.getSqlSession().selectOne("PX-GOODS-PACKAGE-IMAGE-SELECT-SINGLE-BY-ID", imageId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesImageDAO#findPxGoodsPackageImageByGoodsId(java.lang.String)
     */
    @Override
    public List<PxGoodsPackagesImageDO> findPxGoodsPackageImageByGoodsId(String goodsId) {
        return this.getSqlSession().selectList("PX-GOODS-PACKAGE-IMAGE-SELECT-LIST-BY-ID", goodsId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesImageDAO#deleteById(java.lang.String)
     */
    @Override
    public void deleteById(String imageId) {
        this.getSqlSession().delete("PX-GOODS-PACKAGE-IMAGE-DELETE-BY-ID", imageId);
    }

}
