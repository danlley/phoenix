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
 * 温馨提醒摘要模型
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesNoticeModel.java, v 0.1 Aug 5, 2018 8:59:17 PM min.weixm Exp $
 */
public class PxGoodsPackagesNoticeModel implements Serializable {

    /** serialVersionUID */
    private static final long   serialVersionUID = -425189418960036884L;

    /** 温馨提醒摘要ID */
    private String              packagesNoticeId;

    /** 商品ID */
    private String              goodsId;

    /** 温馨提醒名称 */
    private String              packagesNoticeName;

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
     * Getter method for property <tt>goodsId</tt>.
     * 
     * @return property value of goodsId
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * Setter method for property <tt>goodsId</tt>.
     * 
     * @param goodsId value to be assigned to property goodsId
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * Getter method for property <tt>packagesNoticeName</tt>.
     * 
     * @return property value of packagesNoticeName
     */
    public String getPackagesNoticeName() {
        return packagesNoticeName;
    }

    /**
     * Setter method for property <tt>packagesNoticeName</tt>.
     * 
     * @param packagesNoticeName value to be assigned to property packagesNoticeName
     */
    public void setPackagesNoticeName(String packagesNoticeName) {
        this.packagesNoticeName = packagesNoticeName;
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
