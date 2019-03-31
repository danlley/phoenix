/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.myteay.common.util.tools.ToStringUtil;
import com.myteay.phoenix.common.util.enums.PxPayTypeEnum;
import com.myteay.phoenix.core.model.camp.CampShopPrizeOutModel;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;

/**
 * 订单流水处理模型
 * 
 * @author danlley
 * @version $Id: PxGoodsOrderModel.java, v 0.1 Feb 26, 2019 5:43:00 PM danlley Exp $
 */
public class PxGoodsOrderModel implements Serializable {

    /** serialVersionUID */
    private static final long             serialVersionUID         = -7943137818618714563L;

    /** 会员ID */
    private String                        userId;

    /** 订单编号 */
    private String                        orderNo;

    /** 店铺名称 */
    private String                        shopName;

    /** 支付方式 */
    private PxPayTypeEnum                 payType;

    /** 订单列表 */
    private List<PxGoodsModel>            pxGoodsModelList;

    /** 订单上下文（不接受外部传值）， 关键字取值：PxOrderContextKeyEnum */
    private transient Map<String, String> orderContext;

    /** 订单流水清单 */
    private List<PxGoodsOrderOutModel>    pxGoodsOrderOutModelList = new ArrayList<>();

    /** 中奖流水操作模型 */
    private CampShopPrizeOutModel         campShopPrizeOutModel;

    /**
     * Getter method for property <tt>campShopPrizeOutModel</tt>.
     * 
     * @return property value of campShopPrizeOutModel
     */
    public CampShopPrizeOutModel getCampShopPrizeOutModel() {
        return campShopPrizeOutModel;
    }

    /**
     * Setter method for property <tt>campShopPrizeOutModel</tt>.
     * 
     * @param campShopPrizeOutModel value to be assigned to property campShopPrizeOutModel
     */
    public void setCampShopPrizeOutModel(CampShopPrizeOutModel campShopPrizeOutModel) {
        this.campShopPrizeOutModel = campShopPrizeOutModel;
    }

    /**
     * Getter method for property <tt>pxGoodsOrderOutModelList</tt>.
     * 
     * @return property value of pxGoodsOrderOutModelList
     */
    public List<PxGoodsOrderOutModel> getPxGoodsOrderOutModelList() {
        return pxGoodsOrderOutModelList;
    }

    /**
     * Setter method for property <tt>pxGoodsOrderOutModelList</tt>.
     * 
     * @param pxGoodsOrderOutModelList value to be assigned to property pxGoodsOrderOutModelList
     */
    public void setPxGoodsOrderOutModelList(List<PxGoodsOrderOutModel> pxGoodsOrderOutModelList) {
        this.pxGoodsOrderOutModelList = pxGoodsOrderOutModelList;
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
     * Getter method for property <tt>pxGoodsModelList</tt>.
     * 
     * @return property value of pxGoodsModelList
     */
    public List<PxGoodsModel> getPxGoodsModelList() {
        return pxGoodsModelList;
    }

    /**
     * Setter method for property <tt>pxGoodsModelList</tt>.
     * 
     * @param pxGoodsModelList value to be assigned to property pxGoodsModelList
     */
    public void setPxGoodsModelList(List<PxGoodsModel> pxGoodsModelList) {
        this.pxGoodsModelList = pxGoodsModelList;
    }

    /**
     * Getter method for property <tt>orderContext</tt>.
     * 
     * @return property value of orderContext
     */
    public Map<String, String> getOrderContext() {
        return orderContext;
    }

    /**
     * Setter method for property <tt>orderContext</tt>.
     * 
     * @param orderContext value to be assigned to property orderContext
     */
    public void setOrderContext(Map<String, String> orderContext) {
        this.orderContext = orderContext;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
