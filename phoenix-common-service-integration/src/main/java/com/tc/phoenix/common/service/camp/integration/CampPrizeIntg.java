/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.tc.phoenix.common.service.camp.integration;

import java.util.List;

import com.tc.dbcenter.common.orm.model.PxGoodsModel;
import com.tc.phoenix.common.util.MtOperateResult;
import com.tc.promocore.common.orm.model.CampPrizeModel;

/**
 * 
 * @author min.weixm
 * @version $Id: CampPrizeIntg.java, v 0.1 Jun 19, 2019 3:07:40 PM min.weixm Exp $
 */
public interface CampPrizeIntg {

    /**
     * 通过活动ID查询当前活动下的所有奖品信息
     * 
     * @param campId
     * @return
     */
    public MtOperateResult<List<CampPrizeModel<PxGoodsModel>>> queryPrizeListByCampId(String campId);

    /**
     * 店内营销活动奖品管理服务（增、删、改、单条查询）
     * 
     * @param campPrizeModel
     * @return
     */
    public MtOperateResult<CampPrizeModel<PxGoodsModel>> manageCampPrize(CampPrizeModel<PxGoodsModel> campPrizeModel);
}
