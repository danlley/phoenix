/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.mobile;

import java.io.Serializable;
import java.util.List;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 商品温馨提醒模型
 * 
 * @author danlley
 * @version $Id: PxMobileGoodsNotice.java, v 0.1 Aug 19, 2018 7:53:10 PM danlley Exp $
 */
public class PxMobileGoodsNoticeModel implements Serializable {

    /** serialVersionUID */
    private static final long            serialVersionUID = -8530072694111632619L;

    /** 温馨提醒分类ID */
    private String                       packagesNoticeId;

    /** 温馨提醒分类名称 */
    private String                       packagesNoticeName;

    /** 子提醒 */
    private List<PxMobileGoodsSubNoticeModel> subNotices;

    /**
     * Getter method for property <tt>subNotices</tt>.
     * 
     * @return property value of subNotices
     */
    public List<PxMobileGoodsSubNoticeModel> getSubNotices() {
        return subNotices;
    }

    /**
     * Setter method for property <tt>subNotices</tt>.
     * 
     * @param subNotices value to be assigned to property subNotices
     */
    public void setSubNotices(List<PxMobileGoodsSubNoticeModel> subNotices) {
        this.subNotices = subNotices;
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
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
