/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxShopModel;

/**
 * 店铺信息维护组件
 * 
 * @author min.weixm
 * @version $Id: PxShopComponent.java, v 0.1 Jul 24, 2018 7:42:19 PM min.weixm Exp $
 */
public interface PxShopComponent {

    /**
     * 管理店铺
     * 
     * @param pxShopModel
     * @return
     */
    public MtOperateResult<PxShopModel> manageShop(PxShopModel pxShopModel);
}
