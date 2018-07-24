/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.myteay.phoenix.common.dal.daointerface.PxShopDAO;
import com.myteay.phoenix.common.dal.dataobject.PxShopDO;

/**
 * 店铺管理DAO
 * 
 * @author min.weixm
 * @version $Id: IbatisPxShopDAO.java, v 0.1 Jul 24, 2018 10:13:06 AM min.weixm Exp $
 */
public class IbatisPxShopDAO extends SqlSessionDaoSupport implements PxShopDAO {

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxShopDAO#insert(com.myteay.phoenix.common.dal.dataobject.PxShopDO)
     */
    @Override
    public String insert(PxShopDO pxShopDO) {
        if (pxShopDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        this.getSqlSession().insert("PX-SHOP-INSERT", pxShopDO);

        return pxShopDO.getShopId();
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxShopDAO#findPxShopAll()
     */
    @Override
    public List<PxShopDO> findPxShopAll() {
        return this.getSqlSession().selectList("PX-SHOP-SELECT-ALL");
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxShopDAO#findPxShopById(java.lang.String)
     */
    @Override
    public PxShopDO findPxShopById(String shopId) {
        Map<String, String> map = new HashMap<>();
        map.put("shopId", shopId);

        return this.getSqlSession().selectOne("PX-SHOP-SELECT-GET-BY-ID", map);
    }

}