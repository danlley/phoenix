/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.template;

import com.myteay.phoenix.core.model.MtOperateResult;

/**
 * 普通后台管理业务回调接口
 * 
 * @author min.weixm
 * @version $Id: PxCommonCallback.java, v 0.1 Jul 26, 2018 4:04:11 PM min.weixm Exp $
 */
public abstract class PxCommonCallback<T> {

    /**
     * 添加模型
     * 
     * @param t
     * @return
     */
    public abstract MtOperateResult<T> addModel(T t);

    /**
     * 删除模型
     * 
     * @param t
     * @return
     */
    public abstract MtOperateResult<T> deleteModel(T t);

    /**
     * 修改模型
     * 
     * @param t
     * @return
     */
    public abstract MtOperateResult<T> modifyModel(T t);

    /**
     * 查询单个模型
     * 
     * @param t
     * @return
     */
    public abstract MtOperateResult<T> querySingleModel(T t);
}
