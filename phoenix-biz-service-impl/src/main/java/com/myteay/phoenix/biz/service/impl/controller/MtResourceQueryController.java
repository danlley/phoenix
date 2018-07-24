/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.common.util.model.DataDictionaryModel;
import com.myteay.phoenix.biz.service.impl.MtServiceResult;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.core.model.MtOperateResult;

/**
 * 资源查询控制器
 * 
 * @author min.weixm
 * @version $Id: MtResourceQueryController.java, v 0.1 Jul 2, 2018 11:38:14 AM min.weixm Exp $
 */
@RestController
@RequestMapping(value = "myteay/api/phoenix/web/data")
public class MtResourceQueryController {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(MtResourceQueryController.class);

    @RequestMapping(value = "/{key}", method = { RequestMethod.GET })
    public MtServiceResult<List<DataDictionaryModel>> queryImageByKey(@PathVariable("key") String key) {

        if (logger.isInfoEnabled()) {
            logger.info("开始查询图片信息  key=" + key);
        }

        MtOperateResult<List<DataDictionaryModel>> innerResult = null;
        try {

            if (logger.isInfoEnabled()) {
                logger.info("查询图片结果： key=" + key + " innerResult=" + innerResult);
            }
        } catch (Throwable e) {
            logger.warn("查询图片发生异常" + e.getMessage(), e);
        }
        if (innerResult == null || innerResult.getOperateResult() != MtOperateResultEnum.CAMP_OPERATE_SUCCESS) {
            logger.warn("查询图片过程发生异常： innerResult=" + innerResult);
            return new MtServiceResult<List<DataDictionaryModel>>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_OPERATE_FAILED);
        }

        return new MtServiceResult<List<DataDictionaryModel>>(innerResult.getResult());
    }

}
