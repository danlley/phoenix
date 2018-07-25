/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxShopModel;
import com.myteay.phoenix.core.model.manage.repository.PxShopRepository;
import com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent;

/**
 * 后台一般性简单业务管理组件
 * 
 * @author min.weixm
 * @version $Id: PxCommonManageComponentImpl.java, v 0.1 Jul 24, 2018 12:19:29 PM min.weixm Exp $
 */
public class PxCommonManageComponentImpl implements PxCommonManageComponent {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(PxCommonManageComponentImpl.class);

    /** 店铺管理仓储 */
    private PxShopRepository   pxShopRepository;

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxCommonManageComponent#queryShopAll()
     */
    @Override
    public MtOperateResult<List<PxShopModel>> queryShopAll() {
        List<PxShopModel> list = null;
        MtOperateResult<List<PxShopModel>> result = new MtOperateResult<List<PxShopModel>>();
        try {
            list = pxShopRepository.findAll();
            result.setResult(list);
        } catch (PxManageException e) {
            logger.warn("店铺信息查询发生异常", e);
            result = new MtOperateResult<List<PxShopModel>>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_SHOP_QUERY_FAILD);
        }
        return result;
    }

    /**
     * Setter method for property <tt>pxShopRepository</tt>.
     * 
     * @param pxShopRepository value to be assigned to property pxShopRepository
     */
    public void setPxShopRepository(PxShopRepository pxShopRepository) {
        this.pxShopRepository = pxShopRepository;
    }

}
