/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;

/**
 * 商品摘要管理组件
 * 
 * @author min.weixm
 * @version $Id: PxGoodsComponent.java, v 0.1 Jul 26, 2018 1:50:35 PM min.weixm Exp $
 */
public interface PxGoodsComponent {

    /**
     * 商品摘要管理
     * 
     * @param pxGoodsModel
     * @return
     */
    public MtOperateResult<PxGoodsModel> manageGoods(PxGoodsModel pxGoodsModel);

    /**
     * 修改商品概要信息
     * 
     * @param pxGoodsModel
     * @return
     */
    public MtOperateResult<PxGoodsModel> modifyGoodsModel(PxGoodsModel pxGoodsModel);
}
