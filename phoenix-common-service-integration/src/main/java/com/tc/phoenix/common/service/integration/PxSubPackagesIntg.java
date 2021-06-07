/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.tc.phoenix.common.service.integration;

import java.util.List;

import com.tc.dbcenter.common.orm.model.PxSubPackagesModel;
import com.tc.phoenix.common.util.MtOperateResult;

/**
 * 
 * @author min.weixm
 * @version $Id: PxSubPackagesIntg.java, v 0.1 Jun 12, 2019 12:13:47 AM min.weixm Exp $
 */
public interface PxSubPackagesIntg {

    /**
     * 通过套餐包ID查询店铺下的所有子套餐列表
     * 
     * @param packagesDetailId
     * @return
     */
    MtOperateResult<List<PxSubPackagesModel>> querySubPackagesByPackagesId(String packagesDetailId);

    /**
     * 子套餐管理服务（增、删、改、单条查询）
     * 
     * @param pxSubPackagesModel
     * @return
     */
    MtOperateResult<PxSubPackagesModel> manageSubPackages(PxSubPackagesModel pxSubPackagesModel);
}
