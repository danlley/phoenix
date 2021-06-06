/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.integration.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.myteay.phoenix.common.service.integration.PxSubPackagesIntg;
import com.myteay.phoenix.common.util.HttpClientUtil;
import com.myteay.phoenix.common.util.MtOperateResult;
import com.tc.dbcenter.common.orm.model.PxSubPackagesModel;

/**
 * 
 * @author min.weixm
 * @version $Id: PxSubPackagesIntgImpl.java, v 0.1 Jun 12, 2019 12:14:03 AM min.weixm Exp $
 */
public class PxSubPackagesIntgImpl implements PxSubPackagesIntg {

    /** 环境变量 */
    @Autowired
    private Environment env;

    /** 
     * @see com.myteay.phoenix.common.service.integration.PxSubPackagesIntg#querySubPackagesByPackagesId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<PxSubPackagesModel>> querySubPackagesByPackagesId(String packagesDetailId) {
        String pathPrefix = env.getProperty("tiancan.phoenix.dbcenter.path.prefix");
        String url = pathPrefix + "/myteay/api/phoenix/admin/manage/sub/pkgs/list/sub/packages/" + packagesDetailId;

        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<PxSubPackagesModel>> obj = (MtOperateResult<List<PxSubPackagesModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<PxSubPackagesModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.integration.PxSubPackagesIntg#manageSubPackages(com.myteay.phoenix.core.model.manage.PxSubPackagesModel)
     */
    @Override
    public MtOperateResult<PxSubPackagesModel> manageSubPackages(PxSubPackagesModel pxSubPackagesModel) {
        String pathPrefix = env.getProperty("tiancan.phoenix.dbcenter.path.prefix");
        String url = pathPrefix + "/myteay/api/phoenix/admin/manage/sub/pkgs/manage";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(pxSubPackagesModel));
        MtOperateResult<PxSubPackagesModel> obj = (MtOperateResult<PxSubPackagesModel>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<PxSubPackagesModel>>() {
            });
        return obj;
    }

}
