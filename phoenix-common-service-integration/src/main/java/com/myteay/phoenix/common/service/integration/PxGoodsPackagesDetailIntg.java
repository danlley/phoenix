/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.integration;

import java.util.List;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel;

/**
 * 套餐包详情
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesDetailIntg.java, v 0.1 Jun 11, 2019 11:03:00 PM min.weixm Exp $
 */
public interface PxGoodsPackagesDetailIntg {

    /**
     * 查询所有套餐包信息
     * 
     * @return
     */
    MtOperateResult<List<PxGoodsPackagesDetailModel>> queryPackagesDetailListAll();

    /**
     * 通过商品摘要ID查询店铺下的所有套餐包列表
     * 
     * @param goodsId
     * @return
     */
    MtOperateResult<List<PxGoodsPackagesDetailModel>> queryPackagesDetailListByGoodsId(String goodsId);

    /**
     * 套餐包管理服务（增、删、改、单条查询）
     * 
     * @param pxGoodsPackagesDetailModel
     * @return
     */
    MtOperateResult<PxGoodsPackagesDetailModel> manageGoodsPackagesDetail(PxGoodsPackagesDetailModel pxGoodsPackagesDetailModel);
}