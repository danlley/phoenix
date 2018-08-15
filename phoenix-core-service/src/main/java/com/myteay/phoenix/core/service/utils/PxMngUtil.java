/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.utils;

import com.myteay.phoenix.common.util.manage.enums.PxGoodsStatusEnum;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;

/**
 * 后台管理组件工具类
 * 
 * @author min.weixm
 * @version $Id: PxMngUtil.java, v 0.1 Aug 15, 2018 10:18:43 PM min.weixm Exp $
 */
public class PxMngUtil {

    /**
     * 检查当前商品是否允许追加、修改套餐包
     * 
     * @param pxGoodsModel
     * @return
     */
    public static boolean isCanDoOperation(PxGoodsModel pxGoodsModel) {
        if (pxGoodsModel == null || pxGoodsModel.getGoodsStatus() == PxGoodsStatusEnum.PX_GOODS_OFFLINE || pxGoodsModel
            .getGoodsStatus() == PxGoodsStatusEnum.PX_GOODS_ONLINE) {
            return false;
        }

        return true;
    }

}
