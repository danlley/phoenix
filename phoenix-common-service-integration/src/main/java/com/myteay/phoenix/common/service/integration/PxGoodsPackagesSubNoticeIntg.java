/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.integration;

import java.util.List;

import com.tc.dbcenter.common.orm.model.PxGoodsPackagesSubNoticeModel;
import com.tc.phoenix.common.util.MtOperateResult;

/**
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesSubNoticeIntg.java, v 0.1 Jun 11, 2019 11:56:16 PM min.weixm Exp $
 */
public interface PxGoodsPackagesSubNoticeIntg {

    /**
     * 通过温馨提醒摘要ID查询店铺下的所有温馨提醒摘要列表
     * 
     * @param packagesNoticeId
     * @return
     */
    MtOperateResult<List<PxGoodsPackagesSubNoticeModel>> queryPackagesNoticeListByNoticeId(String packagesNoticeId);

    /**
     * 温馨提醒子项管理服务（增、删、改、单条查询）
     * 
     * @param pxGoodsPackagesSubNoticeModel
     * @return
     */
    MtOperateResult<PxGoodsPackagesSubNoticeModel> manageSubPackages(PxGoodsPackagesSubNoticeModel pxGoodsPackagesSubNoticeModel);
}
