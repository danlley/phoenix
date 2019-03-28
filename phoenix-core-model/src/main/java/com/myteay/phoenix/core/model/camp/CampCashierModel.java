/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.camp;

import java.io.Serializable;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 收银台订单结果模型
 * 
 * @author danlley
 * @version $Id: CampCashierModel.java, v 0.1 Mar 28, 2019 9:19:01 PM danlley Exp $
 */
public class CampCashierModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1511839648956446753L;

    /** 奖品模型 */
    private CampPrizeModel    campPrizeModel;

    /** 活动基本信息模型 */
    private CampBaseModel     campBaseModel;

    /** 订单编号 */
    private String            orderNo;

    /** 中奖单号 */
    private String            prizeOutId;

    /** 会员ID */
    private String            userId;

    /** 店铺名称 */
    private String            shopName;

    /** 是否抽奖成功 */
    private boolean           isCampSuccess    = false;

    /** 二维码文件名称 */
    private String            qrCodeName;

    /**
     * Getter method for property <tt>qrCodeName</tt>.
     * 
     * @return property value of qrCodeName
     */
    public String getQrCodeName() {
        return qrCodeName;
    }

    /**
     * Setter method for property <tt>qrCodeName</tt>.
     * 
     * @param qrCodeName value to be assigned to property qrCodeName
     */
    public void setQrCodeName(String qrCodeName) {
        this.qrCodeName = qrCodeName;
    }

    /**
     * Getter method for property <tt>isCampSuccess</tt>.
     * 
     * @return property value of isCampSuccess
     */
    public boolean isCampSuccess() {
        return isCampSuccess;
    }

    /**
     * Setter method for property <tt>isCampSuccess</tt>.
     * 
     * @param isCampSuccess value to be assigned to property isCampSuccess
     */
    public void setCampSuccess(boolean isCampSuccess) {
        this.isCampSuccess = isCampSuccess;
    }

    /**
     * Getter method for property <tt>shopName</tt>.
     * 
     * @return property value of shopName
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * Setter method for property <tt>shopName</tt>.
     * 
     * @param shopName value to be assigned to property shopName
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * Getter method for property <tt>campPrizeModel</tt>.
     * 
     * @return property value of campPrizeModel
     */
    public CampPrizeModel getCampPrizeModel() {
        return campPrizeModel;
    }

    /**
     * Setter method for property <tt>campPrizeModel</tt>.
     * 
     * @param campPrizeModel value to be assigned to property campPrizeModel
     */
    public void setCampPrizeModel(CampPrizeModel campPrizeModel) {
        this.campPrizeModel = campPrizeModel;
    }

    /**
     * Getter method for property <tt>campBaseModel</tt>.
     * 
     * @return property value of campBaseModel
     */
    public CampBaseModel getCampBaseModel() {
        return campBaseModel;
    }

    /**
     * Setter method for property <tt>campBaseModel</tt>.
     * 
     * @param campBaseModel value to be assigned to property campBaseModel
     */
    public void setCampBaseModel(CampBaseModel campBaseModel) {
        this.campBaseModel = campBaseModel;
    }

    /**
     * Getter method for property <tt>orderNo</tt>.
     * 
     * @return property value of orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * Setter method for property <tt>orderNo</tt>.
     * 
     * @param orderNo value to be assigned to property orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * Getter method for property <tt>prizeOutId</tt>.
     * 
     * @return property value of prizeOutId
     */
    public String getPrizeOutId() {
        return prizeOutId;
    }

    /**
     * Setter method for property <tt>prizeOutId</tt>.
     * 
     * @param prizeOutId value to be assigned to property prizeOutId
     */
    public void setPrizeOutId(String prizeOutId) {
        this.prizeOutId = prizeOutId;
    }

    /**
     * Getter method for property <tt>userId</tt>.
     * 
     * @return property value of userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Setter method for property <tt>userId</tt>.
     * 
     * @param userId value to be assigned to property userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
