/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component;

import java.util.List;

import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsCostCfgAdvModel;

/**
 * 商品成本管理组件
 * 
 * @author danlley
 * @version $Id: PxGoodsCostCfgComponent.java, v 0.1 May 7, 2019 1:20:22 PM danlley Exp $
 */
public interface PxGoodsCostCfgComponent {

    /**
     * 修改商品成本信息
     * 
     * @param pxGoodsCostCfgAdvModel
     * @return
     * @throws PxManageException 
     */
    public MtOperateResult<PxGoodsCostCfgAdvModel> manageGoodsCostCfgInfo(PxGoodsCostCfgAdvModel pxGoodsCostCfgAdvModel);

    /**
     * 查询所有商品成本信息
     * 
     * @return
     * @throws PxManageException 
     */
    public MtOperateResult<List<PxGoodsCostCfgAdvModel>> queryAllGoodsCostCfg() throws PxManageException;

}
