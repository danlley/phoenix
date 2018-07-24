/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.phoenix.common.util.exception;

import com.myteay.common.util.tools.ToStringUtil;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;

/**
 * 业务处理异常
 * 
 * @author Administrator
 * @version $Id: MtException.java, v 0.1 2015年11月15日 下午6:30:08 Administrator Exp $
 */
public class PxManageException extends Exception {

    /** serialVersionUID */
    private static final long serialVersionUID = -1698555152104131128L;

    /** 后台配置管理的错误 */
    private String            operateResult;

    /** 后台配置管理的错误详情 */
    private String            operateExResult;

    /** 后台配置管理的错误详情码 */
    private String            operateExCode;

    /**
     * Getter method for property <tt>operateResult</tt>.
     * 
     * @return property value of operateResult
     */
    public String getOperateResult() {
        return operateResult;
    }

    /**
     * Getter method for property <tt>operateExCode</tt>.
     * 
     * @return property value of operateExCode
     */
    public String getOperateExCode() {
        return operateExCode;
    }

    /**
     * Getter method for property <tt>operateExResult</tt>.
     * 
     * @return property value of operateExResult
     */
    public String getOperateExResult() {
        return operateExResult;
    }

    /**
     * MtBizException
     * 
     * @param code              错误内容（取自MtOperateExResultEnum的code）
     * @param operateExResult   取自MtOperateExResultEnum中的message
     */
    public PxManageException(MtOperateResultEnum code, MtOperateExResultEnum operateExResult) {
        super(code.getValue());
        this.operateExResult = operateExResult.getMessage();
        this.operateResult = code.getMessage();
        this.operateExCode = operateExResult.getCode();
    }

    /**
     * MtBizException
     * 
     * @param code              错误内容（取自MtOperateExResultEnum的code）
     * @param operateExResult   取自MtOperateExResultEnum中的message
     */
    public PxManageException(String code, String operateExResult) {
        super(code);
        this.operateExResult = operateExResult;
    }

    /**
     * MtBizException
     * 
     * @param code      错误内容
     * @param e         异常
     */
    public PxManageException(String code, Exception e, String operateExResult) {
        super(code, e);
        this.operateExResult = operateExResult;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}