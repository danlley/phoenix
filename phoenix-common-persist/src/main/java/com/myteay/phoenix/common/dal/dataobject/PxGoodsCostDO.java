/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 商品成本信息记录数据模型
 * 
 * @author danlley
 * @version $Id: PxGoodsCostDO.java, v 0.1 May 7, 2019 11:27:10 PM danlley Exp $
 */
public class PxGoodsCostDO implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -1983131203521081106L;

    /** 商品ID */
    private String            goodsId;

    /** 商品名称 */
    private String            goodsTitle;

    /** 商品类别 */
    private String            goodsDesc;

    /** 店铺ID */
    private String            shopId;

    /** 当日实际销量（去除优惠） */
    private String            actrualSellerPrice;

    /** 当日实际单品销售数量 */
    private int               sellerAmount;

    /** 当日实际成本 */
    private String            actrualCost;

    /** 统计日期 */
    private String            reportDate;

    /** 创建日期 */
    private Date              gmtCreated;

    /** 修改日期 */
    private Date              gmtModified;

    /**
     * Getter method for property <tt>sellerAmount</tt>.
     * 
     * @return property value of sellerAmount
     */
    public int getSellerAmount() {
        return sellerAmount;
    }

    /**
     * Setter method for property <tt>sellerAmount</tt>.
     * 
     * @param sellerAmount value to be assigned to property sellerAmount
     */
    public void setSellerAmount(int sellerAmount) {
        this.sellerAmount = sellerAmount;
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
    public String getActrualSellerPrice() {
        return actrualSellerPrice;
    }

    /**
     * Setter method for property <tt>actrualSellerPrice</tt>.
     * 
     * @param actrualSellerPrice value to be assigned to property actrualSellerPrice
     */
    public void setActrualSellerPrice(String actrualSellerPrice) {
        this.actrualSellerPrice = actrualSellerPrice;
    }

    /**
     * Getter method for property <tt>actrualCost</tt>.
     * 
     * @return property value of actrualCost
     */
    public String getActrualCost() {
        return actrualCost;
    }

    /**
     * Setter method for property <tt>actrualCost</tt>.
     * 
     * @param actrualCost value to be assigned to property actrualCost
     */
    public void setActrualCost(String actrualCost) {
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
