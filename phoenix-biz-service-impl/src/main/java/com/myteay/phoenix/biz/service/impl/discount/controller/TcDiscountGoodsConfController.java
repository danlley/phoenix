/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2015-2020 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.discount.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myteay.phoenix.common.service.discount.integration.TcDiscountGoodsConfMngIntg;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.tc.discount.core.model.TcAvaliableDiscountGoodsConfigModel;
import com.tc.discount.core.model.TcDiscountGoodsConfigModel;

/**
 * 店铺商品折扣配置管理
 * 
 * @author min.weixm
 * @version $Id: TcDiscountGoodsConfController.java, v 0.1 Feb 8, 2020 9:59:00 PM min.weixm Exp $
 */
@RestController
@RequestMapping(value = "tiancan/api/discount/manage")
public class TcDiscountGoodsConfController {

    /** 日志 */
    public static final Logger         logger = Logger.getLogger(TcDiscountGoodsConfController.class);

    /** 店铺商品折扣管理组件 */
    @Autowired
    private TcDiscountGoodsConfMngIntg tcDiscountGoodsConfMngIntg;

    /**
     * 查询店铺下所的折扣商品配置信息
     * 
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/query/shop/{shopId}", method = { RequestMethod.GET })
    public MtOperateResult<List<TcDiscountGoodsConfigModel>> queryDiscountGoodsConfAll(@PathVariable String shopId) {
        return tcDiscountGoodsConfMngIntg.queryDiscountGoodsConfAll(shopId);
    }

    /**
     * 从缓存中查询当前店铺下的所有商品折扣配置信息，并对配置信息以会员为维度进行归类
     * 
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/query/cache/shop/{shopId}", method = { RequestMethod.GET })
    public MtOperateResult<TcAvaliableDiscountGoodsConfigModel> queryDiscountGoodsConfAllFromCache(@PathVariable String shopId) {
        return tcDiscountGoodsConfMngIntg.queryDiscountGoodsConfAllFromCache(shopId);
    }

    /**
     * 保存商品折扣配置信息
     * 
     * @param tcDiscountGoodsConfigModel
     * @return
     */
    @RequestMapping(value = "/opt/save/", method = { RequestMethod.POST })
    public MtOperateResult<String> saveDiscountGoodsConfig(@RequestBody TcDiscountGoodsConfigModel tcDiscountGoodsConfigModel) {
        return tcDiscountGoodsConfMngIntg.saveDiscountGoodsConfig(tcDiscountGoodsConfigModel);
    }

    /**
     * 修改商品折扣配置信息
     * 
     * @param tcDiscountGoodsConfigModel
     * @return
     */
    @RequestMapping(value = "/opt/modify/", method = { RequestMethod.POST })
    public MtOperateResult<TcDiscountGoodsConfigModel> modifyDiscountGoodsConfById(@RequestBody TcDiscountGoodsConfigModel tcDiscountGoodsConfigModel) {
        return tcDiscountGoodsConfMngIntg.modifyDiscountGoodsConfById(tcDiscountGoodsConfigModel);
    }

    /**
     * 删除商品折扣配置信息
     * 
     * @param tcDiscountGoodsConfigModel
     */
    @RequestMapping(value = "/opt/del/", method = { RequestMethod.POST })
    public MtOperateResult<String> removeDiscountGoodsConfById(@RequestBody TcDiscountGoodsConfigModel tcDiscountGoodsConfigModel) {
        return tcDiscountGoodsConfMngIntg.removeDiscountGoodsConfById(tcDiscountGoodsConfigModel);
    }

}
