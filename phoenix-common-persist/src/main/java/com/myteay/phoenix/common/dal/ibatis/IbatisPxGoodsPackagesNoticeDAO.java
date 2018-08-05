/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.ibatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesNoticeDAO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsPackagesNoticeDO;

/**
 * 温馨提示摘要操作DAO
 * 
 * @author min.weixm
 * @version $Id: IbatisPxGoodsPackagesNoticeDAO.java, v 0.1 Aug 5, 2018 3:14:12 PM min.weixm Exp $
 */
public class IbatisPxGoodsPackagesNoticeDAO extends SqlSessionDaoSupport implements PxGoodsPackagesNoticeDAO {

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesNoticeDAO#insert(com.myteay.phoenix.common.dal.dataobject.PxGoodsPackagesNoticeDO)
     */
    @Override
    public String insert(PxGoodsPackagesNoticeDO pxGoodsPackagesNoticeDO) {
        if (pxGoodsPackagesNoticeDO == null) {
            throw new IllegalArgumentException("Can't insert a null data object into db.");
        }

        this.getSqlSession().insert("PX-GOODS-PACKAGE-NOTICE-INSERT", pxGoodsPackagesNoticeDO);

        return pxGoodsPackagesNoticeDO.getPackagesNoticeId();
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesNoticeDAO#findPxGoodsPackagesNoticeAll()
     */
    @Override
    public List<PxGoodsPackagesNoticeDO> findPxGoodsPackagesNoticeAll() {
        return this.getSqlSession().selectList("PX-GOODS-PACKAGE-NOTICE-SELECT-ALL");
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesNoticeDAO#findPxGoodsPackagesNoticeById(java.lang.String)
     */
    @Override
    public PxGoodsPackagesNoticeDO findPxGoodsPackagesNoticeById(String packageNoticeId) {
        return this.getSqlSession().selectOne("PX-GOODS-PACKAGE-NOTICE-SELECT-SINGLE-BY-ID", packageNoticeId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesNoticeDAO#findPxGoodsPackagesNoticeByGoodsId(java.lang.String)
     */
    @Override
    public List<PxGoodsPackagesNoticeDO> findPxGoodsPackagesNoticeByGoodsId(String goodsId) {
        return this.getSqlSession().selectList("PX-GOODS-PACKAGE-NOTICE-SELECT-LIST-BY-ID", goodsId);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesNoticeDAO#updatePxGoodsPackagesNotice(com.myteay.phoenix.common.dal.dataobject.PxGoodsPackagesNoticeDO)
     */
    @Override
    public void updatePxGoodsPackagesNotice(PxGoodsPackagesNoticeDO pxGoodsPackagesNoticeDO) {
        if (pxGoodsPackagesNoticeDO == null) {
            throw new IllegalArgumentException("Can't update by a null data object.");
        }

        this.getSqlSession().update("PX-GOODS-PACKAGE-NOTICE-UPDATE-BY-ID", pxGoodsPackagesNoticeDO);
    }

    /** 
     * @see com.myteay.phoenix.common.dal.daointerface.PxGoodsPackagesNoticeDAO#deleteById(java.lang.String)
     */
    @Override
    public void deleteById(String packageNoticeId) {
        this.getSqlSession().delete("PX-GOODS-PACKAGE-NOTICE-DELETE-BY-ID", packageNoticeId);
    }

}
