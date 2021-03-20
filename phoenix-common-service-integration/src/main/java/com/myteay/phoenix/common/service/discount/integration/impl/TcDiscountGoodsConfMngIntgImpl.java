/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2015-2020 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.discount.integration.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.myteay.phoenix.common.service.discount.integration.TcDiscountGoodsConfMngIntg;
import com.myteay.phoenix.common.util.HttpClientUtil;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.tc.discount.core.model.TcAvaliableDiscountGoodsConfigModel;
import com.tc.discount.core.model.TcDiscountGoodsConfigModel;

/**
 * 商品折扣管理客户端集成
 * 
 * @author min.weixm
 * @version $Id: TcDiscountGoodsConfMngIntgImpl.java, v 0.1 Feb 20, 2020 2:57:31 PM min.weixm Exp $
 */
public class TcDiscountGoodsConfMngIntgImpl implements TcDiscountGoodsConfMngIntg {

    /** 环境变量 */
    @Autowired
    private Environment env;

    /** 
     * @see com.myteay.phoenix.common.service.discount.integration.TcDiscountGoodsConfMngIntg#queryDiscountGoodsConfAll(java.lang.String)
     */
    @Override
    public MtOperateResult<List<TcDiscountGoodsConfigModel>> queryDiscountGoodsConfAll(String shopId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.discount.path.prefix");
        String url = pathPrefix + "/tiancan/api/discount/manage/query/shop/" + shopId;

        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<TcDiscountGoodsConfigModel>> obj = (MtOperateResult<List<TcDiscountGoodsConfigModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<TcDiscountGoodsConfigModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.discount.integration.TcDiscountGoodsConfMngIntg#saveDiscountGoodsConfig(com.tc.discount.core.model.TcDiscountGoodsConfigModel)
     */
    @Override
    public MtOperateResult<String> saveDiscountGoodsConfig(TcDiscountGoodsConfigModel tcDiscountGoodsConfigModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.discount.path.prefix");
        String url = pathPrefix + "/tiancan/api/discount/manage/opt/save/";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(tcDiscountGoodsConfigModel));
        MtOperateResult<String> obj = (MtOperateResult<String>) JSON.parseObject(result, new TypeReference<MtOperateResult<String>>() {
        });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.discount.integration.TcDiscountGoodsConfMngIntg#modifyDiscountGoodsConfById(com.tc.discount.core.model.TcDiscountGoodsConfigModel)
     */
    @Override
    public MtOperateResult<TcDiscountGoodsConfigModel> modifyDiscountGoodsConfById(TcDiscountGoodsConfigModel tcDiscountGoodsConfigModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.discount.path.prefix");
        String url = pathPrefix + "/tiancan/api/discount/manage/opt/modify/";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(tcDiscountGoodsConfigModel));
        MtOperateResult<TcDiscountGoodsConfigModel> obj = (MtOperateResult<TcDiscountGoodsConfigModel>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<TcDiscountGoodsConfigModel>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.discount.integration.TcDiscountGoodsConfMngIntg#removeDiscountGoodsConfById(com.tc.discount.core.model.TcDiscountGoodsConfigModel)
     */
    @Override
    public MtOperateResult<String> removeDiscountGoodsConfById(TcDiscountGoodsConfigModel tcDiscountGoodsConfigModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.discount.path.prefix");
        String url = pathPrefix + "/tiancan/api/discount/manage/opt/del/";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(tcDiscountGoodsConfigModel));
        MtOperateResult<String> obj = (MtOperateResult<String>) JSON.parseObject(result, new TypeReference<MtOperateResult<String>>() {
        });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.discount.integration.TcDiscountGoodsConfMngIntg#queryDiscountGoodsConfAllFromCache(java.lang.String)
     */
    @Override
    public MtOperateResult<TcAvaliableDiscountGoodsConfigModel> queryDiscountGoodsConfAllFromCache(String shopId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.discount.path.prefix");
        String url = pathPrefix + "/tiancan/api/discount/manage/query/cache/shop/" + shopId;

        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<TcAvaliableDiscountGoodsConfigModel> obj = (MtOperateResult<TcAvaliableDiscountGoodsConfigModel>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<TcAvaliableDiscountGoodsConfigModel>>() {
            });
        return obj;
    }

    //    /** 
    //     * @see com.myteay.phoenix.common.service.discount.integration.TcDiscountGoodsConfMngIntg#aplayDiscount(com.tc.discount.core.model.TcDiscountGoodsOrderModel)
    //     */
    //    @Override
    //    public MtOperateResult<TcDiscountGoodsOrderModel<PxGoodsOrderModel, Money, PxGoodsOrderOutModel>> aplayDiscount(@RequestBody TcDiscountGoodsOrderModel<PxGoodsOrderModel, Money, PxGoodsOrderOutModel> orderModel) {
    //        String pathPrefix = env.getProperty("tiancan.phoenix.discount.path.prefix");
    //        String url = pathPrefix + "/tiancan/api/discount/cashier/query/price/";
    //        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(orderModel));
    //        MtOperateResult<TcDiscountGoodsOrderModel<PxGoodsOrderModel, Money, PxGoodsOrderOutModel>> obj = (MtOperateResult<TcDiscountGoodsOrderModel<PxGoodsOrderModel, Money, PxGoodsOrderOutModel>>) JSON
    //            .parseObject(result, new TypeReference<MtOperateResult<TcDiscountGoodsOrderModel<PxGoodsOrderModel, Money, PxGoodsOrderOutModel>>>() {
    //            });
    //        return obj;
    //    }

}
