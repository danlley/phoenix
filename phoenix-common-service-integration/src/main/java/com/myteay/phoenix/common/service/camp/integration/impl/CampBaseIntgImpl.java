/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.camp.integration.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.myteay.phoenix.common.service.camp.integration.CampBaseIntg;
import com.myteay.phoenix.common.util.HttpClientUtil;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampBaseModel;

/**
 * 
 * @author min.weixm
 * @version $Id: CampBaseIntgImpl.java, v 0.1 Jun 19, 2019 2:33:25 PM min.weixm Exp $
 */
public class CampBaseIntgImpl implements CampBaseIntg {

    /** 
     * @see com.myteay.phoenix.common.service.camp.integration.CampBaseIntg#queryAllCampBaseByShopId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<CampBaseModel>> queryAllCampBaseByShopId(String shopId) {
        String url = "http://localhost:40041/myteay/api/phoenix/camp/manage/base/list/shop/" + shopId;
        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<CampBaseModel>> obj = (MtOperateResult<List<CampBaseModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<CampBaseModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.camp.integration.CampBaseIntg#manageCampBase(com.myteay.phoenix.core.model.camp.CampBaseModel)
     */
    @Override
    public MtOperateResult<CampBaseModel> manageCampBase(CampBaseModel campBaseModel) {
        String url = "http://localhost:40041/myteay/api/phoenix/camp/manage/base/manage";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(campBaseModel));
        MtOperateResult<CampBaseModel> obj = (MtOperateResult<CampBaseModel>) JSON.parseObject(result, new TypeReference<MtOperateResult<CampBaseModel>>() {
        });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.camp.integration.CampBaseIntg#changeCampBaseStatus(com.myteay.phoenix.core.model.camp.CampBaseModel)
     */
    @Override
    public MtOperateResult<CampBaseModel> changeCampBaseStatus(CampBaseModel campBaseModel) {
        String url = "http://localhost:40041/myteay/api/phoenix/camp/manage/base/status/";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(campBaseModel));
        MtOperateResult<CampBaseModel> obj = (MtOperateResult<CampBaseModel>) JSON.parseObject(result, new TypeReference<MtOperateResult<CampBaseModel>>() {
        });
        return obj;
    }

}
