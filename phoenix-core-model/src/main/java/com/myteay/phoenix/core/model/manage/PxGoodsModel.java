/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.myteay.common.util.tools.ToStringUtil;
import com.myteay.phoenix.common.util.enums.PxOperationTypeEnum;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsHuiyuanEnum;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsOrderTypeEnum;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsQuanEnum;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsStatusEnum;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsTuanEnum;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsTypeEnum;

/**
 * 商品概要模型
 * 
 * @author min.weixm
 * @version $Id: PxGoodsModel.java, v 0.1 Jul 26, 2018 11:19:30 AM min.weixm Exp $
 */
public class PxGoodsModel implements Serializable {

    /** serialVersionUID */
    private static final long    serialVersionUID = -6161013463657108959L;

    /** 商品流水号 */
    private String               goodsId;

    /** 店铺ID */
    private String               shopId;

    /** 商品缩略图 */
    private String               goodsImage;

    /** 商品标题 */
    private String               goodsTitle;

    /** 套餐信息类型 */
    private String               goodsDesc;

    /** 当前售价 */
    private String               goodsPrice;

    /** 原始价格 */
    private String               goodsCommPrice;

    /** 营业时间 */
    private String               goodsOnlineTime;

    /** 订购类型，如：免预约 */
    private PxGoodsOrderTypeEnum orderType;

    /** 商品类型，如：奶茶、汉堡、茶饮、套餐等 */
    private PxGoodsTypeEnum      goodsType;

    /** 是否会员 */
    private PxGoodsHuiyuanEnum   isHuiyuan;

    /** 是否有券 */
    private PxGoodsQuanEnum      isQuan;

    /** 是否团购 */
    private PxGoodsTuanEnum      isTuan;

    /** 操作类型 */
    private PxOperationTypeEnum  operationType;

    /** 当前销售量 */
    private String               goodsSellAmount;

    /** 商品过期时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date                 gmtExpired;

    /** 创建时间 */
    private Date                 gmtCreated;

    /** 最后修改时间 */
    private Date                 gmtModified;

    /** 商品状态 */
    private PxGoodsStatusEnum    goodsStatus;

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
