/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.mobile.dataobject;

import java.io.Serializable;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 手机端商品管理数据模型
 * 
 * @author min.weixm
 * @version $Id: PxMobileGoodsDO.java, v 0.1 Aug 16, 2018 12:54:45 AM min.weixm Exp $
 */
public class PxMobileGoodsDO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -415496978863316324L;

    /** 店铺名称 */
    private String            shopName;

    /** 店铺地址 */
    private String            shopAddress;

    /** 联系电话 */
    private String            shopTel;

    /** 店铺客服 */
    private String            waiterName;

    /** 店铺状态 */
    private String            shopStatus;

    /** 店铺创建时间 */
    private String            gmtShopCreated;

    /** 店铺过期时间 */
    private String            gmtShopExpired;

    /** 商品ID */
    private String            goodsId;

    /** 店铺ID */
    private String            shopId;

    /** 商品状态 */
    private String            goodsStatus;

    /** 商品摘要图片 */
    private String            goodsImage;

    /** 商品标题 */
    private String            goodsTitle;

    /** 套餐信息类别 */
    private String            goodsDesc;

    /** 商品售价 */
    private String            goodsPrice;

    /** 商品原价 */
    private String            goodsCommPrice;

    /** 营业时间 */
    private String            goodsOnlineTime;

    /** 订购类型 */
    private String            orderType;

    /** 是否支持会员 */
    private String            isHuiyuan;

    /** 是否支持优惠券 */
    private String            isQuan;

    /** 是否支持团购 */
    private String            isTuan;

    /** 当前销量 */
    private String            goodsSellAmount;

    /** 商品过期时间 */
    private String            gmtGoodsExpired;

    /** 商品创建时间 */
    private String            gmtGoodsCreated;

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
    public String getShopStatus() {
        return shopStatus;
    }

    /**
     * Setter method for property <tt>shopStatus</tt>.
     * 
     * @param shopStatus value to be assigned to property shopStatus
     */
    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    /**
     * Getter method for property <tt>gmtShopCreated</tt>.
     * 
     * @return property value of gmtShopCreated
     */
    public String getGmtShopCreated() {
        return gmtShopCreated;
    }

    /**
     * Setter method for property <tt>gmtShopCreated</tt>.
     * 
     * @param gmtShopCreated value to be assigned to property gmtShopCreated
     */
    public void setGmtShopCreated(String gmtShopCreated) {
        this.gmtShopCreated = gmtShopCreated;
    }

    /**
     * Getter method for property <tt>gmtShopExpired</tt>.
     * 
     * @return property value of gmtShopExpired
     */
    public String getGmtShopExpired() {
        return gmtShopExpired;
    }

    /**
     * Setter method for property <tt>gmtShopExpired</tt>.
     * 
     * @param gmtShopExpired value to be assigned to property gmtShopExpired
     */
    public void setGmtShopExpired(String gmtShopExpired) {
        this.gmtShopExpired = gmtShopExpired;
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
     * Getter method for property <tt>goodsStatus</tt>.
     * 
     * @return property value of goodsStatus
     */
    public String getGoodsStatus() {
        return goodsStatus;
    }

    /**
     * Setter method for property <tt>goodsStatus</tt>.
     * 
     * @param goodsStatus value to be assigned to property goodsStatus
     */
    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    /**
     * Getter method for property <tt>goodsImage</tt>.
     * 
     * @return property value of goodsImage
     */
    public String getGoodsImage() {
        return goodsImage;
    }

    /**
     * Setter method for property <tt>goodsImage</tt>.
     * 
     * @param goodsImage value to be assigned to property goodsImage
     */
    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    /**
     * Getter method for property <tt>goodsTitle</tt>.
     * 
     * @return property value of goodsTitle
     */
    public String getGoodsTitle() {
        return goodsTitle;
    }

    /**
     * Setter method for property <tt>goodsTitle</tt>.
     * 
     * @param goodsTitle value to be assigned to property goodsTitle
     */
    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    /**
     * Getter method for property <tt>goodsDesc</tt>.
     * 
     * @return property value of goodsDesc
     */
    public String getGoodsDesc() {
        return goodsDesc;
    }

    /**
     * Setter method for property <tt>goodsDesc</tt>.
     * 
     * @param goodsDesc value to be assigned to property goodsDesc
     */
    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    /**
     * Getter method for property <tt>goodsPrice</tt>.
     * 
     * @return property value of goodsPrice
     */
    public String getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * Setter method for property <tt>goodsPrice</tt>.
     * 
     * @param goodsPrice value to be assigned to property goodsPrice
     */
    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * Getter method for property <tt>goodsCommPrice</tt>.
     * 
     * @return property value of goodsCommPrice
     */
    public String getGoodsCommPrice() {
        return goodsCommPrice;
    }

    /**
     * Setter method for property <tt>goodsCommPrice</tt>.
     * 
     * @param goodsCommPrice value to be assigned to property goodsCommPrice
     */
    public void setGoodsCommPrice(String goodsCommPrice) {
        this.goodsCommPrice = goodsCommPrice;
    }

    /**
     * Getter method for property <tt>goodsOnlineTime</tt>.
     * 
     * @return property value of goodsOnlineTime
     */
    public String getGoodsOnlineTime() {
        return goodsOnlineTime;
    }

    /**
     * Setter method for property <tt>goodsOnlineTime</tt>.
     * 
     * @param goodsOnlineTime value to be assigned to property goodsOnlineTime
     */
    public void setGoodsOnlineTime(String goodsOnlineTime) {
        this.goodsOnlineTime = goodsOnlineTime;
    }

    /**
     * Getter method for property <tt>orderType</tt>.
     * 
     * @return property value of orderType
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * Setter method for property <tt>orderType</tt>.
     * 
     * @param orderType value to be assigned to property orderType
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * Getter method for property <tt>isHuiyuan</tt>.
     * 
     * @return property value of isHuiyuan
     */
    public String getIsHuiyuan() {
        return isHuiyuan;
    }

    /**
     * Setter method for property <tt>isHuiyuan</tt>.
     * 
     * @param isHuiyuan value to be assigned to property isHuiyuan
     */
    public void setIsHuiyuan(String isHuiyuan) {
        this.isHuiyuan = isHuiyuan;
    }

    /**
     * Getter method for property <tt>isQuan</tt>.
     * 
     * @return property value of isQuan
     */
    public String getIsQuan() {
        return isQuan;
    }

    /**
     * Setter method for property <tt>isQuan</tt>.
     * 
     * @param isQuan value to be assigned to property isQuan
     */
    public void setIsQuan(String isQuan) {
        this.isQuan = isQuan;
    }

    /**
     * Getter method for property <tt>isTuan</tt>.
     * 
     * @return property value of isTuan
     */
    public String getIsTuan() {
        return isTuan;
    }

    /**
     * Setter method for property <tt>isTuan</tt>.
     * 
     * @param isTuan value to be assigned to property isTuan
     */
    public void setIsTuan(String isTuan) {
        this.isTuan = isTuan;
    }

    /**
     * Getter method for property <tt>goodsSellAmount</tt>.
     * 
     * @return property value of goodsSellAmount
     */
    public String getGoodsSellAmount() {
        return goodsSellAmount;
    }

    /**
     * Setter method for property <tt>goodsSellAmount</tt>.
     * 
     * @param goodsSellAmount value to be assigned to property goodsSellAmount
     */
    public void setGoodsSellAmount(String goodsSellAmount) {
        this.goodsSellAmount = goodsSellAmount;
    }

    /**
     * Getter method for property <tt>gmtGoodsExpired</tt>.
     * 
     * @return property value of gmtGoodsExpired
     */
    public String getGmtGoodsExpired() {
        return gmtGoodsExpired;
    }

    /**
     * Setter method for property <tt>gmtGoodsExpired</tt>.
     * 
     * @param gmtGoodsExpired value to be assigned to property gmtGoodsExpired
     */
    public void setGmtGoodsExpired(String gmtGoodsExpired) {
        this.gmtGoodsExpired = gmtGoodsExpired;
    }

    /**
     * Getter method for property <tt>gmtGoodsCreated</tt>.
     * 
     * @return property value of gmtGoodsCreated
     */
    public String getGmtGoodsCreated() {
        return gmtGoodsCreated;
    }

    /**
     * Setter method for property <tt>gmtGoodsCreated</tt>.
     * 
     * @param gmtGoodsCreated value to be assigned to property gmtGoodsCreated
     */
    public void setGmtGoodsCreated(String gmtGoodsCreated) {
        this.gmtGoodsCreated = gmtGoodsCreated;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
