/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesImageModel;

/**
 * 套餐详情图片管理
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesImageComponent.java, v 0.1 Aug 1, 2018 1:10:50 PM min.weixm Exp $
 */
public interface PxGoodsPackagesImageComponent {

    /**
     * 管理套餐详情
     * 
     * @param pxGoodsPackagesImageModel
     * @return
     */
    public MtOperateResult<PxGoodsPackagesImageModel> manageGoodsPackagesImage(PxGoodsPackagesImageModel pxGoodsPackagesImageModel);
}
