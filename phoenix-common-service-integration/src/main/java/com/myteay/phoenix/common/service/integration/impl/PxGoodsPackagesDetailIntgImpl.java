/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.integration.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.myteay.phoenix.common.service.integration.PxGoodsPackagesDetailIntg;
import com.myteay.phoenix.common.util.HttpClientUtil;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel;

/**
 * 套餐包详情
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesDetailIntgImpl.java, v 0.1 Jun 11, 2019 11:03:25 PM min.weixm Exp $
 */
public class PxGoodsPackagesDetailIntgImpl implements PxGoodsPackagesDetailIntg {

    /** 
     * @see com.myteay.phoenix.common.service.integration.PxGoodsPackagesDetailIntg#queryPackagesDetailListAll()
     */
    @Override
    public MtOperateResult<List<PxGoodsPackagesDetailModel>> queryPackagesDetailListAll() {
        String url = "http://192.168.0.101:40051/myteay/api/phoenix/admin/manage/pkgs/all";

        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<PxGoodsPackagesDetailModel>> obj = (MtOperateResult<List<PxGoodsPackagesDetailModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<PxGoodsPackagesDetailModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.integration.PxGoodsPackagesDetailIntg#queryPackagesDetailListByGoodsId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<PxGoodsPackagesDetailModel>> queryPackagesDetailListByGoodsId(String goodsId) {
        String url = "http://192.168.0.101:40051/myteay/api/phoenix/admin/manage/pkgs/list/goods/" + goodsId;
        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<PxGoodsPackagesDetailModel>> obj = (MtOperateResult<List<PxGoodsPackagesDetailModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<PxGoodsPackagesDetailModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.integration.PxGoodsPackagesDetailIntg#manageGoodsPackagesDetail(com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel)
     */
    @Override
    public MtOperateResult<PxGoodsPackagesDetailModel> manageGoodsPackagesDetail(PxGoodsPackagesDetailModel pxGoodsPackagesDetailModel) {
        String url = "http://192.168.0.101:40051/myteay/api/phoenix/admin/manage/pkgs/manage";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(pxGoodsPackagesDetailModel));
        MtOperateResult<PxGoodsPackagesDetailModel> obj = (MtOperateResult<PxGoodsPackagesDetailModel>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<PxGoodsPackagesDetailModel>>() {
            });
        return obj;
    }

}
