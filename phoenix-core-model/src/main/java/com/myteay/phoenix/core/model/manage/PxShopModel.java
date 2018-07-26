/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;
import com.myteay.phoenix.common.util.enums.PxOperationTypeEnum;
import com.myteay.phoenix.common.util.manage.enums.PxShopStatusEnum;

/**
 * 店铺模型
 * 
 * @author min.weixm
 * @version $Id: PxShopModel.java, v 0.1 Jul 24, 2018 10:25:43 AM min.weixm Exp $
 */
public class PxShopModel implements Serializable {

    /** serialVersionUID */
    private static final long   serialVersionUID = -5828377760866622621L;

    /** 店铺ID */
    private String              shopId;

    /** 店铺名称 */
    private String              shopName;

    /** 店铺地址 */
    private String              shopAddress;

    /** 店铺联系电话 */
    private String              shopTel;

    /** 店铺服务人员 */
    private String              waiterName;

    /** 店铺管理员 */
    private String              ownerName;

    /** 店铺管理员联系电话 */
    private String              ownerPhone;

    /** 店铺管理员身份证 */
    private String              ownerIdcard;

    /** 店铺状态 */
    private PxShopStatusEnum    shopStatus;

    /** 操作类型 */
    private PxOperationTypeEnum operationType;

    /** 过期时间 */
    private Date                gmtExpired;

    /** 创建时间 */
    private Date                gmtCreated;

    /** 修改时间 */
    private Date                gmtModified;

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
     * Getter method for property <tt>ownerName</tt>.
     * 
     * @return property value of ownerName
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Setter method for property <tt>ownerName</tt>.
     * 
     * @param ownerName value to be assigned to property ownerName
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * Getter method for property <tt>ownerPhone</tt>.
     * 
     * @return property value of ownerPhone
     */
    public String getOwnerPhone() {
        return ownerPhone;
    }

    /**
     * Setter method for property <tt>ownerPhone</tt>.
     * 
     * @param ownerPhone value to be assigned to property ownerPhone
     */
    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    /**
     * Getter method for property <tt>ownerIdcard</tt>.
     * 
     * @return property value of ownerIdcard
     */
    public String getOwnerIdcard() {
        return ownerIdcard;
    }

    /**
     * Setter method for property <tt>ownerIdcard</tt>.
     * 
     * @param ownerIdcard value to be assigned to property ownerIdcard
     */
    public void setOwnerIdcard(String ownerIdcard) {
        this.ownerIdcard = ownerIdcard;
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
     * Getter method for property <tt>gmtExpired</tt>.
     * 
     * @return property value of gmtExpired
     */
    public Date getGmtExpired() {
        return gmtExpired;
    }

    /**
     * Setter method for property <tt>gmtExpired</tt>.
     * 
     * @param gmtExpired value to be assigned to property gmtExpired
     */
    public void setGmtExpired(Date gmtExpired) {
        this.gmtExpired = gmtExpired;
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
