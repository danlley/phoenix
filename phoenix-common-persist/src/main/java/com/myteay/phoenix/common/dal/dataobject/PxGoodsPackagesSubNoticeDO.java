/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 温馨提醒子项管理数据模型
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesSubNoticeDO.java, v 0.1 Aug 5, 2018 10:18:00 PM min.weixm Exp $
 */
public class PxGoodsPackagesSubNoticeDO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 6816708836762013426L;

    /** 温馨提醒子项ID */
    private String            packagesSuNoticeId;

    /** 温馨提醒摘要ID */
    private String            packagesNoticeId;

    /** 温馨提醒子项内容 */
    private String            subNoticeDetail;

    /** 创建时间 */
    private Date              gmtCreated;

    /** 修改时间 */
    private Date              gmtModified;

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
