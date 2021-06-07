/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2021 All Rights Reserved.
 */
package com.tc.phoenix.biz.service.impl.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tc.ccopass.logger.Logger;
import com.tc.ccopass.logger.LoggerFactory;
import com.tc.dbcenter.common.orm.model.PxSubPackagesModel;
import com.tc.phoenix.common.service.integration.PxSubPackagesIntg;
import com.tc.phoenix.common.util.MtOperateResult;
import com.tc.phoenix.common.util.enums.MtOperateExResultEnum;
import com.tc.phoenix.common.util.enums.MtOperateResultEnum;
import com.tc.phoenix.common.util.log.LoggerNames;

/**
 * 子套餐管理
 * 
 * @author min.weixm
 * @version $Id: PxSubPackagesController.java, v 0.1 Jul 28, 2018 11:51:31 AM min.weixm Exp $
 */
@RestController
@RequestMapping(value = "myteay/api/phoenix/admin/manage/sub/pkgs")
public class PxSubPackagesController {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(LoggerNames.PX_MNG);

    /** 后台一般性简单业务管理组件 */
    @Autowired
    private PxSubPackagesIntg   pxSubPackagesIntg;

    /**
     * 通过套餐包ID查询店铺下的所有子套餐列表
     * 
     * @param shopId    
     * @return
     */
    @RequestMapping(value = "/list/sub/packages/{packagesDetailId}", method = { RequestMethod.GET })
    public MtOperateResult<List<PxSubPackagesModel>> queryGoodsByShopId(@PathVariable String packagesDetailId) {
        MtOperateResult<List<PxSubPackagesModel>> result = null;

        MtOperateResult<List<PxSubPackagesModel>> componentResult = null;
        try {
            componentResult = pxSubPackagesIntg.querySubPackagesByPackagesId(packagesDetailId);
            result = new MtOperateResult<>(componentResult.getOperateResult(), componentResult.getOperateExResult());
            result.setResult(componentResult.getResult());
        } catch (Exception e) {
            logger.warn("查询子套餐信息发生未知异常 " + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

    /**
     * 子套餐管理服务（增、删、改、单条查询）
     * 
     * @param pxShopModel
     * @return
     */
    @RequestMapping(value = "/manage", method = { RequestMethod.POST })
    public MtOperateResult<PxSubPackagesModel> manageGoods(@RequestBody PxSubPackagesModel pxSubPackagesModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始保存子套餐信息 pxSubPackagesModel=" + pxSubPackagesModel);
        }
        MtOperateResult<PxSubPackagesModel> result = null;
        try {
            MtOperateResult<PxSubPackagesModel> innerResult = pxSubPackagesIntg.manageSubPackages(pxSubPackagesModel);
            result = new MtOperateResult<>(innerResult.getOperateResult(), innerResult.getOperateExResult());
            result.setResult(innerResult.getResult());
        } catch (Exception e) {
            logger.warn("保存子套餐信息发生异常" + e.getMessage(), e);
            result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_UNKONW, MtOperateExResultEnum.CAMP_UNKNOW_ERR);
        }

        return result;
    }

}
