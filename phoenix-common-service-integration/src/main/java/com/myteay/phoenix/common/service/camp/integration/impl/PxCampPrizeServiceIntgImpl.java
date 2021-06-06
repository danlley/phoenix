/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.camp.integration.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.myteay.phoenix.common.service.camp.integration.PxCampPrizeServiceIntg;
import com.myteay.phoenix.common.util.HttpClientUtil;
import com.myteay.phoenix.common.util.MtOperateResult;
import com.tc.dbcenter.common.orm.model.PxGoodsModel;
import com.tc.promocore.common.orm.model.CampCashierModel;
import com.tc.trade.orm.model.TcTradeModel;

/**
 * 优惠券服务客户端
 * 
 * @author min.weixm
 * @version $Id: PxCampPrizeServiceIntg.java, v 0.1 2019年7月6日 下午5:08:39 min.weixm Exp $
 */
public class PxCampPrizeServiceIntgImpl implements PxCampPrizeServiceIntg {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(PxCampPrizeServiceIntgImpl.class);

    /** 环境变量 */
    @Autowired
    private Environment        env;

    /** 
     * @see com.myteay.phoenix.common.service.camp.integration.PxCampPrizeServiceIntg#doCamp(com.tc.trade.orm.model.TcTradeModel)
     */
    @Override
    public MtOperateResult<CampCashierModel<PxGoodsModel>> doCamp(TcTradeModel tcTradeModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.promocore.path.prefix");
        String url = pathPrefix + "/tiancan/api/promocore/prize/ref/camp/trade/ii/do";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(tcTradeModel));
        MtOperateResult<CampCashierModel<PxGoodsModel>> obj = (MtOperateResult<CampCashierModel<PxGoodsModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<CampCashierModel<PxGoodsModel>>>() {
            });

        return obj;
    }

}
