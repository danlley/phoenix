/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.camp.integration.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.myteay.phoenix.common.service.camp.integration.CampShopPrizeOutIntg;
import com.myteay.phoenix.common.util.HttpClientUtil;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel;

/**
 * 
 * @author min.weixm
 * @version $Id: CampShopPrizeOutIntgImpl.java, v 0.1 Jun 19, 2019 3:57:22 PM min.weixm Exp $
 */
public class CampShopPrizeOutIntgImpl implements CampShopPrizeOutIntg {

    /** 
     * @see com.myteay.phoenix.common.service.camp.integration.CampShopPrizeOutIntg#queryShopPrizeOutById(java.lang.String)
     */
    @Override
    public MtOperateResult<CampShopPrizeOutModel> queryShopPrizeOutById(String prizeOutId) {
        String url = "http://localhost:40041/myteay/api/phoenix/camp/prize/out/list/" + prizeOutId;
        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<CampShopPrizeOutModel> obj = (MtOperateResult<CampShopPrizeOutModel>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<CampShopPrizeOutModel>>() {
            });
        return obj;
    }

}
