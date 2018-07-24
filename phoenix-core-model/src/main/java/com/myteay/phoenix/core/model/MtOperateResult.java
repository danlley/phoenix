/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.phoenix.core.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;

/**
 * 返回结果
 * 
 * @author Administrator
 * @version $Id: MtOperateResult.java, v 0.1 2015年12月1日 下午4:46:13 Administrator Exp $
 */
public class MtOperateResult<T> implements Serializable {

    /** serialVersionUID */
    private static final long     serialVersionUID = -1112148186814322176L;

    /** 操作结果码 */
    private MtOperateResultEnum   operateResult;

    /** 操作结果扩展码 */
    private MtOperateExResultEnum operateExResult;

    /** 操作目标返回对象 */
    private T                     result;

    /** 返回码为 FAILED时，对应的错误参考信息 */
    private String                errorDetail;

    /** 返回码为 FAILED时，对应的错误参考信息 */
    private String                errorCode;

    /**
     * default constructor
     */
    public MtOperateResult() {
        this.errorDetail = MtOperateExResultEnum.CAMP_OPERATE_SUCCESS.getMessage();
        this.errorCode = MtOperateExResultEnum.CAMP_OPERATE_SUCCESS.getCode();
        this.operateResult = MtOperateResultEnum.CAMP_OPERATE_SUCCESS;
        this.operateExResult = MtOperateExResultEnum.CAMP_OPERATE_SUCCESS;
    }

    /**
     * constructor for initial instance
     * 
     * @param operateResult     返回结果类型
     * @param operateExResult   扩展信息
     */
    public MtOperateResult(MtOperateResultEnum operateResult, MtOperateExResultEnum operateExResult) {
        this.errorDetail = operateExResult.getMessage();
        this.errorCode = operateExResult.getCode();
        this.operateResult = operateResult;
        this.operateExResult = operateExResult;
    }

    /**
     * constructor for initial instance
     * 
     * @param operateResult     返回结果类型
     * @param operateExResult   扩展信息
     */
    public MtOperateResult(T result) {
        this.errorDetail = MtOperateExResultEnum.CAMP_OPERATE_SUCCESS.getMessage();
        this.errorCode = MtOperateExResultEnum.CAMP_OPERATE_SUCCESS.getCode();
        this.operateResult = MtOperateResultEnum.CAMP_OPERATE_SUCCESS;
        this.operateExResult = MtOperateExResultEnum.CAMP_OPERATE_SUCCESS;
        this.result = result;
    }

    /**
     * constructor for initial instance
     * 
     * @param operateResult     返回结果类型
     * @param result            执行结果
     * @param errorDetail       错误详情
     * @param operateExResult   扩展信息
     */
    public MtOperateResult(MtOperateResultEnum operateResult, T result, String errorDetail, MtOperateExResultEnum operateExResult) {
        this.errorDetail = errorDetail;
        this.operateResult = operateResult;
        this.result = result;
        this.operateExResult = operateExResult;
    }

    /**
     * Getter method for property <tt>errorCode</tt>.
     * 
     * @return property value of errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Setter method for property <tt>errorCode</tt>.
     * 
     * @param errorCode value to be assigned to property errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Getter method for property <tt>operateExResult</tt>.
     * 
     * @return property value of operateExResult
     */
    public MtOperateExResultEnum getOperateExResult() {
        return operateExResult;
    }

    /**
     * Setter method for property <tt>operateExResult</tt>.
     * 
     * @param operateExResult value to be assigned to property operateExResult
     */
    public void setOperateExResult(MtOperateExResultEnum operateExResult) {
        this.operateExResult = operateExResult;
    }

    /**
     * Getter method for property <tt>operateResult</tt>.
     * 
     * @return property value of operateResult
     */
    public MtOperateResultEnum getOperateResult() {
        return operateResult;
    }

    /**
     * Setter method for property <tt>operateResult</tt>.
     * 
     * @param operateResult value to be assigned to property operateResult
     */
    public void setOperateResult(MtOperateResultEnum operateResult) {
        this.operateResult = operateResult;
    }

    /**
     * Getter method for property <tt>result</tt>.
     * 
     * @return property value of result
     */
    public T getResult() {
        return result;
    }

    /**
     * Setter method for property <tt>result</tt>.
     * 
     * @param result value to be assigned to property result
     */
    public void setResult(T result) {
        this.result = result;
    }

    /**
     * Getter method for property <tt>errorDetail</tt>.
     * 
     * @return property value of errorDetail
     */
    public String getErrorDetail() {
        return errorDetail;
    }

    /**
     * Setter method for property <tt>errorDetail</tt>.
     * 
     * @param errorDetail value to be assigned to property errorDetail
     */
    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
