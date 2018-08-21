/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.mobile;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;
import com.myteay.phoenix.common.util.PxSalesAmountShowUtil;
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

    /** 订购类型说明，如：免预约 */
    private String               orderTypeShow;

    /** 是否会员说明 */
    private String               isHuiyuanShow;

    /** 是否有券说明 */
    private String               isQuanShow;

    /** 是否团购说明 */
    private String               isTuanShow;

    /** 是否会员说明 */
    private boolean              isHuiyuanFlag;

    /** 是否有券说明 */
    private boolean              isQuanFlag;

    /** 是否团购说明 */
    private boolean              isTuanFlag;

    /** 当前销量 */
    private String               goodsSellAmount;

    /** 当前销量(用于展示) */
    private String               goodsSellAmountShow;

    /** 当前销量(星型展示) */
    private boolean[]            goodsStarShow;

    /** 商品过期时间 */
    private Date                 gmtGoodsExpired;

    /** 商品创建时间 */
    private Date                 gmtGoodsCreated;

    /**
     * Getter method for property <tt>goodsStarShow</tt>.
     * 
     * @return property value of goodsStarShow
     */
    public boolean[] getGoodsStarShow() {
        return goodsStarShow;
    }

    /**
     * Setter method for property <tt>goodsStarShow</tt>.
     * 
     * @param goodsStarShow value to be assigned to property goodsStarShow
     */
    public void setGoodsStarShow(boolean[] goodsStarShow) {
        this.goodsStarShow = goodsStarShow;
    }

    /**
     * Getter method for property <tt>goodsSellAmountShow</tt>.
     * 
     * @return property value of goodsSellAmountShow
     */
    public String getGoodsSellAmountShow() {
        return goodsSellAmountShow;
    }

    /**
     * Setter method for property <tt>goodsSellAmountShow</tt>.
     * 
     * @param goodsSellAmountShow value to be assigned to property goodsSellAmountShow
     */
    public void setGoodsSellAmountShow(String goodsSellAmountShow) {
        this.goodsSellAmountShow = goodsSellAmountShow;
    }

    /**
     * Getter method for property <tt>isHuiyuanFlag</tt>.
     * 
     * @return property value of isHuiyuanFlag
     */
    public boolean isHuiyuanFlag() {
        return isHuiyuanFlag;
    }

    /**
     * Setter method for property <tt>isHuiyuanFlag</tt>.
     * 
     * @param isHuiyuanFlag value to be assigned to property isHuiyuanFlag
     */
    public void setHuiyuanFlag(boolean isHuiyuanFlag) {
        this.isHuiyuanFlag = isHuiyuanFlag;
    }

    /**
     * Getter method for property <tt>isQuanFlag</tt>.
     * 
     * @return property value of isQuanFlag
     */
    public boolean isQuanFlag() {
        return isQuanFlag;
    }

    /**
     * Setter method for property <tt>isQuanFlag</tt>.
     * 
     * @param isQuanFlag value to be assigned to property isQuanFlag
     */
    public void setQuanFlag(boolean isQuanFlag) {
        this.isQuanFlag = isQuanFlag;
    }

    /**
     * Getter method for property <tt>isTuanFlag</tt>.
     * 
     * @return property value of isTuanFlag
     */
    public boolean isTuanFlag() {
        return isTuanFlag;
    }

    /**
     * Setter method for property <tt>isTuanFlag</tt>.
     * 
     * @param isTuanFlag value to be assigned to property isTuanFlag
     */
    public void setTuanFlag(boolean isTuanFlag) {
        this.isTuanFlag = isTuanFlag;
    }

    /**
     * Getter method for property <tt>orderTypeShow</tt>.
     * 
     * @return property value of orderTypeShow
     */
    public String getOrderTypeShow() {
        return orderTypeShow;
    }

    /**
     * Setter method for property <tt>orderTypeShow</tt>.
     * 
     * @param orderTypeShow value to be assigned to property orderTypeShow
     */
    public void setOrderTypeShow(String orderTypeShow) {
        this.orderTypeShow = orderTypeShow;
    }

    /**
     * Getter method for property <tt>isHuiyuanShow</tt>.
     * 
     * @return property value of isHuiyuanShow
     */
    public String getIsHuiyuanShow() {
        return isHuiyuanShow;
    }

    /**
     * Setter method for property <tt>isHuiyuanShow</tt>.
     * 
     * @param isHuiyuanShow value to be assigned to property isHuiyuanShow
     */
    public void setIsHuiyuanShow(String isHuiyuanShow) {
        this.isHuiyuanShow = isHuiyuanShow;
    }

    /**
     * Getter method for property <tt>isQuanShow</tt>.
     * 
     * @return property value of isQuanShow
     */
    public String getIsQuanShow() {
        return isQuanShow;
    }

    /**
     * Setter method for property <tt>isQuanShow</tt>.
     * 
     * @param isQuanShow value to be assigned to property isQuanShow
     */
    public void setIsQuanShow(String isQuanShow) {
        this.isQuanShow = isQuanShow;
    }

    /**
     * Getter method for property <tt>isTuanShow</tt>.
     * 
     * @return property value of isTuanShow
     */
    public String getIsTuanShow() {
        return isTuanShow;
    }

    /**
     * Setter method for property <tt>isTuanShow</tt>.
     * 
     * @param isTuanShow value to be assigned to property isTuanShow
     */
    public void setIsTuanShow(String isTuanShow) {
        this.isTuanShow = isTuanShow;
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

        if (orderType != null) {
            this.setOrderTypeShow(orderType.getMessage());
        }
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

        if (isHuiyuan != null) {
            this.setIsHuiyuanShow(isHuiyuan.getMessage());

            if (isHuiyuan == PxGoodsHuiyuanEnum.PX_SUPPORT_HUIYUAN) {
                this.setHuiyuanFlag(true);
            }
        }
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

        if (isQuan != null) {
            this.setIsQuanShow(isQuan.getMessage());

            if (isQuan == PxGoodsQuanEnum.PX_HAS_QUAN) {
                this.setQuanFlag(true);
            }
        }
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

        if (isTuan != null) {
            this.setIsTuanShow(isTuan.getMessage());

            if (isTuan == PxGoodsTuanEnum.PX_HAS_TUAN) {
                this.setTuanFlag(true);
            }
        }
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
        this.setGoodsSellAmountShow(PxSalesAmountShowUtil.getSalesAmountShow(goodsSellAmount));
        this.setGoodsStarShow(PxSalesAmountShowUtil.getStarConfig(goodsSellAmount));
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
