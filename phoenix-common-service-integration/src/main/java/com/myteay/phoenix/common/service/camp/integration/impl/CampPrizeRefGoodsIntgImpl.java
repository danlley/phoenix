/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.camp.integration.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.myteay.phoenix.common.service.camp.integration.CampPrizeRefGoodsIntg;
import com.tc.dbcenter.common.orm.model.PxGoodsModel;
import com.tc.phoenix.common.util.HttpClientUtil;
import com.tc.phoenix.common.util.MtOperateResult;
import com.tc.promocore.common.orm.model.CampPrizeRefGoodsModel;

/**
 * 
 * @author min.weixm
 * @version $Id: CampPrizeRefGoodsIntgImpl.java, v 0.1 Jun 19, 2019 3:48:07 PM min.weixm Exp $
 */
public class CampPrizeRefGoodsIntgImpl implements CampPrizeRefGoodsIntg {

    /** 环境变量 */
    @Autowired
    private Environment env;

    /** 
     * @see com.myteay.phoenix.common.service.camp.integration.CampPrizeRefGoodsIntg#queryPrizeRefGoodsByPrizeId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<CampPrizeRefGoodsModel<PxGoodsModel>>> queryPrizeRefGoodsByPrizeId(String prizeId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.promocore.path.prefix");
        String url = pathPrefix + "/myteay/api/phoenix/camp/manage/prize/ref/list/" + prizeId;
        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<CampPrizeRefGoodsModel<PxGoodsModel>>> obj = (MtOperateResult<List<CampPrizeRefGoodsModel<PxGoodsModel>>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<CampPrizeRefGoodsModel<PxGoodsModel>>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.camp.integration.CampPrizeRefGoodsIntg#managePrizeGoodsRefList(java.lang.String, java.util.List)
     */
    @Override
    public MtOperateResult<List<CampPrizeRefGoodsModel<PxGoodsModel>>> managePrizeGoodsRefList(String prizeId,
                                                                                               List<CampPrizeRefGoodsModel<PxGoodsModel>> campPrizeRefGoodsModelList) {
        String pathPrefix = env.getProperty("tiancan.phoenix.promocore.path.prefix");
        String url = pathPrefix + "/myteay/api/phoenix/camp/manage/prize/ref/manage/" + prizeId;
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(campPrizeRefGoodsModelList));
        MtOperateResult<List<CampPrizeRefGoodsModel<PxGoodsModel>>> obj = (MtOperateResult<List<CampPrizeRefGoodsModel<PxGoodsModel>>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<CampPrizeRefGoodsModel<PxGoodsModel>>>>() {
            });
        return obj;
    }

}
