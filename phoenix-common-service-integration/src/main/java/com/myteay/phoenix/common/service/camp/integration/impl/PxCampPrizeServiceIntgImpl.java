/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.camp.integration.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.myteay.phoenix.common.service.camp.integration.PxCampPrizeServiceIntg;
import com.myteay.phoenix.common.util.HttpClientUtil;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.camp.CampCashierModel;

/**
 * 
 * @author min.weixm
 * @version $Id: PxCampPrizeServiceIntg.java, v 0.1 2019年7月6日 下午5:08:39 min.weixm Exp $
 */
public class PxCampPrizeServiceIntgImpl implements PxCampPrizeServiceIntg {

    /** 
     * @see com.tc.trade.common.service.integration.client.PxCampPrizeServiceIntg#consumePrize(java.lang.String)
     */
    @Override
    public MtOperateResult<String> consumePrize(String orderNo) {
        String url = "http://192.168.0.103:40041/tiancan/api/promocore/prize/service/consume/prize/" + orderNo;
        String result = HttpClientUtil.insureResponseGet(url);
        return (MtOperateResult<String>) JSON.parseObject(result, new TypeReference<MtOperateResult<String>>() {
        });
    }

    /** 
     * @see com.tc.trade.common.service.integration.client.PxCampPrizeServiceIntg#doCamp(com.myteay.phoenix.core.model.PxGoodsOrderModel)
     */
    @Override
    public CampCashierModel doCamp(PxGoodsOrderModel pxGoodsOrderModel) {
        String url = "http://192.168.0.103:40041/tiancan/api/promocore/prize/service/camp/prize/";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(pxGoodsOrderModel));
        MtOperateResult<CampCashierModel> obj = (MtOperateResult<CampCashierModel>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<CampCashierModel>>() {
            });

        if (obj == null) {
            return null;
        }

        return obj.getResult();
    }

}
