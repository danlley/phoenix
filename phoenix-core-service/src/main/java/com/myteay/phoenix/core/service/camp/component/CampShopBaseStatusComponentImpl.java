/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.component;

import org.apache.log4j.Logger;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampBaseModel;

/**
 * 店内营销活动状态管理
 * 
 * @author danlley
 * @version $Id: CampShopBaseStatusComponentImpl.java, v 0.1 Dec 17, 2018 12:54:56 AM danlley Exp $
 */
public class CampShopBaseStatusComponentImpl implements CampShopBaseStatusComponent {

    /** 日志 */
    public static final Logger    logger = Logger.getLogger(CampShopBaseStatusComponentImpl.class);

    /** 针对单个店铺店内消费到场营销活动操作仓储 */
    private CampShopBaseComponent campShopBaseComponent;

    /** 
     * @see com.myteay.phoenix.core.service.camp.component.CampShopBaseStatusComponent#changeCampStatus(com.myteay.phoenix.core.model.camp.CampBaseModel)
     */
    @Override
    public MtOperateResult<CampBaseModel> changeCampStatus(CampBaseModel campBaseModel) {
        // TODO Auto-generated method stub
        return campShopBaseComponent.modifyCampBaseModel(campBaseModel);
    }

    /**
     * Setter method for property <tt>campShopBaseComponent</tt>.
     * 
     * @param campShopBaseComponent value to be assigned to property campShopBaseComponent
     */
    public void setCampShopBaseComponent(CampShopBaseComponent campShopBaseComponent) {
        this.campShopBaseComponent = campShopBaseComponent;
    }

}
