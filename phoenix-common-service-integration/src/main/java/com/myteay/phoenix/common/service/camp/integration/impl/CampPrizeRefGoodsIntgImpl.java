/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.camp.integration.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.myteay.phoenix.common.service.camp.integration.CampPrizeRefGoodsIntg;
import com.myteay.phoenix.common.util.HttpClientUtil;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampPrizeRefGoodsModel;

/**
 * 
 * @author min.weixm
 * @version $Id: CampPrizeRefGoodsIntgImpl.java, v 0.1 Jun 19, 2019 3:48:07 PM min.weixm Exp $
 */
public class CampPrizeRefGoodsIntgImpl implements CampPrizeRefGoodsIntg {

    /** 
     * @see com.myteay.phoenix.common.service.camp.integration.CampPrizeRefGoodsIntg#queryPrizeRefGoodsByPrizeId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<CampPrizeRefGoodsModel>> queryPrizeRefGoodsByPrizeId(String prizeId) {
        String url = "http://localhost:40041/myteay/api/phoenix/camp/manage/prize/ref/list/" + prizeId;
        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<CampPrizeRefGoodsModel>> obj = (MtOperateResult<List<CampPrizeRefGoodsModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<CampPrizeRefGoodsModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.camp.integration.CampPrizeRefGoodsIntg#managePrizeGoodsRefList(java.lang.String, java.util.List)
     */
    @Override
    public MtOperateResult<List<CampPrizeRefGoodsModel>> managePrizeGoodsRefList(String prizeId, List<CampPrizeRefGoodsModel> campPrizeRefGoodsModelList) {
        String url = "http://localhost:40041/myteay/api/phoenix/camp/manage/prize/ref/manage/" + prizeId;
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(campPrizeRefGoodsModelList));
        MtOperateResult<List<CampPrizeRefGoodsModel>> obj = (MtOperateResult<List<CampPrizeRefGoodsModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<CampPrizeRefGoodsModel>>>() {
            });
        return obj;
    }

}
