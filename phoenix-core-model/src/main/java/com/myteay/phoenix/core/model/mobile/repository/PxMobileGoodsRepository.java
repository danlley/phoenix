/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.mobile.repository;

import java.util.List;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.mobile.PxMobileGoodsModel;

/**
 * 手机端商品管理仓储
 * 
 * @author min.weixm
 * @version $Id: PxMobileGoodsRepository.java, v 0.1 Aug 16, 2018 9:51:47 PM min.weixm Exp $
 */
public interface PxMobileGoodsRepository {

    /**
     * 查询所有已发布商品
     * 
     * 条件：
     *      1、商品未到过期时间
     *      2、商品所属店铺未到过期时间
     *      3、商品所属店铺合同未到期
     * 
     * @return
     * @throws PxManageException
     */
    public List<PxMobileGoodsModel> findAll() throws PxManageException;
}
