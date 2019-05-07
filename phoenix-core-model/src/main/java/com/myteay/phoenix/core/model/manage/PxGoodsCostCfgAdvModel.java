/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsStatusEnum;

/**
 * 商品成本管理高级模型
 * 
 * @author danlley
 * @version $Id: PxGoodsCostCfgAdvModel.java, v 0.1 May 7, 2019 12:43:30 PM danlley Exp $
 */
public class PxGoodsCostCfgAdvModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -9073159445377653952L;

    /** 商品ID */
    private String            goodsId;

    /** 店铺ID */
    private String            shopId;

    /** 商品图片 */
    private String            goodsImage;

    /** 商品名称 */
    private String            goodsTitle;

    /** 商品类别 */
    private String            goodsDesc;

    /** 商品成本 */
    private String            goodsPrice;

    /** 商品成本 */
    private String            goodsCost        = "0.00";

    /** 商品成本 */
    private PxGoodsStatusEnum goodsStatus;

    /** 创建时间 */
    private Date              gmtCreated;

    /** 最后修改时间 */
    private Date              gmtExpired;

    /** 最后修改时间 */
    private Date              gmtModified;

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
     * Getter method for property <tt>goodsCost</tt>.
     * 
     * @return property value of goodsCost
     */
    public String getGoodsCost() {
        return goodsCost;
    }

    /**
     * Setter method for property <tt>goodsCost</tt>.
     * 
     * @param goodsCost value to be assigned to property goodsCost
     */
    public void setGoodsCost(String goodsCost) {
        this.goodsCost = goodsCost;
    }

    /**
     * Getter method for property <tt>goodsStatus</tt>.
     * 
     * @return property value of goodsStatus
     */
    public PxGoodsStatusEnum getGoodsStatus() {
        return goodsStatus;
    }

    /**
     * Setter method for property <tt>goodsStatus</tt>.
     * 
     * @param goodsStatus value to be assigned to property goodsStatus
     */
    public void setGoodsStatus(PxGoodsStatusEnum goodsStatus) {
        this.goodsStatus = goodsStatus;
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
