/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
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

import com.myteay.common.util.exception.MtException;
import com.myteay.common.util.model.DataDictionaryModel;
import com.myteay.common.util.tools.EnumUtil;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsHuiyuanEnum;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsOrderTypeEnum;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsQuanEnum;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsTuanEnum;
import com.myteay.phoenix.common.util.manage.enums.PxShopStatusEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.service.component.PxDataDictionaryComponent;

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
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_DATA_DIC_NOT_FOUND);
        }

        return new MtOperateResult<List<DataDictionaryModel>>(DATA_DIC_CACHE.get(key));
    }

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            appendCache();
        } catch (MtException e) {
            logger.warn("初始化消息枚举字典过程发生异常：" + e.getMessage(), e);
        }
    }

    /**
     * 初始化枚举数据缓存
     * 
     * @throws MtException
     */
    private void appendCache() throws MtException {
        List<DataDictionaryModel> list = null;

        list = EnumUtil.getDataDictionaryModelFromEnum(PxShopStatusEnum.class);
        DATA_DIC_CACHE.put("PxShopStatusEnum", list);

        list = EnumUtil.getDataDictionaryModelFromEnum(PxGoodsHuiyuanEnum.class);
        DATA_DIC_CACHE.put("PxGoodsHuiyuanEnum", list);

        list = EnumUtil.getDataDictionaryModelFromEnum(PxGoodsOrderTypeEnum.class);
        DATA_DIC_CACHE.put("PxGoodsOrderTypeEnum", list);

        list = EnumUtil.getDataDictionaryModelFromEnum(PxGoodsQuanEnum.class);
        DATA_DIC_CACHE.put("PxGoodsQuanEnum", list);

        list = EnumUtil.getDataDictionaryModelFromEnum(PxGoodsTuanEnum.class);
        DATA_DIC_CACHE.put("PxGoodsTuanEnum", list);

        logger.warn("缓存初始化结果： " + DATA_DIC_CACHE);
    }

}
