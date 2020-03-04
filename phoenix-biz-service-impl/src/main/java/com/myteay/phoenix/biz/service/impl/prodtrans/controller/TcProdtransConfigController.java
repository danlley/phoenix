/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2015-2020 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.prodtrans.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.phoenix.common.service.prodtrans.integration.TcProdtransConfigMngIntg;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.tc.prodtrans.core.model.TcPtsUserShopProdConfigModel;

/**
 * 会员店铺产品账配置管理
 * 
 * @author min.weixm
 * @version $Id: TcProdtransConfigController.java, v 0.1 Feb 8, 2020 9:59:00 PM min.weixm Exp $
 */
@RestController
@RequestMapping(value = "tiancan/api/prodtrans/manage")
public class TcProdtransConfigController {

    /** 日志 */
    public static final Logger       logger = Logger.getLogger(TcProdtransConfigController.class);

    /** 会员店铺产品账配置信息操作组件 */
    @Autowired
    private TcProdtransConfigMngIntg tcProdtransConfigMngIntg;

    /**
     * 保存会员店铺产品账配置信息
     * 
     * @param tcPtsUserShopProdConfigModel
     * @return
     */
    @RequestMapping(value = "/save/", method = { RequestMethod.POST })
    public MtOperateResult<String> savePtsUserShopProdConfig(@RequestBody TcPtsUserShopProdConfigModel tcPtsUserShopProdConfigModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始保存会员店铺产品账配置信息 tcPtsUserShopProdConfigModel=" + tcPtsUserShopProdConfigModel);
        }

        return tcProdtransConfigMngIntg.savePtsUserShopProdConfig(tcPtsUserShopProdConfigModel);
    }

    /**
     * 查询当前店铺的会员店铺产品账配置信息
     * 
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/query/shop/{shopId}", method = { RequestMethod.POST })
    public MtOperateResult<List<TcPtsUserShopProdConfigModel>> queryPtsUserShopProdConfigByShop(@PathVariable String shopId) {

        if (logger.isInfoEnabled()) {
            logger.info("查询会员店铺产品账配置信息列表 shopId=" + shopId);
        }

        return tcProdtransConfigMngIntg.queryPtsUserShopProdConfigByShop(shopId);
    }

    /**
     * 修改当前店铺的会员店铺产品账配置信息
     * 
     * @param tcPtsUserShopProdConfigModel
     */
    @RequestMapping(value = "/modify/", method = { RequestMethod.POST })
    public MtOperateResult<String> modifyPtsUserShopProdConfig(@RequestBody TcPtsUserShopProdConfigModel tcPtsUserShopProdConfigModel) {

        if (logger.isInfoEnabled()) {
            logger.info("开始修改会员店铺产品账配置信息 tcPtsUserShopProdConfigModel=" + tcPtsUserShopProdConfigModel);
        }

        return tcProdtransConfigMngIntg.modifyPtsUserShopProdConfig(tcPtsUserShopProdConfigModel);
    }

    /**
     * 移除当前店铺的会员店铺产品账配置信息
     * 
     * @param prodtransId
     * @param shopId
     */
    @RequestMapping(value = "/remove/shop/{shopId}/prodtrans/{prodtransId}", method = { RequestMethod.POST })
    public MtOperateResult<String> removePtsUserShopProdConfigById(@PathVariable String prodtransId, @PathVariable String shopId) {

        if (logger.isInfoEnabled()) {
            logger.info("开始删除会员店铺产品账配置信息 prodtransId=" + prodtransId + " shopId=" + shopId);
        }

        return tcProdtransConfigMngIntg.removePtsUserShopProdConfigById(prodtransId, shopId);
    }

}
