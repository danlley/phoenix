/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxSubPackagesModel;

/**
 * 子套餐管理组件
 * 
 * @author min.weixm
 * @version $Id: PxSubPackagesComponent.java, v 0.1 Jul 28, 2018 11:26:17 AM min.weixm Exp $
 */
public interface PxSubPackagesComponent {

    /**
     * 管理子套餐
     * 
     * @param pxSubPackagesModel
     * @return
     */
    public MtOperateResult<PxSubPackagesModel> manageSubPackages(PxSubPackagesModel pxSubPackagesModel);
}
