/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsCostModel;

/**
 * 店铺商品经营成本统计管理组件
 * 
 * @author danlley
 * @version $Id: PxGoodsCostComponent.java, v 0.1 May 8, 2019 12:29:40 AM danlley Exp $
 */
public interface PxGoodsCostComponent {

    /**
     * 管理商品成本信息
     * 
     * @param pxGoodsCostModel
     * @return
     * @throws PxManageException 
     */
    public MtOperateResult<PxGoodsCostModel> manageGoodsCostInfo(PxGoodsCostModel pxGoodsCostModel) throws PxManageException;

}
