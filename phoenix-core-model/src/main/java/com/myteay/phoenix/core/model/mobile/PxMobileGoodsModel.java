/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.mobile;

import java.io.Serializable;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 用户手机端展示用的商品模型
 * 
 * @author min.weixm
 * @version $Id: PxMobileGoodsModel.java, v 0.1 Aug 16, 2018 7:46:24 PM min.weixm Exp $
 */
public class PxMobileGoodsModel implements Serializable {

    /** serialVersionUID */
    private static final long      serialVersionUID = -7840835410752681039L;

    /** 商品ID */
    private String                 goodsId;

    /** 商品基础模型 */
    private PxMobileGoodsBaseModel pxMobileGoodsBaseModel;

    /** 店铺手机端模型 */
    private PxMobileShopModel      pxMobileShopModel;

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
     * Getter method for property <tt>pxMobileGoodsBaseModel</tt>.
     * 
     * @return property value of pxMobileGoodsBaseModel
     */
    public PxMobileGoodsBaseModel getPxMobileGoodsBaseModel() {
        return pxMobileGoodsBaseModel;
    }

    /**
     * Setter method for property <tt>pxMobileGoodsBaseModel</tt>.
     * 
     * @param pxMobileGoodsBaseModel value to be assigned to property pxMobileGoodsBaseModel
     */
    public void setPxMobileGoodsBaseModel(PxMobileGoodsBaseModel pxMobileGoodsBaseModel) {
        this.pxMobileGoodsBaseModel = pxMobileGoodsBaseModel;
    }

    /**
     * Getter method for property <tt>pxMobileShopModel</tt>.
     * 
     * @return property value of pxMobileShopModel
     */
    public PxMobileShopModel getPxMobileShopModel() {
        return pxMobileShopModel;
    }

    /**
     * Setter method for property <tt>pxMobileShopModel</tt>.
     * 
     * @param pxMobileShopModel value to be assigned to property pxMobileShopModel
     */
    public void setPxMobileShopModel(PxMobileShopModel pxMobileShopModel) {
        this.pxMobileShopModel = pxMobileShopModel;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
