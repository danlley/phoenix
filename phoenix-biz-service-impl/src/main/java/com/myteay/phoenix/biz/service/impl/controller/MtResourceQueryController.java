/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.controller;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.phoenix.common.util.enums.PxOperationTypeEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesImageModel;
import com.myteay.phoenix.core.service.manage.component.PxGoodsPackagesImageComponent;

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
    public static final Logger            logger = Logger.getLogger(MtResourceQueryController.class);

    /** 套餐详情图片管理 */
    @Autowired
    private PxGoodsPackagesImageComponent pxGoodsPackagesImageComponent;

    //    private final ResourceLoader resourceLoader;

    /** 套餐详情图片管理 */
    @Autowired
    private Environment                   env;

    @RequestMapping(value = "/{key}", method = { RequestMethod.GET })
    public File queryImageByKey(@PathVariable("key") String key) {

        if (logger.isInfoEnabled()) {
            logger.info("开始查询图片信息  key=" + key);
        }

        String path = env.getProperty("myteay.phoenix.images.path");

        PxGoodsPackagesImageModel pxGoodsPackagesImageModel = new PxGoodsPackagesImageModel();
        pxGoodsPackagesImageModel.setOperationType(PxOperationTypeEnum.PX_QUERY_ONE);
        pxGoodsPackagesImageModel.setImageId(key);
        String image = null;
        try {
            MtOperateResult<PxGoodsPackagesImageModel> innerResult = pxGoodsPackagesImageComponent.manageGoodsPackagesImage(
                pxGoodsPackagesImageModel);
            image = (innerResult.getResult() == null ? null : innerResult.getResult().getImage());
        } catch (Exception e) {
            logger.warn("保存套餐详情图片信息发生异常" + e.getMessage(), e);
        }

        if (StringUtils.isBlank(image)) {
            logger.warn("当前key未找到相应的图片信息 key" + key);
            return null;
        }

        return new File(path, image);

    }

    //    @RequestMapping(method = RequestMethod.GET, value = "/{filename:.+}")
    //    @ResponseBody
    //    public ResponseEntity<?> getFile(@PathVariable String filename) {
    //
    //        try {
    //            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(env.getProperty("myteay.phoenix.images.path"), filename)
    //                .toString()));
    //        } catch (Exception e) {
    //            return ResponseEntity.notFound().build();
    //        }
    //    }

    /**
     * Setter method for property <tt>pxGoodsPackagesImageComponent</tt>.
     * 
     * @param pxGoodsPackagesImageComponent value to be assigned to property pxGoodsPackagesImageComponent
     */
    public void setPxGoodsPackagesImageComponent(PxGoodsPackagesImageComponent pxGoodsPackagesImageComponent) {
        this.pxGoodsPackagesImageComponent = pxGoodsPackagesImageComponent;
    }

    /**
     * Setter method for property <tt>env</tt>.
     * 
     * @param env value to be assigned to property env
     */
    public void setEnv(Environment env) {
        this.env = env;
    }

}
