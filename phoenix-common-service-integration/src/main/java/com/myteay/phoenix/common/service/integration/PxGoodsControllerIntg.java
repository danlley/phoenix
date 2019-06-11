/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.integration;

import java.util.List;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsAdvModel;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;

/**
 * 集成数据中心服务
 * 
 * @author min.weixm
 * @version $Id: PxGoodsControllerIntg.java, v 0.1 Jun 11, 2019 5:08:18 PM min.weixm Exp $
 */
public interface PxGoodsControllerIntg {

    /**
     * 查询所有商品概要信息
     * 
     * @return
     */
    public MtOperateResult<List<PxGoodsModel>> queryGoodsAll();

    /**
     * 通过店铺ID查询店铺下的所有商品列表
     * 
     * @param shopId
     * @return
     */
    public MtOperateResult<List<PxGoodsModel>> queryGoodsListByShopId(String shopId);

    /**
     * 商品概要管理服务（增、删、改、单条查询）
     * 
     * @param goodsId
     * @return
     */
    public MtOperateResult<PxGoodsAdvModel> queryGoodsAdvAll(String goodsId);

    /**
     * 多条件查询商品概要信息
     * 
     * @param shopId
     * @param goodsType
     * @param goodsTitle
     * @return
     */
    public MtOperateResult<List<PxGoodsModel>> findPxShopOnlineGoodsByCondition(String shopId, String goodsType, String goodsTitle);

    /**
     * 商品下架及商品发布
     * 
     * @param pxGoodsModel
     * @return
     */
    public MtOperateResult<PxGoodsModel> manageGoodsStatus(PxGoodsModel pxGoodsModel);

    /**
     * 商品概要管理服务（增、删、改、单条查询）
     * 
     * @param pxGoodsModel
     * @return
     */
    public MtOperateResult<PxGoodsModel> manageGoods(PxGoodsModel pxGoodsModel);
}
