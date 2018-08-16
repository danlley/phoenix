/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.mobile.repository.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.myteay.phoenix.common.dal.mobile.daointerface.PxMobileGoodsDAO;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.mobile.PxMobileGoodsModel;
import com.myteay.phoenix.core.model.mobile.repository.PxMobileGoodsRepository;

/**
 * 手机端商品管理仓储
 * 
 * @author min.weixm
 * @version $Id: PxMobileGoodsRepositoryImpl.java, v 0.1 Aug 16, 2018 9:52:39 PM min.weixm Exp $
 */
public class PxMobileGoodsRepositoryImpl implements PxMobileGoodsRepository {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(PxMobileGoodsRepositoryImpl.class);

    /** 手机端商品管理 */
    private PxMobileGoodsDAO   pxMobileGoodsDAO;

    /** 
     * @see com.myteay.phoenix.core.model.mobile.repository.PxMobileGoodsRepository#findAll()
     */
    @Override
    public List<PxMobileGoodsModel> findAll() throws PxManageException {
        return null;
    }

    /**
     * Setter method for property <tt>pxMobileGoodsDAO</tt>.
     * 
     * @param pxMobileGoodsDAO value to be assigned to property pxMobileGoodsDAO
     */
    public void setPxMobileGoodsDAO(PxMobileGoodsDAO pxMobileGoodsDAO) {
        this.pxMobileGoodsDAO = pxMobileGoodsDAO;
    }

}
