/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.camp.integration;

import java.util.List;

import com.tc.phoenix.common.util.MtOperateResult;
import com.tc.promocore.common.orm.model.CampBaseModel;

/**
 * 
 * @author min.weixm
 * @version $Id: CampBaseIntg.java, v 0.1 Jun 19, 2019 2:30:14 PM min.weixm Exp $
 */
public interface CampBaseIntg {

    /**
     * 通过店铺ID查询店铺下的所有店内营销活动
     * 
     * @param shopId
     * @return
     */
    public MtOperateResult<List<CampBaseModel>> queryAllCampBaseByShopId(String shopId);

    /**
     * 店内营销活动管理服务（增、删、改、单条查询）
     * 
     * @param campBaseModel
     * @return
     */
    public MtOperateResult<CampBaseModel> manageCampBase(CampBaseModel campBaseModel);

    /**
     * 商品下架及商品发布
     * 
     * @param campBaseModel
     * @return
     */
    public MtOperateResult<CampBaseModel> changeCampBaseStatus(CampBaseModel campBaseModel);
}
