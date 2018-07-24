/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.component;

import java.util.List;

import com.myteay.common.util.model.DataDictionaryModel;
import com.myteay.phoenix.core.model.MtOperateResult;

/**
 * 数据字典查询组件
 * 
 * @author min.weixm
 * @version $Id: PxDataDictionaryComponent.java, v 0.1 Feb 8, 2018 9:30:20 PM min.weixm Exp $
 */
public interface PxDataDictionaryComponent {

    /**
     * 根据特定的业务关键字查询相应的数据字典内容
     * 
     * @param key   业务关键字
     * @return      数据字典内容
     */
    public MtOperateResult<List<DataDictionaryModel>> queryDataDictionaryByKey(String key);

}
