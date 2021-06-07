/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.provider.integration;

import com.tc.phoenix.common.util.MtOperateResult;
import com.tc.provider.orm.model.TcProviderProductOutModel;

/**
 * 进货清单管理
 * 
 * @author min.weixm
 * @version $Id: TcProviderProductOrderOutIntg.java, v 0.1 Nov 21, 2019 1:20:35 PM min.weixm Exp $
 */
public interface TcProviderProductOrderOutIntg {
    /**
     * 原材料配置信息管理
     * 
     * @param tcProviderProductModel
     * @return
     */
    public MtOperateResult<TcProviderProductOutModel> manageProviderProductOrderOutInfo(TcProviderProductOutModel tcProviderProductOutModel);
}
