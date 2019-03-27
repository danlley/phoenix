/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.daointerface;

import java.util.List;

import com.myteay.phoenix.common.dal.dataobject.PxGoodsOrderOutDO;

/**
 * 订单流水操作DAO
 * 
 * @author danlley
 * @version $Id: PxGoodsOrderOutDAO.java, v 0.1 Feb 26, 2019 4:45:01 PM danlley Exp $
 */
public interface PxGoodsOrderOutDAO {

    /**
     * 查询所有已经过期的店铺消费废单，为下一步进行清理做准备
     * 
     * @return
     */
    List<PxGoodsOrderOutDO> selectExpiredGoodsOrderOutDOs();

    /**
     * 插入商品概要信息
     * 
     * @param pxGoodsOrderOutDO   数据模型
     * @return
     */
    String insert(PxGoodsOrderOutDO pxGoodsOrderOutDO);

    /**
     * 更新商品概要配置信息
     * 
     * @param pxGoodsOrderOutDO
     */
    void updatePxGoods(PxGoodsOrderOutDO pxGoodsOrderOutDO);

    /**
     * 通过ID 删除商品概要信息
     * 
     * @param id
     */
    void deleteByIdWithStatus(String id);

}
