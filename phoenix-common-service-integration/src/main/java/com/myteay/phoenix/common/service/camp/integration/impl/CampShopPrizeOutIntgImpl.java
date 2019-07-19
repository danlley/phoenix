/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.camp.integration.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.myteay.phoenix.common.service.camp.integration.CampShopPrizeOutIntg;
import com.myteay.phoenix.common.util.HttpClientUtil;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel;

/**
 * 营销管理优惠券
 * 
 * @author min.weixm
 * @version $Id: CampShopPrizeOutIntgImpl.java, v 0.1 Jun 19, 2019 3:57:22 PM min.weixm Exp $
 */
public class CampShopPrizeOutIntgImpl implements CampShopPrizeOutIntg {

    /** 环境变量 */
    @Autowired
    private Environment env;

    /** 
     * @see com.myteay.phoenix.common.service.camp.integration.CampShopPrizeOutIntg#queryShopPrizeOutById(java.lang.String)
     */
    @Override
    public MtOperateResult<CampShopPrizeOutModel> queryShopPrizeOutById(String prizeOutId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.promocore.path.prefix");
        String url = pathPrefix + "/myteay/api/phoenix/camp/prize/out/list/" + prizeOutId;
        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<CampShopPrizeOutModel> obj = (MtOperateResult<CampShopPrizeOutModel>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<CampShopPrizeOutModel>>() {
            });
        return obj;
    }

}
