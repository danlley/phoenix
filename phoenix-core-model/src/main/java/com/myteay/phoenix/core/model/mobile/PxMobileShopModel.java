/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.mobile;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;
import com.myteay.phoenix.common.util.manage.enums.PxShopStatusEnum;

/**
 * 用户手机端展示用的店铺模型
 * 
 * @author min.weixm
 * @version $Id: PxMobileShopModel.java, v 0.1 Aug 16, 2018 7:48:42 PM min.weixm Exp $
 */
public class PxMobileShopModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 5454853806346760398L;

    /** 店铺名称 */
    private String            shopName;

    /** 店铺地址 */
    private String            shopAddress;

    /** 联系电话 */
    private String            shopTel;

    /** 店铺客服 */
    private String            waiterName;

    /** 店铺状态 */
    private PxShopStatusEnum  shopStatus;

    /** 店铺创建时间 */
    private Date              gmtShopCreated;

    /** 店铺过期时间 */
    private Date              gmtShopExpired;

    /** 店铺ID */
    private String            shopId;

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
     * Getter method for property <tt>shopAddress</tt>.
     * 
     * @return property value of shopAddress
     */
    public String getShopAddress() {
        return shopAddress;
    }

    /**
     * Setter method for property <tt>shopAddress</tt>.
     * 
     * @param shopAddress value to be assigned to property shopAddress
     */
    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    /**
     * Getter method for property <tt>shopTel</tt>.
     * 
     * @return property value of shopTel
     */
    public String getShopTel() {
        return shopTel;
    }

    /**
     * Setter method for property <tt>shopTel</tt>.
     * 
     * @param shopTel value to be assigned to property shopTel
     */
    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
    }

    /**
     * Getter method for property <tt>waiterName</tt>.
     * 
     * @return property value of waiterName
     */
    public String getWaiterName() {
        return waiterName;
    }

    /**
     * Setter method for property <tt>waiterName</tt>.
     * 
     * @param waiterName value to be assigned to property waiterName
     */
    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    /**
     * Getter method for property <tt>shopStatus</tt>.
     * 
     * @return property value of shopStatus
     */
    public PxShopStatusEnum getShopStatus() {
        return shopStatus;
    }

    /**
     * Setter method for property <tt>shopStatus</tt>.
     * 
     * @param shopStatus value to be assigned to property shopStatus
     */
    public void setShopStatus(PxShopStatusEnum shopStatus) {
        this.shopStatus = shopStatus;
    }

    /**
     * Getter method for property <tt>gmtShopCreated</tt>.
     * 
     * @return property value of gmtShopCreated
     */
    public Date getGmtShopCreated() {
        return gmtShopCreated;
    }

    /**
     * Setter method for property <tt>gmtShopCreated</tt>.
     * 
     * @param gmtShopCreated value to be assigned to property gmtShopCreated
     */
    public void setGmtShopCreated(Date gmtShopCreated) {
        this.gmtShopCreated = gmtShopCreated;
    }

    /**
     * Getter method for property <tt>gmtShopExpired</tt>.
     * 
     * @return property value of gmtShopExpired
     */
    public Date getGmtShopExpired() {
        return gmtShopExpired;
    }

    /**
     * Setter method for property <tt>gmtShopExpired</tt>.
     * 
     * @param gmtShopExpired value to be assigned to property gmtShopExpired
     */
    public void setGmtShopExpired(Date gmtShopExpired) {
        this.gmtShopExpired = gmtShopExpired;
    }

    /**
     * Getter method for property <tt>shopId</tt>.
     * 
     * @return property value of shopId
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * Setter method for property <tt>shopId</tt>.
     * 
     * @param shopId value to be assigned to property shopId
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
