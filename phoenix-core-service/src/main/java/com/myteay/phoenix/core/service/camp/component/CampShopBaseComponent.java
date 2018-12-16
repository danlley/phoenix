/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.component;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampBaseModel;

/**
 * 店内营销活动基本信息管理组件
 * 
 * @author danlley
 * @version $Id: CampShopBaseComponent.java, v 0.1 Dec 17, 2018 12:03:49 AM danlley Exp $
 */
public interface CampShopBaseComponent {

    /**
     * 店内营销活动基本信息管理
     * 
     * @param campBaseModel
     * @return
     */
    public MtOperateResult<CampBaseModel> manageCampBase(CampBaseModel campBaseModel);

    /**
     * 修改店内营销活动基本信息信息
     * 
     * @param campBaseModel
     * @return
     */
    public MtOperateResult<CampBaseModel> modifyCampBaseModel(CampBaseModel campBaseModel);

    /**
     * 通过活动ID查询店内营销活动基本信息模型（内部组件公用）
     * 
     * @param campId
     * @return
     */
    public CampBaseModel queryCampBaseModelByCampId(String campId);

}
