/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel;

/**
 * 套餐包组件
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesDetailComponent.java, v 0.1 Jul 27, 2018 8:55:27 PM min.weixm Exp $
 */
public interface PxGoodsPackagesDetailComponent {

    /**
     * 管理套餐包
     * 
     * @param pxGoodsPackagesDetailModel
     * @return
     */
    public MtOperateResult<PxGoodsPackagesDetailModel> manageGoodsPackagesDetail(PxGoodsPackagesDetailModel pxGoodsPackagesDetailModel);
}
