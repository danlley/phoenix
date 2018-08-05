/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesSubNoticeModel;

/**
 * 温馨提醒子项管理组件
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesSubNoticeComponent.java, v 0.1 Aug 5, 2018 11:18:24 PM min.weixm Exp $
 */
public interface PxGoodsPackagesSubNoticeComponent {

    /**
     * 管理温馨提醒子项
     * 
     * @param pxGoodsPackagesSubNoticeModel
     * @return
     */
    public MtOperateResult<PxGoodsPackagesSubNoticeModel> manageSubPackages(PxGoodsPackagesSubNoticeModel pxGoodsPackagesSubNoticeModel);
}
