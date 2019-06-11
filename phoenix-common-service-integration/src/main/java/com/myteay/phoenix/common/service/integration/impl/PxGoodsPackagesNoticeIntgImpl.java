/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.integration.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.myteay.phoenix.common.service.integration.PxGoodsPackagesNoticeIntg;
import com.myteay.phoenix.common.util.HttpClientUtil;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesNoticeModel;

/**
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesNoticeIntgImpl.java, v 0.1 Jun 11, 2019 11:42:41 PM min.weixm Exp $
 */
public class PxGoodsPackagesNoticeIntgImpl implements PxGoodsPackagesNoticeIntg {

    /** 
     * @see com.myteay.phoenix.common.service.integration.PxGoodsPackagesNoticeIntg#queryPackagesNoticeListByGoodsId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<PxGoodsPackagesNoticeModel>> queryPackagesNoticeListByGoodsId(String goodsId) {
        String url = "http://localhost:40051/myteay/api/phoenix/admin/manage/pkgs/notice/list/notice/" + goodsId;
        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<PxGoodsPackagesNoticeModel>> obj = (MtOperateResult<List<PxGoodsPackagesNoticeModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<PxGoodsPackagesNoticeModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.integration.PxGoodsPackagesNoticeIntg#manageGoodsPackagesNotice(com.myteay.phoenix.core.model.manage.PxGoodsPackagesNoticeModel)
     */
    @Override
    public MtOperateResult<PxGoodsPackagesNoticeModel> manageGoodsPackagesNotice(PxGoodsPackagesNoticeModel pxGoodsPackagesNoticeModel) {
        String url = "http://localhost:40051/myteay/api/phoenix/admin/manage/pkgs/notice/manage";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(pxGoodsPackagesNoticeModel));
        MtOperateResult<PxGoodsPackagesNoticeModel> obj = (MtOperateResult<PxGoodsPackagesNoticeModel>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<PxGoodsPackagesNoticeModel>>() {
            });
        return obj;
    }

}
