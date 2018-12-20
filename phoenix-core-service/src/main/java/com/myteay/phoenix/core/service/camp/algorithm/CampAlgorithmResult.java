/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 返回结果
 * 
 * @author Administrator
 * @version $Id: MtOperateResult.java, v 0.1 2015年12月1日 下午4:46:13 Administrator Exp $
 */
public class CampAlgorithmResult<T> implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -1112148186814322176L;

    /** 操作结果码 */
    private CampResultEnum    operateResult;

    /** 操作目标返回对象 */
    private T                 result;

    /** 返回码为 FAILED时，对应的错误参考信息 */
    private String            errorDetail;

    /**
     * default constructor
     */
    public CampAlgorithmResult() {
        this.errorDetail = CampResultEnum.CAMP_SUCCESS.getMessage();
        this.operateResult = CampResultEnum.CAMP_SUCCESS;
    }

    /**
     * constructor for initial instance
     * 
     * @param operateResult     返回结果类型
     * @param operateExResult   扩展信息
     */
    public CampAlgorithmResult(CampResultEnum operateResult) {
        this.errorDetail = operateResult.getMessage();
        this.operateResult = operateResult;
    }

    /**
     * constructor for initial instance
     * 
     * @param operateResult     返回结果类型
     * @param operateExResult   扩展信息
     */
    public CampAlgorithmResult(T result) {
        this.errorDetail = CampResultEnum.CAMP_SUCCESS.getMessage();
        this.operateResult = CampResultEnum.CAMP_SUCCESS;
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
    public CampAlgorithmResult(CampResultEnum operateResult, T result, String errorDetail) {
        this.errorDetail = errorDetail;
        this.operateResult = operateResult;
        this.result = result;
    }

    /**
     * Getter method for property <tt>operateResult</tt>.
     * 
     * @return property value of operateResult
     */
    public CampResultEnum getOperateResult() {
        return operateResult;
    }

    /**
     * Setter method for property <tt>operateResult</tt>.
     * 
     * @param operateResult value to be assigned to property operateResult
     */
    public void setOperateResult(CampResultEnum operateResult) {
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
