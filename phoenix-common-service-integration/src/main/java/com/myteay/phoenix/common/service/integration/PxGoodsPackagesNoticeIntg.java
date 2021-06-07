/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.integration;

import java.util.List;

import com.tc.dbcenter.common.orm.model.PxGoodsPackagesNoticeModel;
import com.tc.phoenix.common.util.MtOperateResult;

/**
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesNoticeIntg.java, v 0.1 Jun 11, 2019 11:42:15 PM min.weixm Exp $
 */
public interface PxGoodsPackagesNoticeIntg {

    /**
     * 通过温馨提醒摘要ID查询店铺下的所有温馨提醒摘要列表
     * 
     * @param goodsId
     * @return
     */
    MtOperateResult<List<PxGoodsPackagesNoticeModel>> queryPackagesNoticeListByGoodsId(String goodsId);

    /**
     * 温馨提醒摘要管理服务（增、删、改、单条查询）
     * 
     * @param pxGoodsPackagesNoticeModel
     * @return
     */
    MtOperateResult<PxGoodsPackagesNoticeModel> manageGoodsPackagesNotice(PxGoodsPackagesNoticeModel pxGoodsPackagesNoticeModel);
}
