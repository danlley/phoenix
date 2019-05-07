/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.lang.Money;
import com.myteay.common.util.tools.ToStringUtil;

/**
 * 成本信息记录模型
 * 
 * @author danlley
 * @version $Id: PxGoodsCostModel.java, v 0.1 May 8, 2019 12:02:34 AM danlley Exp $
 */
public class PxGoodsCostModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -8554666390050267356L;

    /** 商品ID */
    private String            goodsId;

    /** 商品名称 */
    private String            goodsTitle;

    /** 商品类别 */
    private String            goodsDesc;

    /** 店铺ID */
    private String            shopId;

    /** 当日实际销量（去除优惠） */
    private Money             actrualSellerPrice;

    /** 当日实际成本 */
    private Money             actrualCost;

    /** 当日实际销量（去除优惠） */
    private String            actrualSellerPriceString;

    /** 当日实际成本 */
    private String            actrualCostString;

    /** 统计日期 */
    private String            reportDate;

    /** 创建日期 */
    private Date              gmtCreated;

    /** 修改日期 */
    private Date              gmtModified;

    /**
     * Getter method for property <tt>actrualSellerPriceString</tt>.
     * 
     * @return property value of actrualSellerPriceString
     */
    public String getActrualSellerPriceString() {
        return actrualSellerPrice.toString();
    }

    /**
     * Setter method for property <tt>actrualSellerPriceString</tt>.
     * 
     * @param actrualSellerPriceString value to be assigned to property actrualSellerPriceString
     */
    public void setActrualSellerPriceString(String actrualSellerPriceString) {
        this.actrualSellerPrice = new Money(actrualSellerPriceString);
        this.actrualSellerPriceString = this.actrualSellerPrice.toString();
    }

    /**
     * Getter method for property <tt>actrualCostString</tt>.
     * 
     * @return property value of actrualCostString
     */
    public String getActrualCostString() {
        return this.actrualCost.toString();
    }

    /**
     * Setter method for property <tt>actrualCostString</tt>.
     * 
     * @param actrualCostString value to be assigned to property actrualCostString
     */
    public void setActrualCostString(String actrualCostString) {
        this.actrualCost = new Money(actrualCostString);
        this.actrualCostString = this.actrualCost.toString();
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
     * Getter method for property <tt>actrualSellerPrice</tt>.
     * 
     * @return property value of actrualSellerPrice
     */
    public Money getActrualSellerPrice() {
        return actrualSellerPrice;
    }

    /**
     * Setter method for property <tt>actrualSellerPrice</tt>.
     * 
     * @param actrualSellerPrice value to be assigned to property actrualSellerPrice
     */
    public void setActrualSellerPrice(Money actrualSellerPrice) {
        this.actrualSellerPrice = actrualSellerPrice;
    }

    /**
     * Getter method for property <tt>actrualCost</tt>.
     * 
     * @return property value of actrualCost
     */
    public Money getActrualCost() {
        return actrualCost;
    }

    /**
     * Setter method for property <tt>actrualCost</tt>.
     * 
     * @param actrualCost value to be assigned to property actrualCost
     */
    public void setActrualCost(Money actrualCost) {
        this.actrualCost = actrualCost;
    }

    /**
     * Getter method for property <tt>reportDate</tt>.
     * 
     * @return property value of reportDate
     */
    public String getReportDate() {
        return reportDate;
    }

    /**
     * Setter method for property <tt>reportDate</tt>.
     * 
     * @param reportDate value to be assigned to property reportDate
     */
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
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
