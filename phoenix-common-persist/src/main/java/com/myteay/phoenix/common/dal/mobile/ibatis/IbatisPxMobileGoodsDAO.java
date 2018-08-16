/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.mobile.ibatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.myteay.phoenix.common.dal.mobile.daointerface.PxMobileGoodsDAO;
import com.myteay.phoenix.common.dal.mobile.dataobject.PxMobileGoodsDO;

/**
 * 手机端商品管理DAO
 * 
 * @author min.weixm
 * @version $Id: IbatisPxMobileGoodsDAO.java, v 0.1 Aug 16, 2018 12:56:19 AM min.weixm Exp $
 */
public class IbatisPxMobileGoodsDAO extends SqlSessionDaoSupport implements PxMobileGoodsDAO {

    /** 
     * @see com.myteay.phoenix.common.dal.mobile.daointerface.PxMobileGoodsDAO#findPxMobileGoodsAll()
     */
    @Override
    public List<PxMobileGoodsDO> findPxMobileGoodsAll() {
        return this.getSqlSession().selectList("PX-MOBILE-GOODS-SELECT-ALL");
    }

}
