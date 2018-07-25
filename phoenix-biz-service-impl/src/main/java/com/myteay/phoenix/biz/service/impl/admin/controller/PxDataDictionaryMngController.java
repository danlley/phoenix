/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.admin.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.common.util.model.DataDictionaryModel;
import com.myteay.phoenix.biz.service.impl.MtServiceResult;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.service.component.PxDataDictionaryComponent;

/**
 * 数据字典查询
 * 
 * @author min.weixm
 * @version $Id: PxDataDictionaryMngController.java, v 0.1 Feb 8, 2018 9:59:00 PM min.weixm Exp $
 */
@RestController
@RequestMapping(value = "myteay/api/phoenix/admin/manage/dic")
public class PxDataDictionaryMngController {

    /** 日志 */
    public static final Logger        logger = Logger.getLogger(PxDataDictionaryMngController.class);

    /** 数据字典管理组件 */
    @Autowired
    private PxDataDictionaryComponent pxDataDictionaryComponent;

    @RequestMapping(value = "/{key}", method = { RequestMethod.GET })
    public MtServiceResult<List<DataDictionaryModel>> queryDataDictionaryByKey(@PathVariable("key") String key) {

        if (logger.isInfoEnabled()) {
            logger.info("开始查询数据字典配置信息  key=" + key);
        }

        MtServiceResult<List<DataDictionaryModel>> result = null;
        try {
            MtOperateResult<List<DataDictionaryModel>> innerResult = pxDataDictionaryComponent.queryDataDictionaryByKey(key);
            result = new MtServiceResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
            if (logger.isInfoEnabled()) {
                logger.info("查询数据字典结果： key=" + key + " innerResult=" + innerResult);
            }
        } catch (Throwable e) {
            logger.warn("查询数据字典发生异常" + e.getMessage(), e);
            result = new MtServiceResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * Setter method for property <tt>pxDataDictionaryComponent</tt>.
     * 
     * @param pxDataDictionaryComponent value to be assigned to property pxDataDictionaryComponent
     */
    public void setPxDataDictionaryComponent(PxDataDictionaryComponent pxDataDictionaryComponent) {
        this.pxDataDictionaryComponent = pxDataDictionaryComponent;
    }

}
