/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.integration.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.myteay.phoenix.common.service.integration.PxGoodsPackagesImageIntg;
import com.myteay.phoenix.common.util.HttpClientUtil;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesImageModel;

/**
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesImageIntgImpl.java, v 0.1 Jun 11, 2019 11:19:49 PM min.weixm Exp $
 */
public class PxGoodsPackagesImageIntgImpl implements PxGoodsPackagesImageIntg {

    /** 
     * @see com.myteay.phoenix.common.service.integration.PxGoodsPackagesImageIntg#queryPackagesImageListByGoodsId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<PxGoodsPackagesImageModel>> queryPackagesImageListByGoodsId(String goodsId) {
        String url = "http://192.168.0.101:40051/myteay/api/phoenix/admin/manage/pkgs/image/list/goods/" + goodsId;
        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<PxGoodsPackagesImageModel>> obj = (MtOperateResult<List<PxGoodsPackagesImageModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<PxGoodsPackagesImageModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.integration.PxGoodsPackagesImageIntg#manageGoodsPackagesImage(com.myteay.phoenix.core.model.manage.PxGoodsPackagesImageModel)
     */
    @Override
    public MtOperateResult<PxGoodsPackagesImageModel> manageGoodsPackagesImage(PxGoodsPackagesImageModel pxGoodsPackagesImageModel) {
        String url = "http://192.168.0.101:40051/myteay/api/phoenix/admin/manage/pkgs/image/manage/goods/";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(pxGoodsPackagesImageModel));
        MtOperateResult<PxGoodsPackagesImageModel> obj = (MtOperateResult<PxGoodsPackagesImageModel>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<PxGoodsPackagesImageModel>>() {
            });
        return obj;
    }

}
