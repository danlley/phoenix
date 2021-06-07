/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.tc.phoenix.common.service.integration;

import java.util.List;

import com.tc.dbcenter.common.orm.model.PxGoodsPackagesImageModel;
import com.tc.phoenix.common.util.MtOperateResult;

/**
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesImageIntg.java, v 0.1 Jun 11, 2019 11:19:32 PM min.weixm Exp $
 */
public interface PxGoodsPackagesImageIntg {

    /**
     * 通过商品摘要ID查询店铺下的所有套餐详情图片列表
     * 
     * @param goodsId
     * @return
     */
    MtOperateResult<List<PxGoodsPackagesImageModel>> queryPackagesImageListByGoodsId(String goodsId);

    /**
     * 套餐详情图片管理服务（增、删、改、单条查询）
     * 
     * @param pxGoodsPackagesImageModel
     * @return
     */
    MtOperateResult<PxGoodsPackagesImageModel> manageGoodsPackagesImage(PxGoodsPackagesImageModel pxGoodsPackagesImageModel);
}
