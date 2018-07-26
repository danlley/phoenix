/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.template;

import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.enums.PxOperationTypeEnum;
import com.myteay.phoenix.core.model.MtOperateResult;

/**
 * 后台管理业务处理分流模板
 * 
 * @author min.weixm
 * @version $Id: PxCommonMngTemplateImpl.java, v 0.1 Jul 26, 2018 4:13:36 PM min.weixm Exp $
 * @param <T>
 */
public class PxCommonMngTemplateImpl<T> implements PxCommonMngTemplate<T> {

    /** 
     * @see com.myteay.phoenix.core.service.manage.template.PxCommonMngTemplate#switchOperation(java.lang.Object, com.myteay.phoenix.common.util.enums.PxOperationTypeEnum, com.myteay.phoenix.core.service.manage.template.PxCommonCallback)
     */
    @Override
    public MtOperateResult<T> switchOperation(T t, PxOperationTypeEnum operationType, PxCommonCallback<T> callback) {

        MtOperateResult<T> result = null;

        switch (operationType) {
            case PX_ADD:
                result = callback.addModel(t);
                break;
            case PX_MODIFY:
                result = callback.modifyModel(t);
                break;
            case PX_DELETE:
                result = callback.deleteModel(t);
                break;
            case PX_QUERY_ONE:
                result = callback.querySingleModel(t);
                break;

            default:
                result = new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_TEMPLATE_OP_UNKNOW);
                break;
        }

        return result;
    }

}
