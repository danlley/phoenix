/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesNoticeModel;

/**
 * 温馨提醒摘要管理组件
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesNoticeComponent.java, v 0.1 Aug 5, 2018 9:22:37 PM min.weixm Exp $
 */
public interface PxGoodsPackagesNoticeComponent {

    /**
     * 管理温馨提醒摘要
     * 
     * @param pxGoodsPackagesNoticeModel
     * @return
     */
    public MtOperateResult<PxGoodsPackagesNoticeModel> manageGoodsPackagesNotice(PxGoodsPackagesNoticeModel pxGoodsPackagesNoticeModel);
}
