/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.mobile;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsHuiyuanEnum;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsOrderTypeEnum;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsQuanEnum;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsTuanEnum;

/**
 * 用户手机端展示用的商品基础模型
 * 
 * @author min.weixm
 * @version $Id: PxMobileGoodsBaseModel.java, v 0.1 Aug 16, 2018 7:51:28 PM min.weixm Exp $
 */
public class PxMobileGoodsBaseModel implements Serializable {

    /** serialVersionUID */
    private static final long    serialVersionUID = 9144870818486609315L;

    /** 商品ID */
    private String               goodsId;

    /** 商品状态 */
    private String               goodsStatus;

    /** 商品摘要图片 */
    private String               goodsImage;

    /** 商品标题 */
    private String               goodsTitle;

    /** 套餐信息类别 */
    private String               goodsDesc;

    /** 商品售价 */
    private String               goodsPrice;

    /** 商品原价 */
    private String               goodsCommPrice;

    /** 营业时间 */
    private String               goodsOnlineTime;

    /** 订购类型，如：免预约 */
    private PxGoodsOrderTypeEnum orderType;

    /** 是否会员 */
    private PxGoodsHuiyuanEnum   isHuiyuan;

    /** 是否有券 */
    private PxGoodsQuanEnum      isQuan;

    /** 是否团购 */
    private PxGoodsTuanEnum      isTuan;

    /** 当前销量 */
    private String               goodsSellAmount;

    /** 商品过期时间 */
    private Date                 gmtGoodsExpired;

    /** 商品创建时间 */
    private Date                 gmtGoodsCreated;

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
    public PxGoodsOrderTypeEnum getOrderType() {
        return orderType;
    }

    /**
     * Setter method for property <tt>orderType</tt>.
     * 
     * @param orderType value to be assigned to property orderType
     */
    public void setOrderType(PxGoodsOrderTypeEnum orderType) {
        this.orderType = orderType;
    }

    /**
     * Getter method for property <tt>isHuiyuan</tt>.
     * 
     * @return property value of isHuiyuan
     */
    public PxGoodsHuiyuanEnum getIsHuiyuan() {
        return isHuiyuan;
    }

    /**
     * Setter method for property <tt>isHuiyuan</tt>.
     * 
     * @param isHuiyuan value to be assigned to property isHuiyuan
     */
    public void setIsHuiyuan(PxGoodsHuiyuanEnum isHuiyuan) {
        this.isHuiyuan = isHuiyuan;
    }

    /**
     * Getter method for property <tt>isQuan</tt>.
     * 
     * @return property value of isQuan
     */
    public PxGoodsQuanEnum getIsQuan() {
        return isQuan;
    }

    /**
     * Setter method for property <tt>isQuan</tt>.
     * 
     * @param isQuan value to be assigned to property isQuan
     */
    public void setIsQuan(PxGoodsQuanEnum isQuan) {
        this.isQuan = isQuan;
    }

    /**
     * Getter method for property <tt>isTuan</tt>.
     * 
     * @return property value of isTuan
     */
    public PxGoodsTuanEnum getIsTuan() {
        return isTuan;
    }

    /**
     * Setter method for property <tt>isTuan</tt>.
     * 
     * @param isTuan value to be assigned to property isTuan
     */
    public void setIsTuan(PxGoodsTuanEnum isTuan) {
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
    public Date getGmtGoodsExpired() {
        return gmtGoodsExpired;
    }

    /**
     * Setter method for property <tt>gmtGoodsExpired</tt>.
     * 
     * @param gmtGoodsExpired value to be assigned to property gmtGoodsExpired
     */
    public void setGmtGoodsExpired(Date gmtGoodsExpired) {
        this.gmtGoodsExpired = gmtGoodsExpired;
    }

    /**
     * Getter method for property <tt>gmtGoodsCreated</tt>.
     * 
     * @return property value of gmtGoodsCreated
     */
    public Date getGmtGoodsCreated() {
        return gmtGoodsCreated;
    }

    /**
     * Setter method for property <tt>gmtGoodsCreated</tt>.
     * 
     * @param gmtGoodsCreated value to be assigned to property gmtGoodsCreated
     */
    public void setGmtGoodsCreated(Date gmtGoodsCreated) {
        this.gmtGoodsCreated = gmtGoodsCreated;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}