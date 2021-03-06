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
import com.tc.dbcenter.common.orm.model.PxGoodsPackagesDetailModel;
import com.tc.phoenix.common.service.integration.PxGoodsPackagesDetailIntg;
import com.tc.phoenix.common.util.HttpClientUtil;
import com.tc.phoenix.common.util.MtOperateResult;

/**
 * 套餐包详情
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesDetailIntgImpl.java, v 0.1 Jun 11, 2019 11:03:25 PM min.weixm Exp $
 */
public class PxGoodsPackagesDetailIntgImpl implements PxGoodsPackagesDetailIntg {

    /** 环境变量 */
    @Autowired
    private Environment env;

    /** 
     * @see com.tc.phoenix.common.service.integration.PxGoodsPackagesDetailIntg#queryPackagesDetailListAll()
     */
    @Override
    public MtOperateResult<List<PxGoodsPackagesDetailModel>> queryPackagesDetailListAll() {
        String pathPrefix = env.getProperty("tiancan.phoenix.dbcenter.path.prefix");
        String url = pathPrefix + "/myteay/api/phoenix/admin/manage/pkgs/all";

        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<PxGoodsPackagesDetailModel>> obj = (MtOperateResult<List<PxGoodsPackagesDetailModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<PxGoodsPackagesDetailModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.tc.phoenix.common.service.integration.PxGoodsPackagesDetailIntg#queryPackagesDetailListByGoodsId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<PxGoodsPackagesDetailModel>> queryPackagesDetailListByGoodsId(String goodsId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.dbcenter.path.prefix");
        String url = pathPrefix + "/myteay/api/phoenix/admin/manage/pkgs/list/goods/" + goodsId;
        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<PxGoodsPackagesDetailModel>> obj = (MtOperateResult<List<PxGoodsPackagesDetailModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<PxGoodsPackagesDetailModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.tc.phoenix.common.service.integration.PxGoodsPackagesDetailIntg#manageGoodsPackagesDetail(com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel)
     */
    @Override
    public MtOperateResult<PxGoodsPackagesDetailModel> manageGoodsPackagesDetail(PxGoodsPackagesDetailModel pxGoodsPackagesDetailModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.dbcenter.path.prefix");
        String url = pathPrefix + "/myteay/api/phoenix/admin/manage/pkgs/manage";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(pxGoodsPackagesDetailModel));
        MtOperateResult<PxGoodsPackagesDetailModel> obj = (MtOperateResult<PxGoodsPackagesDetailModel>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<PxGoodsPackagesDetailModel>>() {
            });
        return obj;
    }

}
