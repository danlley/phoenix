/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.tc.phoenix.common.service.integration.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.tc.dbcenter.common.orm.model.PxGoodsPackagesImageModel;
import com.tc.phoenix.common.service.integration.PxGoodsPackagesImageIntg;
import com.tc.phoenix.common.util.HttpClientUtil;
import com.tc.phoenix.common.util.MtOperateResult;

/**
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesImageIntgImpl.java, v 0.1 Jun 11, 2019 11:19:49 PM min.weixm Exp $
 */
public class PxGoodsPackagesImageIntgImpl implements PxGoodsPackagesImageIntg {

    /** 环境变量 */
    @Autowired
    private Environment env;

    /** 
     * @see com.tc.phoenix.common.service.integration.PxGoodsPackagesImageIntg#queryPackagesImageListByGoodsId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<PxGoodsPackagesImageModel>> queryPackagesImageListByGoodsId(String goodsId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.dbcenter.path.prefix");
        String url = pathPrefix + "/myteay/api/phoenix/admin/manage/pkgs/image/list/goods/" + goodsId;
        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<PxGoodsPackagesImageModel>> obj = (MtOperateResult<List<PxGoodsPackagesImageModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<PxGoodsPackagesImageModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.tc.phoenix.common.service.integration.PxGoodsPackagesImageIntg#manageGoodsPackagesImage(com.myteay.phoenix.core.model.manage.PxGoodsPackagesImageModel)
     */
    @Override
    public MtOperateResult<PxGoodsPackagesImageModel> manageGoodsPackagesImage(PxGoodsPackagesImageModel pxGoodsPackagesImageModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.dbcenter.path.prefix");
        String url = pathPrefix + "/myteay/api/phoenix/admin/manage/pkgs/image/manage/goods/";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(pxGoodsPackagesImageModel));
        MtOperateResult<PxGoodsPackagesImageModel> obj = (MtOperateResult<PxGoodsPackagesImageModel>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<PxGoodsPackagesImageModel>>() {
            });
        return obj;
    }

}
