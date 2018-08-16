/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.mobile.daointerface;

import java.util.List;

import com.myteay.phoenix.common.dal.mobile.dataobject.PxMobileGoodsDO;

/**
 * 手机端商品管理DAO
 * 
 * @author min.weixm
 * @version $Id: PxMobileGoodsDAO.java, v 0.1 Aug 16, 2018 12:53:35 AM min.weixm Exp $
 */
public interface PxMobileGoodsDAO {

    /**
     * 查询所有商品概要信息
     * 
     * @return
     */
    List<PxMobileGoodsDO> findPxMobileGoodsAll();
}
