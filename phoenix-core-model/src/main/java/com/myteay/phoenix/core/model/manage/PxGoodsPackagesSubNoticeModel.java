/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;
import com.myteay.phoenix.common.util.enums.PxOperationTypeEnum;

/**
 * 温馨提醒子项模型
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesSubNoticeModel.java, v 0.1 Aug 5, 2018 10:43:19 PM min.weixm Exp $
 */
public class PxGoodsPackagesSubNoticeModel implements Serializable {

    /** serialVersionUID */
    private static final long   serialVersionUID = 7075021155505788724L;

    /** 温馨提醒子项ID */
    private String              packagesSuNoticeId;

    /** 温馨提醒摘要ID */
    private String              packagesNoticeId;

    /** 温馨提醒子项内容 */
    private String              subNoticeDetail;

    /** 创建时间 */
    private Date                gmtCreated;

    /** 修改时间 */
    private Date                gmtModified;

    /** 操作类型 */
    private PxOperationTypeEnum operationType;

    /**
     * Getter method for property <tt>operationType</tt>.
     * 
     * @return property value of operationType
     */
    public PxOperationTypeEnum getOperationType() {
        return operationType;
    }

    /**
     * Setter method for property <tt>operationType</tt>.
     * 
     * @param operationType value to be assigned to property operationType
     */
    public void setOperationType(PxOperationTypeEnum operationType) {
        this.operationType = operationType;
    }

    /**
     * Getter method for property <tt>packagesSuNoticeId</tt>.
     * 
     * @return property value of packagesSuNoticeId
     */
    public String getPackagesSuNoticeId() {
        return packagesSuNoticeId;
    }

    /**
     * Setter method for property <tt>packagesSuNoticeId</tt>.
     * 
     * @param packagesSuNoticeId value to be assigned to property packagesSuNoticeId
     */
    public void setPackagesSuNoticeId(String packagesSuNoticeId) {
        this.packagesSuNoticeId = packagesSuNoticeId;
    }

    /**
     * Getter method for property <tt>packagesNoticeId</tt>.
     * 
     * @return property value of packagesNoticeId
     */
    public String getPackagesNoticeId() {
        return packagesNoticeId;
    }

    /**
     * Setter method for property <tt>packagesNoticeId</tt>.
     * 
     * @param packagesNoticeId value to be assigned to property packagesNoticeId
     */
    public void setPackagesNoticeId(String packagesNoticeId) {
        this.packagesNoticeId = packagesNoticeId;
    }

    /**
     * Getter method for property <tt>subNoticeDetail</tt>.
     * 
     * @return property value of subNoticeDetail
     */
    public String getSubNoticeDetail() {
        return subNoticeDetail;
    }

    /**
     * Setter method for property <tt>subNoticeDetail</tt>.
     * 
     * @param subNoticeDetail value to be assigned to property subNoticeDetail
     */
    public void setSubNoticeDetail(String subNoticeDetail) {
        this.subNoticeDetail = subNoticeDetail;
    }

    /**
     * Getter method for property <tt>gmtCreated</tt>.
     * 
     * @return property value of gmtCreated
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * Setter method for property <tt>gmtCreated</tt>.
     * 
     * @param gmtCreated value to be assigned to property gmtCreated
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * Getter method for property <tt>gmtModified</tt>.
     * 
     * @return property value of gmtModified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * Setter method for property <tt>gmtModified</tt>.
     * 
     * @param gmtModified value to be assigned to property gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }

}
