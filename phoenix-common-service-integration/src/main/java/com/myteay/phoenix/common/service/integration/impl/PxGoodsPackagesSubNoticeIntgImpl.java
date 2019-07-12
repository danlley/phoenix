/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.integration.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.myteay.phoenix.common.service.integration.PxGoodsPackagesSubNoticeIntg;
import com.myteay.phoenix.common.util.HttpClientUtil;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesSubNoticeModel;

/**
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesSubNoticeIntgImpl.java, v 0.1 Jun 11, 2019 11:56:37 PM min.weixm Exp $
 */
public class PxGoodsPackagesSubNoticeIntgImpl implements PxGoodsPackagesSubNoticeIntg {

    /** 
     * @see com.myteay.phoenix.common.service.integration.PxGoodsPackagesSubNoticeIntg#queryPackagesNoticeListByNoticeId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<PxGoodsPackagesSubNoticeModel>> queryPackagesNoticeListByNoticeId(String packagesNoticeId) {
        String url = "http://192.168.0.101:40051/myteay/api/phoenix/admin/manage/pkgs/sub/notice/list/notice/" + packagesNoticeId;
        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<PxGoodsPackagesSubNoticeModel>> obj = (MtOperateResult<List<PxGoodsPackagesSubNoticeModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<PxGoodsPackagesSubNoticeModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.integration.PxGoodsPackagesSubNoticeIntg#manageSubPackages(com.myteay.phoenix.core.model.manage.PxGoodsPackagesSubNoticeModel)
     */
    @Override
    public MtOperateResult<PxGoodsPackagesSubNoticeModel> manageSubPackages(PxGoodsPackagesSubNoticeModel pxGoodsPackagesSubNoticeModel) {
        String url = "http://192.168.0.101:40051/myteay/api/phoenix/admin/manage/pkgs/sub/notice/manage";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(pxGoodsPackagesSubNoticeModel));
        MtOperateResult<PxGoodsPackagesSubNoticeModel> obj = (MtOperateResult<PxGoodsPackagesSubNoticeModel>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<PxGoodsPackagesSubNoticeModel>>() {
            });
        return obj;
    }

}
