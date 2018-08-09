/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;

/**
 * 商品状态维护组件
 * 
 * @author min.weixm
 * @version $Id: PxGoodsStatusComponent.java, v 0.1 Aug 9, 2018 10:14:50 PM min.weixm Exp $
 */
public interface PxGoodsStatusComponent {

    /**
     * 管理商品状态
     * 
     * @param pxGoodsModel
     * @return
     * @throws PxManageException 
     */
    public MtOperateResult<PxGoodsModel> manageGoodsStatus(PxGoodsModel pxGoodsModel) throws PxManageException;
}
