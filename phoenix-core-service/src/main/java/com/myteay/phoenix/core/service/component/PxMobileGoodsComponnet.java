/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.component;

import java.util.List;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.mobile.PxMobileGoodsModel;

/**
 * 手机端商品信息管理组件
 * 
 * @author min.weixm
 * @version $Id: PxMobileGoodsComponnet.java, v 0.1 Aug 17, 2018 1:42:21 AM min.weixm Exp $
 */
public interface PxMobileGoodsComponnet {

    /**
     * 获取下一页商品列表数据
     * 
     * @param pageNo            需要获取的目标页号
     * @param excludeGoodsIds   需要剔除的商品ID
     * @return
     * @throws PxManageException 
     */
    public MtOperateResult<List<PxMobileGoodsModel>> queryNextGoodsList(List<String> excludeGoodsIds) throws PxManageException;

    /**
     * 获取单个商品详情
     * 
     * @param goodsId
     * @return
     * @throws PxManageException 
     */
    public MtOperateResult<PxMobileGoodsModel> queryGoodsDetail(String goodsId) throws PxManageException;
}
