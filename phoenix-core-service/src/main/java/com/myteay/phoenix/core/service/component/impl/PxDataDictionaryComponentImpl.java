/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2021 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.component.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;

import com.myteay.common.util.model.DataDictionaryModel;
import com.myteay.phoenix.core.service.component.PxDataDictionaryComponent;
import com.tc.phoenix.common.util.MtOperateResult;
import com.tc.phoenix.common.util.enums.MtOperateExResultEnum;
import com.tc.phoenix.common.util.enums.MtOperateResultEnum;

/**
 * 数据字典查询组件
 * 
 * @author min.weixm
 * @version $Id: PxDataDictionaryComponentImpl.java, v 0.1 Feb 8, 2018 9:36:38 PM min.weixm Exp $
 */
public class PxDataDictionaryComponentImpl implements PxDataDictionaryComponent, InitializingBean {

    /** 日志 */
    public static final Logger                                 logger         = Logger.getLogger(PxDataDictionaryComponentImpl.class);

    /** 数据字典缓存 */
    public static final Map<String, List<DataDictionaryModel>> DATA_DIC_CACHE = Collections.synchronizedMap(new HashMap<>());

    /** 
     * @see com.myteay.PxDataDictionaryComponent.core.service.components.MtDataDictionaryComponent#queryDataDictionaryByKey(java.lang.String)
     */
    @Override
    public MtOperateResult<List<DataDictionaryModel>> queryDataDictionaryByKey(String key) {
        if (CollectionUtils.isEmpty(DATA_DIC_CACHE) || StringUtils.isBlank(key)) {
            logger.warn("当前关键字无法找到对应的数据字典，请检查数据字典中是否存在相应的数据 key=" + key);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
        }

        return new MtOperateResult<List<DataDictionaryModel>>(DATA_DIC_CACHE.get(key));
    }

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
    }

}
