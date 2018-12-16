/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.component;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampBaseModel;

/**
 * 店内营销活动状态管理
 * 
 * @author danlley
 * @version $Id: CampShopBaseStatusComponent.java, v 0.1 Dec 17, 2018 12:52:15 AM danlley Exp $
 */
public interface CampShopBaseStatusComponent {

    /**
     * 改变指定店铺下特定店内营销活动的状态（关闭、启动）
     * 
     * @param campBaseModel
     */
    public MtOperateResult<CampBaseModel> changeCampStatus(CampBaseModel campBaseModel) throws PxManageException;
}
