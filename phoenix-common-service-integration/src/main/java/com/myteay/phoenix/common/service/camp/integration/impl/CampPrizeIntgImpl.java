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
import com.myteay.phoenix.common.service.camp.integration.CampPrizeIntg;
import com.myteay.phoenix.common.util.HttpClientUtil;
import com.myteay.phoenix.common.util.MtOperateResult;
import com.tc.dbcenter.common.orm.model.PxGoodsModel;
import com.tc.promocore.common.orm.model.CampPrizeModel;

/**
 * 奖品基本数据维护客户端
 * 
 * @author min.weixm
 * @version $Id: CampPrizeIntgImpl.java, v 0.1 Jun 19, 2019 3:11:29 PM min.weixm Exp $
 */
public class CampPrizeIntgImpl implements CampPrizeIntg {

    /** 环境变量 */
    @Autowired
    private Environment env;

    /** 
     * @see com.myteay.phoenix.common.service.camp.integration.CampPrizeIntg#queryPrizeListByCampId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<CampPrizeModel<PxGoodsModel>>> queryPrizeListByCampId(String campId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.promocore.path.prefix");
        String url = pathPrefix + "/myteay/api/phoenix/camp/manage/prize/list/prize/" + campId;
        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<CampPrizeModel<PxGoodsModel>>> obj = (MtOperateResult<List<CampPrizeModel<PxGoodsModel>>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<CampPrizeModel<PxGoodsModel>>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.camp.integration.CampPrizeIntg#manageCampPrize(com.myteay.phoenix.core.model.camp.CampPrizeModel)
     */
    @Override
    public MtOperateResult<CampPrizeModel<PxGoodsModel>> manageCampPrize(CampPrizeModel<PxGoodsModel> campPrizeModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.promocore.path.prefix");
        String url = pathPrefix + "/myteay/api/phoenix/camp/manage/prize/manage";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(campPrizeModel));
        MtOperateResult<CampPrizeModel<PxGoodsModel>> obj = (MtOperateResult<CampPrizeModel<PxGoodsModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<CampPrizeModel<PxGoodsModel>>>() {
            });
        return obj;
    }

}
