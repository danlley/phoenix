/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.trade.integration.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.myteay.phoenix.common.service.trade.integration.PxTradeServiceIntg;
import com.myteay.phoenix.common.util.HttpClientUtil;
import com.myteay.phoenix.common.util.enums.PxOrderStatusEnum;
import com.myteay.phoenix.common.util.enums.PxPayTypeEnum;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;

/**
 * 交易订单管理客户端
 * 
 * @author min.weixm
 * @version $Id: PxTradeServiceIntgImpl.java, v 0.1 Jul 6, 2019 9:29:16 PM min.weixm Exp $
 */
public class PxTradeServiceIntgImpl implements PxTradeServiceIntg {

    /** 环境变量 */
    @Autowired
    private Environment env;

    /** 
     * @see com.myteay.phoenix.common.service.trade.integration.PxTradeServiceIntg#createGoodsOrderOut(com.myteay.phoenix.core.model.PxGoodsOrderModel)
     */
    @Override
    public MtOperateResult<PxGoodsOrderModel> createGoodsOrderOut(PxGoodsOrderModel pxGoodsOrderModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.dbcenter.path.prefix");
        String url = pathPrefix + "/myteay/api/phoenix/cashier/manage/base/order/";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(pxGoodsOrderModel));
        return (MtOperateResult<PxGoodsOrderModel>) JSON.parseObject(result, new TypeReference<MtOperateResult<PxGoodsOrderModel>>() {
        });

    }

    /** 
     * @see com.myteay.phoenix.common.service.trade.integration.PxTradeServiceIntg#modifyGoodsOrderOut(java.lang.String, com.myteay.phoenix.common.util.enums.PxPayTypeEnum, com.myteay.phoenix.common.util.enums.PxOrderStatusEnum)
     */
    @Override
    public MtOperateResult<String> modifyGoodsOrderOut(String orderNo, PxPayTypeEnum pxPayTypeEnum, PxOrderStatusEnum pxOrderStatusEnum) {
        String pathPrefix = env.getProperty("tiancan.phoenix.dbcenter.path.prefix");
        String url = pathPrefix + "/myteay/api/phoenix/cashier/manage/base/order/change" + "?orderNo=" + orderNo + "&pxOrderStatusEnum=" + pxOrderStatusEnum
                + "&pxPayTypeEnum=" + pxPayTypeEnum;
        String result = HttpClientUtil.insureResponsePost(url, null);
        return (MtOperateResult<String>) JSON.parseObject(result, new TypeReference<MtOperateResult<String>>() {
        });
    }

}
