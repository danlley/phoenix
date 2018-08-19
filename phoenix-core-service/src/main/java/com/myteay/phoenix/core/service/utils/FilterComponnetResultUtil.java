/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.utils;

import com.myteay.phoenix.core.model.MtOperateResult;

/**
 * 过滤处理结果
 * 
 * @author danlley
 * @version $Id: FilterComponnetResultUtil.java, v 0.1 Aug 19, 2018 6:51:20 PM danlley Exp $
 */
public class FilterComponnetResultUtil<T> {

    private static FilterComponnetResultUtil<?> _instance = new FilterComponnetResultUtil<>();

    /**
     * 获取实例
     * 
     * @return
     */
    public static FilterComponnetResultUtil<?> getInstance() {
        return _instance;
    }

    /**
     * 获取结果中的实体结果
     * 
     * @param param
     * @return
     */
    public T filter(MtOperateResult<T> param) {

        if (param == null) {
            return null;
        }

        return param.getResult();
    }
}
