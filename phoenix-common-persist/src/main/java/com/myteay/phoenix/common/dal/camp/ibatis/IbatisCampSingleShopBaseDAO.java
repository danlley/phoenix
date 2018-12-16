/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.camp.ibatis;

import java.util.List;

import com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopBaseDAO;
import com.myteay.phoenix.common.dal.camp.dataobject.CampBaseDO;

/**
 * 针对单个店铺店内消费到场营销活动操作DAO
 * 
 * @author danlley
 * @version $Id: IbatisCampSingleShopBaseDAO.java, v 0.1 Dec 16, 2018 7:16:51 PM danlley Exp $
 */
public class IbatisCampSingleShopBaseDAO implements CampSingleShopBaseDAO {

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopBaseDAO#insert(com.myteay.phoenix.common.dal.camp.dataobject.CampBaseDO)
     */
    @Override
    public String insert(CampBaseDO campBaseDO) {
        // TODO Auto-generated method stub
        return null;
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopBaseDAO#findPxGoodsAll()
     */
    @Override
    public List<CampBaseDO> findPxGoodsAll() {
        // TODO Auto-generated method stub
        return null;
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopBaseDAO#findCampBaseById(java.lang.String)
     */
    @Override
    public CampBaseDO findCampBaseById(String campId) {
        // TODO Auto-generated method stub
        return null;
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopBaseDAO#findCampBaseByShopId(java.lang.String)
     */
    @Override
    public List<CampBaseDO> findCampBaseByShopId(String shopId) {
        // TODO Auto-generated method stub
        return null;
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopBaseDAO#findCampBaseOnlineByShopId(java.lang.String)
     */
    @Override
    public List<CampBaseDO> findCampBaseOnlineByShopId(String shopId) {
        // TODO Auto-generated method stub
        return null;
    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopBaseDAO#updateCampBase(com.myteay.phoenix.common.dal.camp.dataobject.CampBaseDO)
     */
    @Override
    public void updateCampBase(CampBaseDO campBaseDO) {
        // TODO Auto-generated method stub

    }

    /** 
     * @see com.myteay.phoenix.common.dal.camp.daointerface.CampSingleShopBaseDAO#deleteById(java.lang.String)
     */
    @Override
    public void deleteById(String campId) {
        // TODO Auto-generated method stub

    }

}
