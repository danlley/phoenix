/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 商品成本高级数据模型
 * 
 * @author danlley
 * @version $Id: PxGoodsCostCfgAdvDO.java, v 0.1 May 6, 2019 11:15:06 PM danlley Exp $
 */
public class PxGoodsCostCfgAdvDO implements Serializable {

    /**  */
    private static final long serialVersionUID = -5638846984636317691L;

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
    private String            goodsCost;

    /** 商品成本 */
    private String            goodsStatus;

    /** 创建时间 */
    private Date              gmtCreated;

    /** 最后修改时间 */
    private Date              gmtExpired;

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
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
