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
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampPrizeModel;

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
    public MtOperateResult<List<CampPrizeModel>> queryPrizeListByCampId(String campId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.promocore.path.prefix");
        String url = pathPrefix + "/myteay/api/phoenix/camp/manage/prize/list/prize/" + campId;
        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<CampPrizeModel>> obj = (MtOperateResult<List<CampPrizeModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<CampPrizeModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.camp.integration.CampPrizeIntg#manageCampPrize(com.myteay.phoenix.core.model.camp.CampPrizeModel)
     */
    @Override
    public MtOperateResult<CampPrizeModel> manageCampPrize(CampPrizeModel campPrizeModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.promocore.path.prefix");
        String url = pathPrefix + "/myteay/api/phoenix/camp/manage/prize/manage";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(campPrizeModel));
        MtOperateResult<CampPrizeModel> obj = (MtOperateResult<CampPrizeModel>) JSON.parseObject(result, new TypeReference<MtOperateResult<CampPrizeModel>>() {
        });
        return obj;
    }

}
