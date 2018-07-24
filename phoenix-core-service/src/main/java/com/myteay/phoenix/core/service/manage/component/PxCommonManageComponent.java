/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component;

import java.util.List;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxShopModel;

/**
 * 后台一般性简单业务管理组件
 * 
 * @author min.weixm
 * @version $Id: PxCommonManageComponent.java, v 0.1 Jul 24, 2018 11:58:41 AM min.weixm Exp $
 */
public interface PxCommonManageComponent {

    /**
     * 保存店铺模型
     * 
     * @param pxShopModel
     * @return
     */
    public MtOperateResult<PxShopModel> saveShopModel(PxShopModel pxShopModel);

    /**
     * 查询所有店铺模型
     * 
     * @return
     */
    public MtOperateResult<List<PxShopModel>> queryShopAll();
}