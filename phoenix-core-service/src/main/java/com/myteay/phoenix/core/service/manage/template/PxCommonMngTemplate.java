/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.template;

import com.myteay.phoenix.common.util.enums.PxOperationTypeEnum;
import com.myteay.phoenix.core.model.MtOperateResult;

/**
 * 后台管理业务处理分流模板
 * 
 * @author min.weixm
 * @version $Id: PxCommonMngTemplate.java, v 0.1 Jul 26, 2018 4:01:51 PM min.weixm Exp $
 */
public interface PxCommonMngTemplate<T> {

    /**
     * 根据不同的管理目标执行管理动作
     * 
     * @param pxShopModel
     * @param operationType
     * @return
     */
    public MtOperateResult<T> switchOperation(T t, PxOperationTypeEnum operationType, PxCommonCallback<T> callback);
}
