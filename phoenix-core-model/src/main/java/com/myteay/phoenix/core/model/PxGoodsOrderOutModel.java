/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.model;

import java.io.Serializable;

import com.myteay.common.util.tools.ToStringUtil;
import com.myteay.phoenix.common.util.enums.PxOrderStatusEnum;
import com.myteay.phoenix.common.util.enums.PxPayTypeEnum;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsTypeEnum;

/**
 * 商品订单流水模型
 * 
 * @author danlley
 * @version $Id: PxGoodsOrderOutModel.java, v 0.1 Mar 21, 2019 3:15:55 PM danlley Exp $
 */
public class PxGoodsOrderOutModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -3441348657141560685L;

    /** 订单流水号 */
    private String            id;

    /** 订单编号 */
    private String            orderNo;

    /** 会员ID */
    private String            userId;

    /** 商品ID */
    private String            goodsId;

    /** 店铺ID */
    private String            shopId;

    /** 店铺名称 */
    private String            shopName;

    /** 商品名称 */
    private String            goodsTitle;

    /** 订单状态 */
    private PxOrderStatusEnum orderStatus;

    /** 订单状态 */
    private PxPayTypeEnum     payType;

    /** 商品售价 */
    private String            goodsPrice;

    /** 商品数量 */
    private String            sellerAmount;

    /** 商品原价 */
    private String            goodsCommPrice;

    /** 商品类型 */
    private PxGoodsTypeEnum   goodsType;

    /** 创建时间 */
    private String            gmtCreated;

    /** 最后修改时间 */
    private String            gmtModified;

    /**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id value to be assigned to property id
     */
    public void setId(String id) {
        this.id = id;
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
     * Getter method for property <tt>orderStatus</tt>.
     * 
     * @return property value of orderStatus
     */
    public PxOrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    /**
     * Setter method for property <tt>orderStatus</tt>.
     * 
     * @param orderStatus value to be assigned to property orderStatus
     */
    public void setOrderStatus(PxOrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Getter method for property <tt>payType</tt>.
     * 
     * @return property value of payType
     */
    public PxPayTypeEnum getPayType() {
        return payType;
    }

    /**
     * Setter method for property <tt>payType</tt>.
     * 
     * @param payType value to be assigned to property payType
     */
    public void setPayType(PxPayTypeEnum payType) {
        this.payType = payType;
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
     * Getter method for property <tt>sellerAmount</tt>.
     * 
     * @return property value of sellerAmount
     */
    public String getSellerAmount() {
        return sellerAmount;
    }

    /**
     * Setter method for property <tt>sellerAmount</tt>.
     * 
     * @param sellerAmount value to be assigned to property sellerAmount
     */
    public void setSellerAmount(String sellerAmount) {
        this.sellerAmount = sellerAmount;
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
     * Getter method for property <tt>goodsType</tt>.
     * 
     * @return property value of goodsType
     */
    public PxGoodsTypeEnum getGoodsType() {
        return goodsType;
    }

    /**
     * Setter method for property <tt>goodsType</tt>.
     * 
     * @param goodsType value to be assigned to property goodsType
     */
    public void setGoodsType(PxGoodsTypeEnum goodsType) {
        this.goodsType = goodsType;
    }

    /**
     * Getter method for property <tt>gmtCreated</tt>.
     * 
     * @return property value of gmtCreated
     */
    public String getGmtCreated() {
        return gmtCreated;
    }

    /**
     * Setter method for property <tt>gmtCreated</tt>.
     * 
     * @param gmtCreated value to be assigned to property gmtCreated
     */
    public void setGmtCreated(String gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * Getter method for property <tt>gmtModified</tt>.
     * 
     * @return property value of gmtModified
     */
    public String getGmtModified() {
        return gmtModified;
    }

    /**
     * Setter method for property <tt>gmtModified</tt>.
     * 
     * @param gmtModified value to be assigned to property gmtModified
     */
    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
