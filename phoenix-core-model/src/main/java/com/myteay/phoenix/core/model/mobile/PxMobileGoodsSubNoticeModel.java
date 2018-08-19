/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.mobile;

import java.io.Serializable;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 手机端商品温馨提醒子提醒模型
 * 
 * @author danlley
 * @version $Id: PxMobileGoodsSubNotice.java, v 0.1 Aug 19, 2018 8:26:32 PM danlley Exp $
 */
public class PxMobileGoodsSubNoticeModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 8384485283123079468L;

    /** 子提醒ID */
    private String            packagesSuNoticeId;

    /** 子提醒内容 */
    private String            subNoticeDetail;

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
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }

}
